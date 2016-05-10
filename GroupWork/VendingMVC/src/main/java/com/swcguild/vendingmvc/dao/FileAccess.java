
package com.swcguild.vendingmvc.dao;

import com.swcguild.vendingmvc.dto.Item;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.HashMap;
import java.util.Scanner;

/**
 *
 * @author Jake
 */
public class FileAccess 
{
    String file;
    
    public FileAccess(String fileName)
    {
        file = fileName;
    }
    
    public HashMap<Integer, Item> read() 
    {
         try
        {
            Scanner in = new Scanner(new BufferedReader(new FileReader(file)));
            
            HashMap<Integer, Item> pass = new HashMap<>();
            
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
            return pass;
        }
        catch (FileNotFoundException e)
        {
            return null;
        }
    }
    
    public void write(HashMap<Integer,Item> l) 
    {
        try
        {
            PrintWriter out = new PrintWriter(new FileWriter(file));
            Collection<Item> inven = l.values();

            for (Item c : inven)
            {
                out.println(c.getName() + "::" + c.getCost() + "::" + c.getQuantity() + "::.");
            }
            
            
            out.flush();
            out.close();
    
        } 
        catch (IOException e)
        {
            System.out.println("File failed to save.");
        }
        //return "\nFile Saved";
    }
}
