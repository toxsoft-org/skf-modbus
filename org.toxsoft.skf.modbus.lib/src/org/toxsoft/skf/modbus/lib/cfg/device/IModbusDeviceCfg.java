package org.toxsoft.skf.modbus.lib.cfg.device;

import org.toxsoft.core.tslib.bricks.strid.*;

/**
 * Configuration of the single MODBUS device.
 * <p>
 * Describes physical device to read the data from. Device may be accesses either by Modbus/TCP or Modbus/RTU protocol.
 * Device configuration is {@link IStridable} Id/name/description and list of the ModbusRegisters descriptions. In other
 * words, this configuration contains some extract from the device manufacturer's data sheet.
 * <p>
 * TODO how the {@link #params()} are used?
 * <p>
 * Naming recommendations:
 * <ul>
 * <li>specify {@link #id()} as a concatenation of the manufacturer's name and device model name. For example:
 * <code><i>"com.teltonika_networks.trb141.v4";</i></code>;</li>
 * <li>{@link #nmName()} - the device designation and model name in short, eg. "4G gateway TRB141";</li>
 * <li>{@link #description()} - additional information about usage details;</li>
 * <li>filling the {@link #params()} with options listed in {@link IModbusDeviceCfgConstants} is gratly
 * appreciated.</li>
 * </ul>
 *
 * @author hazard157
 */
public interface IModbusDeviceCfg
    extends IStridableParameterized {

}
