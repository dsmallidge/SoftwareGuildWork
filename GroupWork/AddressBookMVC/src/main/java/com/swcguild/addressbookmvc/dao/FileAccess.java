/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swcguild.addressbookmvc.dao;

/**
 *
 * @author apprentice
 */
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import com.swcguild.addressbookmvc.dto.Address;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public class FileAccess
{

    String file;

    public FileAccess(String fileName)
    {
        file = fileName;
    }

    public void saveAddresses(Collection<Address> list)
    {

        try
        {
            PrintWriter out = new PrintWriter(new FileWriter(file));
            for (Address a : list)
            {
                out.print(a.getId() + "::" + a.getFirstName() + "::" + a.getLastName()
                        + "::" + a.getStreet() + "::" + a.getCity() + "::"
                        + a.getState() + "::" + a.getZip() + "::." + "\n");
            }
            out.flush();
            out.close();
        } catch (IOException e)
        {

        }

    }

    public HashMap<Integer, Address> readFromFile() 
    {
        HashMap<Integer, Address> addressList = new HashMap<>();
        try
        {
            Scanner in = new Scanner(new BufferedReader(new FileReader(file)));

            
            Address currentAddress;
            String inString;
            String[] inArray;

            while (in.hasNextLine())
            {
                inString = in.nextLine();
                inArray = inString.split("::");
                currentAddress = new Address(Integer.parseInt(inArray[0]), inArray[1], inArray[2], inArray[3], inArray[4], inArray[5], inArray[6]);
                addressList.put(Integer.parseInt(inArray[0]),currentAddress);
            }
            
        } catch (FileNotFoundException e)
        {

        }
        return addressList;
    }
}
