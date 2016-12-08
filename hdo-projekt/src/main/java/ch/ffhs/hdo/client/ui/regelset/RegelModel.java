package ch.ffhs.hdo.client.ui.regelset;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import ch.ffhs.hdo.client.ui.base.Model;

public class RegelModel extends Model {

	// TODO: ruleset-ID brauchts nicht. => nur auf DB

	// TODO: ist kopiert aus View (sollte nicht kopiert werden)
	static private final String I18N = "hdo.regelset";
	static private final String TITLE_KEY = I18N + ".title";
	static private final String CONTEXT_COMBOBOXKEY = I18N + ".combobox.attribute.";

	/**
	 * Model für die Regeln
	 * 
	 * @author Daniel Crazzolara
	 */

	public static enum ComparisonTypeEnum {
		EMPTY, COMPARISON_EQUAL, COMPARISON_UNEQUAL, COMPARISON_LESS_EQUAL, COMPARISON_GREATER_EQUAL, COMPARISON_REGEX;
	}

	public static enum DataTypeEnum {
		NULL, STRING(ComparisonTypeEnum.COMPARISON_EQUAL, ComparisonTypeEnum.COMPARISON_UNEQUAL,
				ComparisonTypeEnum.COMPARISON_REGEX), INT(ComparisonTypeEnum.COMPARISON_EQUAL,
						ComparisonTypeEnum.COMPARISON_GREATER_EQUAL, ComparisonTypeEnum.COMPARISON_LESS_EQUAL,
						ComparisonTypeEnum.COMPARISON_UNEQUAL), DATE(ComparisonTypeEnum.COMPARISON_EQUAL,
								ComparisonTypeEnum.COMPARISON_GREATER_EQUAL, ComparisonTypeEnum.COMPARISON_LESS_EQUAL,
								ComparisonTypeEnum.COMPARISON_UNEQUAL);

		private ComparisonTypeEnum[] comparisontype;

		private DataTypeEnum(ComparisonTypeEnum... comparisonTypeEnums) {
			this.comparisontype = comparisonTypeEnums;

		}

		public ComparisonTypeEnum[] getComparisontype() {
			return comparisontype;
		}
	}

	public static enum ContextAttributeEnum {
		EMPTY(ContextTypeEnum.EMPTY, DataTypeEnum.NULL), PDF_TITLE(ContextTypeEnum.CONTEXT_PDF,
				DataTypeEnum.STRING), PDF_AUTHOR(ContextTypeEnum.CONTEXT_PDF, DataTypeEnum.STRING), PDF_CREATION_DATE(
						ContextTypeEnum.CONTEXT_PDF,
						DataTypeEnum.DATE), PDF_CONTENT(ContextTypeEnum.CONTEXT_PDF, DataTypeEnum.STRING), PDF_SIZE(
								ContextTypeEnum.CONTEXT_PDF, DataTypeEnum.INT), FILE_NAME(ContextTypeEnum.CONTEXT_FILE,
										DataTypeEnum.STRING), FILE_EXTENSION(ContextTypeEnum.CONTEXT_FILE,
												DataTypeEnum.STRING), FILE_SIZE(ContextTypeEnum.CONTEXT_FILE,
														DataTypeEnum.INT), FILE_CREATION_DATE(
																ContextTypeEnum.CONTEXT_FILE,
																DataTypeEnum.DATE), FILE_OWNER(
																		ContextTypeEnum.CONTEXT_FILE,
																		DataTypeEnum.STRING);

		private ContextTypeEnum context;
		private String I18NValue;

		private DataTypeEnum type;

		private ContextAttributeEnum(ContextTypeEnum context, DataTypeEnum type) {
			this.context = context;
			this.type = type;
			final ResourceBundle bundle = ResourceBundle.getBundle("ch/ffhs/hdo/client/ui/resourceBundle");

			this.I18NValue = bundle.getString(CONTEXT_COMBOBOXKEY + this.name().toLowerCase());

		}

		public ContextTypeEnum getContextTypeEnum() {
			return this.context;
		}

		public DataTypeEnum getDataType() {
			return this.type;
		}

		public static ContextAttributeEnum[] values(ContextTypeEnum context) {

			List<ContextAttributeEnum> attributeEnumsList = new ArrayList<ContextAttributeEnum>();

			for (ContextAttributeEnum attribute : ContextAttributeEnum.values()) {
				if (attribute.getContextTypeEnum().equals(context)) {
					attributeEnumsList.add(attribute);
				}
			}

			return attributeEnumsList.toArray(new ContextAttributeEnum[0]);
		}

		@Override
		public String toString() {
			return this.I18NValue;

		}
	}

	public static enum ContextTypeEnum {
		EMPTY, CONTEXT_PDF, CONTEXT_FILE, CONTEXT_CONTENT;
		// TODO: Content für PDF und FILE getrennt handeln?
	}

	private String compareValue;
	private ComparisonTypeEnum comparisonType = ComparisonTypeEnum.EMPTY;
	private ContextAttributeEnum contextAttribute = ContextAttributeEnum.EMPTY;
	// TODO: eigenes Pdf-Attr
	// unschön, da später word, xls,...
	private ContextTypeEnum contextType = ContextTypeEnum.EMPTY;
	private Integer id;
	private String ruleName;

	//
	// TODO: sind alle firePropertyChange wirlich nötig?
	// (gibt ja kein dynanisches nachladen von DB)

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
