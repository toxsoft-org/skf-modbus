package org.toxsoft.skf.modbus.lib.cfg.device.conv;

import static org.toxsoft.skf.modbus.lib.l10n.ISkfModbusLibSharedResources.*;

import org.toxsoft.core.tslib.av.*;
import org.toxsoft.core.tslib.bricks.keeper.*;
import org.toxsoft.core.tslib.bricks.keeper.std.*;
import org.toxsoft.core.tslib.bricks.strid.*;
import org.toxsoft.core.tslib.bricks.strid.coll.*;
import org.toxsoft.core.tslib.bricks.strid.coll.impl.*;
import org.toxsoft.core.tslib.utils.errors.*;
import org.toxsoft.skf.modbus.lib.mbspec.*;

/**
 * The types of converter of logical register raw words to {@link IAtomicValue}.
 *
 * @author jhazard157
 */
public enum EMbConverterType
    implements IStridable {

  /**
   * Single register or bit will be converted to a boolean where 0 is <code>false</code>, everything else -
   * <code>true</code>.
   * <p>
   * Together with {@link #CONV_INTEGER} this is mostly used conversion.
   * <p>
   * Important note: this conversion applies also to the raw words of functions {@link EModbusFuncCode#FC_01_READ_COILS}
   * and {@link EModbusFuncCode#FC_15_WRITE_COILS} where value of each register is packed into the single bit of a raw
   * words so each word contains values of up to 16 COIL registers.
   */
  CONV_BOOL( "bool", STR_EMCT_CONV_BOOL, STR_EMCT_CONV_BOOL_D, MbConvBool.FACTORY ), //$NON-NLS-1$

  /**
   * 1..4 consecutive physical registers are simply interpreted as an up to 64-bit (8-byte) length integer number.
   * <p>
   * No additional options because {@link EAtomicType#INTEGER} will exactly, with bit-to-bit accuracy represent content
   * or the physical registers.
   * <p>
   * Together with {@link #CONV_BOOL} this is mostly used conversion allowing application to interpret MOSBUD data.
   */
  CONV_INTEGER( "integer", STR_EMCT_CONV_INTEGER, STR_EMCT_CONV_INTEGER_D, MbConvInteger.FACTORY ), //$NON-NLS-1$

  /**
   * 2 or 4 registers contains IEEE 754 single or double-precision floating point value.
   * <p>
   * Has tuning options: <b>k</b> and <b>b</b> coefficients for <code>y = k*x+b</code> conversion.
   */
  CONV_FLOAT( "float", STR_EMCT_CONV_FLOAT, STR_EMCT_CONV_FLOAT_D, MbConvFloat.FACTORY ), //$NON-NLS-1$

  /**
   * Consecutive registers contains characters making the string.
   * <p>
   * TODO possible options:
   * <ul>
   * <li>encoding (like ASCII or UTF-8 or whatever);</li>
   * <li>NULL terminated?;</li>
   * <li>first 1 or 2 bytes encodes string length (actually, 8-bit characters count).</li>
   * </ul>
   */
  CONV_STRING( "string", STR_EMCT_CONV_STRING, STR_EMCT_CONV_STRING_D, MbConvString.FACTORY ); //$NON-NLS-1$

  /**
   * The registered keeper ID.
   */
  public static final String KEEPER_ID = "EMbConverterType"; //$NON-NLS-1$

  /**
   * The keeper singleton.
   */
  public static final IEntityKeeper<EMbConverterType> KEEPER = new StridableEnumKeeper<>( EMbConverterType.class );

  private static IStridablesListEdit<EMbConverterType> list = null;

  private final String              id;
  private final String              name;
  private final String              description;
  private final IMbConverterFactory factory;

  EMbConverterType( String aId, String aName, String aDescription, IMbConverterFactory aFactory ) {
    id = aId;
    name = aName;
    description = aDescription;
    factory = aFactory;
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

  // ------------------------------------------------------------------------------------
  // API
  //

  /**
   * Returns the factory for converter creation.
   *
   * @return {@link IMbConverterFactory} - converter factory and descriptive information
   */
  public IMbConverterFactory factory() {
    return factory;
  }

  // ----------------------------------------------------------------------------------
  // Stridable enum common API
  //

  /**
   * Returns all constants in single list.
   *
   * @return {@link IStridablesList}&lt; {@link EMbConverterType} &gt; - list of constants in order of declaraion
   */
  public static IStridablesList<EMbConverterType> asList() {
    if( list == null ) {
      list = new StridablesList<>( values() );
    }
    return list;
  }

  /**
   * Returns the constant by the ID.
   *
   * @param aId String - the ID
   * @return {@link EMbConverterType} - found constant
   * @throws TsNullArgumentRtException any argument = <code>null</code>
   * @throws TsItemNotFoundRtException no constant found by specified ID
   */
  public static EMbConverterType getById( String aId ) {
    return asList().getByKey( aId );
  }

  /**
   * Finds the constant by the name.
   *
   * @param aName String - the name
   * @return {@link EMbConverterType} - found constant or <code>null</code>
   * @throws TsNullArgumentRtException any argument = <code>null</code>
   */
  public static EMbConverterType findByName( String aName ) {
    TsNullArgumentRtException.checkNull( aName );
    for( EMbConverterType item : values() ) {
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
   * @return {@link EMbConverterType} - found constant
   * @throws TsNullArgumentRtException any argument = <code>null</code>
   * @throws TsItemNotFoundRtException no constant found by specified name
   */
  public static EMbConverterType getByName( String aName ) {
    return TsItemNotFoundRtException.checkNull( findByName( aName ) );
  }

}
