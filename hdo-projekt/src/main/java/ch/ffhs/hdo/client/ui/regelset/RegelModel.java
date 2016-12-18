package ch.ffhs.hdo.client.ui.regelset;

import ch.ffhs.hdo.client.ui.base.Model;
import ch.ffhs.hdo.domain.regel.ComparisonTypeEnum;
import ch.ffhs.hdo.domain.regel.ContextAttributeEnum;
import ch.ffhs.hdo.domain.regel.ContextTypeEnum;

/**
 * RegelModel
 * 
 * @author Daniel Crazzolara
 */

public class RegelModel extends Model {

	private String compareValue;
	private ComparisonTypeEnum comparisonType = ComparisonTypeEnum.EMPTY;
	private ContextAttributeEnum contextAttribute = ContextAttributeEnum.EMPTY;
	private ContextTypeEnum contextType = ContextTypeEnum.EMPTY;
	private Integer id;
	private String ruleName;

	private RegelModel() {
			// neue Instanzen mit getNullModel() erstellen
	}

	public String getCompareValue() {
		return compareValue;
	}

	public ComparisonTypeEnum getComparisonType() {
		return comparisonType;
	}

	public ContextAttributeEnum getContextAttribute() {
		return contextAttribute;
	}

	public ContextTypeEnum getContextType() {
		return contextType;
	}

	public Integer getId() {
		return id;
	}

	public String getRuleName() {
		return ruleName;
	}

	public void setCompareValue(String compareValue) {
		String oldValue = compareValue;
		this.compareValue = compareValue;
		firePropertyChange("compareValue", oldValue, compareValue);
	}

	public void setComparisonType(ComparisonTypeEnum comparisonType) {
		ComparisonTypeEnum oldValue = this.comparisonType;
		this.comparisonType = comparisonType;
		firePropertyChange("comparisonType", oldValue, comparisonType);
	}

	public void setContextAttribute(ContextAttributeEnum contextAttribute) {
		ContextAttributeEnum oldValue = this.contextAttribute;
		this.contextAttribute = contextAttribute;
		firePropertyChange("contextAttribute", oldValue, contextAttribute);
	}

	public void setContextType(ContextTypeEnum contextType) {
		ContextTypeEnum oldValue = this.contextType;
		this.contextType = contextType;
		firePropertyChange("contextType", oldValue, contextType);
	}

	public void setId(Integer id) {
		Integer oldValue = this.id;
		this.id = id;
		firePropertyChange("id", oldValue, id);
	}

	public void setRuleName(String ruleName) {
		String oldValue = this.ruleName;
		this.ruleName = ruleName;
		firePropertyChange("ruleName", oldValue, ruleName);
	}

	/**
	 * erstellt ein "leeres" Regel Model (null object)
	 *
	 * @return model
	 *             Neues Regel Model
	 */
	public static RegelModel getNullModel() {
		RegelModel model = new RegelModel();

		model.setContextType(ContextTypeEnum.EMPTY);
		model.setContextAttribute(ContextAttributeEnum.EMPTY);
		model.setComparisonType(ComparisonTypeEnum.EMPTY);

		return model;
	}
}
