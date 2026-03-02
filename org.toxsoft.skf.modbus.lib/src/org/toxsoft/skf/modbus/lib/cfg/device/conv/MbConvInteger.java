package org.toxsoft.skf.modbus.lib.cfg.device.conv;

import static org.toxsoft.core.tslib.av.impl.AvUtils.*;
import static org.toxsoft.skf.modbus.lib.l10n.ISkfModbusLibSharedResources.*;
import static org.toxsoft.skf.modbus.lib.mbspec.EModbusRegisterKind.*;

import org.toxsoft.core.tslib.av.*;
import org.toxsoft.core.tslib.bricks.strid.coll.*;
import org.toxsoft.core.tslib.bricks.validator.*;
import org.toxsoft.core.tslib.utils.errors.*;
import org.toxsoft.skf.modbus.lib.cfg.device.*;
import org.toxsoft.skf.modbus.lib.mbspec.*;

/**
 * Converter of type {@link EMbConverterType#CONV_BOOL}.
 *
 * @author hazard157
 */
class MbConvInteger
    extends AbstractMbConverter {

  /**
   * The factory singleton.
   */
  public static final IMbConverterFactory FACTORY = new AbstractMbConverterFactory( //
      EMbConverterType.CONV_BOOL, EAtomicType.BOOLEAN, //
      IStridablesList.EMPTY, //
      new EModbusRegisterKind[] { DI, COIL } ) {

    @Override
    protected ValidationResult doValidateCreation( MbDeviceCfg aDevCfg, MbRegisterCfg aRegCfg ) {
      // check logical register consist of 2 or 4 physical registers
      int wordsCount = aRegCfg.getRegistersPoolLength();
      if( wordsCount != 1 && wordsCount != 2 && wordsCount != 4 ) {
        return ValidationResult.error( FMT_ERR_CONV_INT_INV_REG_POOL_SIZE, Integer.valueOf( wordsCount ) );
      }
      return ValidationResult.SUCCESS;
    }

    @Override
    protected IMbConverter doCreate( MbDeviceCfg aDevCfg, MbRegisterCfg aRegCfg ) {
      return new MbConvInteger( aDevCfg, aRegCfg );
    }

  };

  MbConvInteger( MbDeviceCfg aDevCfg, MbRegisterCfg aRegisterCfg ) {
    super( aDevCfg, aRegisterCfg );
  }

  // ------------------------------------------------------------------------------------
  // implementation
  //

  private static IAtomicValue readAbcdInt4Bytes( short[] aWords, int aOffset ) {
    int value = 0;
    for( int i = aOffset; i < 2; i++ ) {
      value = value << 16;
      value += aWords[i];
    }
    return avInt( value );
  }

  private static IAtomicValue readAbcdInt8Bytes( short[] aWords, int aOffset ) {
    long value = 0;
    for( int i = aOffset; i < 4; i++ ) {
      value = value << 16;
      value += aWords[i];
    }
    return avInt( value );
  }

  private static IAtomicValue readCdabInt4Bytes( short[] aWords, int aOffset ) {
    int value = 0;
    for( int i = aOffset; i < 2; i++ ) {
      value += aWords[i] << (16 * i);
    }
    return avInt( value );
  }

  private static IAtomicValue readCdabInt8Bytes( short[] aWords, int aOffset ) {
    long value = 0;
    for( int i = aOffset; i < 4; i++ ) {
      value += aWords[i] << (16 * i);
    }
    return avInt( value );
  }

  // ------------------------------------------------------------------------------------
  // AbstractMbConverter
  //

  @Override
  protected IAtomicValue doReadFrom( short[] aWords, int aOffset ) {
    switch( wordsCount ) {
      case 1: {
        return avInt( aWords[0] );
      }
      case 2: {
        if( isCDAB ) {
          return readCdabInt4Bytes( aWords, aOffset );
        }
        return readAbcdInt4Bytes( aWords, aOffset );
      }
      case 3: {
        if( isCDAB ) {
          return readCdabInt8Bytes( aWords, aOffset );
        }
        return readAbcdInt8Bytes( aWords, aOffset );
      }
      default:
        throw new TsNotAllEnumsUsedRtException( Integer.toString( wordsCount ) );
    }
  }

  @Override
  protected void doWriteTo( IAtomicValue aValue, short[] aWords, int aOffset ) {

    // switch( wordsCount ) {
    // case 1: {
    //
    // break;
    // }
    // case 2: {
    //
    // break;
    // }
    // case 3: {
    //
    // break;
    // }
    // default:
    // throw new TsNotAllEnumsUsedRtException( Integer.toString( wordsCount ) );
    // }

    // TODO implement MbConvInteger.doWriteTo()
    throw new TsUnderDevelopmentRtException( "MbConvInteger.doWriteTo()" );

  }

}
