package ch.ffhs.hdo.client.ui.utils;

import java.io.File;

import javax.swing.JFileChooser;

import ch.ffhs.hdo.client.ui.base.viewhandler.Executable;

/**
 * Öffnet eine Datei-Auswahl, womit man eine Datei im lokalen Filesystem
 * auswählen kann.
 * 
 * @author Jonas Segessemannn
 *
 */
public class FileChooserExecuter implements Executable {

	IFileModel model;

	/**
	 * Konstruktor welcher das Objekt erstellt.
	 * 
	 * @param model
	 *            Dateimodel, welches den default Pfad zur Datei enthält.
	 */
	public FileChooserExecuter(IFileModel model) {
		this.model = model;
	}

	/**
	 * Gibt den aktuellen Pfad der Datei zurück.
	 * 
	 * @return aktueller Datei-Pfad.
	 */
	public IFileModel getModel() {
		return model;
	}

	/**
	 * Öffnen die Dateiauswahl.
	 */
	public void execute(Object arg) {

		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

		if (getModel().getFilePath() != null) {
			fileChooser.setCurrentDirectory(new File(getModel().getFilePath()));
		} else {
			fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
		}

		int result = fileChooser.showOpenDialog(null);

		if (result == JFileChooser.APPROVE_OPTION) {
			File selectedFolder = fileChooser.getSelectedFile();
			model.setFilePath(selectedFolder.getAbsolutePath());

		}
	}
}