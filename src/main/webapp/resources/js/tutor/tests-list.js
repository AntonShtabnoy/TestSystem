$(document).ready(function() {
    // $('li.active').removeClass('active');
    // $('a[href="' + location.pathname + '"]').closest('li').addClass('active');
    var topics = [];
    $('#checkAll').change(function () {
        $("input[name='tests-check']").prop('checked', $(this).prop("checked"));
    });
    $("input[name='tests-check']").change(function() {
        if (false === $(this).prop("checked")) {
            $("#checkAll").prop('checked', false);
        }
        if ($("input[name='tests-check']:checked").length === $("input[name='tests-check']").length ){
            $("#checkAll").prop('checked', true);
        }
    });
    $('#delete-test').click(function () {
        topics = [];
        $("input[name='tests-check']:checked").each(function() {
            names.push($(this).val());
        });
        alert(names);
    });
    $('#delete-test').click(function () {
        var token = $("meta[name='_csrf']").attr("content");
        var header = $("meta[name='_csrf_header']").attr("content");
        $(document).ajaxSend(function(e, xhr, options) {
            xhr.setRequestHeader(header, token);
        });
        $.ajax({
            url: '/tutor/test/' + names,
            type: 'DELETE',
            success: function () {
                $("#tests_table_container").load("http://localhost:8080/tutor/test #tests_table");
            }
        })
        ;
    });
} );



