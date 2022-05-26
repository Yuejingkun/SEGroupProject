package main.view;

import main.controller.QueryController;
import main.model.Baggage;
import main.model.Flight;
import main.model.Order;
import main.model.Passenger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Show all information and print.
 * This interface will show the Boarding pass , Carry-on tag and its checked information card . They can be printed.
 * @Author Tianze Li
 * @version 1.0
 */
public class menuPrint {
    private JFrame frame0;
    private JPanel panel0;
    private JButton buttonPrint;
    private JTextArea textAreaPass;
    private JTextArea textAreaCard;
    private JTextArea textAreaTag;
    private QueryController queryController = new QueryController();

    public menuPrint(String orderId) {
        //页面生成区域
        frame0 = new JFrame("menuPrint");
        frame0.setContentPane(panel0);
        frame0.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame0.pack();
        frame0.setVisible(true);
        frame0.setSize(800,500);
        frame0.setResizable(false);

        try {
            Flight flightInfo=queryController.getFlightInfo(queryController.getFlightId(orderId));//航班信息
            Order orderInfo=queryController.getOrderInfo(orderId);//订单信息
            Passenger passengerInfo=queryController.getPassengerInfo(orderId);//乘客信息
            ArrayList<Baggage> baggageArrayList= queryController.checkBaggage(orderId);//行李信息（包括托运行李和随身行李//)


            String Boarding_info="Name: "+passengerInfo.getfName()+" "+passengerInfo.getSurName()+"\n"+
                    "ID number: "+passengerInfo.getIDNo()+"\n"+
                    "Flight_id: "+flightInfo.getFlight()+"\n"+
                    "Flight_time: "+flightInfo.getFlightTime()+"\n"+
                    "Departure: "+flightInfo.getDeparture()+"\n"+
                    "Destination: "+flightInfo.getDestination()+"\n"+
                    "Seat: "+orderInfo.getSeat()+"\n"+
                    "Meal: "+orderInfo.getMealNo();

            textAreaPass.setText(Boarding_info);
            textAreaPass.setFont(new Font(null, Font.PLAIN, 15));
           String check_info="Your baggageNo is: ";
           int i=0;
            for(;i<baggageArrayList.size();i++){
                check_info+=baggageArrayList.get(i).getBaggageNo()+"\n";
            }
            check_info+="So this is your taking code: "+"CJHDIIUEHW";
            textAreaCard.setText(check_info);
            textAreaTag.setText("CJHDIIUEHW");
            textAreaCard.setFont(new Font(null, Font.PLAIN, 15));
            textAreaTag.setFont(new Font(null, Font.PLAIN, 30));

        } catch (IOException e) {
            e.printStackTrace();
        }


        //事件监听区域
        buttonPrint.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null,"Print successfully!","Success",JOptionPane.PLAIN_MESSAGE);
                //System.out.println("Print successfully");
                frame0.dispose();
                new feed_back();
            }
        });
    }


    //测试用
    public static void main(String[] args) {
               new menuPrint("sdfs");
    }
}
