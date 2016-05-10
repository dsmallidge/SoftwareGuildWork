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
public class Item {
    
    private String name;
    private int cost;
    private int quantity;
    
    public Item(){
        
    }
    
    public Item(String name, int cost, int quantity){
        this.name = name;
        this.cost = cost;
        this.quantity = quantity;
    }
    
    public Item(Item s){
        this.name = s.name;
        this.cost = s.cost;
        this.quantity = s.quantity;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the cost
     */
    public int getCost() {
        return cost;
    }

    /**
     * @return the quantity
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * @param quantity the quantity to set
     */
    public void setQuantity(int quantity) {
        this.quantity = this.quantity + quantity;
    }
    
    
}
