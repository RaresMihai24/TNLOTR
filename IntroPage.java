package com.mycompany.tnlotr1;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.sql.SQLException;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class IntroPage extends JFrame {
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
    private final LoginPanel loginPanel = new LoginPanel();
    private final RegisterPanel registerPanel = new RegisterPanel();

    public IntroPage() throws LineUnavailableException, UnsupportedAudioFileException, IOException, SQLException {

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

}
