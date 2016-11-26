package ch.ffhs.hdo.persistence.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;

import ch.ffhs.hdo.persistence.dto.RegelsetDto;
import ch.ffhs.hdo.persistence.jdbc.JdbcHelper;

public class RegelsetDao extends JdbcHelper {
	
	private final String SELECTALL = "SELECT RULESET.KEY, RULESET.VALUE FROM RULESET";
	
	private final String INSERT = "INSERT INTO RULESET (KEY, VALUE, VALUE, VALUE, VALUE, VALUE,SYSTIMESTAMP,SYSTIMESTAMP) VALUES (?,?,?,?,?,?,CURTIME () ,CURTIME () )";
	
	private final String UPDATE = "UPDATE RULESET SET VALUE = ? , CHANGEDATE = CURTIME () WHERE KEY = ? ";
	
	public RegelsetDto findAllRegelsets() throws SQLException {
		
		RegelsetDto dto = new RegelsetDto();
		PreparedStatement selectAllRegelsets = conn.prepareStatement(SELECTALL);

		ResultSet executeQuery = selectAllRegelsets.executeQuery();

		while (executeQuery.next()) {
			/**
			 *	Warten auf DB-Schema von Denis
			 * 
			 */
			dto.put(executeQuery.getString("ruleset_id"), executeQuery.getString("value"));
			dto.put(executeQuery.getString("ruleset_name"), executeQuery.getString("value"));
			dto.put(executeQuery.getString("file_name"), executeQuery.getString("value"));
			dto.put(executeQuery.getString("targetdir_path"), executeQuery.getString("value"));
			dto.put(executeQuery.getString("is_activ"), executeQuery.getString("value"));
			dto.put(executeQuery.getString("priority"), executeQuery.getString("value"));
			
		}

		terminate();

		return dto;
	}

	public void save(RegelsetDto dto, boolean newEntry) throws SQLException {

		Set<String> keySet = dto.keySet();
		PreparedStatement insertRegelset = null;
		if (newEntry) {
			insertRegelset = conn.prepareStatement(INSERT);

		} else {
			insertRegelset = conn.prepareStatement(UPDATE);
		}

		for (String rulesetId : keySet) {
			String id = dto.get(rulesetId);

			insertRegelset.setString(1, id);
			insertRegelset.setString(2, rulesetId);

			insertRegelset.executeUpdate();
		}

		terminate();
	}

}