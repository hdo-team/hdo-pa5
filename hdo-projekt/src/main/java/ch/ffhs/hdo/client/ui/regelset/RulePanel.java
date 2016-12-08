package ch.ffhs.hdo.client.ui.regelset;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.Date;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.jgoodies.forms.builder.FormBuilder;

import ch.ffhs.hdo.client.ui.regelset.RegelModel.ComparisonTypeEnum;
import ch.ffhs.hdo.client.ui.regelset.RegelModel.ContextAttributeEnum;
import ch.ffhs.hdo.client.ui.regelset.RegelModel.ContextTypeEnum;
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
	private JTextField compareTextField;

	DefaultComboBoxModel<ContextAttributeEnum> pdfAttributeModel;
	DefaultComboBoxModel<ContextAttributeEnum> fileAttributeModel;
	DefaultComboBoxModel<ContextAttributeEnum> contentAttributeModel;

	DefaultComboBoxModel<RegelModel.ContextAttributeEnum> getAttributeModel(RegelModel regelModel) {
		DefaultComboBoxModel<RegelModel.ContextAttributeEnum> attributeModel = null;

		if (regelModel.getContextType().equals(ContextTypeEnum.CONTEXT_PDF)) {
			attributeModel = pdfAttributeModel;
		} else if (regelModel.getContextType().equals(ContextTypeEnum.CONTEXT_FILE)) {
			attributeModel = fileAttributeModel;
		} else if (regelModel.getContextType().equals(ContextTypeEnum.CONTEXT_CONTENT)) {
			attributeModel = contentAttributeModel;
		} // else if (regelModel.getContextType().equals(ContextTypeEnum.EMPTY))
			// {
			// attributeModel = contentAttributeModel; // TODO: sollte
			// eigentlich nicht sein
			// }//
		if (regelModel.getContextAttribute() == null) {

		}
		// attributeModel.setSelectedItem(anObject);

		return attributeModel;
	}

	DefaultComboBoxModel getComparisonModeModel(RegelModel.ContextAttributeEnum attributeEnum) {

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

		paneBuilder.addLabel(rulePanelView.getMessage(rulePanelView.I18N + ".label.sortrule")).rcw(1, 1, 7);

		contextComboBox = new JComboBox<ContextTypeEnum>(
				rulePanelView.getContextList(ruleModel.getContextType() == null));
		// contextComboBox.setSelectedIndex(ruleModel.getContextType() == null ?
		// 0 : ruleModel.getContextType().ordinal() - 1);
		paneBuilder.add(contextComboBox).rcw(3, 1, 1);

		attributeComboBox = new JComboBox<ContextAttributeEnum>(
				rulePanelView.getAttributList(ruleModel.getContextType()));
		// attributeComboBox.setSelectedIndex(ruleModel.getContextAttribute() ==
		// null ? 0 : ruleModel.getContextAttribute().ordinal() - 1);
		paneBuilder.add(attributeComboBox).rcw(3, 4, 4);

		comparisonModeComboBox = new JComboBox<ComparisonTypeEnum>(
				rulePanelView.getComparisonModeList(ruleModel.getContextAttribute()));
		// comparisonModeComboBox.setSelectedIndex(ruleModel.getContextAttribute()
		// == null ? 0 : ruleModel.getContextAttribute().ordinal() - 1);
		// default-wERt ins Model sonst NP-Ex
		ruleModel.setComparisonType(RegelModel.ComparisonTypeEnum.COMPARISON_EQUAL);
		paneBuilder.add(comparisonModeComboBox).rcw(11, 1, 2);

		paneBuilder.addLabel(rulePanelView.getMessage(rulePanelView.I18N + ".label.rule.dynamic")).rcw(7, 1, 7);
		paneBuilder.addLabel(rulePanelView.getMessage(rulePanelView.I18N + ".label.rule.from")).rcw(9, 1, 7);
		paneBuilder.addLabel(rulePanelView.getMessage(rulePanelView.I18N + ".label.rule.to")).rcw(9, 4, 2);

		JDatePickerImpl datePicker;
		UtilDateModel modelDate = new UtilDateModel();
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
		JDatePanelImpl datePanel = new JDatePanelImpl(modelDate);
		datePicker = new JDatePickerImpl(datePanel, null);
		paneBuilder.add(datePicker).rcw(11, 4, 6);

		compareTextField = new JTextField();
		paneBuilder.add(compareTextField).rcw(15, 1, 9);

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

		this.add(paneBuilder.build());
	}

	private void setComboboxModel() {
		pdfAttributeModel = new DefaultComboBoxModel<ContextAttributeEnum>(
				rulePanelView.getAttributList(ContextTypeEnum.CONTEXT_PDF));
		fileAttributeModel = new DefaultComboBoxModel<ContextAttributeEnum>(
				rulePanelView.getAttributList(ContextTypeEnum.CONTEXT_FILE));
		contentAttributeModel = new DefaultComboBoxModel<ContextAttributeEnum>(
				rulePanelView.getAttributList(ContextTypeEnum.CONTEXT_CONTENT));
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
					ruleModel.setComparisonType(RegelModel.ComparisonTypeEnum.COMPARISON_EQUAL);

				} else if (comboBox == comparisonModeComboBox) {
					System.out
							.println("comparisonModeComboBox: " + comparisonModeComboBox.getModel().getSelectedItem());
					ruleModel.setComparisonType(
							(ComparisonTypeEnum) comparisonModeComboBox.getModel().getSelectedItem());
				}
			}
		}
	}
}