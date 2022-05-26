package main.view;

import main.controller.ChoiceController;
import main.controller.JudgeController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

/**
 * This class is to handle extra pay.
 * If you choose the extra meal or first class's seat , you will turn to this interface and input credit number to pay the extra money.
 *
 * @author Tianze Li and Tingyu Xie
 * @version 1.0
 */
public class menuSelectExtraMeal {
    private JFrame frame0;
    private JPanel panel0;
    private JButton buttonBack;
    private JButton buttonNext;
    private JCheckBox hamburgerCheckBox;
    private JCheckBox beefCheckBox;
    private JCheckBox coffeeCheckBox;
    private JCheckBox juiceCheckBox;
    private ChoiceController choiceController = new ChoiceController();
    //private QueryController queryController = new QueryController();
    private JudgeController judgeController = new JudgeController();

    public menuSelectExtraMeal(String orderId) {
        frame0 = new JFrame("menuSelectExtraMeal");
        frame0.setContentPane(panel0);
        frame0.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame0.pack();
        frame0.setVisible(true);
        frame0.setSize(800, 500);
        frame0.setResizable(false);

        buttonBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new menuSelectMeal(orderId);
                frame0.dispose();

            }
        });
        buttonNext.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<String> extraMeal = new ArrayList<String>();
                int flag = 0;
                System.out.println("flag is " + flag);
                try {
                    if (hamburgerCheckBox.isSelected()) {
                        extraMeal.add("Hamburger");
                        flag = 1;
                    }
                    if (beefCheckBox.isSelected()) {
                        extraMeal.add("Beef");
                        flag = 1;
                    }
                    if (coffeeCheckBox.isSelected()) {
                        extraMeal.add("Coffee");
                        flag = 1;
                    }
                    if (juiceCheckBox.isSelected()) {
                        extraMeal.add("Juice");
                        flag = 1;
                    }
                    choiceController.extraMealChoose(orderId, extraMeal);
                    if (flag == 1) {
                        new menuAddCreditCard(orderId);
                    } else if (flag == 0 && judgeController.isExtraSeat(orderId)) {
                        new menuAddCreditCard(orderId);
                    } else if (flag == 0 && !judgeController.isExtraSeat(orderId)) {
                        new menuShowInfo(orderId);
                    }
                    frame0.dispose();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }

            }
        });
    }


    public static void main(String[] args) {
        new menuSelectExtraMeal("");
    }
}
