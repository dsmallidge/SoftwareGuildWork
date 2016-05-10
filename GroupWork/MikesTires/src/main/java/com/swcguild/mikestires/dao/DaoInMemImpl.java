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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DaoInMemImpl implements Dao {

    private Map<Integer, Blog> blogMap = new HashMap<>();
    private Map<Integer, StaticPage> staticPageMap = new HashMap<>();
    private static int blogIdCounter = 0;
    private static int staticPageCounter = 0;

    @Override
    public Blog addBlog(Blog blog) {

        blog.setBlogId(blogIdCounter);
        blogIdCounter++;
        blogMap.put(blog.getBlogId(), blog);
        return blog;
    }

    @Override
    public void removeBlog(int blogId) {
        blogMap.remove(blogId);
    }

    @Override
    public void updateBlog(Blog blog) {
        blogMap.put(blog.getBlogId(), blog);
    }
    
    public void updateApproval(int blogId) {
        getBlogById(blogId).setApproved("Y");
    }

    @Override
    public Blog getBlogById(int blogId) {
        return blogMap.get(blogId);
    }

    @Override
    public List<Blog> needsApproval() {
        List<Blog> result = blogMap
                .values()
                .stream()
                .filter(a -> a.getApproved()
                        .equalsIgnoreCase("y"))
                .collect(Collectors.toList());
        return result;
    }
    
    public List<Blog> findBlogsByDate(String start, String end) {
        List<Blog> result = blogMap
                .values()
                .stream()
                .filter(p -> ((p.getStartDate().compareTo(start) >= 0) && (p.getStartDate().compareTo(end) <= 0)))
                .collect(Collectors.toList());
        return result;
    }

    @Override
    public List<Blog> getLatestBlogs() {
        List<Blog> result;
        HashMap<Integer, Blog> approvedMap = new HashMap<>();

        for (Map.Entry<Integer, Blog> entry : blogMap.entrySet()) {
            int key = entry.getKey();
            if (blogMap.get(key).getApproved().equalsIgnoreCase("y")) {
                approvedMap.put(key, blogMap.get(key));
            }
        }

        result = approvedMap.values()
                .stream()
                .sorted((b1, b2) -> Integer.compare(b2.getBlogId(), b1.getBlogId()))
                .limit(5)
                .collect(Collectors.toList());

        return result;
    }

    @Override
    public StaticPage addStaticPage(StaticPage page) {
        page.setPageId(staticPageCounter);
        staticPageCounter++;
        staticPageMap.put(page.getPageId(), page);
        return page;
    }

    @Override
    public Tag addTag(String tagName) {
        Tag t = new Tag(tagName);
        return t;
    }

    @Override
    public List<Blog> searchBlogs(int tagId) {
        List<Blog> result;
        HashMap<Integer, Blog> approvedMap = new HashMap<>();

        for (Map.Entry<Integer, Blog> entry : blogMap.entrySet()) {
            int key = entry.getKey();
            if (blogMap.get(key).getApproved().equalsIgnoreCase("y")) {
                approvedMap.put(key, blogMap.get(key));
            }
        }

        result = approvedMap.values()
                .stream()
                .filter((b -> b.getTags().equals(tagId)))
                .collect(Collectors.toList());
        return result;
    }

    @Override
    public List<Blog> getBlogsByCategory(String category) {
        List<Blog> result;
        HashMap<Integer, Blog> approvedMap = new HashMap<>();

        for (Map.Entry<Integer, Blog> entry : blogMap.entrySet()) {
            int key = entry.getKey();
            if (blogMap.get(key).getApproved().equalsIgnoreCase("y")) {
                approvedMap.put(key, blogMap.get(key));
            }
        }

        result = approvedMap.values()
                .stream()
                .filter(b -> b.getCategory().equalsIgnoreCase(category))
                .collect(Collectors.toList());
        return result;
    }

    @Override
    public List<Blog> getBlogsByMonth(String monthYear) {
        List<Blog> result;
        result = blogMap.values()
                .stream()
                .filter(a -> a.getStartDate().startsWith(monthYear))
                .collect(Collectors.toList());
        return result;
    }

    public Tag getTagByName(String name) {
        Tag t = new Tag(name);
        return t;
    }
    
    @Override
    public List<StaticPage> getStaticPages() {
        List<StaticPage> temp = new ArrayList<>();
        return temp;
    }
    
    @Override
    public List<String> fillArchive() {
        List<String> ls = new ArrayList<>();
        return ls;
    }
    
    @Override
    public StaticPage getStaticPageById(int pageId){
        StaticPage result = new StaticPage();
        return result;
    }
    
    @Override
     public List<TagList> getTagList() {
        List<TagList> tl = new ArrayList<>();
        return tl;
     }
     
    @Override
    public String[] tagNames(int blogId) {
        String[] temp = new String[2];
        return temp;
    }
     
}
