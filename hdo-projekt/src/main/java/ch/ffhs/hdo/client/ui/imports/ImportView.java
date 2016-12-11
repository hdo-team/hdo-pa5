package ch.ffhs.hdo.client.ui.imports;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.jgoodies.forms.builder.FormBuilder;

import ch.ffhs.hdo.client.ui.base.View;
import ch.ffhs.hdo.client.ui.utils.ChooseFilePathViewOperation;
import ch.ffhs.hdo.client.ui.utils.ReadFileViewOperation;

/**
 * 
 * @author Adrian Perez Rodriguez
 */
public class ImportView extends View<ImportModel> {

	private final String I18N = "hdo.import";
	private final String TITLE_KEY = I18N + ".title";
	JTextField filePath; // FRAGE
	private JButton filePathButton;
	private JButton loadButton;
	private JButton cancelButton;

	public ImportView(ResourceBundle resourceBundle) {
		super(resourceBundle);
		setTitle(getMessage(TITLE_KEY));

		initComponents();
	}

	private void initComponents() {
		createComponents();
		layoutForm();

	}

	@Override
	public void configureBindings() {
		getModel().addObserver(new Observer() {

			public void update(Observable o, Object arg) {

				filePath.setText((String) arg);

				System.out.println(o.toString() + " " + arg.toString());

			}
		});

	}

	private void createComponents() {

		filePath = new JTextField();
		filePathButton = new JButton(getMessage(I18N + ".button.filePathButton"));
		filePathButton.addActionListener(new ChooseFilePathAction());
		loadButton = new JButton(getMessage(I18N + ".button.import"));
		loadButton.addActionListener(new ReadFileAction());
		cancelButton = new JButton(getMessage("base.cancel"));
	}

	private void layoutForm() {

		FormBuilder builder = FormBuilder.create()
				.columns("right:pref, 5dlu,[30dlu, pref],5dlu,[20dlu, pref],5dlu, [20dlu, pref]")
				.rows("p, $lg, p, $lg, p, $lg, p, $lg, p , $lg, p , $lg, p");

		builder.addLabel(getMessage(I18N + ".label.filePath")).rc(3, 1);
		builder.add(filePath).rcw(3, 3, 3);
		builder.add(filePathButton).rcw(3, 7, 1);

		builder.add(loadButton).rcw(11, 3, 1);
		builder.add(cancelButton).rcw(11, 5, 1);

		builder.padding(new EmptyBorder(5, 5, 5, 5));
		JPanel build = builder.build();

		getFrame().add(build, BorderLayout.CENTER);

		setDimension(430, 145);
	}

	private class ChooseFilePathAction extends AbstractAction {

		public void actionPerformed(ActionEvent e) {

			getHandler().performOperation(ChooseFilePathViewOperation.class);
			
		}

	}

	private class ReadFileAction extends AbstractAction {

		public void actionPerformed(ActionEvent e) {

			getHandler().performOperation(ReadFileViewOperation.class);

		}

	}

}
