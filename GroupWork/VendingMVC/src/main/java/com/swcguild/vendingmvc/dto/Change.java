/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swcguild.vendingmvc.dto;

/**
 *
 * @author apprentice
 */
public class Change {
    
    private int quarters;
    private int dimes;
    private int nickels;
    private int pennies;
    
    public Change(){
        
    }

    /**
     * @return the quarters
     */
    public int getQuarters() {
        return quarters;
    }

    /**
     * @return the dimes
     */
    public int getDimes() {
        return dimes;
    }

    /**
     * @return the nickels
     */
    public int getNickels() {
        return nickels;
    }

    /**
     * @return the pennies
     */
    public int getPennies() {
        return pennies;
    }
    
    public void calculateChange(int changeDue){
        
        int re = changeDue;
        this.quarters = re / 25;
        re = re % 25;
        this.dimes = re / 10;
        re = re % 10;
        this.nickels = re / 5;
        re = re % 5;
        this.pennies = re;     
    }  
}