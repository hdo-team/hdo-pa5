package ch.ffhs.hdo.client.ui.einstellungen.executable;

import ch.ffhs.hdo.client.ui.base.viewhandler.Executable;
import ch.ffhs.hdo.client.ui.einstellungen.OptionController;
import ch.ffhs.hdo.client.ui.einstellungen.OptionModel;
import ch.ffhs.hdo.client.ui.hauptfenster.FolderTreeModel;
import ch.ffhs.hdo.infrastructure.option.OptionFacade;

/**
 * Oeffnet das Konfigurationsfenster mit den aktuellen Einstellungen oeffnen.
 * 
 * @author Jonas Segessemann
 *
 */
public class OptionViewStartExecutable implements Executable<FolderTreeModel> {
	/**
	 * Oeffnet die Konfigurationen mit den aktuellen Einstellungen.
	 * 
	 * @param folderModel
	 *            Model in welchem der Inbox Pfad geaendert wird und das View
	 *            aktualisiert wird.
	 */
	public void execute(FolderTreeModel folderModel) {

		OptionFacade facade = new OptionFacade();
		OptionModel model = facade.getModel();
		model.setFolderModel(folderModel);
		OptionController optionController = new OptionController(model);

		optionController.show();
	}

}
