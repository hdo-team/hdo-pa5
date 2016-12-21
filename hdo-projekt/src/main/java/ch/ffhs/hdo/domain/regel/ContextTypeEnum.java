package ch.ffhs.hdo.domain.regel;

import java.util.ResourceBundle;

/**
 * Enumeration fuer den Regelkontext. Ein Kontext ist einen Eingeschaftsbereich
 * einer Regel.
 * 
 * 
 * @author Daniel Crazzolara
 * 
 *         Mögliche Kontexte sind: <br>
 *         {@link ContextTypeEnum#EMPTY}<br>
 *         {@link ContextTypeEnum#CONTEXT_PDF}<br>
 *         {@link ContextTypeEnum#CONTEXT_FILE}<br>
 *         {@link ContextTypeEnum#CONTENT_FILE}<br>
 */

public enum ContextTypeEnum {
	/**
	 * Nur fuer Combobox nötig
	 */
	EMPTY,
	/**
	 * PDF Kontext, damit sind Eigenschaften gemeint die eine PDF besitzt
	 */
	CONTEXT_PDF,
	/**
	 * File Kontext, damit sind Eigenschaften gemeint die ein File besitzt
	 * 
	 */
	CONTEXT_FILE,
	/**
	 * File Inhalt
	 */
	CONTENT_FILE;

	private static final String I18N = "hdo.regelset";
	private static final String CONTEXT_COMBOBOXKEY = I18N + ".combobox.";

	private String I18NValue;

	ContextTypeEnum() {
	}

	/**
	 * liefert die Auspraegung eines Enums
	 * 
	 * @return I18NValue Auspraegung in der gewuenschten Sprache
	 */
	@Override
	public String toString() {
		final ResourceBundle bundle = ResourceBundle.getBundle("ch/ffhs/hdo/client/ui/resourceBundle");
		this.I18NValue = bundle.getString(CONTEXT_COMBOBOXKEY + this.name().toLowerCase());

		return this.I18NValue;
	}
}