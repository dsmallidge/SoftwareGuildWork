/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swcguild.vendingmvc.dao;

import com.swcguild.vendingmvc.dto.Item;
import java.util.Collection;
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
public class InventoryTest
{

    ApplicationContext ctx
            = new ClassPathXmlApplicationContext("test-applicationContext.xml");
    Inventory i;
    HashMap<Integer, Item> items = new HashMap<>();

    public InventoryTest()
    {
    }

    @BeforeClass
    public static void setUpClass()
    {
    }

    @AfterClass
    public static void tearDownClass()
    {
    }

    @Before
    public void setUp()
    {
        i = (Inventory)ctx.getBean("inventoryBean");
        
        items.put(0, new Item("Apple Juice", 75, 20));
        items.put(1, new Item("Root Beer", 99, 20));
        items.put(2, new Item("Bubble Gum", 25, 40));

        i.fillMachine(items);
    }

    @After
    public void tearDown()
    {
        
    }
    /**
     * Test of getItem method, of class Inventory.
     */
    @Test
    public void testGetItem_String()
    {
        Item apple = new Item("Apple Juice", 75, 20);
        
        
        assertEquals(apple.getName(), i.getItem("Apple Juice").getName());
        assertEquals(apple.getCost(), i.getItem("Apple Juice").getCost());
        assertEquals(apple.getQuantity(), i.getItem("Apple Juice").getQuantity());
        //assertEquals(null, i.getItem("Watermelon"));
    }

    /**
     * Test of getItem method, of class Inventory.
     */
    @Test
    public void testGetItem_Integer()
    {
        Item apple = new Item("Apple Juice", 75, 20);

        assertEquals(apple.getName(), i.getItem(0).getName());
        assertEquals(apple.getCost(), i.getItem(0).getCost());
        assertEquals(apple.getQuantity(), i.getItem(0).getQuantity());
        assertEquals(null, i.getItem(4));
    }

    /**
     * Test of getKey method, of class Inventory.
     */
    @Test
    public void testGetKey()
    {
        String apple = "Apple Juice";

        assertEquals(apple, (i.getItem(i.getKey(apple)).getName()));
    }

    /**
     * Test of vend method, of class Inventory.
     */
    @Test
    public void testVend()
    {
        Item creamSoda = new Item("Cream Soda", 99, 5);
        items.put(3,creamSoda);
        i.vend("Cream Soda", 120);
        assertEquals(4, items.get(3).getQuantity());

    }

    /**
     * Test of fillMachine method, of class Inventory.
     */
    @Test
    public void testFillMachine()
    {
        assertEquals(items.size(), i.getSize());
    }


    /**
     * Test of checkStock method, of class Inventory.
     */
    @Test
    public void testCheckStock()
    {
        Item rootBeer = new Item("Root Beer", 99, 19);
        Item creamSoda = new Item("Cream Soda", 99, 0);
        assertEquals(true,i.checkStock(rootBeer));
        assertEquals(false,i.checkStock(creamSoda));
        
    }

}
