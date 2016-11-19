package ch.ffhs.hdo.client.ui.utils;

import java.io.File;

import javax.swing.JFileChooser;

import ch.ffhs.hdo.client.ui.base.viewhandler.Executable;

public class FileChooserExecuter implements Executable {

	IFileModel model;

	public FileChooserExecuter(IFileModel model) {
		this.model = model;
	}

	public IFileModel getModel() {
		return model;
	}

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