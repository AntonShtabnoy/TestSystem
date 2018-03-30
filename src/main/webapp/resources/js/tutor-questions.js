$(document).ready(function () {
    $('#pickerId').change(function () {
        var selectTest = $(this).val();
        alert(selectTest);
        var token = $("meta[name='_csrf']").attr("content");
        var header = $("meta[name='_csrf_header']").attr("content");
        $(document).ajaxSend(function (e, xhr, options) {
            xhr.setRequestHeader(header, token);
        });
        $.ajax({
            url: '/tutor/questions/' + selectTest,
            type: 'PUT',
            success: function () {
                $("#questions_table_container").load("http://localhost:8080/tutor/questions/" + selectTest + " #questions_table");
            }
        })
        ;
    });
});