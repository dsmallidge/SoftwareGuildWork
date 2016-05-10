$('#submitDates').click(function () {
    loadBlogs();
});
function loadBlogs() {
    var startDate = $('#blogStart').val();
    var endDate = $('#blogEnd').val();
    $.ajax({
        data: { 'start' : startDate, 'end' : endDate },
        url: "daterange"
    }).success(function (data, status) {
        fillBlogTable(data, status);
    });
}
function clearTable() {
    $('#contentBlogs').empty();
}
function clearBlogTable() {
    $('#blogTable').empty();
}

function clearChangeMade() {
    $('#changeMade').empty();
}

function fillBlogTable(blogList, status) {
    clearTable();
    var bTable = $('#contentBlogs');
    // fills the table on the left to select from
    $.each(blogList, function (index, blog) {
        var titleAnswer = blog.title;
        bTable.append($('<tr>')
                .append($('<td>')
                        .append($('<a>')
                                .attr({
                                    'data-blog-id': blog.blogId,
                                    'data-target': '#correct1',
                                    'onClick': 'loadBlog(' + blog.blogId + ')'
                                })
                                .html(titleAnswer)
                                )
                        )
                );
    }); // ends the 'each' function
}

function loadTextArea(id) {
    clearChangeMade();
    $.ajax({
        type: 'GET',
        url: 'blog/' + id
    }).success(function (data, status) {
        tinyMCE.get('blogData').setContent(data.blogEntry);
        $('#correct-startDate').val(data.startDate);
        $('#correct-endDate').val(data.endDate);
        $('#correct-category').val(data.category);
        var tags = data.tags;
        var tag = tags.toString();
        tag = tag.replace(/,/g, ', ');
        $('#correct-tags').val(tag);
        $('#correct-title').val(data.title);
        $('#correct-category1').val(data.category);
        $('#blog-id').val(id);
    });
}

function loadBlog(id) {

    $.ajax({
        type: 'GET',
        url: 'blog/' + id
    }).success(function (data, status) {
        //fillBlogTableSingle(data, status);
        loadTextArea(id);
    });
}

$('#Edit-Post').click(function (event) {

    event.preventDefault();
    var editTags = $('#correct-tags').val();
    var splitTags = editTags.split(", ");
    var editBlogEntry = tinymce.get('blogData').getContent();
    editBlogEntry = editBlogEntry.replace(/(\r\n|\n|\r)/gm, " ");

    $.ajax({
        type: 'PUT',
        url: 'blog/' + $('#blog-id').val(),
        data: JSON.stringify({
            blogEntry: editBlogEntry,
            blogId: $('#blog-id').val(),
            title: $('#correct-title').val(),
            startDate: $('#correct-startDate').val(),
            endDate: $('#correct-endDate').val(),
            tags: splitTags,
            category: $('#correct-category1').val()
        }),
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        'dataType': 'json'
    }).success(function (data, status) {
        //clearSuccessTable();
        returnChangeMade();
        loadBlogs();
        $('#validation-errors').empty();
    }).error(function (data, status) {
        $('#validation-errors').empty();
        $.each(data.responseJSON.fieldErrors, function (index, validationError) {
            var errorDiv = $('#validation-errors');
            errorDiv.append(validationError.message).append($('<br>'));
        });
    });
});


function returnChangeMade() {
    var changeTable = $('#changeMade');
    clearChangeMade();
    changeTable.append($('<tr>')
            .append($('<td>')
                    .text("The change has been made successfully.\n\
                    Select another Link above to modify another Blog.")
                    )
            );
}