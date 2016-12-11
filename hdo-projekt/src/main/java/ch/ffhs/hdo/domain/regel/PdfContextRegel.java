package ch.ffhs.hdo.domain.regel;

public class PdfContextRegel extends AbstractRegel {

	public boolean verifizieren() {

		final ContextAttributeEnum contextAttribute = getContextAttribute();

		setCompareToValue(getModel().getPdfMetadata().get(contextAttribute));

		return mainCompare();

	}

}
