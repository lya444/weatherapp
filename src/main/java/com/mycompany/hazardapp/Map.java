package main.java.com.mycompany.hazardapp;

import javax.swing.JFrame;
import java.awt.Image;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Map {

    private JLayeredPane layeredPane; // Add this line

    public void openMap() {

        // Create a new JFrame
        JFrame frame = new JFrame("Display Image");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 700); // Set the size of the frame
        frame.setLayout(null); // Disable layout manager for absolute positioning

        // Load the image
        String imagePath = "images/The_World_map.png"; // Specify the path to your image
        ImageIcon imageIcon = new ImageIcon(imagePath);

        // Scale the image to fit the frame
        Image image = imageIcon.getImage(); // Transform it 
        Image scaledImage = image.getScaledInstance(frame.getWidth(), frame.getHeight(), Image.SCALE_SMOOTH); // Scale it the smooth way
        imageIcon = new ImageIcon(scaledImage);  // Transform it back

        // Create a JLabel to hold the image
        JLabel label = new JLabel(imageIcon);
        label.setBounds(0, 0, frame.getWidth(), frame.getHeight()); // Set the position and size of the label

        layeredPane = new JLayeredPane();
        layeredPane.setBounds(0, 0, frame.getWidth(), frame.getHeight());
        frame.add(layeredPane);

        // Add map image label to default layer (0)
        layeredPane.add(label, Integer.valueOf(0));

        label.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int x = e.getX();
                int y = e.getY();

                System.out.println("Clicked at: (" + x + ", " + y + ")");
                JTextArea textArea = new JTextArea(5, 20); // 5 rows, 20 columns
                textArea.setBorder(BorderFactory.createLineBorder(Color.BLACK)); // Add a border

                // Load the new image
                String newImagePath = "images/668-6687315_location-pointer-png.png"; // Specify the path to your new image
                ImageIcon newImageIcon = new ImageIcon(newImagePath);
                Image iconImage = newImageIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
                newImageIcon = new ImageIcon(iconImage);

                // Create a JLabel to hold the new image
                JLabel newLabel = new JLabel(newImageIcon);
                int xsize = 100;
                int ysize = 100;
                newLabel.setBounds(x - xsize / 2, y - ysize / 2 - 10, xsize, ysize); // Set the position and size of the new label


                // Questions to ask
                JTextField question1 = new JTextField();
                JTextField question2 = new JTextField();
                JTextField question3 = new JTextField();
                
                Object[] message = {
                    "Place", question1,
                    "Hazard Type", question2,
                    "Dangerosity", question3,
                };
                
                int option = JOptionPane.showConfirmDialog(frame, message, "Enter your answers", JOptionPane.OK_CANCEL_OPTION);
                if (option == JOptionPane.OK_OPTION) {            
                    
                    String answer1 = question1.getText();
                    String answer2 = question2.getText();
                    String answer3 = question3.getText();
                    
                    // Display the answers at the click location
                    JLabel answerLabel = new JLabel("<html>Place : " + answer1 + "<br>Hazard Type : " + answer2 + "<br>Dangerosity : "+ answer3+ "</html>" );
                    answerLabel.setBounds(x-newImageIcon.getIconWidth(), y, 200, 50); // Adjust the position next to the icon
                    layeredPane.add(answerLabel, Integer.valueOf(1));
                }
                layeredPane.add(newLabel, Integer.valueOf(1));
                layeredPane.revalidate();
                layeredPane.repaint();

            }
        }
        );

        // Set the frame to be visible
        frame.setVisible(
                true);
    }
}
