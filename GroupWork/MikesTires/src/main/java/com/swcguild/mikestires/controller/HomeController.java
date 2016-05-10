/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swcguild.mikestires.controller;

/**
 *
 * @author : Malos, Smallidge, & Kasel
 */
import com.swcguild.mikestires.dao.Dao;
import com.swcguild.mikestires.model.Blog;
import com.swcguild.mikestires.model.StaticPage;
import com.swcguild.mikestires.model.Tag;
import com.swcguild.mikestires.model.TagList;
import java.util.List;
import javax.inject.Inject;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
public class HomeController {

    private Dao dao;

    @Inject
    public HomeController(Dao dao) {
        this.dao = dao;
    }

    @RequestMapping(value = {"/", "/home"}, method = RequestMethod.GET)
    public String displayHomePage() {
        return "home";
    }

    @RequestMapping(value = {"/adminHome"}, method = RequestMethod.GET)
    public String displayAdminHomePage() {
        return "adminHome";
    }

    @RequestMapping(value = "/blog/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Blog getBlog(@PathVariable("id") int id) {
        return dao.getBlogById(id);
    }

    @RequestMapping(value = "/article/{id}", method = RequestMethod.GET)
    @ResponseBody
    public StaticPage getStaticPage(@PathVariable("id") int id) {
        return dao.getStaticPageById(id);
    }

    @RequestMapping(value = "/archives/{id}", method = RequestMethod.GET)
    @ResponseBody
    public List<Blog> getBlogsByMonth(@PathVariable("id") String id) {
        // string will be, e.g. April2016
        int idLen = id.length();
        String monthYear = id.substring(0, idLen - 4) + " " + id.substring(idLen - 4);
        // monthYear should now be "April 2016"
        return dao.getBlogsByMonth(monthYear);
    }

    @RequestMapping(value = "/blog", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public Blog createBlog(@Valid @RequestBody Blog blog) {
        
        dao.addBlog(blog);
        return blog;
    }

    @RequestMapping(value = "/blog/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBlog(@PathVariable("id") int id) {
        dao.removeBlog(id);
    }

    @RequestMapping(value = "/blog/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void putBlog(@PathVariable("id") int id, @Valid @RequestBody Blog blog) {
        blog.setBlogId(id);
        dao.updateBlog(blog);
    }
    
    @RequestMapping(value = "/approved/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void putApproval(@PathVariable("id") int id) {
        dao.updateApproval(id);
    }

    @RequestMapping(value = "/approvals", method = RequestMethod.GET)
    @ResponseBody
    public List<Blog> getApprovals() {
        return dao.needsApproval();
    }

    @RequestMapping(value = "/search/{id}", method = RequestMethod.GET)
    @ResponseBody
    public List<Blog> searchByTagId(@PathVariable("id") int id) {
        return dao.searchBlogs(id);
    }
    
    @RequestMapping(value = "/searchbyname/{name}", method = RequestMethod.GET)
    @ResponseBody
    public List<Blog> searchByTagId(@PathVariable("name") String name) {
        Tag temp = dao.getTagByName(name);
        return dao.searchBlogs(temp.getTagId());
    }

    @RequestMapping(value = "/taglist", method = RequestMethod.GET)
    @ResponseBody
    public List<TagList> getTagList() {
        return dao.getTagList();
    }

    @RequestMapping(value = "/staticpage", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public StaticPage createStaticPage(@Valid @RequestBody StaticPage page) {
        dao.addStaticPage(page);
        return page;
    }
    
    @RequestMapping(value = "/daterange", method = RequestMethod.GET)
    @ResponseBody
    public List<Blog> loadBlogsByDate(String start, String end) {
        return dao.findBlogsByDate(start, end);
    }

    @RequestMapping(value = "/latestblogs", method = RequestMethod.GET)
    @ResponseBody
    public List<Blog> loadLatestBlogs() {
        return dao.getLatestBlogs();
    }

    @RequestMapping(value = "/categories/{category}", method = RequestMethod.GET)
    @ResponseBody
    public List<Blog> getBlogsByCategory(@PathVariable("category") String category) {
        return dao.getBlogsByCategory(category);
    }

    @RequestMapping(value = "/archives", method = RequestMethod.GET)
    @ResponseBody
    public List<String> loadArchiveDropDownTable() {
        return dao.fillArchive();
    }

    @RequestMapping(value = "/staticpages", method = RequestMethod.GET)
    @ResponseBody
    public List<StaticPage> loadStaticPages() {
        return dao.getStaticPages();
    }

    @RequestMapping(value = {"/customError"}, method = RequestMethod.GET)
    public String displayCustomErrorPage() {
        return "customError";
    }

    @RequestMapping(value = {"/editPost"}, method = RequestMethod.GET)
    public String displayEditPostPage() {
        return "editPost";
    }

    @RequestMapping(value = {"/newArticle"}, method = RequestMethod.GET)
    public String displayNewArticlePage() {
        return "newArticle";
    }

    @RequestMapping(value = {"/newBlogPostForm"}, method = RequestMethod.GET)
    public String displayNewBlogPostFormPage() {
        return "newBlogPostForm";
    }

    @RequestMapping(value = {"/approvePosts"}, method = RequestMethod.GET)
    public String displayApprovePostsPage() {
        return "approvePosts";
    }

}
