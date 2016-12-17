package ch.ffhs.hdo.client.ui.export;

import ch.ffhs.hdo.client.ui.base.Controller;
import ch.ffhs.hdo.client.ui.base.executable.CloseViewOperation;
import ch.ffhs.hdo.client.ui.base.executable.DefaultClosingViewExecutable;
import ch.ffhs.hdo.client.ui.base.viewhandler.ViewHandlerImpl;
import ch.ffhs.hdo.client.ui.export.executable.ExportSaveExecutable;
import ch.ffhs.hdo.client.ui.export.executable.ExportSaveOperation;
import ch.ffhs.hdo.client.ui.hauptfenster.FolderTreeModel;
import ch.ffhs.hdo.client.ui.utils.ChooseDirectoryPathViewOperation;
import ch.ffhs.hdo.client.ui.utils.FolderChooserExecuter;

/**
 * Controller für den Konfigurations-Export.
 * 
 * @author Adrian Perez
 */
public class ExportController extends Controller<ExportModel, ExportView> {

	private ViewHandlerImpl viewHandler;

	/**
	 * Erstellt das Objekt
	 * 
	 * @param model
	 *            Export Model welches die Einstellungen aus der DB enhält.
	 * @param folderModel
	 *            Enhält Verzeichnisstruktur, welche gespeichert wird.
	 */
	public ExportController(ExportModel model, FolderTreeModel folderModel) {

		super(model);
		model.setFolderModel(folderModel);
		setView(new ExportView(getResourceBundle()));

		viewHandler = new ViewHandlerImpl();

		model.setFilePath(System.getProperty("user.home"));

		initializeViewHandler();
		initializeView();
	}

	/**
	 * Fügt die ausführbaren Optionen dem view Handler hinzu.
	 */
	private void initializeViewHandler() {
		viewHandler.addOperation(ChooseDirectoryPathViewOperation.class, new FolderChooserExecuter(getModel()));
		viewHandler.addOperation(ExportSaveOperation.class, new ExportSaveExecutable(getModel()));
		viewHandler.addOperation(CloseViewOperation.class, new DefaultClosingViewExecutable(this));
	}

	/**
	 * Inizialisierung der erstellten View.
	 */
	public void initializeView() {
		getView().setHandler(viewHandler);
		getView().setResourceBundle(getResourceBundle());
		getView().setModel(getModel());
	}

}
