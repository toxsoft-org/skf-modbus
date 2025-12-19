package org.toxsoft.skf.modbus.lib.cfg.device;

import static org.toxsoft.skf.modbus.lib.cfg.device.IModbusDeviceCfgConstants.*;

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
 * Describes physical device to read the data from. Device may be accesses either by Modbus/TCP or Modbus/RTU protocol.
 * Device configuration is {@link IStridable} Id/name/description and list of the ModbusRegisters descriptions. In other
 * words, this configuration contains some extract from the device manufacturer's data sheet.
 * <p>
 * Notes about terminology: Previously such device was called 'slave', while current MODBUS specification uses term
 * 'server'. Previously 'Master' device (PLC or PC or whatever) now is called client. However we use master/slave naming
 * because it is more intuitive and true to reality.
 * <p>
 * Naming recommendations:
 * <ul>
 * <li>specify {@link #id()} as a concatenation of the manufacturer's name and device model name. For example:
 * <code><i>"com.teltonika_networks.trb141.v4";</i></code>;</li>
 * <li>{@link #nmName()} - the device designation and model name in short, eg. "4G gateway TRB141";</li>
 * <li>{@link #description()} - additional information, details of device identification, such as firmware version or
 * full part number to order device;</li>
 * <li>filling the {@link #params()} with options listed in {@link IModbusDeviceCfgConstants} is greatly
 * appreciated.</li>
 * </ul>
 * <p>
 * This is an immutable class.
 * <p>
 *
 * @author hazard157
 */
public final class ModbusDeviceCfg
    extends StridableParameterized {

  private static final String KW_REGISTERS = "Registers"; //$NON-NLS-1$

  /**
   * The keeper singleton.
   */
  @SuppressWarnings( "hiding" )
  public static final IEntityKeeper<ModbusDeviceCfg> KEEPER =
      new AbstractEntityKeeper<>( ModbusDeviceCfg.class, EEncloseMode.ENCLOSES_BASE_CLASS, null ) {

        @Override
        protected void doWrite( IStrioWriter aSw, ModbusDeviceCfg aEntity ) {
          aSw.incNewLine();
          aSw.writeAsIs( aEntity.id() );
          aSw.writeSeparatorChar();
          aSw.writeEol();
          OptionSetKeeper.KEEPER_INDENTED.write( aSw, aEntity.params() );
          aSw.writeSeparatorChar();
          aSw.writeEol();
          StrioUtils.writeIntMap( aSw, KW_REGISTERS, aEntity.registerCfs(), ModbusRegisterCfg.KEEPER, true );
          aSw.writeEol();
        }

        @Override
        protected ModbusDeviceCfg doRead( IStrioReader aSr ) {
          String id = aSr.readIdPath();
          aSr.ensureSeparatorChar();
          IOptionSet params = OptionSetKeeper.KEEPER.read( aSr );
          aSr.ensureSeparatorChar();
          IIntMap<ModbusRegisterCfg> regCfgs = StrioUtils.readIntMap( aSr, KW_REGISTERS, ModbusRegisterCfg.KEEPER );
          return new ModbusDeviceCfg( id, params, regCfgs );
        }
      };

  private final IIntMap<ModbusRegisterCfg> regsMap;

  /**
   * Constructor.
   *
   * @param aId String the device ID
   * @param aParams {@link IOptionSet} - additional options listed in {@link IModbusDeviceCfgConstants}
   * @param aRegCfgs {@link IIntMap}&lt;{@link ModbusRegisterCfg}&gt; - map "register no" - "register configuration"
   */
  public ModbusDeviceCfg( String aId, IOptionSet aParams, IIntMap<ModbusRegisterCfg> aRegCfgs ) {
    super( aId, aParams );
    IIntMapEdit<ModbusRegisterCfg> map = new SortedIntMap<>();
    map.setAll( aRegCfgs );
    regsMap = map;
  }

  /**
   * Constructor.
   *
   * @param aId String the device ID
   * @param aParams {@link IOptionSet} - values of {@link #params()}
   * @param aRegCfgs {@link IList}&lt;{@link ModbusRegisterCfg}&gt; - list of register configurations
   */
  public ModbusDeviceCfg( String aId, IOptionSet aParams, IList<ModbusRegisterCfg> aRegCfgs ) {
    super( aId, aParams );
    IIntMapEdit<ModbusRegisterCfg> map = new SortedIntMap<>();
    for( ModbusRegisterCfg r : aRegCfgs ) {
      map.put( r.regNo(), r );
    }
    regsMap = map;
  }

  /**
   * Returns all known register configuration of the device.
   *
   * @return {@link IIntMap}&lt;{@link ModbusRegisterCfg}&gt; - map "register no" - "register configuration"
   */
  public IIntMap<ModbusRegisterCfg> registerCfs() {
    return regsMap;
  }

  // ------------------------------------------------------------------------------------
  // Configuration options as a separate method
  //

  /**
   * Returns value of the option {@link IModbusDeviceCfgConstants#OPDEF_MANUFACTURER_NAME}.
   *
   * @return String - device manufacturer company name
   */
  public String getManufacturerName() {
    return OPDEF_MANUFACTURER_NAME.getValue( params() ).asString();
  }

  /**
   * Returns value of the option {@link IModbusDeviceCfgConstants#OPDEF_MANUFACTURER_URL}.
   *
   * @return String - manufacturer company site web-address
   */
  public String getManufacturerUrl() {
    return OPDEF_MANUFACTURER_URL.getValue( params() ).asString();
  }

  /**
   * Returns value of the option {@link IModbusDeviceCfgConstants#OPDEF_PRODUCT_URL}.
   *
   * @return String - product site web-address
   */
  public String getProdiuctUrl() {
    return OPDEF_PRODUCT_URL.getValue( params() ).asString();
  }

  /**
   * Returns value of the option {@link IModbusDeviceCfgConstants#OPDEF_DEVELOPER_NOTES}.
   *
   * @return String - developer notes
   */
  public String getDeveloperNotes() {
    return OPDEF_DEVELOPER_NOTES.getValue( params() ).asString();
  }

}
