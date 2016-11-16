package ch.ffhs.hdo.client.ui.utils;

import ch.ffhs.hdo.client.ui.base.Model;

public class FileModel extends Model {

	protected String filePath; // FRAGE

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String newValue) {
		
		String oldValue = filePath;
		this.filePath = newValue;
		firePropertyChange("filePath", oldValue, filePath);
		
	}

}
