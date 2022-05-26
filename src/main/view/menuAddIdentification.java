package main.view;
import main.controller.DataManage;
import main.controller.JudgeController;
import main.controller.QueryController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;

/**
 * Add ID card and confirm all previously selected information.
 * @author Tianze Li
 * @version 1.0
 */
public class menuAddIdentification {
    private JFrame frame0;
    private JPanel panel0;
    private JButton buttonBack;
    private JButton buttonNext;
    private JTextField textFieldIDNum;
    private JCheckBox CheckBoxConfirm;
    private JLabel Img;
    private JudgeController judgeController = new JudgeController();
    private QueryController queryController = new QueryController();
    public menuAddIdentification(String orderId) {

        //页面生成区域
        frame0 = new JFrame("menuAddIdentification");
        URL url = this.getClass().getResource("/img/kongjie.png");
        ImageIcon img = new ImageIcon(url);
        img.setImage(img.getImage().getScaledInstance(350,250, Image.SCALE_DEFAULT));
        Img.setIcon(img);

        frame0.setContentPane(panel0);
        frame0.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame0.pack();
        frame0.setVisible(true);
        frame0.setSize(800,500);
        frame0.setResizable(false);

        //事件监听区
        buttonNext.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //跳转到打印页面
                try {
                    if (judgeController.isCorrectID(orderId,textFieldIDNum.getText()) && CheckBoxConfirm.isSelected()){
                        queryController.getOrderInfo(orderId).setStatus(1);
                        DataManage.getInstance().commit();
                        System.out.println(DataManage.getInstance().getOrders().get(0).getStatus());
                        new menuPrint(orderId);
                        frame0.dispose();
                    }
                    else if (!CheckBoxConfirm.isSelected()){
                        JOptionPane.showMessageDialog(null,
                                "You must confirm the checkbox!",
                                "Error",JOptionPane.ERROR_MESSAGE);
                    }
                    else if (!judgeController.isCorrectID(orderId,textFieldIDNum.getText())){
                        JOptionPane.showMessageDialog(null,
                                "Wrong ID number!",
                                "Error",JOptionPane.ERROR_MESSAGE);
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
        buttonBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //跳转回信息展示页面
                new menuShowInfo(orderId);
                frame0.dispose();

            }
        });
    }

//    public static void main(String[] args) {
//        JFrame frame = new JFrame("menuAddIdentification");
////        URL url = this.getClass().getResource("/img/kongjie.png");
//        ImageIcon img5 = new ImageIcon("src/resource/img/kongjie.png");
//        img5.setImage(img5.getImage().getScaledInstance(400,300, Image.SCALE_DEFAULT));
//
//        frame.setContentPane(new menuAddIdentification("11").panel0);
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.pack();
//        frame.setVisible(true);
//        frame.setSize(800,500);
//    }
}
