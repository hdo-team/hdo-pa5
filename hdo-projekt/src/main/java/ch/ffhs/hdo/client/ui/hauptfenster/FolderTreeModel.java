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
 * Erstellt einen TreeCellRenderer um spezifische Anpassungen in der Ansicht
 * ermoeglichen.
 * 
 * @author Jonas Segessemann
 *
 */
class FileTreeCellRenderer extends DefaultTreeCellRenderer {
	private static final long serialVersionUID = 5778773605340605661L;

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

/**
 * Model fuer die Verzeichnisansicht im Hauptfenster.
 * 
 * @author Jonas Segessemann
 *
 */
public class FolderTreeModel extends Model {
	private static Logger LOGGER = LogManager.getLogger(SortService.class);
	private String HIDDEN_FOLDER = ".db";
	private File inboxFolder;
	private DefaultTreeModel treeModel;
	private DefaultMutableTreeNode treeNode;
	private boolean updateView = false;

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

	/**
	 * Getter fuer Inbox Pfad
	 * 
	 * @return Inbox Pfad
	 */
	public String getInboxPath() {
		return inboxFolder.getAbsolutePath();
	}

	/**
	 * Getter fuer Tree Model
	 * 
	 * @return see {@link DefaultTreeModel}
	 */
	public DefaultTreeModel getTreeModel() {
		return treeModel;
	}

	/**
	 * Getter fuer Tree Node
	 * 
	 * @return see {@link DefaultMutableTreeNode}
	 */
	public DefaultMutableTreeNode getTreeNode() {
		return treeNode;
	}

	/**
	 * Getter fuer Update View Marker <br>
	 * is true wenn sich etwas am System geaendert hat was die View interssiert
	 * 
	 * @return boolean updateView
	 */
	public boolean getUpdateView() {

		return updateView;
	}

	/**
	 * Setter fuer Inbox Folder
	 * 
	 * @param inboxFolder
	 */
	public void setInboxPath(File inboxFolder) {
		this.inboxFolder = inboxFolder;
		setTreeNode(inboxFolder);
		treeModel = new DefaultTreeModel(getTreeNode());
	}

	/**
	 * Setter Inbox Folder
	 * 
	 * @param inboxFolder
	 */
	public void setTreeNode(File inboxFolder) {
		treeNode = addNodes(inboxFolder);
	}

	/**
	 * Setter fuer Update View
	 * 
	 * @param updateView
	 */
	public void setUpdateView(boolean updateView) {

		boolean oldValue = this.updateView;
		this.updateView = updateView;
		firePropertyChange("updateView", oldValue, updateView);
	}

}