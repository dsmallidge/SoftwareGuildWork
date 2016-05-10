<%-- 
    Document   : approvePosts
    Created on : Apr 22, 2016, 10:21:58 AM
    Author     : Malos, Smallidge, & Kasel
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Approve Posts Form</title>
        <link rel="shortcut icon" href="${pageContext.request.contextPath}/img/icon.png">
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
        <link href="css/mikesTires.css" rel="stylesheet" type="text/css">     
    </head>
    <body>
        <div id="tires" class="container-fluid">
           <h2>Welcome to the Approve Posts Form for Mike's Tires</h2>
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
                    <li role="presentation" class="active">
                        <a href="${pageContext.request.contextPath}/approvePosts">
                            <span class="glyphicon glyphicon-thumbs-up"></span> Approve Posts</a>
                    </li>
                    <li role="presentation">
                        <a href="${pageContext.request.contextPath}/editPost">
                            <span class="glyphicon glyphicon-pencil"></span> Edit Posts</a>
                    </li>
                    <li role="presentation" >
                        <a href="${pageContext.request.contextPath}/displayUserList">
                            <span class="glyphicon glyphicon-user"></span> Display Users</a>
                    </li>
                    <li role="presentation">
                        <a href="${pageContext.request.contextPath}/j_spring_security_logout">
                            <span class="glyphicon glyphicon-log-out"></span> Log Out</a>
                    </li>
                </ul>
            </div> <!-- End Nav Bar -->
            <div class="container">
                <div class="row">
                    <div id="approve" class="col-md-6">
                        <table id="approvalTable" class="table table-hover">
                            <tr>
                                <th width="40%">Blog Title</th>
                                <th width="30%">Start Date</th>
                                <th width="15%"></th>
                                <th width="15%"></th>
                            </tr>
                            <tbody id="content-rows"></tbody>
                        </table>
                        <br><br>
                    </div>
                </div>
            </div>
            <div>
                <footer>
                    <img style="border-radius: 15px; height: 100px; width: 100px" 
                         src="http://www.fastcoolcars.com/images/wallpaper49/exotic-car-54.jpg"> 
                    <p>powered by Paths of Glory. Est. 2016</p>
                </footer>      
            </div>
            <script src="${pageContext.request.contextPath}/js/jquery-1.11.1.min.js"></script>
            <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
            <script src="${pageContext.request.contextPath}/js/approvepost.js"></script>
    </body>
    <div class="modal" id="approvalModal" tabindex="-1" role="dialog" aria-labelledby="approvalModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">
                        <span aria-hidden="true">&times;</span>
                        <span class="sr-only">Close</span>
                    </button>
                    <h4 class="modal-title" id="approveModalLabel">Approve Blog</h4>
                </div>
                <div class="modal-body">
                    <h3 id="blog-id"></h3>
                    <form>
                    <table class="table table-bordered">
                        <tr>
                            <th>Title</th>
                            <td id="blog-title"></td>
                        </tr>
                        <tr>
                            <th>Category</th>
                            <td id="blog-category"></td>
                        </tr>
                        <tr>
                            <th>Entry</th>
                            <td id="blog-blog-entry"></td>
                        </tr>
                        <tr>
                            <th>Start Date</th>
                            <td id="blog-start-date"></td>
                        </tr>
                        <tr>
                            <th>End Date</th>
                            <td id="blog-end-date"></td>
                        </tr>
                        <tr>
                            <th>Approved</th>
                            <td id="blog-approved"></td>
                        </tr>

                        <tr>
                            <th>Tags</th>
                            <td id="blog-tags"></td>
                        </tr>
                        <tr>
                            <th>User Name</th>
                            <td id="blog-user-name"></td>
                        </tr>
                        <div class="col-md-offset-4 col-md-8">
                            <button type="submit" id="approve-button" class="btn btn-default"
                                    data-dismiss="modal" data-blog-id = "blog-id">Approve Blog
                            </button>
                            <button type="button" class="btn btn-default"
                                    data-dismiss="modal">Cancel
                            </button>
                            <input type="hidden" id = "blog-id">
                        </div>          
                    </table>
                    </form>
                </div>
            </div>
        </div>
    </div>
</html>