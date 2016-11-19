package ch.ffhs.hdo.infrastructure.service.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.DirectoryFileFilter;
import org.apache.commons.io.filefilter.RegexFileFilter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Utility um Dateien zu bearbeiten.
 * 
 * @author Denis Bittante
 *
 */
public class FileHandling {
	private static Logger LOGGER = LogManager.getLogger(FileHandling.class);

	/**
	 * Verschiebt eine Datei von filePath nach new Location
	 *
	 */
	public static void moveFile(String filePath, String newLocation) {

		try {
			File moveto = new File(newLocation);

			if (!moveto.isDirectory()) {
				moveto = new File(moveto.getAbsolutePath() + File.separator);
			}

			if (!moveto.exists()) {
				LOGGER.debug("Make Directory: " + moveto.getAbsolutePath().toString());
				moveto.mkdirs();
			}
			if (!moveto.canWrite()) {
				LOGGER.error("Can Not Write in Directory: " + moveto.getAbsolutePath().toString());
				throw new SecurityException("File kann nicht geschrieben werden weil das Verzeichnis gesperrt ist.");
			}

			File afile = new File(filePath);

			File dest = new File(newLocation + afile.getName());
			if (dest.exists()) {
				LOGGER.warn("File " + dest.getAbsolutePath() + " Already Exists in Directory: "
						+ moveto.getAbsolutePath().toString());

				int i = 1;
				File destnew = dest;
				while (destnew.exists()) {

					i++;
					String[] name = dest.getName().split("\\.(?=[^\\.]+$)");
					destnew = new File(destnew.getParent() + File.separator + name[0] + "(" + i + ")." + name[1]);

					LOGGER.debug("Try Rename File " + dest.getAbsolutePath() + " to  " + destnew.getName());
				}
				dest = destnew;
			}

			if (afile.renameTo(dest)) {
				LOGGER.debug("File: " + afile.getName() + " moved from [" + filePath + "] to [" + newLocation + "]");
			} else {
				System.out.println("File [" + filePath + "]is failed to move to [" + newLocation + "]!");
			}

		} catch (Exception e) {
			LOGGER.error("Ein Fehler ist beim Verschieben aufgetreten !", e);
		}

	}

	public static void deleteFolder(File folder) {
		File[] files = folder.listFiles();
		if (files != null) { // some JVMs return null for empty dirs
			for (File f : files) {
				if (f.isDirectory()) {
					deleteFolder(f);
				} else {
					f.delete();
				}
			}
		}
		folder.delete();
	}

	public static void deleteFile(File file) {
		LOGGER.debug("File :" + file.getAbsolutePath() + "was deleted");
		file.delete();
	}

	/**
	 * Eigenschaften eines Files
	 */
	public static enum FileMetaData {

		CREATION_TIME, LAST_ACCESS_TIME, LAST_MODIFICATION_TIME, SIZE;
	}

	/**
	 * Liefert die Metadaten einer Datei zurück.
	 * 
	 * @param file
	 *            das zu analysierende File
	 * @return MashMap mit Key die {@link FileMetaData} mit deren Objekten
	 */
	public static HashMap<FileMetaData, Object> getFileMetaData(File file) {

		BasicFileAttributes attr;
		try {
			attr = Files.readAttributes(file.toPath(), BasicFileAttributes.class);

			HashMap<FileMetaData, Object> metadata = new HashMap<FileHandling.FileMetaData, Object>();

			metadata.put(FileMetaData.CREATION_TIME, attr.creationTime());
			metadata.put(FileMetaData.LAST_ACCESS_TIME, attr.lastAccessTime());
			metadata.put(FileMetaData.LAST_MODIFICATION_TIME, attr.lastModifiedTime());
			metadata.put(FileMetaData.SIZE, attr.size());

			return metadata;
		} catch (IOException e) {
			LOGGER.error("Metadaten einer Datei konnten nicht geleasen werden ", e);
		}
		return null;
	}

	public static Collection<File> getFileList(String inboxPath, boolean rekursiv) {
		File folder = new File(inboxPath);

		Collection<File> files;
		if (rekursiv) {

			files = FileUtils.listFiles(folder, new RegexFileFilter("^(.*?)"), DirectoryFileFilter.DIRECTORY);

		} else {

			File[] listFiles = folder.listFiles();
			files = new ArrayList<File>(Arrays.asList(listFiles));

		}

		return files;

	}

}
