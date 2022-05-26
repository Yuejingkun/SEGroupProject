package main.view;

import main.controller.ChoiceController;
import main.controller.QueryController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;

/**
 * This class is for Seat selection .
 * In this interface , you can select Business Class or Economic Class and also select the actual seat .
 * @Author Tianze Li
 * @version 1.0
 */
public class menuSelectSeat {
    private JFrame frame0;//当前页面容器
    private JPanel panel0;
    private JPanel panelTop;
    private JPanel panelMid;
    private JPanel panelBot;
    private JPanel panelBotLeft;
    private JPanel panelBotRight;
    private JButton buttonNext;
    private JButton buttonBack;
    private JTabbedPane tabbedPane1;
    private JPanel panelMidRight;
    private JPanel panelMidLeft;
    private JPanel BusinessRight;
    private JPanel BusinessLeft;
    private JComboBox comboBoxBusiRow;
    private JComboBox comboBoxSusiSeat;
    private JLabel Img;
    private JPanel EconomyLeft;
    private JPanel EconomyRight;
    private JComboBox comboBoxEcoRow;
    private JComboBox comboBoxEcoSeat;
    private JLabel labelTop;
    private ChoiceController choiceController = new ChoiceController();
    private QueryController queryController = new QueryController();

    public menuSelectSeat(String orderId) {

        frame0 = new JFrame("menuSelectSeat");
        URL url1 = this.getClass().getResource("/img/seat.png");
        ImageIcon img = new ImageIcon(url1);
        img.setImage(img.getImage().getScaledInstance(400,300, Image.SCALE_DEFAULT));
        Img.setIcon(img);

        frame0.setContentPane(panel0);
        frame0.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame0.pack();
        frame0.setVisible(true);
        frame0.setSize(800, 500);
        frame0.setResizable(false);


        //监听事件部分
        buttonNext.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //提取、存储相关数据
                String flightNo = null;
                try {
                    flightNo = queryController.getFlightId(orderId);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                int[][] seatchoosed = new int[12][20];
                try {
                    seatchoosed = queryController.flightChoosed(flightNo);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }

                int index = tabbedPane1.getSelectedIndex();
                if(index == 0){ //business class
                    String row =(String)comboBoxBusiRow.getItemAt(comboBoxBusiRow.getSelectedIndex());
                    String column =(String)comboBoxSusiSeat.getItemAt(comboBoxSusiSeat.getSelectedIndex());

                    System.out.println(row+column);

                    if (seatchoosed[Integer.parseInt(row)][column.charAt(0)-'A']==0){

                        try {
                            choiceController.seatChoose(orderId,"0"+row+column,0);
                            /*
                                //此段代码为测试座位是否真正写入order类里，已经测试成功，可以删除----------------
                            DataManage instance = DataManage.getInstance();
                            ArrayList<Order> orderArrayList = instance.getOrders();
                            for (Order temp:orderArrayList){
                                if (temp.getOrderNo().equals(orderId)){
                                    System.out.println(temp.getSeat());
                                }
                            }
                                //---------------------------------------------------------------------
                            */
                        } catch (IOException ioException) {
                            ioException.printStackTrace();
                        }
                        //跳转到餐品选择界面
                        new  menuSelectMeal(orderId);
                        frame0.dispose();
                    }
                    else{
                        JOptionPane.showMessageDialog(null,
                                "This seat has been chosen by others! Please choose again!",
                                "Error",JOptionPane.ERROR_MESSAGE);
                    }
                }
                else{       //economy class
                    String row =(String)comboBoxEcoRow.getItemAt(comboBoxEcoRow.getSelectedIndex());
                    String column =(String)comboBoxEcoSeat.getItemAt(comboBoxEcoSeat.getSelectedIndex());
                    System.out.println(row+column);
                    if(row.length()==1){
                        row ="0"+row;
                    }
                    if (seatchoosed[Integer.parseInt(row)][column.charAt(0)-'A']==0){
                        try {
                            choiceController.seatChoose(orderId,row+column,1);
                            /*
                                //此段代码为测试座位是否真正写入order类里，已经测试成功，可以删除----------------
                            DataManage instance = DataManage.getInstance();
                            ArrayList<Order> orderArrayList = instance.getOrders();
                            for (Order temp:orderArrayList){
                                if (temp.getOrderNo().equals(orderId)){
                                    System.out.println(temp.getSeat());
                                }
                            }
                                //---------------------------------------------------------------------
                            */
                        } catch (IOException ioException) {
                            ioException.printStackTrace();
                        }
                        //跳转到餐品选择界面
                        new  menuSelectMeal(orderId);
                        frame0.dispose();
                    }
                    else{
                        JOptionPane.showMessageDialog(null,
                                "This seat has been chosen by others! Please choose again!",
                                "Error",JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
        buttonBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    new Custom_query_result(queryController.queryByBookNum(orderId));
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
                frame0.dispose();
            }
        });
    }
}
