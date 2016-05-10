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
        <title>Mike's Tires Public View</title>
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
        <link href="css/mikesTires.css" rel="stylesheet" type="text/css">
        <link rel="shortcut icon" href="${pageContext.request.contextPath}/img/icon.png">
    </head>
    <body>
        <div id ="tires" class="container-fluid">
            <h2>Welcome to the Public View Home Page of Mike's Tires</h2> <!-- no nav bar as this is home page-->
            <div class="row">
                <div class="col-sm-4">
                    <div style ="text-align: left">
                        <h4>Recent Posts</h4>
                        <table id="recentTable" class="table table-hover">
                            <tbody id="contentRecents"></tbody>  <!-- list out last 3 blog titles-->
                        </table>   
                    </div>
                    <div style ="text-align: left">
                        <h4>Articles</h4>
                        <table id="articleTable" class="table table-hover">
                            <tbody id="contentArticles"></tbody>  <!-- list out last 3 article titles-->
                        </table>   
                    </div>
                    <div style ="text-align: left">
                        <h4>Tags</h4>
                        <table id="tagTable" class="table table-hover">
                            <tbody id="contentTags"></tbody>  <!-- list out tags-->
                        </table>   
                    </div>
                    <div style ="text-align: left">
                        <h4>Categories</h4>
                        <table id="categoryTable" class="table table-hover">
                            <tbody id="contentCategories"></tbody> 
                        </table>  
                    </div>
                    <div style="text-align:left">
                        <h4>Archives</h4>
                        <select style="width: 150px" id="archiveMonth">
                            <option value="">Select Month</option>
                        </select>
                    </div>
                </div> <!-- End col div -->
                <div class="col-sm-8">
                    <h4 id="displayTitle"></h4>
                    <div class="form-group">
                        <div class="col-md-2"></div>  <!-- space out the display a bit 2-8-2-->
                        <div style ="text-align: left" class="col-md-8">
                            <table id="blogTable" class="table table-hover">
                                <tbody id="contentBlogs"></tbody>  <!-- list out blogs and Id's-->
                            </table>   
                        </div>
                        <div class="col-md-2"></div>
                    </div>           
                </div> <!-- End col div -->
            </div> <!-- End row div -->
            <div class="col-sm-12">
                <footer>
                    <img style="border-radius: 15px; height: 100px; width: 100px" 
                         src="http://www.fastcoolcars.com/images/wallpaper49/exotic-car-54.jpg"> 
                    <p>powered by Paths of Glory. Est. 2016</p>
                </footer>
            </div>
        </div> <!-- end page div -->
        <script src="${pageContext.request.contextPath}/js/jquery-1.11.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/mikesTires.js"></script>
    </body>
</html>