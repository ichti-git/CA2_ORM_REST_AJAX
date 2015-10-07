$(document).ready(readyFunc);
function readyFunc() {
    $('.jsonexample').hide();
    $('.toggleexample').on('click', function(event) {
        
        var ex = $(event.target).children(".jsonexample");
        ex.toggle();
        if (ex.is(":visible")) {
            $(event.target).html("Click to hide example<br>");
            $(event.target).append(ex);
        }
        else {
            $(event.target).html("Click to show example<br>");
            $(event.target).append(ex);
        }
    });
}