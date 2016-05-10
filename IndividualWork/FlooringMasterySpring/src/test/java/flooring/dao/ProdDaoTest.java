/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flooring.dao;

import flooring.dto.Order;
import java.io.BufferedReader;
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
public class ProdDaoTest {
    
    public ProdDaoTest() {
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
     * Test of writeToDisk method, of class ProdDao.
     */
    @Test
    public void testWriteToDisk() throws Exception {
        System.out.println("writeToDisk");
        HashMap<String, HashMap<Integer, Order>> testMap = new HashMap<>();
        String date = "01011970";
        HashMap<Integer, Order> testMap2 = new HashMap<>();
        Order testOrder = new Order();
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        DAOInterface instance = (DAOInterface) ctx.getBean("productionMode");
        int orderNumber = 1;
        testOrder.setOrderNumber(1);
        testOrder.setCustomerName("Mike");
        testOrder.setState("PA");
        testOrder.setTaxRate(6.75);
        testOrder.setProductType("Laminate");
        testOrder.setArea(400);
        testOrder.setCostPerSquareFoot(1.75);
        testOrder.setLaborCostPerSquareFoot(2.1);
        testMap2.put(orderNumber, testOrder);
        testMap.put(date, testMap2);
        instance.writeToDisk(testMap);
        Scanner in = new Scanner(new BufferedReader(new FileReader("orders_01011970.txt")));
        String inString;
        String[] inArray;
        inString = in.nextLine();
        inArray = inString.split(",");
        assertEquals("Mike", inArray[1]);
        assertEquals("Laminate", inArray[4]);
        
    }

    /**
     * Test of readFromDisk2 method, of class ProdDao.
     */
    @Test
    public void testReadFromDisk2() throws Exception {
        System.out.println("readFromDisk2");
        HashMap<String, HashMap<Integer, Order>> testMap = new HashMap<>();
        String date = "01011970";
        HashMap<Integer, Order> testMap2 = new HashMap<>();
        Order testOrder = new Order();
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        DAOInterface instance = (DAOInterface) ctx.getBean("productionMode");
        int orderNumber = 1;
        testOrder.setOrderNumber(1);
        testOrder.setCustomerName("Mike");
        testOrder.setState("PA");
        testOrder.setTaxRate(6.75);
        testOrder.setProductType("Laminate");
        testOrder.setArea(400);
        testOrder.setCostPerSquareFoot(1.75);
        testOrder.setLaborCostPerSquareFoot(2.1);
        testMap2.put(orderNumber, testOrder);
        testMap.put(date, testMap2);
        instance.writeToDisk(testMap);
        HashMap<Integer, Order> result = instance.readFromDisk2("orders_" + date + ".txt");
        assertEquals("Mike", result.get(1).getCustomerName());
        
    }
    
}
