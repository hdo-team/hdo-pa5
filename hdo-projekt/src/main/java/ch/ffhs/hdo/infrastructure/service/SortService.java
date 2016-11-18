package ch.ffhs.hdo.infrastructure.service;

import java.util.List;

import javax.swing.SwingWorker;

import ch.ffhs.hdo.client.ui.einstellungen.OptionModel;
import ch.ffhs.hdo.infrastructure.option.OptionFacade;
import ch.ffhs.hdo.persistence.dao.OptionDao;
import ch.ffhs.hdo.persistence.dto.OptionDto;
import ch.ffhs.hdo.persistence.dto.OptionDto.OptionValues;

public class SortService extends SwingWorker<String, Integer> {

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
		try {

			new OptionDao().protocollSortServiceRun(true);

			return null;
		} catch (Exception e) {
	
			new OptionDao().protocollSortServiceRun(false);

		}
		return null;
	}
}
