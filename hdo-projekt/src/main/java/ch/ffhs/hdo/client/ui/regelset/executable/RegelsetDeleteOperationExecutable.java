package ch.ffhs.hdo.client.ui.regelset.executable;

import ch.ffhs.hdo.client.ui.base.viewhandler.Executable;
import ch.ffhs.hdo.infrastructure.regelset.RegelsetFacade;

/**
 * Loescht ein Regelset mithilfe der RegelsetFacade aus der Datenbank.
 * 
 * @author Jonas Segessemann
 *
 */
public class RegelsetDeleteOperationExecutable implements Executable<Integer> {
	/**
	 * Loescht mithilfe der RegelsetFassade das Model aus der Datenbank.
	 */
	public void execute(Integer arg) {
		RegelsetFacade facade = new RegelsetFacade();
		facade.deleteRegelset(arg);
	}
}