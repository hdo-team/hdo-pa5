package ch.ffhs.hdo.persistence.dto;

import java.util.HashMap;

public class RegelsetDto extends HashMap<String, String> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1434028776535025109L;

	public enum RegelsetValues {
		AUTOMODUS("auto_modus"), INBOXPATH("inbox_path"), INTERVALL("intervall"), LAST_SORTRUN("lastsortRun");

		private String keyName;

		RegelsetValues(String keyName) {
			this.keyName = keyName;
		}

		public String getKeyName() {
			return keyName;
		}
	}

	public String get(RegelsetValues type) {
		return super.get(type.getKeyName());
	}

	public String put(RegelsetValues type, String value) {
		return super.put(type.getKeyName(), value);
	}

	public String put(RegelsetValues type, Boolean value) {
		return super.put(type.getKeyName(), value.toString());
	}

}
