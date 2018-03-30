$(document).ready(function () {
    $('#type').change(function () {
        if ($('#type').val() === 'single') {
            $(':checkbox').attr('type', 'radio');
        } else {
            $(':radio').attr('type', 'checkbox');
        }
    });
});