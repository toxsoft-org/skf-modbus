package org.toxsoft.skf.modbus.gui.glib.device;

import static org.toxsoft.core.tsgui.valed.api.IValedControlConstants.*;
import static org.toxsoft.skf.modbus.gui.l10n.ISkfModbusGuiSharedResources.*;

import org.toxsoft.core.tsgui.bricks.ctx.*;
import org.toxsoft.core.tsgui.bricks.ctx.impl.*;
import org.toxsoft.core.tsgui.dialogs.datarec.*;
import org.toxsoft.core.tsgui.m5.*;
import org.toxsoft.core.tsgui.m5.gui.*;
import org.toxsoft.core.tsgui.m5.model.*;
import org.toxsoft.core.tsgui.valed.api.*;
import org.toxsoft.core.tsgui.valed.controls.helpers.*;
import org.toxsoft.core.tsgui.valed.impl.*;
import org.toxsoft.core.tslib.coll.*;
import org.toxsoft.core.tslib.coll.impl.*;
import org.toxsoft.core.tslib.coll.primtypes.*;
import org.toxsoft.core.tslib.coll.primtypes.impl.*;
import org.toxsoft.core.tslib.utils.errors.*;
import org.toxsoft.skf.modbus.gui.m5.*;
import org.toxsoft.skf.modbus.gui.m5.device.*;
import org.toxsoft.skf.modbus.lib.mbspec.*;

/**
 * Edits {@link IIntList} as a list of MODBUS function codes of enum {@link EModbusFuncCode}.
 *
 * @author hazard157
 */
public class ValedIntListMbFuncCodes
    extends AbstractValedTextAndButton<IIntList> {

  /**
   * The factory name.
   */
  public static final String FACTORY_NAME = VALED_EDNAME_PREFIX + ".IntListMbFuncCodes"; //$NON-NLS-1$

  /**
   * The factory class.
   *
   * @author hazard157
   */
  @SuppressWarnings( "unchecked" )
  static class Factory
      extends AbstractValedControlFactory {

    protected Factory() {
      super( FACTORY_NAME );
    }

    @Override
    protected IValedControl<IIntList> doCreateEditor( ITsGuiContext aContext ) {
      return new ValedIntListMbFuncCodes( aContext );
    }

    @Override
    protected IValedControl<IIntList> doCreateViewer( ITsGuiContext aContext ) {
      return new ValedIntListMbFuncCodes( aContext );
    }

  }

  /**
   * The factory singleton.
   */
  public static final AbstractValedControlFactory FACTORY = new Factory();

  private final IIntListBasicEdit value = new SortedIntLinkedBundleList();

  /**
   * Constructor.
   *
   * @param aContext {@link ITsGuiContext} - the context
   * @throws TsNullArgumentRtException any argument = <code>null</code>
   */
  public ValedIntListMbFuncCodes( ITsGuiContext aContext ) {
    super( aContext );
  }

  // ------------------------------------------------------------------------------------
  // implementation
  //

  /**
   * Shows {@link #value} in the {@link #getTextControl()}.
   */
  private void updateDisplayedValue() {
    if( !value.isEmpty() ) {
      StringBuilder sb = new StringBuilder();
      for( int i = 0, n = value.size(); i < n; i++ ) {
        sb.append( value.getValue( i ) );
        if( i < n - 1 ) {
          sb.append( ',' );
          sb.append( ' ' );
        }
      }
      getTextControl().setText( sb.toString() );
    }
    else {
      getTextControl().setText( STR_MB_FUMC_CODES_NOT_SPECIFIED );
    }
  }

  // ------------------------------------------------------------------------------------
  // AbstractValedControl
  //

  @Override
  protected boolean doProcessButtonPress() {
    ITsGuiContext ctx = new TsGuiContext( tsContext() );
    ITsDialogInfo di = new TsDialogInfo( ctx, DLG_SELECT_MB_FUMC_CODES, DLG_SELECT_MB_FUMC_CODES_D );
    IM5Model<EModbusFuncCode> mfcModel = m5().getModel( MbFuncCodeM5Model.MODEL_ID, EModbusFuncCode.class );
    // prepare list of enum constants from the #value
    IListEdit<EModbusFuncCode> initVal = new ElemArrayList<>();
    for( int i = 0; i < value.size(); i++ ) {
      EModbusFuncCode mfc = EModbusFuncCode.asMap().findByKey( value.getValue( i ) );
      if( mfc != null ) {
        initVal.add( mfc );
      }
    }
    IM5ItemsProvider<EModbusFuncCode> ip = EModbusFuncCode::asList;
    IList<EModbusFuncCode> llMfc = M5GuiUtils.askSelectItemsList( di, mfcModel, initVal, ip );
    if( llMfc == null || llMfc.equals( initVal ) ) {
      return false;
    }
    // update #value and display
    value.clear();
    for( EModbusFuncCode mfc : llMfc ) {
      value.add( mfc.code() );
    }
    updateDisplayedValue();
    return true;
  }

  @Override
  protected void doDoSetUnvalidatedValue( IIntList aValue ) {
    value.setAll( aValue );
    updateDisplayedValue();
  }

  @Override
  protected IIntList doGetUnvalidatedValue() {
    return value;
  }
}
