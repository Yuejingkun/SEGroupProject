package main.view;
import main.controller.ChoiceController;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * If the extra payment item is selected, jump to this interface; otherwise, jump to menuShowInfo.java .
 * @Author Tianze Li
 * @version 1.0
 */
public class menuAddCreditCard {
    private JFrame frame0;
    private JPanel panel0;
    private JPanel panelBot;
    private JButton buttonBack;
    private JButton buttonNext;
    private JPanel panelTop;
    private JPanel panelMid;
    private JTextField textField1;
    private ChoiceController choiceController = new ChoiceController();


    public menuAddCreditCard(String orderId) {
        //页面生成区域
        frame0 = new JFrame("menuAddCreditCard");
        frame0.setContentPane(panel0);
        frame0.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame0.pack();
        frame0.setVisible(true);
        frame0.setSize(800,500);
        frame0.setResizable(false);

        //监听事件区域
        buttonNext.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("next");
                //跳转到信息展示页面,得判断信用卡是否存在
                try {
                    choiceController.pay(orderId,textField1.getText());
                    new menuShowInfo(orderId);
                    frame0.dispose();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
        buttonBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("back");
                //跳回餐品选择界面
                new menuSelectMeal(orderId);
                frame0.dispose();

            }
        });
    }

    //测试用
//    public static void main(String[] args) {
//        JFrame frame = new JFrame("menuAddCreditCard");
//        frame.setContentPane(new menuAddCreditCard().panel0);
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.pack();
//        frame.setVisible(true);
//        frame.setSize(800,500);
//    }
}
