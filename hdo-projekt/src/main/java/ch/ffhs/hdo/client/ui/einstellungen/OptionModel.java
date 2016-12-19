package ch.ffhs.hdo.client.ui.einstellungen;

import ch.ffhs.hdo.client.ui.base.Model;
import ch.ffhs.hdo.client.ui.base.ParamChecker;
import ch.ffhs.hdo.client.ui.hauptfenster.FolderTreeModel;
import ch.ffhs.hdo.client.ui.utils.IFileModel;

/**
 * Model fuer das Konfigurations Fenster.
 * 
 * @author Jonas Segessemann
 *
 */
public class OptionModel extends Model implements IFileModel {

	private String inboxPath;
	private boolean autoModus = false;
	private int intervall = -1;
	private FolderTreeModel folderModel;

	public String getInboxPath() {
		return inboxPath;
	}

	public void setInboxPath(String inboxPath) {
	//	ParamChecker.notEmpty(inboxPath, "inboxPath");
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

	public String getFilePath() {
		return getInboxPath();
	}

	public void setFilePath(String newValue) {
		setInboxPath(newValue);
	}

	public void setFolderModel(FolderTreeModel folderModel) {
		this.folderModel = folderModel;
	}

	public FolderTreeModel getFolderModel() {
		return folderModel;
	}

}
