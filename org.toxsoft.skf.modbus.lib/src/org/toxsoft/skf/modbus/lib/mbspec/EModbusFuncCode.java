package org.toxsoft.skf.modbus.lib.mbspec;

import static org.toxsoft.skf.modbus.lib.l10n.ISkfModbusLibSharedResources.*;
import static org.toxsoft.skf.modbus.lib.mbspec.IModbusSpecificationConstants.*;

import org.toxsoft.core.tslib.bricks.keeper.*;
import org.toxsoft.core.tslib.bricks.keeper.std.*;
import org.toxsoft.core.tslib.bricks.strid.*;
import org.toxsoft.core.tslib.bricks.strid.coll.*;
import org.toxsoft.core.tslib.bricks.strid.coll.impl.*;
import org.toxsoft.core.tslib.coll.primtypes.*;
import org.toxsoft.core.tslib.coll.primtypes.impl.*;
import org.toxsoft.core.tslib.utils.errors.*;

/**
 * A subset of the MODBUS public function code as described in section 5.1 of the MODBUS specification.
 *
 * @author hazard157
 */
@SuppressWarnings( "javadoc" )
public enum EModbusFuncCode
    implements IStridable {

  FC_01_READ_COILS( "fc01", MB_FC_01_READ_COILS, STR_MFC_01_READ_COILS, STR_MFC_01_READ_COILS_D ), //$NON-NLS-1$

  FC_02_READ_DI( "fc02", MB_FC_02_READ_DI, STR_MFC_02_READ_DI, STR_MFC_02_READ_DI_D ), //$NON-NLS-1$

  FC_03_READ_HOLD_REGS( "fc03", MB_FC_03_READ_HOLD_REGS, STR_MFC_03_READ_HOLD_REGS, STR_MFC_03_READ_HOLD_REGS_D ), //$NON-NLS-1$

  FC_04_READ_IN_REG( "fc04", MB_FC_04_READ_IN_REG, STR_MFC_04_READ_IN_REG, STR_MFC_04_READ_IN_REG_D ), //$NON-NLS-1$

  FC_05_WRITE_COIL( "fc05", MB_FC_05_WRITE_COIL, STR_MFC_05_WRITE_COIL, STR_MFC_05_WRITE_COIL_D ), //$NON-NLS-1$

  FC_15_WRITE_COILS( "fc15", MB_FC_15_WRITE_COILS, STR_MFC_15_WRITE_COILS, STR_MFC_15_WRITE_COILS_D ), //$NON-NLS-1$

  FC_06_WRITE_REG( "fc06", MB_FC_06_WRITE_REG, STR_MFC_06_WRITE_REG, STR_MFC_06_WRITE_REG_D ), //$NON-NLS-1$

  FC_12_WRITE_REGS( "fc12", MB_FC_12_WRITE_REGS, STR_MFC_12_WRITE_REGS, STR_MFC_12_WRITE_REGS_D ), //$NON-NLS-1$

  ;

  /**
   * The registered keeper ID.
   */
  public static final String KEEPER_ID = "EModbusFuncCode"; //$NON-NLS-1$

  /**
   * The keeper singleton.
   */
  public static final IEntityKeeper<EModbusFuncCode> KEEPER = new StridableEnumKeeper<>( EModbusFuncCode.class );

  private static IStridablesListEdit<EModbusFuncCode> list = null;
  private static IIntMap<EModbusFuncCode>             map  = null;

  private final String id;
  private final int    code;
  private final String name;
  private final String description;

  EModbusFuncCode( String aId, int aCode, String aName, String aDescription ) {
    id = aId;
    code = aCode;
    name = aName;
    description = aDescription;
  }

  // --------------------------------------------------------------------------
  // IStridable
  //

  @Override
  public String id() {
    return id;
  }

  @Override
  public String nmName() {
    return name;
  }

  @Override
  public String description() {
    return description;
  }

  // --------------------------------------------------------------------------
  // API
  //

  /**
   * Returns the function code.
   *
   * @return int - the function code
   */
  public int code() {
    return code;
  }

  /**
   * Returns all constants as a map.
   * <p>
   * Map keys (function codes) are sorted in increasing order.
   *
   * @return {@link IIntMap}&lt;{@link EModbusFuncCode}&gt; - sorted map "register no" - "register configuration"
   */
  public static IIntMap<EModbusFuncCode> asMap() {
    if( map == null ) {
      IIntMapEdit<EModbusFuncCode> mm = new SortedIntMap<>();
      for( EModbusFuncCode fc : values() ) {
        mm.put( fc.code, fc );
      }
      map = mm;
    }
    return map;
  }

  // ----------------------------------------------------------------------------------
  // Stridable enum common API
  //

  /**
   * Returns all constants in single list.
   *
   * @return {@link IStridablesList}&lt; {@link EModbusFuncCode} &gt; - list of constants in order of declaraion
   */
  public static IStridablesList<EModbusFuncCode> asList() {
    if( list == null ) {
      list = new StridablesList<>( values() );
    }
    return list;
  }

  /**
   * Returns the constant by the ID.
   *
   * @param aId String - the ID
   * @return {@link EModbusFuncCode} - found constant
   * @throws TsNullArgumentRtException any argument = <code>null</code>
   * @throws TsItemNotFoundRtException no constant found by specified ID
   */
  public static EModbusFuncCode getById( String aId ) {
    return asList().getByKey( aId );
  }

  /**
   * Finds the constant by the name.
   *
   * @param aName String - the name
   * @return {@link EModbusFuncCode} - found constant or <code>null</code>
   * @throws TsNullArgumentRtException any argument = <code>null</code>
   */
  public static EModbusFuncCode findByName( String aName ) {
    TsNullArgumentRtException.checkNull( aName );
    for( EModbusFuncCode item : values() ) {
      if( item.name.equals( aName ) ) {
        return item;
      }
    }
    return null;
  }

  /**
   * Returns the constant by the name.
   *
   * @param aName String - the name
   * @return {@link EModbusFuncCode} - found constant
   * @throws TsNullArgumentRtException any argument = <code>null</code>
   * @throws TsItemNotFoundRtException no constant found by specified name
   */
  public static EModbusFuncCode getByName( String aName ) {
    return TsItemNotFoundRtException.checkNull( findByName( aName ) );
  }

}
