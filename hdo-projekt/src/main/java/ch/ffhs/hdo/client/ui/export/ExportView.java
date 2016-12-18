package ch.ffhs.hdo.client.ui.export;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.XMLEncoder;
import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ResourceBundle;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.jgoodies.forms.builder.FormBuilder;

import ch.ffhs.hdo.client.ui.base.View;
import ch.ffhs.hdo.client.ui.base.executable.CloseViewOperation;
import ch.ffhs.hdo.client.ui.export.executable.ExportSaveOperation;
import ch.ffhs.hdo.client.ui.utils.ChooseDirectoryPathViewOperation;

/**
 * Exportfenster welches ueber das Menue Export im Hauptfenster geoeffnet werden
 * kann.
 * 
 * @author Jonas Segessemann
 *
 */
public class ExportView extends View<ExportModel> {
	private static Logger LOGGER = LogManager.getLogger(ExportView.class);

	private final String I18N = "hdo.export";
	private final String TITLE_KEY = I18N + ".title";
	private JTextField pathTextField;
	private JButton pathChooserButton;

	private JButton exportButton;
	private JButton cancelButton;

	/**
	 * Laedt die Sprachdatei, und setzt den Titel des Fensters.
	 * 
	 * @param resourceBundle
	 *            Uebersetzungen der aktuellen Sprache.
	 */
	public ExportView(ResourceBundle resourceBundle) {
		super(resourceBundle);
		setTitle(getMessage(TITLE_KEY));

		initComponents();

	}

	/**
	 * Initialisierung des Export-Fensters.
	 */
	private void initComponents() {
		createComponents();
		layoutForm();

	}

	/**
	 * Erstellt alle GUI Komponenten.
	 */
	private void createComponents() {

		pathTextField = new JTextField();

		pathChooserButton = new JButton(getMessage(I18N + ".button.pathChooser"));
		pathChooserButton.addActionListener(new ChooseFolderPathAction());

		exportButton = new JButton(getMessage(I18N + ".button.export"));
		exportButton.addActionListener(new ExportAllAction());

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

	/**
	 * Konfiguriert die einzelnen Komponenten und erstellt die Listener.
	 */
	@Override
	public void configureBindings() {

		getModel().addPropertyChangeListener(new PropertyChangeListener() {

			public void propertyChange(PropertyChangeEvent evt) {
				if (evt.getPropertyName() == "filePath") {
					pathTextField.setText(getModel().getFilePath());
				}

			}
		});

	}

	/**
	 * Speichert alle Konfigurationen unter dem Export-Pfad.
	 */
	private class ExportAllAction extends AbstractAction {

		public void actionPerformed(ActionEvent e) {

			String errorMsg = checkFolderPathValue();
			if (errorMsg != null) {
				JOptionPane.showMessageDialog(null, errorMsg);
			} else {
				getHandler().performOperation(ExportSaveOperation.class);

				// Ordnerstruktur speichern
				XMLEncoder enc = null;
				try {
					enc = new XMLEncoder(new BufferedOutputStream(
							new FileOutputStream(System.getProperty("user.home") + "/Desktop/tree.xml")));
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				if (getModel() != null) {
					System.out.println("Teil 1");
					if (getModel().getFolderModel() != null) {
						System.out.println("Teil 2");

						if (getModel().getFolderModel().getTreeModel() != null) {
							System.out.println("Teil 3");
						} else {
							System.out.println("Teil 3 Abbruch");
						}

					} else {
						System.out.println("Teil 2 Abbruch");
					}
				} else {
					System.out.println("Teil 1 Abbruch");
				}
				enc.close();

				getHandler().performOperation(CloseViewOperation.class);
			}
		}
	}

	/**
	 * Oeffnet die Verzeichnisauswahl, um einen Export-Pfad auszuwaehlen.
	 */
	private class ChooseFolderPathAction extends AbstractAction {

		public void actionPerformed(ActionEvent e) {

			getHandler().performOperation(ChooseDirectoryPathViewOperation.class);
		}
	}

	/**
	 * Ueberprueft ob ein gueltiger Export-Pfad ausgwewaehlt wurde.
	 * 
	 * @return null oder im Fehlerfall eine Meldung.
	 */
	private String checkFolderPathValue() {
		String errorString = null;

		// Whitespace entfernen und dann erst checken!
		if (pathTextField.getText() == null || pathTextField.getText().equals("")) {
			errorString = "Bitte Speicherort auswählen"; // TODO via ressourcen
		}
		return errorString;
	}

	/**
	 * Schliesst das Export-Fenster.
	 */
	private class CloseAction extends AbstractAction {

		public void actionPerformed(ActionEvent e) {

			getHandler().performOperation(CloseViewOperation.class);
		}
	}

}
