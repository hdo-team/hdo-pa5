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
		* 
		* 		TODO: Daniel: ich habe TEMPorär und provisorisch die RulesetId/Priority von String nach Integer umgwandelt (ich denke Denis passt RegelsetDto noch an...
		*	                  (damit es auch compilierbar ist mit rulsetid/priroty als Integer) 
		*
		*/
		regelsetModel.setRulesetId(Integer.valueOf(regelsetDto.get(RULESETID)));

		regelsetModel.setRulesetName(regelsetDto.get(RULESETNAME));
		
		regelsetModel.setTargetDirectory(regelsetDto.get(TARGETDIR));
		
		regelsetModel.setFilenameKonfiguration(regelsetDto.get(FILENAME));
		
		String stringStatus = regelsetDto.get(RULESETSTATUS);
		boolean booleanStatus = Boolean.valueOf(stringStatus);
		regelsetModel.setRuleActiv(booleanStatus);

		regelsetModel.setPriority(Integer.valueOf(regelsetDto.get(PRIORITY)));

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
		* 
		* 		TODO: Daniel: ich habe TEMPorär und provisorisch die RulesetId/Priority von Integer nach String umgwandelt (ich denke Denis passt RegelsetDto noch an...
		*	                  (damit es auch compilierbar ist mit rulsetid/priroty als Integer) 
		*/
		
		dto.put(RULESETID, regelsetModelOriginal.getRulesetId().toString());
		dto.put(RULESETNAME, regelsetModelOriginal.getRulesetName());
		//dto.put(TARGETDIR, String.valueOf(getTargetDirectory());
		dto.put(TARGETDIR, regelsetModelOriginal.getTargetDirectory());
		dto.put(FILENAME, regelsetModelOriginal.getFilenameKonfiguration());
		dto.put(RULESETSTATUS, regelsetModelOriginal.isRuleActiv());
		dto.put(PRIORITY, regelsetModelOriginal.getPriority().toString());
		
		return dto;
	}
}
