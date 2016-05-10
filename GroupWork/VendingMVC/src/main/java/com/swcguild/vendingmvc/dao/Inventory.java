/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swcguild.vendingmvc.dao;

import com.swcguild.vendingmvc.dto.Item;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

/**
 *
 * @author apprentice
 */
public class Inventory
{

    private HashMap<Integer, Item> stock = new HashMap<>();
    FileAccess files;
    
    public Inventory(FileAccess records)
    {
        files = records;
        stock = files.read();
    }

    public HashMap<Integer, Item> getItemList()
    {

        HashMap<Integer, Item> list = new HashMap<>();
        for (Integer s : stock.keySet())
        {

            list.put(s, new Item(stock.get(s)));
        }

        return list;
        
        
        

    }
    
    public void setDefaults() {
        stock.put(0, new Item("Juice", 85, 25));
        stock.put(1, new Item("Water", 150, 20));
        stock.put(2, new Item("Gum", 35, 40));
        stock.put(3, new Item("Candy Bar", 200, 15));
        stock.put(4, new Item("Chips", 125, 30));
        stock.put(5, new Item("Chocolate", 120, 35));
    }

    public Item getItem(String itemName)
    {

        Collection<Item> itemCol = stock.values();
        Iterator<Item> itemIter = itemCol.iterator();

        while (itemIter.hasNext())
        {
            Item t = itemIter.next();
            if (t.getName().equals(itemName))
            {
                return t;
            }
        }

        return null;

    }
    
    public Item getItem(Integer key)
    {
        return stock.get(key);
    }

    public Integer getKey(String itemName)
    {

        Set<Integer> itemSet = stock.keySet();
        Iterator<Integer> itemIter = itemSet.iterator();

        while (itemIter.hasNext())
        {
            Integer t = itemIter.next();
            if (stock.get(t).getName().equals(itemName))
            {
                return t;
            }

        }

        return null;
    }

    public void vend(String itemName, int moneyIn)
    {

        Item s = getItem(itemName);

        //Change c = new Change();
        if (s == null)
        {
          
        } 
        else if (moneyIn >= s.getCost())
        {

            //c.calculateChange(moneyIn - s.getCost());
            stock.get(getKey(itemName)).setQuantity(-1);

        }

      

    }

    public void fillMachine(HashMap<Integer, Item> items)
    {
        stock = items;
    }
    
    public int getSize()
    {
        return stock.size();
    }
    public Collection<Item> getCollection () {
        return stock.values();
    }
    
    public boolean checkStock(Item c)
    {
        if (c.getQuantity() > 0)
        {
            return true;
        } else
        {
            return false;
        }
    }

}
