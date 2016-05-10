/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swcguild.dvd.dao;

import com.swcguild.dvd.dao.DvdListDBDao.SearchTerm;
import com.swcguild.dvd.model.Dvd;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 *
 * @author apprentice
 */
public class DvdListDaoInMemImpl implements DvdListDao {

    private Map<Integer, Dvd> dvdMap = new HashMap<>();
    private static int dvdIdCounter = 0;
    FileAccess fileAccess;
    
    public DvdListDaoInMemImpl() {
        dvdIdCounter = 0;
    }

    public DvdListDaoInMemImpl (FileAccess file) throws FileNotFoundException {
        this.fileAccess = file;
        dvdMap = file.readFromFile();
        
    }
    
    @Override
    public Dvd addDvd(Dvd dvd) {

        dvd.setId(dvdIdCounter);
        getDvdMap().put(dvd.getId(), dvd);
        dvdIdCounter++;
        try {
            fileAccess.writeToFile(dvdMap);
        } catch (IOException ex) {
            
        }   
        return dvd;

    }

    @Override
    public void removeDvd(int dvdId) {
        getDvdMap().remove(dvdId);
        try {
            fileAccess.writeToFile(dvdMap);
        } catch (IOException ex) {
            
        }
    }

    @Override
    public void updateDvd(Dvd dvd) {
        getDvdMap().put(dvd.getId(), dvd);
        try {
            fileAccess.writeToFile(dvdMap);
        } catch (IOException ex) {
            
        }
    }

    @Override
    public List<Dvd> getAllDvds() {
        
        Collection<Dvd> c = getDvdMap().values();
        return new ArrayList(c);
    }

    @Override
    public Dvd getDvdById(int dvdId) {
        return getDvdMap().get(dvdId);
    }

    @Override
    public List<Dvd> searchDvds(Map<SearchTerm, String> criteria) {
        
        String title = criteria.get(SearchTerm.TITLE);
        String releaseDate = criteria.get(SearchTerm.RELEASE_DATE);
        String mpaaRating = criteria.get(SearchTerm.MPAA_RATING);
        String director = criteria.get(SearchTerm.DIRECTOR);
        String studio = criteria.get(SearchTerm.STUDIO);
        String note = criteria.get(SearchTerm.NOTE);
        
        Dvd search = new Dvd(title, releaseDate, mpaaRating, director, studio, note);

        List<Dvd> seachList = new ArrayList<Dvd>();

        for (Dvd a : getDvdMap().values()) {
            if ((a.getTitle().equalsIgnoreCase(search.getTitle())) 
                 || search.getTitle().equals("")) {
            
                if ((a.getReleaseDate().equals(search.getReleaseDate()))
                     || search.getReleaseDate().equals("")) {
                
                    if ((a.getMpaaRating().equalsIgnoreCase(search.getMpaaRating()))
                         || search.getMpaaRating().equals("")) {
                    
                        if ((a.getDirector().equalsIgnoreCase(search.getDirector()))
                             || search.getDirector().equals("")) {
                        
                            if ((a.getStudio().equalsIgnoreCase(search.getStudio()))
                                 || search.getStudio().equals("")) {
                            
                                if ((a.getNote().equalsIgnoreCase(search.getNote()))
                                     || search.getNote().equals("")) {
                                
                                    seachList.add(a);
                                }
                            }
                        }
                    }
                }
            }
        }
        return seachList;
    }
    
//    @Override
//    public List<Dvd> searchDvds(Dvd temp)
//    {
//        List<Dvd> book = dvdList.values().stream().collect(Collectors.toList());
//        
//        if ((temp.getTitle() != null) && (!temp.getTitle().equals("")))
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

    /**
     * @return the dvdMap
     */
    
    public Map<Integer, Dvd> getDvdMap() {
        return dvdMap;
    }

    /**
     * @param dvdMap the dvdMap to set
     */
    
}
