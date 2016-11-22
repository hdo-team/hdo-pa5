package ch.ffhs.hdo.client.ui.regelset;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.HashMap;
import java.util.ResourceBundle;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.jgoodies.forms.builder.FormBuilder;

import ch.ffhs.hdo.client.ui.base.View;
import ch.ffhs.hdo.client.ui.base.executable.CloseViewOperation;
import ch.ffhs.hdo.client.ui.einstellungen.executable.OptionsSaveOperation;
import ch.ffhs.hdo.client.ui.regelset.executable.RegelsetSaveOperation;
import ch.ffhs.hdo.client.ui.utils.ChooseDirectoryPathViewOperation;
import ch.ffhs.hdo.client.ui.utils.ChooseFilePathViewOperation;
import ch.ffhs.hdo.infrastructure.service.util.FileHandling;
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

/******************************************************
 * 
 * 
 * erste QUICK n DIRTY Version zum Ausprobieren
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
	private final String COMBOBOXKEY = I18N + ".target.directory";
	private final String CONTEXT_COMBOBOXKEY = I18N + ".rule.context";
	private final String ATTRIBUTE_COMBOBOXKEY = I18N + ".rule.attribute";
	private JTextField regelsetNameTextField;
	private JTextField dateinamenKonfigurationTextField;
	private JTextField fromDateTextField[];
	private JTextField toDateTextField[];


	private JTextField targetPathTextField;
	
	private JButton fileChooseButton;


	private JComboBox<String> targetDirectoryComboBox;
	private HashMap<String, String> targetDirectoryListe = new HashMap<String, String>();


	private JComboBox contextComboBox;
	private JComboBox attributeComboBox[] = new JComboBox[4];
	private String contextComboBoxList[] = new String[3];
	private String attributeComboBoxList[] = new String[3];

	private JButton addButton;
	private JButton deleteButton;

	private JButton saveButton;
	private JButton cancelButton;

	private JCheckBox statusCheckBox;

	JPanel rulePanel[] = new JPanel[4];
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

	private void createComponents() {

		regelsetNameTextField = new JTextField();

		// TODO: DirectoryListe ab FileSystem Lesen !!
		String targetDirectoryListe[] = { "C:\\temp", "C:\\daten\\rechnungen", "C:\\daten\\fotos", "C:\\daten\\gugus" };
		//targetDirectoryComboBox = new JComboBox<String>(targetDirectoryListe);

		targetPathTextField = new JTextField();
		targetPathTextField.setEditable(false);
		fileChooseButton = new JButton(getMessage("base.filechooser"));
		
		//String targetDirectoryListe[] = { "C:\\temp", "C:\\daten\\rechnungen", "C:\\daten\\fotos", "C:\\daten\\gugus" };
		//targetDirectoryComboBox = new JComboBox<String>(targetDirectoryListe);
		
		dateinamenKonfigurationTextField = new JTextField();
		fromDateTextField = new JTextField[4];
		toDateTextField = new JTextField[4];
		statusCheckBox = new JCheckBox(getMessage(I18N + ".checkbox.status"));

		tabbedPane = new JTabbedPane(JTabbedPane.TOP);

		contextComboBoxList[0] = getMessage(CONTEXT_COMBOBOXKEY + ".pdf.property");
		contextComboBoxList[1] = getMessage(CONTEXT_COMBOBOXKEY + ".file.attribute");
		contextComboBoxList[2] = getMessage(CONTEXT_COMBOBOXKEY + ".file.content");

		attributeComboBoxList[0] = getMessage(ATTRIBUTE_COMBOBOXKEY + ".date");
		attributeComboBoxList[1] = getMessage(ATTRIBUTE_COMBOBOXKEY + ".author");
		attributeComboBoxList[2] = getMessage(ATTRIBUTE_COMBOBOXKEY + ".size");
		attributeComboBox[0] = new JComboBox<String>(attributeComboBoxList);
		attributeComboBox[1] = new JComboBox<String>(attributeComboBoxList);
		attributeComboBox[2] = new JComboBox<String>(attributeComboBoxList);
		attributeComboBox[3] = new JComboBox<String>(attributeComboBoxList);

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
		saveButton.addActionListener(new SaveRulesetAction());

		cancelButton = new JButton(getMessage("base.cancel"));
		fileChooseButton.addActionListener(new OpenDirectoryChooser());
		
	}

	private void layoutForm() {

		FormBuilder builder = FormBuilder.create()
				.columns("right:pref, 5dlu,[20dlu, pref],5dlu,[20dlu, pref],5dlu, [20dlu, pref]")
				.rows("p, $lg, p, $lg, p, $lg, p, $lg, p, $lg, p, $lg, p, $lg, p, $lg, p, $lg, p, $lg, p, $lg, p, $lg, p, $lg, p, $lg, p, $lg, p, $lg, p, $lg, p, $lg, p, $lg, p");
				

		builder.addLabel(getMessage(I18N + ".label.rulesetName")).rcw(1, 1, 7);
		builder.add(regelsetNameTextField).rcw(3, 1, 3);

//		builder.add(inboxPathTextField).rcw(3, 3, 3);//
	//	builder.add(fileChooseButton).rcw(3, 7, 1);
		
		builder.addLabel(getMessage(I18N + ".label.targetDirectory")).rcw(5, 1, 1);
		builder.add(targetPathTextField).rcw(7, 3, 3);
		builder.add(fileChooseButton).rcw(7, 7, 1);
		
		//builder.add(targetDirectoryComboBox).rcw(7, 1, 3);

		builder.addLabel(getMessage(I18N + ".label.filenameConfigure")).rcw(9, 1, 7);
		builder.add(dateinamenKonfigurationTextField).rcw(11, 1, 3);

		builder.addLabel(getMessage(I18N + ".label.status")).rcw(13, 1, 3);
		builder.add(statusCheckBox).rcw(15, 1, 3);

		// ----------------------
		// mit Builder "innere" verschachtelte JPane für Regel_<x>
		//
		//
		// MUSS in END-Version aus Model "gezogen" werden
		//
		for (int i = 0; i < 4; i++) {
			FormBuilder paneBuilder = FormBuilder.create()
					.columns("right:pref, 5dlu,[20dlu, pref],5dlu,[20dlu, pref],5dlu, [20dlu, pref]")
					.rows("p, $lg, p, $lg, p, $lg, p, $lg, p , $lg, p , $lg, p");

			paneBuilder.addLabel(getMessage(I18N + ".label.sortrule")).rcw(1, 1, 7);

			contextComboBox = new JComboBox<String>(contextComboBoxList);
			paneBuilder.add(contextComboBox).rcw(3, 1, 1);

			paneBuilder.add(attributeComboBox[i]).rcw(3, 4, 3);

			paneBuilder.addLabel(getMessage(I18N + ".label.rule.dynamic")).rcw(5, 1, 7);
			paneBuilder.addLabel(getMessage(I18N + ".label.rule.from")).rcw(7, 1, 7);
			paneBuilder.addLabel(getMessage(I18N + ".label.rule.to")).rcw(7, 4, 3);

			// JFrame frame=new JFrame("date display");
			JDatePickerImpl datePickerFrom;
			JDatePickerImpl datePickerTo;
			UtilDateModel model = new UtilDateModel();
			model.setDate(2016, 11, 16);
			model.setSelected(true);
			JDatePanelImpl datePanel = new JDatePanelImpl(model);
			datePickerFrom = new JDatePickerImpl(datePanel, null);
			datePickerTo = new JDatePickerImpl(datePanel, null);

			// paneBuilder.add(fromDateTextField[i]).rcw(9, 1, 2);
			// paneBuilder.add(toDateTextField[i]).rcw(9, 4, 3);
			paneBuilder.add(datePickerFrom).rcw(9, 1, 2);
			paneBuilder.add(datePickerTo).rcw(9, 4, 3);

			paneBuilder.padding(new EmptyBorder(5, 5, 5, 5));

			rulePanel[i] = paneBuilder.build();
			tabbedPane.addTab("Test_" + i, rulePanel[i]);
		}

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
		
		getModel().addPropertyChangeListener(new PropertyChangeListener() {

			public void propertyChange(PropertyChangeEvent evt) {
				if (evt.getPropertyName() == "rulesetName") {
					regelsetNameTextField.setText(getModel().getRulesetName());
				}

			}
		});
		
		String resourcebundlekey = COMBOBOXKEY + "." + (getModel().getTargetDirectory());

		try {

			targetDirectoryComboBox.setSelectedItem(getMessage(resourcebundlekey));
		} catch (Exception e) {
			LOGGER.debug("Key existiert nicht Standard wir angezeigt");
		}
		
		
		dateinamenKonfigurationTextField.setText(getModel().getFilenameKonfiguration());
		
		getModel().addPropertyChangeListener(new PropertyChangeListener() {

			public void propertyChange(PropertyChangeEvent evt) {
				if (evt.getPropertyName() == "filenameKonfiguration") {
					dateinamenKonfigurationTextField.setText(getModel().getRulesetName());
				}

			}
		});

		statusCheckBox.setSelected(getModel().isRuleActiv());
		
		statusCheckBox.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				getModel().setRuleActiv(statusCheckBox.isSelected());

			}
		});

		statusCheckBox.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				getModel().setRuleActiv(statusCheckBox.isSelected());

			}
		});
	}
	
	// Aktion zum Speichern der Regelsets
	// von Adrian Perez Rodriguez
	private class SaveRulesetAction extends AbstractAction {

		public void actionPerformed(ActionEvent e) {
			
			// Gehört das hier hinein?
			getModel().setRulesetName(regelsetNameTextField.getText());
			getModel().setFilenameKonfiguration(dateinamenKonfigurationTextField.getText());
		
			getHandler().performOperation(RegelsetSaveOperation.class);
			getHandler().performOperation(CloseViewOperation.class);
			
		}
	}
	
	private class CloseAction extends AbstractAction {

		public void actionPerformed(ActionEvent e) {

			getHandler().performOperation(CloseViewOperation.class);

		}
	}

	private class OpenDirectoryChooser extends AbstractAction {

		public void actionPerformed(ActionEvent e) {

			getHandler().performOperationWithArgs(ChooseDirectoryPathViewOperation.class, true);

		}
	}		
}
