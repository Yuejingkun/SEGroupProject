package main.model;

public class Baggage {
    private String size;
    private double quality;
    private String baggageNo;
    private String baggageType;
    private String extraInfo;
    private String Order_orderNo;

   public String getOrder_orderNo() {
       return Order_orderNo;
   }

    public void setOrder_orderNo(String order_orderNo) {
        Order_orderNo = order_orderNo;
    }

    public String getBaggageNo() {
        return baggageNo;
    }

    public String getExtraInfo() {
        return extraInfo;
    }

    public double getQuality() {
        return quality;
    }

    public String getSize() {
        return size;
    }

    public void setBaggageNo(String baggageNo) {
        this.baggageNo = baggageNo;
    }

    public String getBaggageType() {
        return baggageType;
    }

    public void setExtraInfo(String extraInfo) {
        this.extraInfo = extraInfo;
    }

    public void setQuality(double quality) {
        this.quality = quality;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public void setBaggageType(String baggageType) {
        this.baggageType = baggageType;
    }

}
