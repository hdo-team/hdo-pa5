package ch.ffhs.hdo.client.ui.hauptfenster.executable;

import ch.ffhs.hdo.client.ui.base.viewhandler.Executable;
import ch.ffhs.hdo.client.ui.hauptfenster.RegelsetTableModel;
import ch.ffhs.hdo.infrastructure.regelset.RegelsetFacade;

/**
 * Aktualisiert die Regelset Übersicht mit dem neuen Model.
 * 
 * @author Jonas Segessemann
 *
 */
public class RegelsetTableUpdateOperationExecutable implements Executable<Object> {

	private RegelsetTableModel model;

	/**
	 * Konstruktor zum erstellen des Objekts.
	 * 
	 * @param model
	 *            Regelset Model mit allen Regelsets.
	 */
	public RegelsetTableUpdateOperationExecutable(RegelsetTableModel model) {
		this.model = model;
	}

	/**
	 * Lädt alle Regelsets aus der Datenbank ins Model und aktualisiert das
	 * View.
	 */
	public void execute(Object arg) {
		RegelsetFacade facade = new RegelsetFacade();
		model.setRulsetList(facade.getAllRegelsets());
	}
}