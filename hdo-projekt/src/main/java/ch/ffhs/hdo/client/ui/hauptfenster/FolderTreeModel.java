package ch.ffhs.hdo.client.ui.hauptfenster;

import java.awt.Component;
import java.io.File;

import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ch.ffhs.hdo.client.ui.base.Model;
import ch.ffhs.hdo.infrastructure.service.SortService;

/**
 * Model für die Verzeichnisansicht im Hauptfenster.
 * 
 * @author Jonas Segessemann
 *
 */
public class FolderTreeModel extends Model {
	private static Logger LOGGER = LogManager.getLogger(SortService.class);
	private File inboxFolder;
	private boolean updateView = false;
	private DefaultMutableTreeNode treeNode;
	private DefaultTreeModel treeModel;
	private String HIDDEN_FOLDER = ".db";

	/**
	 * Erstellte das Model Objekt.
	 * 
	 * @param inboxPath
	 *            Verzeichnis, welches als Root verwendet werden soll
	 */
	public FolderTreeModel(String inboxPath) {

		if (inboxPath != null) {
			this.inboxFolder = new File(inboxPath);
		} else {
			this.inboxFolder = new File("No-Inbox-Folder");
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
		treeNode = addNodes(inboxFolder);
	}

	/**
	 * Erstellt rektursiv einen Baum aus Verzeichnisse unter dem Inbox Pfad.
	 * 
	 * @param dir
	 *            Verzeichnis als File.
	 * @return Aktueller Baum mit neuem
	 */
	public DefaultMutableTreeNode addNodes(File dir) {
		DefaultMutableTreeNode node = new DefaultMutableTreeNode(dir);

		if (dir.listFiles() != null) {
			for (File file : dir.listFiles()) {
				if (file.isDirectory() && !file.getName().equals(HIDDEN_FOLDER)) {
					node.add(addNodes(file));
				}
			}
		}
		return node;
	}

}

/**
 * Erstellt einen TreeCellRenderer um spezifische Anpassungen in der Ansicht
 * ermöglichen.
 * 
 * @author Jonas Segessemann
 *
 */
class FileTreeCellRenderer extends DefaultTreeCellRenderer {
	@Override
	public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel, boolean expanded, boolean leaf,
			int row, boolean hasFocus) {
		if (value instanceof DefaultMutableTreeNode) {
			value = ((DefaultMutableTreeNode) value).getUserObject();
			if (value instanceof File) {
				value = ((File) value).getName();
			}
		}
		super.setLeafIcon(super.getClosedIcon());
		;
		return super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);
	}

}