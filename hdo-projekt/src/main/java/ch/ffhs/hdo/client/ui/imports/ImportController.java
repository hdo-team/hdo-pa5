package ch.ffhs.hdo.client.ui.imports;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import ch.ffhs.hdo.client.ui.base.Controller;
/**
 * 
 * @author Adrian Perez Rodriguez
 */
import ch.ffhs.hdo.client.ui.base.viewhandler.ViewHandlerImpl;
import ch.ffhs.hdo.client.ui.utils.ChooseFilePath;
import ch.ffhs.hdo.client.ui.utils.FileChooserExecuter;
import ch.ffhs.hdo.client.ui.utils.ReadFile;
import ch.ffhs.hdo.client.ui.utils.ReadFileExecutable;

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
		viewHandler.addOperation(ChooseFilePath.class, new FileChooserExecuter(getModel()));
		viewHandler.addOperation(ReadFile.class, new ReadFileExecutable(getModel()));

	}

	public void initializeView() {
		getView().setHandler(viewHandler);
		getView().setResourceBundle(getResourceBundle());
		getView().setModel(getModel());
	}

	
}