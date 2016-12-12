package ch.ffhs.hdo.client.ui.hauptfenster;

import ch.ffhs.hdo.client.ui.base.Model;

public class MainModel extends Model {

	private FolderTreeModel folderModel;
	private RegelsetTableModel regelsetModel;

	public void setFolderModel(FolderTreeModel folderModel) {
		this.folderModel = folderModel;
	}

	public FolderTreeModel getFolderModel() {
		return folderModel;
	}

	public RegelsetTableModel getRegelsetModel() {
		return regelsetModel;
	}

	public void setRegelsetModel(RegelsetTableModel regelsetTableModel) {
		this.regelsetModel = regelsetTableModel;
	}

}
