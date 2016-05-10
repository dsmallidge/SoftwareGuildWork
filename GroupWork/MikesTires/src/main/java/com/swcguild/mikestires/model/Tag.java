/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swcguild.mikestires.model;

/**
 *
 * @author : Malos, Smallidge, & Kasel
 */
import java.util.Objects;

public class Tag {
    
    private int tagId;
    private String name;
    
    public Tag() {
        
    }
    
    public Tag(String name) {
        this.name = name;
    }
    
    public Tag (int tagId, String name) {
        this.tagId = tagId;
        this.name = name;
    }

    /**
     * @return the tagID
     */
    public int getTagId() {
        return tagId;
    }

    /**
     * @param tagID the tagID to set
     */
    public void setTagId(int tagId) {
        this.tagId = tagId;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }
    
     @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Tag other = (Tag) obj;
        if (this.tagId != other.tagId) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return true;
    }
    
    
}
