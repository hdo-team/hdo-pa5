package ch.ffhs.hdo.client.ui.export.executable;

import java.sql.SQLException;

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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}