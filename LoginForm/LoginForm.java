import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
//These are import statements, bringing in necessary classes from the Java Swing
// library for creating GUI components, handling events, and SQLException for database-related errors.
public class LoginPage implements ActionListener {
    //This declares a class named LoginPage which implements the ActionListener interface,
    // indicating that instances of this class can listen for and respond to action events.java
    private JFrame frame;
    private JButton loginButton;
    private JButton newUserButton; // New button for adding new users
    private JTextField userIDField;
    private JPasswordField userPasswordField;
    private JLabel userIDLabel;
    private JLabel userPasswordLabel;
    private JLabel messageLabel;
    private DatabaseManager databaseManager;

    public LoginPage(DatabaseManager databaseManager) {
        this.databaseManager = databaseManager;

        frame = new JFrame("Login Page");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 350);
        frame.getContentPane().setBackground(new Color(240, 248, 255)); // Light Blue background
        frame.setLayout(null);

        userIDLabel = new JLabel("User ID:");
        userIDLabel.setBounds(50, 50, 75, 25);
        frame.add(userIDLabel);

        userIDField = new JTextField();
        userIDField.setBounds(150, 50, 200, 25);
        frame.add(userIDField);

        userPasswordLabel = new JLabel("Password:");
        userPasswordLabel.setBounds(50, 100, 75, 25);
        frame.add(userPasswordLabel);

        userPasswordField = new JPasswordField();
        userPasswordField.setBounds(150, 100, 200, 25);
        frame.add(userPasswordField);

        loginButton = new JButton("Login");
        loginButton.setBounds(150, 150, 100, 30);
        loginButton.addActionListener(this);


        loginButton.setBackground(new Color(50, 205, 50)); // Lime Green button color
        loginButton.setForeground(Color.WHITE); // White text color
        frame.add(loginButton);

        newUserButton = new JButton("New User"); // New button for adding new users
        newUserButton.setBounds(50, 200, 100, 30);
        newUserButton.addActionListener(this);


        newUserButton.setBackground(new Color(50, 50, 255)); // Blue button color
        newUserButton.setForeground(Color.WHITE); // White text color
        frame.add(newUserButton);

        messageLabel = new JLabel();
        messageLabel.setBounds(100, 250, 300, 25);
        frame.add(messageLabel);

        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String userID = userIDField.getText();
        String password = String.valueOf(userPasswordField.getPassword());

        if (e.getSource() == loginButton) {
            boolean isAuthenticated = databaseManager.authenticateUser(userID, password);
            if (isAuthenticated) {
                // Authentication successful, navigate to WelcomePage
                frame.dispose();
                new WelcomePage(userID);
            } else {
                // Authentication failed, display error message
                messageLabel.setForeground(Color.RED);
                messageLabel.setText("Invalid username or password");
            }
        }

        else if (e.getSource() == newUserButton)
        {
            // Handle creating a new user
            try
            {
                databaseManager.addUser(userID, password);
                messageLabel.setForeground(Color.GREEN);
                messageLabel.setText("New user added successfully!");

            }
            catch (SQLException ex)
            {
                ex.printStackTrace();
                messageLabel.setForeground(Color.RED);
                messageLabel.setText("Error adding new user");
            }

        }
    }

    public static void main(String[] args) {
        DatabaseManager databaseManager = new DatabaseManager();
        new LoginPage(databaseManager);
    }
}
