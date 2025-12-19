package org.toxsoft.skf.modbus.gui.glib;

import static org.toxsoft.core.tsgui.valed.api.IValedControlConstants.*;

import org.toxsoft.core.tsgui.bricks.ctx.*;
import org.toxsoft.core.tsgui.valed.api.*;
import org.toxsoft.core.tsgui.valed.controls.av.*;
import org.toxsoft.core.tsgui.valed.impl.*;
import org.toxsoft.core.tslib.av.*;
import org.toxsoft.core.tslib.coll.primtypes.*;

/**
 * Wraps over {@link ValedIntListMbFuncCodes} to edit as {@link IAtomicValue}.
 *
 * @author hazard157
 */
public class ValedAvValobjIntListMbFuncCodes
    extends AbstractAvValobjWrapperValedControl<IIntList> {

  /**
   * The factory name.
   */
  public static final String FACTORY_NAME = VALED_EDNAME_PREFIX + ".AvValobjIntListMbFuncCodes"; //$NON-NLS-1$

  /**
   * The factory class.
   *
   * @author hazard157
   */
  static class Factory
      extends AbstractValedControlFactory {

    protected Factory() {
      super( FACTORY_NAME );
    }

    @SuppressWarnings( "unchecked" )
    @Override
    protected IValedControl<IAtomicValue> doCreateEditor( ITsGuiContext aContext ) {
      return new ValedAvValobjIntListMbFuncCodes( aContext );
    }

  }

  /**
   * The factory singleton.
   */
  public static final AbstractValedControlFactory FACTORY = new Factory();

  protected ValedAvValobjIntListMbFuncCodes( ITsGuiContext aTsContext ) {
    super( aTsContext, ValedIntListMbFuncCodes.FACTORY );
  }

}
