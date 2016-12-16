package ch.ffhs.hdo.client.ui.base.viewhandler;

/**
 * 
 * Interface für die {@link Executable}
 * 
 * @author Denis Bittante
 *
 * @param <T> mögliche Parameter für die Ausführung einer Aktion.
 */
public interface Executable<T> {
	/**
	 * Aktion Ausführen 
	 * @param arg für mögliche Ausführung.
	 */
    void execute(T arg);
}