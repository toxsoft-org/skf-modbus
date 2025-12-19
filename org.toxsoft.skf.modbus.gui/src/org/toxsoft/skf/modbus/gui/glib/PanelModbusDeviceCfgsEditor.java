package org.toxsoft.skf.modbus.gui.glib;

import org.eclipse.swt.*;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.widgets.*;
import org.toxsoft.core.tsgui.bricks.ctx.*;
import org.toxsoft.core.tsgui.m5.*;
import org.toxsoft.core.tsgui.m5.gui.panels.*;
import org.toxsoft.core.tsgui.m5.model.*;
import org.toxsoft.core.tsgui.panels.*;
import org.toxsoft.core.tslib.utils.errors.*;
import org.toxsoft.skf.modbus.gui.e4.services.*;
import org.toxsoft.skf.modbus.gui.m5.*;
import org.toxsoft.skf.modbus.lib.cfg.device.*;

/**
 * Edits the specified registry {@link IModbusDeviceCfgRegistry}.
 *
 * @author hazard157
 */
public class PanelModbusDeviceCfgsEditor
    extends TsStdEventsProducerPanel<ModbusDeviceCfg> {

  private final IM5Model<ModbusDeviceCfg>           model;
  private final IM5CollectionPanel<ModbusDeviceCfg> panel;

  private IModbusDeviceCfgRegistry devRegistry = null;

  /**
   * Constructor.
   * <p>
   * If <code>aDevRegistry</code> = <code>null</code> constructor initializes {@link #devCfgRegistry()} with reference
   * found in context. If context does not contains reference to {@link IModbusDeviceCfgRegistry}, <code>null</code>
   * reference is used and list of devices remains empty until call to
   * {@link #setDevCfgRegistry(IModbusDeviceCfgRegistry)}.
   *
   * @param aParent {@link Composite} - parent component
   * @param aContext {@link ITsGuiContext} - the context
   * @param aViewer boolean - <code>true</code> to create panel in read-only mode
   * @param aDevRegistry {@link IModbusDeviceCfgRegistry} - the registry or <code>null</code>
   * @throws TsNullArgumentRtException any argument = <code>null</code>
   */
  public PanelModbusDeviceCfgsEditor( Composite aParent, ITsGuiContext aContext, boolean aViewer,
      IModbusDeviceCfgRegistry aDevRegistry ) {
    super( aParent, aContext );
    devRegistry = aDevRegistry != null ? aDevRegistry : tsContext().find( IModbusDeviceCfgRegistry.class );
    model = m5().getModel( ModbusDeviceCfgM5Model.MODEL_ID, ModbusDeviceCfg.class );
    IM5LifecycleManager<ModbusDeviceCfg> lm = null;
    IM5ItemsProvider<ModbusDeviceCfg> ip = null;
    if( devRegistry != null ) {
      lm = model.getLifecycleManager( devRegistry );
      ip = lm.itemsProvider();
    }
    if( aViewer ) {
      panel = model.panelCreator().createCollViewerPanel( aContext, ip );
    }
    else {
      panel = model.panelCreator().createCollEditPanel( aContext, ip, lm );
    }
    // init GUI
    this.setLayout( new BorderLayout() );
    panel.createControl( this );
    panel.getControl().setLayoutData( new BorderData( SWT.CENTER ) );
    panel.addTsSelectionListener( selectionChangeEventHelper );
    panel.addTsDoubleClickListener( doubleClickEventHelper );
  }

  // ------------------------------------------------------------------------------------
  // TsStdEventsProducerPanel
  //

  @Override
  public ModbusDeviceCfg selectedItem() {
    return panel.selectedItem();
  }

  @Override
  public void setSelectedItem( ModbusDeviceCfg aItem ) {
    panel.setSelectedItem( aItem );
  }

  // ------------------------------------------------------------------------------------
  // API
  //

  /**
   * Returns displayed MODBUS devices registry.
   *
   * @return {@link IModbusDeviceCfgRegistry} - the registry, may be <code>null</code>
   */
  public IModbusDeviceCfgRegistry devCfgRegistry() {
    return devRegistry;
  }

  /**
   * Sets the devices registry to display it's content/
   *
   * @param aDevRegistry {@link IModbusDeviceCfgRegistry} - the registry or <code>null</code>
   */
  public void setDevCfgRegistry( IModbusDeviceCfgRegistry aDevRegistry ) {
    devRegistry = aDevRegistry;
    // TODO update panel
  }

}
