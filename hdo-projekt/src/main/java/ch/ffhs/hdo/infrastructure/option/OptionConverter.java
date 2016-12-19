package ch.ffhs.hdo.infrastructure.option;

import static ch.ffhs.hdo.persistence.dto.OptionDto.OptionValues.AUTOMODUS;
import static ch.ffhs.hdo.persistence.dto.OptionDto.OptionValues.INBOXPATH;
import static ch.ffhs.hdo.persistence.dto.OptionDto.OptionValues.INTERVALL;

import org.apache.commons.lang3.StringUtils;

import ch.ffhs.hdo.client.ui.einstellungen.OptionModel;
import ch.ffhs.hdo.persistence.dto.OptionDto;

public class OptionConverter {

	public static OptionModel convert(OptionDto optionDto) {

		OptionModel optionModel = new OptionModel();

		String charAutomodus = optionDto.get(AUTOMODUS);

		boolean automodus = Boolean.valueOf(charAutomodus);
		optionModel.setAutoModus(automodus);


		String charIntervall = optionDto.get(INTERVALL);
		if (StringUtils.isNumeric(charIntervall)) {
			int intervall = Integer.valueOf(charIntervall);
			optionModel.setIntervall(intervall);
		}

		return optionModel;
	}

	public static OptionDto convert(OptionModel optionModelOrginal) {

		OptionDto dto = new OptionDto();

		dto.put(INTERVALL, String.valueOf(optionModelOrginal.getIntervall()));
		dto.put(AUTOMODUS, optionModelOrginal.isAutoModus());
		return dto;

	}

}
