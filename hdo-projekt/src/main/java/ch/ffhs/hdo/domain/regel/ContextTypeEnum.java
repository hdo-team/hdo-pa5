package ch.ffhs.hdo.domain.regel;

import java.util.ResourceBundle;

/**
 * Enum für Regel-Kontext
 * 
 * 
 * @author Daniel Crazzolara
 */

public enum ContextTypeEnum {
	EMPTY, CONTEXT_PDF, CONTEXT_FILE;
	
	private static final String I18N = "hdo.regelset";
	private static final String CONTEXT_COMBOBOXKEY = I18N + ".combobox.";
	
	private String I18NValue; 
	
	ContextTypeEnum() {
		
		final ResourceBundle bundle = ResourceBundle.getBundle("ch/ffhs/hdo/client/ui/resourceBundle");

		this.I18NValue = bundle.getString(CONTEXT_COMBOBOXKEY + this.name().toLowerCase());
	}
	
	@Override
	public String toString() {
		return this.I18NValue;
	}
}