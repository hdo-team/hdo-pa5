package ch.ffhs.hdo.client.ui.hauptfenster.executable;

import java.io.File;

import ch.ffhs.hdo.client.ui.base.viewhandler.Executable;
import ch.ffhs.hdo.client.ui.hauptfenster.FolderTreeModel;

/**
 * Aktualisiert die Verzeichnisübersicht mit dem neuen Model.
 * 
 * @author Jonas Segessemann
 *
 */
public class FolderTreeUpdateOperationExecutable implements Executable<Object> {

	private FolderTreeModel model;

	/**
	 * Konstruktor zum erstellen des Objekts.
	 * 
	 * @param model
	 *            Folder Tree Model mit dem Inbox Pfad.
	 */
	public FolderTreeUpdateOperationExecutable(FolderTreeModel model) {
		this.model = model;
	}

	/**
	 * Setzt einen neuen Inboxpfad und aktualisiert dadurch das View.
	 * 
	 * @param inboxPath
	 *            neuer Inbox Pfad.
	 */
	public void execute(Object inboxPath) {
		model.setInboxPath(new File((String) inboxPath));
	}
}