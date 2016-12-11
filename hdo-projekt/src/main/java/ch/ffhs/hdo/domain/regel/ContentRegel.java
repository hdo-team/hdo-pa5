package ch.ffhs.hdo.domain.regel;

public class ContentRegel extends AbstractRegel {

	public boolean verifizieren() {

		setCompareToValue(getModel().getContent());

		return mainCompare();

	}

}
