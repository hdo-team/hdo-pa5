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
import ch.ffhs.hdo.client.ui.einstellungen.executable.OptionViewStartOperation;
import ch.ffhs.hdo.client.ui.export.executable.ExportViewStartOperation;

public class MainView extends View<MainModel> {

	private ResourceBundle resourceBundle;
	private final String I18N = "hdo.main";
	private final String TITLE_KEY = I18N + ".title";

	private JMenuBar menuBar;
	private JMenu file, options;
	private JMenuItem fileResort, fileImport, fileExport, fileExit, optionsConfig;

	private JPanel folderPane;
	private FolderTreeView folderTreeView;
	private JScrollPane folderScrollPane;

	private JPanel rulsetPane;
	private JScrollPane rulsetScrollPane;

	private JSplitPane layoutsplitpane;
	Dimension minimumSize;

	public MainView(ResourceBundle resourceBundle) {
		super(resourceBundle);
		this.resourceBundle=resourceBundle;
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
		optionsConfig = new JMenuItem(getMessage(I18N + ".menuitem.config"), KeyEvent.VK_T);

		optionsConfig.addActionListener(new AbstractAction() {

			public void actionPerformed(ActionEvent e) {
				getHandler().performOperation(OptionViewStartOperation.class);
			}
		});
		
		minimumSize = new Dimension(200, 150);

		// Create Folder Panel
		folderPane = new JPanel();
		folderTreeView = new FolderTreeView(resourceBundle, folderPane);
		folderScrollPane = new JScrollPane(folderTreeView.getPanel());

		// Create Rulset Panel
		rulsetPane = new JPanel();
		rulsetScrollPane = new JScrollPane(rulsetPane);

		layoutsplitpane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, folderScrollPane, rulsetScrollPane);

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
		rulsetScrollPane.setMinimumSize(minimumSize);

		FormBuilder builder = FormBuilder.create()
				.columns("right:pref, 5dlu,[30dlu, pref],5dlu,[20dlu, pref],5dlu, [20dlu, pref]")
				.rows("p, $lg, p, $lg, p, $lg, p, $lg, p , $lg, p , $lg, p");

		builder.padding(new EmptyBorder(5, 5, 5, 5));
		JPanel build = builder.build();

		getFrame().setJMenuBar(menuBar);
		getFrame().add(layoutsplitpane, BorderLayout.CENTER);

		setDimension(900, 900);

	}

	public void configureBindings() {

	}

	public View<FolderModel> getFolderTreeView() {
		return null; //Hier Kommt die FolderTreeView Instanz
	}
	public View<RulsetModel> getRulsetTableView() {
		return null; //Hier Kommt die RulsetTableView Instanz
	}
}
