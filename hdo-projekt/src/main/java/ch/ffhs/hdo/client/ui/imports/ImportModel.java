package ch.ffhs.hdo.client.ui.imports;

import ch.ffhs.hdo.client.ui.base.Model;
/**
 * 
 * @author Adrian Perez Rodriguez
 */
public class ImportModel extends Model {
	
	private String filePath; // FRAGE
	private String inboxPath;
	private boolean autoModus;
	private int intervall;
	
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public String getInboxPath() {
		return inboxPath;
	}
	public void setInboxPath(String inboxPath) {
		this.inboxPath = inboxPath;
	}
	public boolean isAutoModus() {
		return autoModus;
	}
	public void setAutoModus(boolean autoModus) {
		this.autoModus = autoModus;
	}
	public int getIntervall() {
		return intervall;
	}
	public void setIntervall(int intervall) {
		this.intervall = intervall;
	}
}