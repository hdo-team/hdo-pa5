package ch.ffhs.hdo.client.ui.utils;

import java.io.File;

import javax.swing.JFileChooser;

import ch.ffhs.hdo.client.ui.base.viewhandler.Executable;

/**
 * Oeffnet eine Datei-Auswahl, womit man eine Datei im lokalen Filesystem
 * auswaehlen kann.
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
	 *            Dateimodel, welches den default Pfad zur Datei enthaelt.
	 */
	public FileChooserExecuter(IFileModel model) {
		this.model = model;
	}

	/**
	 * Gibt den aktuellen Pfad der Datei zurueck.
	 * 
	 * @return aktueller Datei-Pfad.
	 */
	public IFileModel getModel() {
		return model;
	}

	/**
	 * Oeffnen die Dateiauswahl.
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