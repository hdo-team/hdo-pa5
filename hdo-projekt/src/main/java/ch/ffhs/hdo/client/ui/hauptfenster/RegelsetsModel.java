package ch.ffhs.hdo.client.ui.hauptfenster;

import java.util.ArrayList;

import ch.ffhs.hdo.client.ui.base.Model;
import ch.ffhs.hdo.client.ui.regelset.RegelsetModel;

public class RegelsetsModel extends Model {

	private ArrayList<RegelsetModel> rulsetList;
	
	public void setRulsetList(ArrayList<RegelsetModel> rulsetList) {
		this.rulsetList = rulsetList;
	}
	
	public ArrayList<RegelsetModel> getRulsetList() {
		return rulsetList;
	}

}
