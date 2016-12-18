package ch.ffhs.hdo.infrastructure.service.util;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.io.RandomAccessFile;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentInformation;
import org.apache.pdfbox.text.PDFTextStripper;

import ch.ffhs.hdo.domain.regel.ContextAttributeEnum;

/**
 * Utility um PDF Eigenschaften zu lesen.
 * 
 * @author Denis Bittante
 *
 */
public class PdfUtils {

	static Logger LOGGER = LogManager.getLogger(PdfUtils.class);

	/**
	 * Liefert den Inhalte eines Pdfs als Unicode zurueck.
	 * 
	 * @param file
	 * @return Inhalt als Unicode
	 */
	public static String readPDF(File file) {

		PDFTextStripper pdfStripper = null;
		PDDocument pdDoc = null;
		COSDocument cosDoc = null;
		RandomAccessFile source = null;
		try {
			source = new RandomAccessFile(file, "r");
			PDFParser parser = new PDFParser(source);
			parser.parse();
			cosDoc = parser.getDocument();
			pdfStripper = new PDFTextStripper();
			pdDoc = new PDDocument(cosDoc);

			String parsedText = pdfStripper.getText(pdDoc);
			source.close();
			return parsedText;
		} catch (IOException e) {
			LOGGER.error("Pdf konnte nicht gelesen werden ", e);
		} finally {
			try {
				cosDoc.close();
				pdDoc.close();
				IOUtils.closeQuietly(source);

			} catch (IOException e) {
				// do nothing - PDF bereits geschlossen
			}
		}
		return null;

	}

	/**
	 * Liefert die Metadaten eines PDFs zurueck.
	 * 
	 * @param file
	 * @return MashMap mit Key die {@link PdfMetaData} mit deren Objekten
	 */
	public static HashMap<ContextAttributeEnum, Object> getDocumentInformation(File file) {

		PDDocument pdDoc = null;
		COSDocument cosDoc = null;
		RandomAccessFile source = null;
		try {
			source  = new RandomAccessFile(file, "r");
			PDFParser parser = new PDFParser(source);
			parser.parse();
			cosDoc = parser.getDocument();
			pdDoc = new PDDocument(cosDoc);

			PDDocumentInformation info = pdDoc.getDocumentInformation();

			HashMap<ContextAttributeEnum, Object> metaData = new HashMap<ContextAttributeEnum, Object>();

			metaData.put(ContextAttributeEnum.PDF_PAGECOUNT, pdDoc.getNumberOfPages());
			metaData.put(ContextAttributeEnum.PDF_TITLE, info.getTitle());
			metaData.put(ContextAttributeEnum.PDF_AUTHOR, info.getAuthor());
			metaData.put(ContextAttributeEnum.PDF_SUBJECT, info.getSubject());
			metaData.put(ContextAttributeEnum.PDF_KEYWORDS, info.getKeywords());
			metaData.put(ContextAttributeEnum.PDF_CREATOR, info.getCreator());
			metaData.put(ContextAttributeEnum.PDF_PRODUCER, info.getProducer());
			metaData.put(ContextAttributeEnum.PDF_CREATION_DATE, info.getCreationDate());
			metaData.put(ContextAttributeEnum.PDF_MODIFICATION_DATE, info.getModificationDate());

			return metaData;
		} catch (Exception e) {
			LOGGER.error("Fehler beim lesen von PDF Eigenschaften", e);
		} finally {
			try {
				cosDoc.close();
				pdDoc.close();
				IOUtils.closeQuietly(source);

				} catch (IOException e) {
				// do nothing - pdf already closeds
			}
		}
		return null;

	}
}
