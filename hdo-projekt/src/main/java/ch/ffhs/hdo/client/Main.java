package ch.ffhs.hdo.client;

import javax.swing.JFrame;

import ch.ffhs.hdo.client.ui.einstellungen.OptionController;
import ch.ffhs.hdo.client.ui.einstellungen.OptionModel;
import ch.ffhs.hdo.client.ui.export.ExportController;
import ch.ffhs.hdo.client.ui.export.ExportModel;
import ch.ffhs.hdo.client.ui.regelset.RegelsetController;
import ch.ffhs.hdo.client.ui.regelset.RegelsetModel;

public class Main {

	public static void main(String[] args) {

		// Init Controller
		OptionController optionController = new OptionController(new OptionModel());
		ExportController exportController = new ExportController(new ExportModel());
		RegelsetController regelsetController = new RegelsetController(new RegelsetModel());
		
		// Start MainView
		optionController.show();
		exportController.show();
		regelsetController.show();
	}

}
