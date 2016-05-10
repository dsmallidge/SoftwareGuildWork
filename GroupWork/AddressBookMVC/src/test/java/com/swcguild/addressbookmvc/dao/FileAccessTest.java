/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swcguild.addressbookmvc.dao;

import com.swcguild.addressbookmvc.dto.Address;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
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
    private FileAccess instance;

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
        instance = ctx.getBean("fileAccessBean", FileAccess.class);

    }

    @After
    public void tearDown()
    {
    }

    public void clear()
    {
        try
        {
            PrintWriter out = new PrintWriter(new FileWriter("addressBookTest.txt"));
            out.flush();
            out.close();
        } catch (IOException e) {}
    }
    
    /**
     * Test of saveAddresses method, of class FileAccess.
     */
    @Test
    public void testSaveAddresses()
    {
        HashMap<Integer,Address> l = new HashMap<>();
        HashMap<Integer,Address> r = new HashMap<>();
        l.put(0,new Address("First Name", "Last Name", "Street", "City", "State", "55555"));
        
        try
        {
            instance.saveAddresses(l.values());
            Scanner in = new Scanner(new BufferedReader(new FileReader("addressBookTest.txt")));
                       
            String inString;
            String[] i;
            int c = 0;
            
            while(in.hasNextLine())
            {
                inString = in.nextLine();
                i = inString.split("::");
                r.put(c,new Address(Integer.parseInt(i[0]),i[1],i[2],i[3],i[4],i[5],i[6]));
            }
        }
        catch (FileNotFoundException e)
        {
        }
        assertEquals(r.get(0).getZip(),l.get(0).getZip());
        assertEquals(r.get(0).getState(),l.get(0).getState());
        assertEquals(r.get(0).getStreet(),l.get(0).getStreet());

    }

    /**
     * Test of readFromFile method, of class FileAccess.
     */
    @Test
    public void testReadFromFile()
    {
         try
        {
            PrintWriter out = new PrintWriter(new FileWriter("addressBookTest.txt"));

            out.println("0::Jack::Zoom::Leber::Bum::Minnesota::55245");
            out.println("1::Lan::Temo::Morton::Rocky::Minnesota::54747");

            out.flush();
            out.close();
        } catch (IOException e)
        {
            
        }
        HashMap<Integer,Address> exp = instance.readFromFile();
        HashMap<Integer, Address> t = new HashMap<>();
        t.put(0, new Address(0,"Jack", "Zoom", "Leber", "Bum", "Minnesota","55245"));
        t.put(1, new Address(1,"Lan", "Temo", "Morton", "Rocky", "Minnesota", "54747"));
        assertEquals(t.get(0).toString(), exp.get(0).toString());
        assertEquals(t.get(1).toString(), exp.get(1).toString());
    }
}