package org.toxsoft.skf.modbus.lib.cfg.device.conv;

import static org.toxsoft.skf.modbus.lib.mbspec.EModbusRegisterKind.*;

import org.toxsoft.core.tslib.av.*;
import org.toxsoft.core.tslib.av.impl.*;
import org.toxsoft.core.tslib.bricks.strid.coll.*;
import org.toxsoft.skf.modbus.lib.cfg.device.*;
import org.toxsoft.skf.modbus.lib.mbspec.*;

/**
 * Converter of type {@link EMbConverterType#CONV_BOOL}.
 *
 * @author hazard157
 */
class MbConvBool
    extends AbstractMbConverter {

  /**
   * The factory singleton.
   */
  public static final IMbConverterFactory FACTORY = new AbstractMbConverterFactory( //
      EMbConverterType.CONV_BOOL, EAtomicType.BOOLEAN, //
      IStridablesList.EMPTY, //
      new EModbusRegisterKind[] { DI, COIL } ) {

    @Override
    protected IMbConverter doCreate( MbDeviceCfg aDevCfg, MbRegisterCfg aRegCfg ) {
      return new MbConvBool( aDevCfg, aRegCfg );
    }

  };

  MbConvBool( MbDeviceCfg aDevCfg, MbRegisterCfg aRegisterCfg ) {
    super( aDevCfg, aRegisterCfg );
  }

  @Override
  protected IAtomicValue doReadFrom( short[] aWords, int aOffset ) {
    return AvUtils.avBool( aWords[aOffset] != 0 );
  }

  @Override
  protected void doWriteTo( IAtomicValue aValue, short[] aWords, int aOffset ) {
    aWords[aOffset] = (short)(aValue.asBool() ? 1 : 0);
  }

}
