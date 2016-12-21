package ch.ffhs.hdo.infrastructure.service.executable;

import ch.ffhs.hdo.client.ui.base.viewhandler.Executable;
import ch.ffhs.hdo.client.ui.hauptfenster.RegelsetTableModel;
import ch.ffhs.hdo.client.ui.hauptfenster.RegelsetTableModel.ServiceStatus;
import ch.ffhs.hdo.infrastructure.service.SortService;

/**
 * Startet die Implementation für den Sortservice
 * 
 * @author Denis Bittante
 *
 */
public class ServiceStartOperationExecutable implements Executable<ServiceStatus> {

	private SortService sortService;
	private RegelsetTableModel mainModel;

	/**
	 * Init Executable
	 * 
	 * @param mainModel
	 *            see {@link RegelsetTableModel}
	 * 
	 */
	public ServiceStartOperationExecutable(RegelsetTableModel mainModel) {

		this.mainModel = mainModel;

	}

	public void execute(ServiceStatus arg) {

		switch (arg) {
		case STOP:
			SortService.getInstance(this.mainModel).cancel(true);
			break;
		case START:
			SortService.getInstance(this.mainModel).execute(); // first
																// Start

			break;

		default:
			sortService.cancel(true);

			break;
		}

	}

}