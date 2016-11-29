package ch.ffhs.hdo.client.ui.hauptfenster;

import java.util.ArrayList;

import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;

import ch.ffhs.hdo.client.ui.regelset.RegelsetModel;

public class RegelsetAbstractTableModel extends AbstractTableModel {

	
	private String[] columnNames;
	
	private final int COLUMN_IDX_1 = 0;
	private final int COLUMN_IDX_2 = 1;
	private final int COLUMN_IDX_3 = 2;
	
	
	private ArrayList<RegelsetModel> rulsets;
	
	public RegelsetAbstractTableModel(ArrayList<RegelsetModel> rulsets, String[] columnNames) {
		this.columnNames = columnNames;
		this.rulsets = rulsets;
	}
	
	public int getRowCount() {
		return rulsets.size();
	}
	
	public int getColumnCount() {
		return columnNames.length;
	}
	
	@Override
	public String getColumnName(int columnIndex) {
		return columnNames[columnIndex];
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

}
