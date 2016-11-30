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
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.border.EmptyBorder;

import com.jgoodies.forms.builder.FormBuilder;

import ch.ffhs.hdo.client.ui.base.View;
import ch.ffhs.hdo.client.ui.base.executable.CloseViewOperation;
import ch.ffhs.hdo.client.ui.einstellungen.executable.OptionViewStartOperation;
import ch.ffhs.hdo.client.ui.export.executable.ExportViewStartOperation;
import ch.ffhs.hdo.client.ui.imports.executable.ImportViewStartOperation;
import ch.ffhs.hdo.client.ui.regelset.executable.RegelsetViewStartOperation;

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

	public MainView(ResourceBundle resourceBundle, MainModel model) {
		super(resourceBundle);
		this.setModel(model);
		this.resourceBundle = resourceBundle;
		setTitle(getMessage(TITLE_KEY));
		initComponents();

	}

	private void initComponents() {
		createComponents();
		layoutForm();
		configureBindings();
	}

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
				getHandler().performOperation(OptionViewStartOperation.class);
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
				//	  End-Version: null wenn neues Regelset erstellt werden soll
				//    				effektives Model (muss ja nicht Index 0) sein!
				//
				if (getModel().getRegelsetModel().getRulsetList() != null) {
					getHandler().performOperationWithArgs(RegelsetViewStartOperation.class, getModel().getRegelsetModel().getRulsetList().get(0));
				} else {
					// Kein Model => keine Argument)
					getHandler().performOperation(RegelsetViewStartOperation.class);
				}
			}
		});

		minimumSize = new Dimension(200, 150);

		// Create Folder Panel
		folderTreeView = new FolderTreeView(resourceBundle);
		folderScrollPane = new JScrollPane(folderTreeView.getPanel());

		// Create Rulset Panel
		
		regelsetTableView = new RegelsetTableView(resourceBundle, getModel().regelsetModel);
		

		
		regelsetScrollPane = new JScrollPane(regelsetTableView.getPanel());

		layoutsplitpane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, folderScrollPane, regelsetScrollPane);

	}

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
		layoutsplitpane.setDividerLocation(150);
		folderScrollPane.setMinimumSize(minimumSize);
		regelsetScrollPane.setMinimumSize(minimumSize);

		FormBuilder builder = FormBuilder.create()
				.columns("right:pref, 5dlu,[30dlu, pref],5dlu,[20dlu, pref],5dlu, [20dlu, pref]")
				.rows("p, $lg, p, $lg, p, $lg, p, $lg, p , $lg, p , $lg, p");

		builder.padding(new EmptyBorder(5, 5, 5, 5));
		JPanel build = builder.build();

		getFrame().setJMenuBar(menuBar);
		getFrame().add(layoutsplitpane, BorderLayout.CENTER);

		setDimension(900, 700);

	}

	public void configureBindings() {

	}

	public View<FolderModel> getFolderTreeView() {
		return folderTreeView;
	}

	public View<RegelsetTableModel> getRegelsetTableView() {
		return regelsetTableView;
	}
}
