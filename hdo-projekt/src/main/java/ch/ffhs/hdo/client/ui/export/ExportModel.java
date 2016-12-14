package ch.ffhs.hdo.client.ui.export;

import ch.ffhs.hdo.client.ui.base.Model;
import ch.ffhs.hdo.client.ui.hauptfenster.FolderTreeModel;
import ch.ffhs.hdo.client.ui.utils.IFileModel;

public class ExportModel extends Model implements IFileModel {

	private String filePath;
	private FolderTreeModel folderModel;

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String newValue) {

		String oldValue = filePath;
		this.filePath = newValue;
		firePropertyChange("filePath", oldValue, filePath);

	}
	
	public void setFolderModel(FolderTreeModel folderModel){
		this.folderModel=folderModel;
	}
	
	public FolderTreeModel getFolderModel() {
		return folderModel;
	}

}