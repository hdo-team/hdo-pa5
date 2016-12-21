package ch.ffhs.hdo.domain.regel;

import java.util.HashMap;

/**
 * Regel um Pdf zu ueberpruefen
 * 
 * @author Denis Bittante
 *
 */
public class PdfContextRegel extends AbstractRegel {

	public boolean verifizieren() {

		final ContextAttributeEnum contextAttribute = getContextAttribute();

		final HashMap<ContextAttributeEnum, Object> pdfMetadata = getModel().getPdfMetadata();
		if (pdfMetadata != null) {
			setCompareToValue(pdfMetadata.get(contextAttribute));

			return mainCompare();
		} else {
			return false;
		}

	}

}
