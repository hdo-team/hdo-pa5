package ch.ffhs.hdo.client.ui.regelset.executable;

import ch.ffhs.hdo.client.ui.base.viewhandler.Executable;
import ch.ffhs.hdo.client.ui.regelset.RegelsetController;
import ch.ffhs.hdo.client.ui.regelset.RegelsetModel;
import ch.ffhs.hdo.infrastructure.regelset.RegelsetFacade;

public class RegelsetViewStartExecutable implements Executable<Object> {


	public void execute(Object args) {

		RegelsetModel model = null;
System.out.println("Args: " + args);
		if (args != null) {
				// Hauptuebesicht gibt Regelset-Model mit (Regelset anpassen)
				model = (RegelsetModel)args;
		} else {
			// Hauptübersicht gibt kein Model mit (neues Regelset erstellen)
			RegelsetFacade facade = new RegelsetFacade();
			model = facade.getModel();
		}
		
		RegelsetController regelsetController = new RegelsetController(model);

		regelsetController.show();
	}

}
