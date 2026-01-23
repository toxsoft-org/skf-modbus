package org.toxsoft.skf.modbus.skide.main;

import org.eclipse.swt.widgets.*;
import org.toxsoft.core.tsgui.bricks.ctx.*;
import org.toxsoft.core.tsgui.bricks.ctx.impl.*;
import org.toxsoft.skf.modbus.gui.glib.bridge.*;
import org.toxsoft.skide.core.api.*;
import org.toxsoft.skide.core.api.impl.*;

/**
 * {@link AbstractSkideUnitPanel} implementation.
 *
 * @author max
 */
public class SkidePanelConfigureBridge
    extends AbstractSkideUnitPanel {

  /**
   * Constructor by context and unit
   *
   * @param aContext ITsGuiContext - context.
   * @param aUnit ISkideUnit - unit.
   */
  public SkidePanelConfigureBridge( ITsGuiContext aContext, ISkideUnit aUnit ) {
    super( aContext, aUnit );
  }

  @Override
  protected Control doCreateControl( Composite aParent ) {
    PanelMbBridgeCfgsEditor panel = new PanelMbBridgeCfgsEditor( new TsGuiContext( tsContext() ) );
    panel.createControl( aParent );
    return panel.getControl();
  }

}
