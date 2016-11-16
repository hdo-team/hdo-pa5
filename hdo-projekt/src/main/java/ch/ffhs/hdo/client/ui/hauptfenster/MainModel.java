package ch.ffhs.hdo.client.ui.hauptfenster;

import ch.ffhs.hdo.client.ui.base.Model;

public class MainModel extends Model {

	FolderModel folderModel;
	RulsetModel rulsetModel;
	
	public void setFolderModel(FolderModel folderModel) {
		this.folderModel = folderModel;
	}
	
	public FolderModel getFolderModel() {
		return folderModel;
	}
	
	public void setRulsetModel(RulsetModel rulsetModel) {
		this.rulsetModel = rulsetModel;
	}
	
	public RulsetModel getRulsetModel() {
		return rulsetModel;
	}
}
