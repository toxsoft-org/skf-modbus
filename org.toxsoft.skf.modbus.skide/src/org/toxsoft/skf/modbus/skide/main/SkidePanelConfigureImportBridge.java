package org.toxsoft.skf.modbus.skide.main;

import org.eclipse.swt.*;
import org.eclipse.swt.widgets.*;
import org.toxsoft.core.tsgui.bricks.ctx.*;
import org.toxsoft.skide.core.api.*;
import org.toxsoft.skide.core.api.impl.*;

/**
 * {@link AbstractSkideUnitPanel} implementation.
 *
 * @author max
 */
public class SkidePanelConfigureImportBridge
    extends AbstractSkideUnitPanel {

  /**
   * Constructor by context and unit
   *
   * @param aContext ITsGuiContext - context.
   * @param aUnit ISkideUnit - unit.
   */
  public SkidePanelConfigureImportBridge( ITsGuiContext aContext, ISkideUnit aUnit ) {
    super( aContext, aUnit );
  }

  @Override
  protected Control doCreateControl( Composite aParent ) {

    // TODO SkidePanelConfigureImportBridge.doCreateControl()

    Label l = new Label( aParent, SWT.BORDER );
    l.setText( getClass().getSimpleName() );
    return l;

  }

}
