<%-- 
    Document   : newArticle
    Created on : Apr 22, 2016, 9:29:25 AM
    Author     : Smallidge, Malos & Kasel
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>New Article Form</title>
        <link rel="shortcut icon" href="${pageContext.request.contextPath}/img/icon.png">
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
        <link href="css/mikesTires.css" rel="stylesheet" type="text/css">
        <script src="//cdn.ckeditor.com/4.5.8/standard/ckeditor.js"></script>
    </head>
    <body>
        <div id ="tires" class="container-fluid">
            <h2>Welcome to the New Article Form for Mike's Tires</h2>
            <div class="navbar">
                <ul class="nav nav-pills">
                    <li role="presentation"><a href="${pageContext.request.contextPath}/adminHome">
                            <span class="glyphicon glyphicon-home"></span> Home</a>
                    </li>
                    <li role="presentation">
                        <a href="${pageContext.request.contextPath}/newBlogPostForm">
                            <span class="glyphicon glyphicon-plus"></span> New Blog Post Form</a>
                    </li>
                    <li role="presentation" class="active">
                        <a href="${pageContext.request.contextPath}/newArticle">
                            <span class="glyphicon glyphicon-plus"></span> New Article</a>
                    </li>
                    <li role="presentation">
                        <a href="${pageContext.request.contextPath}/approvePosts">
                            <span class="glyphicon glyphicon-thumbs-up"></span> Approve Posts</a>
                    </li>
                    <li role="presentation">
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
            <form style="padding-bottom: 20px" method="POST" action="https://httpbin.org/post">
                <textarea name="editor1" id="articleText">
                </textarea>
                <script>
                    CKEDITOR.replace('editor1');
                </script>
                <br>
                <div class="row">
                    <div id="size" class="col-sm-5" style="padding-left: 10px">
                        <p>Please enter a name for this Article:</p>
                    </div>
                    <div class="col-sm-5">
                        <input id="articleName" style="width: 300px" type="text" placeholder="Enter Page Name" name="newArticle">
                    </div>
                </div>    
                <br>
                <div >
                    <input id="submit" type="submit" value="Submit"></input>
                </div>
            </form> 
            <div id="validation-errors" style="color: red"></div>
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
            <script src="${pageContext.request.contextPath}/js/newArticle.js"></script>
    </body>
</html>