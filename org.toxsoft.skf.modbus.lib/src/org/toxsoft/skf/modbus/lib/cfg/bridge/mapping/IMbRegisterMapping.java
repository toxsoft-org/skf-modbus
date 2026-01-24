package org.toxsoft.skf.modbus.lib.cfg.bridge.mapping;

import org.toxsoft.core.tslib.av.*;
import org.toxsoft.core.tslib.bricks.strid.*;
import org.toxsoft.skf.modbus.lib.cfg.bridge.*;
import org.toxsoft.skf.modbus.lib.cfg.device.*;

/**
 * The MODBUS logical register mapping to the USkat entities.
 * <p>
 * <i>Logical register</i> is sequence of the physical registers containing single value to be read or written. Such a
 * single value may be mapped to several USkat entities. For example, one 16-bit value (a word of ORed bits) may be
 * mapped to the 16 USkat boolean RT-data.
 * <p>
 * Mapping may describe both reading or writing. Anyway, mapping consists of two steps:
 * <p>
 * <code>&nbsp;&nbsp;Logical <b>register</b> <==> single<b> {@link IAtomicValue IAtomicValue}</b> <==> <b>USkat</b> entities</code>
 * <p>
 * Left <code><b><==></b></code> denotes data <b><i>conversion</i></b> between a byte array <b><i>byte[]</i></b> (the
 * value of the MODBUS logical register) and {@link IAtomicValue} - the single data value representation of the USkat
 * (and basically, <code>tslib</code>).
 * <p>
 * Right <code><b><==></b></code> denotes
 *
 * @author hazard157
 */
public interface IMbRegisterMapping
    extends IStridableParameterized {

  /**
   * Returns the parent (owner) node of the mapping.
   *
   * @return {@link IMbNodeCfg} - the node containing device with mapped register
   */
  IMbNodeCfg parent();

  /**
   * Returns number of MUDBUS register mapped to USkat entity.
   * <p>
   * Returned value is a key in {@link MbDeviceCfg#registerCfs()} to get {@link MbRegisterCfg} - description of the
   * mapped register.
   *
   * @return int - the register number
   */
  int mbRegNo();

  // TODO request parameters OR some parameters to be specified in IMbNodeCfg ?
  // EModbusFuncCode mbFunc();
  // long pollIntervaleMs()
  // grouping with other registers?
  //

  // TODO rules how convert to IAtomicValue
  //

}
