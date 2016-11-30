package ch.ffhs.hdo.infrastructure.regelset;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ch.ffhs.hdo.client.ui.regelset.RegelModel;
import ch.ffhs.hdo.client.ui.regelset.RegelsetModel;
import ch.ffhs.hdo.client.ui.regelset.RegelModel.CompareTypeEnum;
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
		for(int i=0; i<30; i++) {
		rs=new RegelsetModel();
		rs.setRulesetName("Regelset "+ i);
		rs.setTargetDirectory("C:\\Target-Directory" + i);
		if (i % 2 == 0)
		rs.setFilenameKonfiguration("Rechnungen_\\dddd_-" + i + ".pdf");
		else
		rs.setFilenameKonfiguration("[Foto|Bild]-" + i + ".jpg");
		rs.setPriority(i);
		List<String> directoryList = new ArrayList<String>();    // Directory-Liste wird später NICHT aus DB geholt
		directoryList.add("C:\\Daten\\Rechnung");
		directoryList.add("C:\\Daten\\Foto");
		directoryList.add("C:\\Daten\\Divers");
		for (int j=3; j < 12; j++) {
			directoryList.add("C:\\Daten\\gugus-" + j);
		}
		directoryList.add("C:\\Daten\\Rechnungen");
		rs.setDirectories(directoryList);
		RegelModel rule1 = new RegelModel();
		rule1.setContextType(RegelModel.ContextTypeEnum.CONTEXT_PDF);
		rule1.setContextAttribute(RegelModel.ContextAttributeEnum.SIZE);
		rule1.setComparisonType(RegelModel.ComparisonTypeEnum.COMPARISON_BETWEEN);
		HashMap<CompareTypeEnum, String> compareValueMap1 = new HashMap<CompareTypeEnum, String>();
		compareValueMap1.put(RegelModel.CompareTypeEnum.COMPARE_GREATER_EQUAL, "23.03.2016");
		compareValueMap1.put(RegelModel.CompareTypeEnum.COMPARE_LESS_EQUAL, "18.10.2016");
		rule1.setCompareValueMap(compareValueMap1);
		RegelModel rule2 = new RegelModel();
		rule2.setContextType(RegelModel.ContextTypeEnum.CONTEXT_PDF);
		rule2.setContextAttribute(RegelModel.ContextAttributeEnum.AUTHOR);
		rule2.setComparisonType(RegelModel.ComparisonTypeEnum.COMPARISON_EQUAL);
		HashMap<CompareTypeEnum, String> compareValueMap2 = new HashMap<CompareTypeEnum, String>();
		compareValueMap2.put(RegelModel.CompareTypeEnum.COMPARE_EQUAL, "Schreiberling");
		rule2.setCompareValueMap(compareValueMap2);
		rs.setRuleModelList(new ArrayList<RegelModel>());
		rs.getRuleModelList().add(rule1);
		rs.getRuleModelList().add(rule2);
		
		regelsets.add(rs);
		}
		//*************
		
		return regelsets;
		
	}
	
	
}
