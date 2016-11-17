package ch.ffhs.hdo.client.ui.imports.executable;

import ch.ffhs.hdo.client.ui.base.viewhandler.Executable;
import ch.ffhs.hdo.client.ui.imports.ImportController;
import ch.ffhs.hdo.client.ui.imports.ImportModel;

public class ImportViewStartExecutable implements Executable<Object> {

	public void execute(Object args) {
		
		ImportModel model = new ImportModel();
		
		ImportController importController = new ImportController(model);
		importController.show();
		
	}
}