package ch.ffhs.hdo.client.ui.hauptfenster;

import ch.ffhs.hdo.client.ui.base.Model;

public class MainModel extends Model {

	private FolderModel folderModel;
	private RegelsetTableModel regelsetModel;

	public void setFolderModel(FolderModel folderModel) {
		this.folderModel = folderModel;
	}

	public FolderModel getFolderModel() {
		return folderModel;
	}

	public RegelsetTableModel getRegelsetModel() {
		return regelsetModel;
	}

	public void setRegelsetModel(RegelsetTableModel regelsetTableModel) {
		this.regelsetModel = regelsetTableModel;
	}

}
