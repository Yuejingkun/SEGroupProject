package main.model;

public class Order {
    private String orderNo;
    private String Flight_flightNo;
    private String Passenger_IDNo;
    private String seat;
    private String mealNo;
    private String payNo;
    private int status;

    public int getStatus() {
        return status;
    }

    public String getSeat() {
        return seat;
    }

    public String getFlight_flightNo() {
        return Flight_flightNo;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public String getMealNo() {
        return mealNo;
    }

    public String getPayNo() {
        return payNo;
    }

    public String getPassenger_IDNo() {
        return Passenger_IDNo;
    }


    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public void setFlight_flightNo(String flight_flightNo) {
        Flight_flightNo = flight_flightNo;
    }


    public void setPassenger_IDNo(String passenger_IDNo) {
        Passenger_IDNo = passenger_IDNo;
    }

    public void setSeat(String seat) {
        this.seat = seat;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setMealNo(String mealNo) {
        this.mealNo = mealNo;
    }

    public void setPayNo(String payNo) {
        this.payNo = payNo;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderNo='" + orderNo + '\'' +
                ", Flight_flightNo='" + Flight_flightNo + '\'' +
                ", Passenger_IDNo='" + Passenger_IDNo + '\'' +
                ", seat='" + seat + '\'' +
                ", mealNo='" + mealNo + '\'' +
                ", payNo='" + payNo + '\'' +
                ", status=" + status +
                '}';
    }
}
