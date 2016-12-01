package ch.ffhs.hdo.client.ui.hauptfenster;

import java.util.ArrayList;

import javax.swing.event.TableModelListener;

import ch.ffhs.hdo.client.ui.base.Model;
import ch.ffhs.hdo.client.ui.regelset.RegelsetModel;

public class RegelsetTableModel extends Model {

	
	private RegelsetAbstractTableModel abstractModel;
	private ArrayList<RegelsetModel> rulsets;
		
	public RegelsetTableModel(ArrayList<RegelsetModel> rulsets) {
		this.rulsets = rulsets;
	}
	
	public void createAbstractTableModel(String[] columnNames) {
		abstractModel = new RegelsetAbstractTableModel(rulsets,columnNames);
	}
	
	public RegelsetAbstractTableModel getAbstractModel() {
		return abstractModel;
	}
	
	public ArrayList<RegelsetModel> getRulsetList() {
		return rulsets;
	}
	
	public void addTableModelListener(TableModelListener l) {
		// TODO Auto-generated method stub
		
	}

	public void removeTableModelListener(TableModelListener l) {
		// TODO Auto-generated method stub
		
	}

}
