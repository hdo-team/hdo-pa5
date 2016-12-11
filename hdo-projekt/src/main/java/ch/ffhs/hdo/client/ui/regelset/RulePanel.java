package ch.ffhs.hdo.client.ui.regelset;

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
import ch.ffhs.hdo.infrastructure.service.util.FileHandling;
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

public class RulePanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3417369737468476369L;
	private static Logger LOGGER = LogManager.getLogger(FileHandling.class);

	private RegelsetView rulePanelView;
	private JComboBox<ContextTypeEnum> contextComboBox;
	private JComboBox<ContextAttributeEnum> attributeComboBox;
	private JComboBox<ComparisonTypeEnum> comparisonModeComboBox;
	private JTextField compareValueTextField;
	private JDatePickerImpl datePicker;
	private JLabel reiterNameLabel;
	private JDatePanelImpl datePanel;
	private RegelModel model;

	
	DefaultComboBoxModel<ContextAttributeEnum> pdfAttributeModel;
	DefaultComboBoxModel<ContextAttributeEnum> fileAttributeModel;

	RulePanel getRulePanel() {
		return this;
	}
	
	
	
	private DefaultComboBoxModel<ContextAttributeEnum> getAttributeModel(RegelModel regelModel) {
		DefaultComboBoxModel<ContextAttributeEnum> attributeModel = null;

		if (regelModel.getContextType().equals(ContextTypeEnum.CONTEXT_PDF)) {
			attributeModel = pdfAttributeModel;
		} else if (regelModel.getContextType().equals(ContextTypeEnum.CONTEXT_FILE)) {
			attributeModel = fileAttributeModel;
		}

		if (regelModel.getContextAttribute() == null) {

		}
		// attributeModel.setSelectedItem(anObject);

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

	public RulePanel(RegelsetView regelsetView, final RegelModel ruleModel) {
		super();
		rulePanelView = regelsetView;
		this.model= ruleModel;
		
		createComponent();
		setComboboxModel();

		configureBinding();
		setLayout();
	}

	
	private void configureBinding() {
									
		contextComboBox.setSelectedItem(getModel().getContextType());
		attributeComboBox.setSelectedItem(getModel().getContextAttribute());
		comparisonModeComboBox.setSelectedItem(getModel().getComparisonType());

		if (getModel().getContextAttribute().getDataType() == DataTypeEnum.DATE) {
			Date compareDate = null;
			try {
				compareDate = rulePanelView.simpleDateFormat.parse(model.getCompareValue());
			} catch (ParseException e1) {
				LOGGER.error("Datum konnte nicht konveriert werden.", e1);
				throw new IllegalArgumentException("invalid date: " + model.getCompareValue());

			}
			((UtilDateModel) datePanel.getModel()).setValue(compareDate);
		
		
		} else {
			compareValueTextField.setText(model.getCompareValue());
		}
	}

	private void createComponent() {
		contextComboBox = new JComboBox<ContextTypeEnum>(rulePanelView.getContextList());
		attributeComboBox = new JComboBox<ContextAttributeEnum>(rulePanelView.getAttributList(model.getContextType()));
		comparisonModeComboBox = new JComboBox<ComparisonTypeEnum>(
				rulePanelView.getComparisonModeList(model.getContextAttribute()));
		datePanel = new JDatePanelImpl(new UtilDateModel());
		datePicker = new JDatePickerImpl(datePanel, null);
		compareValueTextField = new JTextField();

		datePicker.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				JDatePanelImpl impl = (JDatePanelImpl) e.getSource();
				Date date = (Date) impl.getModel().getValue();
				model.setCompareValue(RulePanel.this.rulePanelView.simpleDateFormat.format(date));
			}
		});

		ActionListener al = new ComboBoxActionListener();
		contextComboBox.addActionListener(al);
		attributeComboBox.addActionListener(al);
		comparisonModeComboBox.addActionListener(al);
		compareValueTextField.getDocument().addDocumentListener(new RegelsetDocumentListener(compareValueTextField));

		model.addPropertyChangeListener(new MyPropertyChangeListener());
	}

	private FormBuilder setLayout() {
		FormBuilder paneBuilder = FormBuilder.create()
				.columns(
						"right:pref, 5dlu, [20dlu, pref], 5dlu, [20dlu, pref], 5dlu, [20dlu, pref], 5dlu, [20dlu, pref], 5dlu, [20dlu, pref], 5dlu, [20dlu, pref], 5dlu, [20dlu, pref]")
				.rows("p, $lg, p, $lg, p, $lg, p, $lg, p , $lg, p, $lg, p, $lg, p");

		// TODO: Titel von TAB  reiterNameLabel = new JLabel();

		paneBuilder.add(rulePanelView.getMessage(rulePanelView.I18N + ".label.sortrule")).rcw(1, 1, 7);
		paneBuilder.add(contextComboBox).rcw(3, 1, 1);
		paneBuilder.add(attributeComboBox).rcw(3, 4, 4);
		paneBuilder.add(comparisonModeComboBox).rcw(11, 1, 2);
		paneBuilder.addLabel(rulePanelView.getMessage(rulePanelView.I18N + ".label.rule.dynamic")).rcw(7, 1, 7);
		paneBuilder.add(datePicker).rcw(11, 4, 6);
		paneBuilder.add(compareValueTextField).rcw(15, 1, 9);

		paneBuilder.padding(new EmptyBorder(5, 5, 5, 5));

		
		
		if (getModel().getContextAttribute().getDataType() == DataTypeEnum.DATE) {
			datePanel.setVisible(true);
			datePicker.setVisible(true);
			compareValueTextField.setVisible(false);
		}else{
			datePanel.setVisible(false);
			datePicker.setVisible(false);
			compareValueTextField.setVisible(true);
			
		}

		

		this.add(paneBuilder.build());

		return paneBuilder;
	}

	private void setComboboxModel() {
		pdfAttributeModel = new DefaultComboBoxModel<ContextAttributeEnum>(
				rulePanelView.getAttributList(ContextTypeEnum.CONTEXT_PDF));
		fileAttributeModel = new DefaultComboBoxModel<ContextAttributeEnum>(
				rulePanelView.getAttributList(ContextTypeEnum.CONTEXT_FILE));
	}

	private class ComboBoxActionListenerALT implements ActionListener {

		public void actionPerformed(ActionEvent e) {

			if (e.getSource() instanceof JComboBox) {
				JComboBox comboBox = (JComboBox) e.getSource();

				if (comboBox == contextComboBox) {
					if (contextComboBox.getModel().getElementAt(0).equals(ContextTypeEnum.EMPTY)) { 
						// -TODO
						//contextComboBox.removeItemAt(0);
					}
					LOGGER.debug("contextComboBox: " + contextComboBox.getModel().getSelectedItem());

					model.setContextType((ContextTypeEnum) contextComboBox.getSelectedItem());

					// abhängiges AttributeContext neu aufbauen
System.out.println("attributeComboBox:_ " + attributeComboBox); 
System.out.println("model: " + model);
System.out.println("model-ctx: " + getModel().getContextType());
System.out.println("model-attr: " + getModel().getContextAttribute());
System.out.println("model-comp: " + getModel().getCompareValue());
					attributeComboBox.setModel(getAttributeModel(getModel()));
					model.setContextAttribute(ContextAttributeEnum.EMPTY);

					attributeComboBox.setVisible(attributeComboBox.getModel().getSize() != 0);
				} else if (comboBox == attributeComboBox) {
					System.out.println("attributeComboBox: "
							+ ((ContextAttributeEnum) attributeComboBox.getModel().getSelectedItem()).name());
					model.setContextAttribute((ContextAttributeEnum) attributeComboBox.getModel().getSelectedItem());

					// abhängiges AttributeContext neu aufbauen
					comparisonModeComboBox.setModel(getComparisonModeModel(model.getContextAttribute()));

					// default-wERt ins Model sonst NP-Ex
					model.setComparisonType(ComparisonTypeEnum.COMPARISON_EQUAL);

				} else if (comboBox == comparisonModeComboBox) {
					System.out
							.println("comparisonModeComboBox: " + comparisonModeComboBox.getModel().getSelectedItem());
					model.setComparisonType((ComparisonTypeEnum) comparisonModeComboBox.getModel().getSelectedItem());
				}
			}
		}
	}

	
	private class ComboBoxActionListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {

			if (e.getSource() instanceof JComboBox) {
				JComboBox comboBox = (JComboBox) e.getSource();

				if (comboBox == contextComboBox) {
					if (contextComboBox.getModel().getElementAt(0).equals(ContextTypeEnum.EMPTY)) { 
						// -TODO
						//contextComboBox.removeItemAt(0);
					}
					LOGGER.debug("contextComboBox: " + contextComboBox.getModel().getSelectedItem());

					model.setContextType((ContextTypeEnum) contextComboBox.getSelectedItem());
				} else if (comboBox == attributeComboBox) {
					System.out.println("attributeComboBox: "
							+ ((ContextAttributeEnum) attributeComboBox.getModel().getSelectedItem()).name());
					model.setContextAttribute((ContextAttributeEnum) attributeComboBox.getModel().getSelectedItem());
				} else if (comboBox == comparisonModeComboBox) {
					System.out
							.println("comparisonModeComboBox: " + comparisonModeComboBox.getModel().getSelectedItem());
					model.setComparisonType((ComparisonTypeEnum) comparisonModeComboBox.getModel().getSelectedItem());
				}
			}
		}
	}

	
	
	private class RegelsetDocumentListener implements DocumentListener {

		// TODO gleicher DocumentListener für RegelsetView + RulePanel
		// in separater Klasse möglich ?
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

			if (myTextField == compareValueTextField) {
				model.setCompareValue(myTextField.getText());
			}
		}
	}

	private class MyPropertyChangeListener implements PropertyChangeListener {

		// TODO: abhängige Comboboxen hier cheken und aufbauen?
		// oder im ActionListener?

		public void propertyChange(PropertyChangeEvent evt) {
			if (evt.getPropertyName().equals("compareValue")) {
				System.out.println("Property: compareValue");
				// compareTextField
			} else if (evt.getPropertyName().equals("comparisonType")) {
				comparisonModeComboBox.setSelectedItem(evt.getNewValue());
			} else if (evt.getPropertyName().equals("contextType")) {
				contextComboBox.setSelectedItem(evt.getNewValue());
				if (! evt.getNewValue().equals(evt.getOldValue())) {
					model.setContextAttribute(ContextAttributeEnum.EMPTY);
					attributeComboBox.setModel(getAttributeModel(getModel()));
					attributeComboBox.setVisible(attributeComboBox.getModel().getSize() != 0);
					
					model.setRuleName(evt.getNewValue().toString());
				}
			} else if (evt.getPropertyName().equals("contextAttribute")) {
				attributeComboBox.setSelectedItem(evt.getNewValue());
				if (! evt.getNewValue().equals(evt.getOldValue())) {
					// => neu Comparison Combo aufbauen
					model.setComparisonType(ComparisonTypeEnum.EMPTY);
					comparisonModeComboBox.setModel(getComparisonModeModel(model.getContextAttribute()));
				}
			
				
				
				//
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
				// TODO: Tab-Titel anpassen reiterNameLabel.setText((String) evt.getNewValue());
			} else if (evt.getPropertyName().equals("compareValue")) {
				ContextAttributeEnum attribute = (ContextAttributeEnum) attributeComboBox.getSelectedItem();

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