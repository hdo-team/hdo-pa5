package ch.ffhs.hdo.client.ui.export;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;

import ch.ffhs.hdo.client.ui.base.Controller;

/**
 * Controller für die den Konfigurations-Export
 * 
 * @author Jonas Segessemann
 */
public class ExportController extends Controller<ExportModel, ExportView> {

	public ExportController(ExportModel model) {
		super(model);

		setView(new ExportView(getResourceBundle()));

		initializeView();

	}

	@Override
	public void initializeView() {
		getView().setResourceBundle(getResourceBundle());
		getView().setModel(getModel());
		getView().addExportButtonListener(new ExportButtonListener());
	}


	class ExportButtonListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
			//TODO export configuration
        	System.out.println("Export to: "+getView().getSelectedPath());
		}
    }
	
	
}
