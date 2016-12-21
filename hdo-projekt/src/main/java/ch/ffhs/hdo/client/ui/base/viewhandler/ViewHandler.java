package ch.ffhs.hdo.client.ui.base.viewhandler;

/**
 * Ein ViewHandler ist ein Objekt, ueber welches die View mit dem Controller,
 * der die View erzeugt hat, interagieren kann.
 * 
 * @author Denis Bittante
 */
public interface ViewHandler extends Destroyable {

	/**
	 * Fuehrt die Operation aus.
	 * 
	 * @param operation
	 *            Die auszufuehrende Operation
	 * 
	 * @throws UnsupportedOperationException
	 *             Falls die Operation nicht erlaubt ist.
	 * 
	 */
	void performOperation(Class<? extends ViewOperation> operation);

	/**
	 * Fuehrt die Operation aus und uebergibt ihr das mitgegebene Argument.
	 * 
	 * @param operation
	 *            Die auszufuehrende Operation
	 * @param arg
	 *            Das Argument, welches an die Operation weitergereicht wird.
	 * 
	 * @throws UnsupportedOperationException
	 *             Falls die Operation nicht erlaubt ist.
	 */
	void performOperationWithArgs(Class<? extends ViewOperation> operation, Object arg);

}