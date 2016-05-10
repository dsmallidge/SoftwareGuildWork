/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swcguild.vendingmvc.dao;

import com.swcguild.vendingmvc.dto.Item;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
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
public class FileAccessTest
{
    ApplicationContext ctx
            = new ClassPathXmlApplicationContext("test-applicationContext.xml");
    FileAccess v;
    public FileAccessTest()
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
        clear();
        v = (FileAccess)ctx.getBean("fileAccessBean");
    }
    
    @After
    public void tearDown()
    {
    }
    
     public void clear()
    {
        try
        {
            PrintWriter out = new PrintWriter(new FileWriter("vendingTest.txt"));
            out.flush();
            out.close();
        } catch (IOException e) {}
    }

    /**
     * Test of read method, of class FileAccess.
     */
    @Test
    public void testRead()
    {
         try
        {
            PrintWriter testFile = new PrintWriter(new FileWriter("vendingTest.txt"));
            testFile.println("Juice::75::15::.");
            testFile.println("Water::95::12::.");
            
            testFile.flush();
            testFile.close();
        }
        catch (IOException e)
        {
            System.out.println("File failed to save.");
        }
        
        HashMap<Integer, Item> t = v.read();
        
        assertEquals("Juice",t.get(0).getName());
        assertEquals(15,t.get(0).getQuantity());
        assertEquals(95,t.get(1).getCost());
    }

    /**
     * Test of write method, of class FileAccess.
     */
    @Test
    public void testWrite()
    {
         HashMap<Integer, Item> t = new HashMap<>();
        HashMap<Integer, Item> pass = new HashMap<>();

        
        t.put(0, new Item("Juice",75,15));
        t.put(1, new Item("Water",95,12));
        
        v.write(t);
        
        try
        {
            Scanner in = new Scanner(new BufferedReader(new FileReader("vendingTest.txt")));
            
            
            String inString;
            String[] i;
            Integer count = 0;
            while(in.hasNextLine())
            {
                inString = in.nextLine();
                i = inString.split("::");
                pass.put(count, new Item(i[0],Integer.parseInt(i[1]),Integer.parseInt(i[2])));
                count++;
            }
            
        }
        catch (FileNotFoundException e)
        {
           
        }
        
        assertEquals(t.get(0).getCost(), pass.get(0).getCost());
        assertEquals(t.get(0).getQuantity(), pass.get(0).getQuantity());
        assertEquals(t.get(1).getName(), pass.get(1).getName());
    }
    
}
