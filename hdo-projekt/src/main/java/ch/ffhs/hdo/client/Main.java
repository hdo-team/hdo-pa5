package ch.ffhs.hdo.client;

import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ch.ffhs.hdo.client.ui.hauptfenster.MainController;
import ch.ffhs.hdo.client.ui.hauptfenster.MainModel;
import ch.ffhs.hdo.client.ui.hauptfenster.RegelsetTableModel;
import ch.ffhs.hdo.client.ui.regelset.RegelsetModel;
import ch.ffhs.hdo.infrastructure.regelset.RegelsetFacade;
import ch.ffhs.hdo.infrastructure.service.util.FileHandling;

public class Main {

	private static Logger LOGGER = LogManager.getLogger(FileHandling.class);

	public static void main(String[] args) {

		
		//init StartExecutableMainView
		
		RegelsetTableModel regelsetsTable = new RegelsetTableModel(new RegelsetFacade().getAllRegelsets());
		
		


		// Init Controller
		MainController mainController = new MainController(new MainModel(),regelsetsTable);

		// Start MainView
		mainController.show();


	}


}