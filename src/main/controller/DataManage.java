package main.controller;

import main.model.*;
import com.alibaba.fastjson.JSON;
import java.io.IOException;
import java.util.ArrayList;

/**
 * manage for data including data IO and Initialization
 * @author ZheLiu
 * @version 1.0
 */
public class DataManage {
    public static DataManage instance = null;
    public ArrayList<Administrator> administrators;
    public ArrayList<Baggage> baggages;
    public ArrayList<Flight> flights;
    public ArrayList<Meal> meals;
    public ArrayList<Order> orders;
    public ArrayList<Passenger> passengers;
    public ArrayList<Payment> payments;

    public static DataManage getInstance() throws IOException {
        if (instance == null) {
            instance = new DataManage();
        }
        return instance;
    }

    /**
     * return the arraylist of the administrators
     * @return the information of administrators
     */
    public ArrayList<Administrator> getAdministrators() {
        return administrators;
    }
    /**
     * return the arraylist of the baggage including check-in and carry-on baggage
     * @return the information of administrators
     */
    public ArrayList<Baggage> getBaggages() {
        return baggages;
    }
    /**
     * return the arraylist of the flights
     * @return the information of flights
     */
    public ArrayList<Flight> getFlights() {
        return flights;
    }
    /**
     * return the arraylist of the orders
     * @return the information of orders of all passengers
     */
    public ArrayList<Order> getOrders() {
        return orders;
    }
    /**
     * return the arraylist of the meals
     * @return the information of all kinds of meals in the flight
     */
    public ArrayList<Meal> getMeals() {
        return meals;
    }
    /**
     * return the arraylist of the passengers
     * @return the information of all passengers
     */
    public ArrayList<Passenger> getPassengers() {
        return passengers;
    }
    /**
     * return the arraylist of the payment
     * @return the information of all payments
     */
    public ArrayList<Payment> getPayments() {
        return payments;
    }

    public DataManage() throws IOException{
        passengers= (ArrayList<Passenger>) JSON.parseArray(IO.read("Passenger.json"),Passenger.class);
        orders= (ArrayList<Order>) JSON.parseArray(IO.read("Order.json"),Order.class);
        payments= (ArrayList<Payment>) JSON.parseArray(IO.read("Payment.json"),Payment.class);
        meals= (ArrayList<Meal>)JSON.parseArray(IO.read("Meal.json"),Meal.class);
        flights= (ArrayList<Flight>)JSON.parseArray(IO.read("Flight.json"),Flight.class);
        baggages= (ArrayList<Baggage>)JSON.parseArray(IO.read("Baggage.json"),Baggage.class);
        administrators= (ArrayList<Administrator>)JSON.parseArray(IO.read("Administrator.json"), Administrator.class);
    }

    public void commit() throws IOException {
        System.out.println(JSON.toJSONString(orders,true));
        IO.write("Passenger.json", JSON.toJSONString(passengers,true));
        IO.write("Order.json", JSON.toJSONString(orders,true));
        IO.write("Payment.json", JSON.toJSONString(payments,true));
        IO.write("Meal.json", JSON.toJSONString(meals,true));
        IO.write("Flight.json", JSON.toJSONString(flights,true));
        IO.write("Baggage.json", JSON.toJSONString(baggages,true));
        IO.write("Administrator.json", JSON.toJSONString(administrators,true));
    }
}
