package ch.ffhs.hdo.client.ui.utils;

import java.io.File;

import javax.swing.JFileChooser;

import ch.ffhs.hdo.client.ui.base.viewhandler.Executable;

public class FileChooserExecuter implements Executable<Boolean> {

	FileModel model;

	public FileChooserExecuter(FileModel model) {
		this.model = model;
	}

	public FileModel getModel() {
		return model;
	}

	public void execute(Boolean directoriesOnly) {

		JFileChooser fileChooser = new JFileChooser();

		if (directoriesOnly) {
			fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		}

		fileChooser.setCurrentDirectory(new File(getModel().getFilePath()));
		int result = fileChooser.showOpenDialog(null);

		if (result == JFileChooser.APPROVE_OPTION) {
			File selectedFile = fileChooser.getSelectedFile();
			model.setFilePath(selectedFile.getAbsolutePath()); // FRAGE

		}

	}

}
