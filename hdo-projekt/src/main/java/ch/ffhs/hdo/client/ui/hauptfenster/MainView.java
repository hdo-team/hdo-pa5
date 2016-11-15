package ch.ffhs.hdo.client.ui.hauptfenster;

import java.awt.BorderLayout;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JFileChooser;
import javax.swing.border.EmptyBorder;
import java.awt.event.*;
import java.io.File;

import com.jgoodies.forms.builder.FormBuilder;

import ch.ffhs.hdo.client.ui.base.View;

public class MainView extends View<MainModel> {

	private final String I18N = "hdo.main";
	private final String TITLE_KEY = I18N + ".title";
	
	public MainView(ResourceBundle resourceBundle) {
		super(resourceBundle);
		setTitle(getMessage(TITLE_KEY));

		initComponents();

	}

	private void initComponents() {
		createComponents();
		layoutForm();
		configureBindings();
	}

	private void createComponents() {

	
	}

	private void layoutForm() {

		
	}
		
	private void configureBindings() {

	}
}
