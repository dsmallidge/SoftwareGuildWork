package com.swcguild.addressbookmvc.dto;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author apprentice
 */
public class Address {
    
    private int id;
    
    @NotEmpty(message = "You must supply a value for First Name.")
    @Length(max = 50, message = "First Name must be no more than 50 characters in length.")
    private String firstName;
    
    @NotEmpty(message = "You must supply a value for Last Name.")
    @Length(max = 50, message = "Last Name must be no more than 50 characters in length.")
    private String lastName;
    
    @NotEmpty(message = "You must add a street.")
    @Length(max = 50, message = "Street name must be no more than 50 characters in length.")
    private String street;
    
    @NotEmpty(message = "You must add a city.")
    @Length(max = 50, message = "City name must be no more than 50 characters in length.")
    private String city;
    
    @NotEmpty(message = "You must add a state.")
    @Length(max = 15, message = "State name must be no more than 15 characters in length.")
    private String state;
    
    @NotEmpty(message = "You must supply a zip code.")
    @Length(max = 10, message = "Zip code mush be no more than 10 characters in length.")
    private String zip;
    
    public Address(){}
    
    public Address(int id, String firstName, String lastName, String street, String city, String state, String zip){
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.street = street;
        this.city = city;
        this.state = state;
        this.zip = zip;
    }
    
     public Address( String firstName, String lastName, String street, String city, String state, String zip){
        this.firstName = firstName;
        this.lastName = lastName;
        this.street = street;
        this.city = city;
        this.state = state;
        this.zip = zip;
    }

    /**
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @return the city
     */
    public String getCity() {
        return city;
    }

    /**
     * @param city the city to set
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * @return the state
     */
    public String getState() {
        return state;
    }

    /**
     * @param state the state to set
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * @return the street
     */
    public String getStreet() {
        return street;
    }

    /**
     * @param street the street to set
     */
    public void setStreet(String street) {
        this.street = street;
    }

    /**
     * @return the zip
     */
    public String getZip() {
        return zip;
    }

    /**
     * @param zip the zip to set
     */
    public void setZip(String zip) {
        this.zip = zip;
    }
    
    public String toString() {
        return (id + " " + firstName + " " + lastName + "\n" + street + "\n" + city + ", " + state + "  " + zip + "\n");
         
    }    

    /**
     * @return the id
     */
    public int getId()
    {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id)
    {
        this.id = id;
    }
    
    
}
