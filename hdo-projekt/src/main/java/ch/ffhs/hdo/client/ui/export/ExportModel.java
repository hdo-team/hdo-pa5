package ch.ffhs.hdo.client.ui.export;

import ch.ffhs.hdo.client.ui.base.Model;

public class ExportModel extends Model {

	private String targetPath;
	
	public String getTargetPath() {
		return targetPath;
	}

	public void setTargetPath(String targetPath) {

		String oldValue = this.targetPath;
		this.targetPath = targetPath;
		firePropertyChange("targetPath", oldValue, targetPath);

	}

}
