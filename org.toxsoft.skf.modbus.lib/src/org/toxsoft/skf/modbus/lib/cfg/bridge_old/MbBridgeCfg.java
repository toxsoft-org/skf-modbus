package org.toxsoft.skf.modbus.lib.cfg.bridge_old;

import org.toxsoft.core.tslib.bricks.events.change.*;
import org.toxsoft.core.tslib.bricks.keeper.*;
import org.toxsoft.core.tslib.bricks.keeper.AbstractEntityKeeper.*;
import org.toxsoft.core.tslib.bricks.strid.coll.*;
import org.toxsoft.core.tslib.bricks.strid.coll.impl.*;
import org.toxsoft.core.tslib.bricks.strid.coll.notifier.*;
import org.toxsoft.core.tslib.bricks.strid.impl.*;
import org.toxsoft.core.tslib.bricks.strio.*;
import org.toxsoft.core.tslib.utils.errors.*;
import org.toxsoft.skf.modbus.lib.cfg.device.*;

/**
 * {@link IMbBridgeCfg} editable implementation.
 *
 * @author hazard157
 */
public class MbBridgeCfg
    extends StridableParameterized
    implements IMbBridgeCfg, IGenericChangeEventCapable {

  public static final IEntityKeeper<IMbBridgeCfg> KEEPER =
      new AbstractEntityKeeper<>( IMbBridgeCfg.class, EEncloseMode.ENCLOSES_BASE_CLASS, null ) {

        @Override
        protected void doWrite( IStrioWriter aSw, IMbBridgeCfg aEntity ) {
          // TODO Auto-generated method stub

        }

        @Override
        protected IMbBridgeCfg doRead( IStrioReader aSr ) {
          // TODO Auto-generated method stub
          return null;
        }
      };

  private final GenericChangeEventer                     genericChangeEventer;
  private final INotifierStridablesListEdit<MbBusCfg>    busCfgs;
  private final INotifierStridablesListEdit<MbDeviceCfg> usedDevices;

  /**
   * Constructor.
   *
   * @param aId String - the bridge ID (IDpath)
   * @throws TsNullArgumentRtException any argument = <code>null</code>
   * @throws TsIllegalArgumentRtException ID is not an IDpath
   */
  public MbBridgeCfg( String aId ) {
    super( aId );
    genericChangeEventer = new GenericChangeEventer( this );
    busCfgs = new NotifierStridablesListEditWrapper<>( new StridablesList<>() );
    busCfgs.addCollectionChangeListener( genericChangeEventer );
    usedDevices = new NotifierStridablesListEditWrapper<>( new StridablesList<>() );
    usedDevices.addCollectionChangeListener( genericChangeEventer );
  }

  // ------------------------------------------------------------------------------------
  // IMbBridgeCfg
  //

  @Override
  public IStridablesList<IMbBusCfg> listBusCfgs() {
    return (IStridablesList)busCfgs;
  }

  @Override
  public INotifierStridablesList<MbDeviceCfg> listUsedDevices() {
    return usedDevices;
  }

  // ------------------------------------------------------------------------------------
  // Editing API
  //

  public INotifierStridablesListEdit<MbBusCfg> busCfgsEdit() {
    return busCfgs;
  }

  public INotifierStridablesList<MbDeviceCfg> usedDevicesEdit() {
    return usedDevices;
  }

  // ------------------------------------------------------------------------------------
  // IGenericChangeEventCapable
  //

  @Override
  public IGenericChangeEventer genericChangeEventer() {
    return genericChangeEventer;
  }

}
