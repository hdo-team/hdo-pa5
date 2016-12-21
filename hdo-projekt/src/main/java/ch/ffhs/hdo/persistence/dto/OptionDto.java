package ch.ffhs.hdo.persistence.dto;

import java.util.HashMap;

/**
 * Data Transfer Object fuer die Option
 * 
 * @author Denis Bittante
 *
 */
public class OptionDto extends HashMap<String, String> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1434028776535025109L;

	/**
	 * Eigenschaften die in der Datenbank persistiert werden
	 * 
	 * @author Denis Bittante
	 *
	 */
	public enum OptionValues {
		/**
		 * Automodus Datenbank index <code>auto_modus</code>
		 */
		AUTOMODUS("auto_modus"),
		/**
		 * 
		 * Wird nicht mehr genutzt
		 */
		INBOXPATH("inbox_path"),
		/**
		 * Intervall in Sekunden
		 */
		INTERVALL("intervall"),
		/**
		 * Letzter Run als Value kann <code>SUCCESS</code> oder
		 * <code>ERROR</code> stehen
		 */
		LAST_SORTRUN("lastsortRun");

		private String keyName;

		OptionValues(String keyName) {
			this.keyName = keyName;
		}

		/**
		 * Liefert die Namen für die Datenbank der jeweiligen Option zurück
		 * 
		 * @return Key
		 */
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
