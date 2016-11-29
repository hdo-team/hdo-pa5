package ch.ffhs.hdo.client.ui.hauptfenster;

import java.awt.BorderLayout;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.jgoodies.forms.builder.FormBuilder;

import ch.ffhs.hdo.client.ui.base.View;

public class RegelsetTableView extends View<RegelsetTableModel> {
	

	private final String I18N = "hdo.main";
	private JPanel jPanel;
	private JScrollPane tableScrollPane;
	private JTable regelsetTable;
	private JPanel toolbarPanel;
	private JButton prioUpButton;
	private JButton prioDownButton;
	private JButton editButton;
	private JButton newButton;
	private JButton deleteButton;
	private JLabel stateLabel;

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
		
		getModel().createAbstractTableModel(new String[]{getMessage(I18N + ".rulsetTableHeader.name"),getMessage(I18N + ".rulsetTableHeader.directory"),getMessage(I18N + ".rulsetTableHeader.active")});
		regelsetTable = new JTable(getModel().getAbstractModel());
		tableScrollPane = new JScrollPane(regelsetTable);
		
		//Toolbar
		toolbarPanel = new JPanel();
		prioUpButton = new JButton(getMessage(I18N + ".button.prioUp"));
		prioDownButton = new JButton(getMessage(I18N + ".button.prioDown"));
		newButton = new JButton(getMessage(I18N + ".button.newRegelset"));
		editButton = new JButton(getMessage(I18N + ".button.editRegelset"));
		deleteButton = new JButton(getMessage(I18N + ".button.deleteRegelset"));
		stateLabel = new JLabel(getMessage(I18N + ".label.stateStop"));
		
		regelsetTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

		    public void valueChanged(ListSelectionEvent event) {
		        if (regelsetTable.getSelectedRow() > -1) {
		            // print first column value from selected row
		            System.out.println(regelsetTable.getValueAt(regelsetTable.getSelectedRow(), 0).toString());
		        }
		    }});
		
		//prioUpButton.addActionListener(new prioUpAction());
	}

	private void layoutForm() {
		regelsetTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		regelsetTable.setDragEnabled(true);
		jPanel.setLayout(new BorderLayout());
		
		//Toolbar
		FormBuilder builder = FormBuilder.create()
				.columns("right:pref, 5dlu,[20dlu, pref], 5dlu,[20dlu, pref], 20dlu,[20dlu, pref], 5dlu,[20dlu, pref], 5dlu,[20dlu, pref], 20dlu,[20dlu, pref]")
				.rows("p, $lg, p");
		builder.add(prioUpButton).rc(3, 3);
		builder.add(prioDownButton).rc(3, 5);
		builder.add(newButton).rc(3, 7);
		builder.add(editButton).rc(3, 9);
		builder.add(deleteButton).rc(3, 11);
		builder.add(stateLabel).rc(3, 13);;
		builder.padding(new EmptyBorder(5, 5, 5, 5));
		toolbarPanel = builder.build();
		
		jPanel.add(regelsetTable.getTableHeader(), BorderLayout.NORTH);
		jPanel.add(regelsetTable, BorderLayout.CENTER); //TODO tableScrollPane statt regelsetTable
		jPanel.add(toolbarPanel, BorderLayout.SOUTH);
	}
	
	@Override
	public void configureBindings() {

		
	}
	
	public JPanel getPanel() {
		return jPanel;
	}


}
