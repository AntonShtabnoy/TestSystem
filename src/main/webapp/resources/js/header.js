$(document).ready(function() {
    $('li.active').removeClass('active');
    $('a[href="' + location.pathname + '"]').closest('li').addClass('active');
});

function formSubmit() {
    document.getElementById("logoutForm").submit();
}