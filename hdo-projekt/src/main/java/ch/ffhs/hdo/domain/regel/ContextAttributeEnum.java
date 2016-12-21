package ch.ffhs.hdo.domain.regel;

/**
 * Enumeration fuer die Regelattribute
 * 
 * 
 * @author Daniel Crazzolara
 */

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Enum für die Attribute die fuer die Regel anwendbar sind
 * 
 * @author Denis Bittante
 * 
 *         Folgende Enumerationen sind möglich: <br>
 *         {@link ContextAttributeEnum#EMPTY}<br>
 *         {@link ContextAttributeEnum#FILE_CREATION_DATE}<br>
 *         {@link ContextAttributeEnum#FILE_EXTENSION}<br>
 *         {@link ContextAttributeEnum#FILE_NAME}<br>
 *         {@link ContextAttributeEnum#FILE_OWNER}<br>
 *         {@link ContextAttributeEnum#FILE_SIZE}<br>
 *         {@link ContextAttributeEnum#PDF_AUTHOR}<br>
 *         {@link ContextAttributeEnum#PDF_CONTENT}<br>
 *         {@link ContextAttributeEnum#PDF_CREATION_DATE}<br>
 *         {@link ContextAttributeEnum#PDF_SIZE}<br>
 *         {@link ContextAttributeEnum#PDF_TITLE}<br>
 *         {@link ContextAttributeEnum#PDF_SUBJECT}<br>
 *         {@link ContextAttributeEnum#PDF_KEYWORDS}<br>
 *         {@link ContextAttributeEnum#PDF_CREATOR}<br>
 *         {@link ContextAttributeEnum#PDF_PRODUCER}<br>
 *         {@link ContextAttributeEnum#PDF_MODIFICATION_DATE}<br>
 *         {@link ContextAttributeEnum#PDF_PAGECOUNT}<br>
 */
public enum ContextAttributeEnum {
	/**
	 * Leeres Element fuer die Combobox
	 */
	EMPTY(ContextTypeEnum.EMPTY, DataTypeEnum.NULL),
	/**
	 * Erstellungsdatum eines Files
	 */
	FILE_CREATION_DATE(ContextTypeEnum.CONTEXT_FILE, DataTypeEnum.DATE),
	/**
	 * File-Endung
	 */
	FILE_EXTENSION(ContextTypeEnum.CONTEXT_FILE, DataTypeEnum.STRING),
	/**
	 * File Name
	 */
	FILE_NAME(ContextTypeEnum.CONTEXT_FILE, DataTypeEnum.STRING),
	/**
	 * Besitzer eines Files
	 */
	FILE_OWNER(ContextTypeEnum.CONTEXT_FILE, DataTypeEnum.STRING),
	/**
	 * File Grösse
	 */
	FILE_SIZE(ContextTypeEnum.CONTEXT_FILE, DataTypeEnum.INT),
	/**
	 * PDF Autor
	 */
	PDF_AUTHOR(ContextTypeEnum.CONTEXT_PDF, DataTypeEnum.STRING),
	/**
	 * PDF Inhalt als String ohne Formatierung
	 */
	PDF_CONTENT(ContextTypeEnum.CONTENT_FILE, DataTypeEnum.CONTENT_STRING),
	/**
	 * PDF Erstellungsdatum
	 */
	PDF_CREATION_DATE(ContextTypeEnum.CONTEXT_PDF, DataTypeEnum.DATE),
	/**
	 * PDF Grösse
	 */
	PDF_SIZE(ContextTypeEnum.CONTEXT_PDF, DataTypeEnum.INT),
	/**
	 * PDF Titel
	 */
	PDF_TITLE(ContextTypeEnum.CONTEXT_PDF, DataTypeEnum.STRING),
	/**
	 * PDF Betreff
	 */
	PDF_SUBJECT(ContextTypeEnum.CONTEXT_PDF, DataTypeEnum.STRING),
	/**
	 * PDF Stichwörter
	 */
	PDF_KEYWORDS(ContextTypeEnum.CONTEXT_PDF, DataTypeEnum.STRING),
	/**
	 * PDF Hersteller, Meist das Programm welches dieses Erstellt hat.
	 */
	PDF_CREATOR(ContextTypeEnum.CONTEXT_PDF, DataTypeEnum.STRING),
	/**
	 * PDF Hersteller Produzent Source etwa das selbe wie
	 * {@link ContextAttributeEnum#PDF_CREATOR}
	 */
	PDF_PRODUCER(ContextTypeEnum.CONTEXT_PDF, DataTypeEnum.STRING),
	/**
	 * PDF Änderungsdatum
	 */
	PDF_MODIFICATION_DATE(ContextTypeEnum.CONTEXT_PDF, DataTypeEnum.DATE),
	/**
	 * PDF Seitenanzahl
	 */
	PDF_PAGECOUNT(ContextTypeEnum.CONTEXT_PDF, DataTypeEnum.INT);

	/**
	 * Liefert die Regelattribut-Enums in Abhaenigkeit vom Regelkontext
	 * 
	 * @param context
	 *            Regelkontext
	 * @return attributEnum[] Array mit den Regelattribut-Enums
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
	 *            Datentyp der zu dieser Attribut-Enumeration gehoert
	 */
	private ContextAttributeEnum(ContextTypeEnum context, DataTypeEnum type) {
		this.context = context;
		this.type = type;

	}

	/**
	 * Getter den {@link ContextTypeEnum} zurück
	 * 
	 * @return {@link getContextTypeEnum}
	 */
	public ContextTypeEnum getContextTypeEnum() {
		return this.context;
	}

	/**
	 * Getter fuer den DatenTyp
	 * 
	 * @return {@link DataTypeEnum}
	 */
	public DataTypeEnum getDataType() {
		return this.type;
	}

	/**
	 * liefert die Auspraegung der Enumeration
	 * 
	 * @return I18NValue Auspraegung in der gewuenschten Sprache
	 */
	@Override
	public String toString() {
		final ResourceBundle bundle = ResourceBundle.getBundle("ch/ffhs/hdo/client/ui/resourceBundle");

		this.I18NValue = bundle.getString(CONTEXT_COMBOBOXKEY + this.name().toLowerCase());

		return this.I18NValue;

	}
}