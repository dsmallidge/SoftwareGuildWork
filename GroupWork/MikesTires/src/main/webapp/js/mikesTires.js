$(document).ready(function () {
    loadBlogs();
    loadRecents();
    loadArticles();
    loadTags();
    fillCategoryTable();
    loadArchiveMonths();
});

function loadBlogs() {
    clearBlogTable();
//    var bTable = $('#contentBlogs');
    $.ajax({
        url: "latestblogs"
    }).success(function (data, status) {
        fillBlogTable(data, status);
    });
}

function loadBlog(id) {
    clearBlogTable();
    $.ajax({
        type: 'GET',
        url: 'blog/' + id
    }).success(function (data, status) {
        fillBlogTableSingle(data, status);
    });
}

function fillBlogTableSingle(blog, status) {
    clearBlogTable();
    $('#displayTitle').empty();
    var dTitle = $('#displayTitle');
    dTitle.append('Blog Post');
    var bTable = $('#contentBlogs');   
    var i;
    var titlePlusEntry = "<h2>" + blog.title + "</h2>" + blog.blogEntry
                + "<h4>Tags: </h4>";

        for (i = 0; i < blog.tags.length; i++) {
            var titlePlusEntry = titlePlusEntry + "<a onClick = loadTagNameBlogs('" + blog.tags[i] + "')>" + blog.tags[i] + " </a>";
        }
        ;
        bTable.append($('<tr>')
                .append($('<td>').html(titlePlusEntry))
                );
}

function fillBlogTable(blogList, status) {
    // clear the previous list
    clearBlogTable();
    $('#displayTitle').empty();
    var dTitle = $('#displayTitle');
    dTitle.append('Blog Posts');
    var bTable = $('#contentBlogs');
    $.each(blogList, function (index, blog) {
        var titlePlusEntry = "<h2>" + blog.title + "</h2>" + blog.blogEntry
                + "<h4>Tags: </h4>";

        for (i = 0; i < blog.tags.length; i++) {
            var titlePlusEntry = titlePlusEntry + "<a onClick = loadTagNameBlogs('" + blog.tags[i] + "')>" + blog.tags[i] + " </a>";
        }
        ;
        bTable.append($('<tr>')
                .append($('<td>').html(titlePlusEntry))
                );

    }); // ends the 'each' function
}

function loadArticle(id) {
    clearBlogTable();
    $.ajax({
        type: 'GET',
        url: 'article/' + id
    }).success(function (data, status) {
        fillBlogTableArticle(data, status);
    });
}

function fillBlogTableArticle(staticPage, status) {
    clearBlogTable();
    $('#displayTitle').empty();
    var dTitle = $('#displayTitle');
    dTitle.append('Article');
    var bTable = $('#contentBlogs');
    var titlePlusEntry = "<h2>" + staticPage.pageName + "</h2>" + staticPage.pageContent;
    bTable.append($('<tr>')
            .append($('<td>').html(titlePlusEntry))
            );
}

function loadTagBlogs(id) {
    clearBlogTable();
    $.ajax({
        type: 'GET',
        url: 'search/' + id
    }).success(function (data, status) {
        fillBlogTable(data, status);
    });
}

function loadTagNameBlogs(name) {
    $.ajax({
        type: 'GET',
        url: 'searchbyname/' + name
    }).success(function (data, status) {
        fillBlogTable(data, status);
    });
}

// Clear all content rows from the summary table
function clearBlogTable() {
    $('#contentBlogs').empty();
}

function loadRecents() {
    clearRecentTable();
    //var rTable = $('#contentRecents');
    $.ajax({
        url: "latestblogs"
    }).success(function (data, status) {
        fillRecentTable(data, status);
    });
}

// Clear all content rows from the summary table
function clearRecentTable() {
    $('#contentRecents').empty();
}
function fillRecentTable(blogList, status) {
    // clear the previous list
    clearRecentTable();
    var rTable = $('#contentRecents');

    // render the new address data to the table
    $.each(blogList, function (index, blog) {
        rTable.append($('<tr>')
                .append($('<td>')
                        .append($('<a>')
                                .attr({
                                    'onClick': 'loadBlog(' + blog.blogId + ')'
                                })
                                .text(blog.title)
                                ) // ends the <a> tag
                        ) // ends the <td> tag for the blog
                );
    }); // ends the 'each' function
}

function loadArticles() {
    clearArticleTable();
    //var aTable = $('#contentArticles');
    $.ajax({
        url: "staticpages"
    }).success(function (data, status) {
        fillArticleTable(data, status);
    });
}

// Clear all content rows from the summary table
function clearArticleTable() {
    $('#contentArticles').empty();
}

function fillArticleTable(staticPageList, status) {
    clearArticleTable();
    var aTable = $('#contentArticles');

    $.each(staticPageList, function (index, staticPage) {
        aTable.append($('<tr>')
                .append($('<td>')
                        .append($('<a>')
                                .attr({
                                    'onClick': 'loadArticle(' + staticPage.pageId + ')'
                                })
                                .text(staticPage.pageName)
                                ) // ends the <a> tag
                        ) // ends the <td> tag for the blog
                );
    }); // ends the 'each' function
}

function loadTags() {
    clearTagTable();
    $.ajax({
        url: "taglist"
    }).success(function (data, status) {
        fillTagTable(data, status);
    });
}

function clearTagTable() {
    $('#contentTags').empty();
}

function fillTagTable(tagList, status) {
    clearTagTable();
    var tTable = $('#contentTags');
    tTable.append($('<tr>')
            .append($('<td>'),
                    $.each(tagList, function (index, tag) {
                        var thisFontSize = tag.tagCount * 7;
                        if (thisFontSize === 7) {
                            thisFontSize = 12;
                        }
                        tTable.append($('<a>')
                                .attr({'onClick': 'loadTagBlogs(' + tag.tagId + ')'})
                                .text(tag.tagName + "  ")
                                .css("fontSize", thisFontSize)
                                );
                    }) // ends the 'each' function
                    ));
}

function loadCategory(category) {
    clearBlogTable();
    $.ajax({
        type: 'GET',
        url: 'categories/' + category
    }).success(function (data, status) {
        fillBlogTable(data, status);
    });
}

function fillCategoryTable() {
    $('#contentCategories').empty();
    var cTable = $('#contentCategories');
    var catList = ["Promotions", "General", "Uncategorized"];
    $.each(catList, function (index, category) {
        cTable.append($('<tr>')
                .append($('<td>')
                        .append($('<a>')
                                .attr({
                                    'onClick': "loadCategory('" + category + "')"
                                })
                                .text(category)
                                ) // ends the <a> tag
                        ) // ends the <td> tag for the blog
                );
    }); // ends the 'each' function 
}

function loadArchiveMonths() {
    $.ajax({
        url: "archives"
    }).success(function (data, status) {
        fillArchiveMonths(data, status);
    });
}

function fillArchiveMonths(archiveList, status) {
    var $select = $('#archiveMonth');
    //$select.find('option').remove();
    $.each(archiveList, function (index, archiveMonth)
    {
        $select.append('<option value="' + archiveMonth + '">' + archiveMonth + '</option>');
    });
}

function getByMonthList(archiveMonth) {
    clearBlogTable();
    $.ajax({
        type: 'GET',
        url: 'archives/' + archiveMonth
    }).success(function (data, status) {
        fillBlogTable(data, status);
    });
}

$('#archiveMonth').change(function () {
    var text = $(this).val();
    var monthYear = text.split(" ");
    // change April 2016 to April2016
    var archiveMonth = monthYear[0] + monthYear[1];
    getByMonthList(archiveMonth);
});