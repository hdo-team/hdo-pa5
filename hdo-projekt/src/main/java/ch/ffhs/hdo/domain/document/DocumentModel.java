package ch.ffhs.hdo.domain.document;

import java.io.File;
import java.util.HashMap;

import ch.ffhs.hdo.infrastructure.service.util.FileHandling;
import ch.ffhs.hdo.infrastructure.service.util.FileHandling.FileMetaData;
import ch.ffhs.hdo.infrastructure.service.util.PdfUtils;
import ch.ffhs.hdo.infrastructure.service.util.PdfUtils.PdfMetaData;

public class DocumentModel {

	File file;
	HashMap<FileMetaData, Object> fileMetadata;
	HashMap<PdfMetaData, Object> pdfMetadata;
	String content;

	public DocumentModel(File file) {

		this.file = file;
		fileMetadata = FileHandling.getFileMetaData(file);
		pdfMetadata = PdfUtils.getDokumentInformation(file);
		this.content = PdfUtils.readPDF(file);

	}

}
