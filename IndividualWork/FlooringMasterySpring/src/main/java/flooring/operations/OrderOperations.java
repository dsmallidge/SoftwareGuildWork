/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flooring.operations;

import flooring.dao.DAOInterface;
import flooring.dao.ProdDao;
import flooring.dao.TestDao;
import flooring.dto.Order;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Resource;

/**
 *
 * @author apprentice
 */
public class OrderOperations {

    @Resource
    private HashMap<String, HashMap<Integer, Order>> orderMap;
    
    public OrderOperations() {
        orderMap = new HashMap<>();
    }
    
    
    public String displayOrders(String date) {
        HashMap<Integer, Order> orderList = new HashMap<>();
        orderList = getOrderMap().get(date);
        String temp = "";

        if (orderList == null) {
            return "\nCould not find date\n";
        }

        for (Integer orderNumber : orderList.keySet()) {
            Order tempOrder = orderList.get(orderNumber);
            temp += "\nDate: " + date
                    + "\nOrder Number: " + orderNumber
                    + "\nName: " + tempOrder.getCustomerName()
                    + "\nState: " + tempOrder.getState()
                    + "\ntaxRate: " + tempOrder.getTaxRate()
                    + "\nProduct Type: " + tempOrder.getProductType()
                    + "\nArea: " + tempOrder.getArea()
                    + "\nCost Per Square Foot: " + tempOrder.getCostPerSquareFoot()
                    + "\nLabor Cost Per Square Foot: " + tempOrder.getLaborCostPerSquareFoot()
                    + "\nMaterial Cost: " + tempOrder.getMaterialCost()
                    + "\nLabor Cost: " + tempOrder.getLaborCost()
                    + "\ntax: " + tempOrder.getTax()
                    + "\nTotal : " + tempOrder.getTotal() + "\n";
        }
        return temp;

    }

    public String addOrder(String customerName, String date, String state, String productType, double area, String taxes, String productInfo) {
        HashMap<Integer, Order> orderList = new HashMap<>();
        double taxRate = 0;
        double costPerSquareFoot = 0;
        double laborCostPerSquareFoot = 0;
        double materialCost;
        double laborCost;
        double tax;
        int orderNumber;
        for (int i = 0; i < taxes.length() - 2; i++) {
            if (taxes.substring(i, i + 2).equals(state)) {
                String tempTaxRate = taxes.substring(i + 2, i + 6);
                taxRate = Double.parseDouble(tempTaxRate);
                break;
            }
        }
        if (taxRate == 0.00) {
            return "invalid state name";
        }

        for (int i = 0; i < productInfo.length() - productType.length(); i++) {
            if (productInfo.substring(i, i + productType.length()).equals(productType)) {
                String tempcostpsqft = productInfo.substring(i + productType.length(), i + productType.length() + 4);
                costPerSquareFoot = Double.parseDouble(tempcostpsqft);
                String templaborCostpsqft = productInfo.substring(i + productType.length() + 4, i + productType.length() + 4 + 4);
                laborCostPerSquareFoot = Double.parseDouble(templaborCostpsqft);
                break;
            }
        }
        if (costPerSquareFoot == 0.00) {
            return "invalid Product Type";
        }
        if (Integer.parseInt(date) < 1011900 || Integer.parseInt(date) > 12122100) {
            return "invalid date";
        }
        materialCost = area * costPerSquareFoot;
        laborCost = area * laborCostPerSquareFoot;
        tax = (materialCost + laborCost) * taxRate / 100;
        double total = materialCost + laborCost + tax;
        if (getOrderMap().get(date) == null) {
            orderNumber = 1;
        } else {
            orderNumber = getNewOrderNumber(getOrderMap().get(date));
            orderList = getOrderMap().get(date);
        }
        Order tempOrder = new Order(orderNumber, customerName, state, taxRate, productType, area,
                costPerSquareFoot, laborCostPerSquareFoot);
        orderList.put(orderNumber, tempOrder);
        getOrderMap().put(date, orderList);
        return "order added";
    }

    public HashMap editAnOrder(int orderNumber, String customerName, String date, String state, String productType, String taxes, double area, String productInfo) {
        HashMap<Integer, Order> orderList = new HashMap<>();
        orderList = getOrderMap().get(date);
        double taxRate = 0;
        double costPerSquareFoot = 0;
        double laborCostPerSquareFoot = 0;
        double materialCost;
        double laborCost;
        double tax;
        double total;
        for (int i = 0; i < taxes.length() - 2; i++) {
            if (taxes.substring(i, i + 2).equals(state)) {
                String tempTaxRate = taxes.substring(i + 2, i + 6);
                taxRate = Double.parseDouble(tempTaxRate);
                break;
            }
        }
        for (int i = 0; i < productInfo.length() - productType.length(); i++) {
            if (productInfo.substring(i, i + productType.length()).equals(productType)) {
                String tempcostpsqft = productInfo.substring(i + productType.length(), i + productType.length() + 4);
                costPerSquareFoot = Double.parseDouble(tempcostpsqft);
                String templaborCostpsqft = productInfo.substring(i + productType.length() + 4, i + productType.length() + 4 + 4);
                laborCostPerSquareFoot = Double.parseDouble(templaborCostpsqft);
                break;
            }
        }

        materialCost = area * costPerSquareFoot;
        laborCost = area * laborCostPerSquareFoot;
        tax = (materialCost + laborCost) * taxRate / 100;
        total = materialCost + laborCost + tax;

        Order tempOrder = new Order(orderNumber, customerName, state, taxRate, productType, area,
                costPerSquareFoot, laborCostPerSquareFoot);
        orderList.put(orderNumber, tempOrder);
        getOrderMap().put(date, orderList);

        return getOrderMap();
    }

    public String removeAnOrder(String date, int orderNumber, String choice) {
        if (choice.equals("y")) {
            getOrderMap().get(date).remove(orderNumber);
            String yes = "It has successfully been deleted!\n";
            return yes;
        }
        if (choice.equals("n")) {
            String no = "you have not deleted the order.\n";
            return no;
        } else {
            return "error\n";
        }
    }

    public String saveCurrentWork(String date, DAOInterface file) {

        try {
            file.writeToDisk(getOrderMap());
            return "saved\n";
        } catch (IOException e) {
            return "Couldn't write to file, sorry.\n";
        }
    }

    public String quit(String date, DAOInterface file) {

        try {
            file.writeToDisk(getOrderMap());
            return "saved exiting program!\n";
        } catch (IOException e) {
            return "Couldn't write to file, sorry. Exiting program.\n";
        }
    }

    public String DisplayDelete(String date, int orderNumber) {
        HashMap<Integer, Order> orderList = new HashMap<>();
        orderList = getOrderMap().get(date);
        String temp = "";
        if (orderList.containsKey(orderNumber)) {
            Order tempOrder = orderList.get(orderNumber);
            temp += "\nDate: " + date
                    + "\nOrder Number: " + tempOrder.getOrderNumber()
                    + "\nName: " + tempOrder.getCustomerName()
                    + "\nState: " + tempOrder.getState()
                    + "\ntaxRate: " + tempOrder.getTaxRate()
                    + "\nProduct Type: " + tempOrder.getProductType()
                    + "\nArea: " + tempOrder.getArea()
                    + "\nCost Per Square Foot: " + tempOrder.getCostPerSquareFoot()
                    + "\nLabor Cost Per Square Foot: " + tempOrder.getLaborCostPerSquareFoot()
                    + "\nMaterial Cost: " + tempOrder.getMaterialCost()
                    + "\nLabor Cost: " + tempOrder.getLaborCost()
                    + "\ntax: " + tempOrder.getTax()
                    + "\nTotal : " + tempOrder.getTotal() + "\n";

            return temp;
        } else {
            return "Could not find order number";
        }
    }

    public String returnCheck(String newInput, String originalInput) {
        if (newInput.isEmpty()) {
            return originalInput;
        } else {
            return newInput;
        }

    }

    public Double returnCheck2(String newInput, String originalInput) {
        if (newInput.isEmpty()) {
            return Double.parseDouble(originalInput);
        } else {
            return Double.parseDouble(newInput);
        }

    }

    private int getNewOrderNumber(HashMap<Integer, Order> orderList) {
        int max = 0;
        for (Integer orderNumber : orderList.keySet()) {
            if (orderNumber > max) {
                max = orderNumber;
            }
        }
        return (max + 1);
    }

    /**
     * @return the orderMap
     */
    public HashMap<String, HashMap<Integer, Order>> getOrderMap() {
        if (orderMap == null){
            setOrderMap(new HashMap<>());
        }
        return orderMap;
    }

    /**
     * @param orderMap the orderMap to set
     */
    public void setOrderMap(HashMap<String, HashMap<Integer, Order>> orderMap) {
        this.orderMap = orderMap;
    }

}
