package main.controller;

//import com.sun.org.apache.xpath.internal.operations.Or;
import main.model.Meal;
import main.model.Order;
import main.model.Payment;

import java.io.IOException;
import java.util.ArrayList;

/**
 *  The class stores the tool methods of modification.
 *  @Author Jingkun Yue
 *  @Version 1.0
 */
public class ChoiceController implements ChoiceUtl{

    @Override
    public void seatChoose(String orderNo, String seat, int type) throws IOException {
        DataManage instance = DataManage.getInstance();
        ArrayList<Order> orderArrayList=instance.getOrders();
        System.out.println(orderNo);
        if (type==0){
            ArrayList<Payment> paymentArrayList = instance.getPayments();
            for (Order temp:orderArrayList){
                if (temp.getOrderNo().equals(orderNo)){
                    temp.setSeat(seat);
                }
                for (Payment temp1:paymentArrayList){
                    if (temp1.getPayNo().equals(temp.getPayNo())){
                        temp1.setCost(100.0);
                    }
                }
            }
        }
        else {
            for(Order temp:orderArrayList){
                if(temp.getOrderNo().equals(orderNo)){
                    temp.setSeat(seat);
                }
            }
        }

    }


    public void basicMealChoose(String orderNo, String basicMeal) throws IOException{
        DataManage instance = DataManage.getInstance();
        ArrayList<Order> orderArrayList=instance.getOrders();
        ArrayList<Meal> mealArrayList=instance.getMeals();
        for (Order temp:orderArrayList){
            if (temp.getOrderNo().equals(orderNo)){
                for (Meal temp1:mealArrayList){
                    if (temp1.getMealNo().equals(temp.getMealNo())){
                        temp1.setBasicMeal(basicMeal);
                    }
                }
            }
        }
    }

    public void extraMealChoose(String orderNo, ArrayList<String> extraMeal ) throws IOException{
        DataManage instance = DataManage.getInstance();
        ArrayList<Order> orderArrayList=instance.getOrders();
        ArrayList<Meal> mealArrayList=instance.getMeals();
        ArrayList<Payment> paymentArrayList = instance.getPayments();
        double cost=0.0;
        System.out.println("use extraMealChoose method");
        for (Order temp:orderArrayList){
            if (temp.getOrderNo().equals(orderNo)){
                for (Meal temp1:mealArrayList){
                    if (temp1.getMealNo().equals(temp.getMealNo())){
                        temp1.setExtraMeal(extraMeal);
                        //遍历extrameal，算cost，并且更新到payment里
                        for(int i=0;i<extraMeal.size();i++){
                            if (extraMeal.get(i)=="Hamburger"){
                                cost = cost+10.0;
                            }
                            else if (extraMeal.get(i)=="Beef"){
                                cost = cost+10.0;
                            }
                            else if (extraMeal.get(i)=="Coffee"){
                                cost = cost+5.0;
                            }
                            else if (extraMeal.get(i)=="Juice"){
                                cost = cost+2.5;
                            }
                        }
                        temp1.setExtraCost(cost);
                        System.out.println(extraMeal);
                        System.out.println(cost);
                        for (Payment temp2:paymentArrayList){
                            if (temp2.getPayNo().equals(temp.getPayNo())){
                                double cost1 = temp2.getCost();
                                cost1 = cost1+cost;
                                System.out.println(cost1);
                                temp2.setCost(cost1);
                            }
                        }
                    }
                }
            }
        }
    }

    public double pay(String orderNo, String creditNo)throws IOException{
        DataManage instance = DataManage.getInstance();
        ArrayList<Order> orderArrayList=instance.getOrders();
        ArrayList<Payment> paymentArrayList=instance.getPayments();
        double temp= 0.0;
        for (Order order1:orderArrayList){
            if(order1.getOrderNo().equals(orderNo)){
                for (Payment pay1:paymentArrayList){
                    if (pay1.getPayNo().equals(order1.getPayNo())){
                        temp= pay1.getCost();
                        System.out.println("You have successfully paid £ " + pay1.getCost());
                        pay1.setCost(0.0);
                        pay1.setCreditNo(creditNo);
                    }
                }
            }
        }
        return temp;
    }

    public void defaultPayment(String orderNo) throws IOException{
        DataManage instance = DataManage.getInstance();
        ArrayList<Order> orderArrayList = instance.getOrders();
        ArrayList<Payment> paymentArrayList = instance.getPayments();
        for (Order temp:orderArrayList){
            if (temp.getOrderNo().equals(orderNo)){
                for (Payment temp1:paymentArrayList){
                    if (temp1.getPayNo().equals(temp.getPayNo())){
                        temp1.setCreditNo(null);
                        temp1.setCost(0.0);
                    }
                }
            }
        }
    }
    public void defaultSeat(String orderNo) throws IOException{
        DataManage instance = DataManage.getInstance();
        ArrayList<Order> orderArrayList = instance.getOrders();
        for (Order temp:orderArrayList){
            if (temp.getOrderNo().equals(orderNo)){
                temp.setSeat(null);
            }
        }
    }
    public void defaultMeal(String orderNo) throws IOException{
        DataManage instance = DataManage.getInstance();
        ArrayList<Order> orderArrayList = instance.getOrders();
        ArrayList<Meal> mealArrayList = instance.getMeals();
        for (Order temp:orderArrayList){
            if (temp.getOrderNo().equals(orderNo)){
                for (Meal temp1:mealArrayList){
                    if (temp1.getMealNo().equals(temp.getMealNo())){
                        temp1.setExtraCost(0.0);
                        temp1.setExtraMeal(null);//不知道ArrayList的json格式是什么.
                        temp1.setBasicMeal(null);
                    }
                }
            }
        }
    }


}
