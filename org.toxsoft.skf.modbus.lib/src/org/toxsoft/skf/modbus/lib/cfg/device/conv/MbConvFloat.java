package org.toxsoft.skf.modbus.lib.cfg.device.conv;

import static org.toxsoft.core.tslib.av.EAtomicType.*;
import static org.toxsoft.core.tslib.av.impl.AvUtils.*;
import static org.toxsoft.core.tslib.av.metainfo.IAvMetaConstants.*;
import static org.toxsoft.skf.modbus.lib.l10n.ISkfModbusLibSharedResources.*;
import static org.toxsoft.skf.modbus.lib.mbspec.EModbusRegisterKind.*;

import org.toxsoft.core.tslib.av.*;
import org.toxsoft.core.tslib.av.impl.*;
import org.toxsoft.core.tslib.av.metainfo.*;
import org.toxsoft.core.tslib.bricks.strid.coll.impl.*;
import org.toxsoft.core.tslib.bricks.validator.*;
import org.toxsoft.core.tslib.utils.errors.*;
import org.toxsoft.skf.modbus.lib.cfg.device.*;
import org.toxsoft.skf.modbus.lib.mbspec.*;

/**
 * Converter of type {@link EMbConverterType#CONV_BOOL}.
 *
 * @author hazard157
 */
class MbConvFloat
    extends AbstractMbConverter {

  private static final IDataDef OPDEF_COEFF_K = DataDef.create( "coeffK", FLOATING, //$NON-NLS-1$
      TSID_NAME, STR_CONV_FLOAT_COEFF_K, //
      TSID_DESCRIPTION, STR_CONV_FLOAT_COEFF_K_D, //
      TSID_DEFAULT_VALUE, IAtomicValue.NULL //
  );

  private static final IDataDef OPDEF_COEFF_B = DataDef.create( "coeffB", FLOATING, //$NON-NLS-1$
      TSID_NAME, STR_CONV_FLOAT_COEFF_B, //
      TSID_DESCRIPTION, STR_CONV_FLOAT_COEFF_B_D, //
      TSID_DEFAULT_VALUE, IAtomicValue.NULL //
  );

  /**
   * The factory singleton.
   */
  public static final IMbConverterFactory FACTORY = new AbstractMbConverterFactory( //
      EMbConverterType.CONV_BOOL, EAtomicType.BOOLEAN, //
      new StridablesList<>( OPDEF_COEFF_K, OPDEF_COEFF_B ), //
      new EModbusRegisterKind[] { DI, COIL } ) {

    @Override
    protected ValidationResult doValidateCreation( MbDeviceCfg aDevCfg, MbRegisterCfg aRegCfg ) {
      // check logical register consist of 2 or 4 physical registers
      int wordsCount = aRegCfg.getRegistersPoolLength();
      if( wordsCount != 2 && wordsCount != 4 ) {
        return ValidationResult.error( FMT_ERR_CONV_FLOAT_INV_REG_POOL_SIZE, Integer.valueOf( wordsCount ) );
      }
      // check K and B coefficients if defined has FLOATING value
      IAtomicValue avK = aRegCfg.converterCfg().params().getValue( OPDEF_COEFF_K );
      if( avK.isAssigned() && avK.atomicType() != EAtomicType.FLOATING ) {
        return ValidationResult.error( FMT_ERR_CONV_FLOAT_NON_FLOAT_OP, OPDEF_COEFF_K.id() );
      }
      IAtomicValue avB = aRegCfg.converterCfg().params().getValue( OPDEF_COEFF_B );
      if( avB.isAssigned() && avB.atomicType() != EAtomicType.FLOATING ) {
        return ValidationResult.error( FMT_ERR_CONV_FLOAT_NON_FLOAT_OP, OPDEF_COEFF_B.id() );
      }
      if( avK.isAssigned() != avB.isAssigned() ) {
        return ValidationResult.warn( FMT_ERR_CONV_FLOAT_NOT_BOTH_OPS, OPDEF_COEFF_K.id(), OPDEF_COEFF_B.id() );
      }
      return ValidationResult.SUCCESS;
    }

    @Override
    protected IMbConverter doCreate( MbDeviceCfg aDevCfg, MbRegisterCfg aRegCfg ) {
      return new MbConvFloat( aDevCfg, aRegCfg );
    }

  };

  private final boolean useCalc;
  private final double  k;
  private final double  b;

  MbConvFloat( MbDeviceCfg aDevCfg, MbRegisterCfg aRegisterCfg ) {
    super( aDevCfg, aRegisterCfg );
    IAtomicValue avK = aRegisterCfg.converterCfg().params().getValue( OPDEF_COEFF_K );
    IAtomicValue avB = aRegisterCfg.converterCfg().params().getValue( OPDEF_COEFF_B );
    useCalc = avK.isAssigned() && avB.isAssigned();
    if( useCalc ) {
      k = avK.asDouble();
      b = avB.asDouble();
    }
    else {
      k = 1.0;
      b = 0.0;
    }
  }

  // ------------------------------------------------------------------------------------
  // implementation
  //

  private static double readAbcdFloat( short[] aWords, int aOffset ) {
    int value = 0;
    for( int i = aOffset; i < 2; i++ ) {
      value += aWords[i] << (16 * (1 - i));
    }
    return Float.intBitsToFloat( value );
  }

  private static double readCdabFloat( short[] aWords, int aOffset ) {
    int value = 0;
    for( int i = aOffset; i < 2; i++ ) {
      value += aWords[i] << (16 * i);
    }
    return Float.intBitsToFloat( value );
  }

  private static double readAbcdDouble( short[] aWords, int aOffset ) {
    long value = 0;
    for( int i = aOffset; i < 4; i++ ) {
      value += ((long)aWords[i]) << (16 * (3 - i));
    }
    return Double.longBitsToDouble( value );
  }

  private static double readCdabDouble( short[] aWords, int aOffset ) {
    long value = 0;
    for( int i = aOffset; i < 4; i++ ) {
      value += ((long)aWords[i]) << (16 * i);
    }
    return Double.longBitsToDouble( value );
  }

  private double internalReadFrom( short[] aWords, int aOffset ) {
    if( isCDAB ) {
      if( wordsCount == 2 ) {
        return readCdabFloat( aWords, aOffset );
      }
      return readAbcdFloat( aWords, aOffset );
    }
    if( wordsCount == 2 ) {
      return readCdabDouble( aWords, aOffset );
    }
    return readAbcdDouble( aWords, aOffset );
  }

  // ------------------------------------------------------------------------------------
  // AbstractMbConverter
  //

  @Override
  protected IAtomicValue doReadFrom( short[] aWords, int aOffset ) {
    double value = internalReadFrom( aWords, aOffset );
    if( useCalc ) {
      value = k * value + b;
    }
    return avFloat( value );
  }

  @Override
  protected void doWriteTo( IAtomicValue aValue, short[] aWords, int aOffset ) {
    // if( isCDAB ) {
    // if( wordsCount == 2 ) {
    //
    // }
    // }
    // else {
    // if( wordsCount == 2 ) {
    //
    // }
    // }

    // TODO implement MbConvFloat.doWriteTo()
    throw new TsUnderDevelopmentRtException( "MbConvFloat.doWriteTo()" );

  }

}
