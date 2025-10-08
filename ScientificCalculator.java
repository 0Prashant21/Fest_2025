import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.lang.Math;

public class ScientificCalculator extends JFrame implements ActionListener {
    // Declare components
    JTextField display;

    // Constructor to set up the GUI
    public ScientificCalculator() {
        setTitle("Scientific Calculator");
        setSize(400, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create a text field to display results
        display = new JTextField();
        display.setFont(new Font("Arial", Font.BOLD, 24));
        display.setEditable(false);
        add(display, BorderLayout.NORTH);

        // Create a panel for buttons
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 4, 10, 10));

        // Add buttons
        String[] buttons = {
            "7", "8", "9", "/",
            "4", "5", "6", "*",
            "1", "2", "3", "-",
            "0", ".", "=", "+",
            "sin", "cos", "tan", "^"
        };

        for (String text : buttons) {
            JButton button = new JButton(text);
            button.setFont(new Font("Arial", Font.BOLD, 20));
            button.addActionListener(this);
            panel.add(button);
        }

        add(panel, BorderLayout.CENTER);
    }

    // Variables to store operation details
    double num1 = 0, num2 = 0, result = 0;
    char operator;

    // Handle button click events
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        try {
            // Number or decimal input
            if ((command.charAt(0) >= '0' && command.charAt(0) <= '9') || command.equals(".")) {
                display.setText(display.getText() + command);
            }
            // Operations
            else if (command.equals("+") || command.equals("-") || command.equals("*") || command.equals("/") || command.equals("^")) {
                num1 = Double.parseDouble(display.getText());
                operator = command.charAt(0);
                display.setText("");
            }
            // Trigonometric operations
            else if (command.equals("sin")) {
                num1 = Double.parseDouble(display.getText());
                display.setText(Double.toString(Math.sin(Math.toRadians(num1))));
            } else if (command.equals("cos")) {
                num1 = Double.parseDouble(display.getText());
                display.setText(Double.toString(Math.cos(Math.toRadians(num1))));
            } else if (command.equals("tan")) {
                num1 = Double.parseDouble(display.getText());
                display.setText(Double.toString(Math.tan(Math.toRadians(num1))));
            }
            // Result
            else if (command.equals("=")) {
                num2 = Double.parseDouble(display.getText());
                switch (operator) {
                    case '+': result = num1 + num2; break;
                    case '-': result = num1 - num2; break;
                    case '*': result = num1 * num2; break;
                    case '/': result = num1 / num2; break;
                    case '^': result = Math.pow(num1, num2); break;
                }
                display.setText(Double.toString(result));
            }
        } catch (Exception ex) {
            display.setText("Error");
        }
    }

    // Main method to start the calculator
    public static void main(String[] args) {
        ScientificCalculator calculator = new ScientificCalculator();
        calculator.setVisible(true);
    }
}
