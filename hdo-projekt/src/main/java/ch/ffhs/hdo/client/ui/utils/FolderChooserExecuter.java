package ch.ffhs.hdo.client.ui.utils;

import java.io.File;
import java.io.InputStream;

import javax.swing.JFileChooser;

import ch.ffhs.hdo.client.ui.base.viewhandler.Executable;

public class FolderChooserExecuter implements Executable {

	FileModel model;

	public FolderChooserExecuter(FileModel model) {
		this.model = model;
	}

	public FileModel getModel() {
		return model;
	}

	public void execute(Object arg) {

		InputStream input = null;

		JFileChooser folderChooser = new JFileChooser();
		folderChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		folderChooser.setCurrentDirectory(new File(getModel().getFilePath()));
		int result = folderChooser.showOpenDialog(null);

		if (result == JFileChooser.APPROVE_OPTION) {
			File selectedFolder = folderChooser.getSelectedFile();
			model.setFilePath(selectedFolder.getAbsolutePath());

		}

	}

}
