package ch.ffhs.hdo.client.ui.regelset;

import java.util.ArrayList;
import java.util.List;

import ch.ffhs.hdo.client.ui.base.Model;
import ch.ffhs.hdo.client.ui.hauptfenster.RegelsetTableModel;

/**
 * Model für das Regelset (inkl. Liste von Regel-Models)
 * 
 * @author Adrian Perez Rodriguez
 *
 */

public class RegelsetModel extends Model {

	private Long filenameCounter;
	private String newFilename;
	private Integer rulesetId;
	private Integer priority;
	private boolean ruleActiv;
	private List<RegelModel> ruleModelList;
	private String rulesetName;
	private String targetDirectory;
	private RegelsetTableModel regelsetTableModel;

	public Long getFilenameCounter() {
		return filenameCounter;
	}

	public String getNewFilename() {
		return newFilename;
	}

	public Integer getRulesetId() {
		return rulesetId;
	}

	public Integer getPriority() {
		return priority;
	}

	public List<RegelModel> getRuleModelList() {
		return ruleModelList;
	}

	public String getRulesetName() {

		return rulesetName;
	}

	public String getTargetDirectory() {

		return targetDirectory;
	}

	public boolean isRuleActiv() {

		return ruleActiv;
	}

	public void setFilenameCounter(Long filenameCounter) {
		Long oldValue = this.filenameCounter;
		this.filenameCounter = filenameCounter;
		firePropertyChange("filenameCounter", oldValue, filenameCounter);
	}

	public void setNewFilename(String newFilename) {
		String oldValue = this.newFilename;
		this.newFilename = newFilename;
		firePropertyChange("newFilename", oldValue, newFilename);
	}

	public void setRulesetId(Integer id) {
		Integer oldValue = this.rulesetId;
		this.rulesetId = id;
		firePropertyChange("rulesetId", oldValue, id);
	}

	public void setPriority(Integer priority) {
		Integer oldValue = this.priority;
		this.priority = priority;
		firePropertyChange("priority", oldValue, priority);
	}

	public void setRuleActiv(boolean ruleActiv) {
		boolean oldValue = this.ruleActiv;
		this.ruleActiv = ruleActiv;
		firePropertyChange("ruleActiv", oldValue, ruleActiv);
	}

	public void setRulesetName(String rulesetName) {
		String oldValue = this.rulesetName;
		this.rulesetName = rulesetName;
		firePropertyChange("rulesetName", oldValue, rulesetName);
	}

	public void setTargetDirectory(String targetDirectory) {
		String oldValue = this.targetDirectory;
		this.targetDirectory = targetDirectory;
		firePropertyChange("targetDirectory", oldValue, targetDirectory);
	}

	public void setRegelsetTableModel(RegelsetTableModel tableModel) {
		this.regelsetTableModel = tableModel;
	}

	public RegelsetTableModel getRegelsetTableModel() {
		return regelsetTableModel;
	}

	/**
	 * erstellt ein "leeres" Regelset Model (null object)
	 *
	 * @return model
	 *             Neues Regelset Model
	 */
	public static RegelsetModel getNullModel() {
		RegelsetModel model = new RegelsetModel();
		model.setFilenameCounter(0L);
		model.setPriority(0);
		model.setRuleActiv(true);
		model.ruleModelList = new ArrayList<RegelModel>();
		return model;
	}

}
