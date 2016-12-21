package ch.ffhs.hdo.infrastructure.option;

import static ch.ffhs.hdo.persistence.dto.OptionDto.OptionValues.AUTOMODUS;
import static ch.ffhs.hdo.persistence.dto.OptionDto.OptionValues.INBOXPATH;
import static ch.ffhs.hdo.persistence.dto.OptionDto.OptionValues.INTERVALL;

import org.apache.commons.lang3.StringUtils;

import ch.ffhs.hdo.client.ui.einstellungen.OptionModel;
import ch.ffhs.hdo.persistence.dto.OptionDto;

/**
 * Converter für OptionModel zu Option Dto
 * 
 * @author Denis Bittante
 *
 */
public class OptionConverter {
	/**
	 * Convertiert {@link OptionDto} nach {@link OptionModel}
	 * 
	 * @param optionDto
	 *            see {@link OptionDto}
	 * @return see {@link OptionModel}
	 */
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

	/**
	 * Convertiert {@link OptionModel} nach {@link OptionDto}
	 * 
	 * @param optionModel
	 *            see {@link OptionModel}
	 * @return see {@link OptionDto}
	 */
	public static OptionDto convert(OptionModel optionModel) {

		OptionDto dto = new OptionDto();

		dto.put(INTERVALL, String.valueOf(optionModel.getIntervall()));
		dto.put(AUTOMODUS, optionModel.isAutoModus());
		return dto;

	}

}
