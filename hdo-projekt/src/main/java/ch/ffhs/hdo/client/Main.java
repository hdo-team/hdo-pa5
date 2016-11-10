package ch.ffhs.hdo.client;

import javax.swing.JFrame;

import ch.ffhs.hdo.client.ui.einstellungen.OptionController;
import ch.ffhs.hdo.client.ui.einstellungen.OptionModel;

public class Main {

	public static void main(String[] args) {

		// Init Controller
		OptionController controller = new OptionController(new OptionModel());
		// Start MainView

		controller.show();

	

	}

}
