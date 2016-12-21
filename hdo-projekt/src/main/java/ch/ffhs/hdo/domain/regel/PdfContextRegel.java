package ch.ffhs.hdo.domain.regel;

/**
 * Regel um Pdf zu ueberpruefen
 * 
 * @author Denis Bittante
 *
 */
public class PdfContextRegel extends AbstractRegel {

	public boolean verifizieren() {

		final ContextAttributeEnum contextAttribute = getContextAttribute();

		setCompareToValue(getModel().getPdfMetadata().get(contextAttribute));

		return mainCompare();

	}

}
