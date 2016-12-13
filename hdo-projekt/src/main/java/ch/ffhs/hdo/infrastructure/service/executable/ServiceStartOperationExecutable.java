package ch.ffhs.hdo.infrastructure.service.executable;

import ch.ffhs.hdo.client.ui.base.viewhandler.Executable;
import ch.ffhs.hdo.client.ui.hauptfenster.RegelsetTableModel;
import ch.ffhs.hdo.client.ui.hauptfenster.RegelsetTableModel.ServiceStatus;
import ch.ffhs.hdo.infrastructure.service.SortService;

public class ServiceStartOperationExecutable implements Executable {

	private SortService sortService;
	private RegelsetTableModel model;

	public ServiceStartOperationExecutable(RegelsetTableModel model) {
		this.model = model;
		this.sortService = SortService.getInstance();

	}

	public void execute(Object arg) {

		switch (model.getServiceStatus()) {
		case START:
			sortService.execute(); // first Start
			model.setServiceStatus(ServiceStatus.STOP);

			break;

		case STOP:
			sortService.cancel(true);
			model.setServiceStatus(ServiceStatus.START);

			break;

		default:
			model.setServiceStatus(ServiceStatus.START);
			sortService.cancel(true);

			break;
		}

	}

}