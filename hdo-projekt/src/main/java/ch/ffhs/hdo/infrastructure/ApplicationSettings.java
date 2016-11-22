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
	private static final String RULESET_NAME = "rulesetName";
	private static final String TARGETDIR_PATH = "targetDirectory";
	private static final String FILENAME = "filenameKonfiguration";
	private static final String RULESET_STATUS = "ruleActiv";

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
	
	public void saveRulesetName(String rulesetName) {
		try {

			config.setProperty(RULESET_NAME, rulesetName);
			config.save();

			if (rulesetName != null && new File(rulesetName).exists()) {
				new InitDatabase();
			}

			
		} catch (ConfigurationException e) {
			LOGGER.error("Fehler beim Speichern des Ruleset-Namen", e);
		}
	}
	
	public void saveTargetDirectoryPath(String targetDirectory) {
		try {

			config.setProperty(TARGETDIR_PATH, targetDirectory);
			config.save();

			if (targetDirectory != null && new File(targetDirectory).exists()) {
				new InitDatabase();
			}

			
		} catch (ConfigurationException e) {
			LOGGER.error("Fehler beim Speichern des Zielverzeichnisses", e);
		}
	}
	
	public void saveFilenameKonfiguration(String filenameKonfiguration) {
		try {

			config.setProperty(FILENAME, filenameKonfiguration);
			config.save();

			if (filenameKonfiguration != null && new File(filenameKonfiguration).exists()) {
				new InitDatabase();
			}

			
		} catch (ConfigurationException e) {
			LOGGER.error("Fehler beim Speichern des Filenamen", e);
		}
	}
	
	public void saveRulesetActiv(Boolean rulesetactiv) {
		try {
			
			String status = rulesetactiv.toString();
			
			config.setProperty(RULESET_STATUS, status);
			config.save();

			if (status != null && new File(status).exists()) {
				new InitDatabase();
			}

		
		} catch (ConfigurationException e) {
			LOGGER.error("Fehler beim Speichern des Ruleset Status", e);
		}
	}
}
