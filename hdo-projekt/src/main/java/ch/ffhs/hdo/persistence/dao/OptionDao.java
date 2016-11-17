package ch.ffhs.hdo.persistence.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;

import ch.ffhs.hdo.persistence.dto.OptionDto;
import ch.ffhs.hdo.persistence.jdbc.JdbcHelper;

public class OptionDao extends JdbcHelper {

	private final String SELECTALL = "SELECT CONFIG.KEY, CONFIG.VALUE FROM CONFIG";

	private final String INSERT = "INSERT INTO CONFIG (KEY, VALUE,CREATIONDATE,CHANGEDATE) VALUES (?,?,SYSDATE ,SYSDATE )";

	private final String UPDATE = "UPDATE CONFIG SET VALUE = ? , CHANGEDATE = SYSDATE WHERE KEY = ? ";

	
	public OptionDto findAllOptions() throws SQLException {

		OptionDto dto = new OptionDto();
		PreparedStatement selectAllConfig = conn.prepareStatement(SELECTALL);

		ResultSet executeQuery = selectAllConfig.executeQuery();

		while (executeQuery.next()) {
			dto.put(executeQuery.getString("key"), executeQuery.getString("value"));
		}

		terminate();

		return dto;

	}

	public void save(OptionDto dto) throws SQLException {

		Set<String> keySet = dto.keySet();

		PreparedStatement insertConfig = conn.prepareStatement(UPDATE);

		for (String key : keySet) {
			String value = dto.get(key);

			insertConfig.setString(1, value);
			insertConfig.setString(2, key);

			insertConfig.executeUpdate();
		}

		terminate();
	}

}
