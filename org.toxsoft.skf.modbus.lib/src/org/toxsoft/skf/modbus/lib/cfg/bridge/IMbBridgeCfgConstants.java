package org.toxsoft.skf.modbus.lib.cfg.bridge;

import static org.toxsoft.core.tslib.av.EAtomicType.*;
import static org.toxsoft.core.tslib.av.impl.AvUtils.*;
import static org.toxsoft.core.tslib.av.metainfo.IAvMetaConstants.*;
import static org.toxsoft.skf.modbus.lib.l10n.ISkfModbusLibSharedResources.*;

import org.toxsoft.core.tslib.av.impl.*;
import org.toxsoft.core.tslib.av.metainfo.*;
import org.toxsoft.core.tslib.bricks.strid.coll.*;
import org.toxsoft.core.tslib.bricks.strid.coll.impl.*;
import org.toxsoft.skf.modbus.lib.mbspec.*;

/**
 * Constants used to configure MODBUS bridges, buses and nodes.
 *
 * @author hazard157
 */
public interface IMbBridgeCfgConstants {

  // ------------------------------------------------------------------------------------
  // IMbBridgeCfg options
  //

  /**
   * Option of {@link IMbBridgeCfg#params()}: human readable register name
   */
  IDataDef OPDEF_MB_BRI_NAME = DataDef.create3( TSID_NAME, DDEF_NAME, //
      TSID_NAME, STR_MBBCC_BRI_NAME, //
      TSID_DESCRIPTION, STR_MBBCC_BRI_NAME_D //
  );

  /**
   * Option of {@link IMbBridgeCfg#params()}: optional detailed register description
   */
  IDataDef OPDEF_MB_BRI_DESCRIPTION = DataDef.create3( TSID_DESCRIPTION, DDEF_DESCRIPTION, //
      TSID_NAME, STR_MBBCC_BRI_DESCRIPTION, //
      TSID_DESCRIPTION, STR_MBBCC_BRI_DESCRIPTION_D //
  );

  /**
   * All possible options of {@link IMbBridgeCfg#params()}.
   */
  IStridablesList<IDataDef> MB_BRI_ALL_OPDEFS = new StridablesList<>( //
      OPDEF_MB_BRI_NAME, //
      OPDEF_MB_BRI_DESCRIPTION //
  );

  // ------------------------------------------------------------------------------------
  // IMbBusCfg options
  //

  /**
   * ID of option {@link #OPDEF_MB_BUS_IS_RTU}
   */
  String OPID_MB_BUS_IS_RTU = "isRTU"; //$NON-NLS-1$

  /**
   * Option of {@link IMbBusCfg#params()}: human readable register name
   */
  IDataDef OPDEF_MB_BUS_NAME = DataDef.create3( TSID_NAME, DDEF_NAME, //
      TSID_NAME, STR_MBBCC_BUS_NAME, //
      TSID_DESCRIPTION, STR_MBBCC_BUS_NAME_D //
  );

  /**
   * Option of {@link IMbBusCfg#params()}: optional detailed register description
   */
  IDataDef OPDEF_MB_BUS_DESCRIPTION = DataDef.create3( TSID_DESCRIPTION, DDEF_DESCRIPTION, //
      TSID_NAME, STR_MBBCC_BUS_DESCRIPTION, //
      TSID_DESCRIPTION, STR_MBBCC_BUS_DESCRIPTION_D //
  );

  /**
   * Option of {@link IMbBusCfg#params()}: bus kind is RTU flag, <code>true</code> - TRU, <code>false</code> - TCP.
   */
  IDataDef OPDEF_MB_BUS_IS_RTU = DataDef.create( OPID_MB_BUS_IS_RTU, BOOLEAN, //
      TSID_NAME, STR_MBBCC_BUS_IS_RTU, //
      TSID_DESCRIPTION, STR_MBBCC_BUS_IS_RTU_D, //
      TSID_FORMAT_STRING, "%Б[TCP|RTU]", //$NON-NLS-1$
      TSID_DEFAULT_VALUE, AV_FALSE //
  );

  /**
   * All possible options of {@link IMbBusCfg#params()}.
   */
  IStridablesList<IDataDef> MB_BUS_ALL_OPDEFS = new StridablesList<>( //
      OPDEF_MB_BUS_NAME, //
      OPDEF_MB_BUS_DESCRIPTION, //
      OPDEF_MB_BUS_IS_RTU //
  );

  // ------------------------------------------------------------------------------------
  // IMbNodeCfg options
  //

  /**
   * ID of option {@link #OPDEF_MB_NOD_TCP_IP_ADDRESS}.
   */
  String OPID_MB_NOD_TCP_IP_ADDRESS = "IpAddress"; //$NON-NLS-1$

  /**
   * ID of option {@link #OPDEF_MB_NOD_TCP_PORT_NO}.
   */
  String OPID_MB_NOD_TCP_PORT_NO = "PortNo"; //$NON-NLS-1$

  /**
   * ID of option {@link #OPDEF_MB_NOD_RTU_DEV_ADDR}.
   */
  String OPID_MB_NOD_RTU_DEV_ADDR = "DeviceAddress"; //$NON-NLS-1$

  /**
   * Option of {@link IMbNodeCfg#params()}: human readable register name
   */
  IDataDef OPDEF_MB_NOD_NAME = DataDef.create3( TSID_NAME, DDEF_NAME, //
      TSID_NAME, STR_MBBCC_NOD_NAME, //
      TSID_DESCRIPTION, STR_MBBCC_NOD_NAME_D //
  );

  /**
   * Option of {@link IMbNodeCfg#params()}: optional detailed register description
   */
  IDataDef OPDEF_MB_NOD_DESCRIPTION = DataDef.create3( TSID_DESCRIPTION, DDEF_DESCRIPTION, //
      TSID_NAME, STR_MBBCC_NOD_DESCRIPTION, //
      TSID_DESCRIPTION, STR_MBBCC_NOD_DESCRIPTION_D //
  );

  /**
   * Option of {@link IMbNodeCfg#params()}: for TCP bus: IP address on the bus. <br>
   * IP address may be specified either in numerical form (like "127.0.0.1") or as domain name (like "dev.example.com").
   */
  IDataDef OPDEF_MB_NOD_TCP_IP_ADDRESS = DataDef.create( OPID_MB_NOD_TCP_IP_ADDRESS, STRING, //
      TSID_NAME, STR_MBBCC_NOD_TCP_IP_ADDRESS, //
      TSID_DESCRIPTION, STR_MBBCC_NOD_TCP_IP_ADDRESS_D, //
      TSID_DEFAULT_VALUE, avStr( "127.0.0.1" ) //$NON-NLS-1$
  );

  /**
   * Option of {@link IMbNodeCfg#params()}: for TCP bus: TCP porn number, usually equals to 502.
   */
  IDataDef OPDEF_MB_NOD_TCP_PORT_NO = DataDef.create( OPID_MB_NOD_TCP_PORT_NO, INTEGER, //
      TSID_NAME, STR_MBBCC_NOD_TCP_PORT_NO, //
      TSID_DESCRIPTION, STR_MBBCC_NOD_TCP_PORT_NO_D, //
      TSID_MIN_INCLUSIVE, AV_1, //
      TSID_MAX_INCLUSIVE, avInt( 65535 ), //
      TSID_DEFAULT_VALUE, avInt( 502 ) //
  );

  /**
   * Option of {@link IMbNodeCfg#params()}: for RTU bus: physical address of the device on bus.<br>
   * For possible values see {@link IModbusSpecificationConstants#MB_DEVICE_ADDRESS_RANGE}.
   */
  IDataDef OPDEF_MB_NOD_RTU_DEV_ADDR = DataDef.create( OPID_MB_NOD_RTU_DEV_ADDR, INTEGER, //
      TSID_NAME, STR_MBBCC_NOD_RTU_DEV_ADDR, //
      TSID_DESCRIPTION, STR_MBBCC_NOD_RTU_DEV_ADDR_D, //
      TSID_MIN_INCLUSIVE, AV_1, //
      TSID_MAX_INCLUSIVE, avInt( 255 ), //
      TSID_DEFAULT_VALUE, AV_1 //
  );

  /**
   * All possible options of {@link IMbBusCfg#params()}.
   * <p>
   * Note: Not all options may present at same time, the TCP and RTU node addressing information is mutually exclusive..
   */
  IStridablesList<IDataDef> MB_NOD_ALL_OPDEFS = new StridablesList<>( //
      OPDEF_MB_NOD_NAME, //
      OPDEF_MB_NOD_DESCRIPTION, //
      OPDEF_MB_NOD_TCP_IP_ADDRESS, //
      OPDEF_MB_NOD_TCP_PORT_NO, //
      OPDEF_MB_NOD_RTU_DEV_ADDR //
  );
}
