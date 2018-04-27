$(document).ready(function () {
    $('#pickerId').change(function () {
        let selectTest = $(this).val();
        let token = $("meta[name='_csrf']").attr("content");
        let header = $("meta[name='_csrf_header']").attr("content");
        $(document).ajaxSend(function (e, xhr, options) {
            xhr.setRequestHeader(header, token);
        });
        $.ajax({
            url: window.location.pathname + selectTest,
            type: 'PUT',
            success: function () {
                $("#questions_table_container").load(window.location.pathname + selectTest + " #questions_table");
            }
        })
        ;
    });
});

function addURL() {
    let select = $('#pickerId').val();
    console.log(window.location.pathname + 'create/' + select);
    return window.location.href = window.location.pathname.substring(0, window.location.pathname.length - 1) + 'create/' + select;
}