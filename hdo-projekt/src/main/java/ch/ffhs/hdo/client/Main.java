package ch.ffhs.hdo.client;

import java.io.File;
import java.io.IOException;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ch.ffhs.hdo.client.ui.hauptfenster.MainController;
import ch.ffhs.hdo.client.ui.hauptfenster.MainModel;
import ch.ffhs.hdo.infrastructure.ApplicationSettings;
import ch.ffhs.hdo.infrastructure.service.util.FileHandling;
import ch.ffhs.hdo.persistence.jdbc.InitDatabase;

public class Main {

	private static Logger LOGGER = LogManager.getLogger(FileHandling.class);

	public static void main(String[] args) {


		// Init Controller
		MainController mainController = new MainController(new MainModel());

		// Start MainView
		mainController.show();


	}


}