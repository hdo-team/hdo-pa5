package ch.ffhs.hdo.persistence.dto;

import java.util.HashMap;

public class RegelsetDto extends HashMap<String, String> {

	private static final long serialVersionUID = 6814842222879287290L;
	
	// DENIS 

	public enum RegelsetValues {
		RULESETID("ruleset_id"), RULESETNAME("ruleset_name"), TARGETDIR("targetdir_path"), FILENAME("file_name")
		, RULESETSTATUS("is_activ"), PRIORITY("priority");

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
