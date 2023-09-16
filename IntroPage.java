package tnlotr;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import javax.swing.*;



public class IntroPage extends JFrame{
    private Image backgroundImage;
    JFrame frame;
    JButton buttonLogin = new JButton("Login");
    JButton buttonRegister = new JButton("Register");
    JTextField usernameLogin = new JTextField("Username");
    JPasswordField passwordLogin = new JPasswordField("Password");
    JTextField usernameRegister = new JTextField("Username");
    JPasswordField registerPassword = new JPasswordField("123456789");
    JPasswordField registerPasswordConf = new JPasswordField("123456789");
    JTextField emailRegister = new JTextField("example@address.com");
    JTextField refferalID = new JTextField("Referral ID");

    
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
    int width = 1920;
    int height = 1080;

    public IntroPage() {
        frame = new JFrame("TNLOTR");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        backgroundImage = new ImageIcon("background.jpg").getImage();

        usernameLogin.setHorizontalAlignment(JTextField.CENTER);
        passwordLogin.setHorizontalAlignment(JTextField.CENTER);
        usernameRegister.setHorizontalAlignment(JTextField.CENTER);
        registerPassword.setHorizontalAlignment(JTextField.CENTER);
        registerPasswordConf.setHorizontalAlignment(JTextField.CENTER);
        emailRegister.setHorizontalAlignment(JTextField.CENTER);
        refferalID.setHorizontalAlignment(JTextField.CENTER);
        loginPanel.setLayout(new GridLayout(3, 1));
        loginPanel.setPreferredSize(new Dimension(200, 100));
        

        loginPanel.add(usernameLogin);
        loginPanel.add(passwordLogin);
        loginPanel.add(buttonLogin);

        registerPanel.setLayout(new GridLayout(6, 1));
        registerPanel.setPreferredSize(new Dimension(200, 100));
        registerPanel.add(usernameRegister);
        registerPanel.add(registerPassword);
        registerPanel.add(registerPasswordConf);
        registerPanel.add(emailRegister);
        registerPanel.add(refferalID);
        registerPanel.add(buttonRegister);

        contentPane.setLayout(null);

        loginPanel.setBounds(1350, 800, 200, 100);
        registerPanel.setBounds(400, 500, 220, 300);
   
        contentPane.add(loginPanel);
        contentPane.add(registerPanel);

        frame.setContentPane(contentPane);
        frame.setSize(width, height);
        buttonLogin.setVisible(true);
        frame.setResizable(false);
        frame.setVisible(true);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
    }
}

