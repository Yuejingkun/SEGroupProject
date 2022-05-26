package test.main.controller;



import main.controller.ChoiceController;
import main.controller.DataManage;
import main.model.Meal;
import main.model.Order;
import main.model.Payment;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

/**
 *  The class tests the tool methods of modification.
 *  @Author Jingkun Yue
 *  @Version 1.0
 */

class ChoiceControllerTest {

    ChoiceController choiceController = new ChoiceController();

    @BeforeEach
    void setUp() {
        System.out.println("begin....");
    }

    @AfterEach
    void tearDown() {
        System.out.println("finish...");
    }

    @Test
    void seatChoose() throws IOException {
        System.out.println("test seatChoose");
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

    @Test
    void basicMealChoose() throws IOException {
        System.out.println("test basicMealChoose");
        choiceController.basicMealChoose("8201465425955","standard");
        DataManage instance = DataManage.getInstance();
        ArrayList<Order> orderArrayList = instance.getOrders();
        ArrayList<Meal> mealArrayList = instance.getMeals();
        for (Order order:orderArrayList){
            if (order.getOrderNo().equals("8201465425955")){
                for (Meal meal:mealArrayList){
                    if (meal.getMealNo().equals(order.getMealNo())){
                        System.out.println(meal.getBasicMeal());
                        assertEquals("standard",meal.getBasicMeal(),"basicMealChoose isn't correct");
                    }
                }
            }
        }
    }

    @Test
    void extraMealChoose() throws IOException {
        System.out.println("test extraMealChoose");
        ArrayList<String> extraMeal = new ArrayList<String>();
        extraMeal.add("Beef");
        extraMeal.add("Juice");
        choiceController.extraMealChoose("8201465425955",extraMeal);
        DataManage instance = DataManage.getInstance();
        ArrayList<Order> orderArrayList = instance.getOrders();
        ArrayList<Meal> mealArrayList = instance.getMeals();
        ArrayList<Payment> paymentArrayList = instance.getPayments();
        for (Order order:orderArrayList){
            if (order.getOrderNo().equals("8201465425955")){
                for (Meal meal:mealArrayList){
                    if (meal.getMealNo().equals(order.getMealNo())){
                        System.out.println(meal.getExtraMeal());
                        assertEquals(extraMeal,meal.getExtraMeal(),"extraMealChoose isn't correct");
                        assertEquals(12.5,meal.getExtraCost(),"extraCost isn't correct");
                        for (Payment payment:paymentArrayList){
                            if (payment.getPayNo().equals(order.getPayNo())){
                                System.out.println(payment.getCost());
                                assertEquals(12.5,payment.getCost(),"payment of extraMealChoose isn't correct");
                            }
                        }
                    }
                }
            }
        }
    }

    @Test
    void pay() throws IOException {
        System.out.println("test pay");
        DataManage instance = DataManage.getInstance();
        ArrayList<Order> orderArrayList = instance.getOrders();
        ArrayList<Payment> paymentArrayList = instance.getPayments();
        for (Order order:orderArrayList){
            if (order.getOrderNo().equals("8201465425955")){
                for (Payment payment:paymentArrayList){
                    if (payment.getPayNo().equals(order.getPayNo())){
                        payment.setCost(10.0);
                        System.out.println(payment.getCost());
                        assertEquals(10.0,payment.getCost());
                        choiceController.pay("8201465425955","3822714694");
                        System.out.println(payment.getCost());
                        assertEquals(0.0,payment.getCost(),"pay isn't correct");
                    }
                }
            }
        }

    }

    @Test
    void defaultPayment() throws IOException {
        System.out.println("test defaultPayment");
        DataManage instance = DataManage.getInstance();
        ArrayList<Order> orderArrayList = instance.getOrders();
        ArrayList<Payment> paymentArrayList = instance.getPayments();
        for (Order order:orderArrayList){
            if (order.getOrderNo().equals("8201465425955")){
                for (Payment payment:paymentArrayList){
                    if (payment.getPayNo().equals(order.getPayNo())){
                        payment.setCost(10.0);
                        System.out.println(payment.getCost());
                        assertEquals(10.0,payment.getCost());
                        choiceController.defaultPayment("8201465425955");
                        System.out.println(payment.getCost());
                        assertEquals(0.0,payment.getCost(),"defaultPayment isn't correct");
                    }
                }
            }
        }
    }

    @Test
    void defaultSeat() throws IOException {
        System.out.println("test defaultSeat");
        DataManage instance = DataManage.getInstance();
        ArrayList<Order> orderArrayList = instance.getOrders();
        for (Order order:orderArrayList){
            if (order.getOrderNo().equals("8201465425955")){
                order.setSeat("1A");
                System.out.println(order.getSeat());
                assertEquals("1A",order.getSeat());
                choiceController.defaultSeat("8201465425955");
                System.out.println(order.getSeat());
                assertEquals(null,order.getSeat(),"defaultSeat isn't correct");
            }
        }
    }

    @Test
    void defaultMeal() throws IOException {
        System.out.println("test defaultMeal");
        DataManage instance = DataManage.getInstance();
        ArrayList<Order> orderArrayList = instance.getOrders();
        ArrayList<Meal> mealArrayList = instance.getMeals();
        ArrayList<String> extraMeal = new ArrayList<>();
        extraMeal.add("Beef");
        extraMeal.add("Juice");
        for (Order order:orderArrayList){
            if (order.getOrderNo().equals("8201465425955")){
                for (Meal meal:mealArrayList){
                    if (meal.getMealNo().equals(order.getMealNo())){
                        meal.setBasicMeal("standard");
                        meal.setExtraMeal(extraMeal);
                        meal.setExtraCost(12.5);
                        System.out.println(meal.getBasicMeal());
                        System.out.println(meal.getExtraMeal());
                        assertEquals("standard",meal.getBasicMeal());
                        assertEquals(extraMeal,meal.getExtraMeal());
                        assertEquals(12.5,meal.getExtraCost());
                        choiceController.defaultMeal("8201465425955");
                        System.out.println(meal.getBasicMeal());
                        System.out.println(meal.getExtraMeal());
                        assertEquals(null,meal.getBasicMeal(),"defaultBasicMeal isn't correct");
                        assertEquals(null,meal.getExtraMeal(),"defaultExtraMeal isn't correct");
                        assertEquals(0.0,meal.getExtraCost(),"defaultExtraCost isn't correct");
                    }
                }
            }
        }
    }
}