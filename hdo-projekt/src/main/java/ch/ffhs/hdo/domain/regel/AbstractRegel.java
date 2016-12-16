package ch.ffhs.hdo.domain.regel;

import java.nio.file.attribute.FileTime;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import ch.ffhs.hdo.domain.document.DocumentModel;

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
			return fileDate.equals(regelDate);
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
		if (date instanceof String) {

			SimpleDateFormat dateFormat = new SimpleDateFormat("DD-MM-YYYY");
			try {
				final Date parse = dateFormat.parse(regelValue);
				gregcal.setTime(parse);
			} catch (ParseException e) {
				e.printStackTrace();
			}

		}

		if (fileValue instanceof FileTime) {

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
			return matcher.matches();
		case COMPARISON_UNEQUAL:
			return !valStr.equals(this.regelValue);
		case EMPTY:
			return false;
		default:
			break;
		}
		return false;
	}

	public String getCompareValue() {
		return regelValue;
	}

	public ComparisonTypeEnum getComparisonType() {
		return comparisonType;
	}

	public ContextAttributeEnum getContextAttribute() {
		return contextAttribute;
	}

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
			// Ein Fehler in der Konfiguraiton soll nicht zum Abbruch führen.

			return false;
		}

	}

	public void setCompareToValue(Object compareToValue) {
		this.fileValue = compareToValue;
	}

	public void setCompareValue(String compareValue) {
		this.regelValue = compareValue;
	}

	public void setComparisonType(ComparisonTypeEnum comparisonType) {
		this.comparisonType = comparisonType;
	}

	public void setContextAttribute(ContextAttributeEnum contextAttribute) {
		this.contextAttribute = contextAttribute;
	}

	public void setModel(DocumentModel model) {
		this.model = model;
	}

	abstract boolean verifizieren();

}
