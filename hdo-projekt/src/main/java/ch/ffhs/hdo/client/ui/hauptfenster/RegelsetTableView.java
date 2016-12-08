package ch.ffhs.hdo.client.ui.hauptfenster;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ResourceBundle;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;

import com.jgoodies.forms.builder.FormBuilder;

import ch.ffhs.hdo.client.ui.base.View;
import ch.ffhs.hdo.client.ui.regelset.executable.RegelsetDeleteOperation;
import ch.ffhs.hdo.client.ui.regelset.executable.RegelsetSwapOperation;
import ch.ffhs.hdo.client.ui.regelset.executable.RegelsetViewStartOperation;
import ch.ffhs.hdo.infrastructure.service.executable.ServiceStartOperation;

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
	private JButton stateButton;

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

		getModel().createAbstractTableModel(new String[] { getMessage(I18N + ".rulsetTableHeader.name"),
				getMessage(I18N + ".rulsetTableHeader.directory"), getMessage(I18N + ".rulsetTableHeader.active") });
		regelsetTable = new JTable(getModel().getAbstractModel());
		tableScrollPane = new JScrollPane(regelsetTable);

		// Toolbar
		toolbarPanel = new JPanel();
		prioUpButton = new JButton(getMessage(I18N + ".button.prioUp"));
		prioDownButton = new JButton(getMessage(I18N + ".button.prioDown"));
		newButton = new JButton(getMessage(I18N + ".button.newRegelset"));
		editButton = new JButton(getMessage(I18N + ".button.editRegelset"));
		deleteButton = new JButton(getMessage(I18N + ".button.deleteRegelset"));
		stateButton = new JButton(getMessage(I18N + ".button.state.STOP"));

		prioUpButton.addActionListener(new AbstractAction() {
			public void actionPerformed(ActionEvent e) {
				callSwapOperation(1);
			}

			private void callSwapOperation(int operation) {
				if (regelsetTable.getSelectedRow() > -1) {
					final Integer rulesetId = getModel().getRulsetList().get(regelsetTable.getSelectedRow())
							.getRulesetId();
					getHandler().performOperationWithArgs(RegelsetSwapOperation.class,
							new int[] { rulesetId, operation });
				}
			}
		});

		prioDownButton.addActionListener(new AbstractAction() {
			public void actionPerformed(ActionEvent e) {
				callSwapOperation(2);

			}

			private void callSwapOperation(int operation) {
				if (regelsetTable.getSelectedRow() > -1) {
					final Integer rulesetId = getModel().getRulsetList().get(regelsetTable.getSelectedRow())
							.getRulesetId();
					getHandler().performOperationWithArgs(RegelsetSwapOperation.class,
							new int[] { rulesetId, operation });
				}
			}
		});

		newButton.addActionListener(new AbstractAction() {
			public void actionPerformed(ActionEvent e) {
				getHandler().performOperation(RegelsetViewStartOperation.class);
			}
		});

		editButton.addActionListener(new AbstractAction() {
			public void actionPerformed(ActionEvent e) {
				if (regelsetTable.getSelectedRow() > -1) {
					getHandler().performOperationWithArgs(RegelsetViewStartOperation.class,
							getModel().getRulsetList().get(regelsetTable.getSelectedRow()));
				}
			}
		});
		regelsetTable.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2 && regelsetTable.getSelectedRow() > -1) {
					getHandler().performOperationWithArgs(RegelsetViewStartOperation.class,
							getModel().getRulsetList().get(regelsetTable.getSelectedRow()));
				}
			}
		});

		deleteButton.addActionListener(new AbstractAction() {
			public void actionPerformed(ActionEvent e) {
				getHandler().performOperationWithArgs(RegelsetDeleteOperation.class, getRegelsetId());
			}

			private int getRegelsetId() {
				return getModel().getRulsetList().get(regelsetTable.getSelectedRow()).getRulesetId();
			}
		});

		stateButton.addActionListener(new AbstractAction() {
			public void actionPerformed(ActionEvent e) {
				getHandler().performOperationWithArgs(ServiceStartOperation.class, getModel());
			}
		});

	}

	private void layoutForm() {
		regelsetTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		regelsetTable.setDragEnabled(true);
		jPanel.setLayout(new BorderLayout());

		// Toolbar
		FormBuilder builder = FormBuilder.create()
				.columns(
						"right:pref, 5dlu,[20dlu, pref], 5dlu,[20dlu, pref], 20dlu,[20dlu, pref], 5dlu,[20dlu, pref], 5dlu,[20dlu, pref], 20dlu,[20dlu, pref]")
				.rows("p, $lg, p");
		builder.add(prioUpButton).rc(3, 3);
		builder.add(prioDownButton).rc(3, 5);
		builder.add(newButton).rc(3, 7);
		builder.add(editButton).rc(3, 9);
		builder.add(deleteButton).rc(3, 11);
		builder.add(stateButton).rc(3, 13);
		;
		builder.padding(new EmptyBorder(5, 5, 5, 5));
		toolbarPanel = builder.build();

		jPanel.add(regelsetTable.getTableHeader(), BorderLayout.NORTH);
		jPanel.add(regelsetTable, BorderLayout.CENTER); // TODO tableScrollPane
														// statt regelsetTable
		jPanel.add(toolbarPanel, BorderLayout.SOUTH);
	}

	@Override
	public void configureBindings() {

		getModel().addPropertyChangeListener(new PropertyChangeListener() {

			public void propertyChange(PropertyChangeEvent evt) {
				if (evt.getPropertyName() == "serviceStatus") {
					stateButton.setText(getMessage("hdo.main.button.state." + evt.getNewValue().toString()));
				}

			}
		});

	}

	public JPanel getPanel() {
		return jPanel;
	}

	public int getSelectedRow() {
		if (regelsetTable.getSelectedRow() > -1) {
			return regelsetTable.getSelectedRow();
		}
		return -1;
	}
}
