package ch.ffhs.hdo.domain.regel;

/**
 * Enumeration für die Regelattribute
 * 
 * 
 * @author Daniel Crazzolara
 */

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;


public enum ContextAttributeEnum {
	EMPTY(ContextTypeEnum.EMPTY, DataTypeEnum.NULL), 
	FILE_CREATION_DATE(ContextTypeEnum.CONTEXT_FILE, DataTypeEnum.DATE), 
	FILE_EXTENSION(ContextTypeEnum.CONTEXT_FILE, DataTypeEnum.STRING), 
	FILE_NAME(ContextTypeEnum.CONTEXT_FILE,	DataTypeEnum.STRING), 
	FILE_OWNER(ContextTypeEnum.CONTEXT_FILE, DataTypeEnum.STRING), 
	FILE_SIZE(ContextTypeEnum.CONTEXT_FILE,	DataTypeEnum.INT), 
	PDF_AUTHOR(ContextTypeEnum.CONTEXT_PDF, DataTypeEnum.STRING), 
	PDF_CONTENT(ContextTypeEnum.CONTENT_FILE, DataTypeEnum.CONTENT_STRING), 
	PDF_CREATION_DATE(ContextTypeEnum.CONTEXT_PDF, DataTypeEnum.DATE),
	PDF_SIZE(ContextTypeEnum.CONTEXT_PDF, DataTypeEnum.INT),
	PDF_TITLE(ContextTypeEnum.CONTEXT_PDF, DataTypeEnum.STRING),
	PDF_SUBJECT(ContextTypeEnum.CONTEXT_PDF, DataTypeEnum.STRING),
	PDF_KEYWORDS(ContextTypeEnum.CONTEXT_PDF, DataTypeEnum.STRING),
	PDF_CREATOR(ContextTypeEnum.CONTEXT_PDF, DataTypeEnum.STRING),
	PDF_PRODUCER(ContextTypeEnum.CONTEXT_PDF, DataTypeEnum.STRING),
	PDF_MODIFICATION_DATE(ContextTypeEnum.CONTEXT_PDF, DataTypeEnum.DATE),
	PDF_PAGECOUNT(ContextTypeEnum.CONTEXT_PDF, DataTypeEnum.INT);

	/**
	 * Liefert die Regelattribut-Enums in Abhänigkeit vom Regelkontext
	 * 
	 * @param context
	 *            Regelkontext
	 * @return attributEnum[]
	 * 	          Array mit den Regelattribut-Enums
	 */
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

	/** 
	 * Konstruktor zur Erstellung einer Regelattribut-Enum
	 * 
	 * @param context
	 *            Regelkontext der zu dieser Attribut-Enumeration gehoert
	 * @param type
	 * 	          Datentyp der zu dieser Attribut-Enumeration gehoert
	 */
	private ContextAttributeEnum(ContextTypeEnum context, DataTypeEnum type) {
		this.context = context;
		this.type = type;
	
	}

	public ContextTypeEnum getContextTypeEnum() {
		return this.context;
	}

	public DataTypeEnum getDataType() {
		return this.type;
	}

	/**
	 * liefert die Auspraegung der Enumeration
	 * 
	 * @return I18NValue
	 *             Auspraegung in der gewuenschten Sprache
	 */
	@Override
	public String toString() {
		final ResourceBundle bundle = ResourceBundle.getBundle("ch/ffhs/hdo/client/ui/resourceBundle");

		this.I18NValue = bundle.getString(CONTEXT_COMBOBOXKEY + this.name().toLowerCase());

		return this.I18NValue;

	}
}