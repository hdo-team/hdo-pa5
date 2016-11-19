package ch.ffhs.hdo.client.ui.utils;

import java.io.File;

import javax.swing.JFileChooser;

import ch.ffhs.hdo.client.ui.base.viewhandler.Executable;

public class FileChooserExecuter implements Executable<Boolean> {

	IFileModel model;

	public FileChooserExecuter(IFileModel model) {
		this.model = model;
	}

	public IFileModel getModel() {
		return model;
	}

	public void execute(Boolean directoriesOnly) {

		JFileChooser fileChooser = new JFileChooser();

		if (directoriesOnly) {
			fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		}

		if (getModel().getFilePath() != null) {
			fileChooser.setCurrentDirectory(new File(getModel().getFilePath()));
		} else {
			fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
		}
		int result = fileChooser.showOpenDialog(null);

		if (result == JFileChooser.APPROVE_OPTION) {
			File selectedFile = fileChooser.getSelectedFile();
			model.setFilePath(selectedFile.getAbsolutePath()); // FRAGE

		}

	}

}
