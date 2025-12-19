package org.toxsoft.skf.modbus.lib.l10n;

import org.toxsoft.skf.modbus.lib.cfg.device.*;
import org.toxsoft.skf.modbus.lib.mbspec.*;

/**
 * Localizable resources.
 *
 * @author hazard157
 */
@SuppressWarnings( "javadoc" )
public interface ISkfModbusLibSharedResources {

  /**
   * {@link IModbusSpecificationConstants}
   */
  String FMT_ERR_REG_ADDR_OUT_OF_RANGE = Messages.getString( "MbSpec.FMT_ERR_REG_ADDR_OUT_OF_RANGE" ); //$NON-NLS-1$
  String FMT_ERR_INV_REG_SEQ_COUNT     = Messages.getString( "MbSpec.FMT_ERR_INV_REG_SEQ_COUNT" );     //$NON-NLS-1$
  String FMT_ERR_SEQ_REGS_OUT_OF_RANGE = Messages.getString( "MbSpec.FMT_ERR_SEQ_REGS_OUT_OF_RANGE" ); //$NON-NLS-1$

  /**
   * {@link EModbusRegisterKind}
   */
  String STR_EMRK_DI         = Messages.getString( "EModbusRegisterKind.STR_DI" );         //$NON-NLS-1$
  String STR_EMRK_DI_D       = Messages.getString( "EModbusRegisterKind.STR_DI_D" );       //$NON-NLS-1$
  String STR_EMRK_COIL       = Messages.getString( "EModbusRegisterKind.STR_COIL" );       //$NON-NLS-1$
  String STR_EMRK_COIL_D     = Messages.getString( "EModbusRegisterKind.STR_COIL_D" );     //$NON-NLS-1$
  String STR_EMRK_IN_REG     = Messages.getString( "EModbusRegisterKind.STR_IN_REG" );     //$NON-NLS-1$
  String STR_EMRK_IN_REG_D   = Messages.getString( "EModbusRegisterKind.STR_IN_REG_D" );   //$NON-NLS-1$
  String STR_EMRK_HOLD_REG   = Messages.getString( "EModbusRegisterKind.STR_HOLD_REG" );   //$NON-NLS-1$
  String STR_EMRK_HOLD_REG_D = Messages.getString( "EModbusRegisterKind.STR_HOLD_REG_D" ); //$NON-NLS-1$

  /**
   * {@link EModbusFuncCode}
   */
  String STR_MFC_01_READ_COILS       = Messages.getString( "EModbusFuncCode.STR_MFC_01_READ_COILS" );       //$NON-NLS-1$
  String STR_MFC_01_READ_COILS_D     = Messages.getString( "EModbusFuncCode.STR_MFC_01_READ_COILS_D" );     //$NON-NLS-1$
  String STR_MFC_02_READ_DI          = Messages.getString( "EModbusFuncCode.STR_MFC_02_READ_DI" );          //$NON-NLS-1$
  String STR_MFC_02_READ_DI_D        = Messages.getString( "EModbusFuncCode.STR_MFC_02_READ_DI_D" );        //$NON-NLS-1$
  String STR_MFC_03_READ_HOLD_REGS   = Messages.getString( "EModbusFuncCode.STR_MFC_03_READ_HOLD_REGS" );   //$NON-NLS-1$
  String STR_MFC_03_READ_HOLD_REGS_D = Messages.getString( "EModbusFuncCode.STR_MFC_03_READ_HOLD_REGS_D" ); //$NON-NLS-1$
  String STR_MFC_04_READ_IN_REG      = Messages.getString( "EModbusFuncCode.STR_MFC_04_READ_IN_REG" );      //$NON-NLS-1$
  String STR_MFC_04_READ_IN_REG_D    = Messages.getString( "EModbusFuncCode.STR_MFC_04_READ_IN_REG_D" );    //$NON-NLS-1$
  String STR_MFC_05_WRITE_COIL       = Messages.getString( "EModbusFuncCode.STR_MFC_05_WRITE_COIL" );       //$NON-NLS-1$
  String STR_MFC_05_WRITE_COIL_D     = Messages.getString( "EModbusFuncCode.STR_MFC_05_WRITE_COIL_D" );     //$NON-NLS-1$
  String STR_MFC_06_WRITE_REG        = Messages.getString( "EModbusFuncCode.STR_MFC_06_WRITE_REG" );        //$NON-NLS-1$
  String STR_MFC_06_WRITE_REG_D      = Messages.getString( "EModbusFuncCode.STR_MFC_06_WRITE_REG_D" );      //$NON-NLS-1$
  String STR_MFC_12_WRITE_REGS       = Messages.getString( "EModbusFuncCode.STR_MFC_12_WRITE_REGS" );       //$NON-NLS-1$
  String STR_MFC_12_WRITE_REGS_D     = Messages.getString( "EModbusFuncCode.STR_MFC_12_WRITE_REGS_D" );     //$NON-NLS-1$
  String STR_MFC_15_WRITE_COILS      = Messages.getString( "EModbusFuncCode.STR_MFC_15_WRITE_COILS" );      //$NON-NLS-1$
  String STR_MFC_15_WRITE_COILS_D    = Messages.getString( "EModbusFuncCode.STR_MFC_15_WRITE_COILS_D" );    //$NON-NLS-1$

  /**
   * {@link IModbusDeviceCfgConstants}
   */
  String STR_MSDCC_DEVICE_NAME            = Messages.getString( "MSDCC.STR_MSDCC_DEVICE_NAME" );            //$NON-NLS-1$
  String STR_MSDCC_DEVICE_NAME_D          = Messages.getString( "MSDCC.STR_MSDCC_DEVICE_NAME_D" );          //$NON-NLS-1$
  String STR_MSDCC_DEVICE_DESCRIPTION     = Messages.getString( "MSDCC.STR_MSDCC_DEVICE_DESCRIPTION" );     //$NON-NLS-1$
  String STR_MSDCC_DEVICE_DESCRIPTION_D   = Messages.getString( "MSDCC.STR_MSDCC_DEVICE_DESCRIPTION_D" );   //$NON-NLS-1$
  String STR_MSDCC_MANUFACTURER_NAME      = Messages.getString( "MSDCC.STR_MSDCC_MANUFACTURER_NAME" );      //$NON-NLS-1$
  String STR_MSDCC_MANUFACTURER_NAME_D    = Messages.getString( "MSDCC.STR_MSDCC_MANUFACTURER_NAME_D" );    //$NON-NLS-1$
  String STR_MSDCC_MANUFACTURER_URL       = Messages.getString( "MSDCC.STR_MSDCC_MANUFACTURER_URL" );       //$NON-NLS-1$
  String STR_MSDCC_MANUFACTURER_URL_D     = Messages.getString( "MSDCC.STR_MSDCC_MANUFACTURER_URL_D" );     //$NON-NLS-1$
  String STR_MSDCC_PRODUCT_URL            = Messages.getString( "MSDCC.STR_MSDCC_PRODUCT_URL" );            //$NON-NLS-1$
  String STR_MSDCC_PRODUCT_URL_D          = Messages.getString( "MSDCC.STR_MSDCC_PRODUCT_URL_D" );          //$NON-NLS-1$
  String STR_MSDCC_DEVELOPER_NOTES        = Messages.getString( "MSDCC.STR_MSDCC_DEVELOPER_NOTES" );        //$NON-NLS-1$
  String STR_MSDCC_DEVELOPER_NOTES_D      = Messages.getString( "MSDCC.STR_MSDCC_DEVELOPER_NOTES_D" );      //$NON-NLS-1$
  String STR_MSDCC_REGISTER_NAME          = Messages.getString( "MSDCC.STR_MSDCC_REGISTER_NAME" );          //$NON-NLS-1$
  String STR_MSDCC_REGISTER_NAME_D        = Messages.getString( "MSDCC.STR_MSDCC_REGISTER_NAME_D" );        //$NON-NLS-1$
  String STR_MSDCC_REGISTER_DESCRIPTION   = Messages.getString( "MSDCC.STR_MSDCC_REGISTER_DESCRIPTION" );   //$NON-NLS-1$
  String STR_MSDCC_REGISTER_DESCRIPTION_D = Messages.getString( "MSDCC.STR_MSDCC_REGISTER_DESCRIPTION_D" ); //$NON-NLS-1$
  String STR_MSDCC_POOL_LENGTH            = Messages.getString( "MSDCC.STR_MSDCC_POOL_LENGTH" );            //$NON-NLS-1$
  String STR_MSDCC_POOL_LENGTH_D          = Messages.getString( "MSDCC.STR_MSDCC_POOL_LENGTH_D" );          //$NON-NLS-1$
  String STR_MSDCC_APPLICABLE_FUNCS       = Messages.getString( "MSDCC.STR_MSDCC_APPLICABLE_FUNCS" );       //$NON-NLS-1$
  String STR_MSDCC_APPLICABLE_FUNCS_D     = Messages.getString( "MSDCC.STR_MSDCC_APPLICABLE_FUNCS_D" );     //$NON-NLS-1$

}
