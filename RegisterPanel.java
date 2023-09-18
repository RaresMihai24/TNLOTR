package com.mycompany.tnlotr1;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.net.UnknownHostException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class RegisterPanel extends JPanel {
    private final JTextField usernameRegister = createPlaceholderTextField("Username");
    private final JPasswordField registerPassword = createPlaceholderPasswordField("123456789");
    private final JPasswordField registerPasswordConf = createPlaceholderPasswordField("123456789");
    private final JTextField emailRegister = createPlaceholderTextField("example@address.com");
    private final JTextField referralIDField = createPlaceholderTextField("Referral ID");
    public final JButton buttonRegister = new JButton("Register");
    JCheckBox checkBox = new JCheckBox("I agree to the terms and conditions");

    public RegisterPanel() {
        usernameRegister.setPreferredSize(new Dimension(160, 30));
        registerPassword.setPreferredSize(new Dimension(160, 30));
        registerPasswordConf.setPreferredSize(new Dimension(160, 30));
        emailRegister.setPreferredSize(new Dimension(160, 30));
        referralIDField.setPreferredSize(new Dimension(160, 30));
         usernameRegister.setHorizontalAlignment(JTextField.CENTER);
        registerPassword.setHorizontalAlignment(JTextField.CENTER);
        registerPasswordConf.setHorizontalAlignment(JTextField.CENTER);
        emailRegister.setHorizontalAlignment(JTextField.CENTER);
        referralIDField.setHorizontalAlignment(JTextField.CENTER);
        this.setPreferredSize(new Dimension(270, 300));
        this.setLayout(new GridBagLayout());
        

buttonRegister.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameRegister.getText();
                String password = String.valueOf(registerPassword.getPassword());
                String confirmPassword = String.valueOf(registerPasswordConf.getPassword());
                String email = emailRegister.getText();
                String referralID = referralIDField.getText();

                try {
                    RegistrationService.registerUser(username, password, confirmPassword, email, referralID, checkBox);
                } catch (SQLException ex) {
                } catch (UnknownHostException ex) {
                    Logger.getLogger(IntroPage.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
);
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        gbc.gridy = 0;
        this.add(usernameRegister, gbc);

        gbc.gridy = 1;
        this.add(registerPassword, gbc);

        gbc.gridy = 2;
        this.add(registerPasswordConf, gbc);

        gbc.gridy = 3;
        this.add(emailRegister, gbc);

        gbc.gridy = 4;
        this.add(referralIDField, gbc);

        gbc.gridy = 5;
        this.add(checkBox, gbc);

        gbc.gridy = 6;
        this.add(buttonRegister, gbc);
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
}
