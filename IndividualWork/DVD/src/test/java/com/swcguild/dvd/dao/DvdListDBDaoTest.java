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
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author apprentice
 */
public class DvdListDBDaoTest {
    
        private ApplicationContext ctx
                = new ClassPathXmlApplicationContext("test-applicationContext.xml");
    private DvdListDao instance = ctx.getBean("dvdListDao", DvdListDao.class);
    
    public DvdListDBDaoTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        
        JdbcTemplate cleaner = (JdbcTemplate) ctx.getBean("jdbcTemplate");
        cleaner.execute("truncate table dvds");
        
    }
    
    @After
    public void tearDown() {
    }

    
    /**
     * Test of addDvd method, of class DvdListDBDao.
     */
    @Test
    public void testAddDvd() {
        System.out.println("addDvd");
        Dvd testDvd = new Dvd();
        testDvd.setTitle("a movie");
        testDvd.setReleaseDate("11-11-2011");
        testDvd.setMpaaRating("NC-17");
        testDvd.setDirector("a director");
        testDvd.setStudio("a studio");
        testDvd.setNote("no note");
        instance.addDvd(testDvd);
        Dvd fromDb = instance.getDvdById(testDvd.getId());
        assertEquals(fromDb, testDvd);
    }

    /**
     * Test of removeDvd method, of class DvdListDBDao.
     */
    @Test
    public void testRemoveDvd() {
        System.out.println("removeDvd");
        Dvd testDvd = new Dvd();
        testDvd.setTitle("a movie");
        testDvd.setReleaseDate("11-11-2011");
        testDvd.setMpaaRating("NC-17");
        testDvd.setDirector("a director");
        testDvd.setStudio("a studio");
        testDvd.setNote("no note");
        instance.addDvd(testDvd);
        assertEquals(testDvd, instance.getDvdById(testDvd.getId()));
        instance.removeDvd(testDvd.getId());
        assertEquals(null, instance.getDvdById(testDvd.getId()));
    }

    /**
     * Test of updateDvd method, of class DvdListDBDao.
     */
    @Test
    public void testUpdateDvd() {
        System.out.println("updateDvd");
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
        assertEquals("director B", instance.getDvdById(testDvd.getId()).getDirector());
        assertFalse(instance.getDvdById(testDvd.getId()).getStudio().equalsIgnoreCase("a studio"));
    }

    /**
     * Test of getAllDvds method, of class DvdListDBDao.
     */
    @Test
    public void testGetAllDvds() {
        System.out.println("getAllDvds");
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
        assertTrue(instance.getAllDvds().contains(testDvd2));
    }

    /**
     * Test of getDvdById method, of class DvdListDBDao.
     */
    @Test
    public void testGetDvdById() {
        System.out.println("getDvdById");
        Dvd testDvd3 = new Dvd();
        testDvd3.setTitle("a movie3");
        testDvd3.setReleaseDate("11-11-2011");
        testDvd3.setMpaaRating("NC-17");
        testDvd3.setDirector("a director3");
        testDvd3.setStudio("a studio3");
        testDvd3.setNote("no note3");
        instance.addDvd(testDvd3);
        assertEquals(testDvd3, instance.getDvdById(testDvd3.getId()));
    }

    /**
     * Test of searchDvds method, of class DvdListDBDao.
     */
    @Test
    public void testSearchDvds() {
        System.out.println("searchDvds");
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
        criteria.put(SearchTerm.MPAA_RATING, "NC-17");    
        assertTrue(instance.searchDvds(criteria).contains(testDvd3));
        Map<SearchTerm, String> criteria2 = new HashMap<>();
        criteria2.put(SearchTerm.RELEASE_DATE, "11-11-2011");
        assertTrue(instance.searchDvds(criteria2).contains(testDvd));
        assertTrue(instance.searchDvds(criteria2).contains(testDvd2));
        assertTrue(instance.searchDvds(criteria2).contains(testDvd3));
    }
    
}
