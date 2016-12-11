package ch.ffhs.hdo.client.ui.hauptfenster;

import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.event.TableModelListener;

import ch.ffhs.hdo.client.ui.base.Model;
import ch.ffhs.hdo.client.ui.regelset.RegelsetModel;

public class RegelsetTableModel extends Model {

	public enum ServiceStatus {
		DONE, PROCESSING, START, STOP;
	}

	private RegelsetAbstractTableModel abstractModel;

	private ArrayList<RegelsetModel> rulsets;

	private ServiceStatus serviceStatus;

	private boolean updateView = false;

	public RegelsetTableModel(ArrayList<RegelsetModel> rulsets) {
		this.rulsets = rulsets;
	}

	public void createAbstractTableModel(String[] columnNames) {
		abstractModel = new RegelsetAbstractTableModel(rulsets, columnNames);
	}

	public RegelsetAbstractTableModel getAbstractModel() {
		return abstractModel;
	}

	public ArrayList<RegelsetModel> getRulsetList() {
		return rulsets;
	}

	public void setUpdateView(boolean updateView) {

		boolean oldValue = this.updateView;
		this.updateView = updateView;
		firePropertyChange("updateView", oldValue, updateView);

	}

	public boolean getUpdateView() {

		return updateView;
	}

	public void setRulsetList(ArrayList<RegelsetModel> rulsets) {
		ArrayList<RegelsetModel> oldValue = this.rulsets;
		this.rulsets = rulsets;
		firePropertyChange("rulsets", oldValue, rulsets);
		abstractModel.setRulsets(rulsets);
		abstractModel.fireTableDataChanged();

	}

	public ServiceStatus getServiceStatus() {
		return serviceStatus;
	}

	public void removeTableModelListener(TableModelListener l) {
		// TODO Auto-generated method stub

	}

	public void setServiceStatus(ServiceStatus serviceStatus) {
		ServiceStatus oldValue = this.serviceStatus;
		this.serviceStatus = serviceStatus;
		firePropertyChange("serviceStatus", oldValue, serviceStatus);
	}

}
