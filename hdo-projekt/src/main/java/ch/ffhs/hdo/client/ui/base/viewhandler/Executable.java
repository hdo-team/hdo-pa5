package ch.ffhs.hdo.client.ui.base.viewhandler;

/**
 * 
 * Interface fuer die {@link Executable}
 * 
 * @author Denis Bittante
 *
 * @param <T> moegliche Parameter fuer die Ausfuehrung einer Aktion.
 */
public interface Executable<T> {
	/**
	 * Aktion Ausfuehren 
	 * @param arg fuer moegliche Ausfuehrung.
	 */
    void execute(T arg);
}