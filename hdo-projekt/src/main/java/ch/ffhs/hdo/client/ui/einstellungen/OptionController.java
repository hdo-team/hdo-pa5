package ch.ffhs.hdo.client.ui.einstellungen;

import ch.ffhs.hdo.client.ui.base.Controller;
import ch.ffhs.hdo.client.ui.base.executable.CloseViewOperation;
import ch.ffhs.hdo.client.ui.base.executable.DefaultClosingViewExecutable;
import ch.ffhs.hdo.client.ui.base.viewhandler.ViewHandlerImpl;
import ch.ffhs.hdo.client.ui.einstellungen.executable.OptionsSaveExecutable;
import ch.ffhs.hdo.client.ui.einstellungen.executable.OptionsSaveOperation;
import ch.ffhs.hdo.client.ui.hauptfenster.FolderTreeModel;
import ch.ffhs.hdo.client.ui.utils.ChooseDirectoryPathViewOperation;
import ch.ffhs.hdo.client.ui.utils.FolderChooserExecuter;

/**
 * Controller für die Options
 * 
 * @author Denis Bittante
 */
public class OptionController extends Controller<OptionModel, OptionView> {

	private final ViewHandlerImpl viewHandler;

	/**
	 * 
	 * Controller Konstruktor welche Models der erstellten View übergibt.
	 * 
	 * @param model
	 *            Option Model welches die Einstellungen aus der DB enhält.
	 * @param folderModel
	 *            Verzeichnisstruktur, bei welcher der auswählbare Inbox Pfad
	 *            hinterlegt ist.
	 */
	public OptionController(OptionModel model, FolderTreeModel folderModel) {
		super(model);
		model.setFolderModel(folderModel);
		setView(new OptionView(getResourceBundle()));
		viewHandler = new ViewHandlerImpl();

		setupViewHandler();

		initializeView();

	}

	/**
	 * Inizialisierung der erstellten View.
	 */
	@Override
	public void initializeView() {
		getView().setResourceBundle(getResourceBundle());
		getView().setModel(getModel());
		getView().setHandler(viewHandler);
	}

	/**
	 * Fügt die ausführbaren Optionen dem view Handler hinzu.
	 */
	private void setupViewHandler() {
		viewHandler.addOperation(CloseViewOperation.class, new DefaultClosingViewExecutable(this));
		viewHandler.addOperation(OptionsSaveOperation.class, new OptionsSaveExecutable(getModel()));
		viewHandler.addOperation(ChooseDirectoryPathViewOperation.class, new FolderChooserExecuter(getModel()));

	}

}
