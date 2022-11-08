package Caesar;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.*;

public class CaesarFrame extends JFrame {
	public static boolean decode;
	private final JPanel upperPanel, lowerPanel;
	private final JTextField upperTextField;
	private final JTextField lowerTextField;
	private final JButton button;
	private final JComboBox<Object> comboBox;
	public CaesarFrame() {
		super("SwingLab");
		//this.setResizable(false);
		this.setSize(415, 110);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLayout(new GridLayout(2,1));

		Object[] chars = new Object[26];
		for (int i = 'A'; i <= 'Z'; i++) {
			chars[i - 'A'] = (char) i;
		}

		upperPanel = new JPanel(new FlowLayout());
		lowerPanel = new JPanel(new BorderLayout());

		upperTextField = new JTextField(20);
		upperTextField.addFocusListener(new TextFieldFocusListener(false));
		upperTextField.getDocument().addDocumentListener(new inputFieldKeyListener());

		lowerTextField = new JTextField(20);
		lowerTextField.addFocusListener(new TextFieldFocusListener(true));
		lowerTextField.setEditable(true);

		button = new JButton("Code!");
		button.addActionListener(new OkButtonActionListener());

		comboBox = new JComboBox<>(chars);

		upperPanel.add(comboBox);
		upperPanel.add(upperTextField);
		upperPanel.add(button);

		lowerPanel.add(new JLabel("Output:"), BorderLayout.WEST);
		lowerPanel.add(lowerTextField);

		this.add(upperPanel);
		this.add(lowerPanel);

		this.setLocationRelativeTo(null);
	}

	private class OkButtonActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (!decode) {
				String tmp = CaesarCode.caesarCode(upperTextField.getText(), (char) comboBox.getSelectedItem());
				lowerTextField.setText(tmp);
			} else {
				String tmp = CaesarCode.caesarDecode(lowerTextField.getText(), (char) comboBox.getSelectedItem());
				upperTextField.setText(tmp);
			}
		}
	}

	private class inputFieldKeyListener implements DocumentListener {
		@Override
		public void insertUpdate(DocumentEvent e) {
			String tmp = CaesarCode.caesarCode(upperTextField.getText(), (char) comboBox.getSelectedItem());
			lowerTextField.setText(tmp);
		}

		@Override
		public void removeUpdate(DocumentEvent e) {
			String tmp = CaesarCode.caesarCode(upperTextField.getText(), (char) comboBox.getSelectedItem());
			lowerTextField.setText(tmp);
		}

		@Override
		public void changedUpdate(DocumentEvent e) {

		}
	}

	private record TextFieldFocusListener(boolean lower) implements FocusListener {

		@Override
			public void focusGained(FocusEvent e) {
				decode = lower;
			}

			@Override
			public void focusLost(FocusEvent e) {

			}
		}
}
