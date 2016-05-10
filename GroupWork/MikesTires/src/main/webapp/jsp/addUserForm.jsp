<%-- 
    Author     : Malos, Smallidge, & Kasel
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Add Users</title>
        <link href="css/mikesTires.css" rel="stylesheet" type="text/css">
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
        <link rel="shortcut icon" href="${pageContext.request.contextPath}/img/icon.png">
    </head>
    <body>
        <div id="tires" class="container-fluid">
            <h1>Add User Form</h1>
            <div class="navbar">
                <ul class="nav nav-pills">
                    <li role="presentation"><a href="${pageContext.request.contextPath}/adminHome">
                            <span class="glyphicon glyphicon-home"></span> Home</a>
                    </li>
                    <li role="presentation">
                        <a href="${pageContext.request.contextPath}/newBlogPostForm">New Blog Post Form</a>
                    </li>
                    <li role="presentation">
                        <a href="${pageContext.request.contextPath}/newArticle">New Article</a>
                    </li>
                    <li role="presentation" >
                        <a href="${pageContext.request.contextPath}/approvePosts">Approve Posts</a>
                    </li>
                    <li role="presentation">
                        <a href="${pageContext.request.contextPath}/editPost">Edit Posts</a>
                    </li>
                    <li role="presentation" class="active">
                        <a href="${pageContext.request.contextPath}/displayUserList">Display Users</a>
                    </li>
                    <li role="presentation">
                        <a href="${pageContext.request.contextPath}/j_spring_security_logout">Log Out</a>
                    </li>
                </ul>
            </div> <!-- End Nav Bar -->
            <form method="POST" action="addUser">
                Username: <input type="text" name="username"/><br><br>
                Password:&nbsp; <input type="password" name="password"/><br><br>
                Admin User? <input type="checkbox" name="isAdmin" value="yes"/> <br><br>
                <input id="submit"type="submit" value="Add User"/><br>
            </form>
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
    </body>
</html>