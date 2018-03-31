$(document).ready(function () {
    let wrapper = $(".col-lg-12");
    let add_button = $("#add_answer");
    var radioIndex = 3;
    console.log(radioIndex);

    function addAnswer() {
        return '<div class="input-group">\n' +
            '<span class="input-group-addon">\n' +
            '<input type="radio" value="' + radioIndex + '" name="radio-group" aria-label="Checkbox for following text input">\n' +
            '</span>\n' +
            ' <textarea  rows="2" name="text_answer" class="form-control" aria-label="Text input with checkbox"></textarea>\n' +
            ' </div>';
    }

    $(add_button).click(function (e) {
        e.preventDefault();
        $(wrapper).append(addAnswer(radioIndex));
        radioIndex++;
    });

    let map = {};
    let radioMap = [];
    let i = 0, j = 0;
    let type, description, literature, link;
    $('#nextId').click(function () {
        map = {};
        radioMap = {};
        radioMap = [];
        i = 0;
        j = 0;
        $("textarea[name='text_answer']").each(function () {
            map[i] = $(this).val();
            i++;
        });
        if ($('#type').val() === 'single') {
            radioMap.push($("input[name='radio-group']:checked", '#formId').val());
        }
        else {
            $("input[name='radio-group']:checked", '#formId').each(function () {
                radioMap.push($(this).val());
            });
        }
        type = $('#type').val();
        description = $('#description').val();
        literature = $('#literature').val();
        link = $('#link').val();


        let data = {
            "answers": map, "correct": radioMap, "description": description, "literature": literature,
            "link": link
        };
        var siteCode;
        $(function () {
            siteCode = getLastPartOfUrl(window.location.href);
        });

        var getLastPartOfUrl = function ($url) {
            let url = $url;
            let urlsplit = url.split("/");
            let lastPart = urlsplit[urlsplit.length - 1];
            if (lastPart === '') {
                lastPart = urlsplit[urlsplit.length - 2];
            }
            return lastPart;
        };
        $.ajax({
            url: '/tutor/questions/create' + siteCode,
            method: "post",
            dataType: 'json',
            contentType: "application/json; charset=utf-8",
            data: JSON.stringify(data),
            success: function () {
                console.log("All were sent")
            }
        });
        console.log(description);
        console.log(type);
        console.log(map);
        console.log(radioMap);
    });

    $('#type').change(function () {
        if ($('#type').val() === 'single') {
            $(':checkbox').attr('type', 'radio');
        } else {
            $(':radio').attr('type', 'checkbox');
        }
    });
});