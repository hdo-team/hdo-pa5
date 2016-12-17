package ch.ffhs.hdo.client.ui.imports.executable;

import java.io.File;
import java.io.IOException;

import org.rauschig.jarchivelib.Archiver;
import org.rauschig.jarchivelib.ArchiverFactory;

import ch.ffhs.hdo.client.ui.base.viewhandler.Executable;
import ch.ffhs.hdo.client.ui.imports.ImportModel;
import ch.ffhs.hdo.infrastructure.ApplicationSettings;

/**
 * Speichert die Konfigurationen der importierten Daten in das Verzeichnis.
 * 
 * @author Adrian Perez Rodriguez
 *
 */
public class ImportSaveExecutable implements Executable<ImportModel> {

	private ImportModel model;

	/**
	 * Konstruktor zur erstellung des Objekts.
	 * 
	 * @param model
	 *            Model, von welchem der Inboxpfad hinterlegt ist..
	 */
	public ImportSaveExecutable(ImportModel model) {
		this.model = model;

	}

	/**
	 * Daten aus der Importdatei werden in das Verzeichnis gespeichert.
	 */
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
			/**
			* 
			* Falls ein falsches Fileformat (kein .gz) ausgewählt wird, gibts hier die Exception
			* und der Benutzer erhält einen Hinweis, dass der Importvorgang erfolgreich war...
			* 
			* Entweder bei der Fileauswahl checken ob es ein .gz Format ist, oder einen return-Value an die View
			* mitgeben, damit dort dann dementsprechend der Dialog angezeigt wird.
			* 
			*/
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}