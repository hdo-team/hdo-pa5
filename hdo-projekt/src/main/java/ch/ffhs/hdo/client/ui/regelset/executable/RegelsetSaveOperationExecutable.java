package ch.ffhs.hdo.client.ui.regelset.executable;

import ch.ffhs.hdo.client.ui.base.viewhandler.Executable;
import ch.ffhs.hdo.client.ui.regelset.RegelsetModel;
import ch.ffhs.hdo.infrastructure.regelset.RegelsetFacade;

/**
 * Speichert ein Regelset in der Datenbank
 * 
 * @author Daniel Crazzolara
 */

public class RegelsetSaveOperationExecutable implements Executable {

	private RegelsetModel model;

	/**
	 * Konstruktor zur Erstellung des Objekts.
	 * @param model Regelset Model welches persistent in die Datenbank gespeichert werden soll.
	 */
	public RegelsetSaveOperationExecutable(RegelsetModel model) {
		
		this.model = model;

	}
	
	/**
	 * Speichert mithilfe der Regelset-Fassade das Regelset Model in die Datenbank.
	 */
	public void execute(Object arg) {
		RegelsetFacade facade = new RegelsetFacade();
		facade.save(model);		
	}
}