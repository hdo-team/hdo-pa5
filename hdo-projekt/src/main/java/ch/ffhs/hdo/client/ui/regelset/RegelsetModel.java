package ch.ffhs.hdo.client.ui.regelset;

import java.util.ArrayList;
import java.util.List;

import ch.ffhs.hdo.client.ui.base.Model;

public class RegelsetModel extends Model {

	private Long filenameCounter;
	private String newFilename;
	private Integer rulesetId;
	private Integer priority; // TODO: ?? evtl. nur in DB?? oder nötig für
								// Jonas?
	private boolean ruleActiv;
	private List<RegelModel> ruleModelList; // alle Regeln pro Regelset werden
											// AND-verknuepft
	private String rulesetName;
	private String targetDirectory;

	/**
	 * TODO: getXyzList().add(xy) funktionier nicht => kein fireProperty?? TODO:
	 * bruacht's das überhaupt oder ist Inhalt "fix" ab DB? kein dynamisches
	 * Nachladen? => final => keine andere LIST zuweisbar? aber doch List.add()
	 * möglich???
	 * 
	 * 
	 * TODO: KEIN Setter für List<> ??
	 * 
	 */

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

	public void setRuleModelList(List<RegelModel> ruleList) {
		List<RegelModel> oldValue = this.ruleModelList;
		this.ruleModelList = ruleList;
		firePropertyChange("ruleList", oldValue, ruleList);
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

	public static RegelsetModel getNullModel() {
		RegelsetModel model = new RegelsetModel();
		model.setFilenameCounter(0L);
		model.setPriority(0);
		model.setRuleActiv(true);
		model.setRuleModelList(new ArrayList<RegelModel>());
		model.getRuleModelList().add(RegelModel.getNullModel());  // TODO: nicht nötig; wir bei <<add REegel>> gemacht? 
		return model;
	}

}
