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
public class TagList {
    
    private String TagName;
    private int TagId;
    private int TagCount;
    
    public TagList () {
        
    }
    
    public TagList(String TagName, int TagId, int TagCount) {
        this.TagName = TagName;
        this.TagId = TagId;
        this.TagCount = TagCount;
    }

    /**
     * @return the TagName
     */
    public String getTagName() {
        return TagName;
    }

    /**
     * @param TagName the TagName to set
     */
    public void setTagName(String TagName) {
        this.TagName = TagName;
    }

    /**
     * @return the TagId
     */
    public int getTagId() {
        return TagId;
    }

    /**
     * @param TagId the TagId to set
     */
    public void setTagId(int TagId) {
        this.TagId = TagId;
    }

    /**
     * @return the TagCount
     */
    public int getTagCount() {
        return TagCount;
    }

    /**
     * @param TagCount the TagCount to set
     */
    public void setTagCount(int TagCount) {
        this.TagCount = TagCount;
    }
    
}
