package ch.ffhs.hdo.client.ui.einstellungen;

import ch.ffhs.hdo.client.ui.base.Controller;
import ch.ffhs.hdo.client.ui.base.executable.CloseViewOperation;
import ch.ffhs.hdo.client.ui.base.executable.DefaultClosingViewExecutable;
import ch.ffhs.hdo.client.ui.base.viewhandler.ViewHandlerImpl;

/**
 * Controller für die Options
 * 
 * @author Denis Bittante
 */
public class OptionController extends Controller<OptionModel, OptionView> {

	private final ViewHandlerImpl viewHandler;

	public OptionController(OptionModel model) {
		super(model);

		setView(new OptionView(getResourceBundle()));
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
	}

}
