package main.model;

import java.util.ArrayList;

public class Meal {
    private String mealNo;
    private String basicMeal;
    private ArrayList<String> extraMeal;
    private double extraCost;

    public void setBasicMeal(String basicMeal) {
        this.basicMeal = basicMeal;
    }

    public void setExtraCost(double extraCost) {
        this.extraCost = extraCost;
    }

    public ArrayList<String> getExtraMeal() {
        return extraMeal;
    }

    public void setMealNo(String mealNo) {
        this.mealNo = mealNo;
    }

    public double getExtraCost() {
        return extraCost;
    }

    public String getMealNo() {
        return mealNo;
    }

    public String getBasicMeal() {
        return basicMeal;
    }

    public void setExtraMeal(ArrayList<String> extraMeal) {
        this.extraMeal = extraMeal;
    }
}
