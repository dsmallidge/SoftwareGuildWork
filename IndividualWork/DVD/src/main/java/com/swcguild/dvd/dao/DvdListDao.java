/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.swcguild.dvd.dao;
import com.swcguild.dvd.model.Dvd;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;


import java.util.Map;

public interface DvdListDao {


    public Dvd addDvd(Dvd dvd);
    

    public void removeDvd(int dvdId);
   

    public void updateDvd(Dvd dvd);
    

    public List<Dvd> getAllDvds();
    

    public Dvd getDvdById(int dvdId);
    
    public List<Dvd> searchDvds(Map<DvdListDBDao.SearchTerm, String> criteria);
      
}
