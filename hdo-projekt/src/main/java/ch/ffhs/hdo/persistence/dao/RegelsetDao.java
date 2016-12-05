package ch.ffhs.hdo.persistence.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ch.ffhs.hdo.persistence.dto.RegelDto;
import ch.ffhs.hdo.persistence.dto.RegelsetDto;
import ch.ffhs.hdo.persistence.jdbc.JdbcHelper;

public class RegelsetDao extends JdbcHelper {

	private final String SELECTRULESETS = "SELECT RULESET.* FROM RULESET";

	private final String SELECTRULESET = SELECTRULESETS + " WHERE id = ?";
	private final String SELECTRULES = "SELECT RULE.* FROM RULE";
	private final String SELECTRULE = SELECTRULES + " WHERE id = ?";
	private final String SELECTRULEBYRULESET = SELECTRULES + " WHERE rulesetId = ?";


	
	private final String INSERT_RULESET = "INSERT INTO RULESET (targetDirectory, rulesetName, newFilename, filenameCounter, priority, active, creationDate, changedate) VALUES (?,?,?,?,?,?, CURTIME () ,CURTIME () )";
	private final String INSERT_RULE    = "INSERT INTO RULE (rulesetId, contextType, contextAttribute, compareType, compareValue, creationDate, changedate) VALUES (?,?,?,?,?, CURTIME () ,CURTIME () )";

	private final String UPDATE = "UPDATE RULESET SET VALUE = ? , CHANGEDATE = CURTIME () WHERE KEY = ? ";

	private final String DELETE_RULE = "DELETE FROM RULE where rulesetId = ?";
	private final String DELETE_RULESET = "DELETE FROM RULESET where id = ?";

	public List<RegelsetDto> findAllRegelsets() throws SQLException {

		PreparedStatement selectAllRegelsets = conn.prepareStatement(SELECTRULESETS);
		PreparedStatement selectAllRegeln = conn.prepareStatement(SELECTRULEBYRULESET);

		ResultSet executeQueryRegelset = selectAllRegelsets.executeQuery();

		List<RegelsetDto> regelsetlist = new ArrayList<RegelsetDto>();
		while (executeQueryRegelset.next()) {

			RegelsetDto regelsetDto = new RegelsetDto();
			regelsetDto.setId(executeQueryRegelset.getInt("id"));
			regelsetDto.setActive(executeQueryRegelset.getBoolean("active"));
			regelsetDto.setNewFilename(executeQueryRegelset.getString("newFilename"));
			regelsetDto.setTargetDirectory(executeQueryRegelset.getString("targetDirectory"));
			regelsetDto.setChangedate(executeQueryRegelset.getDate("changedate"));
			regelsetDto.setCreationDate(executeQueryRegelset.getDate("creationDate"));
			regelsetDto.setFilenameCounter(executeQueryRegelset.getLong("filenameCounter"));
			regelsetDto.setPrority(executeQueryRegelset.getInt("priority"));
			regelsetDto.setRulesetName(executeQueryRegelset.getString("rulesetName"));
			regelsetDto.setRegeln(new ArrayList<RegelDto>());
			
			// pro Regelset alle Regeln lesen
			selectAllRegeln.setInt(1, regelsetDto.getId());
			ResultSet executeQueryRegel = selectAllRegeln.executeQuery();
	
			while (executeQueryRegel.next()) {
				RegelDto regelDto = new RegelDto();
				regelDto.setId(executeQueryRegel.getInt("id"));
				regelDto.setRulesetId(executeQueryRegel.getInt("rulesetId"));
				regelDto.setContextType(executeQueryRegel.getString("contextType"));
				regelDto.setContextAttribute(executeQueryRegel.getString("contextAttribute"));
				regelDto.setCompareType(executeQueryRegel.getString("compareType"));
				regelDto.setCompareValue(executeQueryRegel.getString("compareValue"));
				regelDto.setCreationDate(executeQueryRegel.getDate("creationDate"));
				regelDto.setChangeDate(executeQueryRegel.getDate("changeDate"));
				
				regelsetDto.getRegeln().add(regelDto);
			}
			regelsetlist.add(regelsetDto);

		}

		return regelsetlist;
	}

	public void save(RegelsetDto regelsetDto, boolean newEntry) throws SQLException {
		// TODO: DB: to implement
		PreparedStatement insertRegelset = null;

		PreparedStatement insertRegel = null;

		if (!newEntry) {
			// delete the old entry and insert the new new one 
			deleteRegelset(regelsetDto.getId());
		}
		insertRegelset = conn.prepareStatement(INSERT_RULESET);
		insertRegelset.setString(1, regelsetDto.getTargetDirectory());
		insertRegelset.setString(2, regelsetDto.getRulesetName());
		insertRegelset.setString(3, regelsetDto.getNewFilename());
		insertRegelset.setLong(4, regelsetDto.getFilenameCounter()); 
		insertRegelset.setInt(5, regelsetDto.getPrority());
		insertRegelset.setBoolean(6, regelsetDto.isActive());
		
		insertRegelset.executeUpdate();		

		for (RegelDto regelDto : regelsetDto.getRegeln()) {
			insertRegel = conn.prepareStatement(INSERT_RULE);
			insertRegel.setInt(1, regelsetDto.getId());
			insertRegel.setString(2, regelDto.getContextType());
			insertRegel.setString(3, regelDto.getContextAttribute());
			insertRegel.setString(4, regelDto.getCompareType());
			insertRegel.setString(5, regelDto.getCompareValue());
		}

		terminate();
	}

	public void changePrioDown(int id) {
		// TODO: DB: to implement

		// UPDATE regelset SET priority (Select min(priority)+1 from Regelset
		// where priority < ? ) where id = ? ;

	}

	public void changePrioUp(int id) {
		// TODO: DB: to implement

		// UPDATE regelset SET priority (Select max(priority)-1 from Regelset
		// where priority > ? ) where id = ? ;

	}

	public void deleteRegelset(int regelsetId) throws SQLException {

		try {
			conn.setAutoCommit(false);

			final PreparedStatement deleteRule = conn.prepareStatement(DELETE_RULE);
			deleteRule.setInt(1, regelsetId);
			deleteRule.executeUpdate();

			final PreparedStatement deleteRuleset = conn.prepareStatement(DELETE_RULESET);
			deleteRuleset.setInt(1, regelsetId);
			deleteRuleset.executeUpdate();
			conn.commit();
			conn.setAutoCommit(true);

		} catch (SQLException e) {
			conn.rollback();
			throw new SQLException(e);

		}

	}

}