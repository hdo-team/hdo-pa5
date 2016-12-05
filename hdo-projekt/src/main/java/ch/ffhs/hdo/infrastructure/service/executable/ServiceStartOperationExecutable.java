package ch.ffhs.hdo.infrastructure.service.executable;

import ch.ffhs.hdo.client.ui.base.viewhandler.Executable;
import ch.ffhs.hdo.client.ui.hauptfenster.MainModel;
import ch.ffhs.hdo.infrastructure.service.SortService;

public class ServiceStartOperationExecutable implements Executable {

	private SortService sortService;
	private MainModel model;

	public ServiceStartOperationExecutable(MainModel model) {
		this.sortService = new SortService(model);
		this.model=model;
		
	}
	public void execute() {
			sortService.execute();
			model.setSortServiceStatus(true);

	}
	public void execute(Object arg) {
		// TODO Auto-generated method stub
		
	}

}