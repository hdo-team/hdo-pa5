package ch.ffhs.hdo.client.ui.regelset;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.ParseException;
import java.util.Date;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.jgoodies.forms.builder.FormBuilder;

import ch.ffhs.hdo.domain.regel.ComparisonTypeEnum;
import ch.ffhs.hdo.domain.regel.ContextAttributeEnum;
import ch.ffhs.hdo.domain.regel.ContextTypeEnum;
import ch.ffhs.hdo.domain.regel.DataTypeEnum;
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

/**
 * Panel zum Erstellen und Bearbeiten einer spezifischen Regel eines Regelsets
 * 
 * @author Daniel Crazzolara
 *
 */
public class RegelPanel extends JPanel {

	private static final long serialVersionUID = -3417369737468476369L;
	private static Logger LOGGER = LogManager.getLogger(RegelPanel.class);

	private RegelsetView rulePanelView;
	private JComboBox<ContextTypeEnum> contextComboBox;
	private JComboBox<ContextAttributeEnum> attributecmbox;
	private JComboBox<ComparisonTypeEnum> comparisonModeComboBox;
	private JTextField compareValueTextField;
	private JDatePickerImpl datePicker;
	private JDatePanelImpl datePanel;
	private RegelModel model;
	private JLabel ruleErrorLabel;
	private JLabel comparisonModeLabel;
	private JLabel compareValueLabel;
	private JLabel compareDateLabel;

	private DefaultComboBoxModel<ContextAttributeEnum> pdfAttributeModel;
	private DefaultComboBoxModel<ContextAttributeEnum> fileAttributeModel;
	private DefaultComboBoxModel<ContextAttributeEnum> contentAttributeModel;

	/**
	 * Liefert (abhaengig vom Regelkontext) das Model fuer die RegelAttribut-Combobox
	 * 
	 * @param RegelModel
	 *            RegelModel (inkl. Regelkontext)
	 * @return attributeModel
	 *		       Model fuer die Combobox
	 */
	private DefaultComboBoxModel<ContextAttributeEnum> getAttributeModel(RegelModel regelModel) {
		DefaultComboBoxModel<ContextAttributeEnum> attributeModel = null;

		if (regelModel.getContextType().equals(ContextTypeEnum.CONTEXT_PDF)) {
			attributeModel = pdfAttributeModel;
		} else if (regelModel.getContextType().equals(ContextTypeEnum.CONTEXT_FILE)) {
			attributeModel = fileAttributeModel;

		} else if (regelModel.getContextType().equals(ContextTypeEnum.CONTENT_FILE)) {
			attributeModel = contentAttributeModel;
		}

		return attributeModel;
	}

	/**
	 * Liefert (abhaengig vom Regelattribut) das Model fuer die Vergleichsart-Combobox
	 * 
	 * @param RegelModel
	 *            RegelModel (inkl. Regelattribut)
	 * @return comparisionModeModel
	 *		       Model fuer die Combobox
	 */
	private DefaultComboBoxModel<ComparisonTypeEnum> getComparisonModeModel(ContextAttributeEnum attributeEnum) {

		DefaultComboBoxModel<ComparisonTypeEnum> comparisionModeModel = 
					new DefaultComboBoxModel<ComparisonTypeEnum>(
								rulePanelView.getComparisonModeList(attributeEnum));

		return comparisionModeModel;
	}

	public RegelModel getModel() {
		return model;
	}

	/**
	 * Kontruktor welcher das Regel-Panel erstellt
	 * 
	 * @param regelsetView
	 *            View auf dem das Regelset (inkl. dieses Panels) dargestellt wird
	 * @param ruleModel
	 *            Regelmodel das zur Regel auf diesem Panel gehoert
	 */
	public RegelPanel(RegelsetView regelsetView, final RegelModel ruleModel) {
		super();
		rulePanelView = regelsetView;
		this.model = ruleModel;

		createComponent();
		setComboboxModel();

		configureBinding();
		setLayout();
	}

	/**
	 * Erstellt alle GUI Komponenten.
	 */
	private void createComponent() {
		final String compmode = rulePanelView.getMessage(rulePanelView.I18N + ".label.comparemode");
		final String compareval = rulePanelView.getMessage(rulePanelView.I18N + ".label.comparevalue");
		final String comparedate = rulePanelView.getMessage(rulePanelView.I18N + ".label.comparedate");

		compareDateLabel = new JLabel(comparedate);
		compareValueLabel = new JLabel(compareval);
		comparisonModeLabel = new JLabel(compmode);

		ruleErrorLabel = new JLabel();
		ruleErrorLabel.setForeground(Color.red);
		Font font = ruleErrorLabel.getFont();
		ruleErrorLabel.setFont(new Font(font.getFontName(), Font.BOLD, font.getSize()));
		ruleErrorLabel.setVisible(false);

		contextComboBox = new JComboBox<ContextTypeEnum>(rulePanelView.getContextList());
		attributecmbox = new JComboBox<ContextAttributeEnum>(rulePanelView.getAttributList(model.getContextType()));
		comparisonModeComboBox = new JComboBox<ComparisonTypeEnum>(
				rulePanelView.getComparisonModeList(model.getContextAttribute()));
		datePanel = new JDatePanelImpl(new UtilDateModel());
		datePicker = new JDatePickerImpl(datePanel, null);
		compareValueTextField = new JTextField();

		datePicker.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				JDatePanelImpl impl = (JDatePanelImpl) e.getSource();
				Date date = (Date) impl.getModel().getValue();
				model.setCompareValue(RegelPanel.this.rulePanelView.simpleDateFormat.format(date));
			}
		});

		ActionListener al = new ComboBoxActionListener();
		contextComboBox.addActionListener(al);
		attributecmbox.addActionListener(al);
		comparisonModeComboBox.addActionListener(al);
		compareValueTextField.getDocument()
				.addDocumentListener(new RegelDocumentListener(model, compareValueTextField));
	}
	
	/**
	 * Ordnet die erstellten GUI Komponenten.
	 */
	private void setLayout() {
		FormBuilder paneBuilder = FormBuilder.create()
				.columns(
						"left:pref, 5dlu, [30dlu, pref], 5dlu, [30dlu, pref], 5dlu, [30dlu, pref], 5dlu, [30dlu, pref]")
				.rows("p, $lg, p, $lg, p, $lg, p, $lg, p, $lg, p , $lg, p, $lg, p, $lg, p");

		paneBuilder.add(ruleErrorLabel).rcw(1, 1, 9);
		;
		paneBuilder.add(rulePanelView.getMessage(rulePanelView.I18N + ".label.rulecontext")).rcw(3, 1, 7);
		paneBuilder.add(rulePanelView.getMessage(rulePanelView.I18N + ".label.ruleattribute")).rcw(3, 4, 4);
		paneBuilder.add(contextComboBox).rcw(5, 1, 1);
		paneBuilder.add(attributecmbox).rcw(5, 4, 4);
		paneBuilder.add(comparisonModeComboBox).rcw(13, 1, 2);

		paneBuilder.add(comparisonModeLabel).rcw(9, 1, 3);
		paneBuilder.add(compareDateLabel).rcw(9, 4, 6);
		paneBuilder.add(datePicker).rcw(13, 4, 6);

		paneBuilder.add(compareValueLabel).rcw(15, 1, 9);
		paneBuilder.add(compareValueTextField).rcw(17, 1, 9);

		paneBuilder.padding(new EmptyBorder(5, 5, 5, 5));

		visibiltyComponentInCombination(getModel().getContextAttribute());

		this.add(paneBuilder.build());
	}

	/**
	 * Konfiguriert die einzelnen Komponenten und erstellt die Listener.
	 */
	private void configureBinding() {

		contextComboBox.setSelectedItem(getModel().getContextType());
		attributecmbox.setSelectedItem(getModel().getContextAttribute());
		comparisonModeComboBox.setSelectedItem(getModel().getComparisonType());

		if (getModel().getContextAttribute().getDataType() == DataTypeEnum.DATE) {
			Date compareDate = null;
			try {
				compareDate = rulePanelView.simpleDateFormat.parse(model.getCompareValue());
			} catch (ParseException e1) {
				// "unmoeglicher" Fehler (User kann Datum nirgends "frei" eingeben)
				LOGGER.error("FATAL: Datum " + model.getCompareValue() + " konnte nicht konveriert werden.", e1);
			}
			((UtilDateModel) datePanel.getModel()).setValue(compareDate);
		} else {
			compareValueTextField.setText(model.getCompareValue());
		}

		model.addPropertyChangeListener(new ModelChangeListener());
	}

	/**
	 * Erstellt kontextabhaengige Default-Models fuer die Regelattribute-Combobox
	 * 
	 */
	private void setComboboxModel() {
		pdfAttributeModel = new DefaultComboBoxModel<ContextAttributeEnum>(
				rulePanelView.getAttributList(ContextTypeEnum.CONTEXT_PDF));
		fileAttributeModel = new DefaultComboBoxModel<ContextAttributeEnum>(
				rulePanelView.getAttributList(ContextTypeEnum.CONTEXT_FILE));
		contentAttributeModel = new DefaultComboBoxModel<ContextAttributeEnum>(
				rulePanelView.getAttributList(ContextTypeEnum.CONTENT_FILE));
	}

	/**
	 * Pruefung ob die Inhalte und Auswahl korrekt sind
	 * 
	 * @return <code>true</code> : es ist valid <br>
	 *         <code>false</code> : nicht valid
	 */
	protected boolean isPanelValid() {

		boolean isValid = false;
		String errorMessage = "";

		final Object item = contextComboBox.getSelectedItem();

		if (item.equals(ContextTypeEnum.EMPTY)) {

			errorMessage = rulePanelView.I18N + ".error.contexttype.empty";

		} else if (attributecmbox.isVisible() && attributecmbox.getSelectedItem().equals(ContextAttributeEnum.EMPTY)) {

			errorMessage = rulePanelView.I18N + ".error.contextattribute.empty";

		} else if (comparisonModeComboBox.getSelectedItem().equals(ComparisonTypeEnum.EMPTY)) {

			errorMessage = rulePanelView.I18N + ".error.comparisonmode.empty";
		}
		ContextAttributeEnum contextAttribute = (ContextAttributeEnum) attributecmbox.getSelectedItem();
		switch (contextAttribute.getDataType()) {
		case DATE:
			if (((UtilDateModel) datePanel.getModel()).getValue() == null) {
				errorMessage = rulePanelView.I18N + ".error.comparedate.empty";
			}

			break;
		case INT:
			if (compareValueTextField.getText() == null || compareValueTextField.getText().equals("")) {
				errorMessage = rulePanelView.I18N + ".error.comparevalue.empty";
			} else {
				try {
					Integer.valueOf(compareValueTextField.getText());
				} catch (NumberFormatException nfe) {
					LOGGER.debug("Vergleichswert is kein Integer", nfe);
					errorMessage = rulePanelView.I18N + ".error.comparevalue.not.integer";
				}
			}
			break;
		case STRING:
			if (compareValueTextField.getText() == null || compareValueTextField.getText().equals("")) {
				errorMessage = rulePanelView.I18N + ".error.comparevalue.empty";
			}
		default:
			break;
		}

		isValid = errorMessage.equals("");
		if (!isValid) {
			ruleErrorLabel.setText(rulePanelView.getMessage(errorMessage));
		}
		ruleErrorLabel.setVisible(!isValid);

		LOGGER.debug("RegelPanel is Valid = " + isValid);

		return isValid;
	}

	/**
	 * {@link ComboBoxActionListener} fuer alle Comboboxen in den Tabs des
	 * Regelpanels
	 *
	 */
	private class ComboBoxActionListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {

			if (e.getSource() instanceof JComboBox) {
				JComboBox comboBox = (JComboBox) e.getSource();

				if (comboBox == contextComboBox) {
					model.setContextType((ContextTypeEnum) contextComboBox.getSelectedItem());
					if (!model.getContextType().equals(ContextTypeEnum.EMPTY)
							&& contextComboBox.getModel().getElementAt(0).equals(ContextTypeEnum.EMPTY)) {
						// nach erster Auswahl "Leer-Element" entfernen
						contextComboBox.removeItemAt(0);
					}
					LOGGER.debug("selected context: " + contextComboBox.getSelectedItem());

				} else if (comboBox == attributecmbox) {
					model.setContextAttribute((ContextAttributeEnum) attributecmbox.getSelectedItem());
					if (!model.getContextAttribute().equals(ContextAttributeEnum.EMPTY)
							&& attributecmbox.getModel().getElementAt(0).equals(ContextAttributeEnum.EMPTY)) {
						// nach erster Auswahl "Leer-Element" entfernen
						attributecmbox.removeItemAt(0);
					}
					LOGGER.debug("selected attribute: " + attributecmbox.getModel().getSelectedItem());
				} else if (comboBox == comparisonModeComboBox) {
					model.setComparisonType((ComparisonTypeEnum) comparisonModeComboBox.getSelectedItem());
					if (!model.getComparisonType().equals(ComparisonTypeEnum.EMPTY)
							&& comparisonModeComboBox.getModel().getElementAt(0).equals(ComparisonTypeEnum.EMPTY)) {
						// nach erster Auswahl "Leer-Element" entfernen
						comparisonModeComboBox.removeItemAt(0);
					}
					LOGGER.debug("comparisonModeComboBox: " + comparisonModeComboBox.getSelectedItem());
				}
			}
		}
	}

	/**
	 *  DocumentListener fuer das JText-Feld Vergleichswert
	 */
	private class RegelDocumentListener implements DocumentListener {

		RegelModel model = null;
		JTextField myTextField = null;

		public RegelDocumentListener(RegelModel model, JTextField myTextField) {
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

			if (myTextField == compareValueTextField) {
				model.setCompareValue(myTextField.getText());
			}
		}
	}

	/**
	 * Dieser ModelChangeListener ist ein PropertyChangeListener des Regel-Models und 
	 * dient dazu die Models und Sichtbarkeiten der abhaengigen Komponenten anzupassen
	 *
	 */
	private class ModelChangeListener implements PropertyChangeListener {

		public void propertyChange(PropertyChangeEvent evt) {

			final String propertyName = evt.getPropertyName();
			if (propertyName.equals("contextType")) {
				contextComboBox.setSelectedItem(evt.getNewValue());

				// Attribut Combobox neu aufbauen
				model.setContextAttribute(ContextAttributeEnum.EMPTY);
				attributecmbox.setModel(getAttributeModel(getModel()));
				attributecmbox.setVisible(attributecmbox.getModel().getSize() != 0);

				model.setRuleName(evt.getNewValue().toString());
			} else if (propertyName.equals("contextAttribute")) {
				attributecmbox.setSelectedItem(evt.getNewValue());

				// Comparison Combobox neu aufbauen
				model.setComparisonType(ComparisonTypeEnum.EMPTY);
				comparisonModeComboBox.setModel(getComparisonModeModel(model.getContextAttribute()));

				visibiltyComponentInCombination((ContextAttributeEnum) evt.getNewValue());
			} else if (propertyName.equals("compareValue")) {
				ContextAttributeEnum attribute = (ContextAttributeEnum) attributecmbox.getSelectedItem();

				if (attribute.getDataType() == DataTypeEnum.DATE) {
					Date compareDate;
					try {
						compareDate = rulePanelView.simpleDateFormat.parse(model.getCompareValue());
					} catch (ParseException e1) {
						throw new IllegalArgumentException("invalid date: " + model.getCompareValue());
					}
					((UtilDateModel) datePanel.getModel()).setValue(compareDate);
				} else {
					compareValueTextField.setText((String) evt.getNewValue());
				}
			}
		}
	}

	/**
	 * Sichtbarkeiten abhaengigkeit vom Regelattribut setzen 
	 * 
	 * @param attribut
	 *            Regelattribut
	 */
	private void visibiltyComponentInCombination(ContextAttributeEnum attribute) {
		if (attribute == ContextAttributeEnum.EMPTY) {
			setCombinationVisible(false, false, false);

		} else {
			if (attribute.getDataType() == DataTypeEnum.DATE) {
				setCombinationVisible(true, true, false);
			} else {
				setCombinationVisible(true, false, true);
			}
		}
	}

	/**
	 * Sichtbarkeiten von Vergleichsart, Vergleichsdatum und Vergleichswert setzen 
	 * 
	 * @param compMode
	 *            Comobox und Label fuer Vergeleichsart sichtbar
	 * @param dateFields
	 *            Textfeld, Picker und Label fuer Vergleichsdatum sichtbar
	 * @param compval
	 *            Textfeld und Label fuer Vergleihswert sichtbar
	 * 
	 */
	private void setCombinationVisible(boolean compMode, boolean dateFields, boolean compval) {

		comparisonModeComboBox.setVisible(compMode);
		comparisonModeLabel.setVisible(compMode);

		datePanel.setVisible(dateFields);
		datePicker.setVisible(dateFields);
		compareDateLabel.setVisible(dateFields);

		compareValueLabel.setVisible(compval);
		compareValueTextField.setVisible(compval);
	}

}