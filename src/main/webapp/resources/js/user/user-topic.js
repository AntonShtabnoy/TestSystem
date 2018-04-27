$(document).ready(function () {
    $('#topicId').change(function () {
        console.log("click");
        let data = $(this).val();
        var token = $("meta[name='_csrf']").attr("content");
        var header = $("meta[name='_csrf_header']").attr("content");
        $(document).ajaxSend(function (e, xhr, options) {
            xhr.setRequestHeader(header, token);
        });
        console.log(window.location.pathname.substring(0, window.location.pathname.lastIndexOf('/')) + "/tests/" + data);
        $('#tests_container').load(window.location.pathname.substring(0, window.location.pathname.lastIndexOf('/')) + "/tests/" + data + " #tests_container");
        $('.selectpicker').selectpicker('refresh');
        // $.ajax({
        //     url: '/user/tests/' + $('#questionId').val(),
        //     type: 'PUT',
        //     headers: {
        //         'Accept': 'application/json',
        //         'Content-Type': 'application/json'
        //     },
        //     dataType: 'json',
        //     contentType: "application/json; charset=utf-8",
        //     data: JSON.stringify(data),
        //     success: function () {
        //         console.log("All were sent");
        //         $('#mainId').load("http://localhost:8080/user/tests/" + nextQuestion + " #testId");
        //     },
        //     error: function (xhr, ajaxOptions, thrownError) {
        //         console.log(xhr.status);
        //     }
        // });
    });


});