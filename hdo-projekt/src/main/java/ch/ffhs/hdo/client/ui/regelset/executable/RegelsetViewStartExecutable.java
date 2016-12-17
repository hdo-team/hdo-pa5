package ch.ffhs.hdo.client.ui.regelset.executable;

import ch.ffhs.hdo.client.ui.base.viewhandler.Executable;
import ch.ffhs.hdo.client.ui.hauptfenster.RegelsetTableModel;
import ch.ffhs.hdo.client.ui.regelset.RegelModel;
import ch.ffhs.hdo.client.ui.regelset.RegelsetController;
import ch.ffhs.hdo.client.ui.regelset.RegelsetModel;

/**
 * Zeigt die Regelset View mit einem bestehenden oder neuen Regelset an
 * 
 * @author Daniel Crazzolara
 */

public class RegelsetViewStartExecutable implements Executable<Object> {

	/**
	 * Es wird die Regelset View gestartet um je nach Argument ein bestehendes Regelset
	 * zu bearbeiten bzw. ein neues zu erstellen.
	 * 
	 * @param args
	 * 			  ist args ein RegelsetModel wird das entprechende Regelset bearbeitet
	 *            ist args ein RegelsetTableModel wird ein neues Regelset erstellt
	 */
	public void execute(Object args) {
		if (args.getClass() == RegelsetModel.class) {
			// bestehendes Regelset bearbeiten
			RegelsetModel model = null;
			model = (RegelsetModel) args;
			RegelsetController regelsetController = new RegelsetController(model);
			regelsetController.show();
		}
		if (args.getClass() == RegelsetTableModel.class) {
			// neues Regelset(inkl. erster "leerer" Regel) erstellen. RegelsetTableModel wird benötigt um
			// ViewUpdate nach dem Erstellen durchzuführen
			RegelsetModel model = null;
			model = RegelsetModel.getNullModel();
			model.getRuleModelList().add(RegelModel.getNullModel());
			RegelsetController regelsetController = new RegelsetController(model, (RegelsetTableModel) args);
			regelsetController.show();
		}
	}

}
