package ch.ffhs.hdo.client.ui.hauptfenster;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

public class AbstractRulsetTableModel extends AbstractTableModel {

	private final String[] COLUMN_NAMES = {"1,2,3"};
	
	private final int COLUMN_IDX_1 = 0;
	private final int COLUMN_IDX_2 = 1;
	private final int COLUMN_IDX_3 = 2;
	
	
	private List<String[]> rulsets;
	
	public AbstractRulsetTableModel(List<String[]> rulsets) {
		this.rulsets = rulsets;
	}
	
	public int getRowCount() {
		return rulsets.size();
	}

	public int getColumnCount() {
		return COLUMN_NAMES.length;
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		String[] rulset = rulsets.get(rowIndex);
		//TODO get Values back
		return null;
	}

}
