package org.toxsoft.skf.modbus.lib.cfg.bridge.impl;

import static org.toxsoft.core.tslib.av.metainfo.IAvMetaConstants.*;
import static org.toxsoft.skf.modbus.lib.l10n.ISkfModbusLibSharedResources.*;

import org.toxsoft.core.tslib.av.opset.*;
import org.toxsoft.core.tslib.bricks.strid.coll.*;
import org.toxsoft.core.tslib.bricks.strid.coll.impl.*;
import org.toxsoft.core.tslib.bricks.validator.*;
import org.toxsoft.core.tslib.bricks.validator.std.*;
import org.toxsoft.core.tslib.coll.primtypes.*;
import org.toxsoft.core.tslib.coll.primtypes.impl.*;
import org.toxsoft.core.tslib.utils.errors.*;
import org.toxsoft.skf.modbus.lib.cfg.bridge.*;

/**
 * {@link IMbBusCfg} base implementation.
 *
 * @author hazard157
 */
class MbBusCfg
    extends MbXxxCfg<MbBridgeCfg>
    implements IMbBusCfg {

  private static final String OPID_IS_RTU = "IsRTU"; //$NON-NLS-1$

  private final IStridablesListEdit<IMbNodeCfg> nodeCfgs = new StridablesList<>();

  public MbBusCfg( MbBridgeCfg aParent, String aBusId, boolean aIsRtu, IOptionSet aParams, IStringList aFixeOpIds ) {
    super( aParent, aBusId, aParams, addSelfOpIds( aFixeOpIds ) );
    TsNullArgumentRtException.checkNulls( aParent, aBusId, aParams );
    TsItemAlreadyExistsRtException.checkTrue( aParent.listBusCfgs().hasKey( aBusId ) );
    params().setBool( OPID_IS_RTU, aIsRtu );
  }

  static IStringList addSelfOpIds( IStringList aFixedOpIds ) {
    IStringListEdit ll = new StringLinkedBundleList( aFixedOpIds );
    ll.add( OPID_IS_RTU );
    return ll;
  }

  // ------------------------------------------------------------------------------------
  // API for subclasses
  //

  protected ValidationResult canAddNodeGeneric( String aNodeId, IOptionSet aParams ) {
    if( nodeCfgs.hasKey( aNodeId ) ) {
      return ValidationResult.error( FMT_ERR_MBNODE_ALREADY_EXISTS, aNodeId );
    }
    String name = aParams.getStr( TSID_NAME, DEFAULT_NAME );
    return NameStringValidator.VALIDATOR.validate( name );
  }

  protected void addNodeToChildren( MbNodeCfg<?> aNode ) {
    nodeCfgs.add( aNode );
    genericChangeEventer().fireChangeEvent();
  }

  // ------------------------------------------------------------------------------------
  // IMbBusCfg
  //

  @Override
  public boolean isRtu() {
    return params().getBool( OPID_IS_RTU );
  }

  @Override
  public IStridablesList<? extends IMbNodeCfg> listNodeCfgs() {
    return nodeCfgs;
  }

  @Override
  public ValidationResult canRemoveNode( String aNodeId ) {
    // TODO check WARNING: node has register or multi mapping
    return ValidationResult.SUCCESS;
  }

  @Override
  public void removeNode( String aNodeId ) {
    if( nodeCfgs.removeById( aNodeId ) != null ) {
      genericChangeEventer().fireChangeEvent();
    }
  }

}
