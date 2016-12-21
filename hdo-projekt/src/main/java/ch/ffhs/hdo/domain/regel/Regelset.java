package ch.ffhs.hdo.domain.regel;

import java.io.File;
import java.nio.file.attribute.FileTime;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.apache.commons.lang.time.DateFormatUtils;

import ch.ffhs.hdo.domain.document.DocumentModel;

/**
 * 
 * @author Denis Bittante
 *
 */
public class Regelset {

	private List<AbstractRegel> regeln;
	private String path;
	private long filenameCounter;
	private String renamePattern;

	public long getFilenameCounter() {
		return filenameCounter;
	}

	public void setFilenameCounter(long filenameCounter) {
		this.filenameCounter = filenameCounter;
	}

	public String getRenamePattern() {
		return renamePattern;
	}

	public void setRenamePattern(String renamePattern) {
		this.renamePattern = renamePattern;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public void setRegeln(List<AbstractRegel> regeln) {
		this.regeln = regeln;
	}

	public boolean verfizieren(DocumentModel model) {

		boolean result = false;
		for (AbstractRegel regel : regeln) {
			regel.setModel(model);
			final boolean verifizieren = regel.verifizieren();
			result = verifizieren;
			if (!verifizieren) {
				return false;
			}

		}
		return result;
	}

	public String rename(DocumentModel documentModel) {

		final Object fileowner = documentModel.getFileMetadata().get(ContextAttributeEnum.FILE_OWNER);
		final Object creationDate = documentModel.getFileMetadata().get(ContextAttributeEnum.FILE_CREATION_DATE);
		final Object filename = documentModel.getFileMetadata().get(ContextAttributeEnum.FILE_NAME);

		final String replaceAll = renamePattern
				.replaceAll("%FILE_CREATION_DATE%", creationDate == null ? "" : formatFileDate((FileTime) creationDate))
				.replaceAll("%FILE_NAME%", filename == null ? "" : filename.toString())
				.replaceAll("%FILE_OWNER%", fileowner == null ? "" : fileowner.toString());
		return replaceAll;

	}

	private String formatFileDate(final FileTime creationDate) {

		final String formatUTC = DateFormatUtils.formatUTC(new Date(creationDate.toMillis()),
				DateFormatUtils.ISO_DATE_FORMAT.getPattern(), Locale.GERMAN);

		return formatUTC;
	}

	public String getPath() {

		if (!this.path.endsWith("\\")) {
			return this.path + File.separator;

		}

		return this.path;
	}

}
