package ch.ffhs.hdo.client.ui.imports;

import ch.ffhs.hdo.client.ui.base.Model;
import ch.ffhs.hdo.client.ui.hauptfenster.RegelsetTableModel;
import ch.ffhs.hdo.client.ui.utils.IFileModel;

/**
 * Model fuer das Konfigurations-Import Fenster.
 * 
 * @author Adrian Perez Rodriguez
 * 
 */
public class ImportModel extends Model implements IFileModel {

	private String filePath;
	private RegelsetTableModel regelsetModel;

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String newValue) {

		String oldValue = filePath;
		this.filePath = newValue;
		firePropertyChange("filePath", oldValue, filePath);
	}

	/**
	 * Setter RegelsetTablemodel
	 * 
	 * @param regelsetModel
	 *            see {@link RegelsetTableModel}
	 */
	public void setRegelsetTableModel(RegelsetTableModel regelsetModel) {
		this.regelsetModel = regelsetModel;
	}

	/**
	 * Getter RegelsetModel
	 * 
	 * @return see {@link RegelsetTableModel}
	 */

	public RegelsetTableModel getRegelsetModel() {
		return regelsetModel;
	}

}