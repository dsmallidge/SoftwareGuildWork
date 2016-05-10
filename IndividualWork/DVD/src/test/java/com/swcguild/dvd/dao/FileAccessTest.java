/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swcguild.dvd.dao;

import static com.swcguild.dvd.dao.FileAccess.readFromFile;
import static com.swcguild.dvd.dao.FileAccess.writeToFile;
import com.swcguild.dvd.model.Dvd;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
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
public class FileAccessTest {

    ApplicationContext ctx
            = new ClassPathXmlApplicationContext("test-applicationContext.xml");
    private DvdListDaoInMemImpl instance;

    public FileAccessTest() {
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
     * Test of writeToFile method, of class FileAccess.
     */
    @Test
    public void testWriteToFile() throws Exception {
        instance = new DvdListDaoInMemImpl();
        Map<Integer, Dvd> dvdLibrary = new HashMap<>();

        Dvd testDvd = new Dvd();

        testDvd.setTitle("a movie");
        testDvd.setReleaseDate("11-11-2011");
        testDvd.setMpaaRating("NC-17");
        testDvd.setDirector("a director");
        testDvd.setStudio("a studio");
        testDvd.setNote("no note");
        instance.addDvd(testDvd);

        try {
            writeToFile(instance.getDvdMap());
        } catch (IOException ex) {

        }

        try {
            Scanner in = new Scanner(new BufferedReader(new FileReader("DVDLibrary.txt")));

            String inString;
            String[] inArray;
            int year, month, day;

            inString = in.nextLine();
            inArray = inString.split("::");

            assertEquals("a movie", (inArray[1]));

            assertEquals("a studio", (inArray[5]));

        } catch (FileNotFoundException ex) {
        }

    }

    /**
     * Test of readFromFile method, of class FileAccess.
     */
    @Test
    public void testReadFromFile() throws Exception {
        System.out.println("readFromFile");
        Map<Integer, Dvd> dvdLibrary = new HashMap<>();
        PrintWriter out = new PrintWriter(new FileWriter("DVDLibrary.txt"));
        out.print("6::ff::2016-04-01::bb::qq::cc::bb\n"
                + "7::cc::2016-04-01::cc::cc::cc::cc\n"
                + "9::bb::2016-04-01::bb::bb::bb::bb");
        out.flush();
        out.close();
        
        try {
            dvdLibrary = readFromFile();
        } catch (FileNotFoundException ex) {
        }
        
        assertEquals("ff", dvdLibrary.get(6).getTitle());
        assertEquals("cc", dvdLibrary.get(7).getStudio());

    }

}
