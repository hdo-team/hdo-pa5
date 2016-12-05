package ch.ffhs.hdo.client.ui.regelset.executable;

import ch.ffhs.hdo.client.ui.base.viewhandler.Executable;
import ch.ffhs.hdo.infrastructure.regelset.RegelsetFacade;
import ch.ffhs.hdo.infrastructure.regelset.RegelsetFacade.PriorityAction;

public class RegelsetSwapOperationExecutable implements Executable <Integer>{
	PriorityAction UP;

	public void execute(Integer arg) {
		RegelsetFacade facade = new RegelsetFacade();
		facade.swapPriority(arg, UP);	
	}

}