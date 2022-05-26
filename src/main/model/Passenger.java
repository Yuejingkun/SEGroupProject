package main.model;

public class Passenger {
    private String surName;
    private String fName;
    private String IDNo;

    public String getfName() {
        return fName;
    }

    public String getIDNo() {
        return IDNo;
    }

    public String getSurName() {
        return surName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public void setIDNo(String IDNo) {
        this.IDNo = IDNo;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    @Override
    public String toString() {
        return "Passenger{" +
                "surName='" + surName + '\'' +
                ", fName='" + fName + '\'' +
                ", IDNo='" + IDNo + '\'' +
                '}';
    }


}
