package org.toxsoft.skf.modbus.gui.glib.bridge;

import static org.toxsoft.core.tsgui.bricks.tstree.impl.TsTreeViewer.*;

import org.eclipse.swt.*;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.widgets.*;
import org.toxsoft.core.tsgui.bricks.ctx.*;
import org.toxsoft.core.tsgui.bricks.ctx.impl.*;
import org.toxsoft.core.tsgui.bricks.tstree.*;
import org.toxsoft.core.tsgui.bricks.tstree.impl.*;
import org.toxsoft.core.tsgui.panels.generic.*;
import org.toxsoft.core.tsgui.panels.toolbar.*;
import org.toxsoft.core.tslib.coll.*;
import org.toxsoft.skf.modbus.lib.cfg.bridge.*;

/**
 * 3-level tree made of {@link IMbBridgeCfg} - {@link IMbBusCfg} - {@link IMbNodeCfg}.
 * <p>
 * The source of root nodes is {@link IEditorEnvironment#bridgeCfgs()}.
 *
 * @author hazard157
 */
class PanelModbusTree
    extends AbstractGenericCollPanel<MbTreePath>
    implements IBridgeEditorPanel {

  private final TsToolbar     toolbar;
  private final ITsTreeViewer treeViewer;

  public PanelModbusTree( ITsGuiContext aContext ) {
    super( aContext );
    ITsGuiContext ctx1 = new TsGuiContext( tsContext() );
    ctx1.params().setBool( OPDEF_IS_HEADER_SHOWN, false );
    treeViewer = new TsTreeViewer( ctx1 );
    ITsGuiContext ctx2 = new TsGuiContext( tsContext() );
    toolbar = new TsToolbar( ctx2 );

    // TODO Auto-generated constructor stub
  }

  // ------------------------------------------------------------------------------------
  // AbstractGenericCollPanel
  //

  @Override
  protected Control doCreateControl( Composite aParent ) {
    Composite backplane = new Composite( aParent, SWT.NONE );
    backplane.setLayout( new BorderLayout() );
    // toolbar
    // TODO set actions
    toolbar.createControl( backplane );
    toolbar.getControl().setLayoutData( new BorderData( SWT.TOP ) );

    // treeViewer
    treeViewer.createControl( backplane );
    treeViewer.getControl().setLayoutData( new BorderData( SWT.CENTER ) );

    // TODO Auto-generated method stub

    return backplane;
  }

  @Override
  public MbTreePath selectedItem() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void setSelectedItem( MbTreePath aItem ) {
    // TODO Auto-generated method stub

  }

  @Override
  public IList<MbTreePath> items() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void refresh() {
    // TODO Auto-generated method stub

  }

}
