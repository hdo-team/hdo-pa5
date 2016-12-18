package ch.ffhs.hdo.persistence.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Set;

import ch.ffhs.hdo.persistence.dto.OptionDto;
import ch.ffhs.hdo.persistence.dto.OptionDto.OptionValues;
import ch.ffhs.hdo.persistence.jdbc.JdbcHelper;

public class OptionDao extends JdbcHelper {

	private final String SELECTALL = "SELECT * FROM PUBLIC.KONFIG";

	private final String INSERT = "INSERT INTO PUBLIC.KONFIG (KEY, VALUE, creationDate , changedate ) VALUES (?,?,CURTIME () ,CURTIME () )";

	private final String UPDATE = "UPDATE PUBLIC.KONFIG SET VALUE = ? , CHANGEDATE = CURTIME () WHERE KEY = ? ";

	private final String TIMESINCEALSTRUN = "SELECT  MAX(changedate) FROM PUBLIC.KONFIG WHERE KEY = ?  AND VALUE = ?";

	private static final String SUCCESSFULL = "SUCCESSFULL";

	public OptionDto findAllOptions() throws SQLException {

		OptionDto dto = new OptionDto();
		PreparedStatement selectAllConfig = conn.prepareStatement(SELECTALL);

		ResultSet executeQuery = selectAllConfig.executeQuery();

		while (executeQuery.next()) {
			dto.put(executeQuery.getString("key"), executeQuery.getString("value"));
		}

		return dto;

	}

	public void save(OptionDto dto, boolean newEntry) throws SQLException {

		Set<String> keySet = dto.keySet();
		PreparedStatement insertConfig = null;
		if (newEntry) {
			insertConfig = conn.prepareStatement(INSERT);

		} else {
			insertConfig = conn.prepareStatement(UPDATE);
		}

		for (String key : keySet) {
			String value = dto.get(key);

			insertConfig.setString(1, key);
			insertConfig.setString(2, value);

			insertConfig.executeUpdate();
			insertConfig.closeOnCompletion();
		}

	}

	public void protocollSortServiceRun(boolean succcessfull) throws SQLException {

		OptionDto dto = new OptionDto();
		if (succcessfull) {
			dto.put(OptionValues.LAST_SORTRUN, SUCCESSFULL);
		} else {
			dto.put(OptionValues.LAST_SORTRUN, "ERROR");
		}

		save(dto, true);
	}

	public long timeLapsed() throws SQLException {


		PreparedStatement lastRunPrepStatement = conn.prepareStatement(TIMESINCEALSTRUN);

		lastRunPrepStatement.setString(1, OptionValues.LAST_SORTRUN.getKeyName());
		lastRunPrepStatement.setString(2, SUCCESSFULL);

		final ResultSet executeQuery = lastRunPrepStatement.executeQuery();
		Date lastrun = null;
		while (executeQuery.next()) {

			lastrun = executeQuery.getTimestamp(1);
		}

		if (lastrun == null) {
			return -1;
		} else {

			long diff = new Date().getTime() - lastrun.getTime();

			return diff / 1000;

		}

	}

}
