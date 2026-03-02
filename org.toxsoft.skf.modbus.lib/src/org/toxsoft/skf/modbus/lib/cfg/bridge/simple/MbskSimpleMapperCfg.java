package org.toxsoft.skf.modbus.lib.cfg.bridge.simple;

import org.toxsoft.core.tslib.av.opset.*;
import org.toxsoft.core.tslib.av.opset.impl.*;
import org.toxsoft.core.tslib.av.utils.*;
import org.toxsoft.core.tslib.bricks.keeper.*;
import org.toxsoft.core.tslib.bricks.keeper.AbstractEntityKeeper.*;
import org.toxsoft.core.tslib.bricks.strid.impl.*;
import org.toxsoft.core.tslib.bricks.strio.*;
import org.toxsoft.core.tslib.utils.errors.*;

/**
 * Configuration for {@link IMbskSimpleMapper} creation.
 *
 * @author hazard157
 * @param factoryId String - transmitter factory ID
 * @param params {@link IOptionSet} - transmitter creation parameters
 */
public record MbskSimpleMapperCfg ( String factoryId, IOptionSet params )
    implements IParameterized {

  /**
   * The keeper singleton.
   */
  public static final IEntityKeeper<MbskSimpleMapperCfg> KEEPER =
      new AbstractEntityKeeper<>( MbskSimpleMapperCfg.class, EEncloseMode.ENCLOSES_BASE_CLASS, null ) {

        @Override
        protected void doWrite( IStrioWriter aSw, MbskSimpleMapperCfg aEntity ) {
          aSw.writeAsIs( aEntity.factoryId() );
          aSw.writeSeparatorChar();
          OptionSetKeeper.KEEPER_INDENTED.write( aSw, aEntity.params() );
        }

        @Override
        protected MbskSimpleMapperCfg doRead( IStrioReader aSr ) {
          String factoryId = aSr.readIdPath();
          aSr.ensureSeparatorChar();
          IOptionSet params = OptionSetKeeper.KEEPER.read( aSr );
          return new MbskSimpleMapperCfg( factoryId, params );
        }

      };

  /**
   * Constructor.
   *
   * @param factoryId String - transmitter factory ID
   * @param params {@link IOptionSet} - transmitter creation parameters
   * @throws TsNullArgumentRtException any argument = <code>null</code>
   * @throws TsIllegalArgumentRtException ID is not an IDpath
   */
  public MbskSimpleMapperCfg( String factoryId, IOptionSet params ) {
    StridUtils.checkValidIdPath( factoryId );
    TsNullArgumentRtException.checkNull( params );
    this.factoryId = factoryId;
    this.params = new OptionSet( params );
  }

}
