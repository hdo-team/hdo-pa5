package ch.ffhs.hdo.client.ui.base;

import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

public abstract class Controller<M extends Model, V extends View<M>> {

	// weiter Informationen :
	// https://docs.oracle.com/javase/tutorial/i18n/resbundle/concept.html

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

}
