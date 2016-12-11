package ch.ffhs.hdo.client.ui.hauptfenster.executable;

import ch.ffhs.hdo.client.ui.base.viewhandler.Executable;
import ch.ffhs.hdo.client.ui.hauptfenster.RegelsetTableModel;
import ch.ffhs.hdo.infrastructure.regelset.RegelsetFacade;

public class RegelsetTableUpdateOperationExecutable implements Executable {

	private RegelsetTableModel model;

	public RegelsetTableUpdateOperationExecutable(RegelsetTableModel model) {
		this.model=model;
	}

	public void execute(Object arg) {
		RegelsetFacade facade = new RegelsetFacade();
		model.setRulsetList(facade.getAllRegelsets());
	}
}