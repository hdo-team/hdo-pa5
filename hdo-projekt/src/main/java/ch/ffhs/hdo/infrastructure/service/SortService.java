package ch.ffhs.hdo.infrastructure.service;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.swing.SwingWorker;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ch.ffhs.hdo.client.ui.einstellungen.OptionModel;
import ch.ffhs.hdo.client.ui.hauptfenster.RegelsetTableModel;
import ch.ffhs.hdo.client.ui.hauptfenster.RegelsetTableModel.ServiceStatus;
import ch.ffhs.hdo.domain.document.DocumentModel;
import ch.ffhs.hdo.domain.regel.Regelset;
import ch.ffhs.hdo.infrastructure.ApplicationSettings;
import ch.ffhs.hdo.infrastructure.option.OptionFacade;
import ch.ffhs.hdo.infrastructure.regelset.RegelsetFacade;
import ch.ffhs.hdo.infrastructure.service.util.FileHandling;

/**
 * 
 * Service die die Regel anwendet und Dateien verschieben lässt.
 * 
 * @author Denis Bittante
 *
 */

public class SortService extends SwingWorker<String, String> {
	private static Logger LOGGER = LogManager.getLogger(SortService.class);
	private RegelsetTableModel mainModel;

	/**
	 * Setter 
	 * @param mainModel
	 */
	public void setMainModel(RegelsetTableModel mainModel) {
		this.mainModel = mainModel;
	}

	private static SortService instance;

	/**
	 * Liefert die aktuelle Instanz zuruerck (Singleton)
	 * 
	 * @param mainModel
	 *            see {@link RegelsetTableModel}
	 * @return see {@link SortService}
	 */
	public static SortService getInstance(RegelsetTableModel mainModel) {

		if (instance == null) {
			instance = new SortService();
		}
		if (instance.isCancelled() || instance.isDone()) {
			instance = new SortService();
		}

		instance.setMainModel(mainModel);
		return instance;
	}

	@Override
	protected void process(List<String> chunks) {
		super.process(chunks);

	}

	@Override
	protected void done() {
		super.done();
		this.mainModel.setServiceStatus(ServiceStatus.START);
	}

	@Override
	protected String doInBackground() throws Exception {

		LOGGER.error("Start SortService doInBackground()");
		boolean firstRun = true;

		RegelsetFacade regelsetFacade = new RegelsetFacade();
		List<Regelset> regelsets = regelsetFacade.getRegelsets();

		OptionModel option = new OptionFacade().getModel();
		while (!isCancelled()) {
			// while (!mainModel.getServiceStatus().equals(ServiceStatus.STOP))
			// {
			OptionFacade facade = new OptionFacade();

			if (facade.getTimeLapsed() || firstRun) {
				LOGGER.debug("run started");
				firstRun = false;
				try {

					// Load Rulesets with Rules

					String inboxPath = ApplicationSettings.getInstance().getInbox_path();
					Collection<File> fileList = FileHandling.getFileList(inboxPath, false);

					ArrayList<DocumentModel> documentModels = new ArrayList<DocumentModel>();

					for (File file : fileList) {
						// Die Files die behandelt werden sind nur PDFs
						if (file.isFile()) {

							// if (FilenameUtils.isExtension(file.getName(), new
							// String[] { "pdf", "PDF", "Pdf" })) {
							documentModels.add(new DocumentModel(file));
						}
					}

					for (DocumentModel documentModel : documentModels) {
						for (Regelset regelset : regelsets) {
							final boolean verfizieren = regelset.verfizieren(documentModel);
							if (verfizieren) {
								if (regelset.getRenamePattern() == null || regelset.getRenamePattern().isEmpty()) {
									FileHandling.moveFile(documentModel.getFile().getAbsolutePath(),
											regelset.getPath());

								} else {
									FileHandling.moveFile(documentModel.getFile().getAbsolutePath(), regelset.getPath(),
											regelset.rename(documentModel));
								}
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

				if (!option.isAutoModus()) {
					return null;
				}

			} else {
				Thread.sleep(1000);
			}
		}

		return null;
	}
}
