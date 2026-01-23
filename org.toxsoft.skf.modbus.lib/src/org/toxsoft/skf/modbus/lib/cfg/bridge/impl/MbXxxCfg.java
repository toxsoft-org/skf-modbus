package org.toxsoft.skf.modbus.lib.cfg.bridge.impl;

import static org.toxsoft.skf.modbus.lib.l10n.ISkfModbusLibSharedResources.*;

import org.toxsoft.core.tslib.av.opset.*;
import org.toxsoft.core.tslib.av.opset.impl.*;
import org.toxsoft.core.tslib.av.utils.*;
import org.toxsoft.core.tslib.bricks.events.change.*;
import org.toxsoft.core.tslib.bricks.strid.impl.*;
import org.toxsoft.core.tslib.bricks.validator.*;
import org.toxsoft.core.tslib.coll.impl.*;
import org.toxsoft.core.tslib.coll.primtypes.*;
import org.toxsoft.core.tslib.coll.primtypes.impl.*;

/**
 * Base class for all MbXxxCfg classes.
 * <p>
 * Supports:
 * <ul>
 * <li>concept of options that can not be changed through {@link #paramsBatchEditor()};</li>
 * <li>change eventer.</li>
 * </ul>
 *
 * @author hazard157
 */
class MbXxxCfg<P extends MbXxxCfg<?>>
    extends StridableParameterized
    implements IGenericChangeEventCapable, IParameterizedBatchEdit {

  private final P                    parent;
  private final GenericChangeEventer eventer;
  private final OpsBatchEdit         paramsBatchEdit;
  private final IStringList          fixedOpIds;     // these options can't be changed by #paramsBatchEdito()

  public MbXxxCfg( P aParent, String aId, IOptionSet aPatams, IStringList aFixedOpIds ) {
    super( aId, aPatams );
    fixedOpIds = new StringArrayList( aFixedOpIds );
    parent = aParent;
    eventer = new GenericChangeEventer( this );
    // inform parent about change
    eventer.addListener( src -> {
      if( parent != null ) {
        parent.genericChangeEventer().fireChangeEvent();
      }
    } );
    // batch editor
    paramsBatchEdit = new OpsBatchEdit( params() ) {
      @Override
      protected void doAfterOpsChanged() {
        eventer.fireChangeEvent(); // inform parent about change
      }

      @Override
      protected ValidationResult doValidateSetParams( IOptionSet aNewValues, IOptionSet aOldValues ) {
        // prohibit fixed options change by params batch editor
        if( TsCollectionsUtils.intersects( aFixedOpIds, aNewValues.keys() ) ) {
          return ValidationResult.error( FMT_ERR_CANT_CHANGE_FIXED_OP, fixedOpIds.toString() );
        }
        return ValidationResult.SUCCESS;
      }
    };
  }

  // ------------------------------------------------------------------------------------
  // API
  //

  final public P parent() {
    return parent;
  }

  // ------------------------------------------------------------------------------------
  // IParameterizedBatchEdit
  //

  @Override
  final public IOpsBatchEdit paramsBatchEditor() {
    return paramsBatchEdit;
  }

  // ------------------------------------------------------------------------------------
  // IGenericChangeEventCapable
  //

  @Override
  final public GenericChangeEventer genericChangeEventer() {
    return eventer;
  }

}
