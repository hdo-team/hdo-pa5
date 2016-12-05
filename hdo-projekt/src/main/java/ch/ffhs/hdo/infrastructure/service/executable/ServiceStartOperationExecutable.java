package ch.ffhs.hdo.infrastructure.service.executable;

import ch.ffhs.hdo.client.ui.base.viewhandler.Executable;
import ch.ffhs.hdo.client.ui.hauptfenster.MainModel;
import ch.ffhs.hdo.infrastructure.service.SortService;

public class ServiceStartOperationExecutable implements Executable<MainModel> {

	private SortService sortService = new SortService();

	public void execute(MainModel arg) {
		if (arg.getSortServiceStatus()) {
			sortService.execute();
			arg.setSortServiceStatus(true);
			arg.hasChanged();
		}
		if (!arg.getSortServiceStatus()) {
			sortService.cancel(true);
			arg.setSortServiceStatus(false);
			arg.hasChanged();
		}

	}

}