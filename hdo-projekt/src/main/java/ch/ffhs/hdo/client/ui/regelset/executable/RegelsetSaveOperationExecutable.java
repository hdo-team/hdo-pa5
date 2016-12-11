package ch.ffhs.hdo.client.ui.regelset.executable;

import ch.ffhs.hdo.client.ui.base.viewhandler.Executable;
import ch.ffhs.hdo.client.ui.regelset.RegelsetModel;
import ch.ffhs.hdo.infrastructure.regelset.RegelsetFacade;

/**
 * RegelsetSaveOperationExecutable
 * 
 * @author Daniel Crazzolara
 */

public class RegelsetSaveOperationExecutable implements Executable<RegelsetModel> {

	private RegelsetModel model;

	public RegelsetSaveOperationExecutable(RegelsetModel model) {
		
		this.model = model;

	}

	public void execute(RegelsetModel arg) {
		RegelsetFacade facade = new RegelsetFacade();
		facade.save(model);		
	}
}