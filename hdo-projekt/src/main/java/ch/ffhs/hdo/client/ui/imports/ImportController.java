package ch.ffhs.hdo.client.ui.imports;

import ch.ffhs.hdo.client.ui.base.Controller;
import ch.ffhs.hdo.client.ui.base.executable.CloseViewOperation;
import ch.ffhs.hdo.client.ui.base.executable.DefaultClosingViewExecutable;
import ch.ffhs.hdo.client.ui.base.viewhandler.ViewHandlerImpl;
import ch.ffhs.hdo.client.ui.imports.executable.ImportSaveExecutable;
import ch.ffhs.hdo.client.ui.imports.executable.ImportSaveOperation;
import ch.ffhs.hdo.client.ui.utils.ChooseFilePathViewOperation;
import ch.ffhs.hdo.client.ui.utils.FileChooserExecuter;

/**
 * Controller für den Konfigurations-Import
 * 
 * @author Adrian Perez Rodirguez
 */
public class ImportController extends Controller<ImportModel, ImportView> {

	private ViewHandlerImpl viewHandler;

	public ImportController(ImportModel model) {

		super(model);
		setView(new ImportView(getResourceBundle()));

		viewHandler = new ViewHandlerImpl();

		model.setFilePath(System.getProperty("user.home"));

		initializeViewHandler();
		initializeView();
	}

	private void initializeViewHandler() {
		viewHandler.addOperation(ChooseFilePathViewOperation.class, new FileChooserExecuter(getModel()));
		viewHandler.addOperation(ImportSaveOperation.class, new ImportSaveExecutable(getModel()));
		viewHandler.addOperation(CloseViewOperation.class, new DefaultClosingViewExecutable(this));
	}

	public void initializeView() {
		getView().setHandler(viewHandler);
		getView().setResourceBundle(getResourceBundle());
		getView().setModel(getModel());
	}

}