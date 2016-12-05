package ch.ffhs.hdo.client.ui.regelset;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
	private JTextField dateinamenKonfigurationTextField;
	private JTextField fromDateTextField[];
	private JTextField toDateTextField[];
	private JTextField equalTextField[];

	private JTextField targetDirectoryTextField;

	private JComboBox targetDirectoryComboBox;



	
	
	//private String attributeComboBoxList[] = new String[3];
	//private String comparisonModeList[]	= new String[5];

	private String targetDirectoryList[];

	private JButton addButton;
	private JButton deleteButton;

	private JButton saveButton;
	private JButton cancelButton;

	private JCheckBox statusCheckBox;

	//JPanel rulePanel[] = new JPanel[4];
	private JTabbedPane tabbedPane;

	private static enum PanelTypeEnum {
		PANEL_CONTEXT, PANEL_CONTEXT_ATTRIBUTE, PANEL_COMPARISON;
	}

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
	
	private String[] getContextList() { //RegelModel ruleModel) {
		String []contextList; // = new String[];
		
		// TODO: ?? elegantere Methode um aus Resourcen ALLE Contexte auszulesen ??

		List<String> contextItems = new ArrayList<String>();
		for (ContextTypeEnum contextItem : RegelModel.ContextTypeEnum.values()) {
			contextItems.add(getMessage(CONTEXT_COMBOBOXKEY + "." + contextItem.name().toLowerCase()));
			System.out.println("item: " + getMessage(CONTEXT_COMBOBOXKEY + "." + contextItem.name().toLowerCase()));
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
					System.out.println("item: " + getMessage(CONTEXT_COMBOBOXKEY + ".attribute.pdf." + contextItem.name().toLowerCase()));
				}
			}
		} else if(contextEnum.equals(contextEnum.CONTEXT_FILE)) {
			
			for (ContextAttributeEnum contextItem : RegelModel.ContextAttributeEnum.values()) {
				if (contextItem.name().startsWith("FILE_")) {
					attributeItems.add(getMessage(CONTEXT_COMBOBOXKEY + ".attribute.file." + contextItem.name().toLowerCase()));
					System.out.println("item: " + getMessage(CONTEXT_COMBOBOXKEY + ".attribute.file." + contextItem.name().toLowerCase()));
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
		
		//public static enum ComparisonTypeEnum {
			// COMPARISON_EQUAL, COMPARISON_UNEQUAL, COMPARISON_LESS_EQUAL, COMPARISON_GREATER_EQUAL, COMPARISON_REGEX, COMPARISON_LIST;
		//}
		// allgemeine Vergleichs-Operationen 
		comparisonModeItems.add(getMessage(COMPARISON_MODE_COMBOBOXKEY + ".comparison." + RegelModel.ComparisonTypeEnum.COMPARISON_EQUAL.toString().toLowerCase()));
		comparisonModeItems.add(getMessage(COMPARISON_MODE_COMBOBOXKEY + ".comparison." + RegelModel.ComparisonTypeEnum.COMPARISON_UNEQUAL.toString().toLowerCase()));
		
		// abhängige Vergleichs-Operationen
		if (attributeEnum.equals(attributeEnum.PDF_CREATION_DATE) ||
				attributeEnum.equals(attributeEnum.PDF_CREATION_DATE)) {
			comparisonModeItems.add(getMessage(COMPARISON_MODE_COMBOBOXKEY + ".comparison." + RegelModel.ComparisonTypeEnum.COMPARISON_UNEQUAL.toString().toLowerCase()));
			comparisonModeItems.add(getMessage(COMPARISON_MODE_COMBOBOXKEY + ".comparison." + RegelModel.ComparisonTypeEnum.COMPARISON_UNEQUAL.toString().toLowerCase()));
			
		} else if (attributeEnum.equals(attributeEnum.PDF_CREATION_DATE) ||
				attributeEnum.equals(attributeEnum.PDF_CREATION_DATE)) {
		    
		}		
		
		comparionModeList = comparisonModeItems.toArray(new String[0]);

		return comparionModeList;
	}

	private void createComponents() {

		regelsetNameTextField = new JTextField();

		targetDirectoryTextField = new JTextField();
		targetDirectoryTextField.setEditable(false);

		dateinamenKonfigurationTextField = new JTextField();
		fromDateTextField = new JTextField[4];
		toDateTextField = new JTextField[4];
		statusCheckBox = new JCheckBox(getMessage(I18N + ".checkbox.status"));

		tabbedPane = new JTabbedPane(JTabbedPane.TOP);

		fromDateTextField[0] = new JTextField();
		fromDateTextField[1] = new JTextField();
		fromDateTextField[2] = new JTextField();
		fromDateTextField[3] = new JTextField();

		toDateTextField[0] = new JTextField();
		toDateTextField[1] = new JTextField();
		toDateTextField[2] = new JTextField();
		toDateTextField[3] = new JTextField();

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
System.out.println("component: " + tabbedPane.getSelectedComponent());
RulePanel component = (RulePanel)tabbedPane.getSelectedComponent();
System.out.println("component cast: " +component.getName());
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


		builder.addLabel(getMessage(I18N + ".label.filenameConfigure")).rcw(9, 1, 7);
		builder.add(dateinamenKonfigurationTextField).rcw(11, 1, 3);

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
		dateinamenKonfigurationTextField.setText(getModel().getFilenameKonfiguration());
		statusCheckBox.setSelected(getModel().isRuleActiv());
		targetDirectoryComboBox.setSelectedItem(getModel().getTargetDirectory());

		
		List<RegelModel>regelModel = getModel().getRuleModelList();
		System.out.println("regelModel: " + regelModel);
		if (regelModel != null) {
			System.out.println("regelModel-len: " + regelModel.size());
		}
		
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
				System.out.println("TargetCombo" + selectedDirectory);
				getModel().setTargetDirectory(selectedDirectory);

			}
		});

		regelsetNameTextField.getDocument().addDocumentListener(new RegelsetDocumentListener(regelsetNameTextField));
		dateinamenKonfigurationTextField.getDocument().addDocumentListener(new RegelsetDocumentListener(dateinamenKonfigurationTextField));

		
		
		// ----------------------
		// mit Builder "innere" verschachtelte JPane für Regel_<x>
		//
		//
		// MUSS in END-Version aus Model "gezogen" werden
		//
		for (int i = 0; i < getModel().getRuleModelList().size(); i++) {
			/*
			 * Besteht jeweils nur aus einem Panel, das wiederum aus mehreren
			 * Panels besteht
			 */
			System.out.println("VOR addTab - Model" + getModel());
			//System.out.println("VOR addTab - List" + getModel().getRuleModelList()); 
			tabbedPane.addTab(getModel().getRuleModelList().get(i).getContextType().toString(), new RulePanel(getModel().getRuleModelList().get(i))); //getModel()));

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
			System.out.println("Event e:" + e);
			System.out.println("Start: " + e.getDocument().getStartPosition());

			System.out.println("textfield: " + myTextField.getText());

			if (myTextField == regelsetNameTextField) {
				System.out.println("regelsetNameTextField: " + myTextField.getText());
				getModel().setRulesetName(myTextField.getText());

			} else if (myTextField == dateinamenKonfigurationTextField) {
				System.out.println("dateinamenKonfigurationTextField: " + myTextField.getText());
				getModel().setFilenameKonfiguration(myTextField.getText());
			} else {
				System.out.println("?????????????????: " + myTextField.getText());
			}

		}
	}

	private class RulePanel extends JPanel  {
		private JComboBox contextComboBox;
		private JComboBox attributeComboBox;
		private JComboBox comparisonModeComboBox;

		
		DefaultComboBoxModel pdfAttributeModel = new DefaultComboBoxModel(getAttributList(RegelModel.ContextTypeEnum.CONTEXT_PDF));
		DefaultComboBoxModel fileAttributeModel = new DefaultComboBoxModel(getAttributList(RegelModel.ContextTypeEnum.CONTEXT_FILE));
		DefaultComboBoxModel contentAttributeModel = new DefaultComboBoxModel(getAttributList(RegelModel.ContextTypeEnum.CONTEXT_CONTENT));
		
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
		
	    	    
				
		RegelModel ruleModel = null;

		RulePanel(RegelModel ruleModel) {
			super(); 
			this.ruleModel = ruleModel;

			FormBuilder paneBuilder = FormBuilder.create()
					.columns("right:pref, 5dlu, [20dlu, pref], 5dlu, [20dlu, pref], 5dlu, [20dlu, pref], 5dlu, [20dlu, pref], 5dlu, [20dlu, pref], 5dlu, [20dlu, pref], 5dlu, [20dlu, pref]")
					.rows("p, $lg, p, $lg, p, $lg, p, $lg, p , $lg, p , $lg, p");

			paneBuilder.addLabel(getMessage(I18N + ".label.sortrule")).rcw(1, 1, 7);

			contextComboBox = new JComboBox<String>(getContextList());
			paneBuilder.add(contextComboBox).rcw(3, 1, 1);

			//attributeComboBox = new JComboBox<String>(getAttributList(ruleModel.getContextType()));
			attributeComboBox = new JComboBox();
			attributeComboBox.setModel(getAttributeModel(ruleModel.getContextType()));
			paneBuilder.add(attributeComboBox).rcw(3, 4, 4);
			
			comparisonModeComboBox = new JComboBox<String>(getComparisonModeList(ruleModel.getContextAttribute()));
			paneBuilder.add(comparisonModeComboBox).rcw(3, 10, 4);


			paneBuilder.addLabel(getMessage(I18N + ".label.rule.dynamic")).rcw(7, 1, 7);
			paneBuilder.addLabel(getMessage(I18N + ".label.rule.from")).rcw(9, 1, 7);
			paneBuilder.addLabel(getMessage(I18N + ".label.rule.to")).rcw(9, 4, 2);

			// JFrame frame=new JFrame("date display");
			JDatePickerImpl datePickerFrom;
			JDatePickerImpl datePickerTo;
			UtilDateModel modelDateFrom = new UtilDateModel();
			UtilDateModel modelDateTo = new UtilDateModel();

			modelDateFrom.setValue(new Date()); //.setDate(2016, 01, 01);
			modelDateFrom.setSelected(true);
			JDatePanelImpl dateFromPanel = new JDatePanelImpl(modelDateFrom);
			datePickerFrom = new JDatePickerImpl(dateFromPanel, null);

			modelDateTo.setDate(2016, 07, 25);
			modelDateTo.setSelected(true);
			JDatePanelImpl dateToPanel = new JDatePanelImpl(modelDateTo);
			datePickerFrom = new JDatePickerImpl(dateFromPanel, null);
			datePickerTo = new JDatePickerImpl(dateToPanel, null);

			// paneBuilder.add(fromDateTextField[i]).rcw(9, 1, 2);
			// paneBuilder.add(toDateTextField[i]).rcw(9, 4, 3);
			paneBuilder.add(datePickerFrom).rcw(11, 1, 2);
			paneBuilder.add(datePickerTo).rcw(11, 4, 6);

			paneBuilder.padding(new EmptyBorder(5, 5, 5, 5));
			datePickerTo.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					System.out.println("picker-Action: " + e.getSource());
					
					JDatePanelImpl impl = (JDatePanelImpl)e.getSource();
					System.out.println("impl- " + impl.getModel().getValue().toString());
					//DateModel m;
					Date date = (Date) impl.getModel().getValue();
					System.out.println("DAtE------------: " + date);
					
					
					System.out.println("date-day: " + impl.getModel().getDay());
					System.out.println("date-mon: " + impl.getModel().getMonth());
					System.out.println("date-day: " + impl.getModel().getYear());

				
					SimpleDateFormat formatter = new SimpleDateFormat("yyyyMd");
					System.out.println("formatter " + formatter.toLocalizedPattern());
					System.out.println("formatter " + formatter.toPattern());
					Date d1 = null;
					try {
						d1 = formatter.parse("20150218");
					} catch (ParseException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					System.out.println("d1: " + d1);
				
				}});
			
			ActionListener al = new comboBoxActionListener();
			contextComboBox.addActionListener(al);
			attributeComboBox.addActionListener(al);
			comparisonModeComboBox.addActionListener(al);
/*			
			contextComboBox.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					// TODO: bei jeder Action? eher unschön?
					// sollte doch nur bei ÄNDERUND des selektierten Items
					
					
					if (e.getSource() == contextComboBox ){
						System.out.println("YES!");
					} else {
						System.out.println("leider nicht");
					}
					
					System.out.println("Context e: " + e);
					String selectedContext = (String) contextComboBox.getSelectedItem();
					System.out.println("ContextCombo: " + selectedContext);
					System.out.println("ContextCombo Index: " + contextComboBox.getSelectedIndex());
					System.out.println("ContextCombo Objects.len: " + contextComboBox.getSelectedObjects().length);
				//	getModel().setTargetDirectory(selectedDirectory);

					// => Attriute Combobox neu aufbauen
					
					// grad ne neue machen? oder besser alte umCONFIGen
					//contextComboBox = new JComboBox<String>(getContextList());
					
					
					ContextTypeEnum selectedEnum = ContextTypeEnum.valueOf(ContextTypeEnum.values()[contextComboBox.getSelectedIndex()].name() );
					System.out.println("selected Enum: " + selectedEnum);
					//attributeComboBox.getModel()..set.get.get =  new JComboBox<String>(getAttributList(selectedEnum));
					//attributeComboBox.setModel(getAttributeModel(ruleModel.getContextType()));
					attributeComboBox.setModel(getAttributeModel(selectedEnum));
					// Model informieren diese Feuert => view neu aubauen
					//ruleModel.setContextAttribute(ContextAttributeEnum.PDF_SIZE.name());
					//

					comparisonModeComboBox.setVisible(false);
				}
			});
			
			attributeComboBox.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					// TODO: bei jeder Action? eher unschön?
					// sollte doch nur bei ÄNDERUND des selektierten Items
					System.out.println("attributeComboBox e: " + e);
					String selectedAttribute = (String) attributeComboBox.getSelectedItem();
					System.out.println("attributeComboBox: " + selectedAttribute);
					System.out.println("attributeComboBox Index: " + attributeComboBox.getSelectedIndex());
					System.out.println("attributeComboBox Object: " + attributeComboBox.getSelectedObjects().length);
				//	getModel().setTargetDirectory(selectedDirectory);

				}
			});
*/			
			
			this.add(paneBuilder.build());
			//this.repaint();
		}
		
		class comboBoxActionListener implements ActionListener {

			public void actionPerformed(ActionEvent e) {
				
				int tabIndex = tabbedPane.getSelectedIndex();
				RegelModel ruleModel = getModel().getRuleModelList().get(tabIndex);
				if (e.getSource() instanceof JComboBox) {
					JComboBox comboBox = (JComboBox)e.getSource();
					int selectedIndex = comboBox.getSelectedIndex();
					
					if (comboBox == contextComboBox ){
						System.out.println("contextComboBox: " + RegelModel.ContextAttributeEnum.values()[contextComboBox.getSelectedIndex()]);
						ruleModel.setContextType(RegelModel.ContextTypeEnum.values()[contextComboBox.getSelectedIndex()]);
					} else if (comboBox == attributeComboBox ) {
						System.out.println("attributeComboBox: " + RegelModel.ContextAttributeEnum.values()[attributeComboBox.getSelectedIndex()]);
						ruleModel.setContextAttribute(RegelModel.ContextAttributeEnum.values()[attributeComboBox.getSelectedIndex()]);
					} else if (comboBox == comparisonModeComboBox) {
						System.out.println("comparisonModeComboBox: " + RegelModel.ComparisonTypeEnum.values()[comparisonModeComboBox.getSelectedIndex()]);
					}
				
					

//					System.out.println("leider nicht");
				}
				
				System.out.println("Context e: " + e);
				String selectedContext = (String) contextComboBox.getSelectedItem();
				System.out.println("ContextCombo: " + selectedContext);
				System.out.println("ContextCombo Index: " + contextComboBox.getSelectedIndex());
				System.out.println("ContextCombo Objects.len: " + contextComboBox.getSelectedObjects().length);
			//	getModel().setTargetDirectory(selectedDirectory);

				// => Attriute Combobox neu aufbauen
				
				// grad ne neue machen? oder besser alte umCONFIGen
				//contextComboBox = new JComboBox<String>(getContextList());
				
				
				ContextTypeEnum selectedEnum = ContextTypeEnum.valueOf(ContextTypeEnum.values()[contextComboBox.getSelectedIndex()].name() );
				System.out.println("selected Enum: " + selectedEnum);
				//attributeComboBox.getModel()..set.get.get =  new JComboBox<String>(getAttributList(selectedEnum));
				//attributeComboBox.setModel(getAttributeModel(ruleModel.getContextType()));
				attributeComboBox.setModel(getAttributeModel(selectedEnum));
				// Model informieren diese Feuert => view neu aubauen
				//ruleModel.setContextAttribute(ContextAttributeEnum.PDF_SIZE.name());
				//

				comparisonModeComboBox.setVisible(false);
				
			}
			
		}
	}

}
