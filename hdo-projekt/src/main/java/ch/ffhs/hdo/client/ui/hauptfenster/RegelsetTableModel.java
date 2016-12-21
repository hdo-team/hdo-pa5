package ch.ffhs.hdo.client.ui.hauptfenster;

import java.util.ArrayList;

import javax.swing.event.TableModelListener;

import ch.ffhs.hdo.client.ui.base.Model;
import ch.ffhs.hdo.client.ui.regelset.RegelsetModel;

/**
 * Das Model der angezeigten Regelset Tabelle und die Navigationsliste im
 * Haputfenster.
 * 
 * @author Jonas Segessemann
 *
 */
public class RegelsetTableModel extends Model {
	/**
	 * Enum welcher alle Service-Stati enthaelt. Mögliche Status sind:<br>
	 * 
	 * {@link ServiceStatus#START} <br>
	 * {@link ServiceStatusEnum#STOP}
	 * 
	 * @author Jonas Segessemann
	 * 
	 */
	public enum ServiceStatus {
		/**
		 * Der Service ist gestoppt er kann gestartet werden
		 */
		START,
		/**
		 * Der Service ist gestartet und kann gestoppt werden.
		 */
		STOP;
	}

	private RegelsetAbstractTableModel abstractModel;

	private ArrayList<RegelsetModel> rulsets;

	private ServiceStatus serviceStatus = ServiceStatus.START;

	private boolean updateView = false;

	/**
	 * Erstellt ein Objekt mit dem Table Model.
	 * 
	 * @param rulsets
	 *            ArrayList aus allen Regelsets.
	 */
	public RegelsetTableModel(ArrayList<RegelsetModel> rulsets) {
		this.rulsets = rulsets;
	}

	/**
	 * Erstellt neues {@link RegelsetAbstractTableModel}
	 * 
	 * @param columnNames
	 *            Namen der Spalten
	 */
	public void createAbstractTableModel(String[] columnNames) {
		abstractModel = new RegelsetAbstractTableModel(rulsets, columnNames);
	}

	/**
	 * Getter AbstrModel
	 * 
	 * @return see {@link RegelsetAbstractTableModel}
	 */
	public RegelsetAbstractTableModel getAbstractModel() {
		return abstractModel;
	}

	/**
	 * Getter RegelsetList
	 * 
	 * @return Liste von {@link RegelsetModel}
	 */
	public ArrayList<RegelsetModel> getRulsetList() {
		return rulsets;
	}

	/**
	 * Setter UpdateView
	 * 
	 * @param updateView
	 *            Updated den Status der View
	 */
	public void setUpdateView(boolean updateView) {

		boolean oldValue = this.updateView;
		this.updateView = updateView;
		firePropertyChange("updateView", oldValue, updateView);

	}

	/**
	 * Getter UpdateView
	 * 
	 * @return {@link Boolean}
	 */
	public boolean getUpdateView() {

		return updateView;
	}

	/**
	 * Setter RulesetList
	 * 
	 * @param rulsets
	 *            List von {@link RegelsetModel}
	 */
	public void setRulsetList(ArrayList<RegelsetModel> rulsets) {
		ArrayList<RegelsetModel> oldValue = this.rulsets;
		this.rulsets = rulsets;
		firePropertyChange("rulsets", oldValue, rulsets);
		abstractModel.setRulsets(rulsets);
		abstractModel.fireTableDataChanged();

	}

	/**
	 * Getter SerivceStatus
	 * 
	 * @return see {@link ServiceStatus}
	 */
	public ServiceStatus getServiceStatus() {
		return serviceStatus;
	}

	/**
	 * Setter ServiceStatus
	 * 
	 * @param serviceStatus
	 *            see {@link ServiceStatus}
	 */
	public void setServiceStatus(ServiceStatus serviceStatus) {
		ServiceStatus oldValue = this.serviceStatus;
		this.serviceStatus = serviceStatus;
		firePropertyChange("serviceStatus", oldValue, serviceStatus);
	}

}
