package org.toxsoft.skf.modbus.lib.cfg.device;

import static org.toxsoft.skf.modbus.lib.cfg.device.IMbDeviceCfgConstants.*;

import org.toxsoft.core.tslib.av.opset.*;
import org.toxsoft.core.tslib.av.opset.impl.*;
import org.toxsoft.core.tslib.bricks.keeper.*;
import org.toxsoft.core.tslib.bricks.keeper.AbstractEntityKeeper.*;
import org.toxsoft.core.tslib.bricks.strid.*;
import org.toxsoft.core.tslib.bricks.strid.impl.*;
import org.toxsoft.core.tslib.bricks.strio.*;
import org.toxsoft.core.tslib.bricks.strio.impl.*;
import org.toxsoft.core.tslib.coll.*;
import org.toxsoft.core.tslib.coll.primtypes.*;
import org.toxsoft.core.tslib.coll.primtypes.impl.*;

/**
 * Configuration of the single MODBUS slave device.
 * <p>
 * This is an immutable class.
 * <p>
 * Describes physical device to read/write the data from/to. Device may be accesses either by Modbus/TCP or Modbus/RTU
 * protocol. Device configuration is {@link IStridable} Id/name/description, list of the available registers
 * descriptions and some additional information about devices as a product. In other words, this configuration contains
 * some extract from the device manufacturer's data sheet.
 * <p>
 * Notes about terminology: previously such device was called 'slave', while current MODBUS specification uses term
 * 'server'. Previously 'Master' device (PLC or PC or whatever) now is called client. However we use master/slave naming
 * because it is more intuitive and true to reality.
 * <p>
 * Naming recommendations:
 * <ul>
 * <li>specify {@link #id()} as a concatenation of the manufacturer's name and device model name. For example:
 * <code><i>"com.teltonika_networks.trb141.v4";</i></code>;</li>
 * <li>{@link #nmName()} - the device designation and model name in short, eg. "TRB141 (4G gateway)";</li>
 * <li>{@link #description()} - additional information, details of device identification, such as firmware version or
 * full part number to order device;</li>
 * <li>filling the {@link #params()} with options listed in {@link IMbDeviceCfgConstants#MB_DEV_ALL_OPDEFS} is greatly
 * appreciated.</li>
 * </ul>
 * <p>
 * Options of the {@link #params()} are listed in {@link IMbDeviceCfgConstants} with prefix <b>OPDEF_MB_DEV_</b>XXX.
 *
 * @author hazard157
 */
public final class MbDeviceCfg
    extends StridableParameterized {

  private static final String KW_REGISTERS = "Registers"; //$NON-NLS-1$

  /**
   * The keeper singleton.
   */
  @SuppressWarnings( "hiding" )
  public static final IEntityKeeper<MbDeviceCfg> KEEPER =
      new AbstractEntityKeeper<>( MbDeviceCfg.class, EEncloseMode.ENCLOSES_BASE_CLASS, null ) {

        @Override
        protected void doWrite( IStrioWriter aSw, MbDeviceCfg aEntity ) {
          aSw.incNewLine();
          aSw.writeAsIs( aEntity.id() );
          aSw.writeSeparatorChar();
          aSw.writeEol();
          OptionSetKeeper.KEEPER_INDENTED.write( aSw, aEntity.params() );
          aSw.writeSeparatorChar();
          aSw.writeEol();
          StrioUtils.writeIntMap( aSw, KW_REGISTERS, aEntity.registerCfs(), MbRegisterCfg.KEEPER, true );
          aSw.writeEol();
        }

        @Override
        protected MbDeviceCfg doRead( IStrioReader aSr ) {
          String id = aSr.readIdPath();
          aSr.ensureSeparatorChar();
          IOptionSet params = OptionSetKeeper.KEEPER.read( aSr );
          aSr.ensureSeparatorChar();
          IIntMap<MbRegisterCfg> regCfgs = StrioUtils.readIntMap( aSr, KW_REGISTERS, MbRegisterCfg.KEEPER );
          return new MbDeviceCfg( id, params, regCfgs );
        }
      };

  private final IIntMap<MbRegisterCfg> regsMap;

  /**
   * Constructor.
   *
   * @param aId String the device ID
   * @param aParams {@link IOptionSet} - additional options listed in {@link IMbDeviceCfgConstants}
   * @param aRegCfgs {@link IIntMap}&lt;{@link MbRegisterCfg}&gt; - map "register no" - "register configuration"
   */
  public MbDeviceCfg( String aId, IOptionSet aParams, IIntMap<MbRegisterCfg> aRegCfgs ) {
    super( aId, aParams );
    IIntMapEdit<MbRegisterCfg> map = new SortedIntMap<>();
    map.setAll( aRegCfgs );
    regsMap = map;
  }

  /**
   * Constructor.
   *
   * @param aId String the device ID
   * @param aParams {@link IOptionSet} - values of {@link #params()}
   * @param aRegCfgs {@link IList}&lt;{@link MbRegisterCfg}&gt; - list of register configurations
   */
  public MbDeviceCfg( String aId, IOptionSet aParams, IList<MbRegisterCfg> aRegCfgs ) {
    super( aId, aParams );
    IIntMapEdit<MbRegisterCfg> map = new SortedIntMap<>();
    for( MbRegisterCfg r : aRegCfgs ) {
      map.put( r.regNo(), r );
    }
    regsMap = map;
  }

  /**
   * Returns all known register configuration of the device.
   *
   * @return {@link IIntMap}&lt;{@link MbRegisterCfg}&gt; - map "register no" - "register configuration"
   */
  public IIntMap<MbRegisterCfg> registerCfs() {
    return regsMap;
  }

  // ------------------------------------------------------------------------------------
  // Configuration options as a separate method
  //

  /**
   * returns the sign that device uses byte sequence CDAB (instead of ABCD) for multi-register data encoding.
   *
   * @return boolean CDAB encoding flag<br>
   *         <b>true</b> - device uses CDAB byte sequence;<br>
   *         <b>false</b> - device uses ABCD byte sequence.
   */
  public boolean isCdab() {
    return OPDEF_MB_DEV_IS_CDAB.getValue( params() ).asBool();
  }

  /**
   * Returns value of the option {@link IMbDeviceCfgConstants#OPDEF_MB_DEV_MANUFACTURER_NAME}.
   *
   * @return String - device manufacturer company name
   */
  public String getManufacturerName() {
    return OPDEF_MB_DEV_MANUFACTURER_NAME.getValue( params() ).asString();
  }

  /**
   * Returns value of the option {@link IMbDeviceCfgConstants#OPDEF_MB_DEV_MANUFACTURER_URL}.
   *
   * @return String - manufacturer company site web-address
   */
  public String getManufacturerUrl() {
    return OPDEF_MB_DEV_MANUFACTURER_URL.getValue( params() ).asString();
  }

  /**
   * Returns value of the option {@link IMbDeviceCfgConstants#OPDEF_MB_DEV_PRODUCT_URL}.
   *
   * @return String - product site web-address
   */
  public String getProdiuctUrl() {
    return OPDEF_MB_DEV_PRODUCT_URL.getValue( params() ).asString();
  }

  /**
   * Returns value of the option {@link IMbDeviceCfgConstants#OPDEF_MB_DEV_DEVELOPER_NOTES}.
   *
   * @return String - developer notes
   */
  public String getDeveloperNotes() {
    return OPDEF_MB_DEV_DEVELOPER_NOTES.getValue( params() ).asString();
  }

}
