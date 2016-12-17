package ch.ffhs.hdo.client.ui.einstellungen.executable;

import ch.ffhs.hdo.client.ui.base.viewhandler.Executable;
import ch.ffhs.hdo.client.ui.einstellungen.OptionModel;
import ch.ffhs.hdo.infrastructure.ApplicationSettings;
import ch.ffhs.hdo.infrastructure.option.OptionFacade;
/**
 * Speichert die Konfigurationen mithilfe der OptionFassade auf die Datenbank.
 * 
 * @author Jonas Segessemann
 *
 */
public class OptionsSaveExecutable implements Executable {

	private OptionModel model;
/**
 * Konstruktor zur Erstellung des Objekts.
 * @param model Model welches persistent in die Datenbank gespeichert werden soll.
 */
	public OptionsSaveExecutable(OptionModel model) {
		this.model = model;

	}
/**
 * Speichert mithilfe der OptionFassade das Model in die Datenbank.
 */
	public void execute(Object arg) {

		ApplicationSettings.getInstance().saveInboxPath(model.getInboxPath());

		OptionFacade facade = new OptionFacade();
		facade.save(model);

	}

}
