import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Clipboard;

public class PasswordGenerator extends JFrame implements ActionListener {

    // Declare components
    JTextField lengthField;
    JCheckBox includeUppercase, includeLowercase, includeDigits, includeSpecial;
    JTextArea resultArea;
    JButton generateButton, copyButton;

    // Constructor to set up the GUI
    public PasswordGenerator() {
        setTitle("Password Generator");
        setSize(400, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Set layout
        setLayout(new GridLayout(7, 1));

        // Password length input
        JPanel lengthPanel = new JPanel();
        lengthPanel.add(new JLabel("Password Length: "));
        lengthField = new JTextField(5);
        lengthPanel.add(lengthField);
        add(lengthPanel);

        // Options for character types
        includeUppercase = new JCheckBox("Include Uppercase Letters");
        includeLowercase = new JCheckBox("Include Lowercase Letters");
        includeDigits = new JCheckBox("Include Digits");
        includeSpecial = new JCheckBox("Include Special Characters");

        add(includeUppercase);
        add(includeLowercase);
        add(includeDigits);
        add(includeSpecial);

        // Generate button
        generateButton = new JButton("Generate Password");
        generateButton.addActionListener(this);
        add(generateButton);

        // Result area
        resultArea = new JTextArea(2, 20);
        resultArea.setEditable(false);
        resultArea.setFont(new Font("Arial", Font.BOLD, 16));
        add(new JScrollPane(resultArea));

        // Copy button
        copyButton = new JButton("Copy to Clipboard");
        copyButton.addActionListener(this);
        add(copyButton);
    }

    // Action performed when button is clicked
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == generateButton) {
            try {
                // Get password length
                int length = Integer.parseInt(lengthField.getText());

                // Validate length
                if (length <= 0) {
                    resultArea.setText("Please enter a valid length.");
                    return;
                }

                // Character pools
                String uppercase = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
                String lowercase = "abcdefghijklmnopqrstuvwxyz";
                String digits = "0123456789";
                String special = "!@#$%^&*()-_+=<>?/|~";

                StringBuilder charPool = new StringBuilder();

                // Build character pool based on user options
                if (includeUppercase.isSelected()) {
                    charPool.append(uppercase);
                }
                if (includeLowercase.isSelected()) {
                    charPool.append(lowercase);
                }
                if (includeDigits.isSelected()) {
                    charPool.append(digits);
                }
                if (includeSpecial.isSelected()) {
                    charPool.append(special);
                }

                // Validate if at least one option is selected
                if (charPool.length() == 0) {
                    resultArea.setText("Please select at least one option.");
                    return;
                }

                // Generate password
                String password = generatePassword(length, charPool.toString());
                resultArea.setText(password);
            } catch (NumberFormatException ex) {
                resultArea.setText("Please enter a valid number for length.");
            }
        } else if (e.getSource() == copyButton) {
            String password = resultArea.getText();
            if (!password.isEmpty()) {
                copyToClipboard(password);
                JOptionPane.showMessageDialog(null, "Password copied to clipboard!");
            } else {
                JOptionPane.showMessageDialog(null, "No password to copy!");
            }
        }
    }

    // Method to generate the password
    private String generatePassword(int length, String charPool) {
        Random random = new Random();
        StringBuilder password = new StringBuilder();

        for (int i = 0; i < length; i++) {
            int index = random.nextInt(charPool.length());
            password.append(charPool.charAt(index));
        }

        return password.toString();
    }

    // Method to copy text to clipboard
    private void copyToClipboard(String text) {
        StringSelection stringSelection = new StringSelection(text);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(stringSelection, null);
    }

    // Main method to start the application
    public static void main(String[] args) {
        PasswordGenerator generator = new PasswordGenerator();
        generator.setVisible(true);
    }
}
