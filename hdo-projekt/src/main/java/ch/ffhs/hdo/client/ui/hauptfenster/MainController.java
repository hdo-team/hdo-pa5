package ch.ffhs.hdo.client.ui.hauptfenster;

import ch.ffhs.hdo.client.ui.base.Controller;
import ch.ffhs.hdo.client.ui.base.viewhandler.ViewHandlerImpl;
import ch.ffhs.hdo.client.ui.einstellungen.executable.OptionViewStartExecutable;
import ch.ffhs.hdo.client.ui.einstellungen.executable.OptionViewStartOperation;
import ch.ffhs.hdo.client.ui.imports.executable.ImportViewStartExecutable;
import ch.ffhs.hdo.client.ui.imports.executable.ImportViewStartOperation;

/**
 * Controller für das Hauptfenster
 * 
 * @author Jonas Segessemann
 */
public class MainController extends Controller<MainModel, MainView> {

	private ViewHandlerImpl viewHandler;

	public MainController(MainModel model) {
		super(model);

		this.viewHandler = new ViewHandlerImpl();

		setView(new MainView(getResourceBundle()));

		initializeView();
		initlizeHandler();
	}

	private void initlizeHandler() {

		this.viewHandler.addOperation(OptionViewStartOperation.class, new OptionViewStartExecutable());
		this.viewHandler.addOperation(ImportViewStartOperation.class, new ImportViewStartExecutable());
	}

	@Override
	public void initializeView() {
		getView().setResourceBundle(getResourceBundle());
		getView().setModel(getModel());
		getView().setHandler(viewHandler);

		// getView().getFolderOverview().setModel(getModel().getFolderModel());

	}

}
