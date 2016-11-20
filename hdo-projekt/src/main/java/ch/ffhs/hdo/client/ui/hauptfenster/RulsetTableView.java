package ch.ffhs.hdo.client.ui.hauptfenster;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.swing.DropMode;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import ch.ffhs.hdo.client.ui.base.View;
import ch.ffhs.hdo.client.ui.regelset.RegelsetModel;

public class RulsetTableView extends View<RulsetModel> {
	
	private JPanel jPanel;
	private AbstractRulsetTableModel tableModel;
	private JTable rulsetTable;
	private final String I18N = "hdo.main";
	private ArrayList regelsets;

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
		regelsets= new ArrayList();
		
		//*****TEMP****
		RegelsetModel rs;
		for(int i=0; i<20; i++) {
		rs=new RegelsetModel();
		rs.setRulesetName("Regelset "+ i);
		rs.setTargetDirectory("C:/" + i);
		regelsets.add(rs);
		}
		//*************
		
		tableModel = new AbstractRulsetTableModel(regelsets, new String[]{getMessage(I18N + ".rulsetTableHeader.name"),getMessage(I18N + ".rulsetTableHeader.directory"),getMessage(I18N + ".rulsetTableHeader.active")});
		rulsetTable = new JTable(tableModel);
	}

	private void layoutForm() {
		rulsetTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		rulsetTable.setDragEnabled(true);
		jPanel.setLayout(new BorderLayout());
		jPanel.add(rulsetTable.getTableHeader(), BorderLayout.NORTH);
		jPanel.add(rulsetTable, BorderLayout.CENTER);
	}
	
	@Override
	public void configureBindings() {
		//regelsets=this.getModel().getRulsetList();
	}
	
	public JPanel getPanel() {
		return jPanel;
	}


}
