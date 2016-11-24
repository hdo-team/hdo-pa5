package ch.ffhs.hdo.client.ui.regelset;

import java.util.List;

import ch.ffhs.hdo.client.ui.base.Model;

public class RegelsetModel extends Model {

	private List<String>     directories;		// Liste mit möglichen Ziel-Verzeichnissen 
	private String           filenameKonfiguration;
	private Integer          id;
	private Integer          priority;			// TODO: ?? evtl. nur in DB?? oder nötig für Jonas?
	private boolean          ruleActiv;
	private List<RegelModel> ruleList;		// alle Regeln pro Regelset werden AND-verknuepft
	private String           rulesetName;
	private String           targetDirectory;
	
	

	/**
	* TODO: getXyzList().add(xy) funktionier nicht => kein
	* fireProperty??
	* TODO: bruacht's das überhaupt oder ist Inhalt "fix" ab DB? kein
	* dynamisches Nachladen?
	* => final => keine andere LIST zuweisbar? aber doch List.add()
	* möglich???
	* 
	* 
	* TODO: KEIN Setter für List<> ?? 
	* 
	*/
	
	
	public List<String> getDirectories() {
		return directories;
	}

	public String getFilenameKonfiguration() {
		return filenameKonfiguration;
	}

	public Integer getId() {
		return id;
	}

	public Integer getPriority() {
		return priority;
	}

	public List<RegelModel> getRuleList() {
		return ruleList;
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

	public void setDirectories(List<String> directories) {
		List<String> oldValue = this.directories;
		this.directories = directories;
		firePropertyChange("directories", oldValue, directories);
	}

	public void setFilenameKonfiguration(String filenameKonfiguration) {
		String oldValue = this.filenameKonfiguration;
		this.filenameKonfiguration = filenameKonfiguration;
		firePropertyChange("filenameKonfiguration", oldValue, filenameKonfiguration);
	}

	public void setId(Integer id) {
		Integer oldValue = this.id;
		this.id = id;
		firePropertyChange("id", oldValue, id);
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

	public void setRuleList(List<RegelModel> ruleList) {
		List<RegelModel> oldValue = this.ruleList;
		this.ruleList = ruleList;
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
}
