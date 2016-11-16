package ch.ffhs.hdo.client.ui.hauptfenster;

import ch.ffhs.hdo.client.ui.base.Controller;

/**
 * Controller für das Hauptfenster
 * 
 * @author Jonas Segessemann
 */
public class MainController extends Controller<MainModel, MainView> {

	public MainController(MainModel model) {
		super(model);

		setView(new MainView(getResourceBundle()));

		initializeView();

	}

	@Override
	public void initializeView() {
		getView().setResourceBundle(getResourceBundle());
		getView().setModel(getModel());

		//getView().getFolderOverview().setModel(getModel().getFolderModel());

	}

}
