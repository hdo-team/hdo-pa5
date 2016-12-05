package ch.ffhs.hdo.client.ui.hauptfenster;

import ch.ffhs.hdo.client.ui.base.Model;

public class MainModel extends Model {

	FolderModel folderModel;
	RegelsetTableModel regelsetModel;
	boolean sortServiceStatus;
	
	
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
		this.regelsetModel=regelsetTableModel;
	}
	
	public void setSortServiceStatus(boolean activ) {
		this.sortServiceStatus=activ;
	}
	
	public boolean getSortServiceStatus() {
		return sortServiceStatus;
	}
}
