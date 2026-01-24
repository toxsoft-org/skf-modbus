package org.toxsoft.skf.modbus.lib.devel;

import org.toxsoft.core.tslib.av.opset.*;
import org.toxsoft.core.tslib.bricks.strid.coll.*;
import org.toxsoft.core.tslib.bricks.strid.coll.impl.*;
import org.toxsoft.core.tslib.bricks.strid.impl.*;
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
public abstract class AbstractMbConverterFactory
    extends StridableParameterized
    implements IMbConverterFactory {

  private final IStridablesList<EModbusRegisterKind> applicableKinds;

  /**
   * Constructor.
   *
   * @param aId String the factory (type) ID, an IDpath
   * @param aParams {@link IOptionSet} - value of {@link #params()}
   * @param aApplicableKinds {@link EModbusRegisterKind}[] - non-empty array of applicable kinds
   * @throws TsNullArgumentRtException any argument = <code>null</code>
   * @throws TsIllegalArgumentRtException ID is not an IDpath
   * @throws TsIllegalArgumentRtException kinds array is empty
   */
  public AbstractMbConverterFactory( String aId, IOptionSet aParams, EModbusRegisterKind... aApplicableKinds ) {
    super( aId, aParams );
    TsErrorUtils.checkArrayArg( aApplicableKinds, 1 );
    applicableKinds = new StridablesList<>( aApplicableKinds );
  }

  // ------------------------------------------------------------------------------------
  // IMbConverterFactory
  //

  @Override
  public ValidationResult validateCreation( MbDeviceCfg aDeviceCfg, int aRegNo ) {
    TsNullArgumentRtException.checkNull( aDeviceCfg );
    MbRegisterCfg regCfg = aDeviceCfg.registerCfs().findByKey( aRegNo );
    if( regCfg == null ) {
      return ValidationResult.error( "Device %s does not have register No %d", aDeviceCfg.id(),
          Integer.valueOf( aRegNo ) );
    }
    if( !applicableKinds.hasElem( regCfg.kind() ) ) {
      return ValidationResult.error( "Register %d kind %s is not applicable for converter %s",
          Integer.valueOf( aRegNo ), regCfg.kind().id(), id() );
    }
    return doValidateCreation( aDeviceCfg, regCfg );
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

  protected abstract ValidationResult doValidateCreation( MbDeviceCfg aDevCfg, MbRegisterCfg aRegCfg );

  protected abstract IMbConverter doCreate( MbDeviceCfg aDevCfg, MbRegisterCfg aRegCfg );

}
