package ch.ffhs.hdo.infrastructure.regelset;

import ch.ffhs.hdo.client.ui.regelset.RegelModel;
import ch.ffhs.hdo.client.ui.regelset.RegelModel.ComparisonTypeEnum;
import ch.ffhs.hdo.client.ui.regelset.RegelModel.ContextAttributeEnum;
import ch.ffhs.hdo.client.ui.regelset.RegelModel.ContextTypeEnum;
import ch.ffhs.hdo.persistence.dto.RegelDto;

public class RegelConverter {

	public RegelModel convert(RegelDto dto) {

		RegelModel model = new RegelModel();

		model.setId(dto.getId());
		model.setContextType(ContextTypeEnum.valueOf(dto.getContextType()));
		model.setContextAttribute(ContextAttributeEnum.valueOf(dto.getContextAttribute()));
		model.setComparisonType(ComparisonTypeEnum.valueOf(dto.getCompareType()));
		model.setCompareValue(dto.getCompareValue());

		return model;

	}

	public RegelDto convert(RegelModel model, Integer id) {

		final RegelDto regelDto = new RegelDto();
		
		regelDto.setId(model.getId());
		regelDto.setCompareType(model.getComparisonType().toString());
		regelDto.setCompareValue(model.getCompareValue());
		regelDto.setCompareType(model.getComparisonType().toString());
		regelDto.setContextAttribute(model.getContextAttribute().toString());
		regelDto.setRulesetId(id);

		return regelDto;

	}

}
