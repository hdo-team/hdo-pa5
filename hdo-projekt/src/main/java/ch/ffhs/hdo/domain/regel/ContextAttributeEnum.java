package ch.ffhs.hdo.domain.regel;

/**
 * Enum für Attribute
 * (abhängig vom Regel-Kontext und vom Daten-Typ)
 * 
 * 
 * @author Daniel Crazzolara
 */

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import ch.ffhs.hdo.client.ui.regelset.RegelModel;


public enum ContextAttributeEnum {
	EMPTY(ContextTypeEnum.EMPTY, DataTypeEnum.NULL), 
	FILE_CREATION_DATE(ContextTypeEnum.CONTEXT_FILE, DataTypeEnum.DATE), 
	FILE_EXTENSION(ContextTypeEnum.CONTEXT_FILE, DataTypeEnum.STRING), 
	FILE_NAME(ContextTypeEnum.CONTEXT_FILE,	DataTypeEnum.STRING), 
	FILE_OWNER(ContextTypeEnum.CONTEXT_FILE, DataTypeEnum.STRING), 
	FILE_SIZE(ContextTypeEnum.CONTEXT_FILE,	DataTypeEnum.INT), 
	PDF_AUTHOR(ContextTypeEnum.CONTEXT_PDF, DataTypeEnum.STRING), 
	PDF_CONTENT(ContextTypeEnum.CONTEXT_PDF, DataTypeEnum.STRING), 
	PDF_CREATION_DATE(ContextTypeEnum.CONTEXT_PDF, DataTypeEnum.DATE),
	PDF_SIZE(ContextTypeEnum.CONTEXT_PDF, DataTypeEnum.INT),
	PDF_TITLE(ContextTypeEnum.CONTEXT_PDF, DataTypeEnum.STRING);

	public static ContextAttributeEnum[] values(ContextTypeEnum context) {

		List<ContextAttributeEnum> attributeEnumsList = new ArrayList<ContextAttributeEnum>();

		for (ContextAttributeEnum attribute : ContextAttributeEnum.values()) {
			if (attribute.getContextTypeEnum().equals(context)) {
				attributeEnumsList.add(attribute);
			}
		}

		return attributeEnumsList.toArray(new ContextAttributeEnum[0]);
	}

	private static final String I18N = "hdo.regelset";
	private static final String CONTEXT_COMBOBOXKEY = I18N + ".combobox.attribute.";

	
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

	@Override
	public String toString() {
		return this.I18NValue;

	}
}