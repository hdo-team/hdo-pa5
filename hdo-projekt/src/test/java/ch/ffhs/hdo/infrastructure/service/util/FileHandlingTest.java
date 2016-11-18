package ch.ffhs.hdo.infrastructure.service.util;

import java.io.File;
import java.io.IOException;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class FileHandlingTest {

	final static String PATH = "C:" + File.separator + "temp" + File.separator;

	final static String MOVE_TO_PATH = PATH + "toMove" + File.separator;
	final static String MOVED_FILE = MOVE_TO_PATH + "FileHandlingTest.txt";
	final static String FILEPATH = PATH + "FileHandlingTest.txt";

	@Before
	public void setUpBeforeClass() throws Exception {

		// Use relative path for Unix systems
		File f = new File(FILEPATH);

		f.getParentFile().mkdirs();
		f.createNewFile();

	}

	@After
	public void tearDownAfterTest() throws Exception {

		FileHandling.deleteFolder(new File(MOVE_TO_PATH));
	}

	@Test
	public void testMoveFile() {
		File path = new File(MOVED_FILE);
		FileHandling.moveFile(FILEPATH, MOVE_TO_PATH);
		Assert.assertTrue(path.exists());

	}

	@Test
	public void testSeveralFile() throws IOException {
		int amout = 20;
		for (int i = 0; i < amout; i++) {

			File file = new File(FILEPATH);
			file.createNewFile();

			FileHandling.moveFile(FILEPATH, MOVE_TO_PATH);
		}

		File[] listFiles = new File(MOVE_TO_PATH).listFiles();
		int i = 0;
		for (File f : listFiles) {
			i++;
		}
		Assert.assertEquals(amout, i);

	}

}
