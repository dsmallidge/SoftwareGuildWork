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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public class DaoDataBaseImpl implements Dao {

    private static final String SQL_INSERT_BLOG
            = "insert into BlogPost (Approved, BlogEntry, BlogTitle, Category, EndDate, StartDate, UserName)"
            + "values (?, ?, ?, ?, ?, ?, ?)";

    private static final String SQL_DELETE_BLOG
            = "delete from BlogPost where BlogId = ?";

    public static final String SQL_SELECT_BLOG
            = "select * from BlogPost where BlogId = ?";

    public static final String SQL_SELECT_STATICPAGE
            = "select * from StaticPage";

    public static final String SQL_SELECT_STATICPAGEBYID
            = "select * from StaticPage where PageId = ?";

    public static final String SQL_TAG_LIST
            = "select a.Tag, a.TagId, count(a.Tag) as Count "
            + "from Tag a, BlogTag b, BlogPost c "
            + "where a.TagId = b.TagId "
            + " and b.BlogId = c.BlogId "
            + " and c.EndDate >= curdate() "
            + " and c.approved = 'Y' "
            + "group by a.Tag, a.TagId "
            + "order by a.Tag";

    public static final String SQL_DELETE_BLOGTAGS
            = "delete from BlogTag "
            + "where BlogTag.BlogId = ?";

    public static final String SQL_SELECT_BLOGBYTAG
            = "select b.* from BlogPost b, BlogTag bt "
            + "where b.BlogId = bt.BlogId and b.Approved = 'Y'"
            + "and TagId = ? "
            + "and EndDate >= curdate()";

    public static final String SQL_SELECT_BLOGBYCATEGORY
            = "select * from BlogPost "
            + "where Approved = 'Y' and Category = ? "
            + "and EndDate >= curdate()";

    private static final String SQL_UPDATE_BLOG
            = "update BlogPost set BlogEntry = ?, BlogTitle = ?, "
            + "Category = ?, EndDate = ?, StartDate = ? "
            + "where  BlogId =  ? ";

    private static final String SQL_UPDATE_APPROVAL
            = "Update BlogPost set Approved = 'Y' where BlogId = ?";

    private static final String SQL_INSERT_TAG
            = "insert into Tag (Tag)"
            + "values (?)";

    private static final String SQL_SELECT_TAGNAMES
            = "select t.Tag from Tag t right join BlogTag bt on bt.TagId = t.TagId where bt.BlogId = ?";

    private static final String SQL_INSERT_STATICPAGE
            = "insert into StaticPage (PageContent, PageName)"
            + "values (?, ?)";
    
    private static final String SQL_SELECT_BLOGSBYDATE
            = "select * from BlogPost "
            + "where StartDate >= ? and StartDate <= ?";

    private static final String SQL_INSERT_BLOGTAG
            = "insert into BlogTag (BlogId, TagId) values (?, ?)";

    private static final String SQL_SELECT_TAG
            = "select * from Tag where Tag = ?";

    private static final String SQL_SELECT_LATESTBLOGS
            = "select * from BlogPost where Approved = 'Y' "
            + "and EndDate >= curdate() "
            + "order by BlogId DESC limit 5";

    private static final String SQL_SELECT_BLOGFROMARCHIVE
            = "select * from BlogPost "
            + "Where ? = concat(monthname(cast(StartDate as DATETIME)),' ',"
            + "year(cast(StartDate as DATETIME))) "
            + "and EndDate >= curdate() "
            + "and Approved = 'Y' "
            + "Order by 1 desc";

    private static final String SQL_SELECT_ARCHIVE
            = "Select distinct concat(monthname(cast(StartDate as DATETIME)),' ',"
            + "year(cast(StartDate as DATETIME))) as month_year from "
            + "(select StartDate from BlogPost where EndDate >= curdate() "
            + "and Approved = 'Y' order by 1 desc) a";

    private static final String SQL_SELECT_NEEDSAPPROVAL
            = "select * from BlogPost where (Approved != 'y') and (Approved != 'Y')";

    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public Blog addBlog(Blog blog) {
        if (blog.getEndDate().equalsIgnoreCase("")) {
            blog.setEndDate("2099-01-01");
        }
        
        jdbcTemplate.update(SQL_INSERT_BLOG,
                blog.getApproved(),
                blog.getBlogEntry(),
                blog.getTitle(),
                blog.getCategory(),
                blog.getEndDate(),
                blog.getStartDate(),
                blog.getUserName());
        blog.setBlogId(jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class));
        for (String tagName : blog.getTags()) {
            Tag temp = getTagByName(tagName);
            if (temp == null) {
                temp = addTag(tagName);
            }
            jdbcTemplate.update(SQL_INSERT_BLOGTAG,
                    blog.getBlogId(),
                    temp.getTagId());
        }
        return blog;
    }

    @Override
    public void removeBlog(int blogId) {
        jdbcTemplate.update(SQL_DELETE_BLOG, blogId);
    }
    
    public List<Blog> findBlogsByDate(String start, String end) {
        return jdbcTemplate.query(SQL_SELECT_BLOGSBYDATE, new BlogMapper(), start, end);
    }

    @Override
    public void updateBlog(Blog blog) {
        if (blog.getEndDate().equalsIgnoreCase("")) {
            blog.setEndDate("2099-01-01");
        }
        
        jdbcTemplate.update(SQL_DELETE_BLOGTAGS, blog.getBlogId());
        jdbcTemplate.update(SQL_UPDATE_BLOG,
                blog.getBlogEntry(),
                blog.getTitle(),
                blog.getCategory(),
                blog.getEndDate(),
                blog.getStartDate(),
                blog.getBlogId());
        for (String tagName : blog.getTags()) {
            Tag temp = getTagByName(tagName);
            if (temp == null) {
                temp = addTag(tagName);
                jdbcTemplate.update(SQL_INSERT_BLOGTAG,
                        blog.getBlogId(),
                        temp.getTagId());
            } else {
                jdbcTemplate.update(SQL_INSERT_BLOGTAG,
                        blog.getBlogId(),
                        temp.getTagId());
            }
        }
    }

    @Override
    public Blog getBlogById(int blogId) {
        try {
            Blog temp = jdbcTemplate.queryForObject(SQL_SELECT_BLOG, new BlogMapper(), blogId);
            temp.setTags(tagNames(blogId));
            return temp;

        } catch (EmptyResultDataAccessException ex) {

            return null;
        }
    }

    public List<Blog> needsApproval() {

        return jdbcTemplate.query(SQL_SELECT_NEEDSAPPROVAL, new BlogMapper());

    }

    public void updateApproval(int blogId) {
        jdbcTemplate.update(SQL_UPDATE_APPROVAL, blogId);
    }

    @Override
    public List<Blog> searchBlogs(int tagId) {
        List<Blog> temp = jdbcTemplate.query(SQL_SELECT_BLOGBYTAG, new BlogMapper(), tagId);
        for (Blog b : temp) {
            b.setTags(tagNames(b.getBlogId()));
        }
        return temp;
    }

    @Override
    public List<TagList> getTagList() {

        return jdbcTemplate.query(SQL_TAG_LIST, new TagListMapper());
    }

    @Override
    public Tag addTag(String tagName) {
        jdbcTemplate.update(SQL_INSERT_TAG,
                tagName);
        Tag t = getTagByName(tagName);
        return t;
    }

    @Override
    public StaticPage addStaticPage(StaticPage page) {
        jdbcTemplate.update(SQL_INSERT_STATICPAGE,
                page.getPageContent(),
                page.getPageName());
        page.setPageId(jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class));
        return page;
    }

    @Override
    public List<Blog> getLatestBlogs() {

        List<Blog> temp = jdbcTemplate.query(SQL_SELECT_LATESTBLOGS, new BlogMapper());
        for (Blog b : temp) {
            b.setTags(tagNames(b.getBlogId()));
        }
        return temp;
    }

    @Override
    public List<Blog> getBlogsByCategory(String category) {

        List<Blog> temp = jdbcTemplate.query(SQL_SELECT_BLOGBYCATEGORY, new BlogMapper(), category);
        for (Blog b : temp) {
            b.setTags(tagNames(b.getBlogId()));
        }
        return temp;

    }

    @Override
    public List<Blog> getBlogsByMonth(String monthYear) {

        List<Blog> temp = jdbcTemplate.query(SQL_SELECT_BLOGFROMARCHIVE, new BlogMapper(), monthYear);
        for (Blog b : temp) {
            b.setTags(tagNames(b.getBlogId()));
        }
        return temp;
    }

    @Override
    public List<String> fillArchive() {

        List<String> temp = (List<String>) jdbcTemplate.queryForList(SQL_SELECT_ARCHIVE, String.class);
        return temp;
    }

    @Override
    public List<StaticPage> getStaticPages() {

        return jdbcTemplate.query(SQL_SELECT_STATICPAGE, new StaticPageMapper());

    }

    @Override
    public StaticPage getStaticPageById(int pageId) {

        return jdbcTemplate.queryForObject(SQL_SELECT_STATICPAGEBYID, new StaticPageMapper(), pageId);

    }

    @Override
    public String[] tagNames(int blogId) {
        List<String> temp = (List<String>) jdbcTemplate.queryForList(SQL_SELECT_TAGNAMES, String.class, blogId);
        String[] temp2 = temp.toArray(new String[0]);
        return temp2;

    }

    private static final class BlogMapper implements
            RowMapper<Blog> {

        @Override
        public Blog mapRow(ResultSet rs, int rowNum) throws SQLException {
            Blog blog = new Blog();
            blog.setApproved(rs.getString("Approved"));
            blog.setBlogEntry(rs.getString("BlogEntry"));
            blog.setBlogId(rs.getInt("BlogId"));
            blog.setTitle(rs.getString("BlogTitle"));
            blog.setCategory(rs.getString("Category"));
            blog.setEndDate(rs.getString("EndDate"));
            blog.setStartDate(rs.getString("StartDate"));
            blog.setUserName(rs.getString("UserName"));
            return blog;
        }
    }

    private static final class TagMapper implements
            RowMapper<Tag> {

        public Tag mapRow(ResultSet rs, int rowNum) throws SQLException {
            Tag tag = new Tag();
            tag.setName(rs.getString("Tag"));
            tag.setTagId(rs.getInt("TagId"));
            return tag;
        }
    }

    private static final class StaticPageMapper implements
            RowMapper<StaticPage> {

        public StaticPage mapRow(ResultSet rs, int rowNum) throws SQLException {
            StaticPage page = new StaticPage();
            page.setPageId(rs.getInt("PageId"));
            page.setPageName(rs.getString("PageName"));
            page.setPageContent(rs.getString("PageContent"));
            return page;
        }
    }

    private static final class TagListMapper implements
            RowMapper<TagList> {

        public TagList mapRow(ResultSet rs, int rowNum) throws SQLException {
            TagList tl = new TagList();
            tl.setTagName(rs.getString("Tag"));
            tl.setTagId(rs.getInt("TagId"));
            tl.setTagCount(rs.getInt("Count"));
            return tl;
        }
    }

    public Tag getTagByName(String name) {
        try {
            return jdbcTemplate.queryForObject(SQL_SELECT_TAG,
                    new TagMapper(), name);
        } catch (EmptyResultDataAccessException ex) {

            return null;
        }

    }

}
