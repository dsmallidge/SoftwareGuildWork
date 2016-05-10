<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Add New Address</title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
        <link rel="shortcut icon" href="${pageContext.request.contextPath}/img/icon.png">

    </head>
        <body>
        <div class="container">
            <h1 style="text-align: center">AddressBot 3.0</h1>
            <hr/>
            <div class="navbar">
                <ul class="nav nav-tabs">
                    <li role="presentation" ><a href="${pageContext.request.contextPath}/">Home</a></li>
                    <li role="presentation" class="active"><a href="${pageContext.request.contextPath}/search">Search</a></li>
                </ul>    
            </div>
            <div class="row"> 
                <div class="col-md-6">
                    <h2 style="text-align: center">Search Results</h2>

                    <%@include file="addressSummaryTableFragment.jsp" %>
                </div> <!-- End col div --> 
                <div class="col-md-6"> <h2 style="text-align: center">Search</h2>
                    <form class="form-horizontal" role="form"> <div class="form-group">
                            <label for="search-first-name" class="col-md-4 control-label"> First Name:
                            </label>
                            <div class="col-md-8">
                                <input type="text" class="form-control" id="search-first-name" placeholder="First Name"/>
                            </div> 
                        </div>
                        <div class="form-group">
                            <label for="search-last-name" class="col-md-4 control-label">
                                Last Name:
                            </label>
                            <div class="col-md-8">
                                <input type="text" class="form-control" id="search-last-name" placeholder="Last Name"/>
                            </div> 
                        </div>
                        <div class="form-group">
                            <label for="search-street" class="col-md-4 control-label">Street:</label> <div class="col-md-8">
                                <input type="text" class="form-control" id="search-street" placeholder="Street"/>
                            </div> 
                        </div>
                        <div class="form-group">
                            <label for="search-city" class="col-md-4 control-label">City</label> <div class="col-md-8">
                                <input type="text" class="form-control" id="search-city" placeholder="City"/>
                            </div> 
                        </div>
                        <div class="form-group">
                            <label for="search-state" class="col-md-4 control-label">State:</label> <div class="col-md-8">
                                <input type="text" class="form-control" id="search-state" placeholder="State"/>
                            </div> 
                        </div>
                        <div class="form-group">
                            <label for="add-zip" class="col-md-4 control-label">Zip:</label> <div class="col-md-8">
                                <input type="text" class="form-control" id="search-zip" placeholder="Zip"/>
                            </div> 
                        </div>
                        <div class="form-group">
                            <div class="col-md-offset-4 col-md-8">
                                <button type="submit" id="search-button" class="btn btn-default"> Search
                                </button> 
                            </div>
                        </div> 
                    </form>
                </div> <!-- End col div --> 
            </div> <!-- End row div -->
        </div>
        <%@include file="modals.jsp" %>
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-1.11.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/addressBook.js"></script>

    </body>
</html>