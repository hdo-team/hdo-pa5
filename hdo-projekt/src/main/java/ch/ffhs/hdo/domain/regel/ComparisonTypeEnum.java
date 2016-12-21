package ch.ffhs.hdo.domain.regel;

import java.util.ResourceBundle;

/**
 * Enumeration fuer die Vergleichsart
 * 
 * @author Daniel Crazzolara
 * 
 *         Mögliche Vergleichsarten sind:
 * 
 *         {@link ComparisonTypeEnum#EMPTY}<br>
 *         {@link ComparisonTypeEnum#COMPARISON_EQUAL}<br>
 *         {@link ComparisonTypeEnum#COMPARISON_GREATER_EQUAL}<br>
 *         {@link ComparisonTypeEnum#COMPARISON_LESS_EQUAL}<br>
 *         {@link ComparisonTypeEnum#COMPARISON_REGEX}<br>
 *         {@link ComparisonTypeEnum#COMPARISON_UNEQUAL}<br>
 * 
 */

public enum ComparisonTypeEnum {
	/**
	 * Nur für Combobox
	 */
	EMPTY,
	/**
	 * Gleich =
	 */
	COMPARISON_EQUAL,
	/**
	 * Nicht gleich !=
	 */
	COMPARISON_UNEQUAL,
	/**
	 * Kleinergleich
	 */
	COMPARISON_LESS_EQUAL,
	/**
	 * Grössergleich
	 */
	COMPARISON_GREATER_EQUAL,
	/**
	 * Regex vergleich. Sucht in einem String ob der Regex gefunden werden kann
	 * nicht ob der ganze String matched
	 */
	COMPARISON_REGEX;

	private static final String I18N = "hdo.regelset";
	private static final String ATTRIBUTE_COMBOBOXKEY = I18N + ".combobox.comparison.";

	private String I18NValue;

	/**
	 * Konstruktor zur Erstellung einer Vergleichsart-Enum (ermittelt auch
	 * gleich die Auspraegung in der gewuenschten Sprache)
	 */
	ComparisonTypeEnum() {

		final ResourceBundle bundle = ResourceBundle.getBundle("ch/ffhs/hdo/client/ui/resourceBundle");

		this.I18NValue = bundle.getString(ATTRIBUTE_COMBOBOXKEY + this.name().toLowerCase());
	}

	/**
	 * liefert die Auspraegung einer Enums
	 * 
	 * @return I18NValue Auspraegung in der gewuenschten Sprache
	 */
	@Override
	public String toString() {
		return this.I18NValue;
	}

}