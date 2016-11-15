package ch.ffhs.hdo.client.ui.regelset;

import java.util.ResourceBundle;

import ch.ffhs.hdo.client.ui.base.Controller;

/**
 * Controller für die Regelset
 * 
 * @author Daniel Crazzolara
 */
public class RegelsetController extends Controller<RegelsetModel, RegelsetView> {

	public RegelsetController(RegelsetModel model) {
		super(model);

		setView(new RegelsetView(getResourceBundle()));

		initializeView();

	}

	@Override
	public void initializeView() {
		getView().setResourceBundle(getResourceBundle());
		getView().setModel(getModel());
	}

	
	
	
}
