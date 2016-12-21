package ch.ffhs.hdo.infrastructure.option;

import java.sql.SQLException;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ch.ffhs.hdo.client.ui.einstellungen.OptionModel;
import ch.ffhs.hdo.persistence.dao.OptionDao;
import ch.ffhs.hdo.persistence.dto.OptionDto;

/**
 * Facade für Einstellungen/Optionen
 * 
 * @author Denis Bittante
 *
 */
public class OptionFacade {
	private static Logger LOGGER = LogManager.getLogger(OptionFacade.class);

	/**
	 * Gibt Option Model aus der Datenbank zurueck
	 * 
	 * @return see {@link OptionModel}
	 */
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

	public void protocollSortServiceRun(boolean successfull) {
		OptionDao dao = new OptionDao();
		try {
			dao.protocollSortServiceRun(successfull);
		} catch (SQLException e) {
			LOGGER.error("SQL Fehler beim protokollieren des Sortiervorgang", e);
		}
	}

	public void save(OptionModel model) {

		OptionDao dao = new OptionDao();

		OptionDto dto = OptionConverter.convert(model);

		try {
			// Optionen werden geupdated nicht neu eingefuegt, darum false
			dao.save(dto, false);
		} catch (SQLException e) {
			LOGGER.error("SQL Fehler beim laden aller Optionen", e);

		}

	}

	public boolean getTimeLapsed() {

		OptionDao dao = new OptionDao();
		try {
			final long timeLapsed = dao.timeLapsed();

			if (timeLapsed == -1) {
				return true;
			} else {
				final OptionModel model = getModel();

				if (timeLapsed > model.getIntervall()) {
					return true;
				} else {
					return false;
				}
			}

		} catch (SQLException e) {
			LOGGER.error("SQL Fehler beim Laden der Lapsed-Time: ", e);
		}
		return false;

	}

}
