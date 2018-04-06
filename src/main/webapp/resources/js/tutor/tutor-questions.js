$(document).ready(function () {
    $('#pickerId').change(function () {
        let selectTest = $(this).val();
        let token = $("meta[name='_csrf']").attr("content");
        let header = $("meta[name='_csrf_header']").attr("content");
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

function addURL() {
    let select = $('#pickerId').val();
    return window.location.href = '/tutor/questions/create/' + select;
}