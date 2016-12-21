package ch.ffhs.hdo.client.ui.base;

import java.sql.SQLException;
import java.util.ResourceBundle;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ch.ffhs.hdo.infrastructure.option.OptionFacade;
import ch.ffhs.hdo.persistence.jdbc.JdbcHelper;

/**
 * 
 * Stammklasse um die Controller zu erstellen. Gibt den Ramen und Anforderung
 * fuer einen Controller an.
 * 
 * @author Denis Bittante
 * 
 *
 * @param <M>
 *            ist das zu benutzende {@link Model}
 * @param <V>
 *            ist die zu benutzende {@link View}
 */
public abstract class Controller<M extends Model, V extends View<M>> {
	private static Logger LOGGER = LogManager.getLogger(Controller.class);

	M model;
	ResourceBundle resourceBundle;
	V view;

	/**
	 * 
	 * @param model
	 *            Model fuer den Controller
	 */
	public Controller(M model) {
		this.resourceBundle = ResourceBundle.getBundle("ch/ffhs/hdo/client/ui/resourceBundle");
		this.model = model;

	}

	/**
	 * Getter des Models
	 * 
	 * @return Implementierung des {@link Model}
	 */
	public M getModel() {
		return model;
	}

	/**
	 * Retouriniert das {@link ResourceBundle}
	 * 
	 * @return {@link ResourceBundle}
	 */
	public ResourceBundle getResourceBundle() {
		return this.resourceBundle;

	}

	/**
	 * Getter fuer {@link View}
	 * 
	 * @return {@link View}
	 */
	public V getView() {
		return view;
	}

	/**
	 * Initialisiert die Handler ueblicherweiese wird folgendes
	 * implementiert:<br>
	 * <code>getView().setHandler(viewHandler);</code><br>
	 * <code>getView().setResourceBundle(getResourceBundle());</code><br>
	 * <code>getView().setModel(getModel());</code>
	 * 
	 */
	public abstract void initializeView();

	/**
	 * Setter fuer View
	 * 
	 * @param view
	 *            {@link View}
	 */
	public void setView(V view) {
		this.view = view;
	}

	/**
	 * Zeigt die View an
	 */
	public void show() {

		this.view.show();
	}

	/**
	 * Stoppt die View und trennt Verbindung zur Datenbank
	 */
	public void terminate() {

		getView().dispose();
		getModel().deleteObservers();

		final JdbcHelper jdbcHelper = new JdbcHelper();
		try {
			jdbcHelper.terminate();
		} catch (SQLException e) {

			LOGGER.error("Fehler beim Terminieren des Controllers", e);
		}
	}

}
