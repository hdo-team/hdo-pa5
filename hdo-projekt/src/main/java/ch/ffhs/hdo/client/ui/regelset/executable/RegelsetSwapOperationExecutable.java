package ch.ffhs.hdo.client.ui.regelset.executable;

import java.util.ArrayList;
import java.util.Map;

import ch.ffhs.hdo.client.ui.base.viewhandler.Executable;
import ch.ffhs.hdo.infrastructure.regelset.RegelsetFacade;
import ch.ffhs.hdo.infrastructure.regelset.RegelsetFacade.PriorityAction;

public class RegelsetSwapOperationExecutable implements Executable<int[]> {
	PriorityAction UP;
	PriorityAction DOWN;

	public void execute(int[] arg) {
		RegelsetFacade facade = new RegelsetFacade();
		if (arg[2] == 1) {
			facade.swapPriority(arg[1], UP);
		}
		if (arg[2] == 2) {
			facade.swapPriority(arg[1], DOWN);
		}
	}

}