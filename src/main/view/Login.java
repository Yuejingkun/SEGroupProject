package main.view;

import main.controller.QueryController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;

/**
 * This class is for manager's login.
 * After pressing the button for administrators in choose.java ,
 * you go in this interface and need to input the right account and password to login.
 * @author Tingyu Xie
 * @version 1.0
 */
public class Login {
    private JTextField accountField;
    private JButton confirmButton;
    private JButton backButton;
    private JPanel panel0;
    private JPanel panel1;
    private JPanel panel1_1;
    private JPanel panel1_2;
    private JPanel panel1_3;
    private JPanel panel1_2_1;
    private JPanel panel1_2_2;
    private JPasswordField passwordField;
    private JPanel panel2;
    private JLabel label2;
    private JLabel label1;
    private QueryController queryController = new QueryController();
    public Login() {
        JFrame frame = new JFrame("Administrator Login");
        frame.setContentPane(panel0);
        URL url = this.getClass().getResource("/img/flyer.png");
        ImageIcon bg = new ImageIcon(url);
        JLabel label = new JLabel(bg);
        label.setSize(800, 500);
        frame.getLayeredPane().add(label, Integer.valueOf(Integer.MIN_VALUE));
        frame.setResizable(false);

        panel0.setOpaque(false);
        panel1.setOpaque(false);
        panel1_1.setOpaque(false);
        panel1_2.setOpaque(false);
        panel1_3.setOpaque(false);
        panel1_2_1.setOpaque(false);
        panel1_2_2.setOpaque(false);
        panel2.setOpaque(false);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 500);
        frame.setVisible(true);
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if(queryController.loginCheck(accountField.getText(), new String(passwordField.getPassword()))){
                        Flight_information flight_information=new Flight_information();
                        frame.dispose();
                    }
                    else{
                        JOptionPane.showMessageDialog(null,
                                "Wrong username and password! Pleas input again!",
                                "Error",JOptionPane.ERROR_MESSAGE);
                    }
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Choose c = new Choose();
                frame.dispose();
            }
        });
    }

    public static void main(String[] args) {
        Login l = new Login();
    }
}
