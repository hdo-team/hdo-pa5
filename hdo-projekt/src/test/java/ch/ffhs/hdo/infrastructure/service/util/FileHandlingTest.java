package ch.ffhs.hdo.infrastructure.service.util;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.attribute.FileTime;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import ch.ffhs.hdo.domain.regel.ContextAttributeEnum;

/**
 * Testet den FileHandler
 * 
 * @author Denis Bittante
 *
 */

public class FileHandlingTest {

	/**
	 * Init Testee
	 * 
	 * @throws Exception
	 */
	@Before
	public void setUpBeforeClass() throws Exception {

		// Use relative path for Unix systems
		File f = new File(getFilePath());

		f.getParentFile().mkdirs();
		f.createNewFile();

	}

	/**
	 * Abraeumen
	 * 
	 * @throws Exception
	 */
	@After
	public void tearDownAfterTest() throws Exception {

		FileHandling.deleteFolder(new File(getMoveToPath()));
	}

	/**
	 * Testet ob man ein File verschieben kann
	 */
	@Test
	public void testMoveFile() {
		File path = new File(getMovedFile());
		FileHandling.moveFile(getFilePath(), getMoveToPath());
		Assert.assertTrue(path.exists());

	}

	/**
	 * Testet mit mehreren Files ob diese neue Namen besitzen mit (n) ald
	 * Postfix
	 * 
	 * @throws IOException
	 */
	@Test
	public void testSeveralFile() throws IOException {
		int amout = 20;
		for (int i = 0; i < amout; i++) {

			File file = new File(getFilePath());
			file.createNewFile();

			FileHandling.moveFile(getFilePath(), getMoveToPath());
		}

		File[] listFiles = new File(getMoveToPath()).listFiles();
		int i = 0;
		for (File f : listFiles) {
			i++;
		}
		Assert.assertEquals(amout, i);

	}

	/**
	 * Testet Metadaten extraction
	 */
	@Test
	public void testFileMetadaten() {

		URL url = Thread.currentThread().getContextClassLoader().getResource("TestLesenDokument.pdf");
		File file = new File(url.getPath());

		HashMap<ContextAttributeEnum, Object> fileMetadaten = FileHandling.getFileMetaData(file);

		Assert.assertEquals(new Long(420100), (Long) fileMetadaten.get(ContextAttributeEnum.FILE_SIZE));
		Assert.assertTrue(((FileTime) fileMetadaten.get(ContextAttributeEnum.FILE_CREATION_DATE))
				.toMillis() < new Date().getTime());
	}

	/**
	 * Testet ob die Methode alle Subfolders zurückgibt
	 */
	@Test
	public void testRekursivFolders() {
		final String rootDir = getPath();

		final List<String> underRootFolders = FileHandling.getAllFolders(rootDir);
		File f = new File(rootDir + File.separator + "TestDirectory" + File.separator);
		f.mkdir();
		for (String string : underRootFolders) {

			Assert.assertTrue(new File(string).isDirectory());
			System.out.println(string);
		}
	}

	private static String getPath() {
		final String rootDir;
		if (isWindows()) {
			rootDir = "C:\\temp\\";
		} else {
			rootDir = "~";
		}
		return rootDir;
	}

	private static boolean isWindows() {
		final String property = System.getProperty("os.name");
		return property.toLowerCase().contains("windows");
	}

	private static String getMoveToPath() {
		return getPath() + "toMove" + File.separator;
	}

	private String getMovedFile() {
		return getMoveToPath() + "FileHandlingTest.txt";
	}

	private String getFilePath() {
		return getPath() + "FileHandlingTest.txt";
	}
}
