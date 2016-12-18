package ch.ffhs.hdo.domain.regel;

/**
 * Enumeration fuer den Datentyp
 * 
 * 
 * @author Daniel Crazzolara
 */

public enum DataTypeEnum {
	DATE(ComparisonTypeEnum.COMPARISON_EQUAL, ComparisonTypeEnum.COMPARISON_GREATER_EQUAL, 
		 ComparisonTypeEnum.COMPARISON_LESS_EQUAL, ComparisonTypeEnum.COMPARISON_UNEQUAL),
	INT(ComparisonTypeEnum.COMPARISON_EQUAL,
		ComparisonTypeEnum.COMPARISON_GREATER_EQUAL, ComparisonTypeEnum.COMPARISON_LESS_EQUAL,
		 ComparisonTypeEnum.COMPARISON_UNEQUAL), 
	NULL, 
	STRING(ComparisonTypeEnum.COMPARISON_EQUAL, ComparisonTypeEnum.COMPARISON_UNEQUAL,
		   ComparisonTypeEnum.COMPARISON_REGEX),
	CONTENT_STRING( ComparisonTypeEnum.COMPARISON_REGEX);

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
	 * @return comparisontype
	 * 	           Vergleichsart-Enums fuer den Datentyp dieser Enum
	 */
	public ComparisonTypeEnum[] getComparisontype() {
		return comparisontype;
	}
}