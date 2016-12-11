package ch.ffhs.hdo.client.ui.hauptfenster;

import ch.ffhs.hdo.client.ui.base.Model;

public class FolderModel extends Model {
	private String inboxPath;
	private boolean updateView = false;

	public FolderModel(String inboxPath) {
		this.inboxPath = inboxPath;
	}

	public String getInboxPath() {
		return inboxPath;
	}
	
	public void setInboxPath(String inboxPath) {
		this.inboxPath = inboxPath;
	}

	public void setUpdateView(boolean updateView) {

		boolean oldValue = this.updateView;
		this.updateView = updateView;
		firePropertyChange("updateView", oldValue, updateView);

	}

	public boolean getUpdateView() {

		return updateView;
	}
}
