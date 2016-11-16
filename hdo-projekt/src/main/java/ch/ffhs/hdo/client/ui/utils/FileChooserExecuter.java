package ch.ffhs.hdo.client.ui.utils;

import java.io.File;
import java.io.InputStream;

import javax.swing.JFileChooser;

import ch.ffhs.hdo.client.ui.base.viewhandler.Executable;

public class FileChooserExecuter implements Executable {

	FileModel model;

	public FileChooserExecuter(FileModel model) {
		this.model = model;
	}

	public FileModel getModel() {
		return model;
	}

	public void execute(Object arg) {

		InputStream input = null;

		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setCurrentDirectory(new File(getModel().getFilePath()));
		int result = fileChooser.showOpenDialog(null);

		if (result == JFileChooser.APPROVE_OPTION) {
			File selectedFile = fileChooser.getSelectedFile();
			model.setFilePath(selectedFile.getAbsolutePath()); // FRAGE

		}

	}

}
