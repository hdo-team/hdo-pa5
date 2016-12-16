package ch.ffhs.hdo.client.ui.base;

import java.sql.SQLException;
import java.util.ResourceBundle;

import ch.ffhs.hdo.persistence.jdbc.JdbcHelper;

/**
 * 
 * Stammklasse um die Controller zu erstellen. Gibt den Ramen und Anforderung
 * für einen Controller an.
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

	ResourceBundle resourceBundle;
	M model;
	V view;

	public Controller(M model) {
		this.resourceBundle = ResourceBundle.getBundle("ch/ffhs/hdo/client/ui/resourceBundle");
		this.model = model;

	}

	public abstract void initializeView();

	public ResourceBundle getResourceBundle() {
		return this.resourceBundle;

	}

	public M getModel() {
		return model;
	}

	public void show() {

		this.view.show();
	}

	public V getView() {
		return view;
	}

	public void setView(V view) {
		this.view = view;
	}

	public void terminate() {

		getView().dispose();
		getModel().deleteObservers();

		final JdbcHelper jdbcHelper = new JdbcHelper();
		try {
			jdbcHelper.terminate();
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

}
