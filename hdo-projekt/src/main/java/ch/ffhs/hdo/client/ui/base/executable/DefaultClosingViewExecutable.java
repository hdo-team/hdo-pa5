package ch.ffhs.hdo.client.ui.base.executable;

import ch.ffhs.hdo.client.ui.base.Controller;
import ch.ffhs.hdo.client.ui.base.ParamChecker;
import ch.ffhs.hdo.client.ui.base.viewhandler.Executable;

public class DefaultClosingViewExecutable implements Executable {

	private final Controller controller;

	public DefaultClosingViewExecutable(Controller controller) {
		ParamChecker.notNull(controller, "controller");

		this.controller = controller;

	}

	public void execute(Object arg) {

		this.controller.terminate();
		
	}

}
