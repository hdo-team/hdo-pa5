package ch.ffhs.hdo.client.ui.regelset.executable;

import ch.ffhs.hdo.client.ui.base.viewhandler.Executable;
import ch.ffhs.hdo.client.ui.regelset.RegelsetController;
import ch.ffhs.hdo.client.ui.regelset.RegelsetModel;
import ch.ffhs.hdo.infrastructure.option.OptionFacade;

public class RegelsetViewStartExecutable implements Executable<Object> {

	public void execute(Object args) {

		OptionFacade facade = new OptionFacade();	// TODO
		RegelsetModel model = new RegelsetModel();  //facade.getModel();		// TODO

		RegelsetController regelsetController = new RegelsetController(model);

		regelsetController.show();
	}

}
