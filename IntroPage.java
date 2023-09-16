package tnlotr;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.*;



public class IntroPage extends JFrame{
    private Image backgroundImage;
    JFrame frame;
    JButton buttonLogin = new JButton("Login");
    JButton buttonRegister = new JButton("Register");
    public final int width = 1920;
    public final int height = 1080;
    
    JPanel contentPane = new JPanel() {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    };
    
    JPanel loginPanel = new JPanel();
    JPanel registerPanel = new JPanel();
    JPanel selectLanguage = new JPanel();

    public IntroPage() {
        frame = new JFrame("TNLOTR");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JTextField usernameLogin = createPlaceholderTextField("Username");
        usernameLogin.setPreferredSize(new Dimension(160, 30));
        JPasswordField passwordLogin = createPlaceholderPasswordField("Password");
        passwordLogin.setPreferredSize(new Dimension(160, 30));
        JTextField usernameRegister = createPlaceholderTextField("Username");
        usernameRegister.setPreferredSize(new Dimension(160, 30));
        JPasswordField registerPassword = createPlaceholderPasswordField("123456789");
        registerPassword.setPreferredSize(new Dimension(160, 30));
        JPasswordField registerPasswordConf = createPlaceholderPasswordField("123456789");
        registerPasswordConf.setPreferredSize(new Dimension(160, 30));
        JTextField emailRegister = createPlaceholderTextField("example@address.com");
        emailRegister.setPreferredSize(new Dimension(160, 30));
        JTextField refferalID = createPlaceholderTextField("Referral ID");
        refferalID.setPreferredSize(new Dimension(160, 30));
        JCheckBox checkBox = new JCheckBox("I agree to the terms and conditions");
        JCheckBox checkBox2 = new JCheckBox("Remember me");
        backgroundImage = new ImageIcon("background.jpg").getImage();

        usernameLogin.setHorizontalAlignment(JTextField.CENTER);
        passwordLogin.setHorizontalAlignment(JTextField.CENTER);
        usernameRegister.setHorizontalAlignment(JTextField.CENTER);
        registerPassword.setHorizontalAlignment(JTextField.CENTER);
        registerPasswordConf.setHorizontalAlignment(JTextField.CENTER);
        emailRegister.setHorizontalAlignment(JTextField.CENTER);
        refferalID.setHorizontalAlignment(JTextField.CENTER);
        loginPanel.setLayout(new GridBagLayout());


        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(4, 4, 5, 5);

        gbc.gridy = 0;
        loginPanel.add(usernameLogin, gbc);

        gbc.gridy = 1;
        loginPanel.add(passwordLogin, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 2;
        
        loginPanel.add(checkBox2, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 3;
        loginPanel.add(buttonLogin, gbc);

        
        GridBagConstraints gbc2 = new GridBagConstraints();
        gbc2.insets = new Insets(5, 5, 5, 5);
        registerPanel.setLayout(new GridBagLayout());
        registerPanel.setPreferredSize(new Dimension(200, 100));
        gbc2.gridy = 0;
        registerPanel.add(usernameRegister, gbc2);
        gbc2.gridy = 1;
        registerPanel.add(registerPassword, gbc2);
        gbc2.gridy = 2;
        registerPanel.add(registerPasswordConf, gbc2);
        gbc2.gridy = 3;
        registerPanel.add(emailRegister, gbc2);
        gbc2.gridy = 4;
        registerPanel.add(refferalID, gbc2);
        gbc2.gridy = 5;
        registerPanel.add(checkBox, gbc2);
        gbc2.gridy = 6;
        registerPanel.add(buttonRegister, gbc2);
        
        contentPane.setLayout(null);

        loginPanel.setBounds(1350, 800, 300, 150);
        registerPanel.setBounds(400, 500, 270, 300);
   
        contentPane.add(loginPanel);
        contentPane.add(registerPanel);

        frame.setContentPane(contentPane);
        frame.setSize(width, height);
        buttonLogin.setVisible(true);
        frame.setResizable(false);
        frame.setVisible(true);

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
    

