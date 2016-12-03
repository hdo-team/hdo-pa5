package ch.ffhs.hdo.client.ui.einstellungen.executable;

import ch.ffhs.hdo.client.ui.base.viewhandler.Executable;
import ch.ffhs.hdo.client.ui.einstellungen.OptionModel;
import ch.ffhs.hdo.infrastructure.ApplicationSettings;
import ch.ffhs.hdo.infrastructure.option.OptionFacade;

public class OptionsSaveExecutable implements Executable {

	private OptionModel model;

	public OptionsSaveExecutable(OptionModel model) {
		this.model = model;

	}

	public void execute(Object arg) {

		ApplicationSettings.getInstance().saveInboxPath(model.getInboxPath());

		OptionFacade facade = new OptionFacade();
		facade.save(model);

	}

}
