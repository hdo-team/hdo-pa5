package ch.ffhs.hdo.client.ui.base;

import java.util.Observable;

import javax.swing.event.SwingPropertyChangeSupport;

public abstract class Model extends Observable {

	private SwingPropertyChangeSupport propertyChangeSupport = new SwingPropertyChangeSupport(this);

	public void firePropertyChange(String propertyName, Object oldValue, Object newValue) {
		propertyChangeSupport.firePropertyChange(propertyName, oldValue, newValue);
	}

	public void firePropertyChange(String propertyName, int oldValue, int newValue) {
		propertyChangeSupport.firePropertyChange(propertyName, oldValue, newValue);
	}

	public void firePropertyChange(String propertyName, boolean oldValue, boolean newValue) {
		propertyChangeSupport.firePropertyChange(propertyName, oldValue, newValue);
	}

}
