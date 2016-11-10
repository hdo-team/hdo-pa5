package ch.ffhs.hdo.client.ui.export;

import java.util.ResourceBundle;

import ch.ffhs.hdo.client.ui.base.Controller;

/**
 * Controller für die den Konfigurations-Export
 * 
 * @author Jonas Segessemann
 */
public class ExportController extends Controller<ExportModel, ExportView> {

	public ExportController(ExportModel model) {
		super(model);

		setView(new ExportView(getResourceBundle()));

		initializeView();

	}

	@Override
	public void initializeView() {
		getView().setResourceBundle(getResourceBundle());
		getView().setModel(getModel());
	}

	
	
	
}
