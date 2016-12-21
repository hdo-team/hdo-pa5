package ch.ffhs.hdo.client;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ch.ffhs.hdo.client.ui.hauptfenster.FolderTreeModel;
import ch.ffhs.hdo.client.ui.hauptfenster.MainController;
import ch.ffhs.hdo.client.ui.hauptfenster.MainModel;
import ch.ffhs.hdo.client.ui.hauptfenster.RegelsetTableModel;
import ch.ffhs.hdo.infrastructure.ApplicationSettings;
import ch.ffhs.hdo.infrastructure.regelset.RegelsetFacade;

/**
 * Main Klasse. Startet die Applikation
 * 
 * @author jonas
 *
 */
public class Main {

	private static Logger LOGGER = LogManager.getLogger(Main.class);

	public static void main(String[] args) {

		// init StartExecutableMainView
		LOGGER.debug("Start Main");
		RegelsetTableModel regelsetsTable = new RegelsetTableModel(new RegelsetFacade().getAllRegelsets());
		FolderTreeModel folderModel = new FolderTreeModel(ApplicationSettings.getInstance().getInbox_path());

		// Init Controller
		MainController mainController = new MainController(new MainModel(), regelsetsTable, folderModel);

		// Start MainView
		mainController.show();

	}

}