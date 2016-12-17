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
import ch.ffhs.hdo.client.ui.hauptfenster.executable.RegelsetTableUpdateOperation;
import ch.ffhs.hdo.client.ui.regelset.executable.RegelsetDeleteOperation;
import ch.ffhs.hdo.client.ui.regelset.executable.RegelsetSwapOperation;
import ch.ffhs.hdo.client.ui.regelset.executable.RegelsetViewStartOperation;
import ch.ffhs.hdo.infrastructure.service.executable.ServiceStartOperation;

/**
 * Regelset-Übersicht und Navigationsbar innerhalb des Hauptfensters. Wird beim
 * Start der Applikation geöffnet.
 * 
 * @author Jonas Segessemann
 *
 */
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

	/**
	 * Konstruktor welcher das View Objekt erstellt.
	 * 
	 * @param resourceBundle
	 *            Übersetzungen der aktuellen Sprache.
	 * @param model
	 *            Model welches die Daten für die Anzeige enthält.
	 */
	public RegelsetTableView(ResourceBundle resourceBundle, RegelsetTableModel model) {
		super(resourceBundle);
		this.setModel(model);
		initComponents();

	}
	/**
	 * Initialisierung des Konfigurations-Fensters.
	 */
	private void initComponents() {
		createComponents();
		layoutForm();
		configureBindings();
	}
	/**
	 * Erstellt alle GUI Komponenten und fügt die Listener hinzu.
	 */
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
					int selection = regelsetTable.getSelectedRow();
					getModel().setUpdateView(true);
					if (selection > 0) {
						regelsetTable.setRowSelectionInterval(0, selection - 1);
					} else {
						regelsetTable.setRowSelectionInterval(0, selection);
					}
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
					int selection = regelsetTable.getSelectedRow();
					getModel().setUpdateView(true);
					if (selection < regelsetTable.getRowCount() - 1) {
						regelsetTable.setRowSelectionInterval(0, selection + 1);
					} else {
						regelsetTable.setRowSelectionInterval(0, selection);
					}
				}
			}
		});

		newButton.addActionListener(new AbstractAction() {
			public void actionPerformed(ActionEvent e) {
				getHandler().performOperationWithArgs(RegelsetViewStartOperation.class, getModel());
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
				getModel().setUpdateView(true);
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
	/**
	 * Ordnet die erstellten GUI Komponenten.
	 */
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
	/**
	 * Konfiguriert die einzelnen Komponenten bei Statusänderungen.
	 */
	@Override
	public void configureBindings() {

		getModel().addPropertyChangeListener(new PropertyChangeListener() {

			public void propertyChange(PropertyChangeEvent evt) {
				if (evt.getPropertyName() == "serviceStatus") {
					stateButton.setText(getMessage("hdo.main.button.state." + evt.getNewValue().toString()));
					if (evt.getNewValue().toString().equals("START")) {
						newButton.setEnabled(false);
						;
						editButton.setEnabled(false);
						prioUpButton.setEnabled(false);
						prioDownButton.setEnabled(false);
						deleteButton.setEnabled(false);
					}
					if (evt.getNewValue().toString().equals("STOP")) {
						newButton.setEnabled(true);
						;
						editButton.setEnabled(true);
						prioUpButton.setEnabled(true);
						prioDownButton.setEnabled(true);
						deleteButton.setEnabled(true);
					}

				}
				if (evt.getPropertyName() == "updateView") {
					if (getModel().getUpdateView()) {
						getHandler().performOperationWithArgs(RegelsetTableUpdateOperation.class, getModel());
						getModel().setUpdateView(false);
					}
				}

			}
		});

	}
/**
 * Übergibt die Gesamte Regelset Übersicht als Panel.
 * @return Regelset Panel.
 */
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
