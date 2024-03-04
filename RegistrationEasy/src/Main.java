import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import java.io.PrintWriter;

public class Main implements ActionListener {

    // Initializing variables here.
    JFrame frame;

    JPanel panel;
    JPanel passPanel;
    JPanel regPanel;

    JLabel label;
    JLabel passLabel;
    JLabel errorMessage;
    JLabel passErrorMessage;
    JLabel passSuccessMessage;
    JLabel regLabel;
    JLabel regNameLabel;
    JLabel regPasswordLabel;

    JButton button;
    JButton passButton;
    JButton regPageButton;
    JButton regSubmitButton;

    JTextField textField;
    JTextField passTextField;
    JTextField regName;
    JTextField regPassword;

    String name;
    String password;
    int attempts = 2;

    // Fonts
    Font myFont = new Font("Times New Roman", Font.PLAIN, 30);
    Font myFont2 = new Font("Helvetica", Font.BOLD, 20);
    Font myFont3 = new Font("Times New Roman", Font.PLAIN, 15);

    Main(){
        // frame settings
        frame = new JFrame();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setBounds(0, 0, 700, 400);
        frame.setFocusable(false);
        frame.setResizable(false);

        // First part ----
        panel = new JPanel(); // Panel where all the things will be.
        panel.setLayout(null);
        panel.setBounds(100, 25, 500, 300);

        // Error message in case if the input doesn't match any file's name in the folder.
        errorMessage = new JLabel("There is no such a user!");
        errorMessage.setLayout(null);
        errorMessage.setBounds(0, -10, 500, 300);
        errorMessage.setHorizontalAlignment(JLabel.CENTER);
        errorMessage.setVerticalAlignment(JLabel.BOTTOM);
        errorMessage.setForeground(new Color(220,0,0));
        errorMessage.setFont(myFont2);
        errorMessage.setVisible(false); // Setting it false because we don't need it now.
        panel.add(errorMessage);

        // Just a text.
        label = new JLabel("Tell Me Your Name");
        label.setFont(myFont);
        label.setLayout(null);
        label.setVerticalAlignment(JLabel.TOP);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setBounds(0, 100, 500, 300);
        panel.add(label);

        // Button that refers to the registration page.
        regPageButton = new JButton("Register");
        regPageButton.addActionListener(this);
        regPageButton.setFont(new Font("Helvetica", Font.PLAIN, 15));
        regPageButton.setLayout(null);
        regPageButton.setBounds(0, 150, 100, 50);
        regPageButton.setFocusable(false);
        panel.add(regPageButton);

        // Input is here.
        textField = new JTextField();
        textField.setLayout(null);
        textField.setBounds(100, 150, 300, 50);
        textField.setFont(myFont2);
        textField.setHorizontalAlignment(JTextField.CENTER);
        panel.add(textField);

        // Submit button.
        button = new JButton("Submit");
        button.addActionListener(this);
        button.setLayout(null);
        button.setBounds(200, 215, 100, 25);
        button.setFont(myFont3);
        button.setHorizontalAlignment(JButton.CENTER);
        panel.add(button);

        // Second part ----
        passPanel = new JPanel();
        passPanel.setLayout(null);
        passPanel.setBounds(100, 25, 500, 300);
        passPanel.setVisible(false); // Making it invisible because we don't need it in the beginning.

        // Text settings are here, but the title is in actionPerformed().
        passLabel = new JLabel();
        passLabel.setFont(myFont);
        passLabel.setLayout(null);
        passLabel.setVerticalAlignment(JLabel.TOP);
        passLabel.setHorizontalAlignment(JLabel.CENTER);
        passLabel.setBounds(0, 100, 500, 300);
        passPanel.add(passLabel);

        // Input is here.
        passTextField = new JTextField();
        passTextField.setLayout(null);
        passTextField.setBounds(100, 150, 300, 50);
        passTextField.setFont(myFont2);
        passTextField.setHorizontalAlignment(JTextField.CENTER);
        passPanel.add(passTextField);

        // Button is here.
        passButton = new JButton("Submit");
        passButton.addActionListener(this);
        passButton.setLayout(null);
        passButton.setBounds(200, 215, 100, 25);
        passButton.setFont(myFont3);
        passButton.setHorizontalAlignment(JButton.CENTER);
        passPanel.add(passButton);

        // Error message which works only when there's wrong answer.
        passErrorMessage = new JLabel();
        passErrorMessage.setLayout(null);
        passErrorMessage.setBounds(0, -10, 500, 300);
        passErrorMessage.setHorizontalAlignment(JLabel.CENTER);
        passErrorMessage.setVerticalAlignment(JLabel.BOTTOM);
        passErrorMessage.setForeground(new Color(220,0,0));
        passErrorMessage.setFont(myFont2);
        passErrorMessage.setVisible(false); // Making it invisible because we don't need it now.
        passPanel.add(passErrorMessage);

        // Success message which works only when there's correct answer.
        passSuccessMessage = new JLabel("Correct! Welcome!");
        passSuccessMessage.setLayout(null);
        passSuccessMessage.setBounds(0, -10, 500, 300);
        passSuccessMessage.setHorizontalAlignment(JLabel.CENTER);
        passSuccessMessage.setVerticalAlignment(JLabel.BOTTOM);
        passSuccessMessage.setForeground(new Color(0,220,0));
        passSuccessMessage.setFont(myFont2);
        passSuccessMessage.setVisible(false); // Making it invisible because we don't need it now.
        passPanel.add(passSuccessMessage);

        // Third Part --- Registration ----

        regPanel = new JPanel();
        regPanel.setLayout(null);
        regPanel.setBounds(100, 25, 500, 300);
        regPanel.setVisible(false); // Making it invisible because we don't need it in the beginning.

        regLabel = new JLabel("Type The Following Data");
        regLabel.setFont(myFont);
        regLabel.setLayout(null);
        regLabel.setVerticalAlignment(JLabel.TOP);
        regLabel.setHorizontalAlignment(JLabel.CENTER);
        regLabel.setBounds(0, 100, 500, 300);
        regPanel.add(regLabel);

        // Fields for Name and Password
        regName = new JTextField();
        regName.setLayout(null);
        regName.setBounds(180, 150, 200, 50);
        regName.setFont(myFont2);
        regPanel.add(regName);
        regNameLabel = new JLabel("Name");
        regNameLabel.setFont(myFont2);
        regNameLabel.setLayout(null);
        regNameLabel.setBounds(100, 150, 200, 50);
        regPanel.add(regNameLabel);

        regPassword = new JTextField();
        regPassword.setLayout(null);
        regPassword.setBounds(180, 200, 200, 50);
        regPassword.setFont(myFont2);
        regPanel.add(regPassword);
        regPasswordLabel = new JLabel("Password");
        regPasswordLabel.setFont(myFont2);
        regPasswordLabel.setLayout(null);
        regPasswordLabel.setBounds(70, 200, 200, 50);
        regPanel.add(regPasswordLabel);

        regSubmitButton = new JButton("Submit");
        regSubmitButton.addActionListener(this);
        regSubmitButton.setLayout(null);
        regSubmitButton.setBounds(200, 265, 100, 25);
        regSubmitButton.setFont(myFont3);
        regPanel.add(regSubmitButton);

        // Adding to the frame
        frame.add(panel);
        frame.add(regPanel);
        frame.add(passPanel);
        frame.setVisible(true);
    } // End of Main() constructor.

    public static void main(String[] args) {
        Main main = new Main();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Checking for the button from name page.
        if (e.getSource() == button) {
            try {
                name = textField.getText(); // Taking name
                File file = new File(textField.getText() + ".txt"); // Checking for existing file

                Scanner scanFile = new Scanner(file);

                while (scanFile.hasNextLine()) {
                    password = scanFile.nextLine();
                }

                // If there's nothing wrong with that, then main panel disappears and another one comes after.
                // Otherwise, errorMessage appears.
                panel.setVisible(false);
                passLabel.setText("Your password, Mr. " + name);
                passPanel.setVisible(true);
            } catch (Exception ex) {
                errorMessage.setVisible(true);
            }
        } // End of checking button.

        // Checking for the button from password page.
        if (e.getSource() == passButton) {

            // If the password is correct, then show and do certain actions.
            if (passTextField.getText().equals(password)) {
                passErrorMessage.setVisible(false);
                passSuccessMessage.setVisible(true);
                passTextField.setEditable(false);
                passTextField.setFocusable(false);
                passButton.setFocusable(false);
            } else { // else, check for amount of attempts and do specific actions after that.
                if (attempts != 0) {
                    passErrorMessage.setVisible(true);
                    passErrorMessage.setText("Wrong password! You have " + attempts + " more attempts.");
                    attempts--;
                } else {
                    passErrorMessage.setText("Wrong password! You have " + attempts + " attempts.");
                    passErrorMessage.setFocusable(false);
                    passTextField.setEditable(false);
                    passButton.setEnabled(false);
                    passButton.setFocusable(false);
                }
            }
        } // End of checking passButton.

        // Checking for the button that refers to the registration page.
        if (e.getSource() == regPageButton) {
            panel.setVisible(false);
            regPanel.setVisible(true);
        }

        // Checking whether the submit button in registration page was clicked.
        if (e.getSource() == regSubmitButton) {
            if (regName.getText().isEmpty() || regPassword.getText().isEmpty()); // If any field is empty, then do nothing.
            else { // Else, create a new txt file with password inside of it.
                try {
                    PrintWriter writer = new PrintWriter(regName.getText() + ".txt", StandardCharsets.UTF_8);
                    writer.write(regPassword.getText());
                    writer.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }

                regPanel.setVisible(false);
                panel.setVisible(true);
            }
        }
    } // End of actionPerformed().
} // End of Main class.