package ch.ffhs.hdo.client.ui.export;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;

import ch.ffhs.hdo.client.ui.base.Controller;
import ch.ffhs.hdo.client.ui.base.viewhandler.ViewHandlerImpl;
import ch.ffhs.hdo.client.ui.utils.ChooseDirectoryPathViewOperation;
import ch.ffhs.hdo.client.ui.utils.ChooseFilePathViewOperation;
import ch.ffhs.hdo.client.ui.utils.FileChooserExecuter;
import ch.ffhs.hdo.client.ui.utils.FolderChooserExecuter;
import ch.ffhs.hdo.client.ui.utils.ReadFileViewOperation;
import ch.ffhs.hdo.client.ui.utils.ReadFileExecutable;

/**
 * Controller für die den Konfigurations-Export
 * 
 * @author Jonas Segessemann
 */
public class ExportController extends Controller<ExportModel, ExportView> {

	private ViewHandlerImpl viewHandler;
	
	public ExportController(ExportModel model) {
		
		super(model);
		setView(new ExportView(getResourceBundle()));
		
		viewHandler = new ViewHandlerImpl();

		model.setFilePath(System.getProperty("user.home"));

		initializeViewHandler();
		initializeView();
	}

	private void initializeViewHandler() {
		viewHandler.addOperation(ChooseDirectoryPathViewOperation.class, new FolderChooserExecuter(getModel()));

	}
	
	public void initializeView() {
		getView().setHandler(viewHandler);
		getView().setResourceBundle(getResourceBundle());
		getView().setModel(getModel());
	}
	
	
}
