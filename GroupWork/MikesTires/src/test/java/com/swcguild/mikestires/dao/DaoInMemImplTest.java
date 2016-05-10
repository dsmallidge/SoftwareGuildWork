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
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class DaoInMemImplTest {

    private static Dao dao1;

    public DaoInMemImplTest() {
    }

    @BeforeClass
    public static void setUpClass() {
        ApplicationContext ctx
                = new ClassPathXmlApplicationContext("test-applicationContext.xml");
        dao1 = (Dao) ctx.getBean("Dao");
        JdbcTemplate cleaner = (JdbcTemplate) ctx.getBean("jdbcTemplate");
        cleaner.execute("delete from BlogTag");
        cleaner.execute("delete from Tag");
        cleaner.execute("delete from BlogPost");
        cleaner.execute("delete from StaticPage");
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testAAddGetDeleteBlog() {
        System.out.println("addGetDeleteBlog");
        Blog nb1 = new Blog();
        nb1.setTitle("Test Blog One");
        nb1.setCategory("General");
        nb1.setBlogEntry("<h2>Blog Post 1</h2><p><strong>It's been a great month at Mike's Tires! Our new favorite brand is Hankook. You'll love 'em!</strong>"
                + "</p><p><strong>Here's Why:</strong></p><ul><li><strong>they have a funny name</strong></li><li><strong>they're great tires</strong>"
                + "</li><li><strong>they're not too cheap but not too pricey</strong></li></ul><h3><em><strong>Happy Driving!</strong></em></h3>");
        nb1.setStartDate("2016-04-01");
        nb1.setEndDate("2017-04-30");
        nb1.setApproved("Y");
        
        String [] ta = new String[2];
        ta[0] = ("cheap");
        ta[1] = ("Hankook");
        nb1.setTags(ta);
        nb1.setUserName("mike");
        Blog fromDao = dao1.addBlog(nb1);
        Blog fromDb = dao1.getBlogById(fromDao.getBlogId());
        assertEquals(fromDb, fromDao);
        dao1.removeBlog(fromDao.getBlogId());
        assertNull(dao1.getBlogById(fromDao.getBlogId()));
    }

    @Test
    public void testBaddUpdateBlog() {
        System.out.println("addUpdateBlog");
        Blog nb2 = new Blog();
        nb2.setTitle("Test Blog Two");
        nb2.setCategory("Promotions");
        nb2.setBlogEntry("<h2>Blog Post 2</h2><p><strong>It's been a great month at Mike's Tires! Our new favorite brand is Uniroyal. You'll love 'em!</strong>"
                + "</p><p><strong>Here's Why:</strong></p><ul><li><strong>they have a famous name</strong></li><li><strong>they're great tires</strong>"
                + "</li><li><strong>they're cheap!!</strong></li></ul><h3></h3>");
        nb2.setStartDate("2016-03-01");
        nb2.setEndDate("2017-03-31");
        nb2.setApproved("Y");
        
        String[] ta1 = new String[2];
        ta1[0] = ("famous");
        ta1[1] = ("Uniroyal");
        nb2.setTags(ta1);
        nb2.setUserName("mike");
        Blog fromDao1 = dao1.addBlog(nb2);
        fromDao1.setStartDate("2016-02-15");
        dao1.updateBlog(fromDao1);
        Blog fromDb1 = dao1.getBlogById(fromDao1.getBlogId());
        assertEquals(fromDb1, fromDao1);
        String[] tags1 = dao1.tagNames(fromDao1.getBlogId());
        assertEquals(tags1[0],ta1[0]);
        assertEquals(tags1[1],ta1[1]);
    }

    @Test
    public void testCGetLatestBlogs() {
        System.out.println("getLatestBlogs");
        Blog nb3 = new Blog();
        nb3.setTitle("Test Blog Three");
        nb3.setCategory("General");
        nb3.setBlogEntry("<h2>Blog Post 3</h2><p><strong>It's been a great month at Mike's Tires! Our new favorite brand is Hankook. You'll love 'em!</strong>"
                + "</p><p><strong>Here's Why:</strong></p><ul><li><strong>they have a funny name</strong></li><li><strong>they're great tires</strong>"
                + "</li><li><strong>they're not too cheap but not too pricey</strong></li></ul><h3><em><strong>Happy Driving!</strong></em></h3>");
        nb3.setStartDate("2016-04-01");
        nb3.setEndDate("2017-04-30");
        nb3.setApproved("Y");
        
        String [] ta = new String [2];
        ta[0] = ("funny");
        ta[1] = ("Hankook");
        nb3.setTags(ta);
        nb3.setUserName("mike");
        dao1.addBlog(nb3);
        Blog nb4 = new Blog();
        nb4.setTitle("Test Blog Four");
        nb4.setCategory("Promotions");
        nb4.setBlogEntry("<h2>Blog Post 4</h2><p><strong>It's been a great month at Mike's Tires! Our new favorite brand is Uniroyal. You'll love 'em!</strong>"
                + "</p><p><strong>Here's Why:</strong></p><ul><li><strong>they have a famous name</strong></li><li><strong>they're great tires</strong>"
                + "</li><li><strong>they're cheap!!</strong></li></ul><h3></h3>");
        nb4.setStartDate("2016-03-01");
        nb4.setEndDate("2017-03-31");
        nb4.setApproved("Y");
        
        String[] ta1 = new String[2];
        ta1[0] = ("famous");
        ta1[1] = ("Uniroyal");
        nb4.setTags(ta1);
        nb4.setUserName("mike");
        dao1.addBlog(nb4);
        Blog nb5 = new Blog();
        nb5.setTitle("Test Blog Five");
        nb5.setCategory("Promotions");
        nb5.setBlogEntry("<h2>Blog Post 5</h2><p><strong>It's been a great month at Mike's Tires! Our new favorite brand is Blasphox. You'll love 'em!</strong>"
                + "</p><p><strong>Here's Why:</strong></p><ul><li><strong>they have a weird name</strong></li><li><strong>they're great tires</strong>"
                + "</li><li><strong>they're cheap!!</strong></li></ul><h3></h3>");
        nb5.setStartDate("2016-02-01");
        nb5.setEndDate("2017-03-31");
        nb5.setApproved("Y");
        
        String[] ta2 = new String[2];
        ta2[0] = ("weird");
        ta2[1] = ("Blasphox");
        nb5.setTags(ta2);
        nb5.setUserName("sarah");
        dao1.addBlog(nb5);
        Blog nb6 = new Blog();
        nb6.setTitle("Test Blog Six");
        nb6.setCategory("Uncategorized");
        nb6.setBlogEntry("<h2>Blog Post 6</h2><p><strong>It's been a great month at Mike's Tires! Our new favorite brand is ABC. You'll love 'em!</strong>"
                + "</p><p><strong>Here's Why:</strong></p><ul><li><strong>they have a funny name</strong></li><li><strong>they're great tires</strong>"
                + "</li><li><strong>they're cheap!!</strong></li></ul><h3></h3>");
        nb6.setStartDate("2016-03-01");
        nb6.setEndDate("2017-03-31");
        nb6.setApproved("N");
        
        String [] ta3 = new String [2];
        ta3[0] = ("funny");
        ta3[1] = ("ABC");
        nb6.setTags(ta3);
        nb6.setUserName("sarah");
        dao1.addBlog(nb6);
        Blog nb7 = new Blog();
        nb7.setTitle("Test Blog Seven");
        nb7.setCategory("General");
        nb7.setBlogEntry("<h2>Blog Post 7</h2><p><strong>It's been a great month at Mike's Tires! Our new favorite brand is Firestone. You'll love 'em!</strong>"
                + "</p><p><strong>Here's Why:</strong></p><ul><li><strong>they have a boring name</strong></li><li><strong>they're great tires</strong>"
                + "</li><li><strong>they're cheap!!</strong></li></ul><h3></h3>");
        nb7.setStartDate("2016-03-01");
        nb7.setEndDate("2017-03-31");
        nb7.setApproved("Y");
        
        String[] ta4 = new String[2];
        ta4[0] = ("funny");
        ta4[1] = ("Firestone");
        nb7.setTags(ta4);
        nb7.setUserName("mike");
        dao1.addBlog(nb7);
        Blog nb8 = new Blog();
        nb8.setTitle("Test Blog Eight");
        nb8.setCategory("Uncategorized");
        nb8.setBlogEntry("<h2>Blog Post 8</h2><p><strong>It's been a great month at Mike's Tires! Our new favorite brand is Hoosier. You'll love 'em!</strong>"
                + "</p><p><strong>Here's Why:</strong></p><ul><li><strong>they have an exciting name</strong></li><li><strong>they're great tires</strong>"
                + "</li><li><strong>they're cheap!!</strong></li></ul><h3></h3>");
        nb8.setStartDate("2016-03-01");
        nb8.setEndDate("2017-05-31");
        nb8.setApproved("Y");

        String[] ta5 = new String[2];
        ta5[0] = ("cheap");
        ta5[1] = ("Hoosier");
        nb8.setTags(ta5);
        nb8.setUserName("mike");
        dao1.addBlog(nb8);
        List<Blog> lb1 = dao1.getLatestBlogs();
        String lastFive = "Eight Seven Five Four Three";
        String thisNum;
        for (Blog b : lb1) {
            System.out.println(b.getBlogId()+" "+b.getTitle());
            thisNum = b.getTitle().substring(10);
            assertTrue(lastFive.contains(thisNum));
        }
    }

    /**
     * Test of addStaticPage method, of class DaoInMemImpl.
     */
    @Test
    public void testDAddStaticPage() {
        System.out.println("addStaticPage");
        StaticPage sb1 = new StaticPage();
        sb1.setPageName("Newsletter");
        sb1.setPageContent("<h2>This is a new Article !!</h2><p>Here&#39;s a new Ordered List:"
                + "</p><ol><li>line 1</li><li>line2</li><li>line3</li></ol><p>Here&#39;s an image:"
                + "</p><p><img alt='x' src='http://www.jimfalk.com/resrc/media/image/152190/tires.jpg' " 
                + "style='height:200px; width:200px' /></p><h1 style='font-style: italic;'>&nbsp;"
                + "</h1><h1 style='font-style: italic;'><em>And now some BIG TEXT</em></h1>");
        StaticPage sb2 = dao1.addStaticPage(sb1);
        assertEquals("Newsletter",sb2.getPageName());
    }

    /**
     * Test of getBlogsByCategory method, of class DaoInMemImpl.
     */
    @Test
    public void testEGetBlogsByCategory() {
        System.out.println("getBlogsByCategory");
        String category = "Uncategorized";
        List<Blog> result = dao1.getBlogsByCategory(category);
        String uncat = "Six Eight";
        // titles for Uncategorized should be "Test Blog Six" and Eight
        String thisNum;
        for (Blog b : result) {
            thisNum = b.getTitle().substring(10);
            System.out.println(b.getTitle());
            assertTrue(uncat.contains(thisNum));
        }
    }
    
    /**
     * Test of fillArchive method
     */
    @Test
    public void testFFillArchive() {
        System.out.println("getFillArchive");
        List<String> result = dao1.fillArchive();
        for (String s : result) {
             System.out.println(s);       
        }
        assertEquals(3,result.size());
    }
    
    /**
     * Test of getBlogsByMonth method
     */
    @Test
    public void testGGetBlogsByMonth() {
        System.out.println("getBlogsByMonth");
        String monthYear = "March 2016";
        List<Blog> result = dao1.getBlogsByMonth(monthYear);
        for (Blog b : result) {
             System.out.println(b.getTitle());       
        }
        assertEquals(3,result.size());
    }
    
    /**
     * Test of getTagList method
     */
    @Test
    public void testHGetTagList() {
        System.out.println("getTagList");
        List<TagList> result = dao1.getTagList();
        for (TagList t : result) {
            System.out.println(t.getTagName()+"--"+t.getTagId()+"--"+t.getTagCount());
            if (t.getTagName().equals("funny")) {
                assertEquals(2,t.getTagCount());
            }
        }
    }
    
    /**
     * Test of searchBlogs method
     */
    @Test
    public void testISearchBlogs() {
        System.out.println("searchBlogs");
        Tag t = dao1.getTagByName("funny");
        List<Blog> lb = dao1.searchBlogs(t.getTagId());
        for (Blog b : lb) {
            System.out.println(b.getTitle());
        }
        assertEquals(2, lb.size());
    }
    
    /**
     * Test of needsApproval method
     */
    @Test
    public void testJNeedsApproval() {
        System.out.println("getNeedsApproval");
        List<Blog> result = dao1.needsApproval();
        for (Blog b : result) {
            System.out.println(b.getTitle());
        }
        assertEquals(1, result.size());
    }
    
    /**
     * Test of needsApproval method
     */
    @Test
    public void testKFindBlogsByDate() {
        System.out.println("findBlogsByDate");
        String startDate1 = "2016-03-01";
        String startDate2 = "2016-03-15";
        List<Blog> result = dao1.findBlogsByDate(startDate1, startDate2);
        assertEquals(4, result.size());
    }

}
