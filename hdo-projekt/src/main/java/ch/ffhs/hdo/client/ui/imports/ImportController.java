package ch.ffhs.hdo.client.ui.imports;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.swing.JFileChooser;

import ch.ffhs.hdo.client.ui.base.Controller;
/**
 * 
 * @author Adrian Perez Rodriguez
 */
public class ImportController extends Controller<ImportModel, ImportView> {
	
	public ImportController(ImportModel model) {
		
		super(model);
		setView(new ImportView(getResourceBundle()));
		initializeView();
	}
	
	public void initializeView() {
		getView().setResourceBundle(getResourceBundle());
		getView().setModel(getModel());
		getView().addFilePathButtonListener(new FilePathButtonListener());
		getView().addLoadButtonListener(new LoadButtonListener());
	}
	
	class FilePathButtonListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
        	Properties prop = new Properties();
    		InputStream input = null;
    		
    		JFileChooser fileChooser = new JFileChooser();
    		fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
    		int result = fileChooser.showOpenDialog(null);
    		
    		if (result == JFileChooser.APPROVE_OPTION) {
    		    File selectedFile = fileChooser.getSelectedFile();
    		    getModel().setFilePath(selectedFile.getAbsolutePath()); // FRAGE
    		    getView().filePath.setText(selectedFile.getAbsolutePath()); // FRAGE
    		    
    		    // Datei erst nach Import-Button lesen oder?! Wie weiterverarbeiten?!
    		    try {
    				input = new FileInputStream(selectedFile);

    				// load a properties file
    				prop.load(input);

    				// get the property values
    				System.out.println(prop.getProperty("inbox"));
    				getModel().setInboxPath(prop.getProperty("inbox"));
    				
    				System.out.println(prop.getProperty("autoModus"));
    				getModel().setAutoModus(Boolean.parseBoolean(prop.getProperty("autoStart")));
    				
    				System.out.println(prop.getProperty("intervall"));
    				getModel().setIntervall(Integer.parseInt(prop.getProperty("intervall")));

    			} catch (IOException ex) {
    				ex.printStackTrace();
    			} finally {
    				if (input != null) {
    					try {
    						input.close();
    					} catch (IOException e1) {
    						e1.printStackTrace();
    					}
    				}
    			}
    		}
		}
    }

	class LoadButtonListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
        	System.out.println("File loaded: "+getModel().getFilePath());
		}
    }
}