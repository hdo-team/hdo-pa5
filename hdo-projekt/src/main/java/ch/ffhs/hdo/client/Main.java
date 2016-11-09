package ch.ffhs.hdo.client;

import javax.swing.JFrame;

import ch.ffhs.hdo.client.ui.einstellungen.OptionController;

public class Main {

	public static void main(String[] args) {

		// Init Controller
		OptionController controller = new OptionController();
		// Start MainView

		JFrame window = controller.getMainWindow();
		

		window.setVisible(true);
		// einmal penetrant nach vorn
		window.setAlwaysOnTop(true);
		// und damit es nicht wirklich so penetrant vorne bleibt, schalten wir
		// das Feature wieder ab
		window.setAlwaysOnTop(false);

	}

}
