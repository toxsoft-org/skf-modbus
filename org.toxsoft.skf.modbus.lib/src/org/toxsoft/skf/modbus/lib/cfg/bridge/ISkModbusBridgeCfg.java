package org.toxsoft.skf.modbus.lib.cfg.bridge;

import org.toxsoft.core.tslib.av.opset.*;
import org.toxsoft.core.tslib.bricks.strid.*;
import org.toxsoft.core.tslib.bricks.strid.coll.*;
import org.toxsoft.core.tslib.bricks.strid.more.*;
import org.toxsoft.core.tslib.coll.*;

public interface ISkModbusBridgeCfg {

  interface IMappingCfg {

    // TODO translator type/kind/ ID ?

    // TODO translator parameters

  }

  interface INodeCfg
      extends IStridableParameterized {

    String deviceId();

    IOptionSet tcpAddress();

    IOptionSet rtuAddress();

    // TODO register <-> Gwid mapping ???

  }

  interface IBusCfg
      extends IStridableParameterized {

    boolean isRtu(); // true - Modbus/RTU via COM/TTY, false - Modbus/TCP view Ethernet

    IOptionSet tcpSettings(); // net mask, the device name, etc.

    IOptionSet rtuSettings(); // COM port, baud create, stop bit, etc.

    IStridablesList<INodeCfg> nodeCfgs();

  }

  IStridablesList<IBusCfg> busCfgs();

  IList<IdPair> listNodeIds();

  INodeCfg findNodeCfg( IdPair aNodeId );

}
