package ch.ffhs.hdo.client.ui.hauptfenster;

import java.awt.BorderLayout;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ResourceBundle;

import javax.swing.JPanel;
import javax.swing.JTree;

import ch.ffhs.hdo.client.ui.base.View;
import ch.ffhs.hdo.client.ui.hauptfenster.executable.FolderTreeUpdateOperation;
import ch.ffhs.hdo.infrastructure.ApplicationSettings;

/**
 * Verzeichnis-Übersicht innerhalb des Hauptfensters. Wird beim Start der
 * Applikation geöffnet.
 * 
 * @author Jonas Segessemann
 *
 */
public class FolderTreeView extends View<FolderTreeModel> {

	private final String I18N = "hdo.main";
	private JPanel jPanel;
	private JTree tree;

	/**
	 * Konstruktor welcher das View Objekt erstellt.
	 * 
	 * @param resourceBundle
	 *            Übersetzungen der aktuellen Sprache.
	 * @param folderModel
	 *            Model welches die Daten für die Anzeige enthält.
	 */
	public FolderTreeView(ResourceBundle resourceBundle, FolderTreeModel folderModel) {
		super(resourceBundle);
		this.setModel(folderModel);
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
	 * Erstellt alle GUI Komponenten.
	 */
	private void createComponents() {
		jPanel = new JPanel();
		tree = new JTree(getModel().getTreeModel());
	}

	/**
	 * Ordnet die erstellten GUI Komponenten.
	 */
	private void layoutForm() {
		tree.setCellRenderer(new FileTreeCellRenderer());
		jPanel.setLayout(new BorderLayout());
		jPanel.add(tree, BorderLayout.CENTER);

	}

	/**
	 * Konfiguriert die einzelnen Komponenten und erstellt die Listener.
	 */
	@Override
	public void configureBindings() {
		getModel().addPropertyChangeListener(new PropertyChangeListener() {

			public void propertyChange(PropertyChangeEvent evt) {
				if (evt.getPropertyName() == "updateView") {
					if (getModel().getUpdateView()) {
						getHandler().performOperationWithArgs(FolderTreeUpdateOperation.class,
								ApplicationSettings.getInstance().getInbox_path());

						tree.setModel(getModel().getTreeModel());
						getModel().setUpdateView(false);
					}
				}

			}
		});
	}
/**
 * Übergibt die Gesamte Verzeichnis Übersicht als Panel.
 * @return Verzeichnis Panel.
 */
	public JPanel getPanel() {
		return jPanel;
	}

}
