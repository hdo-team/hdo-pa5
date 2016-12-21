package ch.ffhs.hdo.client.ui.hauptfenster;

import ch.ffhs.hdo.client.ui.base.Model;

/**
 * Model fuer das Hauptfenster.
 * 
 * @author Jonas Segessemann
 *
 */
public class MainModel extends Model {

	private FolderTreeModel folderModel;
	private RegelsetTableModel regelsetModel;

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

	/**
	 * Getter RegelsetModel
	 * 
	 * @return see {@link RegelsetTableModel}
	 */
	public RegelsetTableModel getRegelsetModel() {
		return regelsetModel;
	}

	/**
	 * Setter RegelsetModel
	 * 
	 * @param regelsetTableModel
	 *            see {@link RegelsetTableModel}
	 */
	public void setRegelsetModel(RegelsetTableModel regelsetTableModel) {
		this.regelsetModel = regelsetTableModel;
	}

}
