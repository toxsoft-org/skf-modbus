package org.toxsoft.skf.modbus.lib.cfg.device;

import static org.toxsoft.core.tslib.av.metainfo.IAvMetaConstants.*;
import static org.toxsoft.skf.modbus.lib.cfg.device.IMbDeviceCfgConstants.*;
import static org.toxsoft.skf.modbus.lib.mbspec.IModbusSpecificationConstants.*;

import org.toxsoft.core.tslib.av.opset.*;
import org.toxsoft.core.tslib.av.opset.impl.*;
import org.toxsoft.core.tslib.av.utils.*;
import org.toxsoft.core.tslib.av.validators.*;
import org.toxsoft.core.tslib.bricks.keeper.*;
import org.toxsoft.core.tslib.bricks.keeper.AbstractEntityKeeper.*;
import org.toxsoft.core.tslib.bricks.strio.*;
import org.toxsoft.core.tslib.bricks.validator.*;
import org.toxsoft.core.tslib.bricks.validator.impl.*;
import org.toxsoft.core.tslib.coll.primtypes.*;
import org.toxsoft.core.tslib.coll.primtypes.impl.*;
import org.toxsoft.core.tslib.utils.errors.*;
import org.toxsoft.skf.modbus.lib.mbspec.*;

/**
 * The MODBUS logical register configuration, describes several consecutive registers read as a whole.
 * <p>
 * Note: the word '<i>register</i>' in specification denotes two closely related, however different concepts. From the
 * one size, the register is 16-bit wide port with unique number on the device - we call it a <i>physical</i> register.
 * But The single unit of data may not fit to the single physical register so several consecutive registers (we call
 * them a <i>logical</i> register) must be read or written at once. For example, logical register for {@link Float}
 * value needs 4 bytes, that is 2 physical registers.
 * <p>
 * The number of logical register {@link #regNo()} is always the number of the first physical register in a sequence.
 * <p>
 * Options of the {@link #params()} are listed in {@link IMbDeviceCfgConstants} with prefix <b>OPDEF_MB_REG_</b>XXX.
 *
 * @author hazard157
 * @param regNo int - starting number of the register
 * @param kind {@link EModbusRegisterKind} - the kind of register as specified in the MODBUS specification
 * @param params {@link IOptionSet} - additional options listed in {@link IMbDeviceCfgConstants}
 */
public record MbRegisterCfg ( int regNo, EModbusRegisterKind kind, IOptionSet params )
    implements IParameterized {

  /**
   * The keeper singleton.
   */
  public static final IEntityKeeper<MbRegisterCfg> KEEPER =
      new AbstractEntityKeeper<>( MbRegisterCfg.class, EEncloseMode.ENCLOSES_BASE_CLASS, null ) {

        @Override
        protected void doWrite( IStrioWriter aSw, MbRegisterCfg aEntity ) {
          aSw.writeInt( aEntity.regNo );
          aSw.writeSeparatorChar();
          EModbusRegisterKind.KEEPER.write( aSw, aEntity.kind );
          aSw.writeSeparatorChar();
          OptionSetKeeper.KEEPER.write( aSw, aEntity.params );
        }

        @Override
        protected MbRegisterCfg doRead( IStrioReader aSr ) {
          int regNo = aSr.readInt();
          aSr.ensureSeparatorChar();
          EModbusRegisterKind kind = EModbusRegisterKind.KEEPER.read( aSr );
          aSr.ensureSeparatorChar();
          IOptionSet params = OptionSetKeeper.KEEPER.read( aSr );
          return new MbRegisterCfg( regNo, kind, params );
        }
      };

  /**
   * Constructor.
   *
   * @param regNo int - starting number of the register
   * @param kind {@link EModbusRegisterKind} - the kind of register as specified in the MODBUS specification
   * @param params {@link IOptionSet} - optional parameters
   * @throws TsNullArgumentRtException any argument = <code>null</code>
   * @throws TsValidationFailedRtException failed {@link #canCreate(int, EModbusRegisterKind, IOptionSet)}
   */
  public MbRegisterCfg( int regNo, EModbusRegisterKind kind, IOptionSet params ) {
    TsValidationFailedRtException.checkError( canCreate( regNo, kind, params ) );
    this.regNo = regNo;
    this.kind = kind;
    this.params = new OptionSet( params );
  }

  /**
   * Checks if an instance can be created with the specified arguments.
   *
   * @param regNo int - starting number of the register
   * @param kind {@link EModbusRegisterKind} - the kind of register as specified in the MODBUS specification
   * @param params {@link IOptionSet} - optional parameters
   * @return {@link ValidationResult} - the check result
   */
  public static ValidationResult canCreate( int regNo, EModbusRegisterKind kind, IOptionSet params ) {
    TsNullArgumentRtException.checkNulls( kind, params );
    ValidationResult vr = validateMbAddress( regNo );
    if( !vr.isError() ) {
      vr = ValidationResult.firstNonOk( vr, NameStringAvValidator.VALIDATOR.validate( DDEF_NAME.getValue( params ) ) );
    }
    return vr;
  }

  // ------------------------------------------------------------------------------------
  // Configuration options as a separate method
  //

  /**
   * Returns value of the option {@link IMbDeviceCfgConstants#OPDEF_MB_REG_POOL_LENGTH}.
   *
   * @return String - quantity of consecutive registers, always >=1
   */
  public int getRegistersPoolLength() {
    int pl = OPDEF_MB_REG_POOL_LENGTH.getValue( params() ).asInt();
    return pl >= 1 ? pl : 1;
  }

  /**
   * Returns value of the option {@link IMbDeviceCfgConstants#OPDEF_MB_REG_APPLICABLE_FUNCS}.
   *
   * @return {@link IIntList} - list of integer function codes applicable to this register
   */
  public IIntList getApplicableFunctionCodes() {
    return OPDEF_MB_REG_APPLICABLE_FUNCS.getValue( params() ).asValobj();
  }

  /**
   * Returns value of the option {@link IMbDeviceCfgConstants#OPDEF_MB_REG_APPLICABLE_FUNCS}.
   * <p>
   * Warning: integer codes that does not corresponding enum constant {@link EModbusFuncCode} will not be included into
   * the resulting map!
   *
   * @return {@link IIntMap}&lt;{@link EModbusFuncCode}&gt; - sorted map "integer code" - "enum constant"
   */
  public IIntMap<EModbusFuncCode> getApplicableFunctionConsts() {
    IIntList llFc = OPDEF_MB_REG_APPLICABLE_FUNCS.getValue( params() ).asValobj();
    if( llFc.isEmpty() ) {
      return IIntMap.EMPTY;
    }
    IIntMapEdit<EModbusFuncCode> mm = new SortedIntMap<>();
    for( int i = 0; i < llFc.size(); i++ ) {
      int code = llFc.getValue( i );
      EModbusFuncCode mfc = EModbusFuncCode.asMap().findByKey( code );
      if( mfc != null ) {
        mm.put( code, mfc );
      }
    }
    return mm;
  }

}
