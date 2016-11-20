package ch.ffhs.hdo.infrastructure.regelset;

/**
import static ch.ffhs.hdo.persistence.dto.RegelsetDto.RegelsetValues.AUTOMODUS;
import static ch.ffhs.hdo.persistence.dto.RegelsetDto.RegelsetValues.INBOXPATH;
import static ch.ffhs.hdo.persistence.dto.RegelsetDto.RegelsetValues.INTERVALL;

import org.apache.commons.lang3.StringUtils;

import ch.ffhs.hdo.client.ui.regelset.RegelsetModel;
import ch.ffhs.hdo.persistence.dto.RegelsetDto;
*/
public class RegelsetConverter {

	/**
	public static RegelsetModel convert(RegelsetDto RegelsetDto) {

		RegelsetModel RegelsetModel = new RegelsetModel();
		
		String charAutomodus = RegelsetDto.get(AUTOMODUS);

		boolean automodus = Boolean.valueOf(charAutomodus);
		
		RegelsetModel.setAutoModus(automodus);
		RegelsetModel.setInboxPath(RegelsetDto.get(INBOXPATH));

		String charIntervall = RegelsetDto.get(INTERVALL);
		if (StringUtils.isNumeric(charIntervall)) {
			int intervall = Integer.valueOf(charIntervall);
			RegelsetModel.setIntervall(intervall);
		}

		return RegelsetModel;
	}

	public static RegelsetDto convert(RegelsetModel RegelsetModelOrginal) {

		RegelsetDto dto = new RegelsetDto();
		
		dto.put(INBOXPATH, RegelsetModelOrginal.getInboxPath());
		dto.put(INTERVALL, String.valueOf(RegelsetModelOrginal.getIntervall()));
		dto.put(AUTOMODUS, RegelsetModelOrginal.isAutoModus());
		return dto;

	}*/

}
