package org.toxsoft.skf.modbus.gui.glib.bridge_old;

import static org.toxsoft.core.tslib.av.metainfo.IAvMetaConstants.*;

import org.toxsoft.core.tsgui.m5.*;
import org.toxsoft.core.tsgui.m5.model.impl.*;
import org.toxsoft.core.tslib.bricks.strid.*;
import org.toxsoft.core.tslib.bricks.validator.*;
import org.toxsoft.core.tslib.coll.*;
import org.toxsoft.core.tslib.utils.errors.*;
import org.toxsoft.skf.modbus.lib.cfg.bridge.*;

/**
 * LM for {@link MbBridgeCfgM5Model}.
 *
 * @author hazard157
 */
public class MbBridgeCfgM5LifecycleManager
    extends M5LifecycleManager<IMbBridgeCfg, IMbBridgeCfgsEditorEnvironment> {

  /**
   * Constructor.
   *
   * @param aModel {@link IM5Model}&lt;T&gt; - the model
   * @param aMaster &lt;{@link IMbBridgeCfgsEditorEnvironment}&gt; - master object
   * @throws TsNullArgumentRtException any argument = <code>null</code>
   */
  public MbBridgeCfgM5LifecycleManager( IM5Model<IMbBridgeCfg> aModel, IMbBridgeCfgsEditorEnvironment aMaster ) {
    super( aModel, true, true, true, true, aMaster );
  }

  // ------------------------------------------------------------------------------------
  // implementation
  //

  private static IMbBridgeCfg make( IM5Bunch<IMbBridgeCfg> aValues ) {
    String id = aValues.getAsAv( TSID_ID ).asString();
    String name = aValues.getAsAv( TSID_NAME ).asString();
    String description = aValues.getAsAv( TSID_DESCRIPTION ).asString();

    // TODO реализовать MbBridgeCfgM5LifecycleManager.make()
    throw new TsUnderDevelopmentRtException( "MbBridgeCfgM5LifecycleManager.make()" );

  }

  // ------------------------------------------------------------------------------------
  // M5LifecycleManager
  //

  @Override
  protected ValidationResult doBeforeCreate( IM5Bunch<IMbBridgeCfg> aValues ) {
    IStridableParameterized s = make( aValues );

    // TODO реализовать MbBridgeCfgM5LifecycleManager.doBeforeCreate()
    throw new TsUnderDevelopmentRtException( "MbBridgeCfgM5LifecycleManager.doBeforeCreate()" );

  }

  @Override
  protected IMbBridgeCfg doCreate( IM5Bunch<IMbBridgeCfg> aValues ) {
    // TODO Auto-generated method stub
    return super.doCreate( aValues );
  }

  @Override
  protected ValidationResult doBeforeEdit( IM5Bunch<IMbBridgeCfg> aValues ) {
    // TODO Auto-generated method stub
    return super.doBeforeEdit( aValues );
  }

  @Override
  protected IMbBridgeCfg doEdit( IM5Bunch<IMbBridgeCfg> aValues ) {
    // TODO Auto-generated method stub
    return super.doEdit( aValues );
  }

  @Override
  protected ValidationResult doBeforeRemove( IMbBridgeCfg aEntity ) {
    MbNodePath path = MbNodePath.ofBridge( aEntity.id() );
    return master().svs().validator().canRemove( path );
  }

  @Override
  protected void doRemove( IMbBridgeCfg aEntity ) {
    MbNodePath path = MbNodePath.ofBridge( aEntity.id() );
    master().remove( path );
  }

  @Override
  protected IList<IMbBridgeCfg> doListEntities() {
    return master().bridgeCfgs();
  }

}
