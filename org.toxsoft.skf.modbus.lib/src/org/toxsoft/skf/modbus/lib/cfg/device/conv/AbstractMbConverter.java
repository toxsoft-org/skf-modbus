package org.toxsoft.skf.modbus.lib.cfg.device.conv;

import org.toxsoft.core.tslib.av.*;
import org.toxsoft.core.tslib.av.errors.*;
import org.toxsoft.core.tslib.utils.errors.*;
import org.toxsoft.skf.modbus.lib.cfg.device.*;

/**
 * Base implementation of {@link IMbConverter}.
 *
 * @author hazard157
 */
non-sealed abstract class AbstractMbConverter
    implements IMbConverter {

  private final MbDeviceCfg   devCfg;
  private final MbRegisterCfg regCfg;

  // ------------------------------------------------------------------------------------
  // Protected field may be useful in #doXxx() method implementation

  /**
   * Number of 16-bit words (2 bytes) to be read/written to/from raw words array.
   * <p>
   * Derived from the {@link MbRegisterCfg#getRegistersPoolLength()}.
   */
  protected final int wordsCount;

  /**
   * The flag indicates that register is writable.
   * <p>
   * Derived from the {@link MbRegisterCfg#kind()}.
   */
  protected final boolean canWrite;

  /**
   * Expected atomic type of the logical register value.
   * <p>
   * Derived from the {@link MbRegisterCfg#valueType()}.
   */
  protected final EAtomicType valueType;

  /**
   * Determines byte order when processing multi-physical-register logical register.
   * <p>
   * Derived from the {@link MbDeviceCfg#isCdab()}.
   */
  protected final boolean isCDAB;

  protected AbstractMbConverter( MbDeviceCfg aDevCfg, MbRegisterCfg aRegisterCfg ) {
    devCfg = aDevCfg;
    regCfg = aRegisterCfg;
    wordsCount = regCfg.getRegistersPoolLength();
    canWrite = regCfg.kind().rwKind().canWrite();
    valueType = regCfg.valueType();
    isCDAB = devCfg.isCdab();
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
  public IAtomicValue readFrom( short[] aWords, int aOffset ) {
    // pre-conditions check
    TsNullArgumentRtException.checkNull( aWords );
    TsIllegalArgumentRtException.checkTrue( aOffset < 0 );
    TsIllegalArgumentRtException.checkTrue( aOffset + 2 * wordsCount > aWords.length );
    // actual reading
    IAtomicValue av = doReadFrom( aWords, aOffset );
    // post-conditions
    TsIllegalArgumentRtException.checkNull( av );
    AvTypeCastRtException.checkCanAssign( valueType, av.atomicType() );
    return av;
  }

  @Override
  public void writeTo( IAtomicValue aValue, short[] aWords, int aOffset ) {
    // pre-conditions check
    TsUnsupportedFeatureRtException.checkFalse( canWrite );
    TsNullArgumentRtException.checkNulls( aValue, aWords );
    TsIllegalArgumentRtException.checkTrue( aOffset < 0 );
    TsIllegalArgumentRtException.checkTrue( aOffset + 2 * wordsCount > aWords.length );
    AvTypeCastRtException.checkCanAssign( valueType, aValue.atomicType() );
    // actual writing
    doWriteTo( aValue, aWords, aOffset );
  }

  // ------------------------------------------------------------------------------------
  // To implement/override
  //

  /**
   * Implementation must read data from raw words buffer and convert to the atomic value.
   * <p>
   * Returned value will be checked to be of atomic type as requested by {@link MbRegisterCfg#valueType()}.
   * <p>
   * Implementation may wish to use protected fields {@link #wordsCount}, {@link #valueType}, {@link #isCDAB} and other
   * configuration information from {@link #devCfg()} and {@link #regCfg()}.
   *
   * @param aWords short[] - raw words previously read from the physical registers
   * @param aOffset int - offset in array to start reading words of first physical register
   * @return {@link IAtomicValue} - the read value, must not be <code>null</code>
   */
  protected abstract IAtomicValue doReadFrom( short[] aWords, int aOffset );

  /**
   * Implementation must write the atomic value to the buffer for further write to the MODBUS physical registers.
   * <p>
   * All arguments are checked to have valid values and write operation is checked to be allowed.
   * <p>
   * Implementation may wish to use protected fields {@link #wordsCount}, {@link #valueType}, {@link #isCDAB} and other
   * configuration information from {@link #devCfg()} and {@link #regCfg()}.
   *
   * @param aValue {@link IAtomicValue} - the value to write
   * @param aWords short[] - raw words to be written to the physical registers
   * @param aOffset int - offset in array to start writing words of first physical register
   */
  protected abstract void doWriteTo( IAtomicValue aValue, short[] aWords, int aOffset );

}
