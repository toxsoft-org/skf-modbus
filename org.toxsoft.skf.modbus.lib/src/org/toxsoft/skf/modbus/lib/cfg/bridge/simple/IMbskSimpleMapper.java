package org.toxsoft.skf.modbus.lib.cfg.bridge.simple;

import org.toxsoft.core.tslib.av.*;

/**
 * Transmits data from one MODBUS register to the USkat entities.
 *
 * @author hazard157
 */
public sealed interface IMbskSimpleMapper
    permits AbstractMbskSimpleMapper {

  /**
   * TODO замечания Максу: <br>
   * <ul>
   * <li>этот интерфейс - как я представляю трансмиттер, вообще-то конфигурация MbskSimpleMapperCfg может порождать
   * экземпляры не этого интерфейса, а IDataTransmitter;</li>
   * <li>метод инициализации start() должна находится не в интерфейсе, а базовом классе.</li>
   * </ul>
   */

  /**
   * Processes data between MODBUS and USkat data buffers.
   * <p>
   * Method is designed to exchange data between MODBUS and USkat data buffers. If data was actually transferred, then
   * method return <code>true</code> indicating that data buffer needs to be send to MODBUS or USkat depending on
   * transmission direction.
   *
   * @param aValue {@link IAtomicValue} - the value read from the MODBUS register
   * @param aTimestamp - current time, milliseconds from epoch
   * @return boolean - indicates that data actually has to be transmitted <br>
   *         <b>true</b> - data has been actually transmitted, container must sent data to received;<br>
   *         <b>false</b> - nothing has to be done, no data was transmitted.
   */
  boolean transmit( IAtomicValue aValue, long aTimestamp );

}
