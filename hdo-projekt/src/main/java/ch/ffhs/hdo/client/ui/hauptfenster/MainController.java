package ch.ffhs.hdo.client.ui.hauptfenster;

import ch.ffhs.hdo.client.ui.base.Controller;
import ch.ffhs.hdo.client.ui.base.executable.CloseViewOperation;
import ch.ffhs.hdo.client.ui.base.executable.DefaultClosingViewExecutable;
import ch.ffhs.hdo.client.ui.base.viewhandler.ViewHandlerImpl;
import ch.ffhs.hdo.client.ui.einstellungen.executable.OptionViewStartExecutable;
import ch.ffhs.hdo.client.ui.einstellungen.executable.OptionViewStartOperation;
import ch.ffhs.hdo.client.ui.export.executable.ExportViewStartExecutable;
import ch.ffhs.hdo.client.ui.export.executable.ExportViewStartOperation;
import ch.ffhs.hdo.client.ui.imports.executable.ImportViewStartExecutable;
import ch.ffhs.hdo.client.ui.imports.executable.ImportViewStartOperation;
import ch.ffhs.hdo.client.ui.regelset.executable.RegelsetDeleteOperation;
import ch.ffhs.hdo.client.ui.regelset.executable.RegelsetDeleteOperationExecutable;
import ch.ffhs.hdo.client.ui.regelset.executable.RegelsetViewStartExecutable;
import ch.ffhs.hdo.client.ui.regelset.executable.RegelsetViewStartOperation;
import ch.ffhs.hdo.infrastructure.service.executable.ServiceStartOperation;
import ch.ffhs.hdo.infrastructure.service.executable.ServiceStartOperationExecutable;

/**
 * Controller für das Hauptfenster
 * 
 * @author Jonas Segessemann
 */
public class MainController extends Controller<MainModel, MainView> {

	private ViewHandlerImpl viewHandler;

	public MainController(MainModel model, RegelsetTableModel regelsetModel) {
		super(model);
		model.setRegelsetModel(regelsetModel);

		this.viewHandler = new ViewHandlerImpl();

		setView(new MainView(getResourceBundle(), model));

		initializeView();
		initlizeHandler();
	}

	private void initlizeHandler() {

		this.viewHandler.addOperation(OptionViewStartOperation.class, new OptionViewStartExecutable());
		this.viewHandler.addOperation(RegelsetViewStartOperation.class, new RegelsetViewStartExecutable());
		this.viewHandler.addOperation(ExportViewStartOperation.class, new ExportViewStartExecutable());
		this.viewHandler.addOperation(ImportViewStartOperation.class, new ImportViewStartExecutable());
		this.viewHandler.addOperation(CloseViewOperation.class, new DefaultClosingViewExecutable(this));
		this.viewHandler.addOperation(RegelsetDeleteOperation.class, new RegelsetDeleteOperationExecutable());
		this.viewHandler.addOperation(ServiceStartOperation.class,
				new ServiceStartOperationExecutable(getModel().getRegelsetModel()));

	}

	@Override
	public void initializeView() {
		getView().setResourceBundle(getResourceBundle());
		getView().setModel(getModel());
		getView().setHandler(viewHandler);

		getView().getRegelsetTableView().setModel(getModel().getRegelsetModel());
		getView().getRegelsetTableView().setHandler(viewHandler);
	}

}
