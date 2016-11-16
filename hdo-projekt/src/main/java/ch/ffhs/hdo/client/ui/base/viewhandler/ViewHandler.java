package ch.ffhs.hdo.client.ui.base.viewhandler;

/**
 * Ein ViewHandler ist ein Objekt, über welches die View mit dem Controller, der
 * die View erzeugt hat, interagieren kann.
 */
public interface ViewHandler extends Destroyable {

	/**
	 * Führt die Operation aus.
	 * 
	 * @param operation
	 *            Die auszuführende Operation
	 * 
	 * @throws UnsupportedOperationException
	 *             Falls die Operation nicht erlaubt ist.
	 * 
	 */
	void performOperation(Class<? extends ViewOperation> operation);

	/**
	 * Führt die Operation aus und übergibt ihr das mitgegebene Argument.
	 * 
	 * @param operation
	 *            Die auszuführende Operation
	 * @param arg
	 *            Das Argument, welches an die Operation weitergereicht wird.
	 * 
	 * @throws UnsupportedOperationException
	 *             Falls die Operation nicht erlaubt ist.
	 */
	void performOperationWithArgs(Class<? extends ViewOperation> operation, Object arg);

}