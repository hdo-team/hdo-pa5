package ch.ffhs.hdo.client.ui.regelset;

import java.util.HashMap;

import ch.ffhs.hdo.client.ui.base.Model;

public class RegelModel extends Model {

	
	/**
	 * Model für die Regeln 
	 * 
	 * @author Daniel Crazzolara
	 */

	public static enum ComparisonTypeEnum {
		COMPARISON_BETWEEN, COMPARISON_EQUAL, COMPARISON_LIST;
	}	
	
	public static enum ContextAttributeEnum {
		AUTHOR, CREATION_DATE, SIZE;
	}

	public static enum ContextTypeEnum {
		CONTEXT_CONTENT, CONTEXT_FILE, CONTEXT_PDF;	
		 // Content-Pdf, Content-File, 2*Content
		// eine Enum über alles ODER eine Enum pro ContextType ??
				 // extension-Typ, doc, pdf, txt, ...
		// bsp? PDF_SIZE, FILE_SIZE ??
	}
	
	
	private HashMap<ComparisonTypeEnum, String> compareValueMap;
	private ComparisonTypeEnum                  comparisonType;
	private ContextAttributeEnum                contextAttribute;
	private ContextTypeEnum                     contextType;
	private Integer                             id;
	private String                              ruleName;
	
	
	//
	// TODO: sind alle firePropertyChange wirlich nötig?
	//     (gibt ja kein dynanisches nachladen von DB)
		
	
	public HashMap<ComparisonTypeEnum, String> getCompareValueMap() {
		return compareValueMap;
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

	public void setCompareValueMap(HashMap<ComparisonTypeEnum, String> compareValueMap) {
		HashMap<ComparisonTypeEnum, String> oldValue = compareValueMap;
		this.compareValueMap = compareValueMap;
		firePropertyChange("compareValueMap", oldValue, compareValueMap);
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
}
