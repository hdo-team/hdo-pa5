package ch.ffhs.hdo.client.ui.hauptfenster;

import ch.ffhs.hdo.client.ui.base.Model;

public class MainModel extends Model {

	public enum ServiceStatus {
		START, PROCESSING, DONE, STOP;
	}
	
	private FolderModel folderModel;
	private RegelsetTableModel regelsetModel;
	private ServiceStatus serviceStatus;
	

	public ServiceStatus getServiceStatus() {
		return serviceStatus;
	}

	public void setServiceStatus(ServiceStatus serviceStatus) {
		ServiceStatus oldValue = this.serviceStatus;
		this.serviceStatus = serviceStatus;
		
		firePropertyChange("serviceStatus", oldValue, serviceStatus);
	}

	public void setFolderModel(FolderModel folderModel) {
		this.folderModel = folderModel;
	}
	
	public FolderModel getFolderModel() {
		return folderModel;
	}
	
	public RegelsetTableModel getRegelsetModel() {
		return regelsetModel;
	}
	
	public void setRegelsetModel(RegelsetTableModel regelsetTableModel) {
		this.regelsetModel=regelsetTableModel;
	}
	
}
