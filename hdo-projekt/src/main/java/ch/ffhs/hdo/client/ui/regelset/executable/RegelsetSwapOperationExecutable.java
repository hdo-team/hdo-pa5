package ch.ffhs.hdo.client.ui.regelset.executable;

import ch.ffhs.hdo.client.ui.base.viewhandler.Executable;
import ch.ffhs.hdo.infrastructure.regelset.RegelsetFacade;
import ch.ffhs.hdo.infrastructure.regelset.RegelsetFacade.PriorityAction;

/**
 * Priorisiert ein Regelset mithilfe der RegelsetFacade in der Datenbank.
 * 
 * @author Jonas Segessemann
 *
 */
public class RegelsetSwapOperationExecutable implements Executable<int[]> {
	/**
	 * Priorisiert, je nach Argument, das Regelset hoch oder runter. Die
	 * Aenderungen werden mithilfe der RegelsetFacade in der Datenbank
	 * gespeichert.
	 */
	public void execute(int[] arg) {
		RegelsetFacade facade = new RegelsetFacade();
		if (arg[1] == 1) {
			facade.swapPriority(arg[0], PriorityAction.UP);
		}
		if (arg[1] == 2) {
			facade.swapPriority(arg[0], PriorityAction.DOWN);
		}
	}

}