$(document).ready(function() {
    // $('li.active').removeClass('active');
    // $('a[href="' + location.pathname + '"]').closest('li').addClass('active');
    var names = [];
    $('#checkAll').change(function () {
        $("input[name='users-check']").prop('checked', $(this).prop("checked"));
    });
    $("input[name='users-check']").change(function() {
        if (false === $(this).prop("checked")) {
            $("#checkAll").prop('checked', false);
        }
        if ($("input[name='users-check']:checked").length === $("input[name='users-check']").length ){
            $("#checkAll").prop('checked', true);
        }
    });
    $('#delete-user').click(function () {
        names = [];
        $("input[name='users-check']:checked").each(function() {
            names.push($(this).val());
        });
        alert(names);
    });
    $('#delete-user').click(function () {
        var token = $("meta[name='_csrf']").attr("content");
        var header = $("meta[name='_csrf_header']").attr("content");
        $(document).ajaxSend(function(e, xhr, options) {
            xhr.setRequestHeader(header, token);
        });
        $.ajax({
            url: '/admin/' + names,
            type: 'DELETE',
            success: function () {
                $("#users_table_container").load("http://localhost:8080/admin #example");
            }
        })
        ;
    });
} );



