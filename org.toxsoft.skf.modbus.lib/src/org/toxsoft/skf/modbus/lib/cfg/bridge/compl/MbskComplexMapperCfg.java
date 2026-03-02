package org.toxsoft.skf.modbus.lib.cfg.bridge.compl;

import org.toxsoft.core.tslib.av.opset.*;
import org.toxsoft.core.tslib.coll.primtypes.*;

public record MbskComplexMapperCfg ( IIntList reNos, IOptionSet params ) {
  //
  // /**
  // * The keeper singleton.
  // */
  // public static final IEntityKeeper<MbskComplexMapperCfg> KEEPER =
  // new AbstractEntityKeeper<>( MbskComplexMapperCfg.class, EEncloseMode.ENCLOSES_BASE_CLASS, null ) {
  //
  // @Override
  // protected void doWrite( IStrioWriter aSw, MbskComplexMapperCfg aEntity ) {
  // aSw.writeAsIs( aEntity.factoryId() );
  // aSw.writeSeparatorChar();
  // OptionSetKeeper.KEEPER_INDENTED.write( aSw, aEntity.params() );
  // }
  //
  // @Override
  // protected MbskComplexMapperCfg doRead( IStrioReader aSr ) {
  // String factoryId = aSr.readIdPath();
  // aSr.ensureSeparatorChar();
  // IOptionSet params = OptionSetKeeper.KEEPER.read( aSr );
  // return new MbskComplexMapperCfg( factoryId, params );
  // }
  //
  // };
  //
  // /**
  // * Constructor.
  // *
  // * @param factoryId String - transmitter factory ID
  // * @param params {@link IOptionSet} - transmitter creation parameters
  // * @throws TsNullArgumentRtException any argument = <code>null</code>
  // * @throws TsIllegalArgumentRtException ID is not an IDpath
  // */
  // public MbskComplexMapperCfg( IIntList reNos, IOptionSet params ) {
  // StridUtils.checkValidIdPath( factoryId );
  // TsNullArgumentRtException.checkNull( params );
  // this.factoryId = factoryId;
  // this.params = new OptionSet( params );
  // }
  //
}
