package ch.ffhs.hdo.client.ui.export.executable;

import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ch.ffhs.hdo.client.ui.base.Controller;
import ch.ffhs.hdo.client.ui.base.viewhandler.Executable;
import ch.ffhs.hdo.client.ui.export.ExportModel;
import ch.ffhs.hdo.persistence.dao.ExportDao;

/**
 * Speichert die Konfigurationen der Datenbank in das ExportModel.
 * 
 * @author Adrian Perez Rodriguez
 *
 */
public class ExportSaveExecutable implements Executable {
	private static Logger LOGGER = LogManager.getLogger(ExportSaveExecutable.class);

	private ExportModel model;

	/**
	 * Konstruktor zur erstellung des Objekts.
	 * 
	 * @param model
	 *            Model, in welchem die Datenbank-Daten gespeichert werden.
	 */
	public ExportSaveExecutable(ExportModel model) {
		this.model = model;

	}

	/**
	 * Daten aus der Datenbank werden in das Model gespeichert.
	 */
	public void execute(Object arg) {
		ExportDao dao = new ExportDao();

		try {
			dao.backup(model);

		} catch (SQLException e) {
			LOGGER.error("Fehler beim Exportieren ",e);
		}

	}

}