package ch.ffhs.hdo.client.ui.einstellungen.executable;

import java.io.File;

import ch.ffhs.hdo.client.ui.base.ParamChecker;
import ch.ffhs.hdo.client.ui.base.viewhandler.Executable;
import ch.ffhs.hdo.client.ui.einstellungen.OptionModel;
import ch.ffhs.hdo.infrastructure.ApplicationSettings;
import ch.ffhs.hdo.infrastructure.option.OptionFacade;
import ch.ffhs.hdo.persistence.jdbc.InitDatabase;

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
	 * 
	 * @param model
	 *            Model welches persistent in die Datenbank gespeichert werden
	 *            soll.
	 */
	public OptionsSaveExecutable(OptionModel model) {
		this.model = model;

	}

	/**
	 * Speichert mithilfe der OptionFassade das Model in die Datenbank.
	 */
	public void execute(Object arg) {
		final String inboxPath = model.getInboxPath();
		ParamChecker.notEmpty(inboxPath, "inboxPath");

		final String inbox_path_old = ApplicationSettings.getInstance().getInbox_path();
		ApplicationSettings.getInstance().saveInboxPath(inboxPath);
		if (inbox_path_old == null && inboxPath != null) {
			new InitDatabase().createDatabase();
		} else if (inboxPath != null && inbox_path_old != null && !inbox_path_old.equals(inboxPath)) {
			new InitDatabase().createDatabase();
		}

		OptionFacade facade = new OptionFacade();
		facade.save(model);

	}

}
