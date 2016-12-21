package ch.ffhs.hdo.persistence.dto;

import java.util.Date;
import java.util.List;

/**
 * Data Tranfer Object fuer Reglsets
 * 
 * @author Denis Bittante
 *
 */
public class RegelsetDto {

	private static final long serialVersionUID = 6814842222879287290L;

	private Integer id;
	private String targetDirectory;
	private String rulesetName;
	private String newFilename;
	private Long filenameCounter;
	private Integer prority;
	private boolean active;
	private Date creationDate;
	private Date changedate;

	private List<RegelDto> regeln;

	public List<RegelDto> getRegeln() {
		return regeln;
	}

	public void setRegeln(List<RegelDto> regeln) {
		this.regeln = regeln;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTargetDirectory() {
		return targetDirectory;
	}

	public void setTargetDirectory(String targetDirectory) {
		this.targetDirectory = targetDirectory;
	}

	public String getRulesetName() {
		return rulesetName;
	}

	public void setRulesetName(String rulesetName) {
		this.rulesetName = rulesetName;
	}

	public String getNewFilename() {
		return newFilename;
	}

	public void setNewFilename(String newFilename) {
		this.newFilename = newFilename;
	}

	public Long getFilenameCounter() {
		return filenameCounter;
	}

	public void setFilenameCounter(Long filenameCounter) {
		this.filenameCounter = filenameCounter;
	}

	public Integer getPrority() {
		return prority;
	}

	public void setPrority(Integer prority) {
		this.prority = prority;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public Date getChangedate() {
		return changedate;
	}

	public void setChangedate(Date changedate) {
		this.changedate = changedate;
	}

}
