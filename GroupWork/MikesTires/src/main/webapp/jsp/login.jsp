<%-- 
    Author     : Malos, Smallidge, & Kasel
--%>

<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
        <link rel="shortcut icon" href="${pageContext.request.contextPath}/img/icon.png">
        <link href="css/mikesTires.css" rel="stylesheet" type="text/css">
        <title>Mikes Tires Blog</title>
    </head>
    <body>
        <div id ="tires" class="container-fluid">
            <br>
            <h1>Sign in to Mike's Tires Blog Admin</h1>
            <!-- #1 - If login_error == 1 then there was a failed login attempt -->
            <!-- so display an error message -->
            <br><br>
            <!-- #2 - Post to Spring security to check our authentication -->
            <div id="size" class="col-sm-12">
                <form method="post" class="signin" action="j_spring_security_check">
                    <fieldset>
                        <table cellspacing="0">
                            <tr>
                                <th>
                                    <label for="username">Username  </label>
                                </th>
                                <td>
                                    <input style="margin-left: 15px" id="username_or_email" name="j_username" type="text" />
                                </td>
                            </tr>
                            <tr><td><br></td></tr>
                            <tr>
                                <th><label for="password">Password</label></th>
                                <!-- #2b - must be j_password for Spring -->
                                <td><input style="margin-left: 15px" id="password" name="j_password"
                                           type="password" />
                                </td>
                            </tr>
                            <tr><td><br></td></tr>
                            <tr>
                                <th></th>
                                <td>
                                    <input id="submit" name="commit" type="submit" value="Sign In" />
                                </td>
                            </tr>
                        </table>
                        <c:if test="${param.login_error == 1}">
                            <h1>Sorry, Wrong ID or Password!</h1>
                        </c:if>
                    </fieldset>
                </form>
            </div>
            <br><br><br><br><br><br><br><br>
            <div class="col-sm-12">
                <footer>
                    <img style="border-radius: 15px; height: 100px; width: 100px" 
                         src="http://www.fastcoolcars.com/images/wallpaper49/exotic-car-54.jpg"> 
                    <p>powered by Paths of Glory. Est. 2016</p>
                </footer>
            </div>
        </div>
        <script src="${pageContext.request.contextPath}/js/jquery-1.11.1.min.js"></script>
    </body>
</html>