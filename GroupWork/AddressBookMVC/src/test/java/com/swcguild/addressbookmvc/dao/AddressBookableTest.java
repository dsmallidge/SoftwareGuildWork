/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swcguild.addressbookmvc.dao;

import com.swcguild.addressbookmvc.dto.Address;
import com.swcguild.addressbookmvc.dao.AddressBookable;
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
public class AddressBookableTest {
    ApplicationContext ctx
                = new ClassPathXmlApplicationContext("test-applicationContext.xml");
    private AddressBookable instance = ctx.getBean("addressBookImplBean", AddressBookable.class);
    
    public AddressBookableTest() {
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
        cleaner.execute("delete from addressbook");
    }
   
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getStateList method, of class AddressBookable.
     */
    

    /**
     * Test of getSize method, of class AddressBookable.
     */
    //@Test
    public void testGetSize() {
        System.out.println("getSize");
        
        Address a = new Address( "First Name",  "Last Name1",  "street",  "city1",  "state",  "12345");
        Address b = new Address( "First Name",  "Last Name2",  "street",  "city1",  "state",  "1236");
        Address c = new Address( "First Name",  "Last Name2",  "street",  "city7",  "state",  "1230");
        Address d = new Address( "First Name",  "Last Name3",  "street",  "city1",  "not state",  "123");
        instance.addAddress(a);
        instance.addAddress(b);
        instance.addAddress(c);
        instance.addAddress(d);
        int expResult = 4;
        int result = instance.getSize();
        assertEquals(expResult, result);
    }

    /**
     * Test of addAddress method, of class AddressBookable.
     */
    @Test
    public void testAddAddress() {
        System.out.println("addAddress");
       
        Address a = new Address( "First Name",  "Last Name1",  "street",  "city1",  "state",  "12345");
        Address b = new Address( "First Name",  "Last Name2",  "street",  "city1",  "state",  "1236");
        Address c = new Address( "First Name",  "Last Name2",  "street",  "city7",  "state",  "1230");
        Address d = new Address( "First Name",  "Last Name3",  "street",  "city1",  "not state",  "123");
        instance.addAddress(a);
        instance.addAddress(b);
        instance.addAddress(c);
        instance.addAddress(d);
        //assertEquals(4, instance.getSize());
        Address t = instance.getAddressById(a.getId());
        assertEquals(a.getCity(),t.getCity());
        assertEquals(a.getStreet(),t.getStreet());
        assertEquals(a.getState(),t.getState());
    }

    /**
     * Test of updateAddress method, of class AddressBookable.
     */
    @Test
    public void testUpdateAddress() {
        System.out.println("updateAddress");
        Address a = new Address(5, "First Name",  "Last Name1",  "street",  "city1",  "state",  "12345");
        instance.addAddress(a);
        a.setCity("city2");
        a.setZip("54321");
        instance.updateAddress(a);
        Address aFromDb = instance.getAddressById(a.getId());
        assertEquals(a.getCity(), aFromDb.getCity());
        assertEquals(a.getZip(), aFromDb.getZip());
        assertFalse(aFromDb.getZip().equals("12345"));
    }

    /**
     * Test of removeAddress method, of class AddressBookable.
     */
    @Test
    public void testRemoveAddress() {
        System.out.println("removeAddress");
        Address a = new Address( "First Name",  "Last Name1",  "street",  "city1",  "state",  "12345");
        Address b = new Address( "First Name",  "Last Name2",  "street",  "city1",  "state",  "1236");
        Address c = new Address( "First Name",  "Last Name2",  "street",  "city7",  "state",  "1230");
        Address d = new Address( "First Name",  "Last Name3",  "street",  "city1",  "not state",  "123");
        instance.addAddress(a);
        instance.addAddress(b);
        instance.addAddress(c);
        instance.addAddress(d);
        instance.removeAddress(a.getId());
        assertEquals(false, instance.getList().contains(a));
        
    }

    /**
     * Test of getList method, of class AddressBookable.
     */
    @Test
    public void testGetList() {
        System.out.println("getList");
        
        Address a = new Address( "a First Name",  "a Last Name1",  "a street",  "a city1",  "a state",  "12345");
        Address b = new Address( "b First Name",  "b Last Name2",  "b street",  "b city1",  "b state",  "1236");
        Address c = new Address( "c First Name",  "c Last Name2",  "c street",  "c city7",  "c state",  "1230");
        Address d = new Address( "d First Name",  "d Last Name3",  "d street",  "d city1",  "d state",  "123");
        instance.addAddress(a);
        instance.addAddress(b);
        instance.addAddress(c);
        instance.addAddress(d);
        List<Address> result = instance.getList();
        assertEquals(4, result.size());
        assertEquals("a First Name", result.get(0).getFirstName());
        assertEquals("c street", result.get(2).getStreet());
    }

    /**
     * Test of getFilteredList method, of class AddressBookable.
     */
    @Test
    public void testGetFilteredList() {
        System.out.println("getFilteredList");
        Address a = new Address( "a First Name",  "a Last Name1",  "a street",  "a city1",  "a state",  "12345");
        Address b = new Address( "c First Name",  "b Last Name2",  "b street",  "c city7",  "b state",  "1236");
        Address c = new Address( "c First Name",  "c Last Name2",  "c street",  "c city7",  "c state",  "1230");
        Address d = new Address( "d First Name",  "d Last Name3",  "d street",  "d city1",  "d state",  "123");
        Address e = new Address( "c First Name", "", "", "c city7", "", "");
        instance.addAddress(a);
        instance.addAddress(b);
        instance.addAddress(c);
        instance.addAddress(d);
        instance.addAddress(e);
        
        Map<SearchTerm, String> criteria = new HashMap<>();
        criteria.put(SearchTerm.LAST_NAME, "b Last Name2");
        List<Address> cList = instance.getFilteredList(criteria);
        assertEquals(1, cList.size());
        assertEquals(b.getZip(), cList.get(0).getZip());
        
        criteria = new HashMap<>();
        criteria.put(SearchTerm.FIRST_NAME, "c First Name");
        cList = instance.getFilteredList(criteria);
        assertEquals(3, cList.size());
        
        criteria = new HashMap<>();
        criteria.put(SearchTerm.CITY, "c city7");
        cList = instance.getFilteredList(criteria);
        assertEquals(3, cList.size());
        assertEquals(b.getStreet(), cList.get(0).getStreet());
        
        criteria = new HashMap<>();
        criteria.put(SearchTerm.ZIP, "123");
        cList = instance.getFilteredList(criteria);
        assertEquals(1, cList.size());
        assertEquals(d.getLastName(), cList.get(0).getLastName());
        
    }

    

    /**
     * Test of getEmptyId method, of class AddressBookable.
     */
    //@Test
    public void testGetEmptyId() {
        
        AddressBookable i = ctx.getBean("addressBookImplBean", AddressBookable.class);
        System.out.println("getEmptyId");
        Address a = new Address( "a First Name",  "a Last Name1",  "a street",  "a city1",  "a state",  "12345");
        Address b = new Address( "c First Name",  "b Last Name2",  "b street",  "c city7",  "b state",  "1236");
        Address c = new Address( "c First Name",  "c Last Name2",  "c street",  "c city7",  "c state",  "1230");
        Address d = new Address( "d First Name",  "d Last Name3",  "d street",  "d city1",  "d state",  "123");
        i.addAddress(a);
        i.addAddress(b);
        i.addAddress(c);
        i.addAddress(d);
        assertEquals(4, i.getEmptyId());
        
    }

    /**
     * Test of getAddressById method, of class AddressBookable.
     */
    @Test
    public void testGetAddressById() {
        AddressBookable j = ctx.getBean("addressBookImplBean", AddressBookable.class);
        Address a = new Address( "a First Name",  "a Last Name1",  "a street",  "a city1",  "a state",  "12345");
        Address b = new Address( "c First Name",  "b Last Name2",  "b street",  "c city7",  "b state",  "1236");
        Address c = new Address( "c First Name",  "c Last Name2",  "c street",  "c city7",  "c state",  "1230");
        Address d = new Address( "d First Name",  "d Last Name3",  "d street",  "d city1",  "d state",  "123");
        j.addAddress(a);
        j.addAddress(b);
        j.addAddress(c);
        j.addAddress(d);
        assertEquals(a.getFirstName(), j.getAddressById(a.getId()).getFirstName());
        assertEquals(c.getStreet(), j.getAddressById(c.getId()).getStreet());
        
    }
    
}
