package ch.ffhs.hdo.infrastructure;

public class ApplicationSettings {

	String inbox_path;

	private static ApplicationSettings instance = null;

	protected ApplicationSettings() {
		// Exists only to defeat instantiation.
	}

	public static ApplicationSettings getInstance() {
		if (instance == null) {
			instance = new ApplicationSettings();
		}
		return instance;
	}

	public void setInbox_path(String inbox_path) {
		this.inbox_path = inbox_path;
	}

	public String getInbox_path() {
		return inbox_path;
	}

}
