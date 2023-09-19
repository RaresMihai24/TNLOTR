package com.mycompany.tnlotr1;

import static com.mycompany.tnlotr1.DatabaseManager.getConnection;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;

public class RegistrationService {

    public static boolean registerUser(String username, String password, String confirmPassword, String email, String referralID, JCheckBox checkbox) throws SQLException, UnknownHostException {
            Connection con = null;
            InetAddress ipAddress = InetAddress.getLocalHost();
            String hostAddress = ipAddress.getHostAddress();
            con = DriverManager.getConnection("jdbc:mysql://localhost/alpha", "root", "");

            if (username.length() < 6 || username.length() > 20) {
                JOptionPane.showMessageDialog(null, "Username must be between 6 and 20 characters.");
                return false;
            }

            String checkQuery = "SELECT COUNT(*) FROM accounts WHERE account_name = ?";
            PreparedStatement checkStatement = con.prepareStatement(checkQuery);
            checkStatement.setString(1, username);
            ResultSet resultSet = checkStatement.executeQuery();

            if (resultSet.next() && resultSet.getInt(1) > 0) {
                JOptionPane.showMessageDialog(null, "Username is already taken. Please choose a different one.");
                return false;
            }

            String passwordRegex = "^(?=.*[A-Z])(?=.*[0-9])(?=.*[@#$%^&+=!])(?=\\S+$).{8,}$";
            if (!password.matches(passwordRegex)) {
                JOptionPane.showMessageDialog(null, "Password must have at least:\n - one uppercase letter\n - one number\n - one special character\n - be at least 8 characters long");
                return false;
            }

            if (!password.equals(confirmPassword)) {
                JOptionPane.showMessageDialog(null, "Passwords do not match.");
                return false;
            }

            String emailRegex = "^(.+)@(yahoo\\.com|gmail\\.com)$";
            if (!email.matches(emailRegex)) {
                JOptionPane.showMessageDialog(null, "Please enter a valid Yahoo or Gmail address.\n Any other type of address is not accepted.");
                return false;
            }

            String checkQuery2 = "SELECT COUNT(*) FROM accounts WHERE mail = ?";
            PreparedStatement checkStatement2 = con.prepareStatement(checkQuery2);
            checkStatement2.setString(1, email);
            ResultSet resultSet2 = checkStatement2.executeQuery();

            if (resultSet2.next() && resultSet2.getInt(1) > 0) {
                 JOptionPane.showMessageDialog(null, "Email address is already in use. Please use a different one.");
                return false;
            }
            

if (!referralID.equals("Referral ID")) {
    if (!referralID.isEmpty()) {
        String checkReferralQuery = "SELECT COUNT(*) FROM accounts WHERE account_name = ?";
        PreparedStatement checkReferralStatement = con.prepareStatement(checkReferralQuery);
        checkReferralStatement.setString(1, referralID);
        ResultSet referralResultSet = checkReferralStatement.executeQuery();

        if (!referralResultSet.next() || referralResultSet.getInt(1) == 0) {
            JOptionPane.showMessageDialog(null, "Referral ID does not exist. Please enter a valid one.");
        }
    } else {
        referralID = "";
    }
}

             if (!checkbox.isSelected()) {
                JOptionPane.showMessageDialog(null, "You must agree to the terms and conditions.");
                return false;
            }
        
        try {
            String vcode = generateRandomVerificationCode();
            getConnection();
            String insertReferralQuery = "INSERT INTO refferal_manager (refferal_id, reffered_player) VALUES (?, ?)";
            PreparedStatement referralPreparedStatement = con.prepareStatement(insertReferralQuery);
            referralPreparedStatement.setString(1, referralID);
            referralPreparedStatement.setString(2, username);
            int rowsAffected1 = referralPreparedStatement.executeUpdate();

            String insertQuery = "INSERT INTO accounts (account_name, password, mail, ip_address, vcode) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = con.prepareStatement(insertQuery);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            preparedStatement.setString(3, email);
            preparedStatement.setString(4, hostAddress);
            preparedStatement.setString(5, vcode);

            int rowsAffected = preparedStatement.executeUpdate();

           if (rowsAffected > 0) {
                EmailSender.sendEmail(email, vcode);
                JOptionPane.showMessageDialog(null, "Registration successful!");
            } else {
                JOptionPane.showMessageDialog(null, "Error during registration. Please try again later.");
            }} catch (Exception e) {
    e.printStackTrace();
}
        return false;
        } 


    private static String generateRandomVerificationCode() {
        return String.format("%06d", new Random().nextInt(1000000));
    }

}
