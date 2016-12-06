package ch.ffhs.hdo.domain.document;

import java.io.File;
import java.util.HashMap;

import ch.ffhs.hdo.infrastructure.service.util.FileHandling;
import ch.ffhs.hdo.infrastructure.service.util.FileHandling.FileMetaData;
import ch.ffhs.hdo.infrastructure.service.util.PdfUtils;
import ch.ffhs.hdo.infrastructure.service.util.PdfUtils.PdfMetaData;

public class DocumentModel {

	private String content;
	private File file;
	private HashMap<FileMetaData, Object> fileMetadata;
	private HashMap<PdfMetaData, Object> pdfMetadata;

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

	public HashMap<FileMetaData, Object> getFileMetadata() {
		return fileMetadata;
	}

	public HashMap<PdfMetaData, Object> getPdfMetadata() {
		return pdfMetadata;
	}

}
