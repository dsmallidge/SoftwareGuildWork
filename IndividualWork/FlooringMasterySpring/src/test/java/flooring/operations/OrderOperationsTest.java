/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flooring.operations;

import flooring.dao.DAOInterface;
import flooring.dao.ProdDao;
import flooring.dto.Order;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author apprentice
 */
public class OrderOperationsTest {

    public OrderOperationsTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of displayOrders method, of class OrderOperations.
     */
    @Test
    public void testDisplayOrders() {
        System.out.println("displayOrders");

        HashMap<String, HashMap<Integer, Order>> testMap = new HashMap<>();
        HashMap<Integer, Order> testMap2 = new HashMap<>();
        OrderOperations instance = new OrderOperations();
        Order testOrder = new Order();
        int orderNumber = 1;
        String date = "03251970";
        testOrder.setOrderNumber(1);
        testOrder.setCustomerName("Barack");
        testOrder.setState("MI");
        testOrder.setTaxRate(5.75);
        testOrder.setProductType("Wood");
        testOrder.setArea(700);
        testOrder.setCostPerSquareFoot(5.15);
        testOrder.setLaborCostPerSquareFoot(4.75);
        testMap2.put(orderNumber, testOrder);
        instance.getOrderMap().put(date, testMap2);
        assertEquals(instance.displayOrders(date).contains("Wood"), true);
        assertEquals(instance.displayOrders(date).contains("700"), true);
        assertEquals(instance.displayOrders(date).contains("Laminate"), false);

    }

    /**
     * Test of addOrder method, of class OrderOperations.
     */
    @Test
    public void testAddOrder() {

        System.out.println("addOrder");
        OrderOperations instance = new OrderOperations();
        int orderNumber = 1;
        String date = "03251970";
        String customerName = "Barack";
        String state = "MI";
        String productType = "Wood";
        double area = 700;
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        DAOInterface testInterface = (DAOInterface) ctx.getBean("testMode");
        String productInfo, taxes;
        try {
            productInfo = testInterface.readFromDisk("productType.txt");
        } catch (FileNotFoundException e) {
            productInfo = "";
        }

        try {
            taxes = testInterface.readFromDisk("taxes.txt");
        } catch (FileNotFoundException e) {
            taxes = "";
        }
        instance.addOrder(customerName, date, state, productType, area, taxes, productInfo);
        assertEquals(instance.getOrderMap().containsKey(date), true);
        assertEquals(instance.getOrderMap().get(date).get(orderNumber).getCustomerName(), "Barack");

    }

    /**
     * Test of editAnOrder method, of class OrderOperations.
     */
    @Test
    public void testEditAnOrder() {
        System.out.println("EditAnOrder");
        OrderOperations instance = new OrderOperations();
        int orderNumber = 1;
        String date = "03251970";
        String customerName = "Barack";
        String state = "MI";
        String productType = "Wood";
        double area = 700;
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        DAOInterface testInterface = (DAOInterface) ctx.getBean("testMode");
        String productInfo, taxes;
        try {
            productInfo = testInterface.readFromDisk("productType.txt");
        } catch (FileNotFoundException e) {
            productInfo = "";
        }

        try {
            taxes = testInterface.readFromDisk("taxes.txt");
        } catch (FileNotFoundException e) {
            taxes = "";
        }
        instance.addOrder(customerName, date, state, productType, area, taxes, productInfo);
        date = "03251970";
        customerName = "Chuck";
        state = "PA";
        area = 200;
        instance.editAnOrder(orderNumber, customerName, date, state, productType, taxes, area, productInfo);
        assertEquals(instance.getOrderMap().get(date).get(orderNumber).getCustomerName().equals("Barack"), false);
        assertEquals(instance.getOrderMap().get(date).get(orderNumber).getState().equals("PA"), true);
        
    }

    /**
     * Test of removeAnOrder method, of class OrderOperations.
     */
    @Test
    public void testRemoveAnOrder() {
        OrderOperations instance = new OrderOperations();
        int orderNumber = 1;
        String date = "03251970";
        String customerName = "Barack";
        String state = "MI";
        String productType = "Wood";
        double area = 700;
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        DAOInterface testInterface = (DAOInterface) ctx.getBean("productionMode");
        String productInfo, taxes;
        try {
            productInfo = testInterface.readFromDisk("productType.txt");
        } catch (FileNotFoundException e) {
            productInfo = "";
        }

        try {
            taxes = testInterface.readFromDisk("taxes.txt");
        } catch (FileNotFoundException e) {
            taxes = "";
        }
        instance.addOrder(customerName, date, state, productType, area, taxes, productInfo);
        assertEquals(instance.getOrderMap().get(date).size(), 1);
        instance.removeAnOrder(date, orderNumber, "y");
        assertEquals(instance.getOrderMap().get(date).size(), 0);
        
    }
    /**
     * Test of removeAnOrder method, of class OrderOperations.
     */
    @Test
    public void testSaveCurrentWork() throws FileNotFoundException {
    
    OrderOperations instance = new OrderOperations();
        int orderNumber = 1;
        String date = "03251975";
        String customerName = "Barack";
        String state = "MI";
        String productType = "Wood";
        double area = 700;
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        DAOInterface testInterface = (DAOInterface) ctx.getBean("productionMode");
        String productInfo, taxes;
        try {
            productInfo = testInterface.readFromDisk("productType.txt");
        } catch (FileNotFoundException e) {
            productInfo = "";
        }

        try {
            taxes = testInterface.readFromDisk("taxes.txt");
        } catch (FileNotFoundException e) {
            taxes = "";
        }
        instance.addOrder(customerName, date, state, productType, area, taxes, productInfo);
        instance.saveCurrentWork(date, testInterface);
        Scanner in = new Scanner(new BufferedReader(new FileReader("orders_03251975.txt")));
        String inString;
        String[] inArray;
        inString = in.nextLine();
        inArray = inString.split(",");
        assertEquals("Barack", inArray[1]);
        assertEquals("Wood", inArray[4]);
       
    }
}