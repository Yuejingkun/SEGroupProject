package main.view;

import main.controller.ChoiceController;
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
 * Display the selected check-in information, etc., which can be modified. If modified, jump to menuSelectSeat.java.
 * @Author Tingyu Xie and Tianze Li
 * @version 1.0
 */
public class menuShowInfo {
    private JFrame frame0;
    private JPanel panel0;
    private JButton buttonBack;
    private JButton buttonNext;
    private JPanel panelTop;
    private JPanel panelLeft;
    private JPanel panelRight;
    private JTextArea textAreaFlightInfo;
    private JTextArea textArea1;
    private JTextArea textArea2;
    private JButton buttonModify;
    private QueryController queryController = new QueryController();
    private ChoiceController choiceController = new ChoiceController();
    public menuShowInfo(String orderId){
        //页面生成区域
        frame0 = new JFrame("menuShowInfo");
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

            String f_info="Flight_id: "+flightInfo.getFlight()+"\n"+
                    "Flight_time: "+flightInfo.getFlightTime()+"\n"+
                    "Departure: "+flightInfo.getDeparture()+"\n"+
                    "Destination: "+flightInfo.getDestination();
            String check_info="Name: "+passengerInfo.getfName()+" "+passengerInfo.getSurName()+"\n"+
                    "ID number: "+passengerInfo.getIDNo()+"\n"+
                    "Booking number: "+orderInfo.getOrderNo()+"\n"+
                    "Seat: "+orderInfo.getSeat()+"\n"+
                    "Meal: "+orderInfo.getMealNo();

            String[][] baggageTable = new String[baggageArrayList.size()][4];
            int i=0;
            for(;i<baggageArrayList.size();i++)
            {
                baggageTable[i][0] = baggageArrayList.get(i).getBaggageNo();
                baggageTable[i][1] = String.valueOf(baggageArrayList.get(i).getQuality());
                baggageTable[i][2] = baggageArrayList.get(i).getSize();
                baggageTable[i][3] = baggageArrayList.get(i).getBaggageType();
            }
            String bag_info="";
            for(int j=0;j<i;j++){
                bag_info+="Baggage number: "+baggageTable[j][0]+"\n"+
                        "Quality: "+baggageTable[j][1]+"\n"+
                        "Size: "+baggageTable[j][2]+"\n"+
                        "Type: "+baggageTable[j][3]+"\n";
            }


            textAreaFlightInfo.setText(f_info);
            textArea1.setText(check_info);
            textArea2.setText(bag_info);
            textAreaFlightInfo.setFont(new Font(null, Font.PLAIN, 15));
            textArea1.setFont(new Font(null, Font.PLAIN, 15));
            textArea2.setFont(new Font(null, Font.PLAIN, 15));

        } catch (IOException e) {
            e.printStackTrace();
        }

        //事件监听区域
        buttonModify.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    choiceController.defaultMeal(orderId);
                    choiceController.defaultPayment(orderId);
                    choiceController.defaultSeat(orderId);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }

                //跳转回座位选择界面
                new menuSelectSeat(orderId);
                frame0.dispose();

            }
        });

        buttonNext.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //跳转到扫描身份证并确认界面
                new menuAddIdentification(orderId);
                frame0.dispose();
            }
        });
    }

    public static void main(String[] args) {
        new menuShowInfo("10000");
    }
}
