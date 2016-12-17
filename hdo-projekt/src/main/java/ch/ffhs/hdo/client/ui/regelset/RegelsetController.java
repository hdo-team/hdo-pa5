package ch.ffhs.hdo.client.ui.regelset;

import ch.ffhs.hdo.client.ui.base.Controller;
import ch.ffhs.hdo.client.ui.base.executable.CloseViewOperation;
import ch.ffhs.hdo.client.ui.base.executable.DefaultClosingViewExecutable;
import ch.ffhs.hdo.client.ui.base.viewhandler.ViewHandlerImpl;
import ch.ffhs.hdo.client.ui.hauptfenster.RegelsetTableModel;
import ch.ffhs.hdo.client.ui.regelset.executable.RegelsetSaveOperation;
import ch.ffhs.hdo.client.ui.regelset.executable.RegelsetSaveOperationExecutable;

/**
 * Controller für das Regelset
 * 
 * @author Daniel Crazzolara
 */

public class RegelsetController extends Controller<RegelsetModel, RegelsetView> {

	private final ViewHandlerImpl viewHandler;

	/**
	 * Konstruktor welcher das Model dem erstellten View übergibt.
	 * (Regelset bearbeiten)
	 * 
	 * @param model
	 *            Regelset Model, das ein bestehendes Regelset enthält
	 */
	public RegelsetController(RegelsetModel model) {
		super(model);

		setView(new RegelsetView(getResourceBundle()));
		viewHandler = new ViewHandlerImpl();

		setupViewHandler();
		initializeView();

	}

	/**
	 * Konstruktor welcher die Models dem erstellten View übergibt.
	 * (Regelset neu erstellen)
	 * 
	 * @param model
	 *            leeres Regelset Model 
	 * @param tableModel
	 * 			  RegelsetTableModel der Hauptuebersicht
	 */
	public RegelsetController(RegelsetModel model, RegelsetTableModel tableModel) {
		super(model);
		model.setRegelsetTableModel(tableModel);
		setView(new RegelsetView(getResourceBundle()));
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
		this.viewHandler.addOperation(CloseViewOperation.class, new DefaultClosingViewExecutable(this));
		this.viewHandler.addOperation(RegelsetSaveOperation.class, new RegelsetSaveOperationExecutable(getModel()));
	}
}
