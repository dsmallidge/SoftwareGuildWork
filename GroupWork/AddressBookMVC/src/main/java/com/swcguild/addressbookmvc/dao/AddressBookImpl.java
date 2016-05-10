/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swcguild.addressbookmvc.dao;

import com.swcguild.addressbookmvc.dto.Address;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 *
 * @author apprentice
 */
public class AddressBookImpl implements AddressBookable {

    private HashMap<Integer, Address> addressList = new HashMap<>();
    //private String bookName;
    private FileAccess fileAccess;

//    public AddressBookImpl(String bookName){
//        this.bookName = bookName;
//    }
    public AddressBookImpl(FileAccess files) {
        this.fileAccess = files;
        addressList = fileAccess.readFromFile();
    }

    public AddressBookImpl(FileAccess files, boolean option) {
        this.fileAccess = files;

    }

//    @Override
//    public List<Address> getFilteredList(Address temp)
//    {
//        List<Address> book = addressList.values().stream().collect(Collectors.toList());
//        System.out.println(temp.getLastName());
//        if ((temp.getFirstName() != null) && (!temp.getFirstName().equals("")))
//        {
//            book = book.stream().filter(a -> a.getFirstName().equalsIgnoreCase(temp.getFirstName())).collect(Collectors.toList());
//        }
//        if ((temp.getLastName() != null) && (!temp.getLastName().equals("")))
//        {
//            book = book.stream().filter(a -> a.getLastName().equalsIgnoreCase(temp.getLastName())).collect(Collectors.toList());
//        }
//        if ((temp.getStreet() != null) && (!temp.getStreet().equals("")))
//        {
//            book = book.stream().filter(a -> a.getStreet().equalsIgnoreCase(temp.getStreet())).collect(Collectors.toList());
//        }
//        if ((temp.getCity() != null) && (!temp.getCity().equals("")))
//        {
//            book = book.stream().filter(a -> a.getCity().equalsIgnoreCase(temp.getCity())).collect(Collectors.toList());
//        }
//        if ((temp.getState() != null) && (!temp.getState().equals("")))
//        {
//            book = book.stream().filter(a -> a.getState().equalsIgnoreCase(temp.getState())).collect(Collectors.toList());
//        }
//        if ((temp.getZip() != null) && (!temp.getZip().equals("")))
//        {
//            book = book.stream().filter(a -> a.getZip().equalsIgnoreCase(temp.getZip())).collect(Collectors.toList());
//        }
//        
//        return book;
//    }
    @Override
    public List<Address> getFilteredList(Map<SearchTerm, String> criteria) {
        return null;
    }

    @Override
    public int getSize() {
        return addressList.size();
    }

    @Override
    public void addAddress(Address a) {
        int space = a.getId();

        if (a.getId() == 0) {
            space = getEmptyId();
            a.setId(space);
        }

        addressList.put(space, a);
        save();
    }

    @Override
    public void updateAddress(Address a) {
        addressList.put(a.getId(), a);
        save();
    }

    public Address getAddressById(int id) {
        for (Address c : addressList.values()) {
            if (c.getId() == (id)) {
                return c;
            }
        }
        return null;
    }

    @Override
    public void removeAddress(int id) {
        // addressList.add(a.getId(),null);
        addressList.remove(id);
        save();
    }

    @Override
    public List<Address> getList() {
        return addressList.values().stream().collect(Collectors.toList());
    }

    @Override
    public int getEmptyId() {
        for (int i = 0; i < getSize(); i++) {
            if (addressList.get(i) == null) {
                return i;
            }
        }
        return getSize();
    }

    @Override
    public String toString() {
        String listOfAddresses = "";
        for (Address a : addressList.values()) {
            listOfAddresses += a.toString() + "\n";
        }
        return listOfAddresses;
    }

    public void save() {
        fileAccess.saveAddresses(addressList.values());
    }

}
