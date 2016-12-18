package ch.ffhs.hdo.client.ui.imports;

import ch.ffhs.hdo.client.ui.base.Controller;
import ch.ffhs.hdo.client.ui.base.executable.CloseViewOperation;
import ch.ffhs.hdo.client.ui.base.executable.DefaultClosingViewExecutable;
import ch.ffhs.hdo.client.ui.base.viewhandler.ViewHandlerImpl;
import ch.ffhs.hdo.client.ui.hauptfenster.RegelsetTableModel;
import ch.ffhs.hdo.client.ui.imports.executable.ImportSaveExecutable;
import ch.ffhs.hdo.client.ui.imports.executable.ImportSaveOperation;
import ch.ffhs.hdo.client.ui.utils.ChooseFilePathViewOperation;
import ch.ffhs.hdo.client.ui.utils.FileChooserExecuter;

/**
 * Controller fuer den Konfigurations-Import
 * 
 * @author Adrian Perez Rodriguez
 */
public class ImportController extends Controller<ImportModel, ImportView> {

	private ViewHandlerImpl viewHandler;

	/**
	 * Erstellt das Objekt
	 * 
	 * @param model
	 *            Import Model welches die Einstellungen vor dem Import aus der View erhaelt.
	 * @param regelsetModel
	 *            Enhaelt Regelsetstruktur, welche importiert werden.
	 */
	public ImportController(ImportModel model, RegelsetTableModel regelsetModel) {

		super(model);
		model.setRegelsetModel(regelsetModel);
		setView(new ImportView(getResourceBundle()));

		viewHandler = new ViewHandlerImpl();

		model.setFilePath(System.getProperty("user.home"));

		initializeViewHandler();
		initializeView();
	}

	/**
	 * Fuegt die ausfuehrbaren Optionen dem view Handler hinzu.
	 */
	private void initializeViewHandler() {
		viewHandler.addOperation(ChooseFilePathViewOperation.class, new FileChooserExecuter(getModel()));
		viewHandler.addOperation(ImportSaveOperation.class, new ImportSaveExecutable(getModel()));
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