package ch.ffhs.hdo.infrastructure.regelset;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import ch.ffhs.hdo.client.ui.regelset.RegelsetModel;
import ch.ffhs.hdo.persistence.dto.RegelsetDto;

@Ignore
public class RegelsetConverterTest {

	RegelsetDto regelsetDtoConverted;
	RegelsetDto regelsetDtoOriginal;
	RegelsetModel regelsetModelConverted;
	RegelsetModel regelsetModelOriginal;

	@Before
	public void setUp() throws Exception {

		regelsetDtoOriginal = new RegelsetDto();
		regelsetModelOriginal = new RegelsetModel();

	}

	/**
	 * Tested wenn ein leeres DTO convertiert wird, die standard Daten im Model
	 * vorhanden sind.
	 */
	@Test
	public void testEmtpyOriginal() {

		regelsetModelConverted = RegelsetConverter.convert(regelsetDtoOriginal);
		thenModelDefaultValues();

	}

	/**
	 * Tested wenn ein korruptes DTO convertiert wird, die standard Daten im
	 * Model vorhanden sind.
	 */
	@Test
	public void testDtoWithCorruptEntries() {

		whenFilledWithDto("", "", "", -1, true);
		regelsetModelConverted = RegelsetConverter.convert(regelsetDtoOriginal);
		thenModelRulesetName("");
		thenModelTargetDirectory("");
		thenModelNewFilename("");
		thenModelFilenameCounter((long) -1);
		thenModelIsActive(true);
	}

	/**
	 * Tested wenn ein gefuelltes DTO convertiert wird, die richtigen Daten im
	 * Model vorhanden sind.
	 */
	@Test
	public void testFilledDtoOrginal() {

		whenFilledWithDto("test", "C:/Temp", "test_neu", 1, true);
		regelsetModelConverted = RegelsetConverter.convert(regelsetDtoOriginal);
		thenModelRulesetName("test");
		thenModelTargetDirectory("C:/Temp");
		thenModelNewFilename("test_neu");
		thenModelFilenameCounter((long) 1);
		thenModelIsActive(true);
	}

	@Test
	public void testEmtpyModel() {

		regelsetDtoConverted = RegelsetConverter.convert(regelsetModelOriginal);
		thenDtoDefaultValues();

	}

	@Test
	public void testModelWithCorruptInformation() {

		whenFilledWithModel("", "", "", -1, false);
		regelsetDtoConverted = RegelsetConverter.convert(regelsetModelOriginal);
		thenDtoRulesetName("");
		thenDtoTargetDirectory("");
		thenDtoNewFilename("");
		thenDtoFilenameCounter("-1");
		thenDtoIsActive("false");
	}

	private void thenDtoRulesetName(String rulesetName) {
		assertEquals(rulesetName, regelsetDtoConverted.getRulesetName());

	}
	
	private void thenDtoTargetDirectory(String targetDirectory) {
		assertEquals(targetDirectory, regelsetDtoConverted.getTargetDirectory());

	}
	
	private void thenDtoNewFilename(String newFilename) {
		assertEquals(newFilename, regelsetDtoConverted.getNewFilename());

	}
	
	private void thenDtoFilenameCounter(String filenameCounter) {
		assertEquals(filenameCounter, regelsetDtoConverted.getFilenameCounter());

	}

	private void thenDtoIsActive(String b) {
		assertEquals(b, regelsetDtoConverted.isActive());

	}


	@Test
	public void testFilledModelOrginal() {

		whenFilledWithModel("test", "C:/Temp", "test_neu", 1, true);
		regelsetDtoConverted = RegelsetConverter.convert(regelsetModelOriginal);
		thenDtoRulesetName("test");
		thenDtoTargetDirectory("C:/Temp");
		thenDtoNewFilename("test_neu");
		thenDtoFilenameCounter("1");
		thenDtoIsActive("true");
	}

	private void thenModelDefaultValues() {
		assertEquals(null, regelsetModelConverted.getRulesetName());
		assertEquals(null, regelsetModelConverted.getTargetDirectory());
		assertEquals(null, regelsetModelConverted.getNewFilename());
		assertEquals(null, regelsetModelConverted.getFilenameCounter());
		assertEquals(false, regelsetModelConverted.isRuleActiv());
	}

	private void thenDtoDefaultValues() {
		assertEquals(null, regelsetDtoConverted.getRulesetName());
		assertEquals(null, regelsetDtoConverted.getTargetDirectory());
		assertEquals(null, regelsetDtoConverted.getNewFilename());
		assertEquals("-1", regelsetDtoConverted.getFilenameCounter());
		assertEquals("false", regelsetDtoConverted.isActive());
	}

	private void thenModelRulesetName(String rulesetName) {
		assertEquals(rulesetName, regelsetModelConverted.getRulesetName());

	}
	
	private void thenModelTargetDirectory(String targetDirectory) {
		assertEquals(targetDirectory, regelsetModelConverted.getTargetDirectory());

	}
	
	private void thenModelNewFilename(String newFilename) {
		assertEquals(newFilename, regelsetModelConverted.getNewFilename());

	}
	
	private void thenModelFilenameCounter(Long filenameCounter) {
		assertEquals(filenameCounter, regelsetModelConverted.getFilenameCounter());

	}
	
	private void thenModelIsActive(boolean ruleActiv) {
		assertEquals(ruleActiv, regelsetModelConverted.isRuleActiv());

	}

	private void whenFilledWithModel(String rulesetName, String targetDirectory, String newFilename, long filenameCounter,
			boolean ruleActiv) {
		regelsetModelOriginal.setRulesetName(rulesetName);
		regelsetModelOriginal.setTargetDirectory(targetDirectory);
		regelsetModelOriginal.setNewFilename(newFilename);
		regelsetModelOriginal.setFilenameCounter(filenameCounter);
		regelsetModelOriginal.setRuleActiv(ruleActiv);
	}

	private void whenFilledWithDto(String rulesetName, String targetDirectory, String newFilename, long filenameCounter,
			boolean ruleActiv) {
		regelsetDtoOriginal.setRulesetName(rulesetName);
		regelsetDtoOriginal.setTargetDirectory(targetDirectory);
		regelsetDtoOriginal.setNewFilename(newFilename);
		regelsetDtoOriginal.setFilenameCounter(filenameCounter);
		regelsetDtoOriginal.setActive(ruleActiv);
	}

}
