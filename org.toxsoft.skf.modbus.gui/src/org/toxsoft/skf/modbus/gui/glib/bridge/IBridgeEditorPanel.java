package org.toxsoft.skf.modbus.gui.glib.bridge;

import org.toxsoft.core.tsgui.bricks.ctx.*;

/**
 * Extends {@link ITsGuiContextable} with access to bridge editor internals.
 *
 * @author hazard157
 */
interface IBridgeEditorPanel
    extends ITsGuiContextable {

  default IEditorEnvironment edEnv() {
    return tsContext().get( IEditorEnvironment.class );
  }

}
