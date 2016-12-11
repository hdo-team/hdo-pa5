package ch.ffhs.hdo.domain.regel;

public class FileContextRegel extends AbstractRegel {

	public boolean verifizieren() {
		
		final ContextAttributeEnum contextAttribute = getContextAttribute();

		setCompareToValue(getModel().getFileMetadata().get(contextAttribute));

		return mainCompare();

	}

}
