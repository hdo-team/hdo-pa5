package ch.ffhs.hdo.client.ui.einstellungen;

import ch.ffhs.hdo.client.ui.base.Model;
import ch.ffhs.hdo.client.ui.base.ParamChecker;
import ch.ffhs.hdo.client.ui.hauptfenster.FolderTreeModel;
import ch.ffhs.hdo.client.ui.utils.IFileModel;

/**
 * Model fuer das Konfigurations Fenster.
 * 
 * @author Denis Bittante
 *
 */
public class OptionModel extends Model implements IFileModel {

	private String inboxPath;
	private boolean autoModus = false;
	private int intervall = -1;
	private FolderTreeModel folderModel;

	/**
	 * Getter Inbox Verzeichnis
	 * 
	 * @return Inbox Pfad
	 */
	public String getInboxPath() {
		return inboxPath;
	}

	/**
	 * Setter Inbox Verzeichnis
	 * 
	 * @param inboxPath
	 */
	public void setInboxPath(String inboxPath) {
		// ParamChecker.notEmpty(inboxPath, "inboxPath");
		String oldValue = this.inboxPath;
		this.inboxPath = inboxPath;
		firePropertyChange("inboxPath", oldValue, inboxPath);

	}

	/**
	 * Ob AutoModus angeschaltet ist. <br>
	 * Wenn AutoModus angeschaltet ist dann kann bleibt der Service aktiv und
	 * wartet die gesetzte zeit bis das Inbox Verzeichnis erneute verarbeitet
	 * wird.
	 * 
	 * @return AutoModus
	 */
	public boolean isAutoModus() {

		return autoModus;
	}

	/**
	 * Setter AutoModus
	 * 
	 * @param autoModus
	 */
	public void setAutoModus(boolean autoModus) {

		boolean oldValue = this.autoModus;
		this.autoModus = autoModus;
		firePropertyChange("autoModus", oldValue, autoModus);

	}

	/**
	 * Getter Intervall das der Service zu warten hat bis er erneut starten
	 * kann.
	 * 
	 * @return Intervall in Sekunden
	 */
	public int getIntervall() {
		return intervall;
	}

	/**
	 * Setter Invervall in Sekunden
	 * 
	 * @param intervall
	 */
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

	/**
	 * Setter FolderModel
	 * 
	 * @param folderModel
	 *            see {@link FolderTreeModel}
	 */
	public void setFolderModel(FolderTreeModel folderModel) {
		this.folderModel = folderModel;
	}

	/**
	 * Getter FolderModel
	 * 
	 * @return see {@link FolderTreeModel}
	 */
	public FolderTreeModel getFolderModel() {
		return folderModel;
	}

}
