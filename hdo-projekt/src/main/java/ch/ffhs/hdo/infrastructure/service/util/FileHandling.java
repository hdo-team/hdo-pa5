package ch.ffhs.hdo.infrastructure.service.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.DirectoryFileFilter;
import org.apache.commons.io.filefilter.NotFileFilter;
import org.apache.commons.io.filefilter.RegexFileFilter;
import org.apache.commons.io.filefilter.TrueFileFilter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ch.ffhs.hdo.domain.regel.ContextAttributeEnum;

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

			File file = new File(filePath);

			File dest = new File(newLocation + file.getName());
			if (dest.exists()) {
				LOGGER.warn("File " + dest.getAbsolutePath() + " Already Exists in Directory: "
						+ moveto.getAbsolutePath().toString());

				int i = 1;
				File destnew = dest;
				while (destnew.exists()) {

					i++;
					String[] name = getExtension(dest);
					destnew = new File(destnew.getParent() + File.separator + name[0] + "(" + i + ")." + name[1]);

					LOGGER.debug("Try Rename File " + dest.getAbsolutePath() + " to  " + destnew.getName());
				}
				dest = destnew;
			}
			FileUtils.moveFile(file, dest);
			LOGGER.debug("File: " + file.getName() + " moved from [" + filePath + "] to [" + newLocation + "]");

		} catch (Exception e) {
			LOGGER.error("File [" + filePath + "]is failed to move to [" + newLocation + "]!");
			LOGGER.error("Ein Fehler ist beim Verschieben aufgetreten !", e);
		}

	}

	private static String[] getExtension(File dest) {
		String[] name = dest.getName().split("\\.(?=[^\\.]+$)");
		return name;
	}

	/**
	 * 
	 * Verzeichnis löschen
	 * 
	 * @param folder
	 *            zu löschende Verzeichnis
	 */
	public static void deleteFolder(File folder) {
		File[] files = folder.listFiles();
		if (files != null) { // some JVMs return null for empty dirs
			for (File f : files) {
				if (f.isDirectory()) {
					deleteFolder(f);
					LOGGER.debug("File: " + f.getName() + " deleted");

				} else {
					f.delete();
				}
			}
		}
		folder.delete();
	}

	/**
	 * File löschen
	 * 
	 * @param file
	 *            zu löschende File
	 */
	public static void deleteFile(File file) {
		LOGGER.debug("File :" + file.getAbsolutePath() + "was deleted");
		file.delete();
	}

	/**
	 * Liefert die Metadaten einer Datei zurück.
	 * 
	 * @param file
	 *            das zu analysierende File
	 * @return MashMap mit Key die {@link FileMetaData} mit deren Objekten
	 */
	public static HashMap<ContextAttributeEnum, Object> getFileMetaData(File file) {

		HashMap<ContextAttributeEnum, Object> metadata = new HashMap<ContextAttributeEnum, Object>();
		try {
			BasicFileAttributes attr;
			attr = Files.readAttributes(file.toPath(), BasicFileAttributes.class);

			metadata.put(ContextAttributeEnum.FILE_CREATION_DATE, attr.creationTime());
			final String fileEnding = getExtension(file)[1];
			metadata.put(ContextAttributeEnum.FILE_EXTENSION, fileEnding);
			metadata.put(ContextAttributeEnum.FILE_NAME, file.getName().substring(0, file.getName().length()-fileEnding.length()));
			metadata.put(ContextAttributeEnum.FILE_SIZE, attr.size());

		} catch (IOException e) {
			LOGGER.error("Metadaten einer Datei konnten nicht geleasen werden ", e);
		}
		return metadata;
	}

	/**
	 * 
	 * Gibt eine Collection von Files zurück die gefuden wurden
	 * 
	 * @param inboxPath
	 *            das zu durchsuchende Verzeichnis
	 * @param rekursive
	 *            true um Rekursiv in allen Unterverzeichnis zu suchen
	 * @return Collection von allen gefundenn Files
	 */
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

	/**
	 * Gibt alle Vezeichnisse aus einem Verzeichnis zurück
	 * 
	 * @param rootDir
	 *            das zu durchsuchende Verzeichnis
	 * @return Liste von Pfaden die gefunden wurden
	 */
	public static List<String> getAllFolders(String rootDir) {

		final Collection<File> listFilesAndDirs = FileUtils.listFilesAndDirs(new File(rootDir),
				new NotFileFilter(TrueFileFilter.INSTANCE), DirectoryFileFilter.DIRECTORY);
		ArrayList<String> folders = new ArrayList<String>();

		for (File folder : listFilesAndDirs) {

			if (!folder.getName().startsWith(".")) {
				folders.add(folder.getAbsolutePath());

			}

		}
		return folders;

	}

}
