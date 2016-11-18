package ch.ffhs.hdo.infrastructure.service.util;

import java.io.File;
import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.io.RandomAccessFile;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

public class PdfUtils {

	static Logger LOGGER = LogManager.getLogger(PdfUtils.class);

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

}
