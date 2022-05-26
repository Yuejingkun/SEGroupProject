package main.controller;

import main.model.*;

import java.io.IOException;
import java.util.*;

/**
 * The class stores the tool methods of query.
 *
 * @Author ZheLiu
 * @Version 1.0
 */
public class QueryController implements QueryUtl {
    /**
     * This method implements how to query the flight and order information
     * by inputing orderId
     *
     * @param orderNo
     * @return A HashMap records the objective information
     * @throws IOException
     */
    @Override
    public HashMap<Order, Flight> queryByBookNum(String orderNo) throws IOException {
        DataManage instance = DataManage.getInstance();
        ArrayList<Order> orderArrayList = instance.getOrders();
        ArrayList<Flight> flightArrayList = instance.getFlights();
        HashMap<Order, Flight> ret = new HashMap<>();
        Order key = null;
        Flight val = null;
        for (Order temp : orderArrayList) {
            if (temp.getOrderNo().equals(orderNo)) {
                key = temp;
                String flightNo = key.getFlight_flightNo();
                for (Flight temp1 : flightArrayList) {
                    if (temp1.getFlight().equals(flightNo)) {
                        val = temp1;
                        ret.put(key, val);
                        return ret;
                    }
                }
            }
        }
        return null;
    }

    /**
     * check if the name and idNo input by the users is valid
     *
     * @param surName
     * @param idNo
     * @return if valid return true, else return false
     * @throws IOException
     */
    @Override
    public boolean checkById(String surName, String idNo) throws IOException {
        DataManage instance = DataManage.getInstance();
        ArrayList<Passenger> passengerArrayList = instance.getPassengers();
        for (Passenger temp : passengerArrayList) {
            if (temp.getIDNo().equals(idNo) && temp.getSurName().equals(surName))
                return true;
        }
        return false;
    }

    /**
     * After checking the input is valid,
     * we should execute the query by the idNo of the passengers
     *
     * @param idNo
     * @return A HashMap records the order and flight information
     * @throws IOException
     */
    @Override
    public HashMap<Order, Flight> queryByID(String idNo) throws IOException {
        DataManage instance = DataManage.getInstance();
        ArrayList<Order> orderArrayList = instance.getOrders();
        ArrayList<Flight> flightArrayList = instance.getFlights();
        HashMap<Order, Flight> ret = new HashMap<>();
        Order key = null;
        Flight val = null;
        for (Order temp : orderArrayList) {
            if (temp.getPassenger_IDNo().equals(idNo)) {
                key = temp;
                String flightNo = key.getFlight_flightNo();
                for (Flight temp1 : flightArrayList) {
                    if (temp1.getFlight().equals(flightNo)) {
                        val = temp1;
                        ret.put(key, val);
                        return ret;
                    }
                }
            }
        }
        return null;
    }

    /**
     * This method helps to show all the flight information
     *
     * @return An ArrayList which records all the information of flights
     */
    public ArrayList<Flight> flightShow() throws IOException {
        DataManage instance = DataManage.getInstance();
        ArrayList<Flight> flight = instance.getFlights();
        return flight;
    }

    /**
     * This method helps to search the flight information when the
     * hostress wants to check all the information of passengers
     *
     * @param flightID
     * @return An ArrayList which records all the information of passengers
     */
    @Override
    public ArrayList<Passenger> flightSearch(String flightID) throws IOException {
        ArrayList<Passenger> retPassengers = new ArrayList<>();
        DataManage instance = DataManage.getInstance();
        ArrayList<Order> orderArrayList = instance.getOrders();
        ArrayList<Passenger> allPassengers = instance.getPassengers();
        for (Order temp : orderArrayList) {
            if (temp.getFlight_flightNo().equals(flightID)) {
                String passNo = temp.getPassenger_IDNo();
                for (Passenger temp1 : allPassengers) {
                    if (temp1.getIDNo().equals(passNo)) {
                        retPassengers.add(temp1);
                    }
                }
            }
        }
        return retPassengers;
    }

    /**
     * login check
     *
     * @param Account
     * @param password
     * @return if valid return true.
     * @throws IOException
     */
    @Override
    public boolean loginCheck(String Account, String password) throws IOException {
        DataManage instance = DataManage.getInstance();
        ArrayList<Administrator> administrators = instance.getAdministrators();
        for (Administrator temp : administrators) {
            if (temp.getUserName().equals(Account) && temp.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Query for baggage information
     *
     * @param orderID
     * @return all the baggage information of a specific passenger
     * @throws IOException
     */
    @Override
    public ArrayList<Baggage> checkBaggage(String orderID) throws IOException {
        ArrayList<Baggage> retBaggage = new ArrayList<>();
        DataManage instance = DataManage.getInstance();
        ArrayList<Baggage> baggageArrayList = instance.getBaggages();
        for (Baggage temp : baggageArrayList) {
            if (temp.getOrder_orderNo().equals(orderID)) {
                retBaggage.add(temp);
            }
        }
        return retBaggage;
    }

    /**
     * 
     * @param flightID
     * @return
     * @throws IOException
     */
    public int[][] flightChoosed(String flightID) throws IOException {
        DataManage instance = DataManage.getInstance();
        ArrayList<Order> orderArrayList = instance.getOrders();
        int[][] a = new int[12][20] ;
        for (Order temp:orderArrayList){
            if (temp.getFlight_flightNo().equals(flightID)){
                if (temp.getSeat()!=null){
                    int row = Integer.parseInt(temp.getSeat().substring(0,2));
                    int column = temp.getSeat().charAt(2)-'A';
                    a[row][column]=1;
                }
            }
        }
        return a;
    }

    public String getFlightId(String orderID) throws IOException{
        DataManage instance = DataManage.getInstance();
        ArrayList<Order> orderArrayList = instance.getOrders();
        for (Order temp:orderArrayList){
            if (temp.getOrderNo().equals(orderID)){
                return temp.getFlight_flightNo();
            }
        }
        return "";
    }

    public String getMealNameByType(int type) throws IOException{
        if (type == 0){
            return "standard";
        }
        else if (type==1){
            return "low-fat";
        }
        else if (type==2){
            return "vegetarian";
        }
        else if (type==3){
            return "halal";
        }
        return null;
    }

    public Flight getFlightInfo(String FlightId){
        DataManage instance = null;
        try {
            instance = DataManage.getInstance();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ArrayList<Flight> flightArrayList = instance.getFlights();
        for(Flight temp:flightArrayList){
            if(temp.getFlight().equals(FlightId)){
                return temp;
            }
        }
        return null;
    }

    public Order getOrderInfo(String OrderId){
        DataManage instance = null;
        try {
            instance = DataManage.getInstance();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ArrayList<Order> flightArrayList = instance.getOrders();
        for(Order temp:flightArrayList){
            if(temp.getOrderNo().equals(OrderId)){
                return temp;
            }
        }
        return null;
    }
    public Passenger getPassengerInfo(String OrderId){
        DataManage instance = null;
        try {
            instance = DataManage.getInstance();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ArrayList<Order> orderArrayList = instance.getOrders();
        ArrayList<Passenger> passengerArrayList=instance.getPassengers();
        for(Order temp:orderArrayList){
            if(temp.getOrderNo().equals(OrderId)){
                String passengerId=temp.getPassenger_IDNo();
                for(Passenger passenger:passengerArrayList){
                    if(passenger.getIDNo().equals(passengerId)){
                        return passenger;
                    }
                }
            }
        }
        return null;
    }
}



