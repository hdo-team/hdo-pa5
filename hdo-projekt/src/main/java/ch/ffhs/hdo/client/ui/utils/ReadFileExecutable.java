package ch.ffhs.hdo.client.ui.utils;

import java.io.FileInputStream;
import java.io.IOException;

import ch.ffhs.hdo.client.ui.base.viewhandler.Executable;

public class ReadFileExecutable implements Executable {

	IFileModel model;

	public ReadFileExecutable(IFileModel model) {

		this.model = model;

	}

	public IFileModel getModel() {
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
