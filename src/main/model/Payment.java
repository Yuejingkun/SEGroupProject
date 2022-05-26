package main.model;

public class Payment {
    private String payNo;
    private double cost;
    private String creditNo;

    public double getCost() {
        return cost;
    }

    public String getCreditNo() {
        return creditNo;
    }

    public String getPayNo() {
        return payNo;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public void setCreditNo(String creditNo) {
        this.creditNo = creditNo;
    }

    public void setPayNo(String payNo) {
        this.payNo = payNo;
    }
}
