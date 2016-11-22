package ch.ffhs.hdo.client.ui.regelset;

import java.util.List;

import ch.ffhs.hdo.client.ui.base.Model;
import ch.ffhs.hdo.client.ui.utils.IFileModel;

public class RegelsetModel extends Model implements IFileModel {

	private String rulesetName;
	private String targetDirectory;   // TODO: oder "index" auf Liste; UND Liste
	private String filenameKonfiguration;
	private boolean ruleActiv;
	private List<String> ruleContextList;
	private List<String> ruleAttributeList;
	private String rulePropertyFrom;	// TODO: oder besser Object? (kann Date, Long, String,...sein)
	private String rulePropertyTo;		// TODO:  " " " " incl. getter + setter
									// TODO: rulePropertyEqual
									// CompareMode
	
	
	
	public String getRulesetName() {
		
		return rulesetName;
	}

	public void setRulesetName(String rulesetName) {

		String oldValue = this.rulesetName;
		this.rulesetName = rulesetName;
		firePropertyChange("rulesetName", oldValue, rulesetName);
	}

	public String getTargetDirectory() {
		
		return targetDirectory;
	}
	
	public void setTargetDirectory(String targetDirectory) {
		
		String oldValue = this.targetDirectory;
		this.targetDirectory = targetDirectory;
		firePropertyChange("targetDirectory", oldValue, targetDirectory);
	}
	
	public String getFilePath() {
		return getTargetDirectory();
	}

	public void setFilePath(String newValue) {
		setTargetDirectory(newValue);
	}
	
	public String getFilenameKonfiguration() {
		
		return filenameKonfiguration;
	}
	
	public void setFilenameKonfiguration(String filenameKonfiguration) {
		
		String oldValue = this.filenameKonfiguration;
		this.filenameKonfiguration = filenameKonfiguration;
		firePropertyChange("filenameKonfiguration", oldValue, filenameKonfiguration);
	}
	
	public boolean isRuleActiv() {

		return ruleActiv;
	}

	public void setRuleActiv(boolean ruleActiv) {

		boolean oldValue = this.ruleActiv;
		this.ruleActiv = ruleActiv;
		firePropertyChange("ruleActiv", oldValue, ruleActiv);
	}
	
	final List<String> getRuleContextList() {
		
		return ruleContextList;		
		
		// TODO: getRuleContextList().add(xy) funktionier nicht => kein fireProperty??
		//  TODO: bruacht's das überhaupt oder ist Inhalt "fix" ab DB? kein dynamisches Nachladen?
		
		
		// => final => keine andere LIST zuweisbar? aber doch List.add() möglich???
	}
	
	public void setRuleContextList(List<String> ruleContextList) {
		List<String> oldValue = this.ruleContextList;
		this.ruleContextList = ruleContextList;
		firePropertyChange("ruleContextList", oldValue, ruleContextList);
		
		// TODO is firePorperty überhaupt nötig??? kein dynamisches nachladen von DB
	}
	
	final List<String> getRuleAttributeList() {
		
		return ruleAttributeList;		
		
		// TODO: getRuleContextList().add(xy) funktionier nicht => kein fireProperty??
		//  TODO: bruacht's das überhaupt oder ist Inhalt "fix" ab DB? kein dynamisches Nachladen?
		
		
		// => final => keine andere LIST zuweisbar? aber doch List.add() möglich???
	}
	
	public void setRuleAttributeList(List<String> ruleAttributeList) {
		List<String> oldValue = this.ruleAttributeList;
		this.ruleAttributeList = ruleAttributeList;
		firePropertyChange("ruleAttributeList", oldValue, ruleAttributeList);
		
		// TODO is firePorperty überhaupt nötig??? kein dynamisches nachladen von DB
	}
	
	public String getRulePropertyFrom() {
		
		return rulePropertyFrom;
	}
	
	public void setRulePropertyFrom(String rulePropertyFrom) {
		
		String oldValue = this.rulePropertyFrom;
		this.rulePropertyFrom = rulePropertyFrom;
		firePropertyChange("rulePropertyFrom", oldValue, rulePropertyFrom);
	}
	
	public String getRulePropertyTo() {
		
		return rulePropertyTo;
	}
	
	public void setRulePropertyTo(String rulePropertyTo) {
		
		String oldValue = this.rulePropertyTo;
		this.rulePropertyTo = rulePropertyTo;
		firePropertyChange("rulePropertyTo", oldValue, rulePropertyTo);
	}

	public String getFilePath() {
		return getTargetDirectory();
	}

	public void setFilePath(String newValue) {
		setTargetDirectory(newValue);
	}
}
