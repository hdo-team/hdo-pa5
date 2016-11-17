package ch.ffhs.hdo.client.ui.regelset.executable;

import ch.ffhs.hdo.client.ui.base.viewhandler.Executable;
import ch.ffhs.hdo.client.ui.einstellungen.OptionModel;
import ch.ffhs.hdo.client.ui.regelset.RegelsetModel;
import ch.ffhs.hdo.infrastructure.option.OptionFacade;

public class RegelsetSaveOperationExecutable implements Executable {

	private RegelsetModel model;

	public RegelsetSaveOperationExecutable(RegelsetModel model) {
		this.model = model;

	}

	public void execute(Object arg) {

		OptionFacade facade = new OptionFacade();
	//		facade.save(model);		// TODO

	}

}
