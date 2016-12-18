package ch.ffhs.hdo.domain.regel;

import java.util.ResourceBundle;

/**
 * Enumeration fuer den Regelkontext
 * 
 * 
 * @author Daniel Crazzolara
 */

public enum ContextTypeEnum {
	EMPTY, CONTEXT_PDF, CONTEXT_FILE, CONTENT_FILE;
	
	private static final String I18N = "hdo.regelset";
	private static final String CONTEXT_COMBOBOXKEY = I18N + ".combobox.";
	
	private String I18NValue; 
	
	ContextTypeEnum() {
	}
	
	/**
	 * liefert die Auspraegung eines Enums
	 * 
	 * @return I18NValue
	 *             Auspraegung in der gewuenschten Sprache
	 */
	@Override
	public String toString() {
		final ResourceBundle bundle = ResourceBundle.getBundle("ch/ffhs/hdo/client/ui/resourceBundle");
		this.I18NValue = bundle.getString(CONTEXT_COMBOBOXKEY + this.name().toLowerCase());

		
		return this.I18NValue;
	}
}