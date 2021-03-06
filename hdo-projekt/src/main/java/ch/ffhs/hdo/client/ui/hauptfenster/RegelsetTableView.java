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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import com.jgoodies.forms.builder.FormBuilder;

import ch.ffhs.hdo.client.ui.base.View;
import ch.ffhs.hdo.client.ui.hauptfenster.RegelsetTableModel.ServiceStatus;
import ch.ffhs.hdo.client.ui.hauptfenster.executable.RegelsetTableUpdateOperation;
import ch.ffhs.hdo.client.ui.regelset.RegelPanel;
import ch.ffhs.hdo.client.ui.regelset.executable.RegelsetDeleteOperation;
import ch.ffhs.hdo.client.ui.regelset.executable.RegelsetSwapOperation;
import ch.ffhs.hdo.client.ui.regelset.executable.RegelsetViewStartOperation;
import ch.ffhs.hdo.infrastructure.service.executable.ServiceStartOperation;

/**
 * Regelset-Uebersicht und Navigationsbar innerhalb des Hauptfensters. Wird beim
 * Start der Applikation geoeffnet.
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
	 *            Uebersetzungen der aktuellen Sprache.
	 * @param model
	 *            Model welches die Daten fuer die Anzeige enthaelt.
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
	 * Erstellt alle GUI Komponenten und fuegt die Listener hinzu.
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
		stateButton = new JButton(getMessage(I18N + ".button.state.START"));

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
				int confirmed = JOptionPane.showConfirmDialog(null,
						getMessage(I18N + ".dialog.regelset.delete.confirm",
								getModel().getRulsetList().get(regelsetTable.getSelectedRow()).getRulesetName()),
						getMessage(I18N + ".dialog.regelset.delete.title"), JOptionPane.YES_NO_OPTION);

				if (confirmed == JOptionPane.YES_OPTION) {
					getHandler().performOperationWithArgs(RegelsetDeleteOperation.class, getRegelsetId());
					getModel().setUpdateView(true);
				}

			}

			private int getRegelsetId() {
				return getModel().getRulsetList().get(regelsetTable.getSelectedRow()).getRulesetId();
			}
		});

		stateButton.addActionListener(new AbstractAction() {
			public void actionPerformed(ActionEvent e) {

				switch (getModel().getServiceStatus()) {
				case START:
					getModel().setServiceStatus(ServiceStatus.STOP);
					getHandler().performOperationWithArgs(ServiceStartOperation.class, ServiceStatus.START);
					break;

				case STOP:
					getModel().setServiceStatus(ServiceStatus.START);
					getHandler().performOperationWithArgs(ServiceStartOperation.class, ServiceStatus.STOP);
					break;
				default:
					break;

				}

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
		jPanel.add(regelsetTable, BorderLayout.CENTER);
		jPanel.add(toolbarPanel, BorderLayout.SOUTH);
	}

	/**
	 * Konfiguriert die einzelnen Komponenten bei Statusaenderungen.
	 */
	@Override
	public void configureBindings() {

		getModel().addPropertyChangeListener(new PropertyChangeListener() {

			public void propertyChange(PropertyChangeEvent evt) {
				if (evt.getPropertyName() == "serviceStatus") {
					final ServiceStatus newValue = (ServiceStatus) evt.getNewValue();
					if (newValue.equals(ServiceStatus.START)) {
						stateButton.setText(getMessage("hdo.main.button.state.START"));
						toggleButton(true);
					}
					if (newValue.equals(ServiceStatus.STOP)) {
						stateButton.setText(getMessage("hdo.main.button.state.STOP"));
						toggleButton(false);
					}

				}
				if (evt.getPropertyName() == "updateView") {
					if (getModel().getUpdateView()) {
						getHandler().performOperationWithArgs(RegelsetTableUpdateOperation.class, getModel());
						getModel().setUpdateView(false);
					}
				}

			}

			private void toggleButton(boolean b) {
				newButton.setEnabled(b);
				editButton.setEnabled(b);
				prioUpButton.setEnabled(b);
				prioDownButton.setEnabled(b);
				deleteButton.setEnabled(b);
			}
		});

	}

	/**
	 * Uebergibt die Gesamte Regelset Uebersicht als Panel.
	 * 
	 * @return Regelset Panel.
	 */
	public JPanel getPanel() {
		return jPanel;
	}

	/***
	 * Gib die selektierte Zeile des Regelset zurueck
	 * @return die Zeile die selektiert wurde
	 */
	public int getSelectedRow() {
		if (regelsetTable.getSelectedRow() > -1) {
			return regelsetTable.getSelectedRow();
		}
		return -1;
	}
}
