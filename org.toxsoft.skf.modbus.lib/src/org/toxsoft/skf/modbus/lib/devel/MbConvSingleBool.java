package org.toxsoft.skf.modbus.lib.devel;

import static org.toxsoft.core.tslib.av.metainfo.IAvMetaConstants.*;
import static org.toxsoft.skf.modbus.lib.ISkfModbusLibConstants.*;

import org.toxsoft.core.tslib.av.*;
import org.toxsoft.core.tslib.av.opset.impl.*;
import org.toxsoft.core.tslib.bricks.validator.*;
import org.toxsoft.skf.modbus.lib.cfg.device.*;
import org.toxsoft.skf.modbus.lib.mbspec.*;

/**
 * @author hazard157
 */
public class MbConvSingleBool
    extends AbstractMbConverter {

  public static final String TYPE_ID = SKF_MB_FULL_ID + ".converter.SingleBool"; //$NON-NLS-1$

  public static final IMbConverterFactory FACTORY = new AbstractMbConverterFactory( TYPE_ID, //
      OptionSetUtils.createOpSet( //
          TSID_NAME, "Single bool", //
          TSID_DESCRIPTION, "SB descr" //
      ), //
      EModbusRegisterKind.DI, //
      EModbusRegisterKind.COIL //
  ) {

    @Override
    protected ValidationResult doValidateCreation( MbDeviceCfg aDevCfg, MbRegisterCfg aRegCfg ) {
      if( aRegCfg.getRegistersPoolLength() != 1 ) {
        return ValidationResult.error( "Converter of type %s works with register pool length 1", id() );
      }
      return ValidationResult.SUCCESS;
    }

    @Override
    protected IMbConverter doCreate( MbDeviceCfg aDevCfg, MbRegisterCfg aRegCfg ) {
      // TODO Auto-generated method stub
      return null;
    }
  };

  MbConvSingleBool( MbDeviceCfg aDevCfg, MbRegisterCfg aRegisterCfg ) {
    super( TYPE_ID, aDevCfg, aRegisterCfg );
    // TODO Auto-generated constructor stub
  }

  @Override
  public MbRegisterCfg regCfg() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public IAtomicValue readFrom( byte[] aArray, int aOffset ) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void writeTo( IAtomicValue aValue, byte[] aArray, int aOffset ) {
    // TODO Auto-generated method stub

  }

}
