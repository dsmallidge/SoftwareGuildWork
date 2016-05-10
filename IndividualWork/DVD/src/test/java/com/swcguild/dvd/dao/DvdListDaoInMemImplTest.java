/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swcguild.dvd.dao;

import com.swcguild.dvd.dao.DvdListDBDao.SearchTerm;
import com.swcguild.dvd.model.Dvd;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
public class DvdListDaoInMemImplTest {
    
    ApplicationContext ctx
                = new ClassPathXmlApplicationContext("test-applicationContext.xml");
    private DvdListDaoInMemImpl instance;
    
    public DvdListDaoInMemImplTest() {
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
     * Test of addDvd method, of class DvdListDaoInMemImpl.
     */
    @Test
    public void testAddDvd() {
        System.out.println("addDvd");
        instance = new DvdListDaoInMemImpl();
        Dvd testDvd = new Dvd();
        testDvd.setTitle("a movie");
        testDvd.setReleaseDate("11-11-2011");
        testDvd.setMpaaRating("NC-17");
        testDvd.setDirector("a director");
        testDvd.setStudio("a studio");
        testDvd.setNote("no note");
        instance.addDvd(testDvd);
        
        assertEquals(testDvd, instance.getDvdById(0));
        
    }

    /**
     * Test of removeDvd method, of class DvdListDaoInMemImpl.
     */
    @Test
    public void testRemoveDvd() {
        System.out.println("removeDvd");
        instance = new DvdListDaoInMemImpl();
        Dvd testDvd = new Dvd();
        testDvd.setTitle("a movie");
        testDvd.setReleaseDate("11-11-2011");
        testDvd.setMpaaRating("NC-17");
        testDvd.setDirector("a director");
        testDvd.setStudio("a studio");
        testDvd.setNote("no note");
        instance.addDvd(testDvd);
        assertEquals(testDvd, instance.getDvdById(0));
        instance.removeDvd(0);
        assertEquals(null, instance.getDvdById(0));
        
    }

    /**
     * Test of updateDvd method, of class DvdListDaoInMemImpl.
     */
    @Test
    public void testUpdateDvd() {
        System.out.println("updateDvd");
        instance = new DvdListDaoInMemImpl();
        Dvd testDvd = new Dvd();
        testDvd.setTitle("a movie");
        testDvd.setReleaseDate("11-11-2011");
        testDvd.setMpaaRating("NC-17");
        testDvd.setDirector("a director");
        testDvd.setStudio("a studio");
        testDvd.setNote("no note");
        instance.addDvd(testDvd);
        testDvd.setDirector("director B");
        testDvd.setStudio("studio B");
        instance.updateDvd(testDvd);
        assertEquals("director B", instance.getDvdById(0).getDirector());
        assertFalse(instance.getDvdById(0).getStudio().equalsIgnoreCase("a studio"));
        
    }

    /**
     * Test of getAllDvds method, of class DvdListDaoInMemImpl.
     */
    @Test
    public void testGetAllDvds() {
        System.out.println("getAllDvds");
        instance = new DvdListDaoInMemImpl();
        Dvd testDvd = new Dvd();
        
        testDvd.setTitle("a movie");
        testDvd.setReleaseDate("11-11-2011");
        testDvd.setMpaaRating("NC-17");
        testDvd.setDirector("a director");
        testDvd.setStudio("a studio");
        testDvd.setNote("no note");
        instance.addDvd(testDvd);
        Dvd testDvd2 = new Dvd();
        
        testDvd2.setTitle("a movie2");
        testDvd2.setReleaseDate("11-11-2011");
        testDvd2.setMpaaRating("NC-17");
        testDvd2.setDirector("a director2");
        testDvd2.setStudio("a studio2");
        testDvd2.setNote("no note2");
        instance.addDvd(testDvd2);
        Dvd testDvd3 = new Dvd();
        
        testDvd3.setTitle("a movie3");
        testDvd3.setReleaseDate("11-11-2011");
        testDvd3.setMpaaRating("NC-17");
        testDvd3.setDirector("a director3");
        testDvd3.setStudio("a studio3");
        testDvd3.setNote("no note3");
        instance.addDvd(testDvd3);
        assertEquals(3, instance.getAllDvds().size());
        assertEquals(testDvd2, instance.getAllDvds().get(1));
        
    }

    /**
     * Test of getDvdById method, of class DvdListDaoInMemImpl.
     */
    @Test
    public void testGetDvdById() {
        System.out.println("getDvdById");
        instance = new DvdListDaoInMemImpl();
        Dvd testDvd3 = new Dvd();
        testDvd3.setTitle("a movie3");
        testDvd3.setReleaseDate("11-11-2011");
        testDvd3.setMpaaRating("NC-17");
        testDvd3.setDirector("a director3");
        testDvd3.setStudio("a studio3");
        testDvd3.setNote("no note3");
        instance.addDvd(testDvd3);
        assertEquals(testDvd3, instance.getDvdById(0));
        
    }

    /**
     * Test of searchDvds method, of class DvdListDaoInMemImpl.
     */
    @Test
    public void testSearchDvds() {
        System.out.println("searchDvds");
        instance = new DvdListDaoInMemImpl();
        Dvd testDvd = new Dvd();
        testDvd.setTitle("a movie");
        testDvd.setReleaseDate("11-11-2011");
        testDvd.setMpaaRating("NC-17");
        testDvd.setDirector("a director");
        testDvd.setStudio("a studio");
        testDvd.setNote("no note");
        instance.addDvd(testDvd);
        Dvd testDvd2 = new Dvd();
        testDvd2.setTitle("a movie2");
        testDvd2.setReleaseDate("11-11-2011");
        testDvd2.setMpaaRating("NC-17");
        testDvd2.setDirector("a director2");
        testDvd2.setStudio("a studio2");
        testDvd2.setNote("no note2");
        instance.addDvd(testDvd2);
        Dvd testDvd3 = new Dvd();
        testDvd3.setTitle("a movie3");
        testDvd3.setReleaseDate("11-11-2011");
        testDvd3.setMpaaRating("NC-17");
        testDvd3.setDirector("a director3");
        testDvd3.setStudio("a studio3");
        testDvd3.setNote("no note3");
        instance.addDvd(testDvd3);
        Map<SearchTerm, String> criteria = new HashMap<>();
        criteria.put(SearchTerm.TITLE, "a movie3");
        criteria.put(SearchTerm.RELEASE_DATE, "");
        criteria.put(SearchTerm.MPAA_RATING, "NC-17");
        
        criteria.put(SearchTerm.DIRECTOR, "");
        criteria.put(SearchTerm.STUDIO, "");
        criteria.put(SearchTerm.NOTE, "");
        assertTrue(instance.searchDvds(criteria).contains(testDvd3));
        Map<SearchTerm, String> criteria2 = new HashMap<>();
        criteria2.put(SearchTerm.TITLE, "");
        criteria2.put(SearchTerm.RELEASE_DATE, "11-11-2011");
        criteria2.put(SearchTerm.MPAA_RATING, "");       
        criteria2.put(SearchTerm.DIRECTOR, "");
        criteria2.put(SearchTerm.STUDIO, "");
        criteria2.put(SearchTerm.NOTE, "");
        assertTrue(instance.searchDvds(criteria2).contains(testDvd));
        assertTrue(instance.searchDvds(criteria2).contains(testDvd2));
        assertTrue(instance.searchDvds(criteria2).contains(testDvd3));
    }
 
}
