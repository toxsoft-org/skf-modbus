package org.toxsoft.skf.modbus.lib.cfg.device.conv;

import static org.toxsoft.skf.modbus.lib.mbspec.EModbusRegisterKind.*;

import org.toxsoft.core.tslib.av.*;
import org.toxsoft.core.tslib.bricks.strid.coll.*;
import org.toxsoft.core.tslib.utils.errors.*;
import org.toxsoft.skf.modbus.lib.cfg.device.*;
import org.toxsoft.skf.modbus.lib.mbspec.*;

/**
 * Converter of type {@link EMbConverterType#CONV_BOOL}.
 *
 * @author hazard157
 */
class MbConvString
    extends AbstractMbConverter {

  /**
   * TODO define options :
   * <ul>
   * <li>encoding (like ASCII or UTF-8 or whatever);</li>
   * <li>NULL terminated?;</li>
   * <li>first 1 or 2 bytes encodes string length (actually, 8-bit characters count).</li>
   * </ul>
   */

  /**
   * The factory singleton.
   */
  public static final IMbConverterFactory FACTORY = new AbstractMbConverterFactory( //
      EMbConverterType.CONV_BOOL, EAtomicType.BOOLEAN, //
      IStridablesList.EMPTY, //
      new EModbusRegisterKind[] { DI, COIL } ) {

    @Override
    protected IMbConverter doCreate( MbDeviceCfg aDevCfg, MbRegisterCfg aRegCfg ) {
      return new MbConvString( aDevCfg, aRegCfg );
    }

  };

  MbConvString( MbDeviceCfg aDevCfg, MbRegisterCfg aRegisterCfg ) {
    super( aDevCfg, aRegisterCfg );
  }

  @Override
  protected IAtomicValue doReadFrom( short[] aWords, int aOffset ) {

    // TODO implement MbConvString.doReadFrom()
    throw new TsUnderDevelopmentRtException( "MbConvString.doReadFrom()" );

  }

  @Override
  protected void doWriteTo( IAtomicValue aValue, short[] aWords, int aOffset ) {

    // TODO implement MbConvString.doWriteTo()
    throw new TsUnderDevelopmentRtException( "MbConvString.doWriteTo()" );

  }

}
