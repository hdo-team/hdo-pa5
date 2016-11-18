package ch.ffhs.hdo.infrastructure.service.util;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.io.RandomAccessFile;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentInformation;
import org.apache.pdfbox.text.PDFTextStripper;

/**
 * Utility um PDF Eigenschaften zu lesen.
 * 
 * @author Denis Bittante
 *
 */
public class PdfUtils {

	static Logger LOGGER = LogManager.getLogger(PdfUtils.class);

	/**
	 * Liefert den Inhalte eines Pdfs als Unicode zurück.
	 * 
	 * @param file
	 * @return Inhalt als Unicode
	 */
	public static String readPDF(File file) {

		PDFTextStripper pdfStripper = null;
		PDDocument pdDoc = null;
		COSDocument cosDoc = null;

		try {
			PDFParser parser = new PDFParser(new RandomAccessFile(file, "r"));
			parser.parse();
			cosDoc = parser.getDocument();
			pdfStripper = new PDFTextStripper();
			pdDoc = new PDDocument(cosDoc);

			String parsedText = pdfStripper.getText(pdDoc);
			return parsedText;
		} catch (IOException e) {
			LOGGER.error("Pdf konnte nicht gelesen werden ", e);
		}
		return null;

	}

	/**
	 * Eigenschaften eines PDFs
	 */
	public static enum PdfMetaData {

		PAGECOUNT, TITLE, AUTHOR, SUBJECT, KEYWORDS, CREATOR, PRODUCER, CREATION_DATE, MODIFICATION_DATE;
	}

	/**
	 * Liefert die Metadaten eines PDFs zurück.
	 * 
	 * @param file
	 * @return MashMap mit Key die {@link PdfMetaData} mit deren Objekten
	 */
	public static HashMap<PdfMetaData, Object> getDokumentInformation(File file) {

		PDDocument pdDoc = null;
		COSDocument cosDoc = null;

		try {
			PDFParser parser = new PDFParser(new RandomAccessFile(file, "r"));
			parser.parse();
			cosDoc = parser.getDocument();
			pdDoc = new PDDocument(cosDoc);

			PDDocumentInformation info = pdDoc.getDocumentInformation();

			HashMap<PdfMetaData, Object> metaData = new HashMap<PdfMetaData, Object>();

			metaData.put(PdfMetaData.PAGECOUNT, pdDoc.getNumberOfPages());
			metaData.put(PdfMetaData.TITLE, info.getTitle());
			metaData.put(PdfMetaData.AUTHOR, info.getAuthor());
			metaData.put(PdfMetaData.SUBJECT, info.getSubject());
			metaData.put(PdfMetaData.KEYWORDS, info.getKeywords());
			metaData.put(PdfMetaData.CREATOR, info.getCreator());
			metaData.put(PdfMetaData.PRODUCER, info.getProducer());
			metaData.put(PdfMetaData.CREATION_DATE, info.getCreationDate());
			metaData.put(PdfMetaData.MODIFICATION_DATE, info.getModificationDate());

			return metaData;
		} catch (Exception e) {
			LOGGER.error("Fehler beim lesen von PDF Eigenschaften", e);
		}
		return null;

	}
}
