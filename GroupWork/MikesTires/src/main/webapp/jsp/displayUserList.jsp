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
        <title>Users</title>
        <link rel="shortcut icon" href="${pageContext.request.contextPath}/img/icon.png">    
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
        <link href="css/mikesTires.css" rel="stylesheet" type="text/css">
    </head>
    <body>
        <div id="tires" class="container-fluid">
            <h2>Mike's Tire's Blog Site User List</h2>
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
                    <li role="presentation" >
                        <a href="${pageContext.request.contextPath}/approvePosts">
                            <span class="glyphicon glyphicon-thumbs-up"></span> Approve Posts</a>
                    </li>
                    <li role="presentation">
                        <a href="${pageContext.request.contextPath}/editPost">
                            <span class="glyphicon glyphicon-edit"></span> Edit Posts</a>
                    </li>
                    <li role="presentation" class="active">
                        <a href="${pageContext.request.contextPath}/displayUserList">
                            <span class="glyphicon glyphicon-user"></span> Display Users</a>
                    </li>
                    <li role="presentation">
                        <a href="${pageContext.request.contextPath}/j_spring_security_logout">
                            <span class="glyphicon glyphicon-log-out"></span> Log Out</a>
                    </li>
                </ul>
            </div> <!-- End Nav Bar -->
            <div id="size">
                <hr/>
                <a href="displayUserForm">
                    <span class="glyphicon glyphicon-plus"></span> Add a User</a><br><br>

                <c:forEach var="user" items="${users}">
                    <c:set var="isAdmin" value="false"/>
                    <c:forEach var="authority" items="${user.authorities}">
                        <c:if test="${authority == 'ROLE_ADMIN'}">
                            <c:set var="isAdmin" value="true"/>
                        </c:if>
                    </c:forEach>
                    <c:out value="${user.username}"/> 
                    <c:if test="${isAdmin == 'false'}">                 
                        | <a href="deleteUser?username=${user.username}">
                            <span class="glyphicon glyphicon-trash"></span> Delete</a>
                        </c:if>
                    <br/><br/>
                </c:forEach>
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
    </body>
</html>