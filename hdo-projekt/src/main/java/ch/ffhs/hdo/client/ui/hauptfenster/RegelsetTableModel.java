package ch.ffhs.hdo.client.ui.hauptfenster;

import java.util.ArrayList;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import ch.ffhs.hdo.client.ui.base.Model;
import ch.ffhs.hdo.client.ui.regelset.RegelsetModel;

public class RegelsetTableModel extends Model implements TableModel {

	
	private String[] columnNames;
	
	private final int COLUMN_IDX_1 = 0;
	private final int COLUMN_IDX_2 = 1;
	private final int COLUMN_IDX_3 = 2;
	
	
	private ArrayList<RegelsetModel> rulsets;
	
	public RegelsetTableModel(ArrayList<RegelsetModel> rulsets) {
		this.rulsets = rulsets;
	}
	
	public void setColumnNames(String[] columnNames) {
		this.columnNames=columnNames;
	}
	
	public ArrayList<RegelsetModel> getRulsetList() {
		return rulsets;
	}
	
	public int getRowCount() {
		return 0;
		//return rulsets.size();
	}
	
	public int getColumnCount() {
		return 0;
		//return columnNames.length;
	}

	public Object getValueAt(int rowIndex, int columnIndex) {

		final RegelsetModel rulset = rulsets.get(rowIndex);
		if(columnIndex == COLUMN_IDX_1) {
			return rulset.getRulesetName();
		}
		
		if(columnIndex == COLUMN_IDX_2) {
			return rulset.getTargetDirectory();
		}
		
		if(columnIndex == COLUMN_IDX_3) {
			return rulset.isRuleActiv();
		}	
		
		return null;
	}
	

	public String getColumnName(int columnIndex) {
		return columnNames[columnIndex];
	}

	public Class<?> getColumnClass(int columnIndex) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean isCellEditable(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		return false;
	}

	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		
	}

	public void addTableModelListener(TableModelListener l) {
		// TODO Auto-generated method stub
		
	}

	public void removeTableModelListener(TableModelListener l) {
		// TODO Auto-generated method stub
		
	}

}
