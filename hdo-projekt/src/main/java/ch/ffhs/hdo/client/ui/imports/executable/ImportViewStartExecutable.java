package ch.ffhs.hdo.client.ui.imports.executable;

import ch.ffhs.hdo.client.ui.base.viewhandler.Executable;
import ch.ffhs.hdo.client.ui.hauptfenster.RegelsetTableModel;
import ch.ffhs.hdo.client.ui.imports.ImportController;
import ch.ffhs.hdo.client.ui.imports.ImportModel;

/**
 * Oeffnet das Importfenster fuer den Import aller Konfigurationen.
 * 
 * @author Adrian Perez Rodriguez
 *
 */
public class ImportViewStartExecutable implements Executable<RegelsetTableModel> {

	/**
	 * Oeffnet das Importfenster
	 * 
	 * @param regelsetModel
	 *            Regelsetstruktur, welche importiert wird.
	 */
	public void execute(RegelsetTableModel regelsetModel) {
		
		ImportModel model = new ImportModel();
		model.setRegelsetModel(regelsetModel);
		ImportController importController = new ImportController(model, regelsetModel);
		importController.show();
		
	}
}