package main.view;

import main.model.Flight;
import main.model.Order;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;

/**
 * The reservation information is obtained according to the query results in Custom_query.java ,
 * the flight to check in can be selected in the table.
 *
 * @version 1.0
 * @Author Tingyu Xie
 */
public class Custom_query_result {
    private JButton backButton;
    private JPanel panel0;
    private JPanel panel1;
    private JPanel panel3;
    private JLabel label1;
    private JPanel panel2;
    private JTable table1;
    private JPanel panel1_1;
    private JPanel panel1_1_1;
    private JPanel panel1_1_2;
    private JLabel label1_1;
    private JTextField textField1;
    private JButton enterButton;

    public Custom_query_result(HashMap<Order, Flight> map) {
        JFrame frame = new JFrame("Custom_query_result");
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
        panel2.setOpaque(false);
        panel3.setOpaque(false);
        panel1_1.setOpaque(false);
        panel1_1_1.setOpaque(false);
        panel1_1_2.setOpaque(false);


        //Traverse HashMap<Order,Flight> map to get the order information
        String[] tableHeader = {"orderNo", "flightNo", "flightTime", "departure", "destination", "status"};
        String[][] mapTable = new String[map.size()][6];
        int i = 0;
        for (Iterator<Order> itr = map.keySet().iterator(); itr.hasNext(); ) {
            Order order = itr.next();
            Flight flight = map.get(order);
            mapTable[i][0] = order.getOrderNo().toString();
            mapTable[i][1] = order.getFlight_flightNo().toString();
            mapTable[i][2] = flight.getFlightTime().toString();
            mapTable[i][3] = flight.getDeparture().toString();
            mapTable[i][4] = flight.getDestination().toString();
            mapTable[i][5] = "" + order.getStatus();
            i++;
        }
        table1.setModel(new DefaultTableModel(mapTable, tableHeader));
        table1.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
//        table1.getColumnModel().getColumn(2).setPreferredWidth(150);
//        table1.getColumnModel().getColumn(0).setPreferredWidth(150);
//        table1.getColumnModel().getColumn(1).setPreferredWidth(100);
//        table1.getColumnModel().getColumn(3).setPreferredWidth(100);
//        table1.getColumnModel().getColumn(4).setPreferredWidth(100);
//        table1.getColumnModel().getColumn(5).setPreferredWidth(100);
        table1.setRowHeight(30);

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Custom_query c = new Custom_query();
                frame.dispose();
            }
        });
        enterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < map.size(); i++) {
                    if (mapTable[i][0].equals(textField1.getText())) {
                        if (mapTable[i][5].equals("1")) {
                            new menuPrint(textField1.getText());//需要传orderNo
                        } else {
                            new menuSelectSeat(textField1.getText());//需要传orderNo
                        }
                        frame.dispose();
                    } else {
                        JOptionPane.showMessageDialog(null,
                                "Wrong booking number!",
                                "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }

            }
        });
    }

    public static void main(String[] args) {
        new menuSelectSeat("8201465425955");
    }
}
