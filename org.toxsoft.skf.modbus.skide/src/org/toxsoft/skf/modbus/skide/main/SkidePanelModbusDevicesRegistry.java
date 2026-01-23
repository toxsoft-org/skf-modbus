package org.toxsoft.skf.modbus.skide.main;

import org.eclipse.swt.widgets.*;
import org.toxsoft.core.tsgui.bricks.ctx.*;
import org.toxsoft.core.tsgui.bricks.ctx.impl.*;
import org.toxsoft.skf.modbus.gui.e4.services.*;
import org.toxsoft.skf.modbus.gui.glib.device.*;
import org.toxsoft.skide.core.api.*;
import org.toxsoft.skide.core.api.impl.*;

/**
 * {@link AbstractSkideUnitPanel} implementation.
 *
 * @author max
 */
public class SkidePanelModbusDevicesRegistry
    extends AbstractSkideUnitPanel {

  /**
   * Constructor by context and unit
   *
   * @param aContext ITsGuiContext - context.
   * @param aUnit ISkideUnit - unit.
   */
  public SkidePanelModbusDevicesRegistry( ITsGuiContext aContext, ISkideUnit aUnit ) {
    super( aContext, aUnit );
  }

  @Override
  protected Control doCreateControl( Composite aParent ) {
    ITsGuiContext ctx = new TsGuiContext( tsContext() );
    IModbusDeviceCfgRegistry devReg = ctx.get( IModbusDeviceCfgRegistry.class );
    PanelMbDeviceCfgsEditor panel = new PanelMbDeviceCfgsEditor( aParent, ctx, isControlValid(), devReg );
    return panel;
  }

}
