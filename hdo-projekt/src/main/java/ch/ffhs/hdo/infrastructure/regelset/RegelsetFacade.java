package ch.ffhs.hdo.infrastructure.regelset;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ch.ffhs.hdo.client.ui.regelset.RegelModel;
import ch.ffhs.hdo.client.ui.regelset.RegelsetModel;
import ch.ffhs.hdo.persistence.dao.RegelsetDao;
import ch.ffhs.hdo.persistence.dto.RegelsetDto;

public class RegelsetFacade {
	private static Logger LOGGER = LogManager.getLogger(RegelsetFacade.class);

	public RegelsetModel getModel() {

		RegelsetModel regelsetModel = new RegelsetModel();
		regelsetModel.setRuleModelList(new ArrayList<RegelModel>());

		return regelsetModel;
		
		/****
		// TODO: abklären ob OK
		//    -> von neues Regelset erstellen => "leeres" Ruleset-Model zurückgeben. 
		
		
		RegelsetDao dao = new RegelsetDao();

		RegelsetDto findAllRegelsets;
		try {
			findAllRegelsets = (RegelsetDto) dao.findAllRegelsets();

			RegelsetModel model = RegelsetConverter.convert(findAllRegelsets);
			return model;

		} catch (SQLException e) {
			LOGGER.error("SQL Fehler beim laden aller Regelsets", e);
		}
		return new RegelsetModel();
		 ***/
	}

	public void save(RegelsetModel model) {

		RegelsetDao dao = new RegelsetDao();

		RegelsetDto dto = RegelsetConverter.convert(model);

		try {
			// Regelsets werden geupdated nicht neu eingefügt, darum false. Ist
			// dies hier auch der Fall?
			dao.save(dto, model.getRulesetId() == null);
		} catch (SQLException e) {
			LOGGER.error("SQL Fehler beim laden aller Regelsets", e);

		}

	}

	public ArrayList<RegelsetModel> getAllRegelsets() {

		RegelsetDao dao = new RegelsetDao();
		ArrayList<RegelsetModel> regelsets = new ArrayList<RegelsetModel>();
		List<RegelsetDto> findAllRegelsets;

		try {
			findAllRegelsets = dao.findAllRegelsets();
			//regelsets.setRulsetList(RegelsetConverter.convert(findAllRegelsets));		TODO: convertMethode für <ALL> erstellen ?
			//																				  (==> dann loop nicht mehr mötig)
			for (RegelsetDto regelset : findAllRegelsets) {
				regelsets.add(RegelsetConverter.convert(regelset));
			}
		
		} catch (SQLException e) {
			LOGGER.error("SQL Fehler beim laden aller Regelsets", e);
		}
		return regelsets;

	}

	public enum PriorityAction {
		UP, DOWN;

	}

	public void swapPriority(int id, PriorityAction action) {

		RegelsetDao dao = new RegelsetDao();

		switch (action) {
		case DOWN:

			dao.changePrioDown(id);
			break;
		case UP:
			dao.changePrioUp(id);

			break;
		default:

			throw new IllegalArgumentException();
		}
	}

	public void deleteRegelset(int id) {

		RegelsetDao dao = new RegelsetDao();
		try {
			dao.deleteRegelset(id);
		} catch (SQLException e) {
			LOGGER.error("SQL Fehler beim löschen eines Regelsets", e);

		}

	}

}
