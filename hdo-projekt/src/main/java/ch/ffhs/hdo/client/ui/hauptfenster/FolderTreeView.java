package ch.ffhs.hdo.client.ui.hauptfenster;

import java.awt.Dimension;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.util.Collections;
import java.util.ResourceBundle;
import java.util.Vector;

import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;

import ch.ffhs.hdo.client.ui.base.View;
import ch.ffhs.hdo.client.ui.hauptfenster.executable.FolderTreeUpdateOperation;
import ch.ffhs.hdo.client.ui.hauptfenster.executable.RegelsetTableUpdateOperation;
import ch.ffhs.hdo.infrastructure.ApplicationSettings;

public class FolderTreeView extends View<FolderModel> {

	private final String I18N = "hdo.main";
	private JPanel jPanel;
	private File inboxFolder;
	private JTree tree;

	public FolderTreeView(ResourceBundle resourceBundle, FolderModel folderModel) {
		super(resourceBundle);
		this.setModel(folderModel);
		initComponents();
	}

	private void initComponents() {
		createComponents();
		layoutForm();
		configureBindings();
	}

	private void createComponents() {
		jPanel = new JPanel();

		if (getModel().getInboxPath() != null) {
			inboxFolder = new File(getModel().getInboxPath());
		} else {
			inboxFolder = new File(getMessage(I18N + ".label.nofolderfound"));
		}

		tree = new JTree(addNodes(null, inboxFolder));
		jPanel.add(tree);
	}

	private void layoutForm() {

	}

	@Override
	public void configureBindings() {
		getModel().addPropertyChangeListener(new PropertyChangeListener() {

			public void propertyChange(PropertyChangeEvent evt) {
				if (evt.getPropertyName() == "updateView") {
					if (getModel().getUpdateView()) {
						getHandler().performOperationWithArgs(FolderTreeUpdateOperation.class, ApplicationSettings.getInstance().getInbox_path());
						//TODO: Tree neu aufbauen
						getModel().setUpdateView(false);
					}
				}

			}
		});
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
