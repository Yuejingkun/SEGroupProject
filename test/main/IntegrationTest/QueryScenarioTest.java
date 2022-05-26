package test.main.IntegrationTest;

import main.controller.DataManage;
import main.controller.QueryController;
import main.model.Flight;
import main.model.Order;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class QueryScenarioTest {
    String orderId = "8201465425955";
    String rightSurname = "Alice";
    String wrongSurname = "Shaw";
    String idNo = "190896542";
    QueryController queryController = new QueryController();
    DataManage instance = DataManage.getInstance();

    public QueryScenarioTest() throws IOException {
    }

    @BeforeEach
    void setUp() {
        System.out.println("begin....");
    }

    @AfterEach
    void tearDown() {
        System.out.println("finish...");
    }

    @Test
    public void queryMethodOne() throws IOException {
        System.out.println("We will query by Booking number");
        System.out.println("Please input your Booking number...");
        System.out.println("Then click the query button!");
        HashMap<Order, Flight> ret = queryController.queryByBookNum(orderId);
        System.out.println("Welcome!");
        assertFalse(ret.isEmpty());
        ArrayList<Order> orderArrayList = instance.getOrders();
        for (Order order:orderArrayList){
            if (order.getOrderNo().equals(orderId)){
                System.out.println("show the information of your flight!");
                System.out.println(ret.get(order).toString());
            }
        }
    }

    @Test
    public void queryMethodTwo() throws IOException{
        System.out.println("We will query by your identification");
        System.out.println("Please input your surname and id");
        System.out.println("Then click the query button!");
        assertFalse(queryController.checkById(wrongSurname,idNo));
        System.out.println("You should input the right surname and id number");
        assertTrue(queryController.checkById(rightSurname,idNo));
        HashMap<Order, Flight> ret = queryController.queryByID(idNo);
        System.out.println("Welcome!");
        assertFalse(ret.isEmpty());
        ArrayList<Order> orderArrayList = instance.getOrders();
        for (Order order:orderArrayList){
            if (order.getPassenger_IDNo().equals(idNo)){
                System.out.println("show the information of your flight!");
                System.out.println(ret.get(order).toString());
            }
        }
    }

    @Test
    public void queryMethodThree() throws IOException{
        System.out.println("We will query by your document");
        System.out.println("Please input your document");
        System.out.println("Then click the query button!");
        System.out.println("Welcome!");
        System.out.println("show the information of your flight!");
        System.out.println("Flight{flight='RJ5955', flightTime='2022-04-01-18:00', departure='Beijing', destination='London'}");
    }

}
