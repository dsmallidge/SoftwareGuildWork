/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flooring.dao;

import flooring.dto.Order;
import flooring.operations.OrderOperations;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import flooring.operations.OrderOperations;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 *
 * @author apprentice
 */
public class ProdDao implements DAOInterface {

    int orderNumber;
    String customerName, date, state, productType;
    double taxRate, area, costPerSquareFoot, laborCostPerSquareFoot, materialCost, laborCost, tax, total;
    OrderOperations ord = new OrderOperations();

    public void writeToDisk(HashMap<String, HashMap<Integer, Order>> orderMap) throws IOException {
        
        for (String date : orderMap.keySet()) {
            String filename = "orders_" + date + ".txt";
            PrintWriter out = new PrintWriter(new FileWriter(filename));
            HashMap<Integer, Order> orderList = new HashMap<>();
            orderList = orderMap.get(date);
            for (Integer orderNumber : orderList.keySet()) {
                Order s = orderList.get(orderNumber);
                String name = orderNumber
                        + "," + s.getCustomerName()
                        + "," + s.getState()
                        + "," + s.getTaxRate()
                        + "," + s.getProductType()
                        + "," + s.getArea()
                        + "," + s.getCostPerSquareFoot()
                        + "," + s.getLaborCostPerSquareFoot();
                

                out.println(name);
            }

            out.flush();
            out.close();
        }
    }

    public String readFromDisk(String filename) throws FileNotFoundException {
        Order currentOrder = null;
        String stateTaxInfo = "";
        String productInfo = "";
        Scanner in = new Scanner(new BufferedReader(new FileReader(filename)));
        if (filename.equals("taxes.txt")) {
            String inString;
            String[] inArray;
            while (in.hasNextLine()) {
                inString = in.nextLine();
                inArray = inString.split(",");
                stateTaxInfo += inArray[0] + inArray[1];
            }
            return stateTaxInfo;
        }

        if (filename.equals("productType.txt")) {
            String inString;
            String[] inArray;
            while (in.hasNextLine()) {
                inString = in.nextLine();
                inArray = inString.split(",");
                productInfo += inArray[0] + inArray[1] + inArray[2];
            }
            return productInfo;
        }
        return "file not found!";
    }

    public HashMap readFromDisk2(String filename) throws FileNotFoundException {

        Scanner in = new Scanner(new BufferedReader(new FileReader(filename)));
        String inString;
        String[] inArray;
        HashMap<Integer, Order> temp = new HashMap<>();
        

        while (in.hasNextLine()) {
            Order o = new Order(orderNumber, customerName, state, taxRate, productType, area,
                    costPerSquareFoot, laborCostPerSquareFoot);
            o.setDate(filename.substring(7, 15));
            String date = o.getDate();
            inString = in.nextLine();
            inArray = inString.split(",");
            orderNumber = (Integer.parseInt(inArray[0]));
            o.setOrderNumber(orderNumber);
            o.setCustomerName(inArray[1]);
            o.setState(inArray[2]);
            o.setTaxRate(Double.parseDouble(inArray[3]));
            o.setProductType(inArray[4]);
            o.setArea(Double.parseDouble(inArray[5]));
            o.setCostPerSquareFoot(Double.parseDouble(inArray[6]));
            o.setLaborCostPerSquareFoot(Double.parseDouble(inArray[7]));

            temp.put(orderNumber, o);

        }
        return temp;
    }

}
