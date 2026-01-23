package org.toxsoft.skf.modbus.gui.glib.bridge;

import static org.toxsoft.skf.modbus.gui.ISkfModbusGuiConstants.*;
import static org.toxsoft.skf.modbus.gui.l10n.ISkfModbusGuiSharedResources.*;

import org.toxsoft.core.tslib.bricks.keeper.*;
import org.toxsoft.core.tslib.bricks.keeper.std.*;
import org.toxsoft.core.tslib.bricks.strid.*;
import org.toxsoft.core.tslib.bricks.strid.coll.*;
import org.toxsoft.core.tslib.bricks.strid.coll.impl.*;
import org.toxsoft.core.tslib.utils.errors.*;
import org.toxsoft.core.tslib.utils.gui.*;
import org.toxsoft.skf.modbus.lib.cfg.bridge.*;

/**
 * What kind of entity the path {@link MbTreePath} points to.
 *
 * @author hazard157
 */
public enum EMbNodePathKind
    implements IStridable, IIconIdable {

  /**
   * Points to the root container of all bridges configuration.
   * <p>
   * There is a singleton path {@link MbNodePath#ROOT_PATH} of this kind.
   */
  ROOT( "root", STR_MB_NPK_ROOT, STR_MB_NPK_ROOT_D, ICONID_MODBUS_LOGO ), //$NON-NLS-1$

  /**
   * Points the {@link IMbBridgeCfg} with ID {@link MbNodePath#bridgeId()}.
   */
  BRIDGE( "bridge", STR_MB_NPK_BRIDGE, STR_MB_NPK_BRIDGE_D, ICONID_MB_BRIDGE ), //$NON-NLS-1$

  /**
   * Points the {@link IMbBusCfg} with ID {@link MbNodePath#busId()} in the bridge {@link MbNodePath#bridgeId()}.
   */
  BUS( "bus", STR_MB_NPK_BUS, STR_MB_NPK_BUS_D, ICONID_MB_BUS ), //$NON-NLS-1$

  /**
   * Points the {@link IMbNodeCfg} with ID {@link MbNodePath#nodeId()} on the bus {@link MbNodePath#busId()} in the
   * bridge {@link MbNodePath#bridgeId()}.
   */
  NODE( "node", STR_MB_NPK_NODE, STR_MB_NPK_NODE_D, ICONID_MB_NODE ); //$NON-NLS-1$

  /**
   * The registered keeper ID.
   */
  public static final String KEEPER_ID = "EMbNodePathKind"; //$NON-NLS-1$

  /**
   * The keeper singleton.
   */
  public static final IEntityKeeper<EMbNodePathKind> KEEPER = new StridableEnumKeeper<>( EMbNodePathKind.class );

  private static IStridablesListEdit<EMbNodePathKind> list = null;

  private final String id;
  private final String name;
  private final String description;
  private final String iconId;

  EMbNodePathKind( String aId, String aName, String aDescription, String aIconId ) {
    id = aId;
    name = aName;
    description = aDescription;
    iconId = aIconId;
  }

  // --------------------------------------------------------------------------
  // IStridable
  //

  @Override
  public String id() {
    return id;
  }

  @Override
  public String nmName() {
    return name;
  }

  @Override
  public String description() {
    return description;
  }

  // ------------------------------------------------------------------------------------
  // IIconIdable
  //

  @Override
  public String iconId() {
    return iconId;
  }

  // ------------------------------------------------------------------------------------
  // API
  //

  /**
   * Returns icon ID for the specifi bus type, if constants supports different bus types.
   * <p>
   * For constants {@link #ROOT} and {@link #BRIDGE} returns {@link #iconId()},
   *
   * @param isRtu boolean - bus kind <br>
   *          <b>true</b> - is serial (RS-485) Modbus/RTU bus or node on such bus;<br>
   *          <b>false</b> - is Ethernet Modbus/TCP bus or node on such bus.
   * @return String - icon ID, specific for specified bus or {@link #iconId()}
   */
  public String getIconIdForBusType( boolean isRtu ) {
    return switch( this ) {
      case BUS -> isRtu ? ICONID_MB_BUS_RTU : ICONID_MB_BUS_TCP;
      case NODE -> isRtu ? ICONID_MB_NODE_RTU : ICONID_MB_NODE_TCP;
      case BRIDGE, ROOT -> iconId();
    };
  }

  // ----------------------------------------------------------------------------------
  // Stridable enum common API
  //

  /**
   * Returns all constants in single list.
   *
   * @return {@link IStridablesList}&lt; {@link EMbNodePathKind} &gt; - list of constants in order of declaraion
   */
  public static IStridablesList<EMbNodePathKind> asList() {
    if( list == null ) {
      list = new StridablesList<>( values() );
    }
    return list;
  }

  /**
   * Returns the constant by the ID.
   *
   * @param aId String - the ID
   * @return {@link EMbNodePathKind} - found constant
   * @throws TsNullArgumentRtException any argument = <code>null</code>
   * @throws TsItemNotFoundRtException no constant found by specified ID
   */
  public static EMbNodePathKind getById( String aId ) {
    return asList().getByKey( aId );
  }

  /**
   * Finds the constant by the name.
   *
   * @param aName String - the name
   * @return {@link EMbNodePathKind} - found constant or <code>null</code>
   * @throws TsNullArgumentRtException any argument = <code>null</code>
   */
  public static EMbNodePathKind findByName( String aName ) {
    TsNullArgumentRtException.checkNull( aName );
    for( EMbNodePathKind item : values() ) {
      if( item.name.equals( aName ) ) {
        return item;
      }
    }
    return null;
  }

  /**
   * Returns the constant by the name.
   *
   * @param aName String - the name
   * @return {@link EMbNodePathKind} - found constant
   * @throws TsNullArgumentRtException any argument = <code>null</code>
   * @throws TsItemNotFoundRtException no constant found by specified name
   */
  public static EMbNodePathKind getByName( String aName ) {
    return TsItemNotFoundRtException.checkNull( findByName( aName ) );
  }

}
