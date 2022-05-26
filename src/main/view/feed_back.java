package main.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

/**
 * This class is for getting feed-back from users.
 * After you print the information successfully,it will be displayed.
 *
 * @version 1.0
 * @Author Tingyu Xie
 */
public class feed_back {
    private JTextField textField1;
    private JLabel label1;
    private JLabel label2;
    private JLabel label3_1;
    private JPanel panel0;
    private JPanel panel1;
    private JPanel panel2;
    private JPanel panel1_1;
    private JPanel panel1_2;
    private JLabel Img;
    private JPanel panel3;
    private JLabel label4;
    private JButton submitButton;
    private JLabel label3_2;

    public static void main(String[] args) {
        feed_back f = new feed_back();
    }

    public feed_back() {
        JFrame frame = new JFrame("Feed Back");
        frame.setContentPane(panel0);

        URL url = this.getClass().getResource("/img/kongjie.png");
        ImageIcon img = new ImageIcon(url);
        img.setImage(img.getImage().getScaledInstance(350, 300, Image.SCALE_DEFAULT));
        Img.setIcon(img);

        label1.setFont(new Font(null, Font.PLAIN, 20));
        label2.setFont(new Font(null, Font.PLAIN, 20));
        label3_1.setFont(new Font(null, Font.PLAIN, 20));
        label4.setFont(new Font(null, Font.PLAIN, 20));

        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setSize(800, 500);
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(textField1.getText());
                frame.dispose();
            }
        });
    }
}
