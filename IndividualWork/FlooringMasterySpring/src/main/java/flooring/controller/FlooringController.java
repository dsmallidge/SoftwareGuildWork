/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flooring.controller;

import flooring.dao.DAOInterface;

import flooring.dto.Order;
import flooring.operations.OrderOperations;
import flooring.ui.ConsoleIO;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author apprentice
 */
public class FlooringController {

    ConsoleIO cio;
    DAOInterface dao;
    OrderOperations tempOrderOperations;

    String date;

    public FlooringController(DAOInterface dao, ConsoleIO cio, OrderOperations tempOrderOperations) {
        this.dao = dao;
        this.cio = cio;
        this.tempOrderOperations = tempOrderOperations;
    }

    public void run() {

        String productInfo, taxes;
//        OrderOperations tempOrderOperations = new OrderOperations();

        try {
            productInfo = dao.readFromDisk("productType.txt");
        } catch (FileNotFoundException e) {
            productInfo = "";
        }

        try {
            taxes = dao.readFromDisk("taxes.txt");
        } catch (FileNotFoundException e) {
            taxes = "";
        }

        try {
            File folder = new File(".");
            File[] listOfFiles = folder.listFiles();
            for (int i = 0; i < listOfFiles.length; i++) {
                if (listOfFiles[i].isFile()) {
                    String fileName = listOfFiles[i].getName();
                    if (fileName.substring(0, 6).equalsIgnoreCase("orders")) {
                        date = fileName.substring(7, 15);
                        HashMap<Integer, Order> fileInputMap = dao.readFromDisk2(fileName);
                        tempOrderOperations.getOrderMap().put(date, fileInputMap);

                    }
                }
            }

        } catch (FileNotFoundException e) {
            
        }

        int orderNumber;
        String customerName, state, productType, temp;
        double taxRate, area, costPerSquareFoot, laborCostPerSquareFoot, materialCost, laborCost, tax, total;

        int menuChoice;
        do {

            cio.write("\n1) Display Orders\n"
                    + "2) Add an Order\n"
                    + "3) Edit an Order\n"
                    + "4) Remove an Order\n"
                    + "5) Save Current Work\n"
                    + "6) Quit\n");
            menuChoice = cio.readInteger("Menu Choice: ");

            switch (menuChoice) {
                case 1:
                    date = cio.readString("\nPlease input a date (MMDDYYYY): ");
                    temp = tempOrderOperations.displayOrders(date);
                    cio.write(temp);
                    break;
                case 2:
                    customerName = cio.readString("Name: ");
                    date = cio.readString("Date MMDDYYYY: ");
                    state = cio.readString("State: ");
                    productType = cio.readString("Product Type: ");
                    area = cio.readDouble("Area: ");

                    String message = tempOrderOperations.addOrder(customerName, date, state, productType, area, taxes, productInfo);
                    cio.write(message + "\n");
                    break;
                case 3:
                    date = cio.readString("\nDate: ");
                    orderNumber = cio.readInteger("\nOrder Number:");
                    Order tempOrder = tempOrderOperations.getOrderMap().get(date).get(orderNumber);
                    String orderName = tempOrder.getCustomerName();
                    customerName = cio.readString("Name(" + orderName + "): ");
                    customerName = tempOrderOperations.returnCheck(customerName, orderName);
                    String orderState = tempOrder.getState();
                    state = cio.readString("State(" + orderState + "): ");
                    state = tempOrderOperations.returnCheck(state, orderState);
                    String orderProductType = tempOrder.getProductType();
                    productType = cio.readString("Product Type(" + orderProductType + "): ");
                    productType = tempOrderOperations.returnCheck(productType, orderProductType);
                    Double orderArea = tempOrder.getArea();
                    String stringOrderArea = orderArea.toString();
                    String stringArea = cio.readString("Area(" + tempOrder.getArea() + "): ");
                    area = tempOrderOperations.returnCheck2(stringArea, stringOrderArea);
                    tempOrderOperations.editAnOrder(orderNumber, customerName, date, state, productType, taxes, area, productInfo);
                    break;
                case 4:
                    date = cio.readString("\nDate (MMDDYYYY): ");
                    orderNumber = cio.readInteger("\nOrder Number: ");
                    String display = tempOrderOperations.DisplayDelete(date, orderNumber);
                    cio.write(display);
                    String choice = cio.readString("Are you sure you want to delete this order? (y/n): ");
                    String result = tempOrderOperations.removeAnOrder(date, orderNumber, choice);
                    cio.write(result);
                    break;
                case 5:
                    String saved = tempOrderOperations.saveCurrentWork(date, dao);
                    cio.write(saved);
                    break;
                case 6:
                    String exiting = tempOrderOperations.quit(date, dao);
                    cio.write(exiting);
                    break;
            }

        } while (menuChoice
                != 6);

    }

}
