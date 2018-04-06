$(document).ready(function () {
    let indexOfQuestion = 1;
    let nextQuestion = 1;
    let radioMap = [];
    var map = {};
    let count = $('#count').val();
    $(document).on("click", "#nextId", function () {
        if (indexOfQuestion >= count) {
            let answers = {"answers": map};
            console.log(answers);
            $.ajax({
                url: '/user/questions',
                type: 'POST',
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                },
                dataType: 'json',
                contentType: "application/json; charset=utf-8",
                data: JSON.stringify(answers),
                success: function () {
                    console.log("All were sent");
                    window.location.href = "/user/statistics";
                },
                error: function (xhr, ajaxOptions, thrownError) {
                    console.log(xhr.status);
                }
            });
        }
        radioMap = [];
        $("input[name='radio-group']:checked").each(function () {
            radioMap.push($(this).val());
        });
        map[$('#questionId').val()] = radioMap;
        radioMap = [];
        var token = $("meta[name='_csrf']").attr("content");
        var header = $("meta[name='_csrf_header']").attr("content");
        $(document).ajaxSend(function (e, xhr, options) {
            xhr.setRequestHeader(header, token);
        });
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
        $('#mainId').load("http://localhost:8080/user/questions/" + nextQuestion + " #mainId");
        nextQuestion++;
        //     },
        //     error: function (xhr, ajaxOptions, thrownError) {
        //         console.log(xhr.status);
        //     }
        // });
        indexOfQuestion++;
        console.log(map);
    });
    $(document).on("click", "#finishId", function () {

        let answers = {"answers": map};
        console.log(answers);
        $.ajax({
            url: '/user/questions',
            type: 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            dataType: 'json',
            contentType: "application/json; charset=utf-8",
            data: JSON.stringify(answers),
            success: function () {
                console.log("All were sent");
                window.location.href = "/user/statistics";
            },
            error: function (xhr, ajaxOptions, thrownError) {
                console.log(xhr.status);
            }
        });
    });

});