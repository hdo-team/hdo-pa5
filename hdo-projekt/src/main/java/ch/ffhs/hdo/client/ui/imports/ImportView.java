package ch.ffhs.hdo.client.ui.imports;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
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
import ch.ffhs.hdo.client.ui.hauptfenster.RegelsetTableModel;
import ch.ffhs.hdo.client.ui.hauptfenster.executable.RegelsetTableUpdateOperation;
import ch.ffhs.hdo.client.ui.hauptfenster.executable.RegelsetTableUpdateOperationExecutable;
import ch.ffhs.hdo.client.ui.imports.executable.ImportSaveOperation;
import ch.ffhs.hdo.client.ui.utils.ChooseFilePathViewOperation;
import ch.ffhs.hdo.infrastructure.ApplicationSettings;
import ch.ffhs.hdo.infrastructure.regelset.RegelsetFacade;

/**
 * Importfenster welches ueber das Menue Import im Hauptfenster geoeffnet werden
 * kann.
 * 
 * @author Adrian Perez Rodriguez
 *
 */
public class ImportView extends View<ImportModel> {

	private final String I18N = "hdo.import";
	private final String TITLE_KEY = I18N + ".title";
	
	JTextField filePath;
	private JButton filePathButton;
	private JButton loadButton;
	private JButton cancelButton;

	/**
	 * Laedt die Sprachdatei, und setzt den Titel des Fensters.
	 * 
	 * @param resourceBundle
	 *            Uebersetzungen der aktuellen Sprache.
	 */
	public ImportView(ResourceBundle resourceBundle) {
		super(resourceBundle);
		setTitle(getMessage(TITLE_KEY));

		initComponents();
	}

	/**
	 * Initialisierung des Import-Fensters.
	 */
	private void initComponents() {
		createComponents();
		layoutForm();

	}

	/**
	 * Konfiguriert die einzelnen Komponenten und erstellt die Listener.
	 */
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

	/**
	 * Erstellt alle GUI Komponenten.
	 */
	private void createComponents() {

		filePath = new JTextField();
		filePathButton = new JButton(getMessage(I18N + ".button.pathChooser"));
		filePathButton.addActionListener(new ChooseFilePathAction());
		
		loadButton = new JButton(getMessage(I18N + ".button.import"));
		loadButton.addActionListener(new ImportAction());
		
		cancelButton = new JButton(getMessage("base.cancel"));
		cancelButton.addActionListener(new CloseAction());
		
	}

	/**
	 * Ordnet die erstellten GUI Komponenten.
	 */
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
	
	/**
	 * Importiert alle Konfigurationen vom ausgewaehlten Pfad im Fenster
	 */
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
					JOptionPane.showMessageDialog(null, "Import erfolgreich!\n\nBitte Programm schliessen und erneut starten!");
				} catch (Exception e1){
					JOptionPane.showMessageDialog(null, "Import nicht erfolgreich!\n\nEs ist ein Fehler aufgetreten.");
				}
				getHandler().performOperation(CloseViewOperation.class);
			}
		}
	}

	/**
	 * Oeffnet die Verzeichnisauswahl (nur Dateien koennen ausgewaehlt werden), um eine Importdatei auszuwaehlen.
	 */
	private class ChooseFilePathAction extends AbstractAction {

		public void actionPerformed(ActionEvent e) {

			getHandler().performOperation(ChooseFilePathViewOperation.class);
		}
	}
	
	/**
	 * Ueberprueft ob eine gueltige Importdatei ausgwewaehlt wurde.
	 * 
	 * @return null oder im Fehlerfall eine Meldung.
	 */
	private String checkFilePathValue() {
		String errorString = null;

		if (filePath.getText() == null || filePath.getText().equals("")) {
			errorString = "Bitte eine Importdatei auswählen";
		}
		return errorString;
	}
	
	/**
	 * Ueberprueft ob ein Inbox-Pfad in den Einstellungen ausgewaehlt wurde.
	 * 
	 * @return null oder im Fehlerfall eine Meldung.
	 */
	private String checkInboxPathValue() {
		String errorString = null;
	
		String inboxPath = ApplicationSettings.getInstance().getInbox_path();
		
		if (inboxPath == null || inboxPath.equals("")) {
			errorString = "Bitte einen Inbox-Pfad in den Einstellungen angeben";
		}
		return errorString;
	}
	
	/**
	 * Schliesst das Import-Fenster.
	 */
	private class CloseAction extends AbstractAction {

		public void actionPerformed(ActionEvent e) {

			getHandler().performOperation(CloseViewOperation.class);
		}
	}
	
}
