package ch.ffhs.hdo.client.ui.utils;

import java.io.File;

import javax.swing.JFileChooser;

import ch.ffhs.hdo.client.ui.base.viewhandler.Executable;

public class FolderChooserExecuter implements Executable {

	IFileModel model;

	public FolderChooserExecuter(IFileModel model) {
		this.model = model;
	}

	public IFileModel getModel() {
		return model;
	}
	
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