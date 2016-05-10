<%-- 
    Document   : editPost
    Created on : Apr 21, 2016, 3:13:25 PM
    Author     : Malos, Smallidge, & Kasel
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Edit Posts</title>
        <link rel="shortcut icon" href="${pageContext.request.contextPath}/img/icon.png">
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
        <link href="css/mikesTires.css" rel="stylesheet" type="text/css">
        <script src="//cdn.tinymce.com/4/tinymce.min.js"></script> 
        <script>tinymce.init({selector: 'textarea', remove_linebreaks: true});</script>
    </head>
    <body>
        <div id ="tires" class="container-fluid">
            <h2>Edit Posts Page for Mike's Tires</h2>
            <div class="navbar">
                <ul class="nav nav-pills">
                    <li role="presentation"><a href="${pageContext.request.contextPath}/adminHome">
                            <span class="glyphicon glyphicon-home"></span> Home</a>
                    </li>
                    <li role="presentation">
                        <a href="${pageContext.request.contextPath}/newBlogPostForm">
                            <span class="glyphicon glyphicon-plus"></span> New Blog Post Form</a>
                    </li>
                    <li role="presentation">
                        <a href="${pageContext.request.contextPath}/newArticle">
                            <span class="glyphicon glyphicon-plus"></span> New Article</a>
                    </li>
                    <li role="presentation">
                        <a href="${pageContext.request.contextPath}/approvePosts">
                            <span class="glyphicon glyphicon-thumbs-up"></span> Approve Posts</a>
                    </li>
                    <li role="presentation" class="active">
                        <a href="${pageContext.request.contextPath}/editPost">
                            <span class="glyphicon glyphicon-pencil"></span> Edit Posts</a>
                    </li>
                    <li role="presentation">
                        <a href="${pageContext.request.contextPath}/displayUserList">
                            <span class="glyphicon glyphicon-user"></span> Display Users</a>
                    </li>
                    <li role="presentation">
                        <a href="${pageContext.request.contextPath}/j_spring_security_logout">
                            <span class="glyphicon glyphicon-log-out"></span> Log Out</a>
                    </li>
                </ul>
            </div> <!-- End Nav Bar -->
            <div class="col-sm-6" class="form-group"> 
                <h5 style="padding-bottom: 5px; text-align: left">Search for blogs between
                    <input id="blogStart" style="width: 133px" type="date" placeholder="Enter Start Date" name="blogStart"> 
                    and
                    <input id="blogEnd" style="width: 133px" type="date" placeholder="Enter End Date" name="blogEnd"></h5> 
                <input id="submitDates" type="submit" value="Retrieve Blogs"></input>                   
                <div class="form-group">
                    <div style ="text-align: left">
                        <table id="blogTable" class="table table-hover">
                            <tbody id="contentBlogs"></tbody>  <!-- list out blogs to choose from-->
                        </table> <br>
                    </div>                          
                </div>              
            </div>
            <form id="loadForm" class= "form-horizontal" role="form">
                <div class="col-sm-6">
                    <textarea id="blogData" name="test"></textarea> 
                    <div class="row"><br>
                        <div class="col-md-12" style="text-align: center; font-weight: 800; padding-bottom: 8px">Enter Corrected Information</div>              
                    </div>
                    <div class="row">
                        <div class="col-md-6" style="padding-left: 10px">New Blog Title:</div>
                        <div><input class="col-md-6" id="correct-title" style="width: 230px" type="text" placeholder="Enter Blog Title" name="correct-title"></div>
                    </div>
                    <br>
                    <div class="row">
                        <div class="col-md-6" style="padding-left: 10px">New Start Date:</div>
                        <div><input class="col-md-6" id="correct-startDate" style="width: 230px" type="date" placeholder="Enter Start Date" name="correct-startDate"></div>
                    </div>
                    <br>
                    <div class="row">
                        <div class="col-md-6" style="padding-left: 10px">New End Date:</div>
                        <div><input class="col-md-4" id="correct-endDate" style="width: 230px" type="date" placeholder="Enter End Date" name="correct-endDate"></div>
                    </div>	
                    <br>
                    <div class="row">
                        <div class="col-md-6" style="padding-left: 10px">New Tag:</div>
                        <div><input class="col-md-4" id="correct-tags" style="width: 230px" type="text" placeholder="Enter New Tag" name="correct-tags"></div>
                    </div>	
                    <br>
                    <div class="row">
                        <div class="col-md-6" style="padding-left: 10px">New Category:</div>
                        <div class="col-md-4">
                            <select style="border: 1px solid #0000cd;" id="correct-category1">
                                <option class="col-md-2" value="Uncategorized" >Uncategorized</option>
                                <option class="col-md-2" value="Promotions" >Promotions</option>
                                <option class="col-md-2" value="General">General</option>
                            </select>
                        </div>
                    </div>	
                    <br>
                    <input type="hidden" id="blog-id">
                    <input id="Edit-Post" type="submit" value="Edit-Post"></input>  
                </div>
            </form>
            <div id="validation-errors" style="color: red"></div>
            <div class="col-sm-12">
                <table id="returnChangeMade">
                    <tbody id="changeMade"></tbody> <!-- success message-->
                </table>
            </div>
            <div class="col-sm-12">
                <footer>
                    <img style="border-radius: 15px; height: 100px; width: 100px" 
                         src="http://www.fastcoolcars.com/images/wallpaper49/exotic-car-54.jpg">                    
                    <p>powered by Paths of Glory. Est. 2016</p>
                </footer>      
            </div>
        </div>
        <script src="${pageContext.request.contextPath}/js/jquery-1.11.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/editPostPage.js"></script>
    </body>
</html>