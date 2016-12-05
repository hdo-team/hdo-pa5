package ch.ffhs.hdo.client.ui.hauptfenster;

import java.awt.Dimension;
import java.io.File;
import java.util.Collections;
import java.util.ResourceBundle;
import java.util.Vector;

import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;

import ch.ffhs.hdo.client.ui.base.View;

public class FolderTreeView extends View<FolderModel> {

	private JPanel jPanel;
	private File inboxFolder;
	private JTree tree;

	public FolderTreeView(ResourceBundle resourceBundle) {
		super(resourceBundle);
		initComponents();
	}

	private void initComponents() {
		createComponents();
		layoutForm();
		configureBindings();
	}

	private void createComponents() {
		jPanel = new JPanel();
		inboxFolder = new File("C:\\Users\\jonas\\hdo");
		tree = new JTree(addNodes(null, inboxFolder));
		 jPanel.add(tree);
	}

	private void layoutForm() {

	}

	@Override
	public void configureBindings() {
		// TODO Auto-generated method stub

	}

	public JPanel getPanel() {
		return jPanel;
	}

	/** Add nodes from under "dir" into curTop. Highly recursive. */
	DefaultMutableTreeNode addNodes(DefaultMutableTreeNode curTop, File dir) {
		String curPath = dir.getPath();
		DefaultMutableTreeNode curDir = new DefaultMutableTreeNode(curPath);
		if (curTop != null) { // should only be null at root
			curTop.add(curDir);
		}
		Vector ol = new Vector();
		String[] tmp = dir.list();
		for (int i = 0; i < tmp.length; i++)
			ol.addElement(tmp[i]);
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
		// Pass two: for files.
		for (int fnum = 0; fnum < files.size(); fnum++)
			curDir.add(new DefaultMutableTreeNode(files.elementAt(fnum)));
		return curDir;
	}
}
