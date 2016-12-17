package ch.ffhs.hdo.client.ui.regelset.executable;

import ch.ffhs.hdo.client.ui.base.viewhandler.Executable;
import ch.ffhs.hdo.infrastructure.regelset.RegelsetFacade;

/**
 * Löscht ein Regelset mithilfe der RegelsetFacade aus der Datenbank.
 * 
 * @author Jonas Segessemann
 *
 */
public class RegelsetDeleteOperationExecutable implements Executable<Integer> {
	/**
	 * Löscht mithilfe der RegelsetFassade das Model aus der Datenbank.
	 */
	public void execute(Integer arg) {
		RegelsetFacade facade = new RegelsetFacade();
		facade.deleteRegelset(arg);
	}
}