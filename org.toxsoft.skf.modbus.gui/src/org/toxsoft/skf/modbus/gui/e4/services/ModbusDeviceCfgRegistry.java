package org.toxsoft.skf.modbus.gui.e4.services;

import static org.toxsoft.skf.modbus.gui.l10n.ISkfModbusGuiSharedResources.*;

import org.toxsoft.core.tslib.bricks.events.change.*;
import org.toxsoft.core.tslib.bricks.strid.coll.*;
import org.toxsoft.core.tslib.bricks.strid.coll.impl.*;
import org.toxsoft.core.tslib.bricks.validator.*;
import org.toxsoft.core.tslib.bricks.validator.impl.*;
import org.toxsoft.core.tslib.bricks.validator.std.*;
import org.toxsoft.core.tslib.utils.errors.*;
import org.toxsoft.skf.modbus.lib.cfg.device.*;

/**
 * {@link IModbusDeviceCfgRegistry} implementation.
 *
 * @author hazard157
 */
public class ModbusDeviceCfgRegistry
    implements IModbusDeviceCfgRegistry {

  /**
   * {@link IModbusDeviceCfgRegistry#svs()} implementation.
   *
   * @author hazard157
   */
  class ValidationSupport
      extends AbstractTsValidationSupport<IModbusDeviceCfgRegistryValidator>
      implements IModbusDeviceCfgRegistryValidator {

    @Override
    public IModbusDeviceCfgRegistryValidator validator() {
      return this;
    }

    @Override
    public ValidationResult canAddDeviceCfg( ModbusDeviceCfg aCfg ) {
      ValidationResult vr = ValidationResult.SUCCESS;
      for( IModbusDeviceCfgRegistryValidator v : validatorsList() ) {
        vr = ValidationResult.firstNonOk( vr, v.canAddDeviceCfg( aCfg ) );
        if( vr.isError() ) {
          break;
        }
      }
      return vr;
    }

    @Override
    public ValidationResult canReplaceDeviceCfg( String aDevId, ModbusDeviceCfg aCfg ) {
      ValidationResult vr = ValidationResult.SUCCESS;
      for( IModbusDeviceCfgRegistryValidator v : validatorsList() ) {
        vr = ValidationResult.firstNonOk( vr, v.canReplaceDeviceCfg( aDevId, aCfg ) );
        if( vr.isError() ) {
          break;
        }
      }
      return vr;
    }

    @Override
    public ValidationResult canRemoveDeviceCfg( String aDevId ) {
      ValidationResult vr = ValidationResult.SUCCESS;
      for( IModbusDeviceCfgRegistryValidator v : validatorsList() ) {
        vr = ValidationResult.firstNonOk( vr, v.canRemoveDeviceCfg( aDevId ) );
        if( vr.isError() ) {
          break;
        }
      }
      return vr;
    }

  }

  private final IModbusDeviceCfgRegistryValidator builtinValidator = new IModbusDeviceCfgRegistryValidator() {

    @Override
    public ValidationResult canAddDeviceCfg( ModbusDeviceCfg aCfg ) {
      TsNullArgumentRtException.checkNull( aCfg );
      if( cfgsList.hasKey( aCfg.id() ) ) {
        return ValidationResult.error( FMT_ERR_MODBUS_DEV_ID_ALREADY_EXISTS, aCfg.id() );
      }
      return NameStringValidator.VALIDATOR.validate( aCfg.nmName() );
    }

    @Override
    public ValidationResult canReplaceDeviceCfg( String aDevId, ModbusDeviceCfg aCfg ) {
      TsNullArgumentRtException.checkNulls( aDevId, aCfg );
      if( !cfgsList.hasKey( aDevId ) ) {
        return ValidationResult.error( FMT_ERR_MODBUS_DEV_ID_NOT_EXISTS, aDevId );
      }
      if( !aDevId.equals( aCfg.id() ) && cfgsList.hasKey( aCfg.id() ) ) {
        return ValidationResult.error( FMT_ERR_MODBUS_DEV_ID_ALREADY_EXISTS, aCfg.id() );
      }
      return NameStringValidator.VALIDATOR.validate( aCfg.nmName() );
    }

    @Override
    public ValidationResult canRemoveDeviceCfg( String aDevId ) {
      TsNullArgumentRtException.checkNull( aDevId );
      if( !cfgsList.hasKey( aDevId ) ) {
        return ValidationResult.warn( FMT_WARN_NO_MODBUS_DEV_ID_TO_REMOVE, aDevId );
      }
      return ValidationResult.SUCCESS;
    }

  };

  private final GenericChangeEventer eventer;
  private final ValidationSupport    svs = new ValidationSupport();

  private final IStridablesListBasicEdit<ModbusDeviceCfg> cfgsList = new SortedStridablesList<>();

  /**
   * Constructor.
   */
  public ModbusDeviceCfgRegistry() {
    eventer = new GenericChangeEventer( this );
    svs.addValidator( builtinValidator );
  }

  // ------------------------------------------------------------------------------------
  // ITsClearable
  //

  @Override
  public void clear() {
    if( !cfgsList.isEmpty() ) {
      cfgsList.clear();
      eventer.fireChangeEvent();
    }
  }

  // ------------------------------------------------------------------------------------
  // IGenericChangeEventCapable
  //

  @Override
  public IGenericChangeEventer genericChangeEventer() {
    return eventer;
  }

  // ------------------------------------------------------------------------------------
  // IModbusDeviceCfgRegistry
  //

  @Override
  public IStridablesList<ModbusDeviceCfg> list() {
    return cfgsList;
  }

  @Override
  public void addDeviceCfg( ModbusDeviceCfg aCfg ) {
    TsValidationFailedRtException.checkError( svs.validator().canAddDeviceCfg( aCfg ) );
    cfgsList.add( aCfg );
    eventer.fireChangeEvent();
  }

  @Override
  public void replaceDeviceCfg( String aDevId, ModbusDeviceCfg aCfg ) {
    TsValidationFailedRtException.checkError( svs.validator().canReplaceDeviceCfg( aDevId, aCfg ) );
    cfgsList.removeById( aDevId );
    cfgsList.add( aCfg );
  }

  @Override
  public void removeDeviceCfg( String aDevId ) {
    TsValidationFailedRtException.checkError( svs.validator().canRemoveDeviceCfg( aDevId ) );
    cfgsList.removeById( aDevId );
    eventer.fireChangeEvent();
  }

  @Override
  public ITsValidationSupport<IModbusDeviceCfgRegistryValidator> svs() {
    return svs;
  }

  // ------------------------------------------------------------------------------------
  // API
  //

  /**
   * Sets registry content to the specified list.
   * <p>
   * Method is intended to fill registry at application startup from the external storage. Method does <b>not</b> fires
   * an {@link #genericChangeEventer()} event.
   *
   * @param aConfigurations {@link IStridablesList}&lt;{@link ModbusDeviceCfg}&gt; - list of device configuration
   */
  public void fillRegistry( IStridablesList<ModbusDeviceCfg> aConfigurations ) {
    cfgsList.setAll( aConfigurations );
  }

}
