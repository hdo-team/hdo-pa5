package ch.ffhs.hdo.client.ui.imports;

import ch.ffhs.hdo.client.ui.base.Model;
import ch.ffhs.hdo.client.ui.hauptfenster.RegelsetTableModel;
import ch.ffhs.hdo.client.ui.utils.IFileModel;

/**
 * 
 * @author Adrian Perez Rodriguez
 */
public class ImportModel extends Model implements IFileModel {

	private  String filePath;
	private  RegelsetTableModel regelsetModel;

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String newValue) {

		String oldValue = filePath;
		this.filePath = newValue;
		firePropertyChange("filePath", oldValue, filePath);
	}
	
	public void setRegelsetModel(RegelsetTableModel regelsetModel){
		this.regelsetModel=regelsetModel;
	}
	
	public RegelsetTableModel getRegelsetModel() {
		return regelsetModel;
	}

}