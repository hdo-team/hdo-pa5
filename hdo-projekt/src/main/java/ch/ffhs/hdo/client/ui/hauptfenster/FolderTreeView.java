package ch.ffhs.hdo.client.ui.hauptfenster;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.util.ResourceBundle;

import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeModel;

import ch.ffhs.hdo.client.ui.base.View;
import ch.ffhs.hdo.client.ui.hauptfenster.executable.FolderTreeUpdateOperation;
import ch.ffhs.hdo.infrastructure.ApplicationSettings;

public class FolderTreeView extends View<FolderTreeModel> {

	private final String I18N = "hdo.main";
	private JPanel jPanel;
	private JTree tree;

	public FolderTreeView(ResourceBundle resourceBundle, FolderTreeModel folderModel) {
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
		tree = new JTree(getModel().getTreeModel());
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
						getHandler().performOperationWithArgs(FolderTreeUpdateOperation.class,
								ApplicationSettings.getInstance().getInbox_path());

						tree.setModel(getModel().getTreeModel());
						getModel().setUpdateView(false);
					}
				}

			}
		});
	}

	public JPanel getPanel() {
		return jPanel;
	}

}
