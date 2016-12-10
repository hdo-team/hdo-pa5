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
import ch.ffhs.hdo.client.ui.hauptfenster.RegelsetTableModel;
import ch.ffhs.hdo.client.ui.hauptfenster.RegelsetTableModel.ServiceStatus;
import ch.ffhs.hdo.domain.document.DocumentModel;
import ch.ffhs.hdo.infrastructure.option.OptionFacade;
import ch.ffhs.hdo.infrastructure.service.util.FileHandling;
import ch.ffhs.hdo.persistence.dao.OptionDao;

public class SortService extends SwingWorker<String, Integer> {
	private static Logger LOGGER = LogManager.getLogger(SortService.class);
	private RegelsetTableModel mainModel;

	public SortService(RegelsetTableModel model) {
		this.mainModel = model;
	}

	@Override
	protected void process(List<Integer> chunks) {
		super.process(chunks);
		mainModel.setServiceStatus(ServiceStatus.START);

	}

	@Override
	protected void done() {
		super.done();
		mainModel.setServiceStatus(ServiceStatus.START);
	}

	
	
	@Override
	protected String doInBackground() throws Exception {
		
		
		System.out.println("Start doInBackground");
		
		OptionDao optionDao = new OptionDao();
		System.out.println("in try");
		try {
			
			OptionFacade facade = new OptionFacade();
			
			facade.getTimeLapsed();
			System.out.println("called time lapsed");
			OptionModel model = facade.getModel();

			
			int intervall = model.getIntervall();
			LOGGER.debug("Intervall geladen: " + intervall);

			String inboxPath = model.getInboxPath();
			Collection<File> fileList = FileHandling.getFileList(inboxPath, false);

			ArrayList<DocumentModel> documentModels = new ArrayList<DocumentModel>();

			for (File file : fileList) {

				mainModel.setServiceStatus(ServiceStatus.PROCESSING);

				// Die Files die behandelt werden sind nur PDFs
				if (FilenameUtils.isExtension(file.getName(), new String[] { "pdf", "PDF", "Pdf" })) {
					documentModels.add(new DocumentModel(file));
				}
				
				if (mainModel.getServiceStatus().equals(ServiceStatus.STOP)) {
					break;
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
