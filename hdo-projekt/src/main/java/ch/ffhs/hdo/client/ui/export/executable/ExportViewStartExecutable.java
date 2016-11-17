package ch.ffhs.hdo.client.ui.export.executable;

import ch.ffhs.hdo.client.ui.base.viewhandler.Executable;
import ch.ffhs.hdo.client.ui.export.ExportController;
import ch.ffhs.hdo.client.ui.export.ExportModel;

public class ExportViewStartExecutable implements Executable<Object> {

	public void execute(Object args) {

		//OptionFacade facade = new OptionFacade();
		ExportModel model = new ExportModel();

		ExportController exportController = new ExportController(model);

		exportController.show();
	}

}
