package org.toxsoft.skf.modbus.gui.m5.device;

import static org.toxsoft.core.tsgui.m5.IM5Constants.*;
import static org.toxsoft.core.tslib.av.EAtomicType.*;
import static org.toxsoft.core.tslib.av.impl.AvUtils.*;
import static org.toxsoft.core.tslib.av.metainfo.IAvMetaConstants.*;
import static org.toxsoft.skf.modbus.gui.l10n.ISkfModbusGuiSharedResources.*;
import static org.toxsoft.uskat.core.ISkHardConstants.*;

import org.toxsoft.core.tsgui.m5.model.*;
import org.toxsoft.core.tsgui.m5.model.impl.*;
import org.toxsoft.core.tsgui.m5.std.models.enums.*;
import org.toxsoft.core.tslib.av.*;
import org.toxsoft.skf.modbus.lib.mbspec.*;

/**
 * M5-model of {@link EModbusFuncCode}.
 *
 * @author hazard157
 */
public class MbFuncCodeM5Model
    extends M5StridableEnumModelBase<EModbusFuncCode> {

  /**
   * The model ID.
   */
  public static final String MODEL_ID = SK_ID + ".m5.skf.modbus.ModbusFuncCode"; //$NON-NLS-1$

  /**
   * ID of field {@link #CODE}
   */
  public static final String FID_CODE = "Code"; //$NON-NLS-1$

  /**
   * Field {@link EModbusFuncCode#code()}
   */
  public final IM5AttributeFieldDef<EModbusFuncCode> CODE = new M5AttributeFieldDef<>( FID_CODE, INTEGER, //
      TSID_NAME, STR_MB_FUNC_INTEGER_CODE, //
      TSID_DESCRIPTION, STR_MB_FUNC_INTEGER_CODE_D, //
      M5_OPDEF_FLAGS, avInt( M5FF_COLUMN | M5FF_READ_ONLY ), //
      TSID_DEFAULT_VALUE, AV_0 //
  ) {

    protected IAtomicValue doGetFieldValue( EModbusFuncCode aEntity ) {
      return avInt( aEntity.code() );
    }

  };

  /**
   * Constructor.
   */
  public MbFuncCodeM5Model() {
    super( MODEL_ID, EModbusFuncCode.class );
    addFieldDefs( CODE );
    fieldsReorderer().move( CODE, 2 );
  }

}
