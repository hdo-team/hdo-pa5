package ch.ffhs.hdo.infrastructure.option;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import ch.ffhs.hdo.client.ui.einstellungen.OptionModel;
import ch.ffhs.hdo.persistence.dto.OptionDto;
import static ch.ffhs.hdo.persistence.dto.OptionDto.OptionValues.*;

public class OptionConverterTest {

	private final static String ACTUAL_PATH = "C:/temp";

	OptionDto optionDtoConverted;
	OptionDto optionDtoOriginal;
	OptionModel optionModelConverted;
	OptionModel optionModelOrginal;

	@Before
	public void setUp() throws Exception {

		optionDtoOriginal = new OptionDto();
		optionModelOrginal = new OptionModel();

	}

	/**
	 * Tested wenn ein leeres DTO convertiert wird, die standard Daten im Model
	 * vorhanden sind.
	 */
	@Test
	public void testEmtpyOriginal() {

		optionModelConverted = OptionConverter.convert(optionDtoOriginal);
		thenModelDefaultValues();

	}

	/**
	 * Tested wenn ein korruptes DTO convertiert wird, die standard Daten im
	 * Model vorhanden sind.
	 */
	@Test
	public void testDtoWithCorruptEntries() {

		whenFilledWith("", "", "");
		optionModelConverted = OptionConverter.convert(optionDtoOriginal);
		thenModelIntervall(-1);
		thenModelAutoModus(false);
		thenModelPathEmtpy();
	}

	private void thenModelPathEmtpy() {
		assertEquals("", optionModelConverted.getInboxPath());
	}

	/**
	 * Tested wenn ein gefülltes DTO convertiert wird, die richtigen Daten im
	 * Model vorhanden sind.
	 */
	@Test
	public void testFilledDtoOrginal() {

		whenFilledWith(ACTUAL_PATH, "true", "3600");
		optionModelConverted = OptionConverter.convert(optionDtoOriginal);
		thenModelIntervall(3600);
		thenModelAutoModus(true);
		thenPathFilled();
	}

	@Test
	public void testEmtpyModel() {

		optionDtoConverted = OptionConverter.convert(optionModelOrginal);
		thenDtoDefaultValues();

	}

	@Test
	public void testModelWithCorruptInformation() {

		whenFilledWith(null, true, -1);
		optionDtoConverted = OptionConverter.convert(optionModelOrginal);
		thenDtoIntervall("-1");
		thenDtoAutoModus("true");
		thenDtoPathEmtpy();
	}

	private void thenDtoIntervall(String intervall) {
		assertEquals(intervall, optionDtoConverted.get(INTERVALL));

	}

	private void thenDtoAutoModus(String b) {
		assertEquals(b, optionDtoConverted.get(AUTOMODUS));

	}

	private void thenDtoPathEmtpy() {
		assertEquals(null, optionDtoConverted.get(INBOXPATH));

	}

	private void thenDtoPathFilled() {
		assertEquals(ACTUAL_PATH, optionDtoConverted.get(INBOXPATH));

	}

	@Test
	public void testFilledModelOrginal() {

		whenFilledWith(ACTUAL_PATH, true, 3600);
		optionDtoConverted = OptionConverter.convert(optionModelOrginal);
		thenDtoIntervall("3600");
		thenDtoAutoModus("true");
		thenDtoPathFilled();
	}

	private void thenModelAutoModus(Boolean b) {
		assertEquals(b, optionModelConverted.isAutoModus());
	}

	private void thenModelDefaultValues() {
		assertEquals(null, optionModelConverted.getInboxPath());
		assertEquals(-1, optionModelConverted.getIntervall());
		assertEquals(false, optionModelConverted.isAutoModus());
	}

	private void thenDtoDefaultValues() {
		assertEquals(null, optionDtoConverted.get(INBOXPATH));
		assertEquals("-1", optionDtoConverted.get(INTERVALL));
		assertEquals("false", optionDtoConverted.get(AUTOMODUS));
	}

	private void thenModelIntervall(int intervall) {
		assertEquals(intervall, optionModelConverted.getIntervall());

	}

	private void thenPathFilled() {
		assertEquals(ACTUAL_PATH, optionModelConverted.getInboxPath());

	}

	private void whenFilledWith(String actualPath, boolean automodus, int intervall) {
		optionModelOrginal.setAutoModus(automodus);
		optionModelOrginal.setIntervall(intervall);
		optionModelOrginal.setInboxPath(actualPath);

	}

	private void whenFilledWith(String actualPath, String automodus, String intervall) {
		optionDtoOriginal.put(AUTOMODUS, automodus);
		optionDtoOriginal.put(INTERVALL, intervall);
		optionDtoOriginal.put(INBOXPATH, actualPath);

	}

}
