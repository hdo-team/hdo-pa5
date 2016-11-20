package ch.ffhs.hdo.infrastructure.regelset;

import java.sql.SQLException;
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
			/**
			RegelsetModel model = RegelsetConverter.convert(findAllRegelsets);
			return model;*/

		} catch (SQLException e) {
			LOGGER.error("SQL Fehler beim laden aller Regelseten", e);
		}
		return new RegelsetModel();

	}

	public void save(RegelsetModel model) {

		RegelsetDao dao = new RegelsetDao();

		//RegelsetDto dto = RegelsetConverter.convert(model);

		/**
		try {
			// Regelseten werden geupdated nicht neu eingefügt, darum false
			dao.save(dto, false);
		} catch (SQLException e) {
			LOGGER.error("SQL Fehler beim laden aller Regelsets", e);

		}*/

	}
}
