package ch.ffhs.hdo.client.ui.hauptfenster;

import java.util.ResourceBundle;

import javax.swing.JPanel;

import ch.ffhs.hdo.client.ui.base.View;

public class FolderTreeView extends View<FolderModel> {
	
	private JPanel jPanel;

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

}
