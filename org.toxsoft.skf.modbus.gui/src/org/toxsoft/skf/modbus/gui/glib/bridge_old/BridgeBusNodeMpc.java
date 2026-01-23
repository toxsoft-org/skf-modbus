package org.toxsoft.skf.modbus.gui.glib.bridge_old;

import org.toxsoft.core.tsgui.bricks.ctx.*;
import org.toxsoft.core.tsgui.m5.*;
import org.toxsoft.core.tsgui.m5.gui.mpc.*;
import org.toxsoft.core.tsgui.m5.gui.mpc.impl.*;
import org.toxsoft.core.tsgui.m5.model.*;

/**
 * {@link IMultiPaneComponent} implementation for {@link MbNodePath} tree.
 *
 * @author hazard157
 */
class BridgeBusNodeMpc
    extends MultiPaneComponentModown<MbNodePath> {

  public BridgeBusNodeMpc( ITsGuiContext aContext, IM5Model<MbNodePath> aModel,
      IM5ItemsProvider<MbNodePath> aItemsProvider, IM5LifecycleManager<MbNodePath> aLifecycleManager ) {
    super( aContext, aModel, aItemsProvider, aLifecycleManager );
  }

  // ------------------------------------------------------------------------------------
  // implementation
  //

  /**
   * Invokes dialog and allows user to select parent in the tree for child creation.
   *
   * @param aInitial {@link MbNodePath} - initially selected path or <code>null</code>
   * @return {@link MbNodePath} - the tree node to create child under or <code>null</code>
   */
  private MbNodePath selectParent( MbNodePath aInitial ) {

    // TODO BridgeBusNodeMpc.selectParent()

    return null;
  }

  private MbNodePath getParentForChildCreation() {
    MbNodePath parent = selectedItem();
    if( parent == null || parent.kind() == EMbNodePathKind.NODE ) {
      parent = selectParent( parent != null ? parent.createParent() : null );
    }
    return parent;
  }

  // ------------------------------------------------------------------------------------
  // MultiPaneComponentModown
  //

  @Override
  protected MbNodePath doAddItem() {
    MbNodePath parent = getParentForChildCreation();
    if( parent == null ) {
      return null;
    }

    // TODO Auto-generated method stub
    return super.doAddItem();
  }

  @Override
  protected MbNodePath doAddCopyItem( MbNodePath aSrcItem ) {
    // TODO Auto-generated method stub
    return super.doAddCopyItem( aSrcItem );
  }

  @Override
  protected MbNodePath doEditItem( MbNodePath aItem ) {
    // TODO Auto-generated method stub
    return super.doEditItem( aItem );
  }

  @Override
  protected boolean doRemoveItem( MbNodePath aItem ) {
    // TODO Auto-generated method stub
    return super.doRemoveItem( aItem );
  }

}
