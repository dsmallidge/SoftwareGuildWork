$(document).ready(function () {
    loadApprovals();
    
    $('#approvalModal').on('show.bs.modal', function (event) {
    var element = $(event.relatedTarget);
    var blogId = element.data('blog-id');          
    var modal = $(this);    
    
    $.ajax({
        type: 'GET',
        url: 'blog/' + blogId}).success(function (blog) {
        var blogEntry = blog.blogEntry;
        blogEntry = $(blogEntry).text();
        
        modal.find('#blog-id').val(blogId);
        modal.find('#blog-title').text(blog.title);
        modal.find('#blog-category').text(blog.category);
        modal.find('#blog-blog-entry').text(blogEntry);
        modal.find('#blog-start-date').text(blog.startDate);
        modal.find('#blog-end-date').text(blog.endDate);
        modal.find('#blog-approved').text(blog.approved);
        modal.find('#blog-tags').text(blog.tags);
        modal.find('#blog-user-name').text(blog.userName);
    });
});

$('#approve-button').click(function (event) {
    
    event.preventDefault();
    var approved = "Y";

    $.ajax({
        type: 'PUT',
        url: 'approved/' + $('#blog-id').val(),
        data: JSON.stringify({
            blogId: $('#blog-id').val(),
            title: $('#blog-title').text(),
            category: $('#blog-category').text(),
            blogEntry: $('#blog-entry').text(),
            startDate: $('#blog-start-date').text(),
            endDate: $('#blog-end-date').text(),
            approved: approved,
            tags: $('#blog-tags').text(),
            userName: $('#blog-user-name').text()

        }),
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        'dataType': 'json'
    }).success(clearApprovalTable(),loadApprovals());
});

});

function clearApprovalTable() {
    $('#content-rows').empty();
}

function loadApprovals() {
    $.ajax({
        url: "approvals"}
    ).success(function (data, status) {
        fillApprovalTable(data, status);
    });
}

function fillApprovalTable(blogList, status) {

    clearApprovalTable();

    var cTable = $('#content-rows');

    $.each(blogList, function (index, blog) {
        cTable.append($('<tr>')
                .append($('<td style="text-align:left">')
                        .text(blog.title)
                        )

                .append($('<td style="text-align:left">').text(blog.startDate))
                .append($('<td>')
                        .append($('<a>')
                                .attr({
                                    'data-blog-id': blog.blogId,
                                    'data-toggle': 'modal',
                                    'data-target': '#approvalModal'
                                })
                                .text('Approve')
                                )
                        )
                );
    });
}