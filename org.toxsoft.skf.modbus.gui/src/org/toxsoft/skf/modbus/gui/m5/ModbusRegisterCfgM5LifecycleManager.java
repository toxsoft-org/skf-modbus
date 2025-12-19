package org.toxsoft.skf.modbus.gui.m5;

import static org.toxsoft.skf.modbus.gui.m5.ModbusRegisterCfgM5Model.*;

import org.toxsoft.core.tsgui.m5.*;
import org.toxsoft.core.tsgui.m5.model.impl.*;
import org.toxsoft.core.tslib.av.*;
import org.toxsoft.core.tslib.av.metainfo.*;
import org.toxsoft.core.tslib.av.opset.*;
import org.toxsoft.core.tslib.av.opset.impl.*;
import org.toxsoft.core.tslib.bricks.validator.*;
import org.toxsoft.skf.modbus.lib.cfg.device.*;
import org.toxsoft.skf.modbus.lib.mbspec.*;

/**
 * LM for {@link ModbusRegisterCfgM5Model}.
 * <p>
 * THis LM is intended only to list {@link ModbusDeviceCfg#registerCfs()}, no other CRUD operations are allowed.
 *
 * @author hazard157
 */
class ModbusRegisterCfgM5LifecycleManager
    extends M5LifecycleManager<ModbusRegisterCfg, Object> {

  public ModbusRegisterCfgM5LifecycleManager( IM5Model<ModbusRegisterCfg> aModel ) {
    super( aModel, true, true, true, false, null );
  }

  // ------------------------------------------------------------------------------------
  // implementation
  //

  private static ValidationResult canMakeCfg( IM5Bunch<ModbusRegisterCfg> aValues ) {
    int regNo = aValues.getAsAv( FID_REG_NO ).asInt();
    EModbusRegisterKind kind = aValues.getAsAv( FID_KIND ).asValobj();
    IOptionSetEdit params = new OptionSet();
    OptionSetUtils.initOptionSet( params, IModbusDeviceCfgConstants.ALL_REGISTER_OPDEFS );
    for( IDataDef dd : IModbusDeviceCfgConstants.ALL_REGISTER_OPDEFS ) {
      if( aValues.hasField( dd.id() ) ) {
        IAtomicValue av = aValues.getAsAv( dd.id() );
        params.setValue( dd, av );
      }
    }
    return ModbusRegisterCfg.canCreate( regNo, kind, params );
  }

  private static ModbusRegisterCfg makeCfg( IM5Bunch<ModbusRegisterCfg> aValues ) {
    int regNo = aValues.getAsAv( FID_REG_NO ).asInt();
    EModbusRegisterKind kind = aValues.getAsAv( FID_KIND ).asValobj();
    IOptionSetEdit params = new OptionSet();
    OptionSetUtils.initOptionSet( params, IModbusDeviceCfgConstants.ALL_REGISTER_OPDEFS );
    for( IDataDef dd : IModbusDeviceCfgConstants.ALL_REGISTER_OPDEFS ) {
      if( aValues.hasField( dd.id() ) ) {
        IAtomicValue av = aValues.getAsAv( dd.id() );
        params.setValue( dd, av );
      }
    }
    return new ModbusRegisterCfg( regNo, kind, params );
  }

  // ------------------------------------------------------------------------------------
  // M5LifecycleManager
  //

  @Override
  protected ValidationResult doBeforeCreate( IM5Bunch<ModbusRegisterCfg> aValues ) {
    return canMakeCfg( aValues );
  }

  @Override
  protected ModbusRegisterCfg doCreate( IM5Bunch<ModbusRegisterCfg> aValues ) {
    return makeCfg( aValues );
  }

  @Override
  protected ValidationResult doBeforeEdit( IM5Bunch<ModbusRegisterCfg> aValues ) {
    return canMakeCfg( aValues );
  }

  @Override
  protected ModbusRegisterCfg doEdit( IM5Bunch<ModbusRegisterCfg> aValues ) {
    return makeCfg( aValues );
  }

  @Override
  protected ValidationResult doBeforeRemove( ModbusRegisterCfg aEntity ) {
    return ValidationResult.SUCCESS;
  }

  @Override
  protected void doRemove( ModbusRegisterCfg aEntity ) {
    // nop
  }

}
