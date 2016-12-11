package ch.ffhs.hdo.domain.document;

import java.io.File;
import java.util.HashMap;

import ch.ffhs.hdo.domain.regel.ContextAttributeEnum;
import ch.ffhs.hdo.infrastructure.service.util.FileHandling;
import ch.ffhs.hdo.infrastructure.service.util.PdfUtils;

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

	public String getContent() {
		return content;
	}

	public File getFile() {
		return file;
	}

	public HashMap<ContextAttributeEnum, Object> getFileMetadata() {
		return fileMetadata;
	}

	public HashMap<ContextAttributeEnum, Object> getPdfMetadata() {
		return pdfMetadata;
	}

}
