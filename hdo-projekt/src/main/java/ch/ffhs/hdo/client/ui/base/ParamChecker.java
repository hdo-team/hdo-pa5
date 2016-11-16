package ch.ffhs.hdo.client.ui.base;

import java.util.Collection;
import java.util.List;

public final class ParamChecker {
	private static final String DEFAULT_PARAM_NAME = "value";

	private ParamChecker() {
		throw new UnsupportedOperationException();
	}

	/**
	 * Ensures that a single parameter is not <code>null</code>.
	 * 
	 * @param value
	 *            checked to be not null
	 * @param parameterName
	 *            name of the parameter, used for exception message if value is
	 *            <code>null</code>
	 * 
	 * @throws IllegalArgumentException
	 *             if <code>value</code> is <code>null</code>
	 */
	public static void notNull(Object value, String parameterName) {
		if (value == null) {
			throw new IllegalArgumentException(createNullErrorMessage(parameterName));
		}
	}

	/**
	 * Ensures that a single field is not <code>null</code>.
	 * 
	 * @param value
	 *            checked to be not null
	 * @param fieldName
	 *            name of the field, used for exception message if value is
	 *            <code>null</code>
	 * 
	 * @throws IllegalStateException
	 *             if <code>value</code> is <code>null</code>
	 */
	public static void notNullState(Object value, String fieldName) {
		if (value == null) {
			throw new IllegalStateException(createNullErrorMessage(fieldName));
		}
	}

	/**
	 * Ensures that a given string parameter is not empty. If the given string
	 * is <code>null</code> it will be considered empty.
	 * 
	 * @param value
	 *            checked to be not empty
	 * @param parameterName
	 *            name of the parameter, used for exception message if value is
	 *            empty
	 * 
	 * @throws IllegalArgumentException
	 *             if <code>value</code> is <code>empty</code>
	 */
	public static void notEmpty(String value, String parameterName) {
		notNull(value, parameterName);

		if (value.isEmpty()) {
			throw new IllegalArgumentException(createEmptinessErrorMessage(parameterName));
		}
	}

	/**
	 * Ensures that a given collection parameter is not empty. If the given
	 * collection is <code>null</code> it will be considered empty.
	 * 
	 * @param value
	 *            checked to be not empty
	 * @param parameterName
	 *            name of the parameter, used for exception message if value is
	 *            empty
	 * 
	 * @throws IllegalArgumentException
	 *             if <code>value</code> is <code>empty</code>
	 */
	public static void notEmpty(Collection<?> value, String parameterName) {
		notNull(value, parameterName);

		if (value.isEmpty()) {
			throw new IllegalArgumentException(createEmptinessErrorMessage(parameterName));
		}
	}

	/**
	 * Ensures that a given string field is not empty. If the given string is
	 * <code>null</code> it will be considered empty.
	 * 
	 * @param value
	 *            checked to be not empty
	 * @param fieldName
	 *            name of the field, used for exception message if value is
	 *            empty
	 * 
	 * @throws IllegalArgumentException
	 *             if <code>value</code> is <code>empty</code>
	 */
	public static void notEmptyState(String value, String fieldName) {
		notNullState(value, fieldName);

		if (value.isEmpty()) {
			throw new IllegalStateException(createEmptinessErrorMessage(fieldName));
		}
	}

	/**
	 * Ensures that a given array parameter is not empty. If the given array is
	 * <code>null</code> it will be considered empty.
	 * 
	 * @param value
	 *            checked to be not empty
	 * @param parameterName
	 *            name of the parameter, used for exception message if value is
	 *            empty
	 * 
	 * @throws IllegalArgumentException
	 *             if <code>value</code> is <code>empty</code>
	 */
	public static void notEmpty(Object[] value, String parameterName) {
		notNull(value, parameterName);

		if (value.length == 0) {
			throw new IllegalArgumentException(createEmptinessErrorMessage(parameterName));
		}
	}

	/**
	 * Ensures that a given index refers to an existing element within a given
	 * array.
	 * 
	 * @param index
	 *            the element index to check
	 * @param array
	 *            the array to which the index applies
	 * @param parameterName
	 *            name of the parameter, used for exception message if value
	 *            index is out of bounds
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>array</code> is <code>null</code> or if
	 *             <code>index</code> is outside the bounds of the array
	 */
	public static void validArrayIndex(int index, Object[] array, String parameterName) {
		notNull(array, parameterName);

		if ((index < 0) || (index >= array.length)) {
			throw new IllegalArgumentException(createBoundsErrorMessage(parameterName));
		}
	}

	/**
	 * Ensures that a given index refers to an existing element within a given
	 * list.
	 * 
	 * @param index
	 *            the element index to check
	 * @param list
	 *            the list to which the index applies
	 * @param parameterName
	 *            name of the parameter, used for exception message if value
	 *            index is out of bounds
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>list</code> is <code>null</code> or if
	 *             <code>index</code> is outside the bounds of the list
	 */
	public static void validListIndex(int index, List<?> list, String parameterName) {
		notNull(list, parameterName);

		if ((index < 0) || (index >= list.size())) {
			throw new IllegalArgumentException(createBoundsErrorMessage(parameterName));
		}
	}

	/**
	 * Ensures that a given value is a positive number.
	 * 
	 * @param value
	 *            the value to check
	 * @param parameterName
	 *            name of the parameter, used for exception message if value
	 *            index is out of bounds
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>value</code> is not zero or negative
	 */
	public static void positive(int value, String parameterName) {
		if (value <= 0) {
			throw new IllegalArgumentException(createBoundsErrorMessage(parameterName));
		}
	}

	/**
	 * Ensures that a given value is a positive number.
	 * 
	 * @param value
	 *            the value to check
	 * @param parameterName
	 *            name of the parameter, used for exception message if value
	 *            index is out of bounds
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>value</code> is not zero or negative
	 */
	public static void positive(long value, String parameterName) {
		if (value <= 0) {
			throw new IllegalArgumentException(createBoundsErrorMessage(parameterName));
		}
	}

	/**
	 * Ensures that a given value is not a negative number.
	 * 
	 * @param value
	 *            the value to check
	 * @param parameterName
	 *            name of the parameter, used for exception message if value
	 *            index is out of bounds
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>value</code> is negative
	 */
	public static void notNegative(int value, String parameterName) {
		if (value < 0) {
			throw new IllegalArgumentException(createBoundsErrorMessage(parameterName));
		}
	}

	/**
	 * Ensures that a given value is of a specific type or subtype.
	 * <code>null</code> is considered valid since it can be assigned to any
	 * variable of type <code>type</code>.
	 * 
	 * @param type
	 *            The expected type
	 * @param value
	 *            the value to check
	 * @param parameterName
	 *            name of the parameter, used for exception message if value
	 *            index is out of bounds
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>value</code> is not an instance of <code>type</code>
	 */
	public static void isA(Class<?> type, Object value, String parameterName) {
		if (type != null) {
			if ((value != null) && !type.isInstance(value)) {
				throw new IllegalArgumentException(createTypeErrorMessage(parameterName, type));
			}
		} else {
			if (value != null) {
				throw new IllegalArgumentException(createNullTypeErrorMessage(parameterName));
			}
		}
	}

	private static String createNullErrorMessage(String parameterName) {
		return ((parameterName == null) ? DEFAULT_PARAM_NAME : parameterName) + " cannot be null";
	}

	private static String createEmptinessErrorMessage(String parameterName) {
		return ((parameterName == null) ? DEFAULT_PARAM_NAME : parameterName) + " cannot be empty";
	}

	private static String createBoundsErrorMessage(String parameterName) {
		return ((parameterName == null) ? DEFAULT_PARAM_NAME : parameterName) + " is out of bounds";
	}

	private static String createTypeErrorMessage(String parameterName, Class<?> type) {
		return ((parameterName == null) ? "value" : parameterName) + " is not of type " + type.getCanonicalName();
	}

	private static String createNullTypeErrorMessage(String parameterName) {
		return ((parameterName == null) ? "value" : parameterName) + " is not of type null";
	}
}
