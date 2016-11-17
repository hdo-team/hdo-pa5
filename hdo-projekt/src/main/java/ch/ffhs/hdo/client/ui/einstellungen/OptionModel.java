package ch.ffhs.hdo.client.ui.einstellungen;

import ch.ffhs.hdo.client.ui.base.Model;

public class OptionModel extends Model {

	private String inboxPath;
	private boolean autoModus = false;
	private int intervall = -1;

	public String getInboxPath() {
		return inboxPath;
	}

	public void setInboxPath(String inboxPath) {

		String oldValue = this.inboxPath;
		this.inboxPath = inboxPath;
		firePropertyChange("inboxPath", oldValue, inboxPath);

	}

	public boolean isAutoModus() {

		return autoModus;
	}

	public void setAutoModus(boolean autoModus) {

		boolean oldValue = this.autoModus;
		this.autoModus = autoModus;
		firePropertyChange("autoModus", oldValue, autoModus);

	}

	public int getIntervall() {
		return intervall;
	}

	public void setIntervall(int intervall) {

		int oldValue = this.intervall;
		this.intervall = intervall;
		firePropertyChange("intervall", oldValue, intervall);

	}

}
