package ch.ffhs.hdo.client.ui.hauptfenster;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.ResourceBundle;

import javax.swing.AbstractAction;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;

import ch.ffhs.hdo.client.ui.base.View;
import ch.ffhs.hdo.client.ui.base.executable.CloseViewOperation;
import ch.ffhs.hdo.client.ui.einstellungen.executable.OptionViewStartOperation;
import ch.ffhs.hdo.client.ui.export.executable.ExportViewStartOperation;
import ch.ffhs.hdo.client.ui.imports.executable.ImportViewStartOperation;
import ch.ffhs.hdo.client.ui.regelset.executable.RegelsetViewStartOperation;

/**
 * Hauptfenster welches die Menüleiste und das den Rahmen der beinden Panels
 * (Verzeichnisstruktur, Regelset-Übersicht) enthält.
 * 
 * @author Jonas Segessemann
 *
 */
public class MainView extends View<MainModel> {

	private ResourceBundle resourceBundle;
	private final String I18N = "hdo.main";
	private final String TITLE_KEY = I18N + ".title";

	private JMenuBar menuBar;
	private JMenu file, options;
	private JMenuItem fileResort, fileImport, fileExport, fileExit, optionsConfig;

	private FolderTreeView folderTreeView;
	private JScrollPane folderScrollPane;

	private RegelsetTableView regelsetTableView;
	private JScrollPane regelsetScrollPane;

	private JSplitPane layoutsplitpane;
	Dimension minimumSize;

	/**
	 * Konstruktor welcher das View Objekt erstellt.
	 * 
	 * @param resourceBundle
	 *            Übersetzungen der aktuellen Sprache.
	 * @param model
	 *            Model welches die Informationen das Main View enthalten.
	 */
	public MainView(ResourceBundle resourceBundle, MainModel model) {
		super(resourceBundle);
		this.setModel(model);
		this.resourceBundle = resourceBundle;
		setTitle(getMessage(TITLE_KEY));
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
		menuBar = new JMenuBar();
		file = new JMenu(getMessage(I18N + ".menu.file"));
		options = new JMenu(getMessage(I18N + ".menu.options"));

		fileResort = new JMenuItem(getMessage(I18N + ".menuitem.resort"), KeyEvent.VK_T);
		fileImport = new JMenuItem(getMessage(I18N + ".menuitem.import"), KeyEvent.VK_T);
		fileExport = new JMenuItem(getMessage(I18N + ".menuitem.export"), KeyEvent.VK_T);

		fileExport.addActionListener(new AbstractAction() {

			public void actionPerformed(ActionEvent e) {
				getHandler().performOperation(ExportViewStartOperation.class);
			}
		});

		fileExit = new JMenuItem(getMessage(I18N + ".menuitem.exit"), KeyEvent.VK_T);
		fileExit.addActionListener(new AbstractAction() {

			public void actionPerformed(ActionEvent e) {
				getHandler().performOperation(CloseViewOperation.class);
			}
		});

		optionsConfig = new JMenuItem(getMessage(I18N + ".menuitem.config"), KeyEvent.VK_T);

		optionsConfig.addActionListener(new AbstractAction() {

			public void actionPerformed(ActionEvent e) {
				getHandler().performOperationWithArgs(OptionViewStartOperation.class, getModel().getFolderModel());
			}
		});

		// Listener für den Importeintrag im Menü
		fileImport.addActionListener(new AbstractAction() {

			public void actionPerformed(ActionEvent e) {
				getHandler().performOperation(ImportViewStartOperation.class);
			}
		});

		fileResort.addActionListener(new AbstractAction() {

			public void actionPerformed(ActionEvent e) {
				// TODO: Zum Testen das Erste ELement mitgeben
				// End-Version: null wenn neues Regelset erstellt werden soll
				// effektives Model (muss ja nicht Index 0) sein!
				//
				if (getModel().getRegelsetModel().getRulsetList() != null) {
					getHandler().performOperationWithArgs(RegelsetViewStartOperation.class,
							getModel().getRegelsetModel().getRulsetList().get(0));
				} else {
					// Kein Model => keine Argument)
					getHandler().performOperation(RegelsetViewStartOperation.class);
				}
			}
		});

		minimumSize = new Dimension(200, 150);

		// Create Folder Panel
		folderTreeView = new FolderTreeView(resourceBundle, getModel().getFolderModel());
		folderScrollPane = new JScrollPane(folderTreeView.getPanel());

		// Create Rulset Panel

		regelsetTableView = new RegelsetTableView(resourceBundle, getModel().getRegelsetModel());

		regelsetScrollPane = new JScrollPane(regelsetTableView.getPanel());

		layoutsplitpane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, folderScrollPane, regelsetScrollPane);

	}

	/**
	 * Ordnet die erstellten GUI Komponenten.
	 */
	private void layoutForm() {
		// Create Menubar Layout
		menuBar.add(file);
		menuBar.add(options);
		file.add(fileResort);
		file.add(fileImport);
		file.add(fileExport);
		file.add(fileExit);
		options.add(optionsConfig);

		// Create Main View Layout
		layoutsplitpane.setOneTouchExpandable(true);
		layoutsplitpane.setDividerLocation(210);
		folderScrollPane.setMinimumSize(minimumSize);
		regelsetScrollPane.setMinimumSize(minimumSize);

		getFrame().setJMenuBar(menuBar);
		getFrame().add(layoutsplitpane, BorderLayout.CENTER);

		setDimension(900, 700);

	}

	/**
	 * Konfiguriert die einzelnen Komponenten und erstellt die Listener.
	 */
	public void configureBindings() {

	}

	/**
	 * Gibt die View mit der Verzeichnisstruktur zurück.
	 * 
	 * @return View Verzeicnisstruktur.
	 */
	public View<FolderTreeModel> getFolderTreeView() {
		return folderTreeView;
	}

	/**
	 * Gibt die View mit der Regelset-Übersicht zurück.
	 * 
	 * @return View Regelset-Übersicht.
	 */
	public View<RegelsetTableModel> getRegelsetTableView() {
		return regelsetTableView;
	}
}
