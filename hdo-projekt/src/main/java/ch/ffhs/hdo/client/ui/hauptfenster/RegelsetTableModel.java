package ch.ffhs.hdo.client.ui.hauptfenster;

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

	public RegelsetTableModel(ArrayList<RegelsetModel> rulsets) {
		this.rulsets = rulsets;
	}

	public void addTableModelListener(TableModelListener l) {
		// TODO Auto-generated method stub

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
	
	public void setRulsetList(ArrayList<RegelsetModel> rulsets) {
		this.rulsets=rulsets;
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
