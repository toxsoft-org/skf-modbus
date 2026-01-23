package org.toxsoft.skf.modbus.gui.glib.bridge_old;

import org.toxsoft.core.tslib.bricks.strid.*;
import org.toxsoft.core.tslib.bricks.validator.*;

public interface IMbBridgeCfgsEditorValidator {

  <T extends IStridableParameterized> ValidationResult canCreate( MbNodePath aParentPath, T aItem );

  <T extends IStridableParameterized> ValidationResult canEdit( MbNodePath aParentPath, String aOldId, T aItem );

  ValidationResult canRemove( MbNodePath aPath );

}
