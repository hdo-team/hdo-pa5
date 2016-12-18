package ch.ffhs.hdo.domain.document;

import java.io.File;
import java.util.HashMap;

import ch.ffhs.hdo.domain.regel.ContextAttributeEnum;
import ch.ffhs.hdo.infrastructure.service.util.FileHandling;
import ch.ffhs.hdo.infrastructure.service.util.PdfUtils;

/**
 * DocumentModel ist die Representation aller Informationen die vom Service
 * geprueft werden
 * 
 * @author Denis Bittante
 *
 */
public class DocumentModel {

	private File file;
	private String content;
	private HashMap<ContextAttributeEnum, Object> fileMetadata;
	private HashMap<ContextAttributeEnum, Object> pdfMetadata;

	public DocumentModel(File file) {

		this.file = file;
		this.fileMetadata = FileHandling.getFileMetaData(file);
		this.pdfMetadata = PdfUtils.getDocumentInformation(file);
		this.content = PdfUtils.readPDF(file);

	}

	/**
	 * Retourniert den Inhalt eines PDFs
	 * @return Pdf Inhalt
	 */
	public String getContent() {
		return content;
	}

	/**
	 * Retourniert das File
	 * @return Pdf als File
	 */
	public File getFile() {
		return file;
	}

	/**
	 * Gibt die Metadaten von eines Files zurueck
	 *
	 * @return HashMap von Attributen und den Values
	 */
	public HashMap<ContextAttributeEnum, Object> getFileMetadata() {
		return fileMetadata;
	}
	/**
	 * Gibt die Metadaten von eines PDFs zurueck
	 *
	 * @return HashMap von Attributen und den Values
	 */
	public HashMap<ContextAttributeEnum, Object> getPdfMetadata() {
		return pdfMetadata;
	}

}
