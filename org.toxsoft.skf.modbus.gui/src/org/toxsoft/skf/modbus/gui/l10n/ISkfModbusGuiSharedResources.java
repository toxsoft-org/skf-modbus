package org.toxsoft.skf.modbus.gui.l10n;

import org.toxsoft.skf.modbus.gui.glib.*;
import org.toxsoft.skf.modbus.gui.m5.*;

/**
 * Localizable resources.
 *
 * @author hazard157
 */
@SuppressWarnings( "javadoc" )
public interface ISkfModbusGuiSharedResources {

  /**
   * {@link ValedIntListMbFuncCodes}
   */
  String STR_MB_FUMC_CODES_NOT_SPECIFIED = Messages.getString( "valeds.STR_MB_FUMC_CODES_NOT_SPECIFIED" ); //$NON-NLS-1$
  String DLG_SELECT_MB_FUMC_CODES        = Messages.getString( "valeds.DLG_SELECT_MB_FUMC_CODES" );        //$NON-NLS-1$
  String DLG_SELECT_MB_FUMC_CODES_D      = Messages.getString( "valeds.DLG_SELECT_MB_FUMC_CODES_D" );      //$NON-NLS-1$

  /**
   * {@link ModbusRegisterCfgM5Model}
   */
  String STR_M5M_MODBUS_REGISTER_CFG   = Messages.getString( "m5.STR_M5M_MODBUS_REGISTER_CFG" );   //$NON-NLS-1$
  String STR_M5M_MODBUS_REGISTER_CFG_D = Messages.getString( "m5.STR_M5M_MODBUS_REGISTER_CFG_D" ); //$NON-NLS-1$
  String STR_MODBUS_REGISTER_REG_NO    = Messages.getString( "m5.STR_MODBUS_REGISTER_REG_NO" );    //$NON-NLS-1$
  String STR_MODBUS_REGISTER_REG_NO_D  = Messages.getString( "m5.STR_MODBUS_REGISTER_REG_NO_D" );  //$NON-NLS-1$
  String STR_MODBUS_REGISTER_KIND      = Messages.getString( "m5.STR_MODBUS_REGISTER_KIND" );      //$NON-NLS-1$
  String STR_MODBUS_REGISTER_KIND_D    = Messages.getString( "m5.STR_MODBUS_REGISTER_KIND_D" );    //$NON-NLS-1$

  /**
   * {@link ModbusDeviceCfgM5Model}
   */
  String STR_M5M_MODBUS_DEVICE_CFG         = Messages.getString( "m5.STR_M5M_MODBUS_DEVICE_CFG" );         //$NON-NLS-1$
  String STR_M5M_MODBUS_DEVICE_CFG_D       = Messages.getString( "m5.STR_M5M_MODBUS_DEVICE_CFG_D" );       //$NON-NLS-1$
  String STR_MODBUS_DEVICE_CFG_ID          = Messages.getString( "m5.STR_MODBUS_DEVICE_CFG_ID" );          //$NON-NLS-1$
  String STR_MODBUS_DEVICE_CFG_ID_D        = Messages.getString( "m5.STR_MODBUS_DEVICE_CFG_ID_D" );        //$NON-NLS-1$
  String STR_MODBUS_DEVICE_CFG_REGISTERS   = Messages.getString( "m5.STR_MODBUS_DEVICE_CFG_REGISTERS" );   //$NON-NLS-1$
  String STR_MODBUS_DEVICE_CFG_REGISTERS_D = Messages.getString( "m5.STR_MODBUS_DEVICE_CFG_REGISTERS_D" ); //$NON-NLS-1$

  /**
   * {@link MODBUSDeviceCfgRegistry}
   */
  String FMT_ERR_MODBUS_DEV_ID_ALREADY_EXISTS = Messages.getString( "FMT_ERR_MODBUS_DEV_ID_ALREADY_EXISTS" ); //$NON-NLS-1$
  String FMT_ERR_MODBUS_DEV_ID_NOT_EXISTS     = Messages.getString( "FMT_ERR_MODBUS_DEV_ID_NOT_EXISTS" );     //$NON-NLS-1$
  String FMT_WARN_NO_MODBUS_DEV_ID_TO_REMOVE  = Messages.getString( "FMT_WARN_NO_MODBUS_DEV_ID_TO_REMOVE" );  //$NON-NLS-1$

  /**
   * {@link ModbusFuncCodeM5Model}
   */
  String STR_MB_FUNC_INTEGER_CODE   = Messages.getString( "m5.STR_MB_FUNC_INTEGER_CODE" );   //$NON-NLS-1$
  String STR_MB_FUNC_INTEGER_CODE_D = Messages.getString( "m5.STR_MB_FUNC_INTEGER_CODE_D" ); //$NON-NLS-1$

  /**
   * AspModbusDeviceCollExpImp
   */
  String FMT_ERR_READING_MODBUS_DEV_CFG_COLL_FILE = Messages.getString( "m5.FMT_ERR_READING_MODBUS_DEV_CFG_COLL_FILE" ); //$NON-NLS-1$
  String FMT_ERR_WRITING_MODBUS_DEV_CFG_COLL_FILE = Messages.getString( "m5.FMT_ERR_WRITING_MODBUS_DEV_CFG_COLL_FILE" ); //$NON-NLS-1$
  String FMT_MB_DEV_CFGS_EXPORTED_TO_FILE         = Messages.getString( "m5.FMT_MB_DEV_CFGS_EXPORTED_TO_FILE" );         //$NON-NLS-1$

}
