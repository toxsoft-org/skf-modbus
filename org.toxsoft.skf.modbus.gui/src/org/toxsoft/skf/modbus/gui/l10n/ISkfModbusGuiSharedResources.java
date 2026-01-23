package org.toxsoft.skf.modbus.gui.l10n;

import org.toxsoft.skf.modbus.gui.e4.services.*;
import org.toxsoft.skf.modbus.gui.glib.device.*;
import org.toxsoft.skf.modbus.gui.m5.device.*;

/**
 * Localizable resources.
 *
 * @author hazard157
 */
@SuppressWarnings( "javadoc" )
public interface ISkfModbusGuiSharedResources {

  /**
   * {@link EMbTreePathKind}
   */
  String STR_MB_NPK_ROOT     = Messages.getString( "EMbNodePathKind.STR_MB_NPK_ROOT" );     //$NON-NLS-1$
  String STR_MB_NPK_ROOT_D   = Messages.getString( "EMbNodePathKind.STR_MB_NPK_ROOT_D" );   //$NON-NLS-1$
  String STR_MB_NPK_BRIDGE   = Messages.getString( "EMbNodePathKind.STR_MB_NPK_BRIDGE" );   //$NON-NLS-1$
  String STR_MB_NPK_BRIDGE_D = Messages.getString( "EMbNodePathKind.STR_MB_NPK_BRIDGE_D" ); //$NON-NLS-1$
  String STR_MB_NPK_BUS      = Messages.getString( "EMbNodePathKind.STR_MB_NPK_BUS" );      //$NON-NLS-1$
  String STR_MB_NPK_BUS_D    = Messages.getString( "EMbNodePathKind.STR_MB_NPK_BUS_D" );    //$NON-NLS-1$
  String STR_MB_NPK_NODE     = Messages.getString( "EMbNodePathKind.STR_MB_NPK_NODE" );     //$NON-NLS-1$
  String STR_MB_NPK_NODE_D   = Messages.getString( "EMbNodePathKind.STR_MB_NPK_NODE_D" );   //$NON-NLS-1$

  /**
   * <code>org.toxsoft.skf.modbus.gui.glib.bridge</code>
   */
  String MSG_ERR_INV_BRIDGE_ID     = Messages.getString( "gui.bridge.MSG_ERR_INV_BRIDGE_ID" );     //$NON-NLS-1$
  String FMT_ERR_DUP_BRIDGE_ID     = Messages.getString( "gui.bridge.FMT_ERR_DUP_BRIDGE_ID" );     //$NON-NLS-1$
  String FMT_WARN_BRIDGE_HAS_BUSES = Messages.getString( "gui.bridge.FMT_WARN_BRIDGE_HAS_BUSES" ); //$NON-NLS-1$

  /**
   * {@link ValedIntListMbFuncCodes}
   */
  String STR_MB_FUMC_CODES_NOT_SPECIFIED = Messages.getString( "valeds.STR_MB_FUMC_CODES_NOT_SPECIFIED" ); //$NON-NLS-1$
  String DLG_SELECT_MB_FUMC_CODES        = Messages.getString( "valeds.DLG_SELECT_MB_FUMC_CODES" );        //$NON-NLS-1$
  String DLG_SELECT_MB_FUMC_CODES_D      = Messages.getString( "valeds.DLG_SELECT_MB_FUMC_CODES_D" );      //$NON-NLS-1$

  /**
   * {@link MbRegisterCfgM5Model}
   */
  String STR_M5M_MODBUS_REGISTER_CFG   = Messages.getString( "m5.STR_M5M_MODBUS_REGISTER_CFG" );   //$NON-NLS-1$
  String STR_M5M_MODBUS_REGISTER_CFG_D = Messages.getString( "m5.STR_M5M_MODBUS_REGISTER_CFG_D" ); //$NON-NLS-1$
  String STR_MODBUS_REGISTER_REG_NO    = Messages.getString( "m5.STR_MODBUS_REGISTER_REG_NO" );    //$NON-NLS-1$
  String STR_MODBUS_REGISTER_REG_NO_D  = Messages.getString( "m5.STR_MODBUS_REGISTER_REG_NO_D" );  //$NON-NLS-1$
  String STR_MODBUS_REGISTER_KIND      = Messages.getString( "m5.STR_MODBUS_REGISTER_KIND" );      //$NON-NLS-1$
  String STR_MODBUS_REGISTER_KIND_D    = Messages.getString( "m5.STR_MODBUS_REGISTER_KIND_D" );    //$NON-NLS-1$

  /**
   * {@link MbDeviceCfgM5Model}
   */
  String STR_M5M_MODBUS_DEVICE_CFG         = Messages.getString( "m5.STR_M5M_MODBUS_DEVICE_CFG" );         //$NON-NLS-1$
  String STR_M5M_MODBUS_DEVICE_CFG_D       = Messages.getString( "m5.STR_M5M_MODBUS_DEVICE_CFG_D" );       //$NON-NLS-1$
  String STR_MODBUS_DEVICE_CFG_ID          = Messages.getString( "m5.STR_MODBUS_DEVICE_CFG_ID" );          //$NON-NLS-1$
  String STR_MODBUS_DEVICE_CFG_ID_D        = Messages.getString( "m5.STR_MODBUS_DEVICE_CFG_ID_D" );        //$NON-NLS-1$
  String STR_MODBUS_DEVICE_CFG_REGISTERS   = Messages.getString( "m5.STR_MODBUS_DEVICE_CFG_REGISTERS" );   //$NON-NLS-1$
  String STR_MODBUS_DEVICE_CFG_REGISTERS_D = Messages.getString( "m5.STR_MODBUS_DEVICE_CFG_REGISTERS_D" ); //$NON-NLS-1$

  /**
   * MbDeviceCfgM5PanelCreator
   */
  String STR_MODBUS_DEVICE_CFG_PROPS_TAB   = Messages.getString( "m5.STR_MODBUS_DEVICE_CFG_PROPS_TAB" );   //$NON-NLS-1$
  String STR_MODBUS_DEVICE_CFG_PROPS_TAB_D = Messages.getString( "m5.STR_MODBUS_DEVICE_CFG_PROPS_TAB_D" ); //$NON-NLS-1$

  /**
   * {@link ModbusDeviceCfgRegistry}
   */
  String FMT_ERR_MODBUS_DEV_ID_ALREADY_EXISTS = Messages.getString( "FMT_ERR_MODBUS_DEV_ID_ALREADY_EXISTS" ); //$NON-NLS-1$
  String FMT_ERR_MODBUS_DEV_ID_NOT_EXISTS     = Messages.getString( "FMT_ERR_MODBUS_DEV_ID_NOT_EXISTS" );     //$NON-NLS-1$
  String FMT_WARN_NO_MODBUS_DEV_ID_TO_REMOVE  = Messages.getString( "FMT_WARN_NO_MODBUS_DEV_ID_TO_REMOVE" );  //$NON-NLS-1$

  /**
   * {@link MbFuncCodeM5Model}
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
