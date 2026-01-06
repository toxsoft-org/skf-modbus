package org.toxsoft.skf.modbus.lib.cfg.device;

import static org.toxsoft.core.tslib.av.EAtomicType.*;
import static org.toxsoft.core.tslib.av.impl.AvUtils.*;
import static org.toxsoft.core.tslib.av.metainfo.IAvMetaConstants.*;
import static org.toxsoft.skf.modbus.lib.l10n.ISkfModbusLibSharedResources.*;

import org.toxsoft.core.tslib.av.impl.*;
import org.toxsoft.core.tslib.av.metainfo.*;
import org.toxsoft.core.tslib.bricks.keeper.std.*;
import org.toxsoft.core.tslib.bricks.strid.coll.*;
import org.toxsoft.core.tslib.bricks.strid.coll.impl.*;
import org.toxsoft.core.tslib.coll.primtypes.*;

/**
 * {@link MbDeviceCfg#params()} and {@link MbRegisterCfg#params()} option definition constants
 *
 * @author hazard157
 */
public interface IMbDeviceCfgConstants {

  // ------------------------------------------------------------------------------------
  // MODBUS registers configuration options for MbRegisterCfg.params()
  //

  /**
   * ID of option {@link #OPDEF_MB_REG_POOL_LENGTH}
   */
  String OPID_MB_REG_POOL_LENGTH = "PoolLength"; //$NON-NLS-1$

  /**
   * ID of option {@link #OPDEF_MB_REG_APPLICABLE_FUNCS}
   */
  String OPID_MB_REG_APPLICABLE_FUNCS = "FuncNos"; //$NON-NLS-1$

  /**
   * Option of {@link MbRegisterCfg#params()}: human readable register name
   */
  IDataDef OPDEF_MB_REG_REGISTER_NAME = DataDef.create3( TSID_NAME, DDEF_NAME, //
      TSID_NAME, STR_MSDCC_REGISTER_NAME, //
      TSID_DESCRIPTION, STR_MSDCC_REGISTER_NAME_D //
  );

  /**
   * Option of {@link MbRegisterCfg#params()}: optional detailed register description
   */
  IDataDef OPDEF_MB_REG_REGISTER_DESCRIPTION = DataDef.create3( TSID_DESCRIPTION, DDEF_DESCRIPTION, //
      TSID_NAME, STR_MSDCC_REGISTER_DESCRIPTION, //
      TSID_DESCRIPTION, STR_MSDCC_REGISTER_DESCRIPTION_D //
  );

  /**
   * Option of {@link MbDeviceCfg#params()}: number of consecutive registers.
   * <p>
   * Some values requires more than one register (for example, string or 64-bit double). This option contains quantity
   * of the consecutive registers used to sore single data.
   * <p>
   * Default option value is 1 - the single register.
   */
  IDataDef OPDEF_MB_REG_POOL_LENGTH = DataDef.create( OPID_MB_REG_POOL_LENGTH, INTEGER, //
      TSID_NAME, STR_MSDCC_POOL_LENGTH, //
      TSID_DESCRIPTION, STR_MSDCC_POOL_LENGTH_D, //
      TSID_DEFAULT_VALUE, AV_1 //
  );

  /**
   * Option of {@link MbDeviceCfg#params()}: applicable function codes to access register.
   * <p>
   * This option contains list of applicable function codes (as an {@link IIntList}) if such list is defined by the
   * device manufacturer. In most cases this option contains an empty list.
   * <p>
   * By default option value is {@link IIntList#EMPTY}.
   */
  IDataDef OPDEF_MB_REG_APPLICABLE_FUNCS = DataDef.create( OPID_MB_REG_APPLICABLE_FUNCS, VALOBJ, //
      TSID_NAME, STR_MSDCC_APPLICABLE_FUNCS, //
      TSID_DESCRIPTION, STR_MSDCC_APPLICABLE_FUNCS_D, //
      TSID_KEEPER_ID, IntListKeeper.KEEPER_ID, //
      TSID_DEFAULT_VALUE, avValobj( IIntList.EMPTY ) //
  );

  /**
   * All known options of the {@link MbRegisterCfg#params()}.
   */
  IStridablesList<IDataDef> MB_REG_ALL_OPDEFS = new StridablesList<>( //
      DDEF_NAME, //
      DDEF_DESCRIPTION, //
      OPDEF_MB_REG_POOL_LENGTH, //
      OPDEF_MB_REG_APPLICABLE_FUNCS //
  );

  // ------------------------------------------------------------------------------------
  // MODBUS device configuration options for MbDeviceCfg.params()
  //

  /**
   * Option of {@link MbDeviceCfg#params()}: contains value of {@link MbDeviceCfg#nmName()}
   */
  IDataDef OPDEF_MB_DEV_DEVICE_NAME = DataDef.create3( TSID_NAME, DDEF_NAME, //
      TSID_NAME, STR_MSDCC_DEVICE_NAME, //
      TSID_DESCRIPTION, STR_MSDCC_DEVICE_NAME_D //
  );

  /**
   * Option of {@link MbDeviceCfg#params()}: contains value of {@link MbDeviceCfg#description()}
   */
  IDataDef OPDEF_MB_DEV_DEVICE_DESCRIPTION = DataDef.create3( TSID_DESCRIPTION, DDEF_DESCRIPTION, //
      TSID_NAME, STR_MSDCC_DEVICE_DESCRIPTION, //
      TSID_DESCRIPTION, STR_MSDCC_DEVICE_DESCRIPTION_D //
  );

  /**
   * Option of {@link MbDeviceCfg#params()}: The name of the device manufacturer company.<br>
   * See chapter 6.21 of MODBUS specification, command 43 / 14 (0x2B / 0x0E) Read Device Identification.
   */
  IDataDef OPDEF_MB_DEV_MANUFACTURER_NAME = DataDef.create( "ManufacturerName", STRING, //$NON-NLS-1$
      TSID_NAME, STR_MSDCC_MANUFACTURER_NAME, //
      TSID_DESCRIPTION, STR_MSDCC_MANUFACTURER_NAME_D, //
      TSID_DEFAULT_VALUE, AV_STR_EMPTY //
  );

  /**
   * Option of {@link MbDeviceCfg#params()}: URL address of the manufacturer company site.<br>
   * See chapter 6.21 of MODBUS specification, command 43 / 14 (0x2B / 0x0E) Read Device Identification.
   */
  IDataDef OPDEF_MB_DEV_MANUFACTURER_URL = DataDef.create( "ManufacturerUrl", STRING, //$NON-NLS-1$
      TSID_NAME, STR_MSDCC_MANUFACTURER_URL, //
      TSID_DESCRIPTION, STR_MSDCC_MANUFACTURER_URL_D, //
      TSID_DEFAULT_VALUE, AV_STR_EMPTY //
  );

  /**
   * Option of {@link MbDeviceCfg#params()}: The URL-address of the device (as a product) description.<br>
   * See chapter 6.21 of MODBUS specification, command 43 / 14 (0x2B / 0x0E) Read Device Identification.
   */
  IDataDef OPDEF_MB_DEV_PRODUCT_URL = DataDef.create( "ProductUrl", STRING, //$NON-NLS-1$
      TSID_NAME, STR_MSDCC_PRODUCT_URL, //
      TSID_DESCRIPTION, STR_MSDCC_PRODUCT_URL_D, //
      TSID_DEFAULT_VALUE, AV_STR_EMPTY //
  );

  /**
   * Option of {@link MbDeviceCfg#params()}: Notes of the developer engineer about product usage.<br>
   * This option may used to describe some hints from the developer engineer, like details of communication, setup,
   * usage or versions of this physical device.
   */
  IDataDef OPDEF_MB_DEV_DEVELOPER_NOTES = DataDef.create3( "DeveloperNotes", DDEF_DESCRIPTION, //$NON-NLS-1$
      TSID_NAME, STR_MSDCC_DEVELOPER_NOTES, //
      TSID_DESCRIPTION, STR_MSDCC_DEVELOPER_NOTES_D, //
      TSID_DEFAULT_VALUE, AV_STR_EMPTY //
  );

  /**
   * All known options of the {@link MbDeviceCfg#params()}.
   */
  IStridablesList<IDataDef> MB_DEV_ALL_OPDEFS = new StridablesList<>( //
      DDEF_NAME, //
      DDEF_DESCRIPTION, //
      OPDEF_MB_DEV_MANUFACTURER_NAME, //
      OPDEF_MB_DEV_MANUFACTURER_URL, //
      OPDEF_MB_DEV_PRODUCT_URL, //
      OPDEF_MB_DEV_DEVELOPER_NOTES //
  );

}
