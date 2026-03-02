package org.toxsoft.skf.modbus.lib.conv_old;

import org.toxsoft.core.tslib.av.opset.*;
import org.toxsoft.core.tslib.av.opset.impl.*;
import org.toxsoft.core.tslib.av.utils.*;
import org.toxsoft.core.tslib.bricks.keeper.*;
import org.toxsoft.core.tslib.bricks.keeper.AbstractEntityKeeper.*;
import org.toxsoft.core.tslib.bricks.strio.*;
import org.toxsoft.core.tslib.utils.errors.*;
import org.toxsoft.skf.modbus.lib.cfg.device.conv.*;
import org.toxsoft.skf.modbus.lib.conv.*;

/**
 * Configuration for {@link IMbConverter} instance creation.
 *
 * @author hazard157
 * @param type {@link EMbConverterType} - the converter type
 * @param params {@link IOptionSet} - values of options defined by {@link IMbConverterFactory#listOptions()}
 */
public record MbConverterCfg ( EMbConverterType type, IOptionSet params )
    implements IParameterized {

  /**
   * The keeper singleton.
   */
  public static final IEntityKeeper<MbConverterCfg> KEEPER =
      new AbstractEntityKeeper<>( MbConverterCfg.class, EEncloseMode.ENCLOSES_BASE_CLASS, null ) {

        @Override
        protected void doWrite( IStrioWriter aSw, MbConverterCfg aEntity ) {
          EMbConverterType.KEEPER.write( aSw, aEntity.type() );
          aSw.writeSeparatorChar();
          OptionSetKeeper.KEEPER.write( aSw, aEntity.params() );
        }

        @Override
        protected MbConverterCfg doRead( IStrioReader aSr ) {
          EMbConverterType type = EMbConverterType.KEEPER.read( aSr );
          aSr.ensureSeparatorChar();
          IOptionSet params = OptionSetKeeper.KEEPER.read( aSr );
          return new MbConverterCfg( type, params );
        }
      };

  /**
   * Constructor.
   *
   * @param type {@link EMbConverterType} - the converter type
   * @param params {@link IOptionSet} - values of options defined by {@link IMbConverterFactory#listOptions()}
   * @throws TsNullArgumentRtException any argument = <code>null</code>
   */
  public MbConverterCfg( EMbConverterType type, IOptionSet params ) {
    TsNullArgumentRtException.checkNulls( params );
    this.type = type;
    this.params = params;
  }

  // ------------------------------------------------------------------------------------
  // IParameterized
  //

  @Override
  public IOptionSet params() {
    return this.params;
  }

}
