package org.toxsoft.skf.modbus.lib.cfg.bridge.impl;

import org.toxsoft.core.tslib.av.opset.*;
import org.toxsoft.core.tslib.coll.primtypes.*;
import org.toxsoft.core.tslib.coll.primtypes.impl.*;
import org.toxsoft.core.tslib.utils.errors.*;
import org.toxsoft.skf.modbus.lib.cfg.bridge.*;

/**
 * {@link IMbNodeCfg} base implementation.
 *
 * @author hazard157
 */
class MbNodeCfg<P extends MbBusCfg>
    extends MbXxxCfg<P>
    implements IMbNodeCfg {

  private static final String OPID_DEVICE_ID = "ModbusDeviceId"; //$NON-NLS-1$

  public MbNodeCfg( P aParent, String aNodeId, String aDeviceId, IOptionSet aParams, IStringList aFixedOpIds ) {
    super( aParent, aNodeId, aParams, addSelfOpIds( aFixedOpIds ) );
    TsNullArgumentRtException.checkNulls( aParent, aNodeId, aDeviceId );
    TsItemAlreadyExistsRtException.checkTrue( aParent.listNodeCfgs().hasKey( aNodeId ) );
    TsItemAlreadyExistsRtException.checkTrue( aParent.parent().listUsedDevices().hasKey( aDeviceId ) );
    params().setStr( aNodeId, aDeviceId );
  }

  static IStringList addSelfOpIds( IStringList aFixedOpIds ) {
    IStringListEdit ll = new StringLinkedBundleList( aFixedOpIds );
    ll.add( OPID_DEVICE_ID );
    return ll;
  }

  // ------------------------------------------------------------------------------------
  // IMbNodeCfg
  //

  @Override
  public String deviceId() {
    return params().getStr( OPID_DEVICE_ID );
  }

  @Override
  public boolean isRtu() {
    return parent().isRtu();
  }

  // ------------------------------------------------------------------------------------
  // Comparable
  //

  @Override
  public int compareTo( IMbNodeCfg aThat ) {
    if( aThat == null ) {
      throw new NullPointerException();
    }
    if( aThat == this ) {
      return 0;
    }
    String bridgeId = parent().parent().id();
    int c = bridgeId.compareTo( aThat.parent().parent().id() );
    if( c == 0 ) {
      String busId = parent().id();
      c = busId.compareTo( aThat.parent().id() );
      if( c == 0 ) {
        if( this.isRtu() != aThat.isRtu() ) {
          c = this.isRtu() ? -1 : +1; // RTU before TCP nodes
        }
        else {
          c = id().compareTo( aThat.id() );
        }
      }
    }
    return c;
  }

}
