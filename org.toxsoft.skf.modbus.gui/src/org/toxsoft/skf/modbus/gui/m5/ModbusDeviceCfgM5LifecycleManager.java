package org.toxsoft.skf.modbus.gui.m5;

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
 * LM for {@link ModbusDeviceCfgM5Model}.
 *
 * @author hazard157
 */
class ModbusDeviceCfgM5LifecycleManager
    extends M5LifecycleManager<ModbusDeviceCfg, IModbusDeviceCfgRegistry> {

  public ModbusDeviceCfgM5LifecycleManager( IM5Model<ModbusDeviceCfg> aModel, IModbusDeviceCfgRegistry aMaster ) {
    super( aModel, true, true, true, true, aMaster );
    TsNullArgumentRtException.checkNull( aMaster );
  }

  // ------------------------------------------------------------------------------------
  // implementation
  //

  private static ModbusDeviceCfg make( IM5Bunch<ModbusDeviceCfg> aValues ) {
    String id = aValues.getAsAv( FID_ID ).asString();
    IOptionSetEdit p = new OptionSet();
    for( IDataDef dd : IModbusDeviceCfgConstants.ALL_DEVICE_OPDEFS ) {
      IAtomicValue av = aValues.getAsAv( dd.id() );
      p.setValue( dd, av );
    }
    IList<ModbusRegisterCfg> llRegs = aValues.get( ModbusDeviceCfgM5Model.FID_REGISTERS );
    return new ModbusDeviceCfg( id, p, llRegs );

  }

  // ------------------------------------------------------------------------------------
  // M5LifecycleManager
  //

  @Override
  protected ValidationResult doBeforeCreate( IM5Bunch<ModbusDeviceCfg> aValues ) {
    ModbusDeviceCfg cfg = make( aValues );
    return master().svs().validator().canAddDeviceCfg( cfg );
  }

  @Override
  protected ModbusDeviceCfg doCreate( IM5Bunch<ModbusDeviceCfg> aValues ) {
    ModbusDeviceCfg cfg = make( aValues );
    master().addDeviceCfg( cfg );
    return cfg;
  }

  @Override
  protected ValidationResult doBeforeEdit( IM5Bunch<ModbusDeviceCfg> aValues ) {
    ModbusDeviceCfg cfg = make( aValues );
    return master().svs().validator().canReplaceDeviceCfg( aValues.originalEntity().id(), cfg );
  }

  @Override
  protected ModbusDeviceCfg doEdit( IM5Bunch<ModbusDeviceCfg> aValues ) {
    ModbusDeviceCfg cfg = make( aValues );
    master().replaceDeviceCfg( aValues.originalEntity().id(), cfg );
    return cfg;
  }

  @Override
  protected ValidationResult doBeforeRemove( ModbusDeviceCfg aEntity ) {
    return master().svs().validator().canRemoveDeviceCfg( aEntity.id() );
  }

  @Override
  protected void doRemove( ModbusDeviceCfg aEntity ) {
    master().removeDeviceCfg( aEntity.id() );
  }

  @Override
  protected IList<ModbusDeviceCfg> doListEntities() {
    return master().list();
  }

}
