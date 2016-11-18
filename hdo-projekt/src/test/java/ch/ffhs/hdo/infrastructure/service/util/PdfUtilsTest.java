package ch.ffhs.hdo.infrastructure.service.util;

import java.io.File;
import java.net.URL;

import org.junit.Assert;
import org.junit.Test;

public class PdfUtilsTest {

	@Test
	public void testReadPdf() {

		URL url = Thread.currentThread().getContextClassLoader().getResource("TestLesenDokument.pdf");
		File file = new File(url.getPath());

		String readPDF = PdfUtils.readPDF(file);

		Assert.assertTrue(readPDF.length() > 100);
	}

}
