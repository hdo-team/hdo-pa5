package ch.ffhs.hdo.client.ui.imports.executable;

import java.io.File;
import java.io.IOException;

import org.rauschig.jarchivelib.Archiver;
import org.rauschig.jarchivelib.ArchiverFactory;

import ch.ffhs.hdo.client.ui.base.viewhandler.Executable;
import ch.ffhs.hdo.client.ui.imports.ImportModel;
import ch.ffhs.hdo.infrastructure.ApplicationSettings;

public class ImportSaveExecutable implements Executable<ImportModel> {

	private ImportModel model;

	public ImportSaveExecutable(ImportModel model) {
		this.model = model;

	}

	public void execute(ImportModel arg) {
		
		String inboxPath = ApplicationSettings.getInstance().getInbox_path() + "/.db";
		
		// Importdatei und Zielverzeichnis angeben
		File archive = new File(model.getFilePath());
		File destination = new File(inboxPath);
	
		// Importdatei entpacken und in Ordner verschieben
		Archiver archiver = ArchiverFactory.createArchiver("tar", "gz");
		try {
			archiver.extract(archive, destination);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}