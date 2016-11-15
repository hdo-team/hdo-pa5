package ch.ffhs.hdo.client;

import ch.ffhs.hdo.client.ui.einstellungen.OptionController;
import ch.ffhs.hdo.client.ui.einstellungen.OptionModel;
import ch.ffhs.hdo.client.ui.export.ExportController;
import ch.ffhs.hdo.client.ui.export.ExportModel;
import ch.ffhs.hdo.client.ui.hauptfenster.MainController;
import ch.ffhs.hdo.client.ui.hauptfenster.MainModel;

public class Main {

	public static void main(String[] args) {

		// Init Controller
		MainController mainController = new MainController(new MainModel());
		OptionController optionController = new OptionController(new OptionModel());
		ExportController exportController = new ExportController(new ExportModel());
		// Start MainView

		mainController.show();
		optionController.show();
		exportController.show();
	

	}

}
