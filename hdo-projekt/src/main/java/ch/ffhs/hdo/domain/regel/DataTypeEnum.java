package ch.ffhs.hdo.domain.regel;

/**
 * Enumeration fuer den Datentyp. Hier werden Vergleichsarten zu den jeweiligen
 * Datumstypen definiert.
 * 
 * 
 * @author Daniel Crazzolara Möglich sind :<br>
 *         {@link DataTypeEnum#DATE}<br>
 *         {@link DataTypeEnum#INT}<br>
 *         {@link DataTypeEnum#NULL}<br>
 *         {@link DataTypeEnum#STRING} <br>
 *         {@link DataTypeEnum#CONTENT_STRING}
 * 
 */

public enum DataTypeEnum {
	/**
	 * Datum kann mittels : =, >=, <=,!= verglichen werden
	 */
	DATE(ComparisonTypeEnum.COMPARISON_EQUAL, ComparisonTypeEnum.COMPARISON_GREATER_EQUAL, ComparisonTypeEnum.COMPARISON_LESS_EQUAL, ComparisonTypeEnum.COMPARISON_UNEQUAL),
	/**
	 * Integer kann mittels : =, >=, <=,!= verglichen werden
	 * 
	 */
	INT(ComparisonTypeEnum.COMPARISON_EQUAL, ComparisonTypeEnum.COMPARISON_GREATER_EQUAL, ComparisonTypeEnum.COMPARISON_LESS_EQUAL, ComparisonTypeEnum.COMPARISON_UNEQUAL),
	/**
	 * NULL hat keine Vergleichsarten
	 */
	NULL,
	/**
	 * String kann mittels : =, != und Regex verglichen werden
	 * 
	 */
	STRING(ComparisonTypeEnum.COMPARISON_EQUAL, ComparisonTypeEnum.COMPARISON_UNEQUAL, ComparisonTypeEnum.COMPARISON_REGEX),
	/**
	 * Ein Inhalt kann mittels : =, != und Regex verglichen werden
	 * 
	 */
	CONTENT_STRING(ComparisonTypeEnum.COMPARISON_REGEX);

	private ComparisonTypeEnum[] comparisontype;

	/**
	 * Konstruktor zur Erstellung einer Datentyp-Enum
	 * 
	 * @param compariosonTypeEnums
	 *            Vergleichsoperationen die fuer diese Enum zugelassen sind
	 * 
	 */
	private DataTypeEnum(ComparisonTypeEnum... comparisonTypeEnums) {
		this.comparisontype = comparisonTypeEnums;
	}

	/**
	 * Liefert die Vergleichs-Enums die zu einen spezifischen Datatyp gehoeren
	 * 
	 * @return comparisontype Vergleichsart-Enums fuer den Datentyp dieser Enum
	 */
	public ComparisonTypeEnum[] getComparisontype() {
		return comparisontype;
	}
}