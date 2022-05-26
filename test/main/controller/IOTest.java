package test.main.controller;

import com.alibaba.fastjson.JSON;
import main.controller.IO;
import main.model.Order;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

/**
 *  The class tests the tool methods of data IO.
 *  @Author ZheLiu
 *  @Version 1.0
 */
class IOTest {

    @Test
    void write() {
        ArrayList<Order> orders;
        try {
            orders= (ArrayList<Order>) JSON.parseArray(IO.read("Order.json"),Order.class);
            orders.get(0).setStatus(2);
            IO.write("Order.json", JSON.toJSONString(orders,true));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}