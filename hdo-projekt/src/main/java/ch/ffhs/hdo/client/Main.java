package ch.ffhs.hdo.client;

import ch.ffhs.hdo.client.ui.einstellungen.OptionController;
import ch.ffhs.hdo.client.ui.einstellungen.OptionModel;
import ch.ffhs.hdo.client.ui.export.ExportController;
import ch.ffhs.hdo.client.ui.export.ExportModel;
import ch.ffhs.hdo.client.ui.hauptfenster.MainController;
import ch.ffhs.hdo.client.ui.hauptfenster.MainModel;
import ch.ffhs.hdo.client.ui.imports.ImportController;
import ch.ffhs.hdo.client.ui.imports.ImportModel;
import ch.ffhs.hdo.client.ui.regelset.RegelsetController;
import ch.ffhs.hdo.client.ui.regelset.RegelsetModel;
import ch.ffhs.hdo.infrastructure.ApplicationSettings;
import ch.ffhs.hdo.persistence.jdbc.InitDatabase;

public class Main {

	public static void main(String[] args) {

		ApplicationSettings.getInstance().setInbox_path("C:/temp/db_file");

		InitDatabase initDatabase = new InitDatabase();

		// Init Controller
		MainController mainController = new MainController(new MainModel());
		ExportController exportController = new ExportController(new ExportModel());
		RegelsetController regelsetController = new RegelsetController(new RegelsetModel());
		ImportController importController = new ImportController(new ImportModel());

		// Start MainView
		mainController.show();

		// Start ExportView
		exportController.show();

		// Start RegelsetView
		regelsetController.show();

		// Start ImportView
		// Bitte zum Testen die config.properties im 'resources' Ordner wo das
		// resourceBundle auswählen...
		importController.show();
	}
}