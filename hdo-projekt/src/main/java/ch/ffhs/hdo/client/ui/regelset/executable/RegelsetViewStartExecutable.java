package ch.ffhs.hdo.client.ui.regelset.executable;

import ch.ffhs.hdo.client.ui.base.viewhandler.Executable;
import ch.ffhs.hdo.client.ui.einstellungen.OptionController;
import ch.ffhs.hdo.client.ui.einstellungen.OptionModel;
import ch.ffhs.hdo.infrastructure.option.OptionFacade;

public class RegelsetViewStartExecutable implements Executable<Object> {

	public void execute(Object args) {

		OptionFacade facade = new OptionFacade();
		OptionModel model = facade.getModel();

		OptionController optionController = new OptionController(model);

		optionController.show();
	}

}
