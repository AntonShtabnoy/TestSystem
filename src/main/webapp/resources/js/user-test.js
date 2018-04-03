$(document).ready(function () {
    let indexOfQuestion = 1;
    let radioMap = [];
    $('#nextId').click(function () {
        radioMap = [];
        $("input[name='radio-group']:checked").each(function () {
            radioMap.push($(this).val());
        });
        console.log(radioMap);
        let data = {"answers": radioMap};
        var token = $("meta[name='_csrf']").attr("content");
        var header = $("meta[name='_csrf_header']").attr("content");
        $(document).ajaxSend(function (e, xhr, options) {
            xhr.setRequestHeader(header, token);
        });
        $.ajax({
            url: '/user/tests/' + indexOfQuestion,
            type: 'PUT',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            dataType: 'json',
            contentType: "application/json; charset=utf-8",
            data: JSON.stringify(data),
            success: function () {
                console.log("All were sent");
                // $("input[name='radio-group']", '#formId').each(function () {
                //     $(this).prop('checked', false);
                // });
                // $("textarea[name='text_answer']").val('');
                // $('#type').val('single');
                // $('#description').val('');
                // $('#literature').val('');
                // $('#link').val('');
            },
            error: function (xhr, ajaxOptions, thrownError) {
                console.log(xhr.status);
            }
        });
        indexOfQuestion++;
    });

});