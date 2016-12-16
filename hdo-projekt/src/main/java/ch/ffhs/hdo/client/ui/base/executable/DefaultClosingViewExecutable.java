package ch.ffhs.hdo.client.ui.base.executable;

import ch.ffhs.hdo.client.ui.base.Controller;
import ch.ffhs.hdo.client.ui.base.ParamChecker;
import ch.ffhs.hdo.client.ui.base.viewhandler.Executable;

/**
 * Executable um eine View zu schliessen
 * 
 * @author Denis Bittante
 *
 */
public class DefaultClosingViewExecutable implements Executable {

	private final Controller controller;

	/**
	 * Controller bei dem <code>terminate()</code> terminate aufgerufen werden
	 * soll.
	 * 
	 * @param controller
	 *            der aktive {@link Controller} muss hier übergeben werden.
	 */
	public DefaultClosingViewExecutable(Controller controller) {
		ParamChecker.notNull(controller, "controller");

		this.controller = controller;

	}

	public void execute(Object arg) {

		this.controller.terminate();

	}

}
