package org.toxsoft.skf.modbus.lib.cfg.device.conv;

import org.toxsoft.core.tslib.av.*;
import org.toxsoft.core.tslib.utils.errors.*;
import org.toxsoft.skf.modbus.lib.cfg.device.*;

/**
 * MODBUS logical register raw words to/from atomic value converter.
 * <p>
 * Converter is created with full knowledge about the dive and the register served -
 * {@link AbstractMbConverter#devCfg()} and {@link AbstractMbConverter#regCfg()}.
 * <p>
 * The number of words read/written is the same as number of physical registers in the logical register and is specified
 * in {@link MbRegisterCfg#getRegistersPoolLength()}.
 * <p>
 * <b>К ВНИМАНИЮ МАКСА:</b> я бы предложил использовать эти конверторы, но чтобы не переделывать (или переделать по
 * минимуму) имеющейся драйвер, просто создать соответствующие трансляторы-оболочки (wrapper, adapter), которв в
 * конструкторе получат <b>экземпляр</b> конвертора и его используют для работы.
 *
 * @author hazard157
 * @see IMbSingleMappingCfg
 * @see AbstractMbConverter
 */
public sealed interface IMbConverter
    permits AbstractMbConverter {

  /**
   * FIXME we need a way to process packed bits of COILS of MODBUS functions 1 and 15
   */

  /**
   * Reads atomic value from the MODBUS registers represented as a raw words already received device.
   *
   * @param aWords short[] - raw words to be read from
   * @param aOffset int - index of the first word to be written in array
   * @return {@link IAtomicValue} - the read value, never is <code>null</code>
   * @throws TsNullArgumentRtException any argument = <code>null</code>
   * @throws TsIllegalArgumentRtException invalid offset, negative or too big
   */
  IAtomicValue readFrom( short[] aWords, int aOffset );

  /**
   * Writes atomic value back to the MODBUS registers as a raw words to be send to the device..
   *
   * @param aValue {@link IAtomicValue} - the value to write
   * @param aWords short[] - raw words to be written
   * @param aOffset int - index of the first word to be written in array
   * @throws TsNullArgumentRtException any argument = <code>null</code>
   * @throws TsIllegalArgumentRtException invalid offset, negative or too big
   */
  void writeTo( IAtomicValue aValue, short[] aWords, int aOffset );

}
