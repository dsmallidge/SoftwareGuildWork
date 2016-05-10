/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flooring.dao;

import flooring.dto.Order;
import flooring.operations.OrderOperations;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;


/**
 *
 * @author apprentice
 */
public interface DAOInterface {
   
    public void writeToDisk(HashMap<String, HashMap<Integer, Order>> orderMap) throws IOException;
    
    public String readFromDisk(String filename) throws FileNotFoundException;
    
    public HashMap readFromDisk2(String filename) throws FileNotFoundException;
}
