package ch.ffhs.hdo.client.ui.regelset.executable;

import ch.ffhs.hdo.client.ui.base.viewhandler.Executable;
import ch.ffhs.hdo.client.ui.regelset.RegelsetController;
import ch.ffhs.hdo.client.ui.regelset.RegelsetModel;
import ch.ffhs.hdo.infrastructure.regelset.RegelsetFacade;

public class RegelsetViewStartExecutable implements Executable<Object> {

	public void execute(Object args) {

		RegelsetFacade facade = new RegelsetFacade();
		RegelsetModel model = facade.getModel();

		RegelsetController regelsetController = new RegelsetController(model);

		regelsetController.show();
	}

}
