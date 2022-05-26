package main.view;
import main.controller.*;
import main.model.Flight;
import main.model.Order;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
/**
 * This class is for three ways for customers to query reservations.
 * After pressing the button for passengers in choose.java , you go in this interface ,and first way to query is inputting your booking number , second is inputting your surname and your ID number , third is scanning your ID document.
 * @Author Tingyu Xie
 * @version 1.0
 */
public class Custom_query {
    private JPanel panel1;
    private JPanel panel0;
    private JPanel panel2;
    private JPanel panel3;
    private JTabbedPane tabbedPane1;
    private JTextField textField1;
    private JPanel panel3_1;
    private JPanel panel3_2;
    private JLabel label1;
    private JButton searchButton;
    private JButton backButton;
    private JTextField textField2;
    private JTextField textField3;
    private JTextArea textArea1;
    private  QueryController queryController = new QueryController();
    public Custom_query() {
        JFrame frame = new JFrame("Custom_query");
        frame.setContentPane(panel0);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 500);
        frame.setVisible(true);
        frame.setResizable(false);

        label1.setFont(new Font(null, Font.PLAIN, 25));

        URL url = this.getClass().getResource("/img/fly.png");
        ImageIcon bg = new ImageIcon(url);
        JLabel label = new JLabel(bg);
        label.setSize(800, 500);
        frame.getLayeredPane().add(label, Integer.valueOf(Integer.MIN_VALUE));

        panel0.setOpaque(false);
        panel1.setOpaque(false);
        panel3_1.setOpaque(false);
        panel3_2.setOpaque(false);
        panel3.setOpaque(false);

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int index = tabbedPane1.getSelectedIndex();
                    switch(index){
                        case 0:
                            if(queryController.queryByBookNum(textField1.getText())!=null){
                                HashMap<Order, Flight> map = queryController.queryByBookNum(textField1.getText());
                                Custom_query_result cq = new Custom_query_result(map);
                                frame.dispose();
                            }
                            else{
                                JOptionPane.showMessageDialog(null,
                                        "Not Found Information! Pleas input again!",
                                        "Error",JOptionPane.ERROR_MESSAGE);
                            }
                            break;
                        case 1:
                            if(queryController.checkById(textField2.getText(),textField3.getText())&&
                                    queryController.queryByID(textField3.getText())!=null){
                                HashMap<Order, Flight> map = queryController.queryByID(textField3.getText());
                                Custom_query_result cq = new Custom_query_result(map);
                                frame.dispose();
                            }
                            else{
                                JOptionPane.showMessageDialog(null,
                                        "Not Found Information! Pleas input again!",
                                        "Error",JOptionPane.ERROR_MESSAGE);
                            }
                            break;
                        default:
                            System.out.println("Scanning");
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
        Custom_query c = new Custom_query();
    }
}
