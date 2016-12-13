package ch.ffhs.hdo.client.ui.regelset;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
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
 * RegelsetView 
 * 
 * @author Daniel Crazzolara
 *
 */

public class RegelsetView extends View<RegelsetModel> {

	private static Logger LOGGER = LogManager.getLogger(RegelsetView.class);

	final String I18N = "hdo.regelset";
	private final String TITLE_KEY = I18N + ".title";
	private JTextField regelsetNameTextField;
	private JTextField newFilenameTextField;

	private JTextField targetDirectoryTextField;

	private JComboBox<String> targetDirectoryComboBox;

	SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

	private String targetDirectoryList[];

	private JButton addButton;
	private JButton deleteButton;

	private JButton saveButton;
	private JButton cancelButton;

	private JCheckBox statusCheckBox;

	JTabbedPane tabbedPane;

	public RegelsetView(ResourceBundle resourceBundle) {
		super(resourceBundle);
		setTitle(getMessage(TITLE_KEY));

		initComponents();

	}

	private void initComponents() {
		createComponents();
		layoutForm();
	}

	private String[] getDirectories(String inboxDirectory, boolean recursiv) {

		final List<String> allFolders = FileHandling.getAllFolders(inboxDirectory);

		return allFolders.toArray(new String[0]);
	}

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

	ContextAttributeEnum[] getAttributList(ContextTypeEnum contextEnum) {

		List<ContextAttributeEnum> attributeList = new ArrayList<ContextAttributeEnum>();

		attributeList.add(ContextAttributeEnum.EMPTY);
		for (ContextAttributeEnum attributeItem : ContextAttributeEnum.values(contextEnum)) {
			attributeList.add(attributeItem);
		}

		return attributeList.toArray(new ContextAttributeEnum[] {});
	}

	ComparisonTypeEnum[] getComparisonModeList(ContextAttributeEnum attributeEnum) {
		List<ComparisonTypeEnum> comparisonList = new ArrayList<ComparisonTypeEnum>();

		final ComparisonTypeEnum[] comparisontype = attributeEnum.getDataType().getComparisontype();

		comparisonList.add(ComparisonTypeEnum.EMPTY);
		for (ComparisonTypeEnum comparisonTypeEnum : comparisontype) {

			comparisonList.add(comparisonTypeEnum);
		}

		return comparisonList.toArray(new ComparisonTypeEnum[0]);
	}

	private void createComponents() {

		regelsetNameTextField = new JTextField();

		targetDirectoryTextField = new JTextField();
		targetDirectoryTextField.setEditable(false);

		newFilenameTextField = new JTextField();
		statusCheckBox = new JCheckBox(getMessage(I18N + ".checkbox.status"));

		tabbedPane = new JTabbedPane(JTabbedPane.TOP);

		addButton = new JButton(getMessage(I18N + ".button.add.icon"));
		deleteButton = new JButton(getMessage(I18N + ".button.delete.icon"));

		saveButton = new JButton(getMessage("base.save"));
		cancelButton = new JButton(getMessage("base.cancel"));

		saveButton.addActionListener(new SaveRulesetAction());
		cancelButton.addActionListener(new CloseAction());
		addButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// TODO Check's auf Null oder ist dies aus der DB gegeneben?

				// 	Rulesets ohne Rules sind nicht möglich
				RegelModel ruleModel = RegelModel.getNullModel();

				getModel().getRuleModelList().add(tabbedPane.getSelectedIndex(), ruleModel);
				ruleModel.setRuleName(getMessage(I18N + ".label.rulename_prefix") + (tabbedPane.getTabCount() + 1));

				//tabbedPane.add(ruleModel.getRuleName(), new RulePanel(RegelsetView.this, ruleModel));
				
				tabbedPane.insertTab("Insert-Pos", null, new RulePanel(RegelsetView.this, ruleModel), null, tabbedPane.getTabCount());
				
				tabbedPane.setSelectedIndex(tabbedPane.getTabCount() - 1);
			}
		});

		deleteButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				int confirmed = JOptionPane.showConfirmDialog(null,
						getMessage(I18N + ".dialog.rule.delete.confirm",
								tabbedPane.getSelectedComponent().getName() + "XXX"),
						getMessage(I18N + ".dialog.rule.delete.title"), JOptionPane.YES_NO_OPTION);

				if (confirmed == JOptionPane.YES_OPTION) {
					tabbedPane.remove(tabbedPane.getSelectedIndex());
				}
			}
		});

	}

	private void layoutForm() {

		FormBuilder builder = FormBuilder.create()
				.columns(
						"right:pref, 5dlu, [20dlu , pref], 5dlu, [20dlu, pref], 5dlu, [20dlu, pref], 5dlu, [20dlu, pref], 5dlu, [20dlu, pref]")
				.rows("p, $lg, p, $lg, p, $lg, p, $lg, p, $lg, p, $lg, p, $lg, p, $lg, p, $lg, p, $lg, p, $lg, p, $lg, p, $lg, p, $lg, p, $lg, p, $lg, p, $lg, p, $lg, p, $lg, p");

		builder.addLabel(getMessage(I18N + ".label.rulesetName")).rcw(1, 1, 7);
		builder.add(regelsetNameTextField).rcw(3, 1, 3);

		builder.addLabel(getMessage(I18N + ".label.targetDirectory")).rcw(5, 1, 7);

		targetDirectoryList = getDirectories(ApplicationSettings.getInstance().getInbox_path(), true);
		targetDirectoryComboBox = new JComboBox<String>(targetDirectoryList);
		builder.add(targetDirectoryComboBox).rcw(7, 1, 3);

		builder.addLabel(getMessage(I18N + ".label.newFilename")).rcw(9, 1, 2);
		builder.add(newFilenameTextField).rcw(11, 1, 2);

		builder.addLabel(getMessage(I18N + ".label.status")).rcw(13, 1, 3);
		builder.add(statusCheckBox).rcw(15, 1, 3);

		//
		// Rule Panel erst später, weil hier ist Model noch nicht bekannt
		// -TODO: Oder bereits "leer" erstellen?
		//
		builder.addSeparator(null).rcw(23, 1, 7);
		builder.add(tabbedPane).rcw(25, 1, 1);
		builder.addSeparator(null).rcw(31, 1, 7);

		builder.add(addButton).rcw(33, 1, 1);
		builder.add(deleteButton).rcw(33, 3, 1);
		builder.add(saveButton).rcw(33, 5, 1);
		builder.add(cancelButton).rcw(33, 7, 1);

		builder.padding(new EmptyBorder(5, 5, 5, 5));
		JPanel build = builder.build();

		getFrame().add(build, BorderLayout.CENTER);

		setDimension(800, 600);
	}

	@Override
	public void configureBindings() {

		regelsetNameTextField.setText(getModel().getRulesetName());
		targetDirectoryComboBox.setSelectedItem(getModel().getTargetDirectory());
		newFilenameTextField.setText(getModel().getNewFilename());
		statusCheckBox.setSelected(getModel().isRuleActiv());
		
		// TODO: Ohne PropertyChangeListener funktionierts besser
		//
		//getModel().addPropertyChangeListener(new MyPropertyChangeListener());
		
		
		List<RegelModel> regelModel = getModel().getRuleModelList();

		statusCheckBox.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				getModel().setRuleActiv(statusCheckBox.isSelected());

			}
		});

		targetDirectoryComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO: bei jeder Action? eher unschön?
				// sollte doch nur bei ÄNDERUND des selektierten Items
				String selectedDirectory = (String) targetDirectoryComboBox.getSelectedItem();
				getModel().setTargetDirectory(selectedDirectory);

			}
		});

		regelsetNameTextField.getDocument().addDocumentListener(new RegelsetDocumentListener(regelsetNameTextField));
		newFilenameTextField.getDocument().addDocumentListener(new RegelsetDocumentListener(newFilenameTextField));

		int counter = 1;
		for (RegelModel ruleModel : getModel().getRuleModelList()) {
			ruleModel.setRuleName(getMessage(I18N + ".label.rulename_prefix") + counter);
			counter++;
			/***
			if (ruleModel.getContextType() != null) {
				//name = ruleModel.getContextType().toString();
			} else {
				name = "TODO: Titel generisch generieren..."; // .. gemäss
																// Combo-Auswahl
			}
			***/
			tabbedPane.addTab(ruleModel.getRuleName(), new RulePanel(this, ruleModel));
		}

	}

	private String checkInputValues() {
		String errorString = null;

		// TODO: anständige Plausi / Fehlerhandling
		//

		// Whitespace entfernen und dann erst checken!
		if (getModel().getRulesetName() == null || getModel().getRulesetName().equals("")) {
			errorString = "Bitte Regelsetname erfassen"; // TODO via ressourcen
		}

		return errorString;
	}

	private class SaveRulesetAction extends AbstractAction {

		public void actionPerformed(ActionEvent e) {
			// TODO: anständige Plausi / Fehlerhandling
			//

			// Whitespace entfernen und dann erst checken!
			String errorMsg = checkInputValues();
			if (errorMsg != null) {
				JOptionPane.showMessageDialog(null, errorMsg);
			} else {
				// Plausi Ok
				getHandler().performOperation(RegelsetSaveOperation.class);
				if (getModel().getRegelsetTableModel() == null) {
					getHandler().performOperation(CloseViewOperation.class);
				}
				else {
					//New Regelset->Update View
					getModel().getRegelsetTableModel().setUpdateView(true);
					getHandler().performOperation(CloseViewOperation.class);
				}
			}
		}
	}

	private class CloseAction extends AbstractAction {

		public void actionPerformed(ActionEvent e) {
			getHandler().performOperation(CloseViewOperation.class);
		}
	}

	private class RegelsetDocumentListener implements DocumentListener {

		JTextField myTextField = null;

		public RegelsetDocumentListener(JTextField myTextField) { // + MODEL$$$
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
				getModel().setRulesetName(myTextField.getText());

			} else if (myTextField == newFilenameTextField) {
				getModel().setNewFilename(myTextField.getText());
			}
		}
	}

	private class MyPropertyChangeListener implements PropertyChangeListener {
		
		
		 public void propertyChange(PropertyChangeEvent evt) { 
			 if (evt.getPropertyName() == "directories") { 
				 // TODO: gehört directories ins Model?
				 //       oder immer neu ab FileSystem lesen?
			 } else if (evt.getPropertyName() == "newFilename") {
				 newFilenameTextField.setText(getModel().getNewFilename());
			 } else if (evt.getPropertyName() == "rulesetId") { // NICHT AUF View  
				 
			 } else if (evt.getPropertyName() == "priority") { // Nicht auf DIESER VIEW =>
			 } else if (evt.getPropertyName() == "ruleActiv") {
				 statusCheckBox.setSelected(getModel().isRuleActiv()); 
			 } else if (evt.getPropertyName() == "ruleList") {
				  
		     } else if (evt.getPropertyName() == "rulesetName") {
		    	 if (! evt.getOldValue().equals(evt.getNewValue())) {
		    		 regelsetNameTextField.setText(getModel().getRulesetName());
		    	 }
		     } else if (evt.getPropertyName() == "targetDirectory") {
		    	 if (! evt.getOldValue().equals(evt.getNewValue())) {
		    		 targetDirectoryComboBox.setSelectedItem(getModel().getTargetDirectory());
		    	 }
		     }
		}
	}
}
