package ch.ffhs.hdo.client.ui.einstellungen;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.jgoodies.forms.builder.FormBuilder;

import ch.ffhs.hdo.client.ui.base.View;
import ch.ffhs.hdo.client.ui.base.executable.CloseViewOperation;
import ch.ffhs.hdo.client.ui.base.executable.DefaultClosingViewExecutable;
import ch.ffhs.hdo.client.ui.base.viewhandler.ViewHandler;

public class OptionView extends View<OptionModel> {

	private final String I18N = "hdo.option";
	private final String TITLE_KEY = I18N + ".title";
	private final String COMBOBOXKEY = I18N + ".intervall.value.min";
	private JTextField inboxPathTextField;

	private JComboBox<String> intervallComboBox;
	private JCheckBox autoModusCheckBox;
	private JButton saveButton;
	private JButton cancelButton;

	public OptionView(ResourceBundle resourceBundle) {
		super(resourceBundle);
		setTitle(getMessage(TITLE_KEY));

		initComponents();

	}

	private void initComponents() {
		createComponents();
		layoutForm();
		configureBindings();
	}

	private void createComponents() {

		inboxPathTextField = new JTextField();

		String comboBoxListe[] = { getMessage(COMBOBOXKEY + ".60"), getMessage(COMBOBOXKEY + ".30"),
				getMessage(COMBOBOXKEY + ".15"), getMessage(COMBOBOXKEY + ".5") };

		intervallComboBox = new JComboBox<String>(comboBoxListe);
		autoModusCheckBox = new JCheckBox();
		saveButton = new JButton(getMessage("base.save"));
		cancelButton = new JButton(getMessage("base.cancel"));

		cancelButton.addActionListener(new CloseAction());
		saveButton.addActionListener(new SaveAction());

	}

	private void layoutForm() {

		FormBuilder builder = FormBuilder.create()
				.columns("right:pref, 5dlu,[20dlu, pref],5dlu,[20dlu, pref],5dlu, [20dlu, pref]")
				.rows("p, $lg, p, $lg, p, $lg, p, $lg, p , $lg, p , $lg, p");

		builder.addSeparator(getMessage(I18N + ".separator.inboxPath")).rcw(1, 1, 7);
		builder.addLabel(getMessage(I18N + ".label.inboxPath")).rc(3, 1);
		builder.add(inboxPathTextField).rcw(3, 3, 3);

		builder.addSeparator(getMessage(I18N + ".label.scanner")).rcw(5, 1, 7);
		builder.addLabel(getMessage(I18N + ".label.automodus")).rc(7, 1);
		builder.add(autoModusCheckBox).rcw(7, 3, 2);

		builder.addLabel(getMessage(I18N + ".label.intervall")).rc(9, 1);
		builder.add(intervallComboBox).rcw(9, 3, 5);

		builder.add(saveButton).rcw(11, 3, 1);
		builder.add(cancelButton).rcw(11, 5, 1);

		builder.padding(new EmptyBorder(5, 5, 5, 5));
		JPanel build = builder.build();

		getFrame().add(build, BorderLayout.CENTER);

		setDimension(400, 400);
	}

	private void configureBindings() {

	}

	

	private class SaveAction extends AbstractAction {

		public void actionPerformed(ActionEvent e) {

			// Saving Operation to implemento to save settings into Database.

		}

	}

	private class CloseAction extends AbstractAction {

		public void actionPerformed(ActionEvent e) {

			getHander().performOperation(CloseViewOperation.class);

		}

	}

}
