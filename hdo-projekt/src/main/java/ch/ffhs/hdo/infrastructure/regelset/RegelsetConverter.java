package ch.ffhs.hdo.infrastructure.regelset;

import java.util.ArrayList;
import java.util.List;

import ch.ffhs.hdo.client.ui.regelset.RegelModel;
import ch.ffhs.hdo.client.ui.regelset.RegelsetModel;
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

		RegelConverter converter = new RegelConverter();

		for (RegelDto regeldto : regelsetDto.getRegeln()) {

			final RegelModel regelmodel = converter.convert(regeldto);
			regelModelList.add(regelmodel);
		}
		regelsetModel.setRuleModelList(regelModelList);

		return regelsetModel;

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
		RegelConverter converter = new RegelConverter();
		for (RegelModel regelModel : ruleModelList) {

			final RegelDto convert = converter.convert(regelModel);
			regeln.add(convert);

		}

		dto.setRegeln(regeln);

		return dto;
	}
}
