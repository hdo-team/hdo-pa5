package ch.ffhs.hdo.client.ui.export;

import ch.ffhs.hdo.client.ui.base.Controller;
import ch.ffhs.hdo.client.ui.base.executable.CloseViewOperation;
import ch.ffhs.hdo.client.ui.base.executable.DefaultClosingViewExecutable;
import ch.ffhs.hdo.client.ui.base.viewhandler.ViewHandlerImpl;
import ch.ffhs.hdo.client.ui.export.executable.ExportSaveExecutable;
import ch.ffhs.hdo.client.ui.export.executable.ExportSaveOperation;
import ch.ffhs.hdo.client.ui.utils.ChooseDirectoryPathViewOperation;
import ch.ffhs.hdo.client.ui.utils.FolderChooserExecuter;

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
		viewHandler.addOperation(ExportSaveOperation.class, new ExportSaveExecutable(getModel()));
		viewHandler.addOperation(CloseViewOperation.class, new DefaultClosingViewExecutable(this));
	}
	
	public void initializeView() {
		getView().setHandler(viewHandler);
		getView().setResourceBundle(getResourceBundle());
		getView().setModel(getModel());
	}
	
	
}
