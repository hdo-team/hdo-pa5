package ch.ffhs.hdo.infrastructure.option;

import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ch.ffhs.hdo.client.ui.einstellungen.OptionModel;
import ch.ffhs.hdo.persistence.dao.OptionDao;
import ch.ffhs.hdo.persistence.dto.OptionDto;

public class OptionFacade {
	private static Logger LOGGER = LogManager.getLogger(OptionFacade.class);

	public OptionModel getModel() {

		OptionDao dao = new OptionDao();

		OptionDto findAllOptions;
		try {
			findAllOptions = dao.findAllOptions();

			OptionModel model = OptionConverter.convert(findAllOptions);
			return model;

		} catch (SQLException e) {
			LOGGER.error("SQL Fehler beim laden aller Optionen", e);
		}
		return new OptionModel();

	}

	public void save(OptionModel model) {

		OptionDao dao = new OptionDao();

		OptionDto dto = OptionConverter.convert(model);

		try {
			// Optionen werden geupdated nicht neu eingefügt, darum false
			dao.save(dto, false);
		} catch (SQLException e) {
			LOGGER.error("SQL Fehler beim laden aller Optionen", e);

		}

	}

}
