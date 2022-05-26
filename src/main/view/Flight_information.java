package main.view;

import main.controller.QueryController;
import main.model.Flight;
import main.model.Passenger;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

/**
 * This class is for displaying of flight information.
 * After you input the right account and password in Login.java , you go in this interface ,
 * and it can display all flight information and be able to search them by flight number.
 *
 * @version 1.0
 * @Author Tingyu Xie and Tianze Li
 */
public class Flight_information {
    private JScrollPane flight_board;
    private JPanel panel2;
    private JPanel panel3;
    private JPanel panel1;
    private JPanel panel0;
    private JPanel panel1_1;
    private JPanel panel1_3;
    private JPanel panel1_2;
    private JTextField textField1;
    private JButton searchButton;
    private JButton backButton;
    private JLabel label1;
    private JTable table1;

    private QueryController queryController = new QueryController();

    public Flight_information() {

        JFrame frame = new JFrame("Flight_information");
        frame.setContentPane(panel0);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 500);
        frame.setVisible(true);

        ArrayList<Flight> flight = null;
        try {
            flight = queryController.flightShow();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String[][] flightTable = new String[flight.size()][4];
        String[] tableHeader={"flight", "flightTime", "departure", "destination"};
        int i = 0;
        for (Flight p : flight) {
            flightTable[i][0] = p.getFlight().toString();
            flightTable[i][1] = p.getFlightTime().toString();
            flightTable[i][2] = p.getDeparture().toString();
            flightTable[i][3] = p.getDestination().toString();
            i++;
        }

        table1.setModel(new DefaultTableModel(flightTable,tableHeader));
//        table1.setSize(new Dimension(800,500));
//        table1.setPreferredSize(new Dimension(800,500));
//        table1.setPreferredScrollableViewportSize(new Dimension(800, 500));
        table1.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
        table1.getColumnModel().getColumn(1).setPreferredWidth(150);
        table1.setRowHeight(30);


        label1.setFont(new Font(null, Font.PLAIN, 15));
        URL url = this.getClass().getResource("/img/1.jpg");
        ImageIcon bg = new ImageIcon(url);
        JLabel label = new JLabel(bg);
        label.setSize(frame.getSize());
        frame.getLayeredPane().add(label, Integer.valueOf(Integer.MIN_VALUE));

        panel0.setOpaque(false);
        panel1.setOpaque(false);
        panel1_1.setOpaque(false);
        panel1_2.setOpaque(false);
        panel1_3.setOpaque(false);
        panel3.setOpaque(false);
        panel2.setOpaque(false);

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (queryController.flightSearch(textField1.getText()) != null) {
                        ArrayList<Passenger> passengers = queryController.flightSearch(textField1.getText());
                        Query_result query_result = new Query_result(passengers);
                        frame.dispose();
                    } else {
                        JOptionPane.showMessageDialog(null,
                                "Not Found Information! Pleas input again!",
                                "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Login l = new Login();
                frame.dispose();
            }
        });
    }

    public static void main(String[] args) {
        Flight_information f = new Flight_information();
    }
}
