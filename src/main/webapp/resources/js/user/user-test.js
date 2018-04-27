$(document).ready(function () {
    let indexOfQuestion = 1;
    let nextQuestion = 1;
    let radioMap = [];
    var map = {};
    let count = $('#count').val();
    $(document).on("click", "#nextId", function () {
        $("input[name='radio-group']:checked").each(function () {
            radioMap.push($(this).val());
        });
        map[$('#questionId').val()] = radioMap;
        radioMap = [];
        if (indexOfQuestion >= count) {
            let answers = {"answers": map};
            console.log(answers);
            var token = $("meta[name='_csrf']").attr("content");
            var header = $("meta[name='_csrf_header']").attr("content");
            $(document).ajaxSend(function (e, xhr, options) {
                xhr.setRequestHeader(header, token);
            });
            $.ajax({
                url: window.location.pathname.substring(0, window.location.pathname.lastIndexOf('/')) + "/questions",
                type: 'POST',
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                },
                dataType: 'json',
                contentType: "application/json; charset=utf-8",
                data: JSON.stringify(answers),
                success: function () {
                    console.log("All were sent" + window.location.pathname.substring(0, window.location.pathname.lastIndexOf('/')) + "/user/statistics");
                    window.location.href = window.location.pathname.substring(0, window.location.pathname.lastIndexOf('/')) + "/statistics";
                },
                error: function (xhr, ajaxOptions, thrownError) {
                    console.log(xhr.status);
                }
            });
        }
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
        indexOfQuestion++;
        // if(indexOfQuestion < count) {
        $('#mainId').load(window.location.pathname.substring(0, window.location.pathname.lastIndexOf('/')) + "/questions" + "/" + nextQuestion + " #mainId");
        nextQuestion++;
        // }
        //     },
        //     error: function (xhr, ajaxOptions, thrownError) {
        //         console.log(xhr.status);
        //     }
        // });
        console.log(map);
    });
    $(document).on("click", "#finishId", function () {
        $("input[name='radio-group']:checked").each(function () {
            radioMap.push($(this).val());
        });
        map[$('#questionId').val()] = radioMap;
        radioMap = [];
        let answers = {"answers": map};
        console.log(answers);
        var token = $("meta[name='_csrf']").attr("content");
        var header = $("meta[name='_csrf_header']").attr("content");
        $(document).ajaxSend(function (e, xhr, options) {
            xhr.setRequestHeader(header, token);
        });
        $.ajax({
            url: window.location.pathname.substring(0, window.location.pathname.lastIndexOf('/')) + "/questions",
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
                window.location.href = window.location.pathname.substring(0, window.location.pathname.lastIndexOf('/')) + "/statistics";
            },
            error: function (xhr, ajaxOptions, thrownError) {
                console.log(xhr.status);
            }
        });
    });

});