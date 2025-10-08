import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;

class TypingSpeedTest extends JFrame {
    TypingSpeedTest() {
        Font f = new Font("Futura", Font.BOLD, 25);
        Font f2 = new Font("Calibri", Font.PLAIN, 22);
        Font f3 = new Font("Calibri", Font.ITALIC, 22);

        JLabel l1 = new JLabel("Typing Speed Test");
        l1.setFont(f);
        l1.setForeground(new Color(40, 40, 40));
        l1.setBounds(250, 30, 300, 50);

        JButton b1 = new JButton("Submit");
        b1.setFont(f2);
        b1.setBackground(new Color(30, 144, 255));
        b1.setForeground(Color.WHITE);
        b1.setBounds(650, 30, 100, 50);

        JButton b2 = new JButton("Show Results");
        b2.setFont(f2);
        b2.setBackground(new Color(34, 139, 34));
        b2.setForeground(Color.WHITE);
        b2.setBounds(250, 425, 250, 50);

        JTextArea t1 = new JTextArea("Reading is a powerful habit that enriches the mind and broadens perspectives. It allows individuals to explore new ideas, cultures, and experiences without leaving their homes. Whether fiction or non-fiction, books stimulate creativity, improve vocabulary, and enhance critical thinking skills. Reading also reduces stress by offering an escape from daily pressures and immersing the reader in engaging narratives. In today’s fast-paced world, it fosters focus and patience, qualities often overlooked. Moreover, it promotes empathy by helping readers understand different viewpoints. A lifelong habit of reading not only entertains but also equips individuals with knowledge and wisdom for personal growth.");
        char[] arr = t1.getText().toCharArray();
        t1.setFont(f2);
        t1.setEditable(false);
        t1.setWrapStyleWord(true);
        t1.setLineWrap(true);

        JScrollPane scrollPane = new JScrollPane(t1);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBounds(100, 100, 600, 100);

        JTextPane t2 = new JTextPane();
        JScrollPane scrollpane2 = new JScrollPane(t2);
        scrollpane2.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        scrollpane2.setBackground(Color.WHITE);

        StyledDocument doc = t2.getStyledDocument();
        Style errorStyle = doc.addStyle("Error", null);
        StyleConstants.setFontFamily(errorStyle, "Calibri");
        StyleConstants.setFontSize(errorStyle, 22);
        StyleConstants.setItalic(errorStyle, true);
        StyleConstants.setForeground(errorStyle, Color.RED);
        scrollpane2.setBounds(100, 300, 600, 100);
        t2.setFont(f2);

        Container c = getContentPane();
        c.setLayout(null);
        c.setBackground(new Color(152, 251, 152));
        c.add(l1);
        c.add(scrollPane);
        c.add(scrollpane2);
        c.add(b1);
        c.add(b2);

        t2.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            @Override
            public void insertUpdate(javax.swing.event.DocumentEvent e) {
                scrollToMatch();
            }

            @Override
            public void removeUpdate(javax.swing.event.DocumentEvent e) {
                scrollToMatch();
            }

            @Override
            public void changedUpdate(javax.swing.event.DocumentEvent e) {
            }

            private void scrollToMatch() {
                int inputLength = t2.getText().length();
                if (inputLength < t1.getText().length()) {
                    t1.setCaretPosition(inputLength);
                }
            }
        });

        b1.addActionListener(a -> {
            char[] arr2 = t2.getText().toCharArray();
            int i = 0;
            while (i < arr2.length && i < arr.length) {
                if (arr[i] != arr2[i]) {
                    doc.setCharacterAttributes(i, 1, errorStyle, true);
                }
                i++;
            }
        });

        b2.addActionListener(a -> {
            String inputText = t2.getText();
            int totalChars = inputText.length();
            int correctChars = 0;
            char[] typedArr = inputText.toCharArray();
            for (int i = 0; i < typedArr.length && i < arr.length; i++) {
                if (typedArr[i] == arr[i]) {
                    correctChars++;
                }
            }
            int wordsTyped = totalChars / 5;
            int wpm = wordsTyped;
            double accuracy = (totalChars == 0) ? 0 : ((correctChars * 100.0) / totalChars);
            JOptionPane.showMessageDialog(null, "WPM: " + wpm + "\nAccuracy: " + String.format("%.2f", accuracy) + "%");
        });

        setVisible(true);
        setSize(800, 550);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Typing Speed Test");
    }

    public static void main(String[] args) {
        new TypingSpeedTest();
    }
}
