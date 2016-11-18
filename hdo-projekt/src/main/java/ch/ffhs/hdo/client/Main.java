package ch.ffhs.hdo.client;

import ch.ffhs.hdo.client.ui.hauptfenster.MainController;
import ch.ffhs.hdo.client.ui.hauptfenster.MainModel;
import ch.ffhs.hdo.infrastructure.ApplicationSettings;
import ch.ffhs.hdo.persistence.jdbc.InitDatabase;

public class Main {

	public static void main(String[] args) {

		ApplicationSettings.getInstance().setInbox_path("C:/temp/db_file");

		InitDatabase initDatabase = new InitDatabase();

		// Init Controller
		MainController mainController = new MainController(new MainModel());
		

		// Start MainView
		mainController.show();

		// Bitte zum Testen die config.properties im 'resources' Ordner wo das
		// resourceBundle auswählen...
		//importController.show();
	}
}