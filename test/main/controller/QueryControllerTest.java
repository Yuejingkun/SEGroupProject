package test.main.controller;

import main.controller.DataManage;
import main.controller.QueryController;
import main.model.Baggage;
import main.model.Flight;
import main.model.Order;
import main.model.Passenger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

/**
 *  The class tests the tool methods of query.
 *  @Author Jingkun Yue
 *  @Version 1.0
 */
class QueryControllerTest {

    QueryController queryController = new QueryController();

    @BeforeEach
    void setUp() {
        System.out.println("begin....");
    }

    @AfterEach
    void tearDown() {
        System.out.println("finish...");
    }

    @Test
    void queryByBookNum() throws IOException {
        System.out.println("test queryByBookNum");
        HashMap<Order, Flight> ret = queryController.queryByBookNum("8201465425955");
        //System.out.println(ret);
        DataManage instance = DataManage.getInstance();
        ArrayList<Order> orderArrayList = instance.getOrders();
        ArrayList<Flight> flightArrayList = instance.getFlights();

        for (Order order:orderArrayList){
            if (order.getOrderNo().equals("8201465425955")){
                for (Flight flight:flightArrayList){
                    if (flight.getFlight().equals(order.getFlight_flightNo())){
                        assertEquals(ret.get(order), flight, "queryByBookNum isn't correct!");
                    }
                }
            }
        }
    }

    @Test
    void checkById() throws IOException {
        System.out.println("test checkById");
        assertTrue(queryController.checkById("Alice", "190896542"),"checkById isn't correct!");
        assertFalse(queryController.checkById("Alice","190897284"),"checkById isn't correct!");
    }

    @Test
    void queryByID() throws IOException {
        System.out.println("test queryByID");
        HashMap<Order, Flight> ret = queryController.queryByID("190896542");
        //System.out.println(ret);
        DataManage instance = DataManage.getInstance();
        ArrayList<Order> orderArrayList = instance.getOrders();
        ArrayList<Flight> flightArrayList = instance.getFlights();

        for (Order order:orderArrayList){
            if (order.getPassenger_IDNo().equals("190896542")){
                for (Flight flight:flightArrayList){
                    if (flight.getFlight().equals(order.getFlight_flightNo())){
                        assertEquals(ret.get(order), flight, "queryByID isn't correct!");
                    }
                }
            }
        }
    }

    @Test
    void flightShow() throws IOException {
        System.out.println("test flightShow!");
        DataManage instance = DataManage.getInstance();
        ArrayList<Flight> flight = instance.getFlights();
        ArrayList<Flight> ret = queryController.flightShow();
        assertEquals(flight,ret,"flightShow isn't correct!");
    }

    @Test
    void flightSearch() throws IOException {
        System.out.println("test flightSearch!");
        DataManage instance = DataManage.getInstance();
        ArrayList<Passenger> passengerArrayList = instance.getPassengers();
        ArrayList<Passenger> ret = queryController.flightSearch("RJ5955");
        System.out.println(ret);
        ArrayList<Order> orderArrayList = instance.getOrders();
        for (Order order:orderArrayList){
            if (order.getFlight_flightNo().equals("RJ5955")){
                String passNo = order.getPassenger_IDNo();
                for (Passenger passenger:passengerArrayList){
                    if (passenger.getIDNo().equals(passNo)){
                        assertTrue(ret.contains(passenger),"flightSearch isn't correct!");
                    }
                }
            }
        }
    }

    @Test
    void loginCheck() throws IOException {
        System.out.println("test loginCheck!");
        assertTrue(queryController.loginCheck("101","111111"),"loginCheck isn't correct!");
        assertFalse(queryController.loginCheck("101","222222"),"loginCheck isn't correct!");
    }

    @Test
    void checkBaggage() throws IOException {
        System.out.println("test checkBaggage!");
        ArrayList<Baggage> ret = queryController.checkBaggage("8201465425955");
        DataManage instance = DataManage.getInstance();
        ArrayList<Baggage> baggageArrayList = instance.getBaggages();
        for (Baggage baggage:baggageArrayList){
            if (baggage.getOrder_orderNo().equals("8201465425955")){
                assertTrue(ret.contains(baggage),"checkBaggage isn't correct!");
            }
        }
    }

    @Test
    void flightChoosed() throws IOException {
        System.out.println("test flightChoosed!");
        int[][] ret = queryController.flightChoosed("RJ5955");
        assertEquals(1,ret[1][0],"flightChoosed isn't correct!");
        assertEquals(0,ret[2][0],"flightChoosed isn't correct!");
    }

    @Test
    void getFlightId() throws IOException {
        System.out.println("test getFlightId!");
        String ret = queryController.getFlightId("8201465425955");
        assertEquals("RJ5955",ret,"getFlightId isn't correct!");
    }

    @Test
    void getMealNameByType() throws IOException {
        System.out.println("test getMealNameByType!");
        String standard = queryController.getMealNameByType(0);
        String lowFat = queryController.getMealNameByType(1);
        String vegetarian = queryController.getMealNameByType(2);
        String halal = queryController.getMealNameByType(3);
        assertEquals("standard",standard,"getMealNameByType isn't correct!");
        assertEquals("low-fat",lowFat,"getMealNameByType isn't correct!");
        assertEquals("vegetarian",vegetarian,"getMealNameByType isn't correct!");
        assertEquals("halal",halal,"getMealNameByType isn't correct!");

    }

    @Test
    void getFlightInfo() {
        System.out.println("test getFlightInfo!");
        Flight ret = queryController.getFlightInfo("RJ5955");
        assertEquals("Beijing",ret.getDeparture(),"getFlightInfo isn't correct!");
        assertEquals("London",ret.getDestination(),"getFlightInfo isn't correct!");
        assertEquals("2022-04-01-18:00",ret.getFlightTime(),"getFlightInfo isn't correct!");
    }

    @Test
    void getOrderInfo() {
        System.out.println("test getOrderInfo!");
        Order ret = queryController.getOrderInfo("8201013215955");
        assertEquals("RJ5955",ret.getFlight_flightNo(),"getOrderInfo isn't correct!");
        assertEquals("103",ret.getMealNo(),"getOrderInfo isn't correct!");
        assertEquals("190895321",ret.getPassenger_IDNo(),"getOrderInfo isn't correct!");
        assertEquals("59550618",ret.getPayNo(),"getOrderInfo isn't correct!");
        assertEquals("01A",ret.getSeat(),"getOrderInfo isn't correct!");
        assertEquals(1,ret.getStatus(),"getOrderInfo isn't correct!");
    }

    @Test
    void getPassengerInfo() {
        System.out.println("test getPassengerInfo");
        Passenger ret = queryController.getPassengerInfo("8201465425955");
        assertEquals("190896542",ret.getIDNo(),"getPassengerInfo isn't correct!");
        assertEquals("An",ret.getfName(),"getPassengerInfo isn't correct!");
        assertEquals("Alice",ret.getSurName(),"getPassengerInfo isn't correct!");
    }
}