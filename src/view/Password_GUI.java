package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.PasswordGenerator;

/**
 * GUI that represents a PasswordGenerator. 
 * @author Brandon Soto
 * @version Apr 10, 2014
 */

@SuppressWarnings("serial")
public final class Password_GUI extends JFrame {
	
	/*
	 * Image icon credit:
	 * https://cdn3.iconfinder.com/data/icons/linecons-free-vector-icons-pack/32/lock-128.png
	 */
	
	/** The password generator to be used by the GUI. */
	private final PasswordGenerator f_passwordGenerator;
	
	/** The center panel of the GUI. Displays options for what should be included in the password. */
	private final JPanel myCenterPanel; 
	
	/** Contains a string of the password. */
	private final JTextField myPasswordField; 
	
	/** 
	 * Constructs a Password_GUI by setting up the GUI panels and giving the password a default
	 * value.
	 */
	public Password_GUI() {
		f_passwordGenerator = PasswordGenerator.GENERATOR_INSTANCE;
		f_passwordGenerator.addGroup(PasswordGenerator.NUMBERS); // numbers included by default
		
		myCenterPanel = new JPanel();
		myCenterPanel.setLayout(new GridLayout(5,5));
		
		myPasswordField = new JTextField();
		myPasswordField.setEditable(false);
		myPasswordField.setBorder(BorderFactory.createEmptyBorder());	
		
		setUpGUI();
	}
	
	/** Sets up all of the GUI's panels and sets the main frame's attributes. */
	private void setUpGUI() {
		setLayout(new BorderLayout());
		
		JPanel northPanel = new JPanel();
		northPanel.setBackground(Color.WHITE);
		northPanel.setBorder(BorderFactory.createBevelBorder(1));
		northPanel.add(myPasswordField);
		
		createCenterPanel();
		
		add(northPanel, BorderLayout.NORTH);
		add(myCenterPanel, BorderLayout.CENTER);
		
		setTitle("Password");
		setIconImage(new ImageIcon("lock-128.png").getImage());
		pack();
		setSize(new Dimension(300, 300));
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	/** Creates the center panel by creating inner panels for each option. */
	private void createCenterPanel() {
		createLengthPanel();
		createNumberOption();
		createLowerCaseOption();
		createUpperCaseOption();
		createPunctuationOption();
		createGenerateButton();
	}
	
	/** Sets up the panel that requests the password's length. */
	private void createLengthPanel() {
		final JLabel lengthLabel = new JLabel("Length :   ");
		lengthLabel.setHorizontalAlignment(JLabel.RIGHT);
		final JComboBox<Integer> options = new JComboBox<>();
		
		// add int options to combo box
		for (int i = 0; i <= 25; i++) {
			options.addItem(i);
		}
		
		options.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				f_passwordGenerator.setPasswordLength(options.getItemAt(options.getSelectedIndex()));
			}
		});
		
		final JPanel temp = new JPanel(); 
		temp.add(options);
		
		myCenterPanel.add(lengthLabel);
		myCenterPanel.add(temp);
	}
	
	/** Sets up the panel that requests whether the password should include numbers. */
	private void createNumberOption() {
		final JLabel numberLabel = new JLabel("Numbers:     ");
		numberLabel.setHorizontalAlignment(JLabel.RIGHT);
		
		final JCheckBox numbers = new JCheckBox();
		numbers.setSelected(true);
		numbers.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				if (numbers.isSelected()) {
					f_passwordGenerator.addGroup(PasswordGenerator.NUMBERS);
				} else {
					f_passwordGenerator.removeGroup(PasswordGenerator.NUMBERS);
				}
			}
		});
		
		myCenterPanel.add(numberLabel);
		myCenterPanel.add(numbers);
	}
	
	/** Sets up the panel that requests whether the password should include punctuation. */
	private void createPunctuationOption() {
		final JLabel puncLabel = new JLabel("Punctuation:   ");
		puncLabel.setHorizontalAlignment(JLabel.RIGHT);
		
		final JCheckBox punc = new JCheckBox();
		punc.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				if (punc.isSelected()) {
					f_passwordGenerator.addGroup(PasswordGenerator.PUNCTUATION);
				} else {
					f_passwordGenerator.removeGroup(PasswordGenerator.PUNCTUATION);
				}
			}
		});
		
		myCenterPanel.add(puncLabel);
		myCenterPanel.add(punc);
	}
	
	/** Sets up the panel that requests whether the password should include lowercase letters.*/
	private void createLowerCaseOption() {
		final JLabel lowerLabel = new JLabel("Lowercase:   ");
		lowerLabel.setHorizontalAlignment(JLabel.RIGHT);
		
		final JCheckBox lower = new JCheckBox();
		lower.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				if (lower.isSelected()) {
					f_passwordGenerator.addGroup(PasswordGenerator.LOWERCASE_LETTERS);
				} else {
					f_passwordGenerator.removeGroup(PasswordGenerator.LOWERCASE_LETTERS);
				}
			}
		});
		
		myCenterPanel.add(lowerLabel);
		myCenterPanel.add(lower);
	}
	
	/** Sets up the panel that requests whether the password should include uppercase letters. */
	private void createUpperCaseOption() {
		final JLabel upperLabel = new JLabel("Uppercase:   ");
		upperLabel.setHorizontalAlignment(JLabel.RIGHT);
		
		final JCheckBox upper = new JCheckBox();
		upper.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				if (upper.isSelected()) {
					f_passwordGenerator.addGroup(PasswordGenerator.UPPERCASE_LETTERS);
				} else {
					f_passwordGenerator.removeGroup(PasswordGenerator.UPPERCASE_LETTERS);
				}
			}
		});
		
		myCenterPanel.add(upperLabel);
		myCenterPanel.add(upper);
	}
	
	/** Sets up the button responsible for generating the password. */
	private void createGenerateButton() {
		final JButton generateButton = new JButton("Generate Password");
		generateButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				myPasswordField.setText(f_passwordGenerator.generatePassword());
				
				Password_GUI.this.revalidate();
			}
		});
		
		final JPanel temp = new JPanel(); 
		temp.add(generateButton);
		
		add(temp, BorderLayout.SOUTH);
	}
}
