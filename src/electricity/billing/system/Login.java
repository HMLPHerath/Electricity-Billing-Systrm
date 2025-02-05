package electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class Login extends JFrame implements ActionListener {
    // Declare input fields for username and password
    JTextField userText, passwordText;

    // Declare a dropdown choice for login options (Admin/Customer)
    Choice loginChoice;

    // Declare buttons for login, cancel, and  
    JButton loginButton, cancelButton, signupButton;

    // Constructor to initialize the Login window
    Login() {
        super("Login"); // Set the title of the JFrame
        getContentPane().setBackground(Color.white); // Set the background color of the JFrame to white

        // Set layout manager to null for absolute positioning
        setLayout(null);

        // Create and position the username label
        JLabel username = new JLabel("UserName");
        username.setBounds(300, 60, 100, 20); // Set position and size of username label
        add(username); // Add the label to the JFrame

        // Create a text field for entering the username
        userText = new JTextField();
        userText.setBounds(400, 60, 150, 20); // Position and size for the username text field
        add(userText);

        // Create and position the password label
        JLabel password = new JLabel("Password");
        password.setBounds(300, 100, 100, 20); // Set position and size for the password label
        add(password); // Add the label to the JFrame

        // Create a text field for entering the password
        passwordText = new JTextField();
        passwordText.setBounds(400, 100, 150, 20); // Position and size for the password text field
        add(passwordText);

        // Create and position the "Login in As" label
        JLabel login = new JLabel("Login in As");
        login.setBounds(300, 140, 100, 20); // Set position and size
        add(login); // Add the label to the JFrame

        // Create a dropdown menu (Choice) for selecting the login type (Admin/Customer)
        loginChoice = new Choice();
        loginChoice.add("Admin"); // Add "Admin" option
        loginChoice.add("Customer"); // Add "Customer" option
        loginChoice.setBounds(400, 140, 150, 20); // Position and size for the dropdown menu
        add(loginChoice);

        // Create and position the login button
        loginButton = new JButton("Login");
        loginButton.setBounds(330, 180, 100, 20); // Set position and size for the login button
        loginButton.addActionListener(this);
        add(loginButton);

        // Create and position the cancel button
        cancelButton = new JButton("Cansel");
        cancelButton.setBounds(460, 180, 100, 20); // Set position and size for the cancel button
        cancelButton.addActionListener(this);
        add(cancelButton);

        // Create and position the signup button
        signupButton = new JButton("Signup");
        signupButton.setBounds(400, 215, 100, 20); // Set position and size for the signup button
        signupButton.addActionListener(this);
        add(signupButton);

        // Load and resize the profile image to fit inside the JFrame
        ImageIcon profileOne = new ImageIcon(ClassLoader.getSystemResource("icon/profile.png"));
        Image profileTwo = profileOne.getImage().getScaledInstance(250, 250, Image.SCALE_DEFAULT); // Resize the image to 250x250
        ImageIcon fprofileOne = new ImageIcon(profileTwo); // Convert the resized image back to ImageIcon
        JLabel profileLable = new JLabel(fprofileOne); // Add the image to a JLabel
        profileLable.setBounds(5, 5, 250, 250); // Set position and size for the JLabel
        add(profileLable);

        // Set the frame size, position, and behavior on close
        setSize(640, 300); // Set the size of the JFrame
        setLocation(400, 200); // Set the location of the JFrame on the screen
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Ensure the application exits on close
        setVisible(true); // Make the JFrame visible
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton) {
            String suserName = userText.getText();
            String spassword = passwordText.getText();
            String user = loginChoice.getSelectedItem();

            try{
                Database c = new Database();

                if (c.connection == null) {
                    JOptionPane.showMessageDialog(null,"Database connection failed.");
                    return;
                }

                String query = "Select * From singup where username = '"+suserName+"' and password = '"+spassword+"' and usertype = '"+user+"' ";
                ResultSet resultSet = c.statement.executeQuery(query);

                if (resultSet.next()) {
                    setVisible(false);
                    new Main_Class();
                } else {
                    JOptionPane.showMessageDialog(null,"Invalid Login");
                }

            }catch (Exception E){
                E.printStackTrace();
            }
        } else if (e.getSource() == cancelButton) {
            setVisible(false);
        } else if (e.getSource() == signupButton) {
            setVisible(false);
            new Signup();
        }
    }

    // Main method to launch the Login window
    public static void main(String[] args) {
        new Login(); // Create an instance of the Login class to display the login window
    }
}