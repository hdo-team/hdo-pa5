package ch.ffhs.hdo.domain.regel;

import java.util.ResourceBundle;

/**
 * Enumeration fuer die Vergleichsart
 * 
 * @author Daniel Crazzolara
 */

public enum ComparisonTypeEnum {
	EMPTY, COMPARISON_EQUAL, COMPARISON_UNEQUAL, COMPARISON_LESS_EQUAL, COMPARISON_GREATER_EQUAL, COMPARISON_REGEX;
	
	private static final String I18N = "hdo.regelset";
	private static final String ATTRIBUTE_COMBOBOXKEY = I18N + ".combobox.comparison.";
	
	private String I18NValue; 
	
	/** 
	 * Konstruktor zur Erstellung einer Vergleichsart-Enum
	 * (ermittelt auch gleich die Auspraegung in der gewuenschten Sprache)
	 */
	ComparisonTypeEnum() {
		
		final ResourceBundle bundle = ResourceBundle.getBundle("ch/ffhs/hdo/client/ui/resourceBundle");

		this.I18NValue = bundle.getString(ATTRIBUTE_COMBOBOXKEY + this.name().toLowerCase());
	}
	
	/**
	 * liefert die Auspraegung einer Enums
	 * 
	 * @return I18NValue
	 *             Auspraegung in der gewuenschten Sprache
	 */
	@Override
	public String toString() {
		return this.I18NValue;
	}

}