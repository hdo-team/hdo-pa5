package ch.ffhs.hdo.persistence.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import ch.ffhs.hdo.client.ui.export.ExportModel;
import ch.ffhs.hdo.persistence.jdbc.JdbcHelper;

public class ExportDao extends JdbcHelper {

	private String filePath;

	public void backup(ExportModel model) throws SQLException {
	
		// Pfad komplett mit letztem Slash angeben
		filePath = model.getFilePath() + "\\";
		
		String BACKUP = "BACKUP DATABASE TO " + "'" + filePath + "' BLOCKING";
		
		PreparedStatement backup = conn.prepareStatement(BACKUP);
		backup.execute();

	}
}