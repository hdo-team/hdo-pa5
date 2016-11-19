package ch.ffhs.hdo.client;

import java.io.File;
import java.io.IOException;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

import ch.ffhs.hdo.client.ui.hauptfenster.MainController;
import ch.ffhs.hdo.client.ui.hauptfenster.MainModel;
import ch.ffhs.hdo.infrastructure.ApplicationSettings;
import ch.ffhs.hdo.persistence.jdbc.InitDatabase;

public class Main {

	static final String INBOXPATH = "inboxPath";

	public static void main(String[] args) {

		PropertiesConfiguration config = null;

		File file = new File(
				System.getProperty("user.home") + File.separator + "hdo" + File.separator + "hdo.settings");

		if (!file.exists()) {
			try {
				file.createNewFile();
				config = new PropertiesConfiguration(file);

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ConfigurationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			try {
				config = new PropertiesConfiguration(file);
			} catch (ConfigurationException e) {
				e.printStackTrace();
			}
			final String inboxPath = config.getString(INBOXPATH);

			if (inboxPath != null && new File(inboxPath).exists()) {
				ApplicationSettings.getInstance().setInbox_path(inboxPath);
				InitDatabase initDatabase = new InitDatabase();
			}
		}

		// Init Controller
		MainController mainController = new MainController(new MainModel());

		// Start MainView
		mainController.show();

		// Bitte zum Testen die config.properties im 'resources' Ordner wo das
		// resourceBundle auswählen...
		// importController.show();

		try {

			config.setProperty(INBOXPATH, ApplicationSettings.getInstance().getInbox_path());
			config.save();

		} catch (ConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}