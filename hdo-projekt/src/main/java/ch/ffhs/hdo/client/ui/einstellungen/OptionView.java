package ch.ffhs.hdo.client.ui.einstellungen;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.HierarchyBoundsListener;
import java.awt.event.HierarchyEvent;
import java.awt.image.DataBufferInt;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import com.jgoodies.forms.builder.FormBuilder;

import ch.ffhs.hdo.client.ui.base.View;
import ch.ffhs.hdo.client.ui.base.executable.CloseViewOperation;
import ch.ffhs.hdo.client.ui.einstellungen.executable.OptionsSaveOperation;

public class OptionView extends View<OptionModel> {

	private final String I18N = "hdo.option";
	private final String TITLE_KEY = I18N + ".title";
	private final String COMBOBOXKEY = I18N + ".intervall.value.min";
	private JTextField inboxPathTextField;

	private JComboBox<String> intervallComboBox;
	private JCheckBox autoModusCheckBox;
	private JButton saveButton;
	private JButton cancelButton;
	private HashMap<String, Integer> comboBoxListe = new HashMap<String, Integer>();

	public OptionView(ResourceBundle resourceBundle) {
		super(resourceBundle);
		setTitle(getMessage(TITLE_KEY));

		initComponents();

	}

	private void initComponents() {
		createComponents();
		layoutForm();
	}

	private void createComponents() {

		inboxPathTextField = new JTextField();

		comboBoxListe.put(getMessage(COMBOBOXKEY + ".60"), 3600);
		comboBoxListe.put(getMessage(COMBOBOXKEY + ".30"), 1800);
		comboBoxListe.put(getMessage(COMBOBOXKEY + ".15"), 900);
		comboBoxListe.put(getMessage(COMBOBOXKEY + ".5"), 300);

		intervallComboBox = new JComboBox<String>(comboBoxListe.keySet().toArray(new String[0]));
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

	@Override
	public void configureBindings() {

		inboxPathTextField.setText(getModel().getInboxPath());
		String resourcebundlekey = COMBOBOXKEY + "." + (getModel().getIntervall() / 60);
		intervallComboBox.setSelectedItem(getMessage(resourcebundlekey));
		autoModusCheckBox.setSelected(getModel().isAutoModus());

		inboxPathTextField.getDocument().addDocumentListener(new DocumentListener() {

			public void changedUpdate(DocumentEvent e) {
				getModel().setInboxPath(inboxPathTextField.getText());
			}

			public void insertUpdate(DocumentEvent e) {
				getModel().setInboxPath(inboxPathTextField.getText());

			}

			public void removeUpdate(DocumentEvent e) {
				getModel().setInboxPath(inboxPathTextField.getText());

			}
		});

		intervallComboBox.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				getModel().setIntervall(comboBoxListe.get(intervallComboBox.getSelectedItem()));

			}
		});

		autoModusCheckBox.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				getModel().setAutoModus(autoModusCheckBox.isSelected());

			}
		});
	}

	private class SaveAction extends AbstractAction {

		public void actionPerformed(ActionEvent e) {

			// Saves settings into Database.
			getHandler().performOperation(OptionsSaveOperation.class);
		}

	}

	private class CloseAction extends AbstractAction {

		public void actionPerformed(ActionEvent e) {

			getHandler().performOperation(CloseViewOperation.class);

		}

	}

}
