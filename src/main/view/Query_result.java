package main.view;

import main.model.Passenger;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.ArrayList;

/**
 * Click the flight that appears in the table which is in Flight_information.java
 * (currently unable to jump from the previous page) to display the passenger list of the flight)
 * @Author Tingyu Xie and Tianze Li
 * @version 1.0
 */
public class Query_result {
    private JButton backButton;
    private JPanel panel0;
    private JPanel panel1;
    private JPanel panel3;
    private JPanel panel2;
    private JLabel label1;
    private JTable table1;

    public Query_result(ArrayList<Passenger> passengers) {
        JFrame frame = new JFrame("Query_result");
        frame.setContentPane(panel0);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 500);
        frame.setVisible(true);
        frame.setResizable(false);

        label1.setFont(new Font(null, Font.PLAIN, 25));
        URL url1 = this.getClass().getResource("/img/1.jpg");
        ImageIcon bg = new ImageIcon(url1);
        JLabel label = new JLabel(bg);
        label.setSize(800, 500);
        frame.getLayeredPane().add(label, Integer.valueOf(Integer.MIN_VALUE));

        panel0.setOpaque(false);
        panel1.setOpaque(false);
        panel3.setOpaque(false);
        panel2.setOpaque(false);

        //Traverse the ArrayList<Passenger> to output
        String[][] passengersTable = new String[passengers.size()][3];
        String[] tableHeader={"IDNo","surName","fName"};
        int i = 0;
        for (Passenger p : passengers) {
            passengersTable[i][0] = p.getIDNo().toString();
            passengersTable[i][1] = p.getSurName().toString();
            passengersTable[i][2] = p.getfName().toString();

            i++;
        }

        table1.setModel(new DefaultTableModel(passengersTable,tableHeader));
        table1.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        table1.setRowHeight(30);

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Flight_information f = new Flight_information();
                frame.dispose();
            }
        });
    }

//    public static void main(String[] args) {
//        Query_result q = new Query_result();
//    }
}
