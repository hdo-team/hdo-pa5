package ch.ffhs.hdo.client.ui.regelset;

import ch.ffhs.hdo.client.ui.base.Controller;
import ch.ffhs.hdo.client.ui.base.executable.CloseViewOperation;
import ch.ffhs.hdo.client.ui.base.executable.DefaultClosingViewExecutable;
import ch.ffhs.hdo.client.ui.base.viewhandler.ViewHandlerImpl;
import ch.ffhs.hdo.client.ui.regelset.executable.RegelsetSaveOperation;
import ch.ffhs.hdo.client.ui.regelset.executable.RegelsetSaveOperationExecutable;
import ch.ffhs.hdo.client.ui.utils.ChooseDirectoryPathViewOperation;
import ch.ffhs.hdo.client.ui.utils.FolderChooserExecuter;

/**
 * Controller für die Regelset
 * 
 * @author Daniel Crazzolara
 */
public class RegelsetController extends Controller<RegelsetModel, RegelsetView> {

	private final ViewHandlerImpl viewHandler;
	
	public RegelsetController(RegelsetModel model) {
		super(model);

		setView(new RegelsetView(getResourceBundle()));
		viewHandler = new ViewHandlerImpl();

		setupViewHandler();
		
		initializeView();

	}

	@Override
	public void initializeView() {
		getView().setResourceBundle(getResourceBundle());
		getView().setModel(getModel());
		getView().setHandler(viewHandler);
	}

	private void setupViewHandler() {
		viewHandler.addOperation(CloseViewOperation.class, new DefaultClosingViewExecutable(this));
		viewHandler.addOperation(RegelsetSaveOperation.class, new RegelsetSaveOperationExecutable(getModel()));
		viewHandler.addOperation(ChooseDirectoryPathViewOperation.class, new FolderChooserExecuter(getModel()));
	}
}
