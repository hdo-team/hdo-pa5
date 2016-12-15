package ch.ffhs.hdo.domain.regel;

/**
 * Enum für Regel-Kontext
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

	private DataTypeEnum(ComparisonTypeEnum... comparisonTypeEnums) {
		this.comparisontype = comparisonTypeEnums;
	}

	public ComparisonTypeEnum[] getComparisontype() {
		return comparisontype;
	}
}