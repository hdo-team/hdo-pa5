package ch.ffhs.hdo.client.ui.export.executable;

import ch.ffhs.hdo.client.ui.base.viewhandler.Executable;
import ch.ffhs.hdo.client.ui.export.ExportController;
import ch.ffhs.hdo.client.ui.export.ExportModel;
import ch.ffhs.hdo.client.ui.hauptfenster.FolderTreeModel;

/**
 * Öffnet das Exportfenster für den Export aller Konfigurationen.
 * 
 * @author Jonas Segessemann
 *
 */
public class ExportViewStartExecutable implements Executable<FolderTreeModel> {
	
	/**
	 * Öffnet das Exportfenster
	 * 
	 * @param folderModel
	 *            Verzeichnisstruktur, welche exportiert wird.
	 */
	public void execute(FolderTreeModel folderModel) {

		ExportModel model = new ExportModel();
		model.setFolderModel(folderModel);

		ExportController exportController = new ExportController(model, folderModel);

		exportController.show();
	}

}
