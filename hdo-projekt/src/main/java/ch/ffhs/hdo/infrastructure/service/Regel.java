package ch.ffhs.hdo.infrastructure.service;

import ch.ffhs.hdo.domain.document.DocumentModel;

public interface Regel  {

	void setModel(DocumentModel model);
	
	boolean verifizieren();
	
}
