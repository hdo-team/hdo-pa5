package ch.ffhs.hdo.client.ui.regelset.executable;

import ch.ffhs.hdo.client.ui.base.viewhandler.Executable;
import ch.ffhs.hdo.infrastructure.regelset.RegelsetFacade;

public class RegelsetDeleteOperationExecutable implements Executable <Integer>{

	public RegelsetDeleteOperationExecutable() {


	}


	public void execute(Integer arg) {
		RegelsetFacade facade = new RegelsetFacade();
		facade.deleteRegelset(arg);;		
	}
}