package org.toxsoft.skf.modbus.gui.glib.bridge_old;

import static org.toxsoft.skf.modbus.lib.ISkfModbusLibConstants.*;

import org.toxsoft.core.tsgui.m5.model.*;
import org.toxsoft.core.tsgui.m5.model.impl.*;
import org.toxsoft.core.tsgui.m5.std.fields.*;
import org.toxsoft.core.tslib.utils.errors.*;
import org.toxsoft.skf.modbus.lib.cfg.bridge.*;

/**
 * M5-model of {@link IMbBridgeCfg}.
 * <p>
 * Model does not displays or edits {@link IMbBridgeCfg#listBusCfgs()}.
 *
 * @author hazard157
 */
public class MbBridgeCfgM5Model
    extends M5Model<IMbBridgeCfg> {

  /**
   * The model ID.
   */
  public static final String MODEL_ID = SKF_MB_M5_ID + ".MbBridgeCfg"; //$NON-NLS-1$

  /**
   * Fields {@link IMbBridgeCfg#id()}
   */
  public final IM5AttributeFieldDef<IMbBridgeCfg> ID = new M5StdFieldDefId<>();

  /**
   * Fields {@link IMbBridgeCfg#nmName()}
   */
  public final IM5AttributeFieldDef<IMbBridgeCfg> NAME = new M5StdFieldDefName<>();

  /**
   * Fields {@link IMbBridgeCfg#description()}
   */
  public final IM5AttributeFieldDef<IMbBridgeCfg> DESCRIPTION = new M5StdFieldDefDescription<>();

  /**
   * Constructor.
   */
  public MbBridgeCfgM5Model() {
    super( MODEL_ID, IMbBridgeCfg.class );
    addFieldDefs( ID, NAME, DESCRIPTION );
  }

  @Override
  protected IM5LifecycleManager<IMbBridgeCfg> doCreateLifecycleManager( Object aMaster ) {

    // TODO реализовать MbBridgeCfgM5Model.doCreateLifecycleManager()
    throw new TsUnderDevelopmentRtException( "MbBridgeCfgM5Model.doCreateLifecycleManager()" );

  }

}
