package ch.ffhs.hdo.client.ui.export;

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
import ch.ffhs.hdo.client.ui.utils.ChooseDirectoryPathViewOperation;

public class ExportView extends View<ExportModel> {

	private final String I18N = "hdo.export";
	private final String TITLE_KEY = I18N + ".title";
	private JTextField pathTextField;
	private JButton pathChooserButton;

	private JButton exportButton;
	private JButton cancelButton;

	public ExportView(ResourceBundle resourceBundle) {
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

				pathTextField.setText((String) arg);

				System.out.println(o.toString() + " " + arg.toString());

			}
		});

	}
	
	private void createComponents() {

		pathTextField = new JTextField();

		pathChooserButton = new JButton(getMessage(I18N + ".button.pathChooser"));
		pathChooserButton.addActionListener(new ChooseFilePathAction());
		

		exportButton = new JButton(getMessage(I18N + ".button.export"));
		cancelButton = new JButton(getMessage("base.cancel"));
	}

	private void layoutForm() {

		FormBuilder builder = FormBuilder.create()
				.columns("right:pref, 5dlu,[30dlu, pref],5dlu,[20dlu, pref],5dlu, [20dlu, pref]")
				.rows("p, $lg, p, $lg, p, $lg, p, $lg, p , $lg, p , $lg, p");

		builder.addLabel(getMessage(I18N + ".label.targetPath")).rc(3, 1);
		builder.add(pathTextField).rcw(3, 3, 3);
		builder.add(pathChooserButton).rcw(3, 7, 1);

		builder.add(exportButton).rcw(11, 3, 1);
		builder.add(cancelButton).rcw(11, 5, 1);

		builder.padding(new EmptyBorder(5, 5, 5, 5));
		JPanel build = builder.build();

		getFrame().add(build, BorderLayout.CENTER);

		setDimension(430, 145);
	}
		

	private class ChooseFilePathAction extends AbstractAction {

		public void actionPerformed(ActionEvent e) {

			getHandler().performOperation(ChooseDirectoryPathViewOperation.class);

		}

	}

}
