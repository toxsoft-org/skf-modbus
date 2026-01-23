package org.toxsoft.skf.modbus.gui.glib.bridge;

import org.toxsoft.core.tsgui.mws.services.currentity.*;
import org.toxsoft.core.tslib.av.opset.*;
import org.toxsoft.core.tslib.bricks.events.change.*;
import org.toxsoft.core.tslib.bricks.strid.coll.*;
import org.toxsoft.core.tslib.bricks.validator.*;
import org.toxsoft.core.tslib.coll.helpers.*;
import org.toxsoft.core.tslib.utils.errors.*;
import org.toxsoft.skf.modbus.lib.cfg.bridge.*;

/**
 * Environment for the editor of the MODBUS bridge configurations list.
 * <p>
 * Instance of this interface is created by the panel {@link PanelMbBridgeCfgsEditor} and put in panel's context. All
 * child panels and dialogs have access to this interface,
 * <p>
 * Interface introduces following concepts:
 * <ul>
 * <li>the list of configuration being edited - {@link #bridgeConfigs()};</li>
 * <li>xxx;</li>
 * <li>zzz.</li>
 * </ul>
 *
 * @author hazard157
 */
interface IEditorEnvironment
    extends IGenericChangeEventCapable {

  /**
   * Returns list of bridge configuration being edited.
   *
   * @return {@link IStridablesListEdit}&lt;{@link IMbBridgeCfg}&gt; - configuration of the bridges
   */
  IStridablesList<IMbBridgeCfg> bridgeCfgs();

  IListReorderer<IMbBridgeCfg> bridgeCfgsReorderer();

  /**
   * Finds the entity by path in {@link #bridgeCfgs()}.
   * <p>
   * If path does not points to any entity in {@link #bridgeCfgs()}, returns <code>null</code>. If kind of argument is
   * {@link EMbNodePathKind#ROOT} also returns <code>null</code>.
   *
   * @param <T> - expected kind of entity
   * @param aPath {@link MbTreePath} - the path
   * @return &lt;T&gt; - found entity or <code>null</code>
   * @throws TsNullArgumentRtException any argument = <code>null</code>
   */
  <T> T find( MbTreePath aPath );

  ValidationResult canAddBridge( String aBridgeId, IOptionSet aParams );

  IMbBridgeCfg addBridge( String aBridgeId, IOptionSet aParams );

  ValidationResult canRemoveBridge( String aBridgeId );

  void removeBridge( String aBridgeId );

  ICurrentEntityService<MbTreePath> cesBridge();

}
