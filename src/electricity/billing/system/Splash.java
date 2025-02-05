package electricity.billing.system;

import javax.swing.*;
import java.awt.*;

public class Splash extends JFrame {

    // Constructor to initialize the Splash screen
    Splash(){
        // Add the image Frame
        ImageIcon imageIcon = new ImageIcon(ClassLoader.getSystemResource("icon/splash/Splash.jpg")); // Load the splash image
        Image imageOne = imageIcon.getImage().getScaledInstance(600,400,Image.SCALE_DEFAULT); // Resize the image to 600x400
        ImageIcon imageIcon2 = new ImageIcon(imageOne); // Convert the resized image back to ImageIcon
        JLabel imageLabel = new JLabel(imageIcon2); // Place the image in a JLabel
        add(imageLabel); // Add the JLabel containing the image to the JFrame

        // Set the size of the JFrame
        setSize(600,400);
        // Set the location of the JFrame on the screen (500px from the left and 200px from the top of the screen)
        setLocation(500,200);
        // Make the JFrame visible
        setVisible(true);

        try {
            // Pause the splash screen for 3 seconds
            Thread.sleep(3000);
            // Hide the splash screen after 3 seconds
            setVisible(false);

            // Open the Login window after the splash screen
            new Login();
        } catch (Exception e) {
            // Print the stack trace for debugging in case of an error
            e.printStackTrace();
        }
    }

    // Main method to launch the Splash screen
    public static void main(String[] args) {
        new Splash(); // Create an instance of the Splash class to display the splash screen
    }
}
