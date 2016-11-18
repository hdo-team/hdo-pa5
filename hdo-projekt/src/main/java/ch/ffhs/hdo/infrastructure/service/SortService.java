package ch.ffhs.hdo.infrastructure.service;

import java.util.List;

import javax.swing.SwingWorker;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ch.ffhs.hdo.client.ui.einstellungen.OptionModel;
import ch.ffhs.hdo.infrastructure.option.OptionFacade;
import ch.ffhs.hdo.persistence.dao.OptionDao;
import ch.ffhs.hdo.persistence.dto.OptionDto;
import ch.ffhs.hdo.persistence.dto.OptionDto.OptionValues;

public class SortService extends SwingWorker<String, Integer> {
	private static Logger LOGGER = LogManager.getLogger(SortService.class);

	public SortService() {
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void process(List<Integer> chunks) {
		super.process(chunks);

	}

	@Override
	protected void done() {
		super.done();
	}

	@Override
	protected String doInBackground() throws Exception {
		OptionDao optionDao = new OptionDao();
		try {

			OptionFacade facade = new OptionFacade();
			OptionModel model = facade.getModel();

			int intervall = model.getIntervall();
			LOGGER.debug("Intervall geladen: " + intervall);

			optionDao.protocollSortServiceRun(true);

			return null;
		} catch (Exception e) {
			LOGGER.error("Beim File Sortierservice ist ein Fehler aufgetreten ", e);
			optionDao.protocollSortServiceRun(false);

		}
		return null;
	}
}
