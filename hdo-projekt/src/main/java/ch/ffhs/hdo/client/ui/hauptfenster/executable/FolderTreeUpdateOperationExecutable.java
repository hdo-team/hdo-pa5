package ch.ffhs.hdo.client.ui.hauptfenster.executable;

import ch.ffhs.hdo.client.ui.base.viewhandler.Executable;
import ch.ffhs.hdo.client.ui.hauptfenster.FolderModel;

public class FolderTreeUpdateOperationExecutable implements Executable {

	private FolderModel model;

	public FolderTreeUpdateOperationExecutable(FolderModel model) {
		this.model=model;
	}

	public void execute(Object inboxPath) {
		model.setInboxPath((String) inboxPath);
	}
}