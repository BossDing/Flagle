$(function () {
    $('#errorMessage').hide();
    var message = $('#errorMessage').text()==''?0:$('#errorMessage').text();
    if (message != 0 && message!='0') {
        $('#errorMessage').show();
    }
});