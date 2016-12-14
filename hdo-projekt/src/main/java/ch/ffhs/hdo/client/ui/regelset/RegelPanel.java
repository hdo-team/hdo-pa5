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

public class RegelPanel extends JPanel {
	/**
	 * 
	 */
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

	DefaultComboBoxModel<ContextAttributeEnum> pdfAttributeModel;
	DefaultComboBoxModel<ContextAttributeEnum> fileAttributeModel;

	RegelPanel getRulePanel() {
		return this;
	}

	private DefaultComboBoxModel<ContextAttributeEnum> getAttributeModel(RegelModel regelModel) {
		DefaultComboBoxModel<ContextAttributeEnum> attributeModel = null;

		if (regelModel.getContextType().equals(ContextTypeEnum.CONTEXT_PDF)) {
			attributeModel = pdfAttributeModel;
		} else if (regelModel.getContextType().equals(ContextTypeEnum.CONTEXT_FILE)) {
			attributeModel = fileAttributeModel;
		}

		return attributeModel;
	}

	private DefaultComboBoxModel getComparisonModeModel(ContextAttributeEnum attributeEnum) {

		// Es wird immer ein neues Model gemacht... TODO: besser das Model NUR
		// anpasssen?
		DefaultComboBoxModel attributeModel = new DefaultComboBoxModel(
				rulePanelView.getComparisonModeList(attributeEnum));

		return attributeModel;
	}

	public RegelModel getModel() {
		return model;
	}

	public RegelPanel(RegelsetView regelsetView, final RegelModel ruleModel) {
		super();
		rulePanelView = regelsetView;
		this.model = ruleModel;

		createComponent();
		setComboboxModel();

		configureBinding();
		setLayout();
	}

	private void configureBinding() {

		contextComboBox.setSelectedItem(getModel().getContextType());
		attributecmbox.setSelectedItem(getModel().getContextAttribute());
		comparisonModeComboBox.setSelectedItem(getModel().getComparisonType());

		if (getModel().getContextAttribute().getDataType() == DataTypeEnum.DATE) {
			Date compareDate = null;
			try {
				compareDate = rulePanelView.simpleDateFormat.parse(model.getCompareValue());
			} catch (ParseException e1) {
				// impossible Error
				LOGGER.error("FATAL: Datum " + model.getCompareValue() + " konnte nicht konveriert werden.", e1);
				// throw new IllegalArgumentException("invalid date: " +
				// model.getCompareValue());

			}
			((UtilDateModel) datePanel.getModel()).setValue(compareDate);

		} else {
			compareValueTextField.setText(model.getCompareValue());
		}

		model.addPropertyChangeListener(new MyPropertyChangeListener());
	}

	private void createComponent() {

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

	private void setLayout() {
		FormBuilder paneBuilder = FormBuilder.create()
				.columns(
						"right:pref, 5dlu, [20dlu, pref], 5dlu, [20dlu, pref], 5dlu, [20dlu, pref], 5dlu, [20dlu, pref], 5dlu, [20dlu, pref], 5dlu, [20dlu, pref], 5dlu, [20dlu, pref]")
				.rows("p, $lg, p, $lg, p, $lg, p, $lg, p, $lg, p , $lg, p, $lg, p, $lg, p");

		paneBuilder.add(ruleErrorLabel).rcw(1, 1, 9);
		;
		paneBuilder.add(rulePanelView.getMessage(rulePanelView.I18N + ".label.rulecontext")).rcw(3, 1, 7);
		paneBuilder.add(rulePanelView.getMessage(rulePanelView.I18N + ".label.ruleattribute")).rcw(3, 4, 4);
		paneBuilder.add(contextComboBox).rcw(5, 1, 1);
		paneBuilder.add(attributecmbox).rcw(5, 4, 4);
		paneBuilder.add(comparisonModeComboBox).rcw(13, 1, 2);
		paneBuilder.addLabel(rulePanelView.getMessage(rulePanelView.I18N + ".label.comparemode")).rcw(9, 1, 7);
		paneBuilder.add(datePicker).rcw(13, 4, 6);
		paneBuilder.add(rulePanelView.getMessage(rulePanelView.I18N + ".label.comparevalue")).rcw(15, 1, 9);
		paneBuilder.add(compareValueTextField).rcw(17, 1, 9);

		paneBuilder.padding(new EmptyBorder(5, 5, 5, 5));

		if (getModel().getContextAttribute().getDataType() == DataTypeEnum.DATE) {
			datePanel.setVisible(true);
			datePicker.setVisible(true);
			compareValueTextField.setVisible(false);
		} else {
			datePanel.setVisible(false);
			datePicker.setVisible(false);
			compareValueTextField.setVisible(true);

		}

		this.add(paneBuilder.build());
	}

	private void setComboboxModel() {
		pdfAttributeModel = new DefaultComboBoxModel<ContextAttributeEnum>(
				rulePanelView.getAttributList(ContextTypeEnum.CONTEXT_PDF));
		fileAttributeModel = new DefaultComboBoxModel<ContextAttributeEnum>(
				rulePanelView.getAttributList(ContextTypeEnum.CONTEXT_FILE));
	}

	protected boolean isPanelValid() {
		// is the RulePanel valid?

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

		System.out.println("RegelPanel is Valid = " + isValid);

		return isValid;
	}

	private class ComboBoxActionListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {

			if (e.getSource() instanceof JComboBox) {
				JComboBox comboBox = (JComboBox) e.getSource();

				if (comboBox == contextComboBox) {
					model.setContextType((ContextTypeEnum) contextComboBox.getSelectedItem());
					if (!model.getContextType().equals(ContextTypeEnum.EMPTY)
							&& contextComboBox.getModel().getElementAt(0).equals(ContextTypeEnum.EMPTY)) {
						// once a context is selected, remove the empty-item
						contextComboBox.removeItemAt(0);
					}
					LOGGER.debug("selected context: " + contextComboBox.getSelectedItem());

				} else if (comboBox == attributecmbox) {
					model.setContextAttribute((ContextAttributeEnum) attributecmbox.getSelectedItem());
					if (!model.getContextAttribute().equals(ContextAttributeEnum.EMPTY)
							&& attributecmbox.getModel().getElementAt(0).equals(ContextAttributeEnum.EMPTY)) {
						// once an attribute is selected, remove the empty-item
						attributecmbox.removeItemAt(0);
					}
					LOGGER.debug("selected attribute: " + attributecmbox.getModel().getSelectedItem());
				} else if (comboBox == comparisonModeComboBox) {
					model.setComparisonType((ComparisonTypeEnum) comparisonModeComboBox.getSelectedItem());
					if (!model.getComparisonType().equals(ComparisonTypeEnum.EMPTY)
							&& comparisonModeComboBox.getModel().getElementAt(0).equals(ComparisonTypeEnum.EMPTY)) {
						// once a comparemode is selected, remove the empty-item
						comparisonModeComboBox.removeItemAt(0);
					}
					LOGGER.debug("comparisonModeComboBox: " + comparisonModeComboBox.getSelectedItem());
				}
			}
		}
	}

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

	private class MyPropertyChangeListener implements PropertyChangeListener {

		public void propertyChange(PropertyChangeEvent evt) {
			if (evt.getPropertyName().equals("compareValue")) {
				System.out.println("Property: compareValue");
				// compareTextField
			} else if (evt.getPropertyName().equals("comparisonType")) {
				comparisonModeComboBox.setSelectedItem(evt.getNewValue());
			} else if (evt.getPropertyName().equals("contextType")) {
				contextComboBox.setSelectedItem(evt.getNewValue());
				if (!evt.getNewValue().equals(evt.getOldValue())) {
					model.setContextAttribute(ContextAttributeEnum.EMPTY);
					attributecmbox.setModel(getAttributeModel(getModel()));
					attributecmbox.setVisible(attributecmbox.getModel().getSize() != 0);

					model.setRuleName(evt.getNewValue().toString());
				}
			} else if (evt.getPropertyName().equals("contextAttribute")) {
				attributecmbox.setSelectedItem(evt.getNewValue());
				if (!evt.getNewValue().equals(evt.getOldValue())) {
					// => neu Comparison Combo aufbauen
					model.setComparisonType(ComparisonTypeEnum.EMPTY);
					comparisonModeComboBox.setModel(getComparisonModeModel(model.getContextAttribute()));
				}

				// je nach Attribute Datum-Picker oder compareValueField
				ContextAttributeEnum attribute = (ContextAttributeEnum) evt.getNewValue();
				if (attribute == ContextAttributeEnum.EMPTY) {
					comparisonModeComboBox.setVisible(false);

					datePanel.setVisible(false);
					datePicker.setVisible(false);

					compareValueTextField.setVisible(false);
				} else {
					comparisonModeComboBox.setVisible(true);
					if (attribute.getDataType() == DataTypeEnum.DATE) {
						datePanel.setVisible(true);
						datePicker.setVisible(true);
						compareValueTextField.setVisible(false);
					} else {
						datePanel.setVisible(false);
						datePicker.setVisible(false);
						compareValueTextField.setVisible(true);
					}
				}
			} else if (evt.getPropertyName().equals("id")) {
				// TODO: nicht auf View! Code entfernen?
			} else if (evt.getPropertyName().equals("ruleName")) {
				// TODO: Tab-Titel anpassen
				// oder fix => kein check
			} else if (evt.getPropertyName().equals("compareValue")) {
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
}