package TestData;
import java.util.*;
import java.lang.*;

public class TestDataProvider {

    public  static List<Double> subtotalTestData1() {
        List<Double> list = new ArrayList<>();
        list.add(2.0); //item1 quantity
        list.add(10.99); //item1 amount
        list.add(2.0 * 10.99); // subtotal
        return list;
    }

    public  static List<Double> subtotalTestData2() {
        List<Double> list = new ArrayList<>();
        list.add(5.0); //item2 quantity
        list.add(9.99); //item2 amount
        list.add(5.0 * 9.99); // subtotal
        return list;
    }

    public  static List<Double> subtotalTestData3() {
        List<Double> list = new ArrayList<>();
        list.add(3.0); //item3 quantity
        list.add(14.99); //item3 amount
        list.add(3.0 * 14.99); // subtotal
        return list;
    }

}




