$(document).ready(function () {
    var userName = $('#userName');
});

$('#submit').click(function (event) {
    event.preventDefault();
    var editBlogEntry = tinymce.get('blogEntry').getContent();
    editBlogEntry = editBlogEntry.replace(/(\r\n|\n|\r)/gm, " ");
    var editTags = $('#newTag').val();
    var splitTags = editTags.split(", ");
    var approved;

    if (userName == "mike") {
        approved = "Y";
    } else {
        approved = "N";
    };

    $.ajax({
        type: 'POST',
        url: 'blog',
        contentType: 'application/json',
        // Build a JSON object from the data in the form
        data: JSON.stringify({
            title: $('#blogTitle').val(),
            category: $('#dropDownMenu1').val(),
            blogEntry: editBlogEntry,
            startDate: $('#startDate').val(),
            endDate: $('#endDate').val(),
            approved: approved,
            tags: splitTags,
            userName: userName
        }),
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        'dataType': 'json'
    }).success(function (data, status) {
        $(location).attr('href', 'adminHome'),
                $('#validation-errors').empty();
    }).error(function (data, status) {
        $('#validation-errors').empty();
        $.each(data.responseJSON.fieldErrors, function (index, validationError) {
            var errorDiv = $('#validation-errors');
            errorDiv.append(validationError.message).append($('<br>'));
        });
    });
});