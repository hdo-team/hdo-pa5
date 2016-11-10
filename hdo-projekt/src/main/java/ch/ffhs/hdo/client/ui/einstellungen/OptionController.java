package ch.ffhs.hdo.client.ui.einstellungen;

import java.util.ResourceBundle;

import ch.ffhs.hdo.client.ui.base.Controller;

/**
 * Controller für die Options
 * 
 * @author Denis Bittante
 */
public class OptionController extends Controller<OptionModel, OptionView> {

	public OptionController(OptionModel model) {
		super(model);

		setView(new OptionView(getResourceBundle()));

		initializeView();

	}

	@Override
	public void initializeView() {
		getView().setResourceBundle(getResourceBundle());
		getView().setModel(getModel());
	}

	
	
	
}
