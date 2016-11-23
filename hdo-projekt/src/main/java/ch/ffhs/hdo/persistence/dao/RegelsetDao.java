package ch.ffhs.hdo.persistence.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;

import ch.ffhs.hdo.persistence.dto.RegelsetDto;
import ch.ffhs.hdo.persistence.jdbc.JdbcHelper;

public class RegelsetDao extends JdbcHelper {
	
	private final String SELECTALL = "tba";
	
	private final String INSERT = "tba";
	
	private final String UPDATE = "tba";
	
	public RegelsetDto findAllRegelsets() throws SQLException {
		
		RegelsetDto dto = new RegelsetDto();
		PreparedStatement selectAllConfig = conn.prepareStatement(SELECTALL);

		ResultSet executeQuery = selectAllConfig.executeQuery();

		while (executeQuery.next()) {
			/**
			 *	Warten auf DB-Schema von Denis
			 * 
			 * 
			dto.put(executeQuery.getString("id"), executeQuery.getString("rulesetName"), executeQuery.getString("targetDirectory")
					, executeQuery.getString("newFilename"), executeQuery.getString("isActiv"), executeQuery.getString("priority"));
			*/
		}

		terminate();

		return dto;
	}

	public void save(RegelsetDto dto, boolean newEntry) throws SQLException {

		Set<String> keySet = dto.keySet();
		PreparedStatement insertConfig = null;
		if (newEntry) {
			insertConfig = conn.prepareStatement(INSERT);

		} else {
			insertConfig = conn.prepareStatement(UPDATE);
		}

		for (String id : keySet) {
			String value = dto.get(id);

			insertConfig.setString(1, value);
			insertConfig.setString(2, id);

			insertConfig.executeUpdate();
		}

		terminate();
		
	}

}