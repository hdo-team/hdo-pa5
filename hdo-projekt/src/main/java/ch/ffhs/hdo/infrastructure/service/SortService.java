package ch.ffhs.hdo.infrastructure.service;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.swing.SwingWorker;

import org.apache.commons.io.FilenameUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ch.ffhs.hdo.client.ui.einstellungen.OptionModel;
import ch.ffhs.hdo.domain.document.DocumentModel;
import ch.ffhs.hdo.infrastructure.option.OptionFacade;
import ch.ffhs.hdo.infrastructure.service.util.FileHandling;
import ch.ffhs.hdo.persistence.dao.OptionDao;

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

			String inboxPath = model.getInboxPath();
			Collection<File> fileList = FileHandling.getFileList(inboxPath, false);

			ArrayList<DocumentModel> documentModels = new ArrayList<DocumentModel>();

			for (File file : fileList) {
				
				//Die Files die behandelt werden sind nur PDFs
				if (FilenameUtils.isExtension(file.getName(), new String[] { "pdf", "PDF","Pdf" })) {
					documentModels.add(new DocumentModel(file));
				}


			}

			// Sortiervorgang protokollieren.

			optionDao.protocollSortServiceRun(true);

			return null;
		} catch (Exception e) {
			LOGGER.error("Beim File Sortierservice ist ein Fehler aufgetreten ", e);
			optionDao.protocollSortServiceRun(false);

		}
		return null;
	}
}
