package ch.ffhs.hdo.domain.regel;

import static ch.ffhs.hdo.domain.regel.ComparisonTypeEnum.*;

public enum DataTypeEnum {
	DATE(COMPARISON_EQUAL, COMPARISON_GREATER_EQUAL, COMPARISON_LESS_EQUAL, COMPARISON_UNEQUAL), 
	INT(COMPARISON_EQUAL,COMPARISON_GREATER_EQUAL, COMPARISON_LESS_EQUAL, COMPARISON_UNEQUAL), 
	NULL, 
	STRING(COMPARISON_EQUAL, COMPARISON_UNEQUAL, COMPARISON_REGEX);

	private ComparisonTypeEnum[] comparisontype;

	private DataTypeEnum(ComparisonTypeEnum... comparisonTypeEnums) {
		this.comparisontype = comparisonTypeEnums;

	}

	public ComparisonTypeEnum[] getComparisontype() {
		return comparisontype;
	}
}