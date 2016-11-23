package ch.ffhs.hdo.client.ui.hauptfenster;

import ch.ffhs.hdo.client.ui.base.Model;

public class MainModel extends Model {

	FolderModel folderModel;
	RegelsetsModel rulsetModel;
	
	public void setFolderModel(FolderModel folderModel) {
		this.folderModel = folderModel;
	}
	
	public FolderModel getFolderModel() {
		return folderModel;
	}
	
	public void setRulsetModel(RegelsetsModel rulsetModel) {
		this.rulsetModel = rulsetModel;
	}
	
	public RegelsetsModel getRulsetModel() {
		return rulsetModel;
	}
}
