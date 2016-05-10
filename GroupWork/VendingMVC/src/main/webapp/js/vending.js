/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function () {
    loadItems();
    loadBalance();
    loadMessage();

});

function loadItems() {
    clearContent();
    var iTable = $('#content-rows');
    $.ajax({
        type: 'GET',
        url: "item/"
    }).success(function (data, status) {
        $.each(data, function (index, item) {
            iTable.append($('<tr>')
                    .append($('<td>')
                            .text(item.name)

                            ) 
                    .append($('<td>').text(item.cost))
                    .append($('<td>')
                            .append($('<a>')
                                    .attr({
                                        'data-item-name': item.name,
                                        'data-toggle': 'modal',
                                        'data-target': '#purchaseModal'
                                    })
                                    .text('Purchase')
                                    ) 
                            ) 
                    );
        });

    });
}

function loadBalance() {
    clearBalance();
    var balanceRow = $('#balance-row');
    $.ajax({
        type: 'GET',
        url: "balance/"
    }).success(function (data, status) {
        balanceRow.append($('<p>').text("Balance: " + data));
    });
}

function loadMessage() {
    clearMessage();
    var messageRow = $('#message-row');
    $.ajax({
        type: 'GET',
        url: "message/"
    }).success(function (data, status) {
        messageRow.append($('<p>').text(data));
    });
}

function clearContent() {
    $('#content-rows').empty();
}
function clearBalance() {
    $('#balance-row').empty();
}
function clearMessage() {
    $('#message-row').empty();
}

$('#purchase-button').click(function (event) {

    event.preventDefault();

    var modal = $(this);
    var element = $(event.relatedTarget);
    var itemName = $('#item-type').text();

    $.ajax({
        type: 'DELETE',
        url: 'item/' + itemName}).success(function () {

    });
    loadItems();
    loadBalance();
    loadMessage();
});



$('#purchaseModal').on('show.bs.modal', function (event) {

    loadMessage();
    loadBalance();
    var element = $(event.relatedTarget);
    var itemName = element.data('item-name');
    var modal = $(this);

    $.ajax({
        type: 'GET',
        url: 'item/' + itemName
    }).success(function (item) {
        modal.find('#item-type').text(item.name);
        modal.find('#item-quantity').text(item.quantity);
        modal.find('#item-cost').text("Cost: " + item.cost);
    });
});

$('#cashOutModal').on('show.bs.modal', function (event) {
    loadMessage();
    loadBalance();
    var element = $(event.relatedTarget);
    var c = element.data('coins');
    var modal = $(this);

    $.ajax({
        type: 'GET',
        url: 'cashOut'
    }).success(function (coin) {
        modal.find('#changeQ').text(coin.quarters);
        modal.find('#changeD').text(coin.dimes);
        modal.find('#changeN').text(coin.nickels);
        modal.find('#changeP').text(coin.pennies);

    });
});