package org.toxsoft.skf.modbus.lib;

import org.toxsoft.core.tslib.utils.valobj.*;
import org.toxsoft.skf.modbus.lib.cfg.device.*;
import org.toxsoft.skf.modbus.lib.cfg.rwkind.*;
import org.toxsoft.skf.modbus.lib.mbspec.*;

/**
 * The library helper methods.
 *
 * @author hazard157
 */
public class ModbusLibUtils {

  /**
   * The library initialization must be called before any action to access classes in this plugin.
   */
  public static void initialize() {
    TsValobjUtils.registerKeeper( ERwKind.KEEPER_ID, ERwKind.KEEPER );
    TsValobjUtils.registerKeeper( EModbusRegisterKind.KEEPER_ID, EModbusRegisterKind.KEEPER );
  }

  /**
   * No subclasses.
   */
  private ModbusLibUtils() {
    // nop
  }

}
