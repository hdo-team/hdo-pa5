package ch.ffhs.hdo.persistence.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;

import ch.ffhs.hdo.persistence.dto.RegelsetDto;
import ch.ffhs.hdo.persistence.dto.RegelsetDto.RegelsetValues;
import ch.ffhs.hdo.persistence.jdbc.JdbcHelper;

public class RegelsetDao extends JdbcHelper {

	private final String SELECTALL = "SELECT CONFIG.KEY, CONFIG.VALUE FROM CONFIG";

	private final String INSERT = "INSERT INTO CONFIG (KEY, VALUE,SYSTIMESTAMP,SYSTIMESTAMP) VALUES (?,?,CURTIME () ,CURTIME () )";

	private final String UPDATE = "UPDATE CONFIG SET VALUE = ? , CHANGEDATE = CURTIME () WHERE KEY = ? ";

	public RegelsetDto findAllRegelsets() throws SQLException {

		RegelsetDto dto = new RegelsetDto();
		PreparedStatement selectAllConfig = conn.prepareStatement(SELECTALL);

		ResultSet executeQuery = selectAllConfig.executeQuery();

		while (executeQuery.next()) {
			dto.put(executeQuery.getString("key"), executeQuery.getString("value"));
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

		for (String key : keySet) {
			String value = dto.get(key);

			insertConfig.setString(1, value);
			insertConfig.setString(2, key);

			insertConfig.executeUpdate();
		}

		terminate();
	}

	public void protocollSortServiceRun(boolean succcessfull) throws SQLException {

		RegelsetDto dto = new RegelsetDto();
		if (succcessfull) {
			dto.put(RegelsetValues.LAST_SORTRUN, "SUCCESSFULL");
		} else {
			dto.put(RegelsetValues.LAST_SORTRUN, "ERROR");
		}

		save(dto, true);
	}

}