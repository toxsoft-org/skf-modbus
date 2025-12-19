package org.toxsoft.skf.modbus.lib.mbspec;

import static org.toxsoft.skf.modbus.lib.l10n.ISkfModbusLibSharedResources.*;

import org.toxsoft.core.tslib.bricks.keeper.*;
import org.toxsoft.core.tslib.bricks.keeper.std.*;
import org.toxsoft.core.tslib.bricks.strid.*;
import org.toxsoft.core.tslib.bricks.strid.coll.*;
import org.toxsoft.core.tslib.bricks.strid.coll.impl.*;
import org.toxsoft.core.tslib.utils.errors.*;
import org.toxsoft.skf.modbus.lib.cfg.rwkind.*;

/**
 * Kind of the MODBUS registers as specified in the standard.
 * <p>
 * MODBUS protocol official specification <href="https://www.modbus.org/file/secure/modbusprotocolspecification.pdf">
 * (also see file <code>modbusprotocolspecification.pdf</code> in this project) data model lists the available kinds of
 * registers. This enumeration simply repeats the specification. Names of this <code>enum</code> constants are the names
 * from the MODBUS official specification.
 * <p>
 * TODO: brief review of the registers, functions and how non-standard data is read
 *
 * @author AUTHOR_NAME
 */
public enum EModbusRegisterKind
    implements IStridable {

  /**
   * Digital input - one bit 1=ON, 0=OFF.
   * <p>
   * Digital input register may be only read.
   */
  DI( "di", STR_EMRK_DI, STR_EMRK_DI_D, ERwKind.R ), //$NON-NLS-1$

  /**
   * Coil is a model of digital output or internal variable.
   * <p>
   * Coil register may be both written and read.
   */
  COIL( "coil", STR_EMRK_COIL, STR_EMRK_COIL_D, ERwKind.RW ), //$NON-NLS-1$

  /**
   * Input register is 16-bit integer.
   * <p>
   * Input register may be only read.
   */
  IN_REG( "inreg", STR_EMRK_IN_REG, STR_EMRK_IN_REG_D, ERwKind.R ), //$NON-NLS-1$

  /**
   * Holding register is 16-bit integer output or internal variable.
   * <p>
   * Holding register may be both written and read.
   */
  HOLD_REG( "holdreg", STR_EMRK_HOLD_REG, STR_EMRK_HOLD_REG_D, ERwKind.RW ); //$NON-NLS-1$

  /**
   * The registered keeper ID.
   */
  public static final String KEEPER_ID = "EModbusRegisterKind"; //$NON-NLS-1$

  /**
   * The keeper singleton.
   */
  public static final IEntityKeeper<EModbusRegisterKind> KEEPER =
      new StridableEnumKeeper<>( EModbusRegisterKind.class );

  private static IStridablesListEdit<EModbusRegisterKind> list = null;

  private final String  id;
  private final String  name;
  private final String  description;
  private final ERwKind rwKind;

  EModbusRegisterKind( String aId, String aName, String aDescription, ERwKind aRwKind ) {
    id = aId;
    name = aName;
    description = aDescription;
    rwKind = aRwKind;
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
   * Returns the ability to read/write this kind of register.
   *
   * @return {@link ERwKind} - register read/write ability
   */
  public ERwKind rwKind() {
    return rwKind;
  }

  // ----------------------------------------------------------------------------------
  // Stridable enum common API
  //

  /**
   * Returns all constants in single list.
   *
   * @return {@link IStridablesList}&lt; {@link EModbusRegisterKind} &gt; - list of constants in order of declaraion
   */
  public static IStridablesList<EModbusRegisterKind> asList() {
    if( list == null ) {
      list = new StridablesList<>( values() );
    }
    return list;
  }

  /**
   * Returns the constant by the ID.
   *
   * @param aId String - the ID
   * @return {@link EModbusRegisterKind} - found constant
   * @throws TsNullArgumentRtException any argument = <code>null</code>
   * @throws TsItemNotFoundRtException no constant found by specified ID
   */
  public static EModbusRegisterKind getById( String aId ) {
    return asList().getByKey( aId );
  }

  /**
   * Finds the constant by the name.
   *
   * @param aName String - the name
   * @return {@link EModbusRegisterKind} - found constant or <code>null</code>
   * @throws TsNullArgumentRtException any argument = <code>null</code>
   */
  public static EModbusRegisterKind findByName( String aName ) {
    TsNullArgumentRtException.checkNull( aName );
    for( EModbusRegisterKind item : values() ) {
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
   * @return {@link EModbusRegisterKind} - found constant
   * @throws TsNullArgumentRtException any argument = <code>null</code>
   * @throws TsItemNotFoundRtException no constant found by specified name
   */
  public static EModbusRegisterKind getByName( String aName ) {
    return TsItemNotFoundRtException.checkNull( findByName( aName ) );
  }

}
