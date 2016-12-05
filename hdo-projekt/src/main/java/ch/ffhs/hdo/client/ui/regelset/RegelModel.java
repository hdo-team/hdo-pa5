package ch.ffhs.hdo.client.ui.regelset;

import ch.ffhs.hdo.client.ui.base.Model;

public class RegelModel extends Model {

	
	//TODO: ruleset-ID brauchts nicht. => nur auf DB
			
			
	
	
	
	/**
	 * Model für die Regeln 
	 * 
	 * @author Daniel Crazzolara
	 */

	public static enum ComparisonTypeEnum {
		 COMPARISON_EQUAL, COMPARISON_UNEQUAL, COMPARISON_LESS_EQUAL, COMPARISON_GREATER_EQUAL, COMPARISON_REGEX, COMPARISON_LIST;
	}	
	
	// TODO: umbenennen auf Context - PDF- ...
	//public static enum ContextAttributeEnum {
		//;		// TODO: Content für PDF separat?
	//}


	public static enum ContextAttributeEnum {
		PDF_TITLE, PDF_AUTHOR, PDF_CREATION_DATE, PDF_CONTENT, PDF_SIZE,
		FILE_NAME, FILE_EXTENSION, FILE_SIZE, FILE_CREATION_DATE, FILE_OWNER;		/// ????	FILE_ -Prefix entferene?
														// dynamischre lsg suche?
	}

	
	
	public static enum ContextTypeEnum {
		CONTEXT_PDF, CONTEXT_FILE, CONTEXT_CONTENT;	
		 // Content-Pdf, Content-File, 2*Content
		// eine Enum über alles ODER eine Enum pro ContextType ??
				 // extension-Typ, doc, pdf, txt, ...
		// bsp? PDF_SIZE, FILE_SIZE ??
	}
	
	
	private String							 compareValue;
	private ComparisonTypeEnum               comparisonType;
	private ContextAttributeEnum             contextAttribute;
																// TODO: eigenes Pdf-Attr
																//	unschön, da später word, xls,...
	private ContextTypeEnum                  contextType;
	private Integer                          id;
	private String                           ruleName;
	
	
	//
	// TODO: sind alle firePropertyChange wirlich nötig?
	//     (gibt ja kein dynanisches nachladen von DB)
		
	
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

	public void setCompareValue(String compareValueMap) {
		 String oldValue = compareValueMap;
		this.compareValue = compareValueMap;
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
