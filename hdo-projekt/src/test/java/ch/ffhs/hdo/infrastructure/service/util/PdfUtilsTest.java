package ch.ffhs.hdo.infrastructure.service.util;

import java.io.File;
import java.net.URL;
import java.util.GregorianCalendar;
import java.util.HashMap;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import ch.ffhs.hdo.domain.regel.ContextAttributeEnum;

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

		HashMap<ContextAttributeEnum, Object> dokumentInformation = PdfUtils.getDocumentInformation(file);

		Assert.assertEquals(new Integer(2), (Integer) dokumentInformation.get(ContextAttributeEnum.PDF_PAGECOUNT));
		Assert.assertEquals("Microsoft® Word 2016", (String) dokumentInformation.get(ContextAttributeEnum.PDF_CREATOR));
		Assert.assertEquals("Denis Bittante", (String) dokumentInformation.get(ContextAttributeEnum.PDF_AUTHOR));
		Assert.assertEquals("TestThema", (String) dokumentInformation.get(ContextAttributeEnum.PDF_SUBJECT));
		Assert.assertEquals("TestTitel", (String) dokumentInformation.get(ContextAttributeEnum.PDF_TITLE));
		Assert.assertEquals("TestTag", (String) dokumentInformation.get(ContextAttributeEnum.PDF_KEYWORDS));
		Assert.assertTrue(((GregorianCalendar) dokumentInformation.get(ContextAttributeEnum.PDF_CREATION_DATE))
				.before(new GregorianCalendar()));
		Assert.assertTrue(((GregorianCalendar) dokumentInformation.get(ContextAttributeEnum.PDF_MODIFICATION_DATE))
				.before(new GregorianCalendar()));

	}

}
