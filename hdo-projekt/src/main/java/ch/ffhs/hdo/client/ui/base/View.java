package ch.ffhs.hdo.client.ui.base;

import java.awt.Dimension;
import java.awt.LayoutManager;
import java.util.ResourceBundle;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import ch.ffhs.hdo.client.ui.base.viewhandler.ViewHandler;

public abstract class View<M extends Model> {

	ResourceBundle resourceBundle;
	String title;

	JFrame frame = new JFrame();
	M model;
	ViewHandler viewHandler;

	Dimension dimension;

	public View(ResourceBundle resourceBundle) {
		this.resourceBundle = resourceBundle;
		setDimension(300, 300);
		frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

	}

	public void setDimension(int width, int height) {
		this.dimension = new Dimension(width, height);

	}

	public void setResourceBundle(ResourceBundle resourceBundle) {
		this.resourceBundle = resourceBundle;
	}

	private String getTitle() {
		return title;
	}

	public void setDimension(Dimension dimension) {
		this.dimension = dimension;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setModel(M model) {
		this.model = model;
	}

	public JFrame getFrame() {
		return frame;
	}

	public void show() {
		frame.setTitle(getTitle());
		frame.setSize(dimension);
		frame.setLocation(100, 100);
		frame.setVisible(true);
		// einmal penetrant nach vorn
		frame.setAlwaysOnTop(true);
		// und damit es nicht wirklich so penetrant vorne bleibt, schalten wir
		// das Feature wieder ab
		frame.setAlwaysOnTop(false);

	}

	public void setLayout(LayoutManager manager) {

		this.frame.setLayout(manager);
	}

	public String getMessage(String key) {
		return resourceBundle.getString(key);
	}

	public void dispose() {
		frame.setVisible(false);
		frame.dispose();
	}

	public void setHandler(ViewHandler viewHandler) {
		this.viewHandler = viewHandler;
	}

	public ViewHandler getHander() {
		return viewHandler;
	}

}
