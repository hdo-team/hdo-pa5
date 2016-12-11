package ch.ffhs.hdo.infrastructure.regelset;

import java.util.ArrayList;
import java.util.List;

import ch.ffhs.hdo.client.ui.regelset.RegelModel;
import ch.ffhs.hdo.client.ui.regelset.RegelsetModel;
import ch.ffhs.hdo.domain.regel.AbstractRegel;
import ch.ffhs.hdo.domain.regel.Regelset;
import ch.ffhs.hdo.persistence.dto.RegelDto;
import ch.ffhs.hdo.persistence.dto.RegelsetDto;

public class RegelsetConverter {

	// ADRIAN !! TEST SCHREIBEN

	public static RegelsetModel convert(RegelsetDto regelsetDto) {

		RegelsetModel regelsetModel = new RegelsetModel();

		regelsetModel.setRulesetId(regelsetDto.getId());
		regelsetModel.setRulesetName(regelsetDto.getRulesetName());
		regelsetModel.setTargetDirectory(regelsetDto.getTargetDirectory());
		regelsetModel.setNewFilename(regelsetDto.getNewFilename());
		regelsetModel.setFilenameCounter(regelsetDto.getFilenameCounter());
		regelsetModel.setPriority(Integer.valueOf(regelsetDto.getPrority()));
		regelsetModel.setRuleActiv(regelsetDto.isActive());

		final ArrayList<RegelModel> regelModelList = new ArrayList<RegelModel>();

		final List<RegelDto> regeln = regelsetDto.getRegeln();
		for (RegelDto regeldto : regeln) {

			final RegelModel regelmodel = RegelConverter.convert(regeldto, regelsetModel.getRulesetId());
			regelModelList.add(regelmodel);
		}
		regelsetModel.setRuleModelList(regelModelList);

		return regelsetModel;

	}

	public static Regelset convertToRegelset(RegelsetDto regelsetDto) {

		
		if (regelsetDto.isActive()){
		 
		Regelset regelsetModel = new Regelset();

		regelsetModel.setPath(regelsetDto.getTargetDirectory());
		regelsetModel.setRenamePattern(regelsetDto.getNewFilename());
		regelsetModel.setFilenameCounter(regelsetDto.getFilenameCounter());
		

		final ArrayList<AbstractRegel> regelList = new ArrayList<AbstractRegel>();


		final List<RegelDto> regeln = regelsetDto.getRegeln();
		for (RegelDto regeldto : regeln) {

			final AbstractRegel regel = RegelConverter.convert(regeldto);
			regelList.add(regel);
		}
		regelsetModel.setRegeln(regelList);

		return regelsetModel;
		}
		return null;

	}

	public static RegelsetDto convert(RegelsetModel model) {

		RegelsetDto dto = new RegelsetDto();

		dto.setId(model.getRulesetId());
		dto.setRulesetName(model.getRulesetName());
		dto.setTargetDirectory(model.getTargetDirectory());
		dto.setNewFilename(model.getNewFilename());
		dto.setFilenameCounter(model.getFilenameCounter());
		dto.setPrority(model.getPriority());
		dto.setActive(model.isRuleActiv());

		final ArrayList<RegelDto> regeln = new ArrayList<RegelDto>();

		final List<RegelModel> ruleModelList = model.getRuleModelList();
		for (RegelModel regelModel : ruleModelList) {

			final RegelDto convert = RegelConverter.convert(regelModel, model.getRulesetId());
			regeln.add(convert);

		}

		dto.setRegeln(regeln);

		return dto;
	}
}
