package org.toxsoft.skf.modbus.lib.l10n;

import org.toxsoft.skf.modbus.lib.cfg.bridge.*;
import org.toxsoft.skf.modbus.lib.cfg.device.*;
import org.toxsoft.skf.modbus.lib.cfg.device.conv.*;
import org.toxsoft.skf.modbus.lib.mbspec.*;

/**
 * Localizable resources.
 *
 * @author hazard157
 */
@SuppressWarnings( "javadoc" )
public interface ISkfModbusLibSharedResources {

  /**
   * org.toxsoft.skf.modbus.lib.cfg.device.conv
   */
  String FMT_ERR_DEV_NO_REG_NO                = Messages.getString( "conv.FMT_ERR_DEV_NO_REG_NO" );                //$NON-NLS-1$
  String FMT_ERR_CONV_FACTORY_INV_TYPE_ID     = Messages.getString( "conv.FMT_ERR_CONV_FACTORY_INV_TYPE_ID" );     //$NON-NLS-1$
  String FMT_ERR_CONV_FACTORY_INV_REG_KIND    = Messages.getString( "conv.FMT_ERR_CONV_FACTORY_INV_REG_KIND" );    //$NON-NLS-1$
  String STR_CONV_FLOAT_COEFF_K               = Messages.getString( "conv.STR_CONV_FLOAT_COEFF_K" );               //$NON-NLS-1$
  String STR_CONV_FLOAT_COEFF_K_D             = Messages.getString( "conv.STR_CONV_FLOAT_COEFF_K_D" );             //$NON-NLS-1$
  String STR_CONV_FLOAT_COEFF_B               = Messages.getString( "conv.STR_CONV_FLOAT_COEFF_B" );               //$NON-NLS-1$
  String STR_CONV_FLOAT_COEFF_B_D             = Messages.getString( "conv.STR_CONV_FLOAT_COEFF_B_D" );             //$NON-NLS-1$
  String FMT_ERR_CONV_FLOAT_INV_REG_POOL_SIZE = Messages.getString( "conv.FMT_ERR_CONV_FLOAT_INV_REG_POOL_SIZE" ); //$NON-NLS-1$
  String FMT_ERR_CONV_FLOAT_NON_FLOAT_OP      = Messages.getString( "conv.FMT_ERR_CONV_FLOAT_NON_FLOAT_OP" );      //$NON-NLS-1$
  String FMT_ERR_CONV_FLOAT_NOT_BOTH_OPS      = Messages.getString( "conv.FMT_ERR_CONV_FLOAT_NOT_BOTH_OPS" );      //$NON-NLS-1$
  String FMT_ERR_CONV_INT_INV_REG_POOL_SIZE   = Messages.getString( "conv.FMT_ERR_CONV_INT_INV_REG_POOL_SIZE" );   //$NON-NLS-1$

  /**
   * {@link EMbConverterType}
   */
  String STR_EMCT_CONV_BOOL      = Messages.getString( "MbXxxCfg.STR_EMCT_CONV_BOOL" );      //$NON-NLS-1$
  String STR_EMCT_CONV_BOOL_D    = Messages.getString( "MbXxxCfg.STR_EMCT_CONV_BOOL_D" );    //$NON-NLS-1$
  String STR_EMCT_CONV_INTEGER   = Messages.getString( "MbXxxCfg.STR_EMCT_CONV_INTEGER" );   //$NON-NLS-1$
  String STR_EMCT_CONV_INTEGER_D = Messages.getString( "MbXxxCfg.STR_EMCT_CONV_INTEGER_D" ); //$NON-NLS-1$
  String STR_EMCT_CONV_FLOAT     = Messages.getString( "MbXxxCfg.STR_EMCT_CONV_FLOAT" );     //$NON-NLS-1$
  String STR_EMCT_CONV_FLOAT_D   = Messages.getString( "MbXxxCfg.STR_EMCT_CONV_FLOAT_D" );   //$NON-NLS-1$
  String STR_EMCT_CONV_STRING    = Messages.getString( "MbXxxCfg.STR_EMCT_CONV_STRING" );    //$NON-NLS-1$
  String STR_EMCT_CONV_STRING_D  = Messages.getString( "MbXxxCfg.STR_EMCT_CONV_STRING_D" );  //$NON-NLS-1$

  /**
   * org.toxsoft.skf.modbus.lib.cfg.bridge.impl
   */
  String FMT_ERR_CANT_CHANGE_FIXED_OP     = Messages.getString( "MbXxxCfg.FMT_ERR_CANT_CHANGE_FIXED_OP" );     //$NON-NLS-1$
  String FMT_ERR_MBNODE_ALREADY_EXISTS    = Messages.getString( "MbXxxCfg.FMT_ERR_MBNODE_ALREADY_EXISTS" );    //$NON-NLS-1$
  String FMT_ERR_INV_MBNODE_ID            = Messages.getString( "MbXxxCfg.FMT_ERR_INV_MBNODE_ID" );            //$NON-NLS-1$
  String FMT_ERR_INV_RTU_ADDR             = Messages.getString( "MbXxxCfg.FMT_ERR_INV_RTU_ADDR" );             //$NON-NLS-1$
  String FMT_ERR_RTU_ADDR_EXISTS          = Messages.getString( "MbXxxCfg.FMT_ERR_RTU_ADDR_EXISTS" );          //$NON-NLS-1$
  String FMT_ERR_NO_SUCH_DEVICE_IN_BRIDGE = Messages.getString( "MbXxxCfg.FMT_ERR_NO_SUCH_DEVICE_IN_BRIDGE" ); //$NON-NLS-1$
  String MSG_ERR_BLANK_IP_ADDRESS         = Messages.getString( "MbXxxCfg.MSG_ERR_BLANK_IP_ADDRESS" );         //$NON-NLS-1$
  String MSG_ERR_INV_TCP_PORT_NO          = Messages.getString( "MbXxxCfg.MSG_ERR_INV_TCP_PORT_NO" );          //$NON-NLS-1$
  String FMT_ERR_TCP_ADDR_EXISTS          = Messages.getString( "MbXxxCfg.FMT_ERR_TCP_ADDR_EXISTS" );          //$NON-NLS-1$
  String FMT_WARN_MBNODE_DUR_IP_ADDR      = Messages.getString( "MbXxxCfg.FMT_WARN_MBNODE_DUR_IP_ADDR" );      //$NON-NLS-1$
  String FMT_ERR_MBBUS_ALREADY_EXISTS     = Messages.getString( "MbXxxCfg.FMT_ERR_MBBUS_ALREADY_EXISTS" );     //$NON-NLS-1$
  String FMT_WARN_BUS_HAS_NODES           = Messages.getString( "MbXxxCfg.FMT_WARN_BUS_HAS_NODES" );           //$NON-NLS-1$

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
   * {@link IMbBridgeCfgConstants}
   */
  String STR_MBBCC_BRI_NAME             = Messages.getString( "Bridge.STR_MBBCC_BRI_NAME" );             //$NON-NLS-1$
  String STR_MBBCC_BRI_NAME_D           = Messages.getString( "Bridge.STR_MBBCC_BRI_NAME_D" );           //$NON-NLS-1$
  String STR_MBBCC_BRI_DESCRIPTION      = Messages.getString( "Bridge.STR_MBBCC_BRI_DESCRIPTION" );      //$NON-NLS-1$
  String STR_MBBCC_BRI_DESCRIPTION_D    = Messages.getString( "Bridge.STR_MBBCC_BRI_DESCRIPTION_D" );    //$NON-NLS-1$
  String STR_MBBCC_BUS_NAME             = Messages.getString( "Bridge.STR_MBBCC_BUS_NAME" );             //$NON-NLS-1$
  String STR_MBBCC_BUS_NAME_D           = Messages.getString( "Bridge.STR_MBBCC_BUS_NAME_D" );           //$NON-NLS-1$
  String STR_MBBCC_BUS_DESCRIPTION      = Messages.getString( "Bridge.STR_MBBCC_BUS_DESCRIPTION" );      //$NON-NLS-1$
  String STR_MBBCC_BUS_DESCRIPTION_D    = Messages.getString( "Bridge.STR_MBBCC_BUS_DESCRIPTION_D" );    //$NON-NLS-1$
  String STR_MBBCC_BUS_IS_RTU           = Messages.getString( "Bridge.STR_MBBCC_BUS_IS_RTU" );           //$NON-NLS-1$
  String STR_MBBCC_BUS_IS_RTU_D         = Messages.getString( "Bridge.STR_MBBCC_BUS_IS_RTU_D" );         //$NON-NLS-1$
  String STR_MBBCC_NOD_NAME             = Messages.getString( "Bridge.STR_MBBCC_NOD_NAME" );             //$NON-NLS-1$
  String STR_MBBCC_NOD_NAME_D           = Messages.getString( "Bridge.STR_MBBCC_NOD_NAME_D" );           //$NON-NLS-1$
  String STR_MBBCC_NOD_DESCRIPTION      = Messages.getString( "Bridge.STR_MBBCC_NOD_DESCRIPTION" );      //$NON-NLS-1$
  String STR_MBBCC_NOD_DESCRIPTION_D    = Messages.getString( "Bridge.STR_MBBCC_NOD_DESCRIPTION_D" );    //$NON-NLS-1$
  String STR_MBBCC_NOD_TCP_IP_ADDRESS   = Messages.getString( "Bridge.STR_MBBCC_NOD_TCP_IP_ADDRESS" );   //$NON-NLS-1$
  String STR_MBBCC_NOD_TCP_IP_ADDRESS_D = Messages.getString( "Bridge.STR_MBBCC_NOD_TCP_IP_ADDRESS_D" ); //$NON-NLS-1$
  String STR_MBBCC_NOD_TCP_PORT_NO      = Messages.getString( "Bridge.STR_MBBCC_NOD_TCP_PORT_NO" );      //$NON-NLS-1$
  String STR_MBBCC_NOD_TCP_PORT_NO_D    = Messages.getString( "Bridge.STR_MBBCC_NOD_TCP_PORT_NO_D" );    //$NON-NLS-1$
  String STR_MBBCC_NOD_RTU_DEV_ADDR     = Messages.getString( "Bridge.STR_MBBCC_NOD_RTU_DEV_ADDR" );     //$NON-NLS-1$
  String STR_MBBCC_NOD_RTU_DEV_ADDR_D   = Messages.getString( "Bridge.STR_MBBCC_NOD_RTU_DEV_ADDR_D" );   //$NON-NLS-1$

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
  String STR_MFC_15_WRITE_COILS      = Messages.getString( "EModbusFuncCode.STR_MFC_15_WRITE_COILS" );      //$NON-NLS-1$
  String STR_MFC_15_WRITE_COILS_D    = Messages.getString( "EModbusFuncCode.STR_MFC_15_WRITE_COILS_D" );    //$NON-NLS-1$
  String STR_MFC_16_WRITE_REGS       = Messages.getString( "EModbusFuncCode.STR_MFC_16_WRITE_REGS" );       //$NON-NLS-1$
  String STR_MFC_16_WRITE_REGS_D     = Messages.getString( "EModbusFuncCode.STR_MFC_16_WRITE_REGS_D" );     //$NON-NLS-1$

  /**
   * {@link IMbDeviceCfgConstants}
   */
  String STR_MSDCC_DEVICE_NAME            = Messages.getString( "MSDCC.STR_MSDCC_DEVICE_NAME" );            //$NON-NLS-1$
  String STR_MSDCC_DEVICE_NAME_D          = Messages.getString( "MSDCC.STR_MSDCC_DEVICE_NAME_D" );          //$NON-NLS-1$
  String STR_MSDCC_DEVICE_DESCRIPTION     = Messages.getString( "MSDCC.STR_MSDCC_DEVICE_DESCRIPTION" );     //$NON-NLS-1$
  String STR_MSDCC_DEVICE_DESCRIPTION_D   = Messages.getString( "MSDCC.STR_MSDCC_DEVICE_DESCRIPTION_D" );   //$NON-NLS-1$
  String STR_MSDCC_IS_CDAB                = Messages.getString( "MSDCC.STR_MSDCC_IS_CDAB" );                //$NON-NLS-1$
  String STR_MSDCC_IS_CDAB_D              = Messages.getString( "MSDCC.STR_MSDCC_IS_CDAB_D" );              //$NON-NLS-1$
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
