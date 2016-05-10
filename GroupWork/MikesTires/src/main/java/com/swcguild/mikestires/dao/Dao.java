/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swcguild.mikestires.dao;

/**
 *
 * @author : Malos, Smallidge, & Kasel
 */
import com.swcguild.mikestires.model.Blog;
import com.swcguild.mikestires.model.StaticPage;
import com.swcguild.mikestires.model.Tag;
import com.swcguild.mikestires.model.TagList;
import java.util.List;

public interface Dao {
    
    public Blog addBlog(Blog blog);

    public void removeBlog(int blogId);

    public void updateBlog(Blog blog);
    
    public void updateApproval(int blogId);

    public Blog getBlogById(int blogId);
    
    public List<Blog> needsApproval();

    public List<TagList> getTagList();
            
    public List<Blog> searchBlogs(int tagId);
    
    public Tag addTag(String tagName);
    
    public StaticPage addStaticPage(StaticPage page);
    
    public List<Blog> getLatestBlogs();
    
    public List<Blog> getBlogsByCategory(String category);
    
    public List<Blog> getBlogsByMonth(String monthYear);
    
    public List<String> fillArchive();
    
    public List<StaticPage> getStaticPages();
    
    public StaticPage getStaticPageById(int pageId);
    
    public Tag getTagByName(String name);
    
    public String[] tagNames(int blogId);
    
    public List<Blog> findBlogsByDate(String start, String end);
    
}
