package ch.ffhs.hdo.client.ui.hauptfenster;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.swing.JPanel;
import javax.swing.JTable;

import ch.ffhs.hdo.client.ui.base.View;
import ch.ffhs.hdo.client.ui.regelset.RegelsetModel;

public class RulsetTableView extends View<RulsetModel> {
	
	private JPanel jPanel;
	private AbstractRulsetTableModel tableModel;
	private JTable rulsetTable;
	//*****TEMP****
	private RegelsetModel rs;
	//*************

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
		
		//*****TEMP****
		ArrayList regelsets= new ArrayList();
		rs=new RegelsetModel();
		rs.setRulesetName("Regelset 1");
		rs.setTargetDirectory("C:/");
		regelsets.add(rs);
		//*************
		
		tableModel = new AbstractRulsetTableModel(regelsets);
		rulsetTable = new JTable(tableModel);
	}

	private void layoutForm() {
		jPanel.setLayout(new BorderLayout());
		jPanel.add(rulsetTable, BorderLayout.CENTER);
	}
	
	@Override
	public void configureBindings() {
		// TODO Auto-generated method stub

	}
	
	public JPanel getPanel() {
		return jPanel;
	}


}
