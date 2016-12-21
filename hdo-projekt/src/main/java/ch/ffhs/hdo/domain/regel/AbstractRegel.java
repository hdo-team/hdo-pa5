package ch.ffhs.hdo.domain.regel;

import java.nio.file.attribute.FileTime;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import ch.ffhs.hdo.domain.document.DocumentModel;

/**
 * Abstrakte implementation von Regeln zusammensetzung und dynamische vergleiche
 * wurden hier implementiert.
 * 
 * @author Denis Bittante
 *
 */
public abstract class AbstractRegel {

	private Object fileValue;
	private String regelValue;

	private ComparisonTypeEnum comparisonType = ComparisonTypeEnum.EMPTY;

	private ContextAttributeEnum contextAttribute = ContextAttributeEnum.EMPTY;

	private DocumentModel model;

	protected boolean compareDate() {

		GregorianCalendar fileDate = convertToDate(this.fileValue);
		GregorianCalendar regelDate = convertToDate(this.regelValue);

		switch (this.getComparisonType()) {
		case COMPARISON_EQUAL:
			final int month = fileDate.get(Calendar.MONTH);
			final int year = fileDate.get(Calendar.YEAR);
			final int dayOfMonth = fileDate.get(Calendar.DAY_OF_MONTH);
			GregorianCalendar g = new GregorianCalendar(year, month, dayOfMonth);
			return g.equals(regelDate);

		case COMPARISON_GREATER_EQUAL:
			return regelDate.before(fileDate);
		case COMPARISON_LESS_EQUAL:
			return fileDate.before(regelDate);
		case COMPARISON_REGEX:
			return false;
		case COMPARISON_UNEQUAL:
			return !fileDate.equals(regelDate);

		case EMPTY:
			break;
		default:
			break;
		}
		return false;

	}

	private GregorianCalendar convertToDate(Object date) {

		GregorianCalendar gregcal = new GregorianCalendar();
		if (date instanceof String && date != null) {

			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			try {
				final Date parse = dateFormat.parse(regelValue);
				gregcal.setTime(parse);
			} catch (ParseException e) {
				e.printStackTrace();
			}

		}

		if (date instanceof FileTime) {

			FileTime filetime = (FileTime) fileValue;
			gregcal.setTimeInMillis(filetime.toMillis());
		}
		return gregcal;
	}

	protected boolean compareInt() {

		final Integer valInt = Integer.valueOf(regelValue);
		final Integer valToInt = Integer.valueOf(this.fileValue.toString());

		switch (this.getComparisonType()) {
		case COMPARISON_EQUAL:
			return valToInt.equals(valInt);
		case COMPARISON_GREATER_EQUAL:
			return valToInt >= valInt;
		case COMPARISON_LESS_EQUAL:
			return valToInt <= valInt;
		case COMPARISON_REGEX:
			return false;
		case COMPARISON_UNEQUAL:
			return !valToInt.equals(valInt);
		case EMPTY:
			break;
		default:
			break;
		}
		return false;
	}

	protected boolean compareString() {
		final String valStr = this.fileValue.toString();

		switch (this.getComparisonType()) {
		case COMPARISON_EQUAL:
			return valStr.equals(this.regelValue);
		case COMPARISON_GREATER_EQUAL:
			return false;
		case COMPARISON_LESS_EQUAL:
			return false;
		case COMPARISON_REGEX:
			Pattern p = Pattern.compile(regelValue.toString());
			final Matcher matcher = p.matcher(valStr);
			final boolean matches = matcher.find();
			return matches;
		case COMPARISON_UNEQUAL:
			return !valStr.equals(this.regelValue);
		case EMPTY:
			return false;
		default:
			break;
		}
		return false;
	}

	/**
	 * Retourniert vergleichswert
	 * 
	 * @return CompareValue
	 */
	public String getCompareValue() {
		return regelValue;
	}

	/**
	 * Retourniert ComparisonMode
	 * 
	 * @return {@link ComparisonTypeEnum}
	 */
	public ComparisonTypeEnum getComparisonType() {
		return comparisonType;
	}

	/**
	 * Retourniert ContextAttribute
	 * 
	 * @return {@link ContextAttributeEnum}
	 */
	public ContextAttributeEnum getContextAttribute() {
		return contextAttribute;
	}

	/**
	 * Getter für DokumentModel
	 * 
	 * @return {@link DocumentModel}
	 */
	public DocumentModel getModel() {
		return model;
	}

	protected boolean mainCompare() {

		try {
			final DataTypeEnum dataType = this.getContextAttribute().getDataType();
			switch (dataType) {
			case DATE:
				return compareDate();
			case INT:
				return compareInt();
			case STRING:
				return compareString();
			case CONTENT_STRING:
				return compareString();
			case NULL:
				return false;
			default:
				return false;
			}

		} catch (Exception e) {
			// Ein Fehler in der Konfiguraiton soll nicht zum Abbruch fuehren.

			return false;
		}

	}

	/**
	 * Setter CompareValueTo Vergleichwert aus dem File
	 * 
	 * @param compareToValue
	 */
	public void setCompareToValue(Object compareToValue) {
		this.fileValue = compareToValue;
	}

	/**
	 * Setter CompareValue Vergleichwert aus der Regel
	 * 
	 * @param compareValue
	 * 
	 */
	public void setCompareValue(String compareValue) {
		this.regelValue = compareValue;
	}

	/**
	 * Vergleichesart siehe {@link ComparisonTypeEnum}
	 * 
	 * @param comparisonType
	 */
	public void setComparisonType(ComparisonTypeEnum comparisonType) {
		this.comparisonType = comparisonType;
	}

	/**
	 * Setter {@link ContextAttributeEnum}
	 * 
	 * @param contextAttribute
	 *            see {@link ContextAttributeEnum}
	 */
	public void setContextAttribute(ContextAttributeEnum contextAttribute) {
		this.contextAttribute = contextAttribute;
	}

	/**
	 * Setter Model
	 * 
	 * @param model
	 *            see {@link DocumentModel}
	 */
	public void setModel(DocumentModel model) {
		this.model = model;
	}

	abstract boolean verifizieren();

}
