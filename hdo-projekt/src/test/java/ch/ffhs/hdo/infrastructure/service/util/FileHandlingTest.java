package ch.ffhs.hdo.infrastructure.service.util;

import java.io.File;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class FileHandlingTest {

	final static String PATH = "C:" + File.separator + "temp" + File.separator;

	final static String MOVE_TO_PATH = PATH + "toMove" + File.separator;
	final static String MOVED_FILE = MOVE_TO_PATH + "FileHandlingTest.txt";
	final static String FILEPATH = PATH + "FileHandlingTest.txt";

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {

		// Use relative path for Unix systems
		File f = new File(FILEPATH);

		f.getParentFile().mkdirs();
		f.createNewFile();

	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {

	FileHandling.deleteFolder(new File(MOVE_TO_PATH));
	}

	@Test
	public void testMoveFile() {
		File path = new File(MOVED_FILE);
		FileHandling.moveFile(FILEPATH, MOVE_TO_PATH);
		Assert.assertTrue(path.exists());

	}

}
