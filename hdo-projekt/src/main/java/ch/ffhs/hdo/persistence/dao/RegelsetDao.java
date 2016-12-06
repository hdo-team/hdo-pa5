package ch.ffhs.hdo.persistence.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ch.ffhs.hdo.persistence.dto.RegelsetDto;
import ch.ffhs.hdo.persistence.jdbc.JdbcHelper;

public class RegelsetDao extends JdbcHelper {

	private final String SELECTRULESETS = "SELECT RULESET.* FROM RULESET ORDER BY priority ASC";
	// private final String SELECTRULESET = SELECTRULESETS + " WHERE id = ?";
	// private final String SELECTRULES = "SELECT RULESET.* FROM RULESET";
	// private final String SELECTRULE = SELECTRULES + " WHERE id = ?";
	// private final String SELECTRULEBYRULESET = SELECTRULES + " WHERE
	// rulesetId = ?";

	private final String FIND_BIGGER_PRIO = "(Select id from Ruleset where priority = (SELECT min(priority) from RULESET where priority > (SELECT priority  FROM RULESET where id = ?)))";
	private final String SWAP_DOWN = "UPDATE RULESET SET priority = ( SELECT SUM(priority) FROM RULESET WHERE id IN (?, "
			+ FIND_BIGGER_PRIO + ") ) - priority WHERE id IN (?, " + FIND_BIGGER_PRIO + ")";

	private final String FIND_SMALLER_PRIO = "(Select id from Ruleset where priority = (SELECT max(priority) from RULESET where priority < (SELECT priority  FROM RULESET where id = ?)))";
	private final String SWAP_UP = "UPDATE RULESET SET priority = ( SELECT SUM(priority) FROM RULESET WHERE id IN (?, "
			+ FIND_SMALLER_PRIO + ") ) - priority WHERE id IN (?, " + FIND_SMALLER_PRIO + ")";

	private final String INSERT = "INSERT INTO RULESET (targetDirectory, rulesetName, newFilename, filenameCounter, priority, active, creationDate, changedate) VALUES (?,?,?,?,?,?, CURTIME () ,CURTIME () )";

	private final String UPDATE = "UPDATE RULESET SET VALUE = ? , CHANGEDATE = CURTIME () WHERE KEY = ? ";

	private final String DELETE_RULE = "DELETE FROM RULE where regelsetId = ?";
	private final String DELETE_RULESET = "DELETE FROM RULESET where id = ?";

	public List<RegelsetDto> findAllRegelsets() throws SQLException {

		PreparedStatement selectAllRegelsets = conn.prepareStatement(SELECTRULESETS);

		ResultSet executeQuery = selectAllRegelsets.executeQuery();

		List<RegelsetDto> regelsetlist = new ArrayList<RegelsetDto>();
		while (executeQuery.next()) {

			RegelsetDto dto = new RegelsetDto();
			dto.setId(executeQuery.getInt("id"));
			dto.setActive(executeQuery.getBoolean("active"));
			dto.setNewFilename(executeQuery.getString("newFilename"));
			dto.setTargetDirectory(executeQuery.getString("targetDirectory"));
			dto.setChangedate(executeQuery.getDate("changedate"));
			dto.setCreationDate(executeQuery.getDate("creationDate"));
			dto.setFilenameCounter(executeQuery.getLong("filenameCounter"));
			dto.setPrority(executeQuery.getInt("priority"));
			dto.setRulesetName(executeQuery.getString("rulesetName"));

			regelsetlist.add(dto);

		}

		return regelsetlist;
	}

	public void save(RegelsetDto dto, boolean newEntry) throws SQLException {
		// TODO: DB: to implement
		PreparedStatement insertRegelset = null;

		if (newEntry) {
			insertRegelset = conn.prepareStatement(INSERT);

		} else {

			// deleteRegelset(dto.getId());
			// insertRegelset = conn.prepareStatement(DELETE_RULESET);

		}

		insertRegelset.setString(1, dto.getRulesetName());
		insertRegelset.setString(2, dto.getTargetDirectory());
		insertRegelset.setString(3, dto.getNewFilename());
		// insertRegelset.setLong(4, dto.getFilenameCounter()); // Bitte
		// hinzufügen
		// insertRegelset.setInt(5, dto.getPrority()); // Bitte hinzufügen
		insertRegelset.setInt(4, 0); // Bitte nach Anpassung entfernen
		insertRegelset.setInt(5, 0); // Bitte nach Anpassung entfernen
		insertRegelset.setBoolean(6, dto.isActive());

		insertRegelset.executeUpdate();
		final ResultSet generatedKeys = insertRegelset.getGeneratedKeys();
		while (generatedKeys.next()) {
			System.out.println(generatedKeys.getInt("id"));
		}

	}

	public void changePrioDown(int id) throws SQLException {

		final PreparedStatement ruleset = conn.prepareStatement(SWAP_DOWN );
		ruleset.setInt(1, id);
		ruleset.setInt(2, id);
		ruleset.setInt(3, id);
		ruleset.setInt(4, id);
		final int executeUpdate = ruleset.executeUpdate();

	}

	public void changePrioUp(int id) throws SQLException {

		final PreparedStatement ruleset = conn.prepareStatement(SWAP_UP);
		ruleset.setInt(1, id);
		ruleset.setInt(2, id);
		ruleset.setInt(3, id);
		ruleset.setInt(4, id);
		ruleset.executeUpdate();

	}

	public void deleteRegelset(int regelset_id) throws SQLException {

		try {
			conn.setAutoCommit(false);

			final PreparedStatement deleteRule = conn.prepareStatement(DELETE_RULE);
			deleteRule.setInt(1, regelset_id);
			deleteRule.executeUpdate();

			final PreparedStatement deleteRuleset = conn.prepareStatement(DELETE_RULESET);
			deleteRuleset.setInt(1, regelset_id);
			deleteRuleset.executeUpdate();
			conn.commit();
			conn.setAutoCommit(true);

		} catch (SQLException e) {
			conn.rollback();
			throw new SQLException(e);

		}

	}

}