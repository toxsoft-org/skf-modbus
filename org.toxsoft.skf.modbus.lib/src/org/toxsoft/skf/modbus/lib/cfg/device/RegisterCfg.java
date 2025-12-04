package org.toxsoft.skf.modbus.lib.cfg.device;

import org.toxsoft.core.tslib.av.opset.*;
import org.toxsoft.core.tslib.av.utils.*;
import org.toxsoft.skf.modbus.lib.incub.rwkind.*;

/**
 * The MODBUS register configuration, describes several consecutive registers read as a whole.
 *
 * @author hazard157
 * @param regNo int - starting number of the register
 * @param wordCount
 * @param rw
 * @param params
 */
public record RegisterCfg ( int regNo, int wordCount, ERwKind rw, IOptionSet params )
    implements IParameterized {

}
