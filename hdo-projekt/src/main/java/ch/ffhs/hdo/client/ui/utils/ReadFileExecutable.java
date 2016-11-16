package ch.ffhs.hdo.client.ui.utils;

import java.io.FileInputStream;
import java.io.IOException;

import ch.ffhs.hdo.client.ui.base.viewhandler.Executable;

public class ReadFileExecutable implements Executable {

	FileModel model;

	public ReadFileExecutable(FileModel model) {

		this.model = model;

	}

	public FileModel getModel() {
		return model;
	}

	public void execute(Object arg) {
		FileInputStream input = null;
		try {
			input = new FileInputStream(getModel().getFilePath());
			System.out.println("Hall mein Freund ");

		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
	}
}
