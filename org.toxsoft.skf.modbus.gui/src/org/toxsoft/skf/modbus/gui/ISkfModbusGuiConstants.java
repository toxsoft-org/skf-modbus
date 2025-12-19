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

  String PREFIX_OF_ICON_FIELD_NAME = "ICONID_";            //$NON-NLS-1$
  String ICONID_MODBUS_LOGO        = "modbus-logo";        //$NON-NLS-1$
  String ICONID_MODBUS_INOUT       = "modbus-inout";       //$NON-NLS-1$
  String ICONID_MODBUS_INOUT_EDIT  = "modbus-inout-edit";  //$NON-NLS-1$
  String ICONID_MODBUS_DEVICE      = "modbus-device";      //$NON-NLS-1$
  String ICONID_MODBUS_DEVICE_EDIT = "modbus-device-edit"; //$NON-NLS-1$

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
