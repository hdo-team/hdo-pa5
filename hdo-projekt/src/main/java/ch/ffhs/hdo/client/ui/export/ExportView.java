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
	private JTextField pathTextField;
	private JFileChooser pathFileChooser;
	private JButton pathChooserButton;

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

		pathTextField = new JTextField();

		pathChooserButton = new JButton(getMessage(I18N + ".button.pathChooser"));
		pathChooserButton.addActionListener(this);
		pathFileChooser = new JFileChooser();
		pathFileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		

		exportButton = new JButton(getMessage(I18N + ".button.export"));
		cancelButton = new JButton(getMessage("base.cancel"));
	}

	private void layoutForm() {

		FormBuilder builder = FormBuilder.create()
				.columns("right:pref, 5dlu,[30dlu, pref],5dlu,[20dlu, pref],5dlu, [20dlu, pref]")
				.rows("p, $lg, p, $lg, p, $lg, p, $lg, p , $lg, p , $lg, p");

		builder.addLabel(getMessage(I18N + ".label.targetPath")).rc(3, 1);
		builder.add(pathTextField).rcw(3, 3, 3);
		builder.add(pathChooserButton).rcw(3, 7, 1);

		builder.add(exportButton).rcw(11, 3, 1);
		builder.add(cancelButton).rcw(11, 5, 1);

		builder.padding(new EmptyBorder(5, 5, 5, 5));
		JPanel build = builder.build();

		getFrame().add(build, BorderLayout.CENTER);

		setDimension(430, 145);
	}
		
	@Override
	public void configureBindings() {

	}

	public void addExportButtonListener(ActionListener listenForExportButton){
		this.exportButton.addActionListener(listenForExportButton);
	}
	
	public String getSelectedPath(){
		return pathTextField.getText();
	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == pathChooserButton) {
			int returnVal = pathFileChooser.showOpenDialog(null);
			if (returnVal == JFileChooser.APPROVE_OPTION) {
                File folder = pathFileChooser.getSelectedFile();
                pathTextField.setText(folder.getAbsolutePath());
                
            }
		}
	}

}
