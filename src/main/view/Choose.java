package main.view;

import main.controller.DataManage;
import com.formdev.flatlaf.FlatIntelliJLaf;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;

/**
 * This class for Selecting customer identity or manager identity.
 * If you are customer , select the button for passengers , if you are manager , select the button for administrators.
 * @Author Tingyu Xie
 * @version 1.0
 */
public class Choose {
    private JPanel panel0;
    private JLabel label1;
    private JButton passengerbutton;
    private JButton administratorbutton;
    private JPanel panel1;
    private JPanel panel1_2;
    private JLabel label2;
    private JPanel panel1_1;
    private JPanel panel1_3;
    private JPanel panel1_3_1;
    private JPanel panel1_3_2;
    private JPanel panel2;


    public static void main(String[] args) {
        FlatIntelliJLaf.install();
        Choose c = new Choose();

    }

    public Choose() {
        try {
            DataManage dataManage = new DataManage();
        } catch (IOException e) {
            e.printStackTrace();
        }
        JFrame frame = new JFrame("Welcome");
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
        panel1_3_1.setOpaque(false);
        panel1_3_2.setOpaque(false);
        panel2.setOpaque(false);


        label1.setFont(new Font(null, Font.PLAIN, 25));
        label2.setFont(new Font(null, Font.PLAIN, 25));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 500);
        frame.setVisible(true);
        administratorbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Login l = new Login();
                frame.dispose();
            }
        });
        passengerbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Custom_query c = new Custom_query();
                frame.dispose();
            }
        });
    }
}
