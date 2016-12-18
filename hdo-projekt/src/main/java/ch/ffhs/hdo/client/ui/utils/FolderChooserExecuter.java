package ch.ffhs.hdo.client.ui.utils;

import java.io.File;

import javax.swing.JFileChooser;

import ch.ffhs.hdo.client.ui.base.viewhandler.Executable;

/**
 * Oeffnet eine Verzeichnis-Auswahl, womit man eine Ordner im lokalen
 * Filesystem auswaehlen kann.
 * 
 * @author Jonas Segessemann
 *
 */
public class FolderChooserExecuter implements Executable {

	IFileModel model;

	/**
	 * Konstruktor welcher das Objekt erstellt.
	 * 
	 * @param model
	 *            Dateimodel, welches den default Pfad zum Ordner enthaelt.
	 */
	public FolderChooserExecuter(IFileModel model) {
		this.model = model;
	}

	/**
	 * Gibt den aktuellen Pfad des Ordners zurueck.
	 * 
	 * @return aktueller Ordner Pfad.
	 */
	public IFileModel getModel() {
		return model;
	}

	/**
	 * Oeffnen die Verzeichnis-Auswahl.
	 */
	public void execute(Object arg) {

		JFileChooser folderChooser = new JFileChooser();
		folderChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

		if (getModel().getFilePath() != null) {
			folderChooser.setCurrentDirectory(new File(getModel().getFilePath()));
		} else {
			folderChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
		}
		int result = folderChooser.showOpenDialog(null);

		if (result == JFileChooser.APPROVE_OPTION) {
			File selectedFile = folderChooser.getSelectedFile();
			model.setFilePath(selectedFile.getAbsolutePath()); // FRAGE

		}
	}
}