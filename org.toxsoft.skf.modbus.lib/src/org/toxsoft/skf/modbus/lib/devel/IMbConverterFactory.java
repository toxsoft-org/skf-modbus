package org.toxsoft.skf.modbus.lib.devel;

import org.toxsoft.core.tslib.bricks.strid.*;
import org.toxsoft.core.tslib.bricks.validator.*;
import org.toxsoft.core.tslib.utils.errors.*;
import org.toxsoft.skf.modbus.lib.cfg.device.*;

/**
 * The kind converter type description is a factory to build {@link IMbConverter} instances.
 *
 * @author hazard157
 */
public interface IMbConverterFactory
    extends IStridableParameterized {

  /**
   * Determines if this type of converter may be created for the specified register.
   *
   * @param aDeviceCfg {@link MbDeviceCfg} - device, owner of the register
   * @param aRegNo {@link MbRegisterCfg} - the number of the register in {@link MbDeviceCfg#registerCfs()}
   * @return boolean - <code>true</code> if converter can be created, <code>false</code> -
   *         {@link #create(MbDeviceCfg, int)} will fail
   */
  ValidationResult validateCreation( MbDeviceCfg aDeviceCfg, int aRegNo );

  /**
   * Creates an instance of the {@link IMbConverter} for the specified MODBUS logical register.
   *
   * @param aDeviceCfg {@link MbDeviceCfg} - device, owner of the register
   * @param aRegNo {@link MbRegisterCfg} - the number of the register in {@link MbDeviceCfg#registerCfs()}
   * @return {@link IMbConverter} - created instance
   * @throws TsNullArgumentRtException any argument = <code>null</code>
   * @throws TsIllegalArgumentRtException failed {@link #validateCreation(MbDeviceCfg, int)}
   */
  IMbConverter create( MbDeviceCfg aDeviceCfg, int aRegNo );

}
