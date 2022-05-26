package test.main.IntegrationTest;

import main.controller.DataManage;
import main.controller.ChoiceController;
import main.model.Meal;
import main.model.Order;
import main.model.Payment;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class mealScenarioTest {

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
    public void mealSelection() throws IOException{
        System.out.println("test basicMealSelection");
        choiceController.basicMealChoose("8201465425955","low-fat");
        DataManage instance = DataManage.getInstance();
        ArrayList<Order> orderArrayList = instance.getOrders();
        ArrayList<Meal> mealArrayList = instance.getMeals();
        for (Order order:orderArrayList){
            if (order.getOrderNo().equals("8201465425955")){
                for (Meal meal:mealArrayList){
                    if (meal.getMealNo().equals(order.getMealNo())){
                        System.out.println(meal.getBasicMeal());
                        assertEquals("low-fat",meal.getBasicMeal(),"basicMealChoose isn't correct");
                    }
                }
            }
        }
        System.out.println("test extraMealSelection");

        ArrayList<String> extraMeal = new ArrayList<>();
        extraMeal.add("Beef");
        extraMeal.add("Juice");
        choiceController.extraMealChoose("8201465425955",extraMeal);
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
}
