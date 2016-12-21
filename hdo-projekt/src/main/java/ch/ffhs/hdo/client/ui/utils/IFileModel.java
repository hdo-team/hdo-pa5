package ch.ffhs.hdo.client.ui.utils;

/**
 * Ist das Interface um den Filechooser an ein Model zu binden ohne das Model
 * weiter aufboren zu muessen.
 * 
 * @author Denis Bittante
 *
 */
public interface IFileModel {

	/**
	 * Getter fuer das FilePath-Attribute
	 * 
	 * @return {@link String} FilePath-Attribute
	 */
	String getFilePath();

	/**
	 * Setter fuer das FilePath-Attribute
	 * 
	 * @param newValue
	 */
	void setFilePath(String newValue);

}
