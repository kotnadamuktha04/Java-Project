import javax.swing.*;
import java.awt.*;

public class WelcomePage {
    private JFrame frame;
    private JLabel welcomeLabel;
    private JLabel messageLabel;

    public WelcomePage(String userID) {
        frame = new JFrame("Welcome Page");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.getContentPane().setBackground(new Color(240, 248, 255)); // Light Blue background
        frame.setLayout(null);

        welcomeLabel = new JLabel("Hello, " + userID);
        welcomeLabel.setBounds(100, 50, 200, 30);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 20));
        frame.add(welcomeLabel);

        messageLabel = new JLabel("All the best for today's Java project submission!");
        messageLabel.setBounds(50, 100, 300, 30);
        messageLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        messageLabel.setForeground(new Color(34, 139, 34)); // Dark Green color
        frame.add(messageLabel);

        frame.setVisible(true);
    }
}
