/* Pivotal 5 Solutions Inc. - Core Java library for all other Pivotal Java Modules.
 * 
 * Copyright (C) 2011  KASRA RASAEE
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>. 
 */
package com.p5solutions.core.jpa.orm;

import org.springframework.core.convert.ConversionService;

import com.p5solutions.core.jpa.orm.exceptions.TypeConversionException;

/**
 * The Interface ConversionUtility: Interface defining the structure for a
 * conversion utility, which should be able to convert between various types of
 * Sql and Java types.
 * 
 * @author Kasra Rasaee
 * @since 2010-11-05
 * 
 * @see ConversionUtilityImpl
 * @see MapUtility
 * @see Query#newSQLParameterSource(AbstractSQL, ConversionUtility)
 * 
 */
public interface ConversionUtility {

  /**
   * Convert using the ConversionService, basic conversion from one type to
   * another, e.g. String "2012-10-10" to proper Date instance.
   * 
   * @param value
   *          the value
   * @param className
   *          the class name
   * @return the object
   */
  Object convert(Object value, String className);

  /**
   * Convert using the ConversionService, basic conversion from one type to
   * another, e.g. String "2012-10-10" to proper Date instance.
   * 
   * @param value
   *          the value
   * @param className
   *          the class name
   * @return the object
   */
  Object convert(Object value, Class<?> clazz);

  /**
   * Convert a number to a given target type. Supports various target types, All
   * sub-types of {@link Number} including {@link Boolean}, and {@link String}
   * 
   * @param pb
   *          (optional) ParameterBinder used as part of the Query or DML
   *          statement
   * 
   * @param value
   *          the value to convert to the given target type
   * @param targetType
   *          the target type to convert the value to.
   * @return the converted value, if converted.
   * @throws TypeConversionException
   *           the type conversion exception
   */
  Object convertNumber(ParameterBinder pb, Number value, Class<?> targetType) throws TypeConversionException;

  /**
   * Convert a value to a given target type. Supports various target types.
   * 
   * @param pb
   *          (optional) ParameterBinder used as part of the Query or DML
   *          statement
   * 
   * @param value
   *          the value to convert to the given target type
   * @param targetType
   *          the target type to convert the value to.
   * @return the converted value, if converted.
   * @throws TypeConversionException
   *           the type conversion exception
   */
  Object convert(ParameterBinder pb, Object value, Class<?> targetType) throws TypeConversionException;

  /**
   * Convert a value to a given target type. Supports various target types.
   * 
   * @param pb
   *          (optional) ParameterBinder used as part of the Query or DML
   *          statement
   * 
   * @param value
   *          the value to convert to the given target type
   * @param bindingPath
   *          the binding path of the sql paramater
   * @param targetType
   *          the target type to convert the value to.
   * @return the converted value, if converted.
   * @throws TypeConversionException
   *           the type conversion exception
   */
  Object convert(ParameterBinder pb, Object value, String bindingPath, Class<?> targetType) throws TypeConversionException;

  /**
   * Convert an object to the {@link ParameterBinder}'s column sql-type defined
   * by the {@link ParameterBinder#getColumnMetaData()}
   * 
   * @param pb
   *          the binder for a given column within an entity, also identifies
   *          the sql-type
   * 
   * @param value
   *          to convert to the sql-type
   * 
   * @return an instance of the value converted to the appropriate sql-type
   */
  Object convertToSqlType(ParameterBinder pb, Object value);

  /**
   * Checks if a given value is of the same type of class.
   * 
   * @param value
   *          the value
   * @param targetType
   *          the target type
   * @return true, if is same class
   */
  boolean isSameClass(Object value, Class<?> targetType);

  /**
   * Sets the conversion service. NOTE: YOU MUST USE THE
   * {@link ConversionUtilityBeanConfigurator}, since there is a hierarchy of
   * dependencies from the {@link FormattingConversionServiceFactoryBeanImpl}
   * 
   * @param conversionService
   *          the new conversion service
   */
  void setConversionService(ConversionService conversionService);
}