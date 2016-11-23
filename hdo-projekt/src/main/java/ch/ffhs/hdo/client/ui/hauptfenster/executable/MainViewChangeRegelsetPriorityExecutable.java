package ch.ffhs.hdo.client.ui.hauptfenster.executable;

import ch.ffhs.hdo.client.ui.base.viewhandler.Executable;
import ch.ffhs.hdo.client.ui.hauptfenster.MainController;
import ch.ffhs.hdo.client.ui.hauptfenster.MainModel;
import ch.ffhs.hdo.infrastructure.regelset.RegelsetFacade;

public class MainViewChangeRegelsetPriorityExecutable implements Executable<Object> {

	public void execute(Object args) {

		RegelsetFacade facade = new RegelsetFacade();
		MainModel model = new MainModel();

		MainController exportController = new MainController(model);

		exportController.show();
	}

}
