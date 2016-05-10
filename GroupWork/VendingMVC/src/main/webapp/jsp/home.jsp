<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Home</title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">

        <!-- Custom styles for this template -->
        <!--<link href="${pageContext.request.contextPath}/css/starter-template.css" rel="stylesheet">-->

        <!-- SWC Icon -->
        <link rel="shortcut icon" href="${pageContext.request.contextPath}/img/icon.png">

    </head>

    <body>
        <div class="container">
            <center><h1>Vending Machine</h1>
                <h2>VendBot 2.0</h2></center>
            <hr/>

            <div class="row">
                <div class="col-md-6">
                    <h2>Vending Machine Options</h2>
                    <table id="iTable" class="table table-hover">
                        <tr>
                            <th width="40%">Item Name</th> <th width="30%">Cost</th>
                            <th width="15%"></th>
                            <th width="15%"></th>
                        </tr> 
                        <tbody id="content-rows"></tbody>
                    </table>
                </div> <!-- End col div -->           

                <div class="col-md-offset-1 col-md-5">
                    
                    
                    <form action="addmoney">
                        <label  id="balance-row"></label>
                        <div class="form-group" >     
                            <div >Insert Coin
                                <input type="number" name="money" required/>
                            </div></div>

                        <div class="form-group" >
                            <div><br>
                                <button type="submit"  class="btn btn-success btn-lg "><span class="glyphicon glyphicon-usd"></span>Add Money</button>
                            </div>
                        </div>

                    </form>
                    
                    <form action="cashOut">
                        <div class="form-group" >
                            <div><br>
                                <button type="button" class="btn btn-danger btn-lg"  data-toggle="modal" data-target="#cashOutModal" id="cashingout">Cash Out</button>
                            </div>
                        </div>
                    </form>
                    
                    <h3 id="message-row"></h3>
                </div>

            </div>
        </div>
        <div class="modal fade" id="purchaseModal" tabindex="-1" role="dialog" aria-labelledby="purchaseModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal">
                            <span aria-hidden="true">&times;</span>
                            <span class="sr-only">Close</span></button>
                        <h4 class="modal-title" id="purchaseModalLabel">Purchase Item</h4>
                    </div>
                    <div class="modal-body">
                        <h3 id="item-name"></h3>
                        <form class="form-horizontal" style="text-align: center" role="form" action="purchaseitem" method="POST">
                            <div class="form-group">

                                <h2 id="item-type"></h2>

                                <h3 id="item-cost"></h3>
                                <h4><span id="item-quantity"></span> in stock.</h4>
                            </div>
                            <button type="submit" id="purchase-button" class="btn-lg btn-success" data-dismiss="modal">
                               <span class="glyphicon glyphicon-ok"></span> Purchase
                            </button>
                            <button type="button" class="btn-lg btn-default"
                                    data-dismiss="modal">
                                <span class="glyphicon glyphicon-remove"></span> Cancel
                            </button>
                            <input type="hidden" id="purchase-item">
                        </form> 
                    </div>
                </div> 
            </div>
        </div>
        
        <div class="modal fade" id="cashOutModal" tabindex="-1" role="dialog" aria-labelledby="cashOutModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal">
                            <span aria-hidden="true">&times;</span>
                            <span class="sr-only">Close</span></button>
                        <h4 class="modal-title" id="cashOutModalLabel">Cashing Out</h4>
                    </div>
                    <div class="modal-body">
                        <h3 id="item-name"></h3>
                        <form class="form-horizontal" role="form" action="cashOut" method="POST" style="text-align: center">
                            <div class="form-group">
                                <h2>Your change is: </h2>
                                <h3><span id="changeQ"></span> Quarters</h3>
                                <h3><span id="changeD"></span> Dimes</h3>
                                <h3><span id="changeN"></span> Nickels</h3>
                                <h3><span id="changeP"></span> Pennies</h3>
                            </div>
                            <button type="button"  class="btn btn-success"
                                    data-dismiss="modal">
                                <span class="glyphicon glyphicon-remove"></span> Cancel
                            </button>
                            
                            <div ></div>
                            <input type="hidden" id="cash-out">
                        </form> 
                    </div>
                </div> 
            </div>
        </div>

        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-1.11.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/vending.js"></script>

    </body>
</html>