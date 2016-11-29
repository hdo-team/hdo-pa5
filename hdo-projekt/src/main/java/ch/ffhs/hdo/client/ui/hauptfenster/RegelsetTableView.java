package ch.ffhs.hdo.client.ui.hauptfenster;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import ch.ffhs.hdo.client.ui.base.View;
import ch.ffhs.hdo.client.ui.regelset.RegelsetModel;

public class RegelsetTableView extends View<RegelsetTableModel> {
	
	private JPanel jPanel;
	private JTable rulsetTable;
	private final String I18N = "hdo.main";

	public RegelsetTableView(ResourceBundle resourceBundle, RegelsetTableModel model) {
		super(resourceBundle);
		this.setModel(model);
		initComponents();
		
	}
	
	private void initComponents() {
		createComponents();
		layoutForm();
		configureBindings();
	}

	private void createComponents() {
		jPanel = new JPanel();
		getModel().setColumnNames(new String[]{getMessage(I18N + ".rulsetTableHeader.name"),getMessage(I18N + ".rulsetTableHeader.directory"),getMessage(I18N + ".rulsetTableHeader.active")});
		rulsetTable = new JTable(getModel());
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

	}
	
	public JPanel getPanel() {
		return jPanel;
	}


}
