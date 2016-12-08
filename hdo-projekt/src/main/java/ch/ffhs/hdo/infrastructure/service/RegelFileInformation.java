package ch.ffhs.hdo.infrastructure.service;

import java.util.HashMap;
import java.util.Set;

import ch.ffhs.hdo.client.ui.regelset.RegelModel.ComparisonTypeEnum;
import ch.ffhs.hdo.domain.document.DocumentModel;
import ch.ffhs.hdo.infrastructure.service.util.FileHandling.FileMetaData;
import ch.ffhs.hdo.persistence.dto.RegelDto;

public class RegelFileInformation extends RegelDto implements Regel {

	private String contextAttribute;
	private ComparisonTypeEnum compareType;
	private String compareValue;

	private DocumentModel documentModel;

	public boolean verifizieren() {
		
		 final HashMap<FileMetaData, Object> fileMetadata = documentModel.getFileMetadata();
		
		 FileMetaData f;

		 
		 final Class class1 = Integer.class;
		
		// final Class<?> forName = Class.forName(class1.getName());
		 
		// final Object cast = forName.cast(1);
		// compare((class1).cast(1) , compareType, compareValue )
		 
		
		
		
		
		 return true;
		
		
		
	}

	private void compare(Integer integer, ComparisonTypeEnum compareType2, String compareValue2) {

		switch (compareType2) {
		case COMPARISON_EQUAL:
			 integer.compareTo(new Integer(0));
			break;

		default:
			break;
		}
		
	}

	public void setModel(DocumentModel model) {
		// TODO Auto-generated method stub

	}

}
