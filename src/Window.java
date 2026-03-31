import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

public class Window {
    private final JFrame window;
    private final JTextArea outputArea;
    private final JTextField inputField;
    private final JButton submitButton;
    private String inputValue;

    public Window() {
        window = new JFrame("Java Hangman");
        window.setSize(800, 600);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLayout(new BorderLayout(10, 10));

        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(Color.BLACK);
        JLabel titleNameLabel = new JLabel("Java Hangman");
        titleNameLabel.setForeground(Color.WHITE);
        titleNameLabel.setFont(new Font("Times New Roman", Font.PLAIN, 42));
        headerPanel.add(titleNameLabel);

        outputArea = new JTextArea();
        outputArea.setEditable(false);
        outputArea.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 18));
        outputArea.setLineWrap(true);
        outputArea.setWrapStyleWord(true);

        JScrollPane scrollPane = new JScrollPane(outputArea);

        JPanel inputPanel = new JPanel(new BorderLayout(10, 10));
        inputField = new JTextField();
        inputField.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 18));
        submitButton = new JButton("Submit");
        submitButton.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 18));
        inputPanel.add(inputField, BorderLayout.CENTER);
        inputPanel.add(submitButton, BorderLayout.EAST);

        submitButton.addActionListener(e -> submitInput());
        inputField.addActionListener(e -> submitInput());

        window.add(headerPanel, BorderLayout.NORTH);
        window.add(scrollPane, BorderLayout.CENTER);
        window.add(inputPanel, BorderLayout.SOUTH);
        window.setLocationRelativeTo(null);
        window.setVisible(true);

        redirectSystemOutput();
    }

    private void redirectSystemOutput() {
        OutputStream out = new OutputStream() {
            @Override
            public void write(int b) throws IOException {
                appendText(String.valueOf((char) b));
            }
        };

        PrintStream printStream = new PrintStream(out, true);
        System.setOut(printStream);
        System.setErr(printStream);
    }

    private void appendText(String text) {
        SwingUtilities.invokeLater(() -> {
            outputArea.append(text);
            outputArea.setCaretPosition(outputArea.getDocument().getLength());
        });
    }

    private synchronized void submitInput() {
        inputValue = inputField.getText();
        inputField.setText("");
        notifyAll();
    }

    public synchronized String readInput(String prompt) {
        System.out.print(prompt);
        inputField.requestFocusInWindow();
        while (inputValue == null) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return "";
            }
        }
        String value = inputValue;
        inputValue = null;
        System.out.println(value);
        return value;
    }
}
