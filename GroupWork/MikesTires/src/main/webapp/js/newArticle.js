$(document).ready(function () {
});

$('#submit').click(function (event) {
    event.preventDefault();
    for (instance in CKEDITOR.instances)
        CKEDITOR.instances[instance].updateElement();

    var newArticle = document.getElementById("articleText").value;
    newArticle = newArticle.replace(/(\r\n|\n|\r|\t)/gm, " ");
    newArticle = newArticle.replace(/\\/gm, " ");
    newArticle = newArticle.replace(/"/gm, "'");
    var articleName = $('#articleName').val();

    $.ajax({
        type: 'POST',
        url: 'staticpage',
        contentType: 'application/json',
        // Build a JSON object from the data in the form
        data: JSON.stringify({
            pageName: articleName,
            pageContent: newArticle
        }),
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        'dataType': 'json'
    }).success(function (data, status) {
            $(location).attr('href', 'adminHome');
            $('#validation-errors').empty();
    }).error(function (data, status) {
        $('#validation-errors').empty();
        $.each(data.responseJSON.fieldErrors, function (index, validationError) {
            var errorDiv = $('#validation-errors');
            errorDiv.append(validationError.message).append($('<br>'));
        });
    });
});