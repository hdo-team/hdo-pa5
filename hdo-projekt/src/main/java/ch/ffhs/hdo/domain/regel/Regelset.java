package ch.ffhs.hdo.domain.regel;

import java.io.File;
import java.util.List;

import ch.ffhs.hdo.domain.document.DocumentModel;

public class Regelset {

	private List<AbstractRegel> regeln;
	private String path;
	private long filenameCounter;

	public long getFilenameCounter() {
		return filenameCounter;
	}

	public void setFilenameCounter(long filenameCounter) {
		this.filenameCounter = filenameCounter;
	}

	private String renamePattern;

	public String getRenamePattern() {
		return renamePattern;
	}

	public void setRenamePattern(String renamePattern) {
		this.renamePattern = renamePattern;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public void setRegeln(List<AbstractRegel> regeln) {
		this.regeln = regeln;
	}

	public boolean verfizieren(DocumentModel model) {

		boolean result = false;
		for (AbstractRegel regel : regeln) {
			regel.setModel(model);
			final boolean verifizieren = regel.verifizieren();
			result = verifizieren;
			if (!verifizieren) {
				return false;
			}

		}
		return result;
	}

	public void rename(DocumentModel documentModel) {
		// TODO: to implement
	}

	public String getPath() {
		
		if (!this.path.endsWith("\\")){
			return this.path +File.separator;
			
		}
		
		return this.path;
	}

}
