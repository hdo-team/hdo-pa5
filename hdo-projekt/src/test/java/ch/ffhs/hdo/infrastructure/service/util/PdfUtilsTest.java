package ch.ffhs.hdo.infrastructure.service.util;

import java.io.File;
import java.net.URL;
import java.util.GregorianCalendar;
import java.util.HashMap;

import org.junit.Assert;
import org.junit.Test;

import ch.ffhs.hdo.infrastructure.service.util.PdfUtils.PdfMetaData;

public class PdfUtilsTest {

	@Test
	public void testReadPdf() {

		URL url = Thread.currentThread().getContextClassLoader().getResource("TestLesenDokument.pdf");
		File file = new File(url.getPath());

		String readPDF = PdfUtils.readPDF(file);

		Assert.assertTrue(readPDF.length() > 100);
	}

	@Test
	public void testReadMetadata() {

		URL url = Thread.currentThread().getContextClassLoader().getResource("TestLesenDokument.pdf");
		File file = new File(url.getPath());

		HashMap<PdfMetaData, Object> dokumentInformation = PdfUtils.getDokumentInformation(file);

		Assert.assertEquals(new Integer(2), (Integer) dokumentInformation.get(PdfMetaData.PAGECOUNT));
		Assert.assertEquals("Microsoft® Word 2016", (String) dokumentInformation.get(PdfMetaData.CREATOR));
		Assert.assertEquals("Denis Bittante", (String) dokumentInformation.get(PdfMetaData.AUTHOR));
		Assert.assertEquals("TestThema", (String) dokumentInformation.get(PdfMetaData.SUBJECT));
		Assert.assertEquals("TestTitel", (String) dokumentInformation.get(PdfMetaData.TITLE));
		Assert.assertEquals("TestTag", (String) dokumentInformation.get(PdfMetaData.KEYWORDS));
		Assert.assertTrue(((GregorianCalendar) dokumentInformation.get(PdfMetaData.CREATION_DATE))
				.before(new GregorianCalendar()));
		Assert.assertTrue(((GregorianCalendar) dokumentInformation.get(PdfMetaData.MODIFICATION_DATE))
				.before(new GregorianCalendar()));

	}

}
