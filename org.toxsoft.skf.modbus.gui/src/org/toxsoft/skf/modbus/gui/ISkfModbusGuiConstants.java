package org.toxsoft.skf.modbus.gui;

import org.eclipse.e4.core.contexts.*;
import org.toxsoft.core.tsgui.graphics.icons.*;

/**
 * Plugin common constants.
 *
 * @author hazard157
 */
@SuppressWarnings( "javadoc" )
public interface ISkfModbusGuiConstants {

  // ------------------------------------------------------------------------------------
  // Icons

  String PREFIX_OF_ICON_FIELD_NAME = "ICONID_"; //$NON-NLS-1$

  String ICONID_MODBUS_LOGO        = "modbus-logo";        //$NON-NLS-1$
  String ICONID_MODBUS_INOUT       = "modbus-inout";       //$NON-NLS-1$
  String ICONID_MODBUS_INOUT_EDIT  = "modbus-inout-edit";  //$NON-NLS-1$
  String ICONID_MODBUS_DEVICE      = "modbus-device";      //$NON-NLS-1$
  String ICONID_MODBUS_DEVICE_EDIT = "modbus-device-edit"; //$NON-NLS-1$

  String ICONID_MB_BRIDGE   = "mb-bridge";   //$NON-NLS-1$
  String ICONID_MB_BUS      = "mb-bus";      //$NON-NLS-1$
  String ICONID_MB_BUS_TCP  = "mb-bus-tcp";  //$NON-NLS-1$
  String ICONID_MB_BUS_RTU  = "mb-bus-rtu";  //$NON-NLS-1$
  String ICONID_MB_NODE     = "mb-node";     //$NON-NLS-1$
  String ICONID_MB_NODE_TCP = "mb-node-tcp"; //$NON-NLS-1$
  String ICONID_MB_NODE_RTU = "mb-node-rtu"; //$NON-NLS-1$

  // String ICONID_MB_BRIDGES_LIST = "mb-bridges-list"; //$NON-NLS-1$
  // String ICONID_MB_BUSES_LIST = "mb-buses-list"; //$NON-NLS-1$
  // String ICONID_MB_BUSES_TCP_LIST = "mb-buses-tcp-list"; //$NON-NLS-1$
  // String ICONID_MB_BUSES_RTU_LIST = "mb-buses-rtu-list"; //$NON-NLS-1$

  /**
   * Constants registration.
   *
   * @param aWinContext {@link IEclipseContext} - windows level context
   */
  static void init( IEclipseContext aWinContext ) {
    // register plug-in built-in icons
    ITsIconManager iconManager = aWinContext.get( ITsIconManager.class );
    iconManager.registerStdIconByIds( Activator.PLUGIN_ID, ISkfModbusGuiConstants.class, PREFIX_OF_ICON_FIELD_NAME );
  }

}
