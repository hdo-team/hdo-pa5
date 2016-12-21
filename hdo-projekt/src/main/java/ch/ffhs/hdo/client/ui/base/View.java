package ch.ffhs.hdo.client.ui.base;

import java.awt.Dimension;
import java.awt.LayoutManager;
import java.util.ResourceBundle;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.WindowConstants;

import ch.ffhs.hdo.client.ui.base.viewhandler.ViewHandler;

/**
 * Stammklasse fuer die Views
 * 
 * @author Denis Bittante
 *
 * @param <M>
 *            Modeltyp, gleiches wie fuer die Controller
 */
public abstract class View<M extends Model> {

	Dimension dimension;
	JFrame frame = new JFrame();

	M model;
	ResourceBundle resourceBundle;
	String title;

	ViewHandler viewHandler;

	public View(ResourceBundle resourceBundle) {

		try {

			for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (Exception e) {
			// If Nimbus is not available, you can set the GUI to another look
			// and feel.
		}

		this.resourceBundle = resourceBundle;
		setDimension(300, 300);
		frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

	}

	/**
	 * Konfiguriert die einzelnen Komponenten und erstellt die Listener.
	 */
	public abstract void configureBindings();

	/**
	 * View verwerfen
	 * 
	 */
	public void dispose() {
		frame.setVisible(false);
		frame.dispose();
	}

	/**
	 * Getter um die View-Frame zu erhalten
	 * 
	 * @return {@link JFrame}
	 */
	public JFrame getFrame() {
		return frame;
	}

	/**
	 * Getter für den ViewHandler
	 * 
	 * @return {@link ViewHandler}
	 */
	public ViewHandler getHandler() {
		return viewHandler;
	}

	/**
	 * Hilfsmethode für um einen Text aus der RessourceBundle zu erhalten.
	 * 
	 * @param key
	 *            Key welcher gesucht werden soll
	 * @return den String aus dem Resoucebundle
	 */
	public String getMessage(String key) {
		return resourceBundle.getString(key);
	}

	/**
	 * Hilfsmethode für um einen Text aus der RessourceBundle zu erhalten.
	 * 
	 * @param key
	 *            Key welcher gesucht werden soll
	 * @param replacement
	 *            einen Teil des String der ersetzt werden soll.
	 * @return den String mit dem ersetzten Teilstring
	 */
	public String getMessage(String key, String replacement) {
		return (resourceBundle.getString(key)).replaceAll("%s", replacement);
	}

	/**
	 * Getter für das Model
	 * 
	 * @return Implementation von {@link Model}
	 */
	public M getModel() {
		return model;
	}

	/**
	 * Gibt den Fenstertitel zurueck.
	 * 
	 * @return Fenstertitel
	 */
	private String getTitle() {
		return title;
	}

	/**
	 * Setzt die Groesse des Fensters
	 * 
	 * @param dimension
	 *            -> Hoehe und Breite
	 */
	public void setDimension(Dimension dimension) {
		this.dimension = dimension;
	}

	/**
	 * Setzt die Groesse des Fensters
	 * 
	 * @param width
	 *            -> Breite
	 * @param height
	 *            -> Hoehe
	 */
	public void setDimension(int width, int height) {
		this.dimension = new Dimension(width, height);

	}

	/**
	 * Handler seten
	 * 
	 * @param model
	 */
	public void setHandler(ViewHandler viewHandler) {
		this.viewHandler = viewHandler;
	}

	/**
	 * Setter umd den LayoutManager anzupassen.
	 * 
	 */
	public void setLayout(LayoutManager manager) {

		this.frame.setLayout(manager);
	}

	/**
	 * Setter für das Model
	 * 
	 * @param model
	 */
	public void setModel(M model) {
		this.model = model;
		configureBindings();

	}

	/**
	 * Setzt die ResourceBundles, diese sind anhand des Locals geladen worden.
	 * 
	 * @param resourceBundle
	 *            {@link ResourceBundle}
	 */
	public void setResourceBundle(ResourceBundle resourceBundle) {
		this.resourceBundle = resourceBundle;
	}

	/**
	 * Setzt den Titel der Frame
	 * 
	 * @param model
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * Zeigt die View an
	 */
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

}
