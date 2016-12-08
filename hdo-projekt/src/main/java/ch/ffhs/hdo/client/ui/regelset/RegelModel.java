package ch.ffhs.hdo.client.ui.regelset;

import java.util.ArrayList;
import java.util.List;

import ch.ffhs.hdo.client.ui.base.Model;

public class RegelModel extends Model {

	
	//TODO: ruleset-ID brauchts nicht. => nur auf DB
			
	// TODO: ist kopiert aus View    (sollte nicht kopiert werden)
	static private final String I18N = "hdo.regelset";
	static private final String TITLE_KEY = I18N + ".title";
	static private final String CONTEXT_COMBOBOXKEY = I18N + ".combobox";
	static private final String ATTRIBUTE_COMBOBOXKEY = I18N + ".combobox";
	static private final String COMPARISON_MODE_COMBOBOXKEY = I18N + ".combobox";
	
	
	
	/**
	 * Model für die Regeln 
	 * 
	 * @author Daniel Crazzolara
	 */

	
	public static enum ComparisonTypeEnum {
		EMPTY, COMPARISON_EQUAL, COMPARISON_UNEQUAL, COMPARISON_LESS_EQUAL, COMPARISON_GREATER_EQUAL, COMPARISON_REGEX, COMPARISON_LIST;
	}	


	public static enum ContextAttributeEnum {
		EMPTY,
		PDF_TITLE, PDF_AUTHOR, PDF_CREATION_DATE, PDF_CONTENT, PDF_SIZE,
		FILE_NAME, FILE_EXTENSION, FILE_SIZE, FILE_CREATION_DATE, FILE_OWNER;	
		
		
		public static ContextAttributeEnum[] values(ContextTypeEnum context) {
			List<ContextAttributeEnum> attributeEnumsList = new ArrayList<ContextAttributeEnum>();

			for (ContextAttributeEnum attribute : values()) {
				if (context.equals(ContextTypeEnum.CONTEXT_PDF) && attribute.toString().startsWith("PDF_")) {
					attributeEnumsList.add(attribute); 
				} else if (context.equals(ContextTypeEnum.CONTEXT_FILE) && attribute.toString().startsWith("FILE_")) {
					attributeEnumsList.add(attribute);
				} else if (context.equals(ContextTypeEnum.CONTEXT_CONTENT) && attribute.toString().startsWith("CONTENT_")) {
					attributeEnumsList.add(attribute);
				}
			}
			
			return attributeEnumsList.toArray(new ContextAttributeEnum[0]);
		}
		
		@Override
		public String toString() {
			return ">>" + name() + "<<";
			
		}
	}

	
	
	public static enum ContextTypeEnum {
		EMPTY, CONTEXT_PDF, CONTEXT_FILE, CONTEXT_CONTENT;	
		 // TODO: Content für PDF und FILE getrennt handeln?
	}
	
	
	private String					compareValue;
	private ComparisonTypeEnum      comparisonType = ComparisonTypeEnum.EMPTY;
	private ContextAttributeEnum    contextAttribute  = ContextAttributeEnum.EMPTY;
																// TODO: eigenes Pdf-Attr
																//	unschön, da später word, xls,...
	private ContextTypeEnum         contextType = ContextTypeEnum.EMPTY;
	private Integer                 id;
	private String                  ruleName;
	
	
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
