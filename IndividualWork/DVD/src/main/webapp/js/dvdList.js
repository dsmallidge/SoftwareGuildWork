$(document).ready(function () {
    loadDvds();
});

$('#add-button').click(function (event) {

    event.preventDefault();

    $.ajax({
        type: 'POST',
        url: 'dvd',
        data: JSON.stringify({
            title: $('#add-title').val(),
            releaseDate: $('#add-release-date').val(),
            mpaaRating: $('#add-mpaa-rating').val(),
            director: $('#add-director').val(),
            studio: $('#add-studio').val(),
            note: $('#add-note').val()
        }),
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        'dataType': 'json'
    }).success(function (data, status) {

        $('#add-title').val('');
        $('#add-release-date').val('');
        $('#add-mpaa-rating').val('');
        $('#add-director').val('');
        $('#add-studio').val('');
        $('#add-note').val('');
        $('#validationErrors').empty();
        loadDvds();

    }).error(function (data, status) {
        $('#validation-errors').empty();
        $.each(data.responseJSON.fieldErrors, function (index, validationError) {
            var errorDiv = $('#validation-errors');
            errorDiv.append(validationError.message).append($('<br>'));
        });
    });
});

function loadDvds() {
    $.ajax({
        url: "dvds"}
    ).success(function (data, status) {
        fillDvdTable(data, status);
    });
}

function clearDvdTable() {
    $('#content-rows').empty();
}

function fillDvdTable(dvdList, status) {

    clearDvdTable();

    var cTable = $('#content-rows');

    $.each(dvdList, function (index, dvd) {
        cTable.append($('<tr>')
                .append($('<td>')
                        .append($('<a>')
                                .attr({
                                    'data-dvd-id': dvd.id,
                                    'data-toggle': 'modal',
                                    'data-target': '#detailsModal'
                                })
                                .text(dvd.title)
                                )
                        )
                .append($('<td>').text(dvd.releaseDate))
                .append($('<td>')
                        .append($('<a>')
                                .attr({
                                    'data-dvd-id': dvd.id,
                                    'data-toggle': 'modal',
                                    'data-target': '#editModal'
                                })
                                .text('Edit')
                                )
                        )
                .append($('<td>')
                        .append($('<a>')
                                .attr({
                                    'onClick': 'deleteDvd(' + dvd.id + ')'
                                })
                                .text('Delete')
                                )
                        )
                );
    });
}

function deleteDvd(id) {
    var answer = confirm("Do you really want to delete this address?");
    if (answer === true) {
        $.ajax({
            type: 'DELETE',
            url: 'dvd/' + id}).success(function () {
            loadDvds();
        });
    }
}

$('#detailsModal').on('show.bs.modal', function (event) {

    var element = $(event.relatedTarget);
    var dvdId = element.data('dvd-id');
    var modal = $(this);

    $.ajax({
        type: 'GET',
        url: 'dvd/' + dvdId}).success(function (dvd) {
        modal.find('#dvd-id').text(dvd.id);
        modal.find('#dvd-title').text(dvd.title);
        modal.find('#dvd-release-date').text(dvd.releaseDate);
        modal.find('#dvd-mpaa-rating').text(dvd.mpaaRating);
        modal.find('#dvd-director').text(dvd.director);
        modal.find('#dvd-studio').text(dvd.studio);
        modal.find('#dvd-note').text(dvd.note);
    });
});


$('#editModal').on('show.bs.modal', function (event) {
    var element = $(event.relatedTarget);
    var dvdId = element.data('dvd-id');
    var modal = $(this);
    $.ajax({
        type: 'GET',
        url: 'dvd/' + dvdId}).success(function (dvd) {
        modal.find('#dvd-id').text(dvd.id);
        modal.find('#edit-dvd-id').val(dvd.id);
        modal.find('#edit-title').val(dvd.title);
        modal.find('#edit-release-date').val(dvd.releaseDate);
        modal.find('#edit-mpaa-rating').val(dvd.mpaaRating);
        modal.find('#edit-director').val(dvd.director);
        modal.find('#edit-studio').val(dvd.studio);
        modal.find('#edit-note').val(dvd.note);
    });
});


$('#edit-button').click(function (event) {

    event.preventDefault();


    $.ajax({
        type: 'PUT',
        url: 'dvd/' + $('#edit-dvd-id').val(),
        data: JSON.stringify({
            id: $('#edit-dvd-id').val(),
            title: $('#edit-title').val(),
            releaseDate: $('#edit-release-date').val(),
            mpaaRating: $('#edit-mpaa-rating').val(),
            director: $('#edit-director').val(),
            studio: $('#edit-studio').val(),
            note: $('#edit-note').val()

        }),
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        'dataType': 'json'
    }).success(function () {
        loadDvds();
    });
});

$('#search-button').click(function (event) {

    event.preventDefault();
    $.ajax({
        type: 'POST',
        url: 'search/dvds',
        data: JSON.stringify({
            title: $('#search-title').val(),
            releaseDate: $('#search-release-date').val(),
            mpaaRating: $('#search-mpaa-rating').val(),
            director: $('#search-director').val(),
            studio: $('#search-studio').val(),
            note: $('#search-note').val()
        }),
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        'dataType': 'json'
    }).success(function (data, status) {
        $('#search-title').val('');
        $('#search-release-date').val('');
        $('#search-mpaa-rating').val('');
        $('#search-director').val('');
        $('#search-studio').val('');
        $('#search-note').val('');
        fillDvdTable(data, status);
    });
});