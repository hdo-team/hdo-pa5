package ch.ffhs.hdo.client.ui.einstellungen.executable;

import ch.ffhs.hdo.client.ui.base.viewhandler.Executable;
import ch.ffhs.hdo.client.ui.einstellungen.OptionModel;
import ch.ffhs.hdo.infrastructure.option.OptionFacade;

public class OptionsSaveOperationExecutable implements Executable {

	private OptionModel model;

	public OptionsSaveOperationExecutable(OptionModel model) {
		this.model = model;

	}

	public void execute(Object arg) {

		OptionFacade facade = new OptionFacade();
		facade.save(model);

	}

}
