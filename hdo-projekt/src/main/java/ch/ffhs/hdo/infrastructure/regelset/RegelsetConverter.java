package ch.ffhs.hdo.infrastructure.regelset;

import java.io.File;
import static ch.ffhs.hdo.persistence.dto.RegelsetDto.RegelsetValues.RULESETID;
import static ch.ffhs.hdo.persistence.dto.RegelsetDto.RegelsetValues.RULESETNAME;
import static ch.ffhs.hdo.persistence.dto.RegelsetDto.RegelsetValues.TARGETDIR;
import static ch.ffhs.hdo.persistence.dto.RegelsetDto.RegelsetValues.FILENAME;
import static ch.ffhs.hdo.persistence.dto.RegelsetDto.RegelsetValues.RULESETSTATUS;
import static ch.ffhs.hdo.persistence.dto.RegelsetDto.RegelsetValues.PRIORITY;

import ch.ffhs.hdo.client.ui.regelset.RegelsetModel;
import ch.ffhs.hdo.persistence.dto.RegelsetDto;

public class RegelsetConverter {

	//ADRIAN  !! TEST SCHREIBEN
	
	public static RegelsetModel convert(RegelsetDto regelsetDto) {

		RegelsetModel regelsetModel = new RegelsetModel();

		/**
		* 		Warten auf RegelsetValues von Denis
		*
		*/
		regelsetModel.setId(regelsetDto.get(RULESETID));

		regelsetModel.setRulesetName(regelsetDto.get(RULESETNAME));
		
		regelsetModel.setTargetDirectory(regelsetDto.get(TARGETDIR));
		
		regelsetModel.setFilenameKonfiguration(regelsetDto.get(FILENAME));
		
		String stringStatus = regelsetDto.get(RULESETSTATUS);
		boolean booleanStatus = Boolean.valueOf(stringStatus);
		regelsetModel.setRuleActiv(booleanStatus);
		
		String intPriority = regelsetDto.get(PRIORITY);
		String priority = String.valueOf(intPriority);
		regelsetModel.setPriority(priority);

		return regelsetModel;
	}
	
	public static RegelsetDto convert(RegelsetModel regelsetModelOriginal) {

		RegelsetDto dto = new RegelsetDto();

		/**
		* Nur den Unterordner in die DB schreiben
		* Stimmt dies so?
		*/
		
		//String targetDirectory  = new File(regelsetModelOriginal.getTargetDirectory()).getName();

		/**
		* 		Warten auf RegelsetValues von Denis
		*/
	
		dto.put(RULESETID, regelsetModelOriginal.getId());
		dto.put(RULESETNAME, regelsetModelOriginal.getRulesetName());
		//dto.put(TARGETDIR, String.valueOf(getTargetDirectory());
		dto.put(TARGETDIR, regelsetModelOriginal.getTargetDirectory());
		dto.put(FILENAME, regelsetModelOriginal.getFilenameKonfiguration());
		dto.put(RULESETSTATUS, regelsetModelOriginal.isRuleActiv());
		dto.put(PRIORITY, regelsetModelOriginal.getPriority());
		
		return dto;
	}
}
