package ch.ffhs.hdo.infrastructure.service.util;

import java.io.File;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Utility um Dateien zu bearbeiten.
 * 
 * @author Denis Bittante
 *
 */
public class FileHandling {
	static Logger LOGGER = LogManager.getLogger(FileHandling.class);

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

	
}
