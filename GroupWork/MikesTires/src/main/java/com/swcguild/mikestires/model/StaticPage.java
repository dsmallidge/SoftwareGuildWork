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
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

public class StaticPage {
    
    @NotEmpty(message="You must supply a value for the Page Content.")
    private String pageContent;
    private int pageId;
    @NotEmpty(message="You must supply a value for Page Name.")
    @Length(max=60, message="Page Name must be no more than 60 characters in length.")
    private String pageName;

    public StaticPage () {
    
}
    
    public StaticPage (String pageContent, int pageId, String pageName) {
    this.pageContent = pageContent;
    this.pageId = pageId;
    this.pageName = pageName;
}
    
    /**
     * @return the pageContent
     */
    public String getPageContent() {
        return pageContent;
    }

    /**
     * @param pageContent the pageContent to set
     */
    public void setPageContent(String pageContent) {
        this.pageContent = pageContent;
    }

    /**
     * @return the pageID
     */
    public int getPageId() {
        return pageId;
    }

    /**
     * @param pageID the pageID to set
     */
    public void setPageId(int pageId) {
        this.pageId = pageId;
    }

    /**
     * @return the pageName
     */
    public String getPageName() {
        return pageName;
    }

    /**
     * @param pageName the pageName to set
     */
    public void setPageName(String pageName) {
        this.pageName = pageName;
    }
    
}
