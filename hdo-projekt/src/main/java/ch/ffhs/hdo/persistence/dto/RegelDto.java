package ch.ffhs.hdo.persistence.dto;

import java.util.Date;

/**
 * Data Transfer Object fuer Regel
 * 
 * @author Denis Bittante
 *
 */
public class RegelDto {

	private Integer id;
	private Integer rulesetId;
	private String contextType;
	private String contextAttribute;
	private String compareType;
	private String compareValue;
	private Date creationDate;
	private Date changeDate;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getRulesetId() {
		return rulesetId;
	}

	public void setRulesetId(Integer rulesetId) {
		this.rulesetId = rulesetId;
	}

	public String getContextType() {
		return contextType;
	}

	public void setContextType(String contextType) {
		this.contextType = contextType;
	}

	public String getContextAttribute() {
		return contextAttribute;
	}

	public void setContextAttribute(String contextAttribute) {
		this.contextAttribute = contextAttribute;
	}

	public String getCompareType() {
		return compareType;
	}

	public void setCompareType(String compareType) {
		this.compareType = compareType;
	}

	public String getCompareValue() {
		return compareValue;
	}

	public void setCompareValue(String compareValue) {
		this.compareValue = compareValue;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public Date getChangeDate() {
		return changeDate;
	}

	public void setChangeDate(Date changeDate) {
		this.changeDate = changeDate;
	}

}
