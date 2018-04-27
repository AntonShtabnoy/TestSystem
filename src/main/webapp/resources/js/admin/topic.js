$(document).ready(function() {
    // $('li.active').removeClass('active');
    // $('a[href="' + location.pathname + '"]').closest('li').addClass('active');
    var topics = [];
    $('#checkAll').change(function () {
        $("input[name='topics-check']").prop('checked', $(this).prop("checked"));
    });
    $("input[name='topics-check']").change(function() {
        if (false === $(this).prop("checked")) {
            $("#checkAll").prop('checked', false);
        }
        if ($("input[name='topics-check']:checked").length === $("input[name='topics-check']").length ){
            $("#checkAll").prop('checked', true);
        }
    });
    $('#delete-topic').click(function () {
        names = [];
        $("input[name='topics-check']:checked").each(function() {
            names.push($(this).val());
        });
    });
    $('#delete-topic').click(function () {
        var token = $("meta[name='_csrf']").attr("content");
        var header = $("meta[name='_csrf_header']").attr("content");
        $(document).ajaxSend(function(e, xhr, options) {
            xhr.setRequestHeader(header, token);
        });
        $.ajax({
            url: window.location.pathname + names,
            type: 'DELETE',
            success: function () {
                $("#topics_table_container").load(window.location.pathname + " #topics_table");
            }
        })
        ;
    });
} );



