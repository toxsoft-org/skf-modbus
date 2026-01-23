package org.toxsoft.skf.modbus.gui.glib.bridge_old;

import java.util.*;

import org.toxsoft.core.tslib.bricks.strid.impl.*;
import org.toxsoft.core.tslib.utils.*;
import org.toxsoft.core.tslib.utils.errors.*;

/**
 * Denotes the entity in the configuration tree MODBUS Bridge - Bus - Node.
 *
 * @author hazard157
 */
public final class MbNodePath
    implements Comparable<MbNodePath> {

  /**
   * Singleton of the kind {@link EMbNodePathKind#ROOT}.
   */
  public static final MbNodePath ROOT_PATH = new MbNodePath();

  private final EMbNodePathKind kind;
  private final String          bridgeId;
  private final String          busId;
  private final String          nodeId;

  private MbNodePath() {
    bridgeId = null;
    busId = null;
    nodeId = null;
    kind = EMbNodePathKind.ROOT;
  }

  private MbNodePath( String aBridgeId ) {
    bridgeId = StridUtils.checkValidIdPath( aBridgeId );
    busId = null;
    nodeId = null;
    kind = EMbNodePathKind.BRIDGE;
  }

  private MbNodePath( String aBridgeId, String aBusId ) {
    bridgeId = StridUtils.checkValidIdPath( aBridgeId );
    busId = StridUtils.checkValidIdPath( aBusId );
    nodeId = null;
    kind = EMbNodePathKind.BUS;
  }

  private MbNodePath( String aBridgeId, String aBusId, String aNodeId ) {
    bridgeId = StridUtils.checkValidIdPath( aBridgeId );
    busId = StridUtils.checkValidIdPath( aBusId );
    nodeId = StridUtils.checkValidIdPath( aNodeId );
    kind = EMbNodePathKind.NODE;
  }

  /**
   * Creates path of the specified kind.
   *
   * @param aKind {@link EMbNodePathKind} - the requested kind
   * @param aBridgeId String - bridge ID or <code>null</code>
   * @param aBusId String - bus ID or <code>null</code>
   * @param aNodeId String - node ID or <code>null</code>
   * @return {@link MbNodePath} - created instance
   * @throws TsNullArgumentRtException any argument = <code>null</code>
   * @throws TsIllegalArgumentRtException any non <code>null</code> ID is not an IDpath
   */
  public static MbNodePath of( EMbNodePathKind aKind, String aBridgeId, String aBusId, String aNodeId ) {
    TsNullArgumentRtException.checkNull( aKind );
    return switch( aKind ) {
      case ROOT -> ROOT_PATH;
      case BRIDGE -> new MbNodePath( aBridgeId );
      case BUS -> new MbNodePath( aBridgeId, aBusId );
      case NODE -> new MbNodePath( aBridgeId, aBusId, aNodeId );
    };
  }

  /**
   * Creates path of kind {@link EMbNodePathKind#BRIDGE}.
   *
   * @param aBridgeId String - the bridge ID
   * @return {@link MbNodePath} - created instance
   * @throws TsNullArgumentRtException any argument = <code>null</code>
   * @throws TsIllegalArgumentRtException ID is not an IDpath
   */
  public static MbNodePath ofBridge( String aBridgeId ) {
    return new MbNodePath( aBridgeId );
  }

  /**
   * Creates path of kind {@link EMbNodePathKind#BUS}.
   *
   * @param aBridgeId String - the bridge ID
   * @param aBusId String - the bus ID
   * @return {@link MbNodePath} - created instance
   * @throws TsNullArgumentRtException any argument = <code>null</code>
   * @throws TsIllegalArgumentRtException any ID is not an IDpath
   */
  public static MbNodePath ofBus( String aBridgeId, String aBusId ) {
    return new MbNodePath( aBridgeId, aBusId );
  }

  /**
   * Creates path of kind {@link EMbNodePathKind#NODE}.
   *
   * @param aBridgeId String - the bridge ID
   * @param aBusId String - the bus ID
   * @param aNodeId String - the node ID
   * @return {@link MbNodePath} - created instance
   * @throws TsNullArgumentRtException any argument = <code>null</code>
   * @throws TsIllegalArgumentRtException any ID is not an IDpath
   */
  public static MbNodePath ofNode( String aBridgeId, String aBusId, String aNodeId ) {
    return new MbNodePath( aBridgeId, aBusId, aNodeId );
  }

  // ------------------------------------------------------------------------------------
  // API
  //

  /**
   * Determines the kind of entity this path points to.
   *
   * @return {@link EMbNodePathKind} - the pointed entity kind
   */
  public EMbNodePathKind kind() {
    return kind;
  }

  /**
   * Returns the MODBUS bridge ID.
   *
   * @return String - bridge ID or <code>null</code>
   */
  public String bridgeId() {
    return bridgeId;
  }

  /**
   * Returns the MODBUS bus ID.
   *
   * @return String - bus ID or <code>null</code>
   */
  public String busId() {
    return busId;
  }

  /**
   * Returns the MODBUS node ID.
   *
   * @return String - node ID or <code>null</code>
   */
  public String nodeId() {
    return nodeId;
  }

  /**
   * Returns path to the parent.
   *
   * @return {@link MbNodePath} - the parent path
   * @throws TsUnsupportedFeatureRtException method is called for {@link #ROOT_PATH}
   */
  public MbNodePath createParent() {
    return switch( kind ) {
      case ROOT -> throw new TsUnsupportedFeatureRtException();
      case BRIDGE -> ROOT_PATH;
      case BUS -> ofBridge( bridgeId );
      case NODE -> ofBus( bridgeId, busId );
      default -> throw new TsNotAllEnumsUsedRtException( kind.id() );
    };
  }

  // ------------------------------------------------------------------------------------
  // Object
  //

  @Override
  public String toString() {
    return kind.name() + ": " + switch( kind ) { //$NON-NLS-1$
      case ROOT -> "---"; //$NON-NLS-1$
      case BRIDGE -> bridgeId;
      case BUS -> bridgeId + '/' + busId;
      case NODE -> bridgeId + '/' + busId + '/' + nodeId;
    };
  }

  @Override
  public boolean equals( Object aThat ) {
    if( aThat == this ) {
      return true;
    }
    if( aThat instanceof MbNodePath that ) {
      return this.kind == that.kind && //
          Objects.equals( this.bridgeId, that.bridgeId ) && //
          Objects.equals( this.bridgeId, that.bridgeId ) && //
          Objects.equals( this.bridgeId, that.bridgeId );
    }
    return false;
  }

  @Override
  public int hashCode() {
    int result = TsLibUtils.INITIAL_HASH_CODE;
    result = TsLibUtils.PRIME * result + kind.hashCode();
    result = bridgeId != null ? TsLibUtils.PRIME * result + bridgeId.hashCode() : 0;
    result = busId != null ? TsLibUtils.PRIME * result + busId.hashCode() : 0;
    result = nodeId != null ? TsLibUtils.PRIME * result + nodeId.hashCode() : 0;
    return result;
  }

  // ------------------------------------------------------------------------------------
  // Comparable
  //

  @Override
  public int compareTo( MbNodePath aThat ) {
    if( aThat == null ) {
      throw new NullPointerException();
    }
    if( aThat == this ) {
      return 0;
    }
    int c = this.kind.compareTo( aThat.kind );
    if( c == 0 && this.bridgeId != null ) {
      c = this.bridgeId.compareTo( aThat.bridgeId );
      if( c == 0 && this.busId != null ) {
        c = this.busId.compareTo( aThat.busId );
        if( c == 0 && this.nodeId != null ) {
          c = this.nodeId.compareTo( aThat.nodeId );
        }
      }
    }
    return c;
  }

}
