package ch.ffhs.hdo.client;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ch.ffhs.hdo.client.ui.hauptfenster.MainController;
import ch.ffhs.hdo.client.ui.hauptfenster.MainModel;
import ch.ffhs.hdo.infrastructure.service.util.FileHandling;

public class Main {

	private static Logger LOGGER = LogManager.getLogger(FileHandling.class);

	public static void main(String[] args) {

		
		//init StartExecutableMainView
		
		

		// Init Controller
		MainController mainController = new MainController(new MainModel());

		// Start MainView
		mainController.show();


	}


}