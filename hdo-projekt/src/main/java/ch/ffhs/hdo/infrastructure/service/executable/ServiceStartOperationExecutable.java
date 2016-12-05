package ch.ffhs.hdo.infrastructure.service.executable;

import ch.ffhs.hdo.client.ui.base.viewhandler.Executable;
import ch.ffhs.hdo.client.ui.hauptfenster.MainModel;
import ch.ffhs.hdo.infrastructure.service.SortService;

public class ServiceStartOperationExecutable implements Executable {

	private SortService sortService;

	public ServiceStartOperationExecutable(MainModel model) {
		this.sortService = new SortService(model);

	}

	public void execute(Object arg) {
		sortService.execute();
	}

}