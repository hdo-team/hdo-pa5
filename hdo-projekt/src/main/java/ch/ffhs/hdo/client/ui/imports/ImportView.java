package ch.ffhs.hdo.client.ui.imports;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.jgoodies.forms.builder.FormBuilder;

import ch.ffhs.hdo.client.ui.base.View;
import ch.ffhs.hdo.client.ui.base.executable.CloseViewOperation;
import ch.ffhs.hdo.client.ui.export.executable.ExportSaveOperation;
import ch.ffhs.hdo.client.ui.imports.executable.ImportSaveOperation;
import ch.ffhs.hdo.client.ui.utils.ChooseFilePathViewOperation;
import ch.ffhs.hdo.client.ui.utils.ReadFileViewOperation;
import ch.ffhs.hdo.infrastructure.ApplicationSettings;

/**
 * 
 * @author Adrian Perez Rodriguez
 */
public class ImportView extends View<ImportModel> {

	private final String I18N = "hdo.import";
	private final String TITLE_KEY = I18N + ".title";
	
	JTextField filePath;
	private JButton filePathButton;
	private JButton loadButton;
	private JButton cancelButton;

	public ImportView(ResourceBundle resourceBundle) {
		super(resourceBundle);
		setTitle(getMessage(TITLE_KEY));

		initComponents();
	}

	private void initComponents() {
		createComponents();
		layoutForm();

	}

	@Override
	public void configureBindings() {

		getModel().addPropertyChangeListener(new PropertyChangeListener() {

			public void propertyChange(PropertyChangeEvent evt) {
				if (evt.getPropertyName() == "filePath") {
					filePath.setText(getModel().getFilePath());
				}

			}
		});

	}

	private void createComponents() {

		filePath = new JTextField();
		filePathButton = new JButton(getMessage(I18N + ".button.pathChooser"));
		filePathButton.addActionListener(new ChooseFilePathAction());
		
		loadButton = new JButton(getMessage(I18N + ".button.import"));
		loadButton.addActionListener(new ImportAction());
		
		cancelButton = new JButton(getMessage("base.cancel"));
		cancelButton.addActionListener(new CloseAction());
		
	}

	private void layoutForm() {

		FormBuilder builder = FormBuilder.create()
				.columns("right:pref, 5dlu,[30dlu, pref],5dlu,[20dlu, pref],5dlu, [20dlu, pref]")
				.rows("p, $lg, p, $lg, p, $lg, p, $lg, p , $lg, p , $lg, p");

		builder.addLabel(getMessage(I18N + ".label.filePath")).rc(3, 1);
		builder.add(filePath).rcw(3, 3, 3);
		builder.add(filePathButton).rcw(3, 7, 1);

		builder.add(loadButton).rcw(11, 3, 1);
		builder.add(cancelButton).rcw(11, 5, 1);

		builder.padding(new EmptyBorder(5, 5, 5, 5));
		JPanel build = builder.build();

		getFrame().add(build, BorderLayout.CENTER);

		setDimension(430, 145);
	}
	
	private class ImportAction extends AbstractAction {

		public void actionPerformed(ActionEvent e) {
			
			String errorMsg_folder = checkFilePathValue();
			String errorMsg_inbox = checkInboxPathValue();
			
			if (errorMsg_folder != null) {
				JOptionPane.showMessageDialog(null, errorMsg_folder);	
			} else if (errorMsg_inbox != null){
				JOptionPane.showMessageDialog(null, errorMsg_inbox);
			} else {
				
				try {
					getHandler().performOperation(ImportSaveOperation.class);
					JOptionPane.showMessageDialog(null, "Import erfolgreich!/nBitte Programm schliessen und erneut starten!");
				} catch (Exception e1){
					JOptionPane.showMessageDialog(null, "Import nicht erfolgreich!");
				}
				getHandler().performOperation(CloseViewOperation.class);
			}
		}
	}

	private class ChooseFilePathAction extends AbstractAction {

		public void actionPerformed(ActionEvent e) {

			getHandler().performOperation(ChooseFilePathViewOperation.class);
		}
	}
	
	private String checkFilePathValue() {
		String errorString = null;

		if (filePath.getText() == null || filePath.getText().equals("")) {
			errorString = "Bitte eine Importdatei auswählen";
		}
		return errorString;
	}
	
	private String checkInboxPathValue() {
		String errorString = null;
	
		String inboxPath = ApplicationSettings.getInstance().getInbox_path();
		
		if (inboxPath == null || inboxPath.equals("")) {
			errorString = "Bitte einen Inbox-Pfad in den Einstellungen angeben";
		}
		return errorString;
	}
	
	private class CloseAction extends AbstractAction {

		public void actionPerformed(ActionEvent e) {

			getHandler().performOperation(CloseViewOperation.class);
		}
	}
	
}
