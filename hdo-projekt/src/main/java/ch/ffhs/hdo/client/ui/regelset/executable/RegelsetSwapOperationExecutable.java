package ch.ffhs.hdo.client.ui.regelset.executable;

import ch.ffhs.hdo.client.ui.base.viewhandler.Executable;
import ch.ffhs.hdo.infrastructure.regelset.RegelsetFacade;
import ch.ffhs.hdo.infrastructure.regelset.RegelsetFacade.PriorityAction;

public class RegelsetSwapOperationExecutable implements Executable<int[]> {

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