package ch.ffhs.hdo.client.ui.regelset;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.Date;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import com.jgoodies.forms.builder.FormBuilder;

import ch.ffhs.hdo.domain.regel.ComparisonTypeEnum;
import ch.ffhs.hdo.domain.regel.ContextAttributeEnum;
import ch.ffhs.hdo.domain.regel.ContextTypeEnum;
import ch.ffhs.hdo.domain.regel.DataTypeEnum;
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

public class RulePanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3417369737468476369L;

	private RegelsetView rulePanelView;
	private JComboBox<ContextTypeEnum> contextComboBox;
	private JComboBox<ContextAttributeEnum> attributeComboBox;
	private JComboBox<ComparisonTypeEnum> comparisonModeComboBox;
	private JTextField compareValueTextField;

	DefaultComboBoxModel<ContextAttributeEnum> pdfAttributeModel;
	DefaultComboBoxModel<ContextAttributeEnum> fileAttributeModel;

	DefaultComboBoxModel<ContextAttributeEnum> getAttributeModel(RegelModel regelModel) {
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

	DefaultComboBoxModel getComparisonModeModel(ContextAttributeEnum attributeEnum) {

		// Es wird immer ein neues Model gemacht... TODO: besser das Model NUR
		// anpasssen?
		DefaultComboBoxModel attributeModel = new DefaultComboBoxModel(
				rulePanelView.getComparisonModeList(attributeEnum));

		return attributeModel;
	}

	RegelModel ruleModel = null;

	public RulePanel(RegelsetView regelsetView, final RegelModel ruleModel) {
		super();
		rulePanelView = regelsetView;
		this.ruleModel = ruleModel;

		
		setComboboxModel();
		
		
		
		FormBuilder paneBuilder = FormBuilder.create()
				.columns(
						"right:pref, 5dlu, [20dlu, pref], 5dlu, [20dlu, pref], 5dlu, [20dlu, pref], 5dlu, [20dlu, pref], 5dlu, [20dlu, pref], 5dlu, [20dlu, pref], 5dlu, [20dlu, pref]")
				.rows("p, $lg, p, $lg, p, $lg, p, $lg, p , $lg, p, $lg, p, $lg, p");

		final JLabel ruleNameLabel = new JLabel(rulePanelView.getMessage(rulePanelView.I18N + ".label.sortrule"));
		paneBuilder.add(ruleNameLabel).rcw(1, 1, 7);

		contextComboBox = new JComboBox<ContextTypeEnum>(
				rulePanelView.getContextList(ruleModel.getContextType() == ContextTypeEnum.EMPTY));
		contextComboBox.setSelectedItem(ruleModel.getContextType());
		paneBuilder.add(contextComboBox).rcw(3, 1, 1);

		attributeComboBox = new JComboBox<ContextAttributeEnum>(
				rulePanelView.getAttributList(ruleModel.getContextType()));
		attributeComboBox.setSelectedItem(ruleModel.getContextAttribute());

		paneBuilder.add(attributeComboBox).rcw(3, 4, 4);

		comparisonModeComboBox = new JComboBox<ComparisonTypeEnum>(
				rulePanelView.getComparisonModeList(ruleModel.getContextAttribute()));
		comparisonModeComboBox.setSelectedItem(ruleModel.getComparisonType());

		paneBuilder.add(comparisonModeComboBox).rcw(11, 1, 2);

		paneBuilder.addLabel(rulePanelView.getMessage(rulePanelView.I18N + ".label.rule.dynamic")).rcw(7, 1, 7);
		paneBuilder.addLabel(rulePanelView.getMessage(rulePanelView.I18N + ".label.rule.from")).rcw(9, 1, 7);
		paneBuilder.addLabel(rulePanelView.getMessage(rulePanelView.I18N + ".label.rule.to")).rcw(9, 4, 2);

		final UtilDateModel modelDate = new UtilDateModel();
		// TODO: Date compareDate = null;
		//
		Date compareDate = null;// TDOO: default <heute> Nok //TDOO: default
								// <heute> Nok
		try {
			compareDate = rulePanelView.simpleDateFormat.parse(ruleModel.getCompareValue());
		} catch (ParseException e1) {
			// TODO: throw new IllegalArgumentException("invalid date: " +
			// compareDate);
		} catch (NullPointerException npEx) {
			// TODO: wieder Entfernen, und abfragen auf DATE
		}
		modelDate.setValue(compareDate);
		final JDatePanelImpl datePanel = new JDatePanelImpl(modelDate);
		final JDatePickerImpl datePicker = new JDatePickerImpl(datePanel, null);
		paneBuilder.add(datePicker).rcw(11, 4, 6);

		compareValueTextField = new JTextField();
		paneBuilder.add(compareValueTextField).rcw(15, 1, 9);

		paneBuilder.padding(new EmptyBorder(5, 5, 5, 5));
		datePicker.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				JDatePanelImpl impl = (JDatePanelImpl) e.getSource();
				Date date = (Date) impl.getModel().getValue();
				ruleModel.setCompareValue(RulePanel.this.rulePanelView.simpleDateFormat.format(date));
			}
		});
		
		
		ActionListener al = new ComboBoxActionListener();
		contextComboBox.addActionListener(al);
		attributeComboBox.addActionListener(al);
		comparisonModeComboBox.addActionListener(al);
		compareValueTextField.getDocument().addDocumentListener(new RegelsetDocumentListener(compareValueTextField));

		ruleModel.addPropertyChangeListener(new PropertyChangeListener() {

			// TODO: abhängige Comboboxen hier cheken und aufbauen?
			//		 oder im ActionListener?
			
			public void propertyChange(PropertyChangeEvent evt) {
				if (evt.getPropertyName().equals("compareValueMap")) {   // TODO sollte doch anderer -Nanme sein ==> kein MAp..
					System.out.println("Property: compareValueMap"); 
					//compareTextField
				} else if (evt.getPropertyName().equals("comparisonType")) {
					comparisonModeComboBox.setSelectedItem(evt.getNewValue());
				} else if (evt.getPropertyName().equals("contextType")) {
					contextComboBox.setSelectedItem(evt.getNewValue());
				} else if (evt.getPropertyName().equals("contextAttribute")) {
					attributeComboBox.setSelectedItem(evt.getNewValue());
					// 
					// je nach Attribute Datum-Picker oder compareValueField
					ContextAttributeEnum attribute = (ContextAttributeEnum)evt.getNewValue(); 
					if (attribute == ContextAttributeEnum.EMPTY)  {
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
					ruleNameLabel.setText((String)evt.getNewValue());
				} else if (evt.getPropertyName().equals("compareValue")) {
					ContextAttributeEnum attribute = (ContextAttributeEnum)attributeComboBox.getSelectedItem();
					
					if (attribute.getDataType() == DataTypeEnum.DATE) {
						Date compareDate;
						try {
							compareDate = rulePanelView.simpleDateFormat.parse(ruleModel.getCompareValue());
						} catch (ParseException e1) {
							throw new IllegalArgumentException("invalid date: " + ruleModel.getCompareValue());
						}
						modelDate.setValue(compareDate);
					} else {
						compareValueTextField.setText((String)evt.getNewValue());
					}

				}
			}
		});
	
		/******************
		 * 
		 * 

	private JComboBox<ContextTypeEnum> ;
	private JComboBox<ContextAttributeEnum> ;
	private JComboBox<ComparisonTypeEnum> ;
	private JTextField ;
		 */
		
		this.add(paneBuilder.build());
	}

	private void setComboboxModel() {
		pdfAttributeModel = new DefaultComboBoxModel<ContextAttributeEnum>(
				rulePanelView.getAttributList(ContextTypeEnum.CONTEXT_PDF));
		fileAttributeModel = new DefaultComboBoxModel<ContextAttributeEnum>(
				rulePanelView.getAttributList(ContextTypeEnum.CONTEXT_FILE));
	}
	
	private class ComboBoxActionListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {

			int tabIndex = RulePanel.this.rulePanelView.tabbedPane.getSelectedIndex();
			RegelModel ruleModel = RulePanel.this.rulePanelView.getModel().getRuleModelList().get(tabIndex);
			if (e.getSource() instanceof JComboBox) {
				JComboBox comboBox = (JComboBox) e.getSource();
				int selectedIndex = comboBox.getSelectedIndex();

				if (comboBox == contextComboBox) {
					if (contextComboBox.getModel().getElementAt(0).equals(ContextTypeEnum.EMPTY)) { // ContextTypeEnum.EMPTY))
																									// {
						contextComboBox.removeItemAt(0);
					}
					System.out.println("contextComboBox: " + contextComboBox.getModel().getSelectedItem());
					ruleModel.setContextType((ContextTypeEnum) contextComboBox.getSelectedItem());
					// abhängiges AttributeContext neu aufbauen
					attributeComboBox.setModel(getAttributeModel(ruleModel)); // .getContextType()));

					attributeComboBox.setVisible(attributeComboBox.getModel().getSize() != 0);
				} else if (comboBox == attributeComboBox) {
					System.out.println("attributeComboBox: "
							+ ((ContextAttributeEnum) attributeComboBox.getModel().getSelectedItem()).name());
					ruleModel
							.setContextAttribute((ContextAttributeEnum) attributeComboBox.getModel().getSelectedItem());

					// abhängiges AttributeContext neu aufbauen
					comparisonModeComboBox.setModel(getComparisonModeModel(ruleModel.getContextAttribute()));

					// default-wERt ins Model sonst NP-Ex
					ruleModel.setComparisonType(ComparisonTypeEnum.COMPARISON_EQUAL);

				} else if (comboBox == comparisonModeComboBox) {
					System.out
							.println("comparisonModeComboBox: " + comparisonModeComboBox.getModel().getSelectedItem());
					ruleModel.setComparisonType(
							(ComparisonTypeEnum) comparisonModeComboBox.getModel().getSelectedItem());
				}
			}
		}
	}
	
	private class RegelsetDocumentListener implements DocumentListener {
		
		// TODO gleicher DocumentListener für RegelsetView + RulePanel
		//      in separater Klasse möglich ?
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
				ruleModel.setCompareValue(myTextField.getText());
			}
		}
	}
}