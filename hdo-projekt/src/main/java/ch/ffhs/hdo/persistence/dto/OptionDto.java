package ch.ffhs.hdo.persistence.dto;

import java.util.HashMap;

public class OptionDto extends HashMap<String, String> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1434028776535025109L;

	public enum OptionValues {
		AUTOMODUS("auto_modus"), INBOXPATH("inbox_path"), INTERVALL("intervall");

		private String keyName;

		OptionValues(String keyName) {
			this.keyName = keyName;
		}

		public String getKeyName() {
			return keyName;
		}
	}

	public String get(OptionValues type) {
		return super.get(type.getKeyName());
	}

	public String put(OptionValues type, String value) {
		return super.put(type.getKeyName(), value);
	}

	public String put(OptionValues type, Boolean value) {
		return super.put(type.getKeyName(), value.toString());
	}

}
