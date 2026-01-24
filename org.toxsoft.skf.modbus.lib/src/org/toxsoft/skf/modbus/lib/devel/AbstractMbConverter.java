package org.toxsoft.skf.modbus.lib.devel;

import org.toxsoft.core.tslib.av.*;
import org.toxsoft.skf.modbus.lib.cfg.device.*;

/**
 * Base implementation of {@link IMbConverter}.
 *
 * @author hazard157
 */
public non-sealed abstract class AbstractMbConverter
    implements IMbConverter {

  private final String        typeId;
  private final MbDeviceCfg   devCfg;
  private final MbRegisterCfg regCfg;

  AbstractMbConverter( String aTypeId, MbDeviceCfg aDevCfg, MbRegisterCfg aRegisterCfg ) {
    typeId = aTypeId;
    devCfg = aDevCfg;
    regCfg = aRegisterCfg;
  }

  // ------------------------------------------------------------------------------------
  // API for subclasses
  //

  /**
   * Returns the configuration of the device where the register is located.
   *
   * @return {@link MbDeviceCfg} - MODBUS device configuration
   */
  public MbDeviceCfg devCfg() {
    return devCfg;
  }

  /**
   * Returns the configuration of the register to read/write.
   *
   * @return {@link MbRegisterCfg} - the register, one of {@link MbDeviceCfg#registerCfs() devCfg().registerCfs()}
   */
  public MbRegisterCfg regCfg() {
    return regCfg;
  }

  // ------------------------------------------------------------------------------------
  // IMbConverter
  //

  @Override
  final public String typeId() {
    return typeId;
  }

  @Override
  public abstract IAtomicValue readFrom( byte[] aArray, int aOffset );

  @Override
  public abstract void writeTo( IAtomicValue aValue, byte[] aArray, int aOffset );

}
