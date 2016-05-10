/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swcguild.dvd.dao;

import com.swcguild.dvd.dao.DvdListDaoInMemImpl;
import com.swcguild.dvd.model.Dvd;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author apprentice
 */
public class FileAccess {

    public static void writeToFile(Map<Integer, Dvd> dvdLibrary) throws IOException {

        try {

            PrintWriter out = new PrintWriter(new FileWriter("DVDLibrary.txt"));
            Collection<Dvd> dvdList = dvdLibrary.values();
            for (Dvd v : dvdList) {
                out.print(v.getId() + "::" + v.getTitle() + "::" + v
                        .getReleaseDate()
                        + "::" + v.getMpaaRating() + "::" + v.getDirector() + "::"
                        + v.getStudio() + "::" + v.getNote() + "\n");

            }
            out.flush();
            out.close();
        } catch (IOException ex) {
        }
    }

    public static Map<Integer, Dvd> readFromFile() throws FileNotFoundException {
        Map<Integer, Dvd> dvdLibrary = new HashMap<>();

        try {
            Scanner in = new Scanner(new BufferedReader(new FileReader("DVDLibrary.txt")));

            String inString;
            String[] inArray;
            int year, month, day;

            while (in.hasNextLine()) {
                inString = in.nextLine();
                inArray = inString.split("::");

                Dvd newDVD = new Dvd();
                int id = Integer.parseInt(inArray[0]);
                newDVD.setId(id);
                newDVD.setTitle(inArray[1]);
                newDVD.setReleaseDate(inArray[2]);
                newDVD.setMpaaRating(inArray[3]);
                newDVD.setDirector(inArray[4]);
                newDVD.setStudio(inArray[5]);
                newDVD.setNote(inArray[6]);
                dvdLibrary.put(id, newDVD);
            }

        } catch (FileNotFoundException ex) {
        }
        return dvdLibrary;
    }
}
