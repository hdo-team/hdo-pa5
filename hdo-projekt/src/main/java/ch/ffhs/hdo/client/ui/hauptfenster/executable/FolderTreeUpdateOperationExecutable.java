package ch.ffhs.hdo.client.ui.hauptfenster.executable;

import java.io.File;

import ch.ffhs.hdo.client.ui.base.viewhandler.Executable;
import ch.ffhs.hdo.client.ui.hauptfenster.FolderTreeModel;

public class FolderTreeUpdateOperationExecutable implements Executable {

	private FolderTreeModel model;

	public FolderTreeUpdateOperationExecutable(FolderTreeModel model) {
		this.model=model;
	}

	public void execute(Object inboxPath) {
		model.setInboxPath(new File((String) inboxPath));
	}
}