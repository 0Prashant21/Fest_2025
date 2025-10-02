import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.HashMap;

public class CurrencyAndTodoApp extends JFrame implements ActionListener {
    // Currency Converter components
    JComboBox<String> fromCurrency, toCurrency;
    JTextField amountField, resultField;
    JButton convertButton;
    
    // To-Do List components
    DefaultListModel<String> listModel;
    JList<String> taskList;
    JTextField taskField;
    JButton addButton, removeButton, clearButton;

    // Exchange rates (for simplicity, using fixed rates)
    HashMap<String, Double> exchangeRates;

    public CurrencyAndTodoApp() {
        setTitle("Currency Converter & To-Do List");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(2, 1));

        // Initialize exchange rates
        exchangeRates = new HashMap<>();
        exchangeRates.put("USD", 1.0);
        exchangeRates.put("EUR", 0.85);
        exchangeRates.put("GBP", 0.76);
        exchangeRates.put("INR", 84.0);
        exchangeRates.put("JPY", 110.0);

        // Currency Converter panel
        JPanel currencyPanel = new JPanel();
        currencyPanel.setLayout(new GridLayout(4, 2, 10, 10));
        currencyPanel.setBorder(BorderFactory.createTitledBorder("Currency Converter"));

        currencyPanel.add(new JLabel("Amount:"));
        amountField = new JTextField();
        currencyPanel.add(amountField);

        currencyPanel.add(new JLabel("From:"));
        fromCurrency = new JComboBox<>(new String[]{"USD", "EUR", "GBP", "INR", "JPY"});
        currencyPanel.add(fromCurrency);

        currencyPanel.add(new JLabel("To:"));
        toCurrency = new JComboBox<>(new String[]{"USD", "EUR", "GBP", "INR", "JPY"});
        currencyPanel.add(toCurrency);

        convertButton = new JButton("Convert");
        convertButton.addActionListener(this);
        currencyPanel.add(convertButton);

        resultField = new JTextField();
        resultField.setEditable(false);
        currencyPanel.add(resultField);

        add(currencyPanel);

        // To-Do List panel
        JPanel todoPanel = new JPanel();
        todoPanel.setLayout(new BorderLayout());
        todoPanel.setBorder(BorderFactory.createTitledBorder("To-Do List"));

        listModel = new DefaultListModel<>();
        taskList = new JList<>(listModel);

        taskField = new JTextField(20);
        addButton = new JButton("Add Task");
        removeButton = new JButton("Remove Task");
        clearButton = new JButton("Clear All");

        addButton.addActionListener(this);
        removeButton.addActionListener(this);
        clearButton.addActionListener(this);

        JPanel taskInputPanel = new JPanel();
        taskInputPanel.add(taskField);
        taskInputPanel.add(addButton);

        JPanel taskActionPanel = new JPanel();
        taskActionPanel.add(removeButton);
        taskActionPanel.add(clearButton);

        todoPanel.add(new JScrollPane(taskList), BorderLayout.CENTER);
        todoPanel.add(taskInputPanel, BorderLayout.NORTH);
        todoPanel.add(taskActionPanel, BorderLayout.SOUTH);

        add(todoPanel);
    }

    // Handle button actions
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == convertButton) {
            // Currency Conversion Logic
            try {
                double amount = Double.parseDouble(amountField.getText());
                String from = fromCurrency.getSelectedItem().toString();
                String to = toCurrency.getSelectedItem().toString();

                double fromRate = exchangeRates.get(from);
                double toRate = exchangeRates.get(to);

                double result = (amount / fromRate) * toRate;
                resultField.setText(String.format("%.2f %s", result, to));
            } catch (NumberFormatException ex) {
                resultField.setText("Invalid amount");
            }
        } else if (e.getSource() == addButton) {
            // Add Task Logic
            String task = taskField.getText();
            if (!task.isEmpty()) {
                listModel.addElement(task);
                taskField.setText("");  // Clear the input field after adding
            }
        } else if (e.getSource() == removeButton) {
            // Remove selected task
            int selectedIndex = taskList.getSelectedIndex();
            if (selectedIndex != -1) {
                listModel.remove(selectedIndex);
            }
        } else if (e.getSource() == clearButton) {
            // Clear all tasks
            listModel.clear();
        }
    }

    // Main method to run the application
    public static void main(String[] args) {
        CurrencyAndTodoApp app = new CurrencyAndTodoApp();
        app.setVisible(true);
    }
}
