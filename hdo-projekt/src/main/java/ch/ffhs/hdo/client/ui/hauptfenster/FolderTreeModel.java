package ch.ffhs.hdo.client.ui.hauptfenster;

import java.io.File;
import java.util.Collections;
import java.util.Vector;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import ch.ffhs.hdo.client.ui.base.Model;

public class FolderTreeModel extends Model {
	private File inboxFolder;
	private boolean updateView = false;
	private DefaultMutableTreeNode treeNode;
	private DefaultTreeModel treeModel;

	public FolderTreeModel(String inboxPath) {

		if (inboxPath != null) {
			this.inboxFolder = new File(inboxPath);
		} else {
			this.inboxFolder = new File("nofolderfound");
		}
		setTreeNode(inboxFolder);
		treeModel = new DefaultTreeModel(getTreeNode());

	}

	public DefaultTreeModel getTreeModel() {
		return treeModel;
	}

	public String getInboxPath() {
		return inboxFolder.getAbsolutePath();
	}

	public void setInboxPath(File inboxFolder) {
		this.inboxFolder = inboxFolder;
		setTreeNode(inboxFolder);
		treeModel = new DefaultTreeModel(getTreeNode());
	}

	public void setUpdateView(boolean updateView) {

		boolean oldValue = this.updateView;
		this.updateView = updateView;
		firePropertyChange("updateView", oldValue, updateView);
	}

	public boolean getUpdateView() {

		return updateView;
	}

	public DefaultMutableTreeNode getTreeNode() {
		return treeNode;
	}

	public void setTreeNode(File inboxFolder) {
		treeNode = addNodes(null, inboxFolder);
	}

	public DefaultMutableTreeNode addNodes(DefaultMutableTreeNode curTop, File dir) {
		String curPath = dir.getPath();
		DefaultMutableTreeNode curDir = new DefaultMutableTreeNode(curPath);
		if (curTop != null) { // should only be null at root
			curTop.add(curDir);
		}
		Vector ol = new Vector();
		String[] tmp = dir.list();
		try {
			for (int i = 0; i < tmp.length; i++)
				ol.addElement(tmp[i]);
		} catch (Exception e) {
			System.out.println(e);
		}
		Collections.sort(ol, String.CASE_INSENSITIVE_ORDER);
		File f;
		Vector files = new Vector();
		// Make two passes, one for Dirs and one for Files. This is #1.
		for (int i = 0; i < ol.size(); i++) {
			String thisObject = (String) ol.elementAt(i);
			String newPath;
			if (curPath.equals("."))
				newPath = thisObject;
			else
				newPath = curPath + File.separator + thisObject;
			if ((f = new File(newPath)).isDirectory())
				addNodes(curDir, f);
			else
				files.addElement(thisObject);
		}

		return curDir;
	}

}
