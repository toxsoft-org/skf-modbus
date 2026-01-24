package org.toxsoft.skf.modbus.lib.devel;

import org.toxsoft.core.tslib.av.*;
import org.toxsoft.skf.modbus.lib.cfg.bridge.mapping.*;

/**
 * Converter
 *
 * @author hazard157
 * @see IMbRegisterMapping
 */
public sealed interface IMbConverter
    permits AbstractMbConverter {

  String typeId();

  IAtomicValue readFrom( byte[] aArray, int aOffset );

  void writeTo( IAtomicValue aValue, byte[] aArray, int aOffset );

}
