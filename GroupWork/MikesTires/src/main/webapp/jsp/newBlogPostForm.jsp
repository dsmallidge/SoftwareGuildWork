<%-- 
    Document   : newBlogPostForm
    Created on : Apr 20, 2016, 1:25:37 PM
    Authors    : Smallidge, Malos & Kasel
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>New Blog Form</title>
        <link rel="shortcut icon" href="${pageContext.request.contextPath}/img/icon.png">
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
        <link href="css/mikesTires.css" rel="stylesheet" type="text/css">
        <script src="//cdn.tinymce.com/4/tinymce.min.js"></script> 
        <script>tinymce.init({selector: 'textarea', remove_linebreaks: true});</script>
    </head>
    <body>
        <sec:authentication var="userName" property="principal.Username"/>
        <script type="text/javascript">var userName ="<c:out value='${userName}'/>"</script>
        <div id ="tires" class="container-fluid">
            <h2>Welcome to the New Blog Post Form for Mike's Tires</h2>
            <div class="navbar">
                <ul class="nav nav-pills">
                    <li role="presentation"><a href="${pageContext.request.contextPath}/adminHome">
                            <span class="glyphicon glyphicon-home"></span> Home</a>
                    </li>
                    <li role="presentation" class="active">
                        <a href="${pageContext.request.contextPath}/newBlogPostForm">
                            <span class="glyphicon glyphicon-plus"></span> New Blog Post Form</a>
                    </li>
                    <sec:authorize access="hasRole('ROLE_ADMIN')">
                        <li role="presentation">
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
                    </sec:authorize>
                    <li role="presentation">
                        <a href="${pageContext.request.contextPath}/j_spring_security_logout">
                            <span class="glyphicon glyphicon-log-out"></span> Log Out</a>
                    </li>
                </ul>
            </div> <!-- End Nav Bar -->
            <div> 
                <form method="POST" >
                    <h3 style="padding-bottom: 5px">New Blog Entry</h3>
                    <textarea name="test" id="blogEntry"></textarea>
                    <br>
                    <div class="row">
                        <div class="col-sm-6" style="padding-left: 10px">Blog Title:</div><div><input class="col-sm-10" id="blogTitle" style="width: 220px" type="text" placeholder="Enter Blog Title" name="blogTitle"></div>
                    </div>
                    <br>
                    <div class="row">
                        <div class="col-sm-6" style="padding-left: 10px">Start Date:</div><div><input type="date" class="col-sm-10" id="startDate" style="width: 220px" type="text" placeholder="Enter Start Date" name="startDate"></div>
                    </div>
                    <br>
                    <div class="row">
                        <div class="col-sm-6" style="padding-left: 10px">End Date:</div><div><input type="date" class="col-sm-10" id="endDate" style="width: 220px" type="text" placeholder="Enter End Date" name="endDate"></div>
                    </div>	
                    <br>
                    <div class="row">
                        <div class="col-sm-6" style="padding-left: 10px">Create Tag:</div>
                        <div><input class="col-sm-5" id="newTag" style="width: 220px" type="text" placeholder="Enter New Tag" name="newTag"></div>
                    </div>	
                    <br>
                    <div class="row">
                        <div class="col-sm-6" style="padding-left: 10px">Category:</div>
                            <div  class="col-sm-2">
                                <select style="border: 1px solid #0000cd;" class="selectpicker" id="dropDownMenu1">
                                    <option value="Uncategorized">Uncategorized</option>
                                    <option value="Promotions">Promotions</option>
                                    <option value="General">General</option>
                                </select>
                            </div>
                        <div class="col-sm-8"></div>
                    </div>	
                    <br>
                    <input id="submit" type="submit" value="Commit Post To Blog"></input>
                </form>
                <div id="validation-errors" style="color: red"></div>
            </div>
            <div>
                <footer>
                    <img style="border-radius: 15px; height: 100px; width: 100px" 
                         src="http://www.fastcoolcars.com/images/wallpaper49/exotic-car-54.jpg"> 
                    <p>powered by Paths of Glory. Est. 2016</p>
                </footer>      
            </div>
        </div>
        <script src="${pageContext.request.contextPath}/js/jquery-1.11.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/newBlogPostForm.js"></script>
    </body>
</html>