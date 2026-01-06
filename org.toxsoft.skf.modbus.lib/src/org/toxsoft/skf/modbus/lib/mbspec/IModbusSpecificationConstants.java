package org.toxsoft.skf.modbus.lib.mbspec;

import static org.toxsoft.skf.modbus.lib.l10n.ISkfModbusLibSharedResources.*;

import org.toxsoft.core.tslib.bricks.validator.*;
import org.toxsoft.core.tslib.bricks.validator.impl.*;
import org.toxsoft.core.tslib.math.*;

/**
 * MODBUS protocol constants to be used in USkat applications..
 * <p>
 * See <a href="https://www.modbus.org/file/secure/modbusprotocolspecification.pdf">MODBUS protocol specification for
 * terminology and details.</a>. Comments below references to the chapter numbers of the mentioned specification.
 * <p>
 * This interface contains only part of the MODBUS protocol definitions. The API neither is full nor intended for
 * production usage. This API is intended solely for USkat applications using MODBUS protocol.
 * <p>
 * This API was created based on specification as for December, 2025.
 *
 * @author hazard157
 */
@SuppressWarnings( "javadoc" )
public interface IModbusSpecificationConstants {

  // ------------------------------------------------------------------------------------
  // Section 5.1 Public function codes (mostly used subset)
  // See also corresponding enum EModbusFuncCode

  int MB_FC_01_READ_COILS     = 1;  // read several coils (output registers, DOs), see section 6.1
  int MB_FC_02_READ_DI        = 2;  // read several digital inputs (DIs), see section 6.2
  int MB_FC_03_READ_HOLD_REGS = 3;  // read several holding registers (AOs), see section 6.3
  int MB_FC_04_READ_IN_REG    = 4;  // read input register (AI), see section 6.4
  int MB_FC_05_WRITE_COIL     = 5;  // write single coil (DO), see section 6.5
  int MB_FC_06_WRITE_REG      = 6;  // write single register (AO), see section 6.6
  int MB_FC_12_WRITE_REGS     = 12; // write several registers (AOs), see section 6.12
  int MB_FC_15_WRITE_COILS    = 15; // write several coils (DOs), see section 6.11

  // ------------------------------------------------------------------------------------
  // 7 MODBUS Exception Responses

  int MB_ERRCODE_OK                       = 0;  // defined by library to indicate no error status of function call
  int MB_ERROCDE_ILLEGAL_FUNCTION         = 1;
  int MB_ERROCDE_ILLEGAL_DATA_ADDRESS     = 2;
  int MB_ERROCDE_ILLEGAL_DATA_VALUE       = 3;
  int MB_ERROCDE_SERVER_DEVICE_FAILURE    = 4;
  int MB_ERROCDE_ACKNOWLEDGE              = 5;
  int MB_ERROCDE_SERVER_DEVICE_BUSY       = 6;
  int MB_ERROCDE_MEMORY_PARITY_ERROR      = 7;
  int MB_ERROCDE_GATEWAY_PATH_UNAVAILABLE = 0xA;
  int MB_ERROCDE_GATEWAY_TARGET_FAIL      = 0xB;

  // ------------------------------------------------------------------------------------
  // Miscellaneous

  /**
   * Allowed range of the TCP port for MODBUS/TCP communication.
   * <p>
   * Defines 2-byte wide port address range.
   */
  IntRange MB_TCP_PORT_RANGE = new IntRange( 1, 65536 );

  /**
   * Allowed range of the device (slave, server) address on the MODBUS bus.
   * <p>
   * Notes:
   * <ul>
   * <li>address 0 is used for broadcasting;</li>
   * <li>device address is optional for TCP communication, however it may be required for specific devices;</li>
   * <li>Defines 1-byte wide address range.</li>
   * </ul>
   */
  IntRange MB_DEVICE_ADDRESS_RANGE = new IntRange( 0, 255 );

  /**
   * Allowed range of MODBUS registers addresses, any device may have lower range of addresses.
   */
  IntRange MB_REG_ADDRESS_RANGE = new IntRange( 0, 65535 );

  /**
   * How many coils may be read by function {@link #MB_FC_01_READ_COILS} - read several coils at once.
   */
  IntRange MB_COILS_AT_ONCE = new IntRange( 1, 2000 );

  // ------------------------------------------------------------------------------------
  // Helper methods

  /**
   * Checks if MODBUS register value is in allowed range {@link IModbusSpecificationConstants#MB_REG_ADDRESS_RANGE}.
   *
   * @param aAddress int - MODBUS register address (0 based addressing)
   * @return {@link ValidationResult} - the check result
   */
  static ValidationResult validateMbAddress( int aAddress ) {
    if( !MB_REG_ADDRESS_RANGE.isInRange( aAddress ) ) {
      return ValidationResult.error( FMT_ERR_REG_ADDR_OUT_OF_RANGE, Integer.valueOf( aAddress ),
          Integer.valueOf( aAddress ), MB_REG_ADDRESS_RANGE.toString() );
    }
    return ValidationResult.SUCCESS;
  }

  /**
   * Throws an exception if argument is not in allowed range {@link IModbusSpecificationConstants#MB_REG_ADDRESS_RANGE}.
   *
   * @param aAddress int - MODBUS register address (0 based addressing)
   * @throws TsValidationFailedRtException argument is not valid MODBUS address
   * @see #validateMbAddress(int)
   */
  static void checkMbAddress( int aAddress ) {
    TsValidationFailedRtException.checkError( validateMbAddress( aAddress ) );
  }

  /**
   * Checks if MODBUS registers range is out of allowed range.
   * <p>
   * Address range is specified as a starting address and number of consecutive words (registers). Number of consecutive
   * registers must be > 0 and last addressed register also must be in allowed range.
   *
   * @param aAddress int - starting MODBUS register address (0 based addressing)
   * @param aRegsQtty int - number of consecutive registers (2-byte words) starting from <code>aAddress</code>
   * @return {@link ValidationResult} - the check result
   */
  static ValidationResult validateMbAddressRange( int aAddress, int aRegsQtty ) {
    ValidationResult vr = validateMbAddress( aAddress );
    if( vr.isError() ) {
      return vr;
    }
    if( aRegsQtty <= 0 ) {
      return ValidationResult.error( FMT_ERR_INV_REG_SEQ_COUNT, Integer.valueOf( aRegsQtty ) );
    }
    int lastAddress = aAddress + aRegsQtty;
    if( lastAddress > MB_REG_ADDRESS_RANGE.maxValue() ) {
      return ValidationResult.error( FMT_ERR_SEQ_REGS_OUT_OF_RANGE, Integer.valueOf( lastAddress ),
          MB_REG_ADDRESS_RANGE.toString() );
    }
    return ValidationResult.SUCCESS;
  }

  /**
   * Throws an exception if address range is not in allowed range {@link #MB_ADDRESS_RANGE}.
   *
   * @param aAddress int - starting MODBUS register address (0 based addressing)
   * @param aRegsQtty int - number of consecutive registers (2-byte words) starting from <code>aAddress</code>
   * @throws TsValidationFailedRtException argument is not valid MODBUS address
   * @see #validateMbAddressRange(int, int)
   */
  static void checkMbAddressRange( int aAddress, int aRegsQtty ) {
    TsValidationFailedRtException.checkError( validateMbAddressRange( aAddress, aRegsQtty ) );
  }

}
