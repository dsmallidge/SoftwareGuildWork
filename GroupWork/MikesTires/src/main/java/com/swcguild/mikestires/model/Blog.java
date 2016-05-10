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
import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

public class Blog {

    private int blogId;
    @NotEmpty(message="You must supply a value for Title.")
    @Length(max=60, message="Title must be no more than 60 characters in length.")
    private String title;
    private String category;
    @NotEmpty(message="You must supply a value for the Blog Entry.")
    private String blogEntry;
    @NotEmpty(message="You must supply a value for the Start Date.")
    private String startDate;
    private String endDate;
    private String approved;
    private String[] tags;
    private String userName;
    
    public Blog () {
        
    }
    
    public Blog (String title, String category, String blogEntry, String startDate, String endDate,
            String approved, String[] tags, String userName) {
        this.title = title;
        this.category = category;
        this.blogEntry = blogEntry;
        this.startDate = startDate;
        this.endDate = endDate;
        this.approved = approved;
        this.tags = tags;
        
        this.userName = userName;
    }
    
    public Blog (int blogId, String title, String category, String blogEntry, String startDate, String endDate,
            String approved, String[] tags, String userName) {
        this.blogId = blogId;
        this.title = title;
        this.category = category;
        this.blogEntry = blogEntry;
        this.startDate = startDate;
        this.endDate = endDate;
        this.approved = approved;
        this.tags = tags;
        this.userName = userName;
     
    }

    /**
     * @return the blogID
     */
    public int getBlogId() {
        return blogId;
    }

    /**
     * @param blogID the blogID to set
     */
    public void setBlogId(int blogId) {
        this.blogId = blogId;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the category
     */
    public String getCategory() {
        return category;
    }

    /**
     * @param category the category to set
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * @return the blogEntry
     */
    public String getBlogEntry() {
        return blogEntry;
    }

    /**
     * @param blogEntry the blogEntry to set
     */
    public void setBlogEntry(String blogEntry) {
        this.blogEntry = blogEntry;
    }

    /**
     * @return the startDate
     */
    public String getStartDate() {
        return startDate;
    }

    /**
     * @param startDate the startDate to set
     */
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    /**
     * @return the endDate
     */
    public String getEndDate() {
        return endDate;
    }

    /**
     * @param endDate the endDate to set
     */
    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    /**
     * @return the approved
     */
    public String getApproved() {
        return approved;
    }

    /**
     * @param approved the approved to set
     */
    public void setApproved(String approved) {
        this.approved = approved;
    }

    /**
     * @return the tags
     */
    public String[] getTags() {
        return tags;
    }

    /**
     * @return the userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @param userName the userName to set
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Blog other = (Blog) obj;
        if (this.blogId != other.blogId) {
            return false;
        }
        if (!Objects.equals(this.title, other.title)) {
            return false;
        }
        if (!Objects.equals(this.category, other.category)) {
            return false;
        }
        if (!Objects.equals(this.blogEntry, other.blogEntry)) {
            return false;
        }
        if (!Objects.equals(this.startDate, other.startDate)) {
            return false;
        }
        if (!Objects.equals(this.endDate, other.endDate)) {
            return false;
        }
        if (!Objects.equals(this.approved, other.approved)) {
            return false;
        }
        if (!Objects.equals(this.userName, other.userName)) {
            return false;
        }
        return true;
    }
    
    public void setTags(String[] tags) {
        tags = new HashSet<String>(Arrays.asList(tags)).toArray(new String[0]);
        this.tags = tags;
    }

}
