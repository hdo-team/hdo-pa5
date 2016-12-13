package ch.ffhs.hdo.infrastructure.service;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.swing.SwingWorker;

import org.apache.commons.io.FilenameUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ch.ffhs.hdo.client.ui.hauptfenster.RegelsetTableModel;
import ch.ffhs.hdo.domain.document.DocumentModel;
import ch.ffhs.hdo.domain.regel.Regelset;
import ch.ffhs.hdo.infrastructure.option.OptionFacade;
import ch.ffhs.hdo.infrastructure.regelset.RegelsetFacade;
import ch.ffhs.hdo.infrastructure.service.util.FileHandling;

/**
 * 
 * @author Denis Bittante
 *
 */

public class SortService extends SwingWorker<String, String> {
	private static Logger LOGGER = LogManager.getLogger(SortService.class);
	private RegelsetTableModel mainModel;

	private static SortService instance;

	public static SortService getInstance() {

		if (instance == null) {
			instance = new SortService();
		}
		return instance;
	}


	@Override
	protected void process(List<String> chunks) {
		super.process(chunks);

	}

	@Override
	protected void done() {
		super.done();
	}

	@Override
	protected String doInBackground() throws Exception {

		RegelsetFacade regelsetFacade = new RegelsetFacade();
		List<Regelset> regelsets = regelsetFacade.getRegelsets();

		System.out.println("Start doInBackground");
		while (!isCancelled()) {
			// while (!mainModel.getServiceStatus().equals(ServiceStatus.STOP))
			// {
			OptionFacade facade = new OptionFacade();
			if (facade.getTimeLapsed()) {

				try {

					// Load Rulesets with Rules

					String inboxPath = facade.getModel().getInboxPath();
					Collection<File> fileList = FileHandling.getFileList(inboxPath, false);

					ArrayList<DocumentModel> documentModels = new ArrayList<DocumentModel>();

					for (File file : fileList) {
						// Die Files die behandelt werden sind nur PDFs
						if (FilenameUtils.isExtension(file.getName(), new String[] { "pdf", "PDF", "Pdf" })) {
							documentModels.add(new DocumentModel(file));
						}
					}

					for (DocumentModel documentModel : documentModels) {
						for (Regelset regelset : regelsets) {
							final boolean verfizieren = regelset.verfizieren(documentModel);
							if (verfizieren) {
								regelset.rename(documentModel);
								FileHandling.moveFile(documentModel.getFile().getAbsolutePath(), regelset.getPath());
								break;
							}
						}
					}
					// Sortiervorgang protokollieren.
					facade.protocollSortServiceRun(true);

				} catch (Exception e) {
					LOGGER.error("Beim File Sortierservice ist ein Fehler aufgetreten ", e);
					facade.protocollSortServiceRun(false);

				}

			} else {
				Thread.sleep(1000);
			}
		}

		return null;
	}
}
