package ch.ffhs.hdo.client.ui.hauptfenster;

import java.awt.Color;
import java.util.ResourceBundle;

import javax.swing.JPanel;
import javax.swing.JTable;

import ch.ffhs.hdo.client.ui.base.View;

public class RulsetTableView extends View<RulsetModel> {
	
	private JPanel jPanel;
	private AbstractRulsetTableModel tableModel;
	private JTable rulsetTable;

	public RulsetTableView(ResourceBundle resourceBundle) {
		super(resourceBundle);
		
		initComponents();
	}
	
	private void initComponents() {
		createComponents();
		layoutForm();
		configureBindings();
	}

	private void createComponents() {
		jPanel = new JPanel();
		tableModel = new AbstractRulsetTableModel(null);
		rulsetTable = new JTable(tableModel);
	}

	private void layoutForm() {

	}
	
	@Override
	public void configureBindings() {
		// TODO Auto-generated method stub

	}
	
	public JPanel getPanel() {
		return jPanel;
	}


}
