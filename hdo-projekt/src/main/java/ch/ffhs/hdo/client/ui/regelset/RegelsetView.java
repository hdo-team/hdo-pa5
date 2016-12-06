package ch.ffhs.hdo.client.ui.regelset;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import javax.imageio.IIOException;
import javax.swing.AbstractAction;
import javax.swing.DefaultComboBoxModel;
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
import ch.ffhs.hdo.client.ui.regelset.RegelModel.ContextAttributeEnum;
import ch.ffhs.hdo.client.ui.regelset.RegelModel.ContextTypeEnum;
import ch.ffhs.hdo.client.ui.regelset.executable.RegelsetSaveOperation;

import ch.ffhs.hdo.infrastructure.ApplicationSettings;

import ch.ffhs.hdo.infrastructure.service.util.FileHandling;
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

/******************************************************
 * 
 * 
 * 
 * wird noch überarbeitet !!!!!!!!!!!!!!!!
 * 
 * 
 * 
 * 
 * 
 * @author Daniel Crazzolara
 *
 */

public class RegelsetView extends View<RegelsetModel> {

	private static Logger LOGGER = LogManager.getLogger(FileHandling.class);

	private final String I18N = "hdo.regelset";
	private final String TITLE_KEY = I18N + ".title";
	private final String CONTEXT_COMBOBOXKEY = I18N + ".combobox";
	private final String ATTRIBUTE_COMBOBOXKEY = I18N + ".combobox";
	private final String COMPARISON_MODE_COMBOBOXKEY = I18N + ".combobox";
	private JTextField regelsetNameTextField;
	private JTextField newFilenameTextField;
	

	private JTextField targetDirectoryTextField;

	private JComboBox targetDirectoryComboBox;

	SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");


	private String targetDirectoryList[];

	private JButton addButton;
	private JButton deleteButton;

	private JButton saveButton;
	private JButton cancelButton;

	private JCheckBox statusCheckBox;

	private JTabbedPane tabbedPane;


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
	
	private String[] getContextList(boolean firstShow) { //RegelModel ruleModel) {
		String []contextList; // = new String[];
		
		// TODO: ?? elegantere Methode um aus Resourcen ALLE Contexte auszulesen ??

		List<String> contextItems = new ArrayList<String>();
		if (firstShow) {
			contextItems.add("");
		}
		for (ContextTypeEnum contextItem : RegelModel.ContextTypeEnum.values()) {
			contextItems.add(getMessage(CONTEXT_COMBOBOXKEY + "." + contextItem.name().toLowerCase()));
		}
		contextList = contextItems.toArray(new String[0]);

		return contextList;
	}

	
	
	private String[] getAttributList(RegelModel.ContextTypeEnum contextEnum) { //RegelModel ruleModel) {
		String []attributeList; // = new String[];
		
		
		//
		// TODO holprig
		//
		if (contextEnum == null) {
			return new String[]{};
		}
		
		// TODO: ?? elegantere Methode um aus Resourcen ALLE Contexte auszulesen ??
		List<String> attributeItems = new ArrayList<String>();
		
		
		//
		// Resourcen + ENUM => attribute <PFD|FILE> pdf_title
		// => elegante Methode suchen, dass weniger Duplicates in namen
		//
		if (contextEnum.equals(contextEnum.CONTEXT_PDF)) {
			for (ContextAttributeEnum contextItem : RegelModel.ContextAttributeEnum.values()) {
				if (contextItem.name().startsWith("PDF_")) {
					attributeItems.add(getMessage(CONTEXT_COMBOBOXKEY + ".attribute.pdf." + contextItem.name().toLowerCase()));
				}
			}
		} else if(contextEnum.equals(contextEnum.CONTEXT_FILE)) {
			
			for (ContextAttributeEnum contextItem : RegelModel.ContextAttributeEnum.values()) {
				if (contextItem.name().startsWith("FILE_")) {
					attributeItems.add(getMessage(CONTEXT_COMBOBOXKEY + ".attribute.file." + contextItem.name().toLowerCase()));
				}
			}
		} else if (contextEnum.equals(contextEnum.CONTEXT_CONTENT)) {
			// keine zweite ComboBox nötig
		} else {
			// eigenlicht nicht möglich (TODO)
			// aber egal
			// 
			// => zweite Combobox bleibt leer
			
		}
		attributeList = attributeItems.toArray(new String[0]);

		return attributeList;
	}
	
	private String[] getComparisonModeList(RegelModel.ContextAttributeEnum attributeEnum) {
		String[] comparionModeList; 
		List<String> comparisonModeItems = new ArrayList<String>();
		
		//
		// TODO holprig
		//
		if (attributeEnum == null) {
			return new String[]{};
		}
		

		// allgemeine Vergleichs-Operationen 
		comparisonModeItems.add(getMessage(COMPARISON_MODE_COMBOBOXKEY + ".comparison." + RegelModel.ComparisonTypeEnum.COMPARISON_EQUAL.toString().toLowerCase()));
		comparisonModeItems.add(getMessage(COMPARISON_MODE_COMBOBOXKEY + ".comparison." + RegelModel.ComparisonTypeEnum.COMPARISON_UNEQUAL.toString().toLowerCase()));
System.out.println("attribute-ENum" + attributeEnum);		
		// abhängige Vergleichs-Operationen
		if (attributeEnum.equals(attributeEnum.PDF_CREATION_DATE) ||
				attributeEnum.equals(attributeEnum.PDF_CREATION_DATE)) {
			System.out.println("im CreationDAte");
			comparisonModeItems.add(getMessage(COMPARISON_MODE_COMBOBOXKEY + ".comparison." + RegelModel.ComparisonTypeEnum.COMPARISON_UNEQUAL.toString().toLowerCase()));
			comparisonModeItems.add(getMessage(COMPARISON_MODE_COMBOBOXKEY + ".comparison." + RegelModel.ComparisonTypeEnum.COMPARISON_UNEQUAL.toString().toLowerCase()));
			
		}//
		//else if (attributeEnum.equals(attributeEnum.PDF_CREATION_DATE) ||
		//		attributeEnum.equals(attributeEnum.PDF_CREATION_DATE)) {
		  //  
		//}		
		
		comparionModeList = comparisonModeItems.toArray(new String[0]);

		return comparionModeList;
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
				// TDODO Check's auf Null oder ist dies aus der DB gegeneben?
				//       Rulesets ohne Rules sind nicht möglich
				RegelModel ruleModel = new RegelModel();
				getModel().getRuleModelList().add(tabbedPane.getSelectedIndex(), ruleModel);
				tabbedPane.add("frisch geADDed", new RulePanel(ruleModel));   //getModel()));  
			}
		});

		deleteButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				int confirmed = JOptionPane.showConfirmDialog(null,
						getMessage(I18N + ".dialog.rule.delete.confirm", tabbedPane.getSelectedComponent().getName() + "XXX"),
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


		builder.addLabel(getMessage(I18N + ".label.newFilename")).rcw(9, 1, 7);
		builder.add(newFilenameTextField).rcw(11, 1, 3);

		builder.addLabel(getMessage(I18N + ".label.status")).rcw(13, 1, 3);
		builder.add(statusCheckBox).rcw(15, 1, 3);

//
		//  Rule Panel erst später, weil hier ist Model noch nicht bekannt
		//  -TODO: Oder bereits "leer" erstellen?
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
		newFilenameTextField.setText(getModel().getNewFilename());
		statusCheckBox.setSelected(getModel().isRuleActiv());
		targetDirectoryComboBox.setSelectedItem(getModel().getTargetDirectory());

		
		List<RegelModel>regelModel = getModel().getRuleModelList();
		
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

		
		

		for (RegelModel ruleModel : getModel().getRuleModelList()) {
			String name;
			if (ruleModel.getContextType() != null) {
				name = ruleModel.getContextType().toString();
			} else {
				name = "TODO: Titel generisch generieren...";  //.. gemäss Combo-Auswahl
			}
			tabbedPane.addTab(name, new RulePanel(ruleModel)); 
		}

		
		
	}

	private class SaveRulesetAction extends AbstractAction {

		public void actionPerformed(ActionEvent e) {
			getHandler().performOperation(RegelsetSaveOperation.class);
			getHandler().performOperation(CloseViewOperation.class);
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
			} else {
				System.out.println("?????????????????: " + myTextField.getText());
			}

		}
	}

	private class RulePanel extends JPanel  {
		private JComboBox contextComboBox;
		private JComboBox attributeComboBox;
		private JComboBox comparisonModeComboBox;
		private JTextField compareTextField; 
		
		
		DefaultComboBoxModel pdfAttributeModel = new DefaultComboBoxModel(getAttributList(RegelModel.ContextTypeEnum.CONTEXT_PDF));
		DefaultComboBoxModel fileAttributeModel = new DefaultComboBoxModel(getAttributList(RegelModel.ContextTypeEnum.CONTEXT_FILE));
		DefaultComboBoxModel contentAttributeModel = new DefaultComboBoxModel(getAttributList(RegelModel.ContextTypeEnum.CONTEXT_CONTENT));
		
		/*
		DefaultComboBoxModel<RegelModel.ContextAttributeEnum> pdfAttributeModel = new DefaultComboBoxModel(RegelModel.ContextAttributeEnum.values(RegelModel.ContextTypeEnum.CONTEXT_PDF));
		DefaultComboBoxModel<RegelModel.ContextAttributeEnum> fileAttributeModel = new DefaultComboBoxModel(RegelModel.ContextAttributeEnum.values(RegelModel.ContextTypeEnum.CONTEXT_FILE));
		DefaultComboBoxModel<RegelModel.ContextAttributeEnum> contentAttributeModel = new DefaultComboBoxModel(RegelModel.ContextAttributeEnum.values(RegelModel.ContextTypeEnum.CONTEXT_CONTENT));
		*/
		
		DefaultComboBoxModel getAttributeModel(RegelModel.ContextTypeEnum contextEnum) {
			DefaultComboBoxModel attributeModel = null;
			
			if (contextEnum.equals(contextEnum.CONTEXT_PDF)) {
				attributeModel = pdfAttributeModel;
			} else if(contextEnum.equals(contextEnum.CONTEXT_FILE)) {
				attributeModel = fileAttributeModel;
			} else if (contextEnum.equals(contextEnum.CONTEXT_CONTENT)) {
				attributeModel = contentAttributeModel;
			}
			
			
			return attributeModel;
		}
		
		DefaultComboBoxModel getComparisonModeModel(RegelModel.ContextAttributeEnum attributeEnum) {
			 
			//Es wird immer ein neues Model gemacht... TODO: besser das Model NUR anpasssen?
			DefaultComboBoxModel attributeModel = new DefaultComboBoxModel(getComparisonModeList(attributeEnum));
				
			return attributeModel;
		}

				
		RegelModel ruleModel = null;

		RulePanel(final RegelModel ruleModel) {
			super(); 
			this.ruleModel = ruleModel;

			FormBuilder paneBuilder = FormBuilder.create()
					.columns("right:pref, 5dlu, [20dlu, pref], 5dlu, [20dlu, pref], 5dlu, [20dlu, pref], 5dlu, [20dlu, pref], 5dlu, [20dlu, pref], 5dlu, [20dlu, pref], 5dlu, [20dlu, pref]")
					.rows("p, $lg, p, $lg, p, $lg, p, $lg, p , $lg, p, $lg, p, $lg, p");

			paneBuilder.addLabel(getMessage(I18N + ".label.sortrule")).rcw(1, 1, 7);

			contextComboBox = new JComboBox<String>(getContextList(ruleModel.getContextType() == null));

//			contextComboBox.setSelectedIndex(ruleModel.getContextType().ordinal() - 1);
			paneBuilder.add(contextComboBox).rcw(3, 1, 1);

			attributeComboBox = new JComboBox<String>(getAttributList(ruleModel.getContextType()));
			//attributeComboBox = new JComboBox<RegelModel.ContextAttributeEnum>();
			//attributeComboBox.setModel(getAttributeModel(ruleModel.getContextType()));
			//>attributeComboBox.setModel(ruleModel.getContextType());
	//		attributeComboBox..setModel(RegelModel.ComparisonTypeEnum.values());
	//		attributeComboBox.setSelectedIndex(ruleModel.getContextAttribute().ordinal() - 1);
			paneBuilder.add(attributeComboBox).rcw(3, 4, 4);
			
			comparisonModeComboBox = new JComboBox<String>(getComparisonModeList(ruleModel.getContextAttribute()));
			
			// default-wERt ins Model sonst NP-Ex	
			ruleModel.setComparisonType(RegelModel.ComparisonTypeEnum.COMPARISON_EQUAL);
			
	//		comparisonModeComboBox.setSelectedIndex(ruleModel.getComparisonType().ordinal() - 1 );
			paneBuilder.add(comparisonModeComboBox).rcw(11, 1, 2);


			paneBuilder.addLabel(getMessage(I18N + ".label.rule.dynamic")).rcw(7, 1, 7);
			paneBuilder.addLabel(getMessage(I18N + ".label.rule.from")).rcw(9, 1, 7);
			paneBuilder.addLabel(getMessage(I18N + ".label.rule.to")).rcw(9, 4, 2);

			JDatePickerImpl datePicker;
			UtilDateModel modelDate = new UtilDateModel();
			// TODO:  Date compareDate = null;
			//
System.out.println("vor compdate: " + ruleModel.getContextAttribute());
			Date compareDate =null;//TDOO:  default <heute> Nok   //TDOO:  default <heute> Nok
			try {
				compareDate = simpleDateFormat.parse(ruleModel.getCompareValue());
			} catch (ParseException  e1) {
				// TODO: throw new IllegalArgumentException("invalid date: " + compareDate);
			} catch (NullPointerException  npEx) {
					// TODO: wieder Entfernen, und abfragen auf DATE
			}
			modelDate.setValue(compareDate); 
			JDatePanelImpl datePanel = new JDatePanelImpl(modelDate);
			datePicker = new JDatePickerImpl(datePanel, null);
			paneBuilder.add(datePicker).rcw(11, 4, 6);

			
			compareTextField = new JTextField();
			paneBuilder.add(compareTextField).rcw(15, 1, 9);
			
			paneBuilder.padding(new EmptyBorder(5, 5, 5, 5));
			datePicker.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					
					JDatePanelImpl impl = (JDatePanelImpl)e.getSource();
					Date date = (Date)impl.getModel().getValue();
					ruleModel.setCompareValue(simpleDateFormat.format(date));
				}});
			
			ActionListener al = new comboBoxActionListener();
			contextComboBox.addActionListener(al);
			attributeComboBox.addActionListener(al);
			comparisonModeComboBox.addActionListener(al);
			
			this.add(paneBuilder.build());
		}
		
		class comboBoxActionListener implements ActionListener {

			public void actionPerformed(ActionEvent e) {
				
				int tabIndex = tabbedPane.getSelectedIndex();
				RegelModel ruleModel = getModel().getRuleModelList().get(tabIndex);
				if (e.getSource() instanceof JComboBox) {
					JComboBox comboBox = (JComboBox)e.getSource();
					int selectedIndex = comboBox.getSelectedIndex();
					
					if (comboBox == contextComboBox ){
						if (contextComboBox.getItemAt(0).equals("")) {
							contextComboBox.removeItemAt(0); 
						}
						
						
						System.out.println("contextComboBox: " + RegelModel.ContextTypeEnum.values()[contextComboBox.getSelectedIndex()]);
						ruleModel.setContextType(RegelModel.ContextTypeEnum.values()[contextComboBox.getSelectedIndex()]);
						// abhängiges AttributeContext neu aufbauen
						attributeComboBox.setModel(getAttributeModel(ruleModel.getContextType()));
System.out.println("attributeComboBox.getModel().getSize(): " + attributeComboBox.getModel().getSize());
						attributeComboBox.setVisible(attributeComboBox.getModel().getSize() != 0);
					} else if (comboBox == attributeComboBox ) {
						System.out.println("attributeComboBox: " + RegelModel.ContextAttributeEnum.values()[attributeComboBox.getSelectedIndex()]);
						System.out.println("attributeComboBox-M: " + attributeComboBox.getModel().getElementAt(attributeComboBox.getSelectedIndex()));
						ruleModel.setContextAttribute(RegelModel.ContextAttributeEnum.values()[attributeComboBox.getSelectedIndex()]);
						// abhängiges AttributeContext neu aufbauen
						comparisonModeComboBox.setModel(getComparisonModeModel(ruleModel.getContextAttribute()));
						// default-wERt ins Model sonst NP-Ex
						ruleModel.setComparisonType(RegelModel.ComparisonTypeEnum.COMPARISON_EQUAL);
						
					} else if (comboBox == comparisonModeComboBox) {
						System.out.println("comparisonModeComboBox: " + RegelModel.ComparisonTypeEnum.values()[comparisonModeComboBox.getSelectedIndex()]);
						ruleModel.setComparisonType(RegelModel.ComparisonTypeEnum.values()[comparisonModeComboBox.getSelectedIndex()]);
					}
				}
			}
			
		}
	}

}
