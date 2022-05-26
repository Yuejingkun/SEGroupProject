package test.main.IntegrationTest;
import main.controller.ChoiceController;
import main.controller.DataManage;
import main.model.Order;
import main.model.Payment;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class seatScenarioTest {
    ChoiceController choiceController= new ChoiceController();
    String orderNo = "8203048215955";

    @BeforeEach
    void setUp() {
        System.out.println("begin....");
    }

    @AfterEach
    void tearDown() {
        System.out.println("finish...");
    }

    @Test
    void seatSelection ()throws IOException{
        System.out.println("test seatSelection");
        choiceController.seatChoose("8204169425955","4A",1);
        choiceController.seatChoose("8201465425955","1A",0);
        DataManage instance = DataManage.getInstance();
        ArrayList<Order> orderArrayList = instance.getOrders();
        ArrayList<Payment> paymentArrayList = instance.getPayments();
        for (Order order:orderArrayList){
            if (order.getOrderNo().equals("8204169425955")){
                System.out.println(order.getSeat());
                assertEquals("4A",order.getSeat(),"type1 seatChoose isn't correct");
            }
            if (order.getOrderNo().equals("8201465425955")){
                System.out.println(order.getSeat());
                assertEquals("1A",order.getSeat(),"type0 seatChoose isn't correct");
                for (Payment payment:paymentArrayList){
                    if (order.getPayNo().equals(payment.getPayNo())){
                        System.out.println(payment.getCost());
                        assertEquals(100.0,payment.getCost(),"the extra payment of seatChoose isn't correct");
                    }
                }
            }
        }
    }
}
