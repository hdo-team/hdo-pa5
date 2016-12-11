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
		this.sortService = SortService.getInstance(model);

	}

	public void execute(Object arg) {

		if (model != null) {
			if (model.getServiceStatus() == null) {
				sortService.execute(); // first Start
			} else {
				if (model.getServiceStatus().equals(ServiceStatus.START)) {
					model.setServiceStatus(ServiceStatus.STOP);
					sortService.cancel(true);
				} else {
					this.sortService = new SortService(model);
					sortService.execute();

				}
			}

		}
	}

}