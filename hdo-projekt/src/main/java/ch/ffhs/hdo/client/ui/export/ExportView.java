package ch.ffhs.hdo.client.ui.export;

import java.awt.BorderLayout;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JFileChooser;
import javax.swing.border.EmptyBorder;
import java.awt.event.*;
import java.io.File;

import com.jgoodies.forms.builder.FormBuilder;

import ch.ffhs.hdo.client.ui.base.View;

public class ExportView extends View<ExportModel> implements ActionListener{

	private final String I18N = "hdo.export";
	private final String TITLE_KEY = I18N + ".title";
	private JTextField targetPathTextField;
	private JFileChooser targetPathFileChooser;
	private JButton targetPathChooserButton;

	private JButton exportButton;
	private JButton cancelButton;

	public ExportView(ResourceBundle resourceBundle) {
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

		targetPathTextField = new JTextField();

		targetPathChooserButton = new JButton(getMessage(I18N + ".button.TargetPathChooser"));
		targetPathChooserButton.addActionListener(this);
		targetPathFileChooser = new JFileChooser();
		targetPathFileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		

		exportButton = new JButton(getMessage(I18N + ".button.export"));
		cancelButton = new JButton(getMessage("base.cancel"));
	}

	private void layoutForm() {

		FormBuilder builder = FormBuilder.create()
				.columns("right:pref, 5dlu,[30dlu, pref],5dlu,[20dlu, pref],5dlu, [20dlu, pref]")
				.rows("p, $lg, p, $lg, p, $lg, p, $lg, p , $lg, p , $lg, p");

		builder.addLabel(getMessage(I18N + ".label.TargetPath")).rc(3, 1);
		builder.add(targetPathTextField).rcw(3, 3, 3);
		builder.add(targetPathChooserButton).rcw(3, 7, 1);

		builder.add(exportButton).rcw(11, 3, 1);
		builder.add(cancelButton).rcw(11, 5, 1);

		builder.padding(new EmptyBorder(5, 5, 5, 5));
		JPanel build = builder.build();

		getFrame().add(build, BorderLayout.CENTER);

		setDimension(430, 145);
	}

	private void configureBindings() {

	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == targetPathChooserButton) {
			int returnVal = targetPathFileChooser.showOpenDialog(null);
			if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = targetPathFileChooser.getSelectedFile();
            }
		}
	}

}
