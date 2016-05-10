/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flooring.dao;

import flooring.dto.Order;
import java.util.ArrayList;
import java.util.HashMap;
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
public class TestDaoTest {
    
    public TestDaoTest() {
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
     * Test of writeToDisk method, of class TestDao.
     */
//    @Test
//    public void testWriteToDisk() throws Exception {
//        System.out.println("writeToDisk");
//        ArrayList<Order> orderList = null;
//        String filename = "";
//        TestDao instance = new TestDao();
//        instance.writeToDisk(orderList, filename);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }

    /**
     * Test of readFromDisk method, of class TestDao.
     */
//    @Test
//    public void testReadFromDisk() throws Exception {
//        System.out.println("readFromDisk");
//        String filename = "";
//        TestDao instance = new TestDao();
//        String expResult = "";
//        String result = instance.readFromDisk(filename);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }

    /**
     * Test of readFromDisk2 method, of class TestDao.
     */
    @Test
    public void testReadFromDisk2() throws Exception {
        System.out.println("readFromDisk2");
        HashMap<String, HashMap<Integer, Order>> testMap = new HashMap<>();
        String date = "01011970";
        HashMap<Integer, Order> testMap2 = new HashMap<>();
        Order testOrder = new Order();
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        DAOInterface instance = (DAOInterface) ctx.getBean("testMode");
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
