package com.mycompany.tnlotr1;

import javax.swing.*;
import java.awt.*;

public class IntroPage extends JFrame implements LoginEventListener{
    private Image backgroundImage;
    private final JFrame frame;
    private final int width = 1920;
    private final int height = 1080;
    private final JPanel contentPane = new JPanel() {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    };
        LoginPanel loginPanel = new LoginPanel();
        RegisterPanel registerPanel = new RegisterPanel();
    public IntroPage() {
        loginPanel.setLoginEventListener(this);
        frame = new JFrame("TNLOTR");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        backgroundImage = new ImageIcon("C:\\Users\\Rares\\Documents\\NetBeansProjects\\TNLOTR1\\background.jpg").getImage();
        loginPanel.loadCredentialsFromFile();

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(4, 4, 5, 5);

        contentPane.setLayout(null);

        loginPanel.setBounds(1350, 800, 300, 150);
        registerPanel.setBounds(400, 500, 270, 300);
        contentPane.add(loginPanel);
        contentPane.add(registerPanel);
        frame.setContentPane(contentPane);
        frame.setSize(width, height);
        frame.setResizable(false);
        frame.setVisible(true);
    }

    @Override
    public void onLoginButtonClicked(String username, String password) {
        backgroundImage = new ImageIcon("C:\\Users\\Rares\\Documents\\NetBeansProjects\\TNLOTR1\\blank.jpg").getImage();
        contentPane.remove(loginPanel); 
        contentPane.remove(registerPanel); 
        frame.revalidate();
        frame.repaint();
        frame.setVisible(true);
    }

}
