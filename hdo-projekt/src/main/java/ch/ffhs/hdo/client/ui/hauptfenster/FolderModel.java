package ch.ffhs.hdo.client.ui.hauptfenster;

import ch.ffhs.hdo.client.ui.base.Model;

public class FolderModel extends Model {
	private String inboxPath;

	public FolderModel(String inboxPath) {
		this.inboxPath = inboxPath;
	}

	public String getInboxPath() {
		return inboxPath;
	}
}
