package ch.ffhs.hdo.infrastructure.regelset;

import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ch.ffhs.hdo.client.ui.regelset.RegelsetModel;
import ch.ffhs.hdo.persistence.dao.RegelsetDao;
import ch.ffhs.hdo.persistence.dto.RegelsetDto;

public class RegelsetFacade {
	private static Logger LOGGER = LogManager.getLogger(RegelsetFacade.class);

	public RegelsetModel getModel() {

		RegelsetDao dao = new RegelsetDao();

		RegelsetDto findAllRegelsets;
		try {
			findAllRegelsets = dao.findAllRegelsets();
			
			RegelsetModel model = RegelsetConverter.convert(findAllRegelsets);
			return model;

		} catch (SQLException e) {
			LOGGER.error("SQL Fehler beim laden aller Regelsets", e);
		}
		return new RegelsetModel();

	}

	public void save(RegelsetModel model) {

		RegelsetDao dao = new RegelsetDao();

		RegelsetDto dto = RegelsetConverter.convert(model);
		
		try {
			// Regelsets werden geupdated nicht neu eingefügt, darum false. Ist dies hier auch der Fall?
			dao.save(dto, false);
		} catch (SQLException e) {
			LOGGER.error("SQL Fehler beim laden aller Regelsets", e);

		}
		
	}
	
	public ArrayList<RegelsetModel> getAllRegelsets(){
		RegelsetDao dao = new RegelsetDao();
		ArrayList<RegelsetModel> regelsets=new ArrayList();
		RegelsetDto findAllRegelsets;
		
		try {
			findAllRegelsets = dao.findAllRegelsets();
			//regelsets.setRulsetList(RegelsetConverter.convert(findAllRegelsets));

		} catch (SQLException e) {
			LOGGER.error("SQL Fehler beim laden aller Regelsets", e);
		}

		//*****TEMP****
		RegelsetModel rs;
		for(int i=0; i<20; i++) {
		rs=new RegelsetModel();
		rs.setRulesetName("Regelset "+ i);
		rs.setTargetDirectory("C:/" + i);
		regelsets.add(rs);
		}
		//*************
		
		return regelsets;
		
	}
	
	
}
