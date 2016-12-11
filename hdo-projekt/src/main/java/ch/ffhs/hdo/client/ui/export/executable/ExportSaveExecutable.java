package ch.ffhs.hdo.client.ui.export.executable;

import java.sql.SQLException;

import ch.ffhs.hdo.client.ui.base.viewhandler.Executable;
import ch.ffhs.hdo.client.ui.export.ExportModel;
import ch.ffhs.hdo.persistence.dao.ExportDao;

public class ExportSaveExecutable implements Executable<ExportModel> {
	
	private ExportModel model;
	
	public ExportSaveExecutable(ExportModel model) {
		this.model = model;

	}

	public void execute(ExportModel arg) {

		ExportDao dao = new ExportDao();
		
		try {
			dao.backup(model);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}