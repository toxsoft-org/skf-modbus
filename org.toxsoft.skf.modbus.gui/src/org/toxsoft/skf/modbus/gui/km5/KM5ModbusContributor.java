package org.toxsoft.skf.modbus.gui.km5;

import org.toxsoft.core.tsgui.m5.*;
import org.toxsoft.core.tslib.bricks.strid.coll.*;
import org.toxsoft.core.tslib.bricks.strid.coll.impl.*;
import org.toxsoft.core.tslib.coll.primtypes.*;
import org.toxsoft.core.tslib.utils.errors.*;
import org.toxsoft.uskat.core.connection.*;
import org.toxsoft.uskat.core.gui.km5.*;

/**
 * Contributes M5-models for opc ua entities.
 *
 * @author max
 * @author dima
 * @author hazard157
 */
public class KM5ModbusContributor
    extends KM5AbstractContributor {

  /**
   * Creator singleton.
   */
  public static final IKM5ContributorCreator CREATOR = KM5ModbusContributor::new;

  /**
   * Constructor.
   *
   * @param aConn {@link ISkConnection} - the connection
   * @param aDomain {@link IM5Domain} - connection domain
   * @throws TsNullArgumentRtException any argument = <code>null</code>
   */
  public KM5ModbusContributor( ISkConnection aConn, IM5Domain aDomain ) {
    super( aConn, aDomain );
  }

  @Override
  protected IStringList papiCreateModels() {
    IStridablesList<IM5Model<?>> llModels = new StridablesList<>();

    // TODO KM5ModbusContributor.papiCreateModels()

    return llModels.ids();
  }

}
