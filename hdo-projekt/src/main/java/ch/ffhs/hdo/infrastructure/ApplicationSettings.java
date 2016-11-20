package ch.ffhs.hdo.infrastructure;

import java.io.File;
import java.io.IOException;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ch.ffhs.hdo.infrastructure.service.util.FileHandling;
import ch.ffhs.hdo.persistence.jdbc.InitDatabase;

public class ApplicationSettings {

	private static final File APP_CONFIG = new File(
			System.getProperty("user.home") + File.separator + "hdo" + File.separator + "hdo.settings");


	private static Logger LOGGER = LogManager.getLogger(FileHandling.class);
	private static final String INBOXPATH = "inboxPath";

	private static ApplicationSettings instance = null;

	private static PropertiesConfiguration config = null;

	protected ApplicationSettings() {

		try {

			if (!APP_CONFIG.exists()) {
				APP_CONFIG.createNewFile();
				config = new PropertiesConfiguration(APP_CONFIG);

			} else {
				config = new PropertiesConfiguration(APP_CONFIG);
			}

		} catch (IOException e) {
			LOGGER.error("File konnte nicht erstellt werden", e);
		} catch (ConfigurationException e) {

			LOGGER.error("Configurationsfehler beim laden der Daten", e);
		}


	}

	public static ApplicationSettings getInstance() {
		if (instance == null) {
			instance = new ApplicationSettings();
		}
		return instance;
	}


	public String getInbox_path() {
		return config.getString(INBOXPATH);
	}


	public void saveInboxPath(String inboxPath) {
		try {

			config.setProperty(INBOXPATH, inboxPath);
			config.save();

			if (inboxPath != null && new File(inboxPath).exists()) {
				new InitDatabase();
			}

			
		} catch (ConfigurationException e) {
			LOGGER.error("Fehler beim Speichern der Konfigdatei", e);
		}
	}
	
	public void saveRulesetName(String ruleset) {
		try {

			config.setProperty(INBOXPATH, ruleset);
			config.save();

			if (ruleset != null && new File(ruleset).exists()) {
				new InitDatabase();
			}

			
		} catch (ConfigurationException e) {
			LOGGER.error("Fehler beim Speichern der Konfigdatei", e);
		}
	}
	
	public void saveTargetDirectoryPath(String targetDirectoryPath) {
		try {

			config.setProperty(INBOXPATH, targetDirectoryPath);
			config.save();

			if (targetDirectoryPath != null && new File(targetDirectoryPath).exists()) {
				new InitDatabase();
			}

			
		} catch (ConfigurationException e) {
			LOGGER.error("Fehler beim Speichern der Konfigdatei", e);
		}
	}
	
	public void saveFilenameKonfiguration(String filename) {
		try {

			config.setProperty(INBOXPATH, filename);
			config.save();

			if (filename != null && new File(filename).exists()) {
				new InitDatabase();
			}

			
		} catch (ConfigurationException e) {
			LOGGER.error("Fehler beim Speichern der Konfigdatei", e);
		}
	}
	
	public void saveRulesetActiv(Boolean rulesetactiv) {
		try {
			
			String status = rulesetactiv.toString();
			config.setProperty(INBOXPATH, status);
			config.save();

			if (status != null && new File(status).exists()) {
				new InitDatabase();
			}

		
		} catch (ConfigurationException e) {
			LOGGER.error("Fehler beim Speichern der Konfigdatei", e);
		}
	}
}
