package ch.ffhs.hdo.client.ui.regelset.executable;

import ch.ffhs.hdo.client.ui.base.viewhandler.Executable;
import ch.ffhs.hdo.client.ui.regelset.RegelsetModel;
import ch.ffhs.hdo.infrastructure.regelset.RegelsetFacade;

public class RegelsetSaveOperationExecutable implements Executable {

	private RegelsetModel model;

	public RegelsetSaveOperationExecutable(RegelsetModel model) {
		
		this.model = model;

	}

	public void execute(Object arg) {

		RegelsetFacade facade = new RegelsetFacade();
		facade.save(model);

	}
}