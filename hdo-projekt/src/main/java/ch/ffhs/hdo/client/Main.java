package ch.ffhs.hdo.client;

import ch.ffhs.hdo.client.ui.hauptfenster.MainController;
import ch.ffhs.hdo.client.ui.hauptfenster.MainModel;
import ch.ffhs.hdo.client.ui.einstellungen.OptionController;
import ch.ffhs.hdo.client.ui.einstellungen.OptionModel;
import ch.ffhs.hdo.client.ui.export.ExportController;
import ch.ffhs.hdo.client.ui.export.ExportModel;
import ch.ffhs.hdo.client.ui.imports.ImportController;
import ch.ffhs.hdo.client.ui.imports.ImportModel;

public class Main {

	public static void main(String[] args) {

		// Init Controller
		MainController mainController = new MainController(new MainModel());
		OptionController optionController = new OptionController(new OptionModel());
		ExportController exportController = new ExportController(new ExportModel());
		ImportController importController = new ImportController(new ImportModel());
		
		// Start MainView
		mainController.show();
		
		// Start OptionView
		optionController.show();
		
		// Start ExportView
		exportController.show();
		
		// Start ImportView
		// Bitte zum Testen die config.properties im 'resources' Ordner wo das resourceBundle auswählen...
		importController.show();
	}
}