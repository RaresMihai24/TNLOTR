package com.mycompany.tnlotr1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoginPanel extends JPanel {
    private JTextField usernameLogin;
    private JPasswordField passwordLogin;
    private JButton buttonLogin;
    private JCheckBox checkBox2;

    public LoginPanel() {
        usernameLogin = createPlaceholderTextField("Username");
        passwordLogin = createPlaceholderPasswordField("Password");
        usernameLogin.setPreferredSize(new Dimension(160, 30));
        passwordLogin.setPreferredSize(new Dimension(160, 30));
        usernameLogin.setHorizontalAlignment(JTextField.CENTER);
        passwordLogin.setHorizontalAlignment(JTextField.CENTER);
        buttonLogin = new JButton("Login");
        checkBox2 = new JCheckBox("I want to be remembered", true);
        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc2 = new GridBagConstraints();
        gbc2.insets = new Insets(4, 4, 5, 5);

        gbc2.gridy = 0;
        add(usernameLogin, gbc2);

        gbc2.gridy = 1;
        add(passwordLogin, gbc2);

        gbc2.gridx = 0;
        gbc2.gridy = 2;
        add(checkBox2, gbc2);

        gbc2.gridx = 0;
        gbc2.gridy = 3;
        add(buttonLogin, gbc2);

buttonLogin.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        String username = usernameLogin.getText();
        String password = String.valueOf(passwordLogin.getPassword());

        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please enter both username and password.");
            return;
        }

        try {
            if (verifyLogin(username, password)) {
                if (checkBox2.isSelected()) {
                    saveCredentialsToFile(username, password);
                }
                
                JOptionPane.showMessageDialog(null, "Login performed succesfully. Entering the game in a second.");
                
            } else {
                JOptionPane.showMessageDialog(null, "Invalid username or password.");
            }
        } catch (SQLException ex) {
            Logger.getLogger(IntroPage.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(IntroPage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
});

        checkBox2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (checkBox2.isSelected()) {
                    try (PrintWriter writer = new PrintWriter("saved_account")) {
                        writer.println(usernameLogin.getText());
                        writer.println(passwordLogin.getText());
                    } catch (FileNotFoundException ex) {
                        ex.printStackTrace();
                    }
                } else {
                    File savedAccountFile = new File("saved_account");
                    if (savedAccountFile.exists()) {
                        savedAccountFile.delete();
                        usernameLogin.setText("");
                        passwordLogin.setText("");
                    }
                }
            }
        });

        File savedAccountFile = new File("saved_account");
        if (savedAccountFile.exists() && checkBox2.isSelected()) {
            try (Scanner scanner = new Scanner(savedAccountFile)) {
                String savedUsername = scanner.nextLine();
                String savedPassword = scanner.nextLine();
                usernameLogin.setText(savedUsername);
                passwordLogin.setText(savedPassword);
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            }
        }

    }

    public static JTextField createPlaceholderTextField(String placeholder) {
        JTextField textField = new JTextField(placeholder);

        textField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (textField.getText().equals(placeholder)) {
                    textField.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (textField.getText().isEmpty()) {
                    textField.setText(placeholder);
                }
            }
        });

        return textField;
    }

    public static JPasswordField createPlaceholderPasswordField(String placeholder) {
        JPasswordField passwordField = new JPasswordField(placeholder);

        passwordField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (String.valueOf(passwordField.getPassword()).equals(placeholder)) {
                    passwordField.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (String.valueOf(passwordField.getPassword()).isEmpty()) {
                    passwordField.setText(placeholder);
                }
            }
        });

        return passwordField;
    }
    
    private boolean verifyLogin(String username, String password) throws SQLException {
    Connection con = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    try {
        con = DriverManager.getConnection("jdbc:mysql://localhost/alpha", "root", "");

        String query = "SELECT COUNT(*) FROM accounts WHERE account_name = ? AND password = ?";
        preparedStatement = con.prepareStatement(query);
        preparedStatement.setString(1, username);
        preparedStatement.setString(2, password);

        resultSet = preparedStatement.executeQuery();

        return resultSet.next() && resultSet.getInt(1) > 0;
    } finally {
        if (resultSet != null) resultSet.close();
        if (preparedStatement != null) preparedStatement.close();
        if (con != null) con.close();
    }
}
    
private void saveCredentialsToFile(String username, String password) throws IOException {
    BufferedWriter writer = new BufferedWriter(new FileWriter("saved_account.txt"));
    writer.write(username + "," + password);
    writer.close();
}

void loadCredentialsFromFile() {
    try {
        File file = new File("saved_account.txt");
        if (file.exists()) {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String[] credentials = reader.readLine().split(",");
            usernameLogin.setText(credentials[0]);
            passwordLogin.setText(credentials[1]);
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
}
    
    
}
