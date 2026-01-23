package org.toxsoft.skf.modbus.gui.m5.device;

import static org.toxsoft.core.tsgui.m5.IM5Constants.*;

import org.toxsoft.core.tsgui.m5.*;
import org.toxsoft.core.tsgui.m5.model.impl.*;
import org.toxsoft.core.tslib.av.*;
import org.toxsoft.core.tslib.av.metainfo.*;
import org.toxsoft.core.tslib.av.opset.*;
import org.toxsoft.core.tslib.av.opset.impl.*;
import org.toxsoft.core.tslib.bricks.validator.*;
import org.toxsoft.core.tslib.coll.*;
import org.toxsoft.core.tslib.utils.errors.*;
import org.toxsoft.skf.modbus.gui.e4.services.*;
import org.toxsoft.skf.modbus.lib.cfg.device.*;

/**
 * LM for {@link MbDeviceCfgM5Model}.
 *
 * @author hazard157
 */
class MbDeviceCfgM5LifecycleManager
    extends M5LifecycleManager<MbDeviceCfg, IModbusDeviceCfgRegistry> {

  public MbDeviceCfgM5LifecycleManager( IM5Model<MbDeviceCfg> aModel, IModbusDeviceCfgRegistry aMaster ) {
    super( aModel, true, true, true, true, aMaster );
    TsNullArgumentRtException.checkNull( aMaster );
  }

  // ------------------------------------------------------------------------------------
  // implementation
  //

  private static MbDeviceCfg make( IM5Bunch<MbDeviceCfg> aValues ) {
    String id = aValues.getAsAv( FID_ID ).asString();
    IOptionSetEdit p = new OptionSet();
    for( IDataDef dd : IMbDeviceCfgConstants.MB_DEV_ALL_OPDEFS ) {
      IAtomicValue av = aValues.getAsAv( dd.id() );
      p.setValue( dd, av );
    }
    IList<MbRegisterCfg> llRegs = aValues.get( MbDeviceCfgM5Model.FID_REGISTERS );
    return new MbDeviceCfg( id, p, llRegs );

  }

  // ------------------------------------------------------------------------------------
  // M5LifecycleManager
  //

  @Override
  protected ValidationResult doBeforeCreate( IM5Bunch<MbDeviceCfg> aValues ) {
    MbDeviceCfg cfg = make( aValues );
    return master().svs().validator().canAddDeviceCfg( cfg );
  }

  @Override
  protected MbDeviceCfg doCreate( IM5Bunch<MbDeviceCfg> aValues ) {
    MbDeviceCfg cfg = make( aValues );
    master().addDeviceCfg( cfg );
    return cfg;
  }

  @Override
  protected ValidationResult doBeforeEdit( IM5Bunch<MbDeviceCfg> aValues ) {
    MbDeviceCfg cfg = make( aValues );
    return master().svs().validator().canReplaceDeviceCfg( aValues.originalEntity().id(), cfg );
  }

  @Override
  protected MbDeviceCfg doEdit( IM5Bunch<MbDeviceCfg> aValues ) {
    MbDeviceCfg cfg = make( aValues );
    master().replaceDeviceCfg( aValues.originalEntity().id(), cfg );
    return cfg;
  }

  @Override
  protected ValidationResult doBeforeRemove( MbDeviceCfg aEntity ) {
    return master().svs().validator().canRemoveDeviceCfg( aEntity.id() );
  }

  @Override
  protected void doRemove( MbDeviceCfg aEntity ) {
    master().removeDeviceCfg( aEntity.id() );
  }

  @Override
  protected IList<MbDeviceCfg> doListEntities() {
    return master().list();
  }

}
