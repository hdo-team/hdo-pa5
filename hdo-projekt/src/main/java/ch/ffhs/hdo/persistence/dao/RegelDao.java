package ch.ffhs.hdo.persistence.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ch.ffhs.hdo.persistence.dto.RegelDto;
import ch.ffhs.hdo.persistence.jdbc.JdbcHelper;

public class RegelDao extends JdbcHelper {

	private final String SELECTRULEBYRULESETID = "SELECT * FROM RULE WHERE rulesetId = ?";

	private final String INSERT = "INSERT INTO RULE (RULESETID, CONTEXTTYPE ,CONTEXTATTRIBUTE,COMPARETYPE, COMPAREVALUE, CREATIONDATE, CHANGEDATE ) VALUES (?,?,?,?,?, CURTIME (), CURTIME ()) ";

	private final String DELETERULEBYRULESETID = "DELETE FROM RULE WHERE rulesetId = ?";
	
	
	public RegelDao() {
		super();
	}

	public List<RegelDto> findAllRegelByRegelsetId(Integer id) throws SQLException {

		PreparedStatement selectAllRegel = conn.prepareStatement(SELECTRULEBYRULESETID);
		selectAllRegel.setInt(1, id);
		ResultSet executeQuery = selectAllRegel.executeQuery();

		List<RegelDto> regelDtos = new ArrayList<RegelDto>();

		while (executeQuery.next()) {

			final RegelDto regel = new RegelDto();
			regel.setChangeDate(executeQuery.getDate("changedate"));
			regel.setCompareType(executeQuery.getString("comparetype"));
			regel.setCompareValue(executeQuery.getString("comparevalue"));
			regel.setContextAttribute(executeQuery.getString("contextattribute"));
			regel.setContextType(executeQuery.getString("contexttype"));
			regel.setCreationDate(executeQuery.getDate("creationdate"));
			regel.setId(executeQuery.getInt("id"));
			regel.setRulesetId(executeQuery.getInt("rulesetid"));

			regelDtos.add(regel);
		}

		return regelDtos;

	}

	public void deleteAllRegelnByRegelsetId(Integer regelsetId) throws SQLException {

		final PreparedStatement deleteRegeln = conn.prepareStatement(DELETERULEBYRULESETID);
		deleteRegeln.setInt(1, regelsetId);
		deleteRegeln.executeUpdate();

		conn.commit();
		conn.setAutoCommit(true);
		
	}
	
	public void save(List<RegelDto> regelDtos) throws SQLException {

		PreparedStatement insertRegelset = conn.prepareStatement(INSERT);

		conn.setAutoCommit(false);

		try {

			for (RegelDto regelDto : regelDtos) {
				insertRegelset.setInt(1, regelDto.getRulesetId());
				insertRegelset.setString(2, regelDto.getContextType());
				insertRegelset.setString(3, regelDto.getContextAttribute());
				insertRegelset.setString(4, regelDto.getCompareType());
				insertRegelset.setString(5, regelDto.getCompareValue());
				insertRegelset.executeUpdate();
			}

			conn.commit();
			conn.setAutoCommit(true);

		} catch (SQLException e) {
			conn.rollback();
			throw new SQLException(e);

		}
	}

}
