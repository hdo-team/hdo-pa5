package ch.ffhs.hdo.client.ui.regelset;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.jgoodies.forms.builder.FormBuilder;

import ch.ffhs.hdo.client.ui.base.View;
import ch.ffhs.hdo.client.ui.base.executable.CloseViewOperation;
import ch.ffhs.hdo.client.ui.regelset.executable.RegelsetSaveOperation;
import ch.ffhs.hdo.domain.regel.ComparisonTypeEnum;
import ch.ffhs.hdo.domain.regel.ContextAttributeEnum;
import ch.ffhs.hdo.domain.regel.ContextTypeEnum;
import ch.ffhs.hdo.infrastructure.ApplicationSettings;
import ch.ffhs.hdo.infrastructure.service.util.FileHandling;


/******************************************************
 * View zum Erstellen und Bearbeiten von Regelsets.
 *
 * 
 * @author Daniel Crazzolara
 *
 */

public class RegelsetView extends View<RegelsetModel> {

	private static Logger LOGGER = LogManager.getLogger(RegelsetView.class);

	final String I18N = "hdo.regelset";
	private final String TITLE_KEY = I18N + ".title";
	private JLabel rulesetErrorLabel;
	private JTextField regelsetNameTextField;
	private JTextField newFilenameTextField;

	private JComboBox<String> targetDirectoryComboBox;

	SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

	private String targetDirectoryList[];

	protected JButton addButton;
	private JButton deleteButton;

	private JButton saveButton;
	private JButton cancelButton;

	private JCheckBox statusCheckBox;

	private JTabbedPane tabbedPane;

	private int previousTabIndex = 0;

	/**
	 * Konstruktor welcher das View Objekt erstellt.
	 * 
	 * @param resourceBundle
	 *            Übersetzungen der aktuellen Sprache.
	 */
	public RegelsetView(ResourceBundle resourceBundle) {
		super(resourceBundle);

		setTitle(getMessage(TITLE_KEY));

		initComponents();

	}

	/**
	 * Initialisierung des Regelset-Fensters
	 */
	private void initComponents() {
		createComponents();
		layoutForm();
	}

	/**
	 * Holen aller Directories unterhalb der INBOX 
	 */
	private String[] getDirectories(String inboxDirectory, boolean recursiv) {

		final List<String> allFolders = FileHandling.getAllFolders(inboxDirectory);

		return allFolders.toArray(new String[0]);
	}

	/**
	 * Liefert die KontextType-Enums fuer die Regelkontext Combobox
	 * 
	 * @return Array mit den Regelkontext Enumerationen
	 * 
	 */
	ContextTypeEnum[] getContextList() {

		List<ContextTypeEnum> contextList = new ArrayList<ContextTypeEnum>();

		contextList.add(ContextTypeEnum.EMPTY);
		for (ContextTypeEnum contextItem : ContextTypeEnum.values()) {
			if (!contextItem.equals(ContextTypeEnum.EMPTY)) {
				contextList.add(contextItem);
			}
		}

		return contextList.toArray(new ContextTypeEnum[0]);
	}

	
	/**
	 * Liefert (abhaengig vom Regelkontext) die Attribut-Enums fuer die 
	 * Regelattribut Combobox 
	 *
	 * @param contextEnum
	 *                   Regelkontext
	 * @return Array mit den Regelattribut Enumerationen
	 */
	ContextAttributeEnum[] getAttributList(ContextTypeEnum contextEnum) {

		List<ContextAttributeEnum> attributeList = new ArrayList<ContextAttributeEnum>();

		attributeList.add(ContextAttributeEnum.EMPTY);
		for (ContextAttributeEnum attributeItem : ContextAttributeEnum.values(contextEnum)) {
			attributeList.add(attributeItem);
		}

		return attributeList.toArray(new ContextAttributeEnum[] {});
	}

	/**
	 * Liefert (abhaengig vom Regelattribut) die Comparison-Enums für die 
	 * Vergleichsart Combobox
	 * 
	 * @param attributeEnum
	 *                     Regelattribut
	 * @return Array mit den Vergleichsart Enumerationen
	 */
	ComparisonTypeEnum[] getComparisonModeList(ContextAttributeEnum attributeEnum) {
		List<ComparisonTypeEnum> comparisonList = new ArrayList<ComparisonTypeEnum>();

		final ComparisonTypeEnum[] comparisontype = attributeEnum.getDataType().getComparisontype();

		comparisonList.add(ComparisonTypeEnum.EMPTY);
		for (ComparisonTypeEnum comparisonTypeEnum : comparisontype) {

			comparisonList.add(comparisonTypeEnum);
		}

		return comparisonList.toArray(new ComparisonTypeEnum[0]);
	}

	/**
	 * Erstellt alle GUI Komponenten.
	 */
	private void createComponents() {

		rulesetErrorLabel = new JLabel();
		rulesetErrorLabel.setForeground(Color.red);
		Font font = rulesetErrorLabel.getFont();
		rulesetErrorLabel.setFont(new Font(font.getFontName(), Font.BOLD, font.getSize()));
		rulesetErrorLabel.setVisible(false);

		regelsetNameTextField = new JTextField();

		newFilenameTextField = new JTextField();
		statusCheckBox = new JCheckBox(getMessage(I18N + ".checkbox.status"));

		tabbedPane = new JTabbedPane(JTabbedPane.TOP);

		addButton = new JButton(getMessage(I18N + ".button.add.icon"));
		deleteButton = new JButton(getMessage(I18N + ".button.delete.icon"));

		saveButton = new JButton(getMessage("base.save"));
		cancelButton = new JButton(getMessage("base.cancel"));

		saveButton.addActionListener(new SaveRulesetAction());
		cancelButton.addActionListener(new CloseAction());
	}

	/**
	 * Ordnet die erstellten GUI Komponenten.
	 */
	private void layoutForm() {

		FormBuilder builder = FormBuilder.create()
				.columns(
						"[20dlu , pref], 5dlu, [20dlu , pref], 5dlu, [20dlu , pref], 5dlu, [20dlu, pref]")
				.rows("p, $lg, p, $lg, p, $lg, p, $lg, p, $lg, p, $lg, p, $lg, p, $lg, p, $lg, p, $lg, p, $lg, p, $lg, p, $lg, p, $lg, p, $lg, p, $lg, p, $lg, p, $lg, p, $lg, p");

		builder.add(rulesetErrorLabel).rcw(1, 1, 7);
		;

		builder.addLabel(getMessage(I18N + ".label.rulesetName")).rcw(3, 1, 7);
		builder.add(regelsetNameTextField).rcw(5, 1, 7);

		builder.addLabel(getMessage(I18N + ".label.targetDirectory")).rcw(7, 1, 7);

		targetDirectoryList = getDirectories(ApplicationSettings.getInstance().getInbox_path(), true);
		targetDirectoryComboBox = new JComboBox<String>(targetDirectoryList);
		builder.add(targetDirectoryComboBox).rcw(9, 1, 7);

		builder.addLabel(getMessage(I18N + ".label.newFilename")).rcw(11, 1, 7);
		builder.add(newFilenameTextField).rcw(13, 1, 7);

		builder.addLabel(getMessage(I18N + ".label.status")).rcw(15, 1, 7);
		builder.add(statusCheckBox).rcw(17, 1, 3);

		builder.addSeparator(null).rcw(25, 1, 7);
		builder.add(tabbedPane).rcw(27, 1, 7);
		builder.addSeparator(null).rcw(33, 1, 7);

		builder.add(addButton).rcw(35, 1, 1);
		builder.add(deleteButton).rcw(35, 3, 1);
		builder.add(saveButton).rcw(35, 5, 1);
		builder.add(cancelButton).rcw(35, 7, 1);

		builder.padding(new EmptyBorder(5, 5, 5, 5));
		JPanel build = builder.build();

		getFrame().add(new JScrollPane(build), BorderLayout.CENTER);

		setDimension(425, 600);
	}

	/**
	 * Konfiguriert die einzelnen Komponenten und erstellt die Listener.
	 */
	@Override
	public void configureBindings() {

		regelsetNameTextField.setText(getModel().getRulesetName());
		targetDirectoryComboBox.setSelectedItem(getModel().getTargetDirectory());
		newFilenameTextField.setText(getModel().getNewFilename());
		statusCheckBox.setSelected(getModel().isRuleActiv());

		statusCheckBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getModel().setRuleActiv(statusCheckBox.isSelected());

			}
		});

		targetDirectoryComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String selectedDirectory = (String) targetDirectoryComboBox.getSelectedItem();
				getModel().setTargetDirectory(selectedDirectory);

			}
		});

		regelsetNameTextField.getDocument()
				.addDocumentListener(new RegelsetDocumentListener(getModel(), regelsetNameTextField));
		newFilenameTextField.getDocument()
				.addDocumentListener(new RegelsetDocumentListener(getModel(), newFilenameTextField));

		int counter = 1;
		for (RegelModel ruleModel : getModel().getRuleModelList()) {
			ruleModel.setRuleName(getMessage(I18N + ".label.rulename_prefix") + counter++);
			tabbedPane.addTab(ruleModel.getRuleName(), new RegelPanel(this, ruleModel));
		}
		
		addButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				int actualPanelIndex = tabbedPane.getSelectedIndex();
				if (((RegelPanel) tabbedPane.getComponentAt(actualPanelIndex)).isPanelValid()) {

					RegelModel ruleModel = RegelModel.getNullModel();

					getModel().getRuleModelList().add(tabbedPane.getSelectedIndex(), ruleModel);
					ruleModel.setRuleName(getMessage(I18N + ".label.rulename_prefix") + (tabbedPane.getTabCount() + 1));

					int tabInsertIndex = tabbedPane.getSelectedIndex() + 1;
					tabbedPane.insertTab(ruleModel.getRuleName(), null, new RegelPanel(RegelsetView.this, ruleModel),
							null, tabInsertIndex);
					tabbedPane.setSelectedIndex(tabInsertIndex);
				}
			}
		});

		deleteButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				if (tabbedPane.getTabCount() > 1) {
					int confirmed = JOptionPane.showConfirmDialog(null,
							getMessage(I18N + ".dialog.rule.delete.confirm",
									((RegelPanel) tabbedPane.getSelectedComponent()).getModel().getRuleName()),
							getMessage(I18N + ".dialog.rule.delete.title"), JOptionPane.YES_NO_OPTION);

					if (confirmed == JOptionPane.YES_OPTION) {
						getModel().getRuleModelList().remove(tabbedPane.getSelectedIndex());
						tabbedPane.remove(tabbedPane.getSelectedIndex());
					}
				} else {
					JOptionPane.showMessageDialog(null, getMessage(I18N + ".error.firstrule.notdeleteable"));
				}
			}
		});

		tabbedPane.addChangeListener(new ChangeListener() {

			private boolean tabChangePrevented = false;

			public void stateChanged(ChangeEvent e) {
				if (tabChangePrevented) {
					// Tab-Wechsel verhindert (bzw. auf urspruenglichen Tab zurueck gewechselt),
					// da Regel-Plausibiliserung NOK war 
					tabChangePrevented = false;
				} else {
					// "normaler" Tab-Wechsel
					if (getModel().getRuleModelList().size() > previousTabIndex) {
						if (((RegelPanel) tabbedPane.getComponentAt(previousTabIndex)).isPanelValid()) {
							// Regel-Plausibilisierung OK -> Tab-Wechsel akzeptieren
							previousTabIndex = tabbedPane.getSelectedIndex();
						} else {
							// Regel-Plausibilisierung NOK -> Tab-Wechel verhindern (bzw. auf 
							// vorherige Tab zurück wechseln)
							tabChangePrevented = true;
							tabbedPane.setSelectedIndex(previousTabIndex);
						}
					}
				}

			}
		});
	}

	/**
	 * Prüfung ob die Eingabewerte des Regelsets und der aktuellen Regel korrekt sind
	 * 
	 * @return <code>true</code> : es ist valid <br>
	 *         <code>false</code> = nicht valid
	 */
	protected boolean isRulesetValid() {

		boolean isValid = false;
		String errorMessage = "";

		if (regelsetNameTextField.getText() == null || regelsetNameTextField.getText().equals("")) {
			errorMessage = I18N + ".error.rulesetname.empty";
		} else if (targetDirectoryComboBox.getSelectedItem() == null
				|| targetDirectoryComboBox.getSelectedItem().equals("")) {
			errorMessage = I18N + ".error.targetdirectory.empty";
		}
		isValid = errorMessage.equals("");

		if (!isValid) {
			rulesetErrorLabel.setText(getMessage(errorMessage));
		}
		rulesetErrorLabel.setVisible(!isValid);

		// immer auch noch aktuelle Regel checken (kein short-circuit)
		isValid = isValid & ((RegelPanel) tabbedPane.getComponentAt(tabbedPane.getSelectedIndex())).isPanelValid();

		return isValid;
	}

	/**
	 * Speichert das Regelset in die Datenbank.
	 */
	private class SaveRulesetAction extends AbstractAction {

		public void actionPerformed(ActionEvent e) {

			if (isRulesetValid()) {
				getHandler().performOperation(RegelsetSaveOperation.class);
				if (getModel().getRegelsetTableModel() != null) {
					// New Regelset->Update View
					getModel().getRegelsetTableModel().setUpdateView(true);
				}
				getHandler().performOperation(CloseViewOperation.class);
			}
		}
	}

	/**
	 *  Schliesst das Regelset-Fenster
	 */
	private class CloseAction extends AbstractAction {

		public void actionPerformed(ActionEvent e) {
			getHandler().performOperation(CloseViewOperation.class);
		}
	}

	/**
	 *  DocumentListener fuer die JText-Felder Regelsetname und newFilename
	 */
	private class RegelsetDocumentListener implements DocumentListener {

		JTextField myTextField = null;
		RegelsetModel model = null;

		public RegelsetDocumentListener(RegelsetModel model, JTextField myTextField) {
			this.model = model;
			this.myTextField = myTextField;
		}

		public void insertUpdate(DocumentEvent e) {
			processEvent(e);

		}

		public void removeUpdate(DocumentEvent e) {
			processEvent(e);

		}

		public void changedUpdate(DocumentEvent e) {
			processEvent(e);
		}

		private void processEvent(DocumentEvent e) {

			if (myTextField == regelsetNameTextField) {
				model.setRulesetName(myTextField.getText());

			} else if (myTextField == newFilenameTextField) {
				model.setNewFilename(myTextField.getText());
			}
		}
	}
}
