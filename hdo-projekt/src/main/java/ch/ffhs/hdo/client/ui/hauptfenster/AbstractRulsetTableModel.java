package ch.ffhs.hdo.client.ui.hauptfenster;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import ch.ffhs.hdo.client.ui.regelset.RegelsetModel;

public class AbstractRulsetTableModel extends AbstractTableModel {

	private final String[] COLUMN_NAMES = {"1,2,3"};
	
	private final int COLUMN_IDX_1 = 0;
	private final int COLUMN_IDX_2 = 1;
	private final int COLUMN_IDX_3 = 2;
	
	
	private ArrayList<RegelsetModel> rulsets;
	
	public AbstractRulsetTableModel(ArrayList<RegelsetModel> rulsets) {
		this.rulsets = rulsets;
	}
	
	public int getRowCount() {
		return rulsets.size();
	}

	public int getColumnCount() {
		return COLUMN_NAMES.length;
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		//String[] rulset = rulsets.get(rowIndex);
		//TODO get Values back
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
