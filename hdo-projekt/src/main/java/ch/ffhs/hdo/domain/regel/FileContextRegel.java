package ch.ffhs.hdo.domain.regel;

/**
 * Regel um Files zu überpüfen
 * 
 * @author Denis Bittante
 *
 */
public class FileContextRegel extends AbstractRegel {

	public boolean verifizieren() {

		final ContextAttributeEnum contextAttribute = getContextAttribute();

		setCompareToValue(getModel().getFileMetadata().get(contextAttribute));

		return mainCompare();

	}

}
