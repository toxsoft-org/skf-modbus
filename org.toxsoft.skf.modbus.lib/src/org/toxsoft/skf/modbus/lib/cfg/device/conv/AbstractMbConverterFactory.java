package org.toxsoft.skf.modbus.lib.cfg.device.conv;

import static org.toxsoft.skf.modbus.lib.l10n.ISkfModbusLibSharedResources.*;

import org.toxsoft.core.tslib.av.*;
import org.toxsoft.core.tslib.av.metainfo.*;
import org.toxsoft.core.tslib.av.opset.impl.*;
import org.toxsoft.core.tslib.bricks.strid.coll.*;
import org.toxsoft.core.tslib.bricks.strid.coll.impl.*;
import org.toxsoft.core.tslib.bricks.validator.*;
import org.toxsoft.core.tslib.bricks.validator.impl.*;
import org.toxsoft.core.tslib.utils.errors.*;
import org.toxsoft.skf.modbus.lib.cfg.device.*;
import org.toxsoft.skf.modbus.lib.mbspec.*;

/**
 * Base implementation of {@link IMbConverterFactory}.
 *
 * @author hazard157
 */
abstract class AbstractMbConverterFactory
    implements IMbConverterFactory {

  private final EMbConverterType                     type;
  private final EAtomicType                          valueAtomicType;
  private final IStridablesList<IDataDef>            opDefs;
  private final IStridablesList<EModbusRegisterKind> applicableKinds;

  /**
   * Constructor.
   *
   * @param aType {@link EMbConverterType} - type enum constant
   * @param aAtomicType {@link EAtomicType} - the atomic type {@link #valueAtomicType()}
   * @param aOpDefs {@link IStridablesList}&lt;{@link IDataDef}&gt; - list be returned by {@link #listOptions()}
   * @param aApplicableKinds {@link EModbusRegisterKind}[] - non-empty array of applicable kinds
   * @throws TsNullArgumentRtException any argument = <code>null</code>
   * @throws TsIllegalArgumentRtException ID is not an IDpath
   * @throws TsIllegalArgumentRtException kinds array is empty
   */
  public AbstractMbConverterFactory( EMbConverterType aType, EAtomicType aAtomicType, IStridablesList<IDataDef> aOpDefs,
      EModbusRegisterKind[] aApplicableKinds ) {
    TsNullArgumentRtException.checkNulls( aType, aAtomicType );
    type = aType;
    valueAtomicType = aAtomicType;
    TsErrorUtils.checkArrayArg( aApplicableKinds, 1 );
    opDefs = new StridablesList<>( aOpDefs );
    applicableKinds = new StridablesList<>( aApplicableKinds );
  }

  // ------------------------------------------------------------------------------------
  // IMbConverterFactory
  //

  @Override
  public EMbConverterType type() {
    return type;
  }

  @Override
  public EAtomicType valueAtomicType() {
    return valueAtomicType;
  }

  @Override
  public IStridablesList<IDataDef> listOptions() {
    return opDefs;
  }

  @Override
  public ValidationResult validateCreation( MbDeviceCfg aDeviceCfg, int aRegNo ) {
    TsNullArgumentRtException.checkNull( aDeviceCfg );
    MbRegisterCfg regCfg = aDeviceCfg.registerCfs().findByKey( aRegNo );
    if( regCfg == null ) {
      return ValidationResult.error( FMT_ERR_DEV_NO_REG_NO, aDeviceCfg.id(), Integer.valueOf( aRegNo ) );
    }
    if( regCfg.converterCfg().type() != type ) {
      return ValidationResult.error( FMT_ERR_CONV_FACTORY_INV_TYPE_ID, type.id(), regCfg.converterCfg().type().id() );
    }
    if( !applicableKinds.hasElem( regCfg.kind() ) ) {
      return ValidationResult.error( FMT_ERR_CONV_FACTORY_INV_REG_KIND, Integer.valueOf( aRegNo ), regCfg.kind().id(),
          type.id() );
    }
    ValidationResult vr = OptionSetUtils.validateOptionSet( regCfg.converterCfg().params(), opDefs );
    if( vr.isError() ) {
      return vr;
    }
    return ValidationResult.firstNonOk( vr, doValidateCreation( aDeviceCfg, regCfg ) );
  }

  @Override
  final public IMbConverter create( MbDeviceCfg aDeviceCfg, int aRegNo ) {
    TsValidationFailedRtException.checkError( validateCreation( aDeviceCfg, aRegNo ) );
    MbRegisterCfg regCfg = aDeviceCfg.registerCfs().getByKey( aRegNo );
    IMbConverter converter = doCreate( aDeviceCfg, regCfg );
    TsInternalErrorRtException.checkNull( converter );
    return converter;
  }

  // ------------------------------------------------------------------------------------
  // To override / implement
  //

  /**
   * Implementation may add specific validation checks.
   * <p>
   * In the base class return {@link ValidationResult#SUCCESS}, no need to call superclass method when overriding.
   *
   * @param aDevCfg {@link MbDeviceCfg} - the device
   * @param aRegCfg {@link MbRegisterCfg} - the register
   * @return {@link ValidationResult} - the check result
   */
  protected ValidationResult doValidateCreation( MbDeviceCfg aDevCfg, MbRegisterCfg aRegCfg ) {
    return ValidationResult.SUCCESS;
  }

  /**
   * Implementation must create the converter instance tuned by the specified parameters.
   * <p>
   * Parameters are specified in {@link MbConverterCfg#params() aRegCfg.converterCfg().params()}.
   *
   * @param aDevCfg {@link MbDeviceCfg} - the device
   * @param aRegCfg {@link MbRegisterCfg} - the register
   * @return {@link IMbConverter} - create instance of convertor
   */
  protected abstract IMbConverter doCreate( MbDeviceCfg aDevCfg, MbRegisterCfg aRegCfg );

}
