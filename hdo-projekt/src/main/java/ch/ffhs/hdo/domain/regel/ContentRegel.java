package ch.ffhs.hdo.domain.regel;

/**
 * Regel um den Inhalt einer Datei zu ueberpruefen
 * 
 * @author Denis Bittante
 *
 */
public class ContentRegel extends AbstractRegel {

	public boolean verifizieren() {

		setCompareToValue(getModel().getContent());

		return mainCompare();

	}

}
