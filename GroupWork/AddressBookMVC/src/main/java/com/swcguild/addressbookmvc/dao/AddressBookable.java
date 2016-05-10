/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swcguild.addressbookmvc.dao;

import com.swcguild.addressbookmvc.dto.Address;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author apprentice
 */
public interface AddressBookable {

    public int getSize();
    public void addAddress(Address a);
    public void updateAddress(Address a);
    public void removeAddress(int id);
    public List<Address> getList();
    public List<Address> getFilteredList(Map<SearchTerm, String> criteria);
    public int getEmptyId();
    public Address getAddressById (int id);
}
