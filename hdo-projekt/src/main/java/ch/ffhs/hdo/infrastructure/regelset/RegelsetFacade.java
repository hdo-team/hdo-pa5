package ch.ffhs.hdo.infrastructure.regelset;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ch.ffhs.hdo.client.ui.regelset.RegelModel;
import ch.ffhs.hdo.client.ui.regelset.RegelsetModel;
import ch.ffhs.hdo.domain.regel.Regelset;
import ch.ffhs.hdo.persistence.dao.RegelDao;
import ch.ffhs.hdo.persistence.dao.RegelsetDao;
import ch.ffhs.hdo.persistence.dto.RegelDto;
import ch.ffhs.hdo.persistence.dto.RegelsetDto;

public class RegelsetFacade {
	private static Logger LOGGER = LogManager.getLogger(RegelsetFacade.class);

	public void save(RegelsetModel model) {

		RegelsetDao regelsetDao = new RegelsetDao();
		RegelsetDto regelsetDto = RegelsetConverter.convert(model);

		RegelDao regelDao = new RegelDao();
		RegelDto regelDto = null;
		List<RegelDto> regelDtoList = new ArrayList<RegelDto>();

		try {
			Integer newRulesetId = regelsetDao.save(regelsetDto, model.getRulesetId() == null);
			if (newRulesetId != null) {
				// update model with the new primaryKey from of the inserted
				// ruleset
				model.setRulesetId(newRulesetId);
			} else {
				// delete old rules form the updated ruleset
				// TODO: terminates checken regelDao hatte hier schon "Session
				// is closed"
				regelDao = new RegelDao();
				regelDao.deleteAllRegelnByRegelsetId(model.getRulesetId());
			}
			for (RegelModel regelModel : model.getRuleModelList()) {
				regelDto = RegelConverter.convert(regelModel, model.getRulesetId());
				regelDtoList.add(regelDto);
			}
			// TODO: terminates checken regelDao hatte hier schon "Session is
			// closed"
			regelDao = new RegelDao();
			regelDao.save(regelDtoList);

		} catch (SQLException e) {
			LOGGER.error("SQL Fehler beim Saven aller Regelsets", e);

		}

	}

	public ArrayList<RegelsetModel> getAllRegelsets() {

		RegelsetDao daoRegelset = new RegelsetDao();
		RegelDao daoRegel = new RegelDao();
		ArrayList<RegelsetModel> regelsets = new ArrayList<RegelsetModel>();
		List<RegelsetDto> findAllRegelsets;

		try {
			findAllRegelsets = daoRegelset.findAllRegelsets();

			for (RegelsetDto regelsetDto : findAllRegelsets) {
				regelsetDto.setRegeln(daoRegel.findAllRegelByRegelsetId(regelsetDto.getId()));

				regelsets.add(RegelsetConverter.convert(regelsetDto));
			}

		} catch (SQLException e) {
			LOGGER.error("SQL Fehler beim Laden aller Regelsets", e);
		}
		return regelsets;

	}

	public enum PriorityAction {
		UP, DOWN;

	}

	public void swapPriority(int id, PriorityAction action) {

		RegelsetDao dao = new RegelsetDao();

		try {
			switch (action) {
			case DOWN:

				dao.changePrioDown(id);
				break;
			case UP:
				dao.changePrioUp(id);
				break;
			default:

			}
		} catch (SQLException e) {
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

	public List<Regelset> getRegelsets() {

		RegelsetDao daoRegelset = new RegelsetDao();
		RegelDao daoRegel = new RegelDao();
		ArrayList<Regelset> regelsets = new ArrayList<Regelset>();
		List<RegelsetDto> findAllRegelsets;

		try {
			findAllRegelsets = daoRegelset.findAllRegelsets();

			for (RegelsetDto regelsetDto : findAllRegelsets) {
				regelsetDto.setRegeln(daoRegel.findAllRegelByRegelsetId(regelsetDto.getId()));

				final Regelset convertToRegelset = RegelsetConverter.convertToRegelset(regelsetDto);
				if (convertToRegelset != null) {
					regelsets.add(convertToRegelset);
				}
			}

		} catch (SQLException e) {
			LOGGER.error("SQL Fehler beim Laden aller Regelsets", e);
		}
		return regelsets;

	}

}
