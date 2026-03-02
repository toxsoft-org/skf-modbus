package org.toxsoft.skf.modbus.gui.utils;

import org.toxsoft.core.tsgui.panels.generic.*;
import org.toxsoft.core.tslib.av.opset.*;

public interface IMapperPanelCreatorsRegistry {

  // FIXME develop API

  IGenericEntityPanel<IOptionSet> registerSimpleMapperPanelCreator( String aSimpleMapperFactoryId,
      IGenericEntityEditPanelCreator<IOptionSet> aCreator );

  IGenericEntityPanel<IOptionSet> registerComplexMapperPanelCreator( String aComplexMapperFactoryId,
      IGenericEntityEditPanelCreator<IOptionSet> aCreator );

}
