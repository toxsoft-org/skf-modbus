package org.toxsoft.skf.modbus.gui.glib.bridge;

import org.eclipse.swt.*;
import org.eclipse.swt.custom.*;
import org.eclipse.swt.widgets.*;
import org.toxsoft.core.tsgui.bricks.ctx.*;
import org.toxsoft.core.tsgui.bricks.ctx.impl.*;
import org.toxsoft.core.tsgui.panels.generic.*;
import org.toxsoft.core.tslib.coll.*;
import org.toxsoft.core.tslib.utils.errors.*;
import org.toxsoft.skf.modbus.lib.cfg.bridge.*;

/**
 * Panel edits list of {@link IMbBridgeCfg}.
 * <p>
 * Panel contains three panes:
 * <ul>
 * <li>left pane - {@link PanelModbusTree} 3-level tree made of MODBUS Bridges {@link IMbBridgeCfg}, child Buses
 * {@link IMbBusCfg} and grandchild MODBUS Nodes {@link IMbNodeCfg};</li>
 * <li>middle pane - editable properties of the item selected in left pane, For the selected MODBUS Node: the list of
 * register mappings {@link IMbRegisterMappingCfg} above and below is the list of multi-mappings
 * {@link IMbMultiMappingCfg};</li>
 * <li>right panel - mapping inplace editor of the settings of the selected mapping either register
 * {@link IMbRegisterMappingCfg} or multi {@link IMbMultiMappingCfg}.</li>
 * </ul>
 * Any changes in the configuration tree generates an event of the eventer {@link #genericChangeEventer()}.
 *
 * @author hazard157
 */
public class PanelMbBridgeCfgsEditor
    extends AbstractGenericCollPanel<IMbBridgeCfg>
    implements IBridgeEditorPanel {

  private final PanelModbusTree panelModbusTree;

  /**
   * Constructor.
   * <p>
   * Constructor stores reference to the context, does not creates copy.
   *
   * @param aContext {@link ITsGuiContext} - the context
   * @throws TsNullArgumentRtException any argument = <code>null</code>
   */
  public PanelMbBridgeCfgsEditor( ITsGuiContext aContext ) {
    super( aContext );
    // IEditorEnvironment
    tsContext().put( IEditorEnvironment.class, new EditorEnvironment() );
    // panelModbusTree
    ITsGuiContext ctx1 = new TsGuiContext( tsContext() );
    panelModbusTree = new PanelModbusTree( ctx1 );
  }

  // ------------------------------------------------------------------------------------
  // AbstractGenericCollPanel
  //

  @Override
  protected Control doCreateControl( Composite aParent ) {
    SashForm sfMain = new SashForm( aParent, SWT.HORIZONTAL );
    // panelModbusTree
    panelModbusTree.createControl( sfMain );

    // TODO Auto-generated method stub
    return sfMain;
  }

  @Override
  public IMbBridgeCfg selectedItem() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void setSelectedItem( IMbBridgeCfg aItem ) {

    // TODO Auto-generated method stub

  }

  @Override
  public IList<IMbBridgeCfg> items() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void refresh() {
    // TODO Auto-generated method stub

  }

  // ------------------------------------------------------------------------------------
  // API
  //

  public void setItems( IList<IMbBridgeCfg> aItems ) {
    // TODO PanelMbBridgeCfgsEditor.setItems()
  }

}
