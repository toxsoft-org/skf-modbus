package org.toxsoft.skf.modbus.gui.m5.device;

import static org.toxsoft.core.tsgui.m5.gui.mpc.IMultiPaneComponentConstants.*;
import static org.toxsoft.core.tslib.av.impl.AvUtils.*;
import static org.toxsoft.skf.modbus.gui.l10n.ISkfModbusGuiSharedResources.*;

import org.toxsoft.core.tsgui.bricks.actions.asp.*;
import org.toxsoft.core.tsgui.bricks.ctx.*;
import org.toxsoft.core.tsgui.m5.gui.panels.*;
import org.toxsoft.core.tsgui.m5.gui.panels.impl.*;
import org.toxsoft.core.tsgui.m5.gui.panels.std.*;
import org.toxsoft.core.tsgui.m5.model.*;
import org.toxsoft.core.tsgui.m5.model.impl.*;
import org.toxsoft.core.tsgui.panels.vecboard.*;
import org.toxsoft.core.tsgui.panels.vecboard.impl.*;
import org.toxsoft.core.tsgui.utils.layout.*;
import org.toxsoft.core.tsgui.valed.api.*;
import org.toxsoft.core.tslib.coll.primtypes.*;
import org.toxsoft.core.tslib.coll.primtypes.impl.*;
import org.toxsoft.skf.modbus.gui.e4.services.*;
import org.toxsoft.skf.modbus.lib.cfg.device.*;

/**
 * {@link IM5PanelCreator} implementation for model {@link MbDeviceCfgM5Model}.
 *
 * @author hazard157
 */
class MbDeviceCfgM5PanelCreator
    extends M5DefaultPanelCreator<MbDeviceCfg> {

  public MbDeviceCfgM5PanelCreator() {
    // nop
  }

  // ------------------------------------------------------------------------------------
  // M5DefaultPanelCreator
  //

  @Override
  protected IM5CollectionPanel<MbDeviceCfg> doCreateCollViewerPanel( ITsGuiContext aContext,
      IM5ItemsProvider<MbDeviceCfg> aItemsProvider ) {
    OPDEF_DETAILS_PANE_PLACE.setValue( aContext.params(), avValobj( EBorderLayoutPlacement.EAST ) );
    return super.doCreateCollViewerPanel( aContext, aItemsProvider );
  }

  @Override
  protected IM5CollectionPanel<MbDeviceCfg> doCreateCollEditPanel( ITsGuiContext aContext,
      IM5ItemsProvider<MbDeviceCfg> aItemsProvider, IM5LifecycleManager<MbDeviceCfg> aLifecycleManager ) {
    IModbusDeviceCfgRegistry devRegistry = IModbusDeviceCfgRegistry.class.cast( aLifecycleManager.master() );
    CompoundTsActionSetProvider asp = new CompoundTsActionSetProvider();
    asp.addHandler( new AspMbDeviceCollExpImp( aContext, devRegistry ) );
    IM5StandardPanelConstants.REFDEF_M5STD_PANEL_ACTIONS_ASP.setRef( aContext, asp );
    OPDEF_DETAILS_PANE_PLACE.setValue( aContext.params(), avValobj( EBorderLayoutPlacement.EAST ) );
    return super.doCreateCollEditPanel( aContext, aItemsProvider, aLifecycleManager );
  }

  @Override
  protected IM5EntityPanel<MbDeviceCfg> doCreateEntityEditorPanel( ITsGuiContext aContext,
      IM5LifecycleManager<MbDeviceCfg> aLifecycleManager ) {
    return new M5DefaultEntityEditorPanel<>( aContext, model(), aLifecycleManager ) {

      @Override
      protected void doInitLayout() {
        // prepare editor: #edMap for tab1, valedRigstersTable - for tab2
        IStringMapEdit<IValedControl<?>> edMap = new StringMap<>( editors() );
        IValedControl<?> valedRigstersTable = edMap.removeByKey( MbDeviceCfgM5Model.FID_REGISTERS );
        // create layout for board
        IVecTabLayout tabLayout = new VecTabLayout( false );
        // tab 1: properties (name, description, etc)
        IVecBoard b1 = new VecBoard();
        b1.setLayout( makeLadderLayout( model(), edMap ) );
        IVecTabLayoutData ld1 =
            new VecTabLayoutData( STR_MODBUS_DEVICE_CFG_PROPS_TAB, STR_MODBUS_DEVICE_CFG_PROPS_TAB_D );
        tabLayout.addControl( b1, ld1 );
        // tab 2: registers table
        IVecTabLayoutData ld2 =
            new VecTabLayoutData( STR_MODBUS_DEVICE_CFG_REGISTERS, STR_MODBUS_DEVICE_CFG_REGISTERS_D );
        tabLayout.addControl( valedRigstersTable, ld2 );
        board().setLayout( tabLayout );
      }

    };
  }

}
