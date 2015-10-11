$(document).ready(readyFunc);
var t; //test variable
//
//Add events when the page is ready
function readyFunc() {
    $('.form_hobbies, .form_hobbies_desc').on('change', function(event) {
        addHobbyField(event);
    });
    $('.form_phones, .form_phones_desc').on('change', function(event) {
        addPhoneField(event);
    });
    
    $('#get').on('click', function(event) {
        var id = $("#form_id").val();
        if (id != "") {
            $.ajax({
                url: 'api/person/complete/'+id,
                type: 'GET',
                contentType: 'application/json; charset=UTF-8'
            }).fail(function(data) {
                errorToInfo(data);
            }).done(function(data) {
                $("#info").html(parsePerson(data));
            });            
        }
    });
    $('#add').on('click', function(event) {
        var person = makeJsonPerson();
        $.ajax({
            url: 'api/person/',
            type: 'PUT',
            data: person,
            contentType: 'application/json; charset=UTF-8'
        }).fail(function(data) {
            errorToInfo(data);
        }).done(function(data) {
            $("#info").html(parsePerson(data));
        });
    });    
    $('#edit').on('click', function(event) {
        var person = makeJsonPerson();
        var id = $('#form_id').val();
        $.ajax({
            url: 'api/person/'+id,
            type: 'POST',
            data: person,
            contentType: 'application/json; charset=UTF-8'
        }).fail(function(data) {
            errorToInfo(data);
        }).done(function(data) {
            $("#info").html(parsePerson(data));
        });
    });
    $('#delete').on('click', function(event) {
        var id = $('#form_id').val();
        $.ajax({
            url: 'api/person/'+id,
            type: 'DELETE',
            contentType: 'application/json; charset=UTF-8'
        }).fail(function(data) {
            errorToInfo(data);
        }).done(function(data) {
            $("#info").html(parsePerson(data));
        });
    });
}

//form event
function addHobbyField(event) {
    h = $(event.target).parent().parent();
    if (h.data("changed") == false) {
        
        var row = $('<tr data-changed="false"><td>Hobby:<input type="text" class="form_hobbies"></td><td>Description: <input type="text" class="form_hobbies_desc"></td></tr>');
        row.find('.form_hobbies, .form_hobbies_desc').on('change', function(event) {
            addHobbyField(event);
        });
        
        $(event.target).parent().parent().parent()
                .append(row);
        h.data("changed", "true");
    }
}

//form event
function addPhoneField(event) {
    h = $(event.target).parent().parent();
    if (h.data("changed") == false) {
        
        var row = $('<tr data-changed="false"><td>Number:<input type="text" class="form_phones"></td><td>Description: <input type="text" class="form_phones_desc"></td></tr>');
        row.find('.form_phones, .form_phones_desc').on('change', function(event) {
            addPhoneField(event);
        });
        
        $(event.target).parent().parent().parent()
                .append(row);
        h.data("changed", "true");
    }
}

//From json object to more readable info, to be used in the info div
function parsePerson(data) {
    var htmlstring = "";
    for (var info in data) {
        if (typeof data[info] === 'object') {
            htmlstring += info +": <br>[<br>";
            for (var i in data[info]) {
                for (var j in data[info][i]) {
                    htmlstring += "&nbsp;&nbsp;&nbsp;&nbsp;" + j + ": " + data[info][i][j] + " ";
                }
                htmlstring += "<br>"
            }
            htmlstring += "]<br>";
        }
        else {
            htmlstring += info + ": " + data[info] + "<br>";
        }
    }
    return htmlstring;
}

//Converts the info in the person form to a json string
//Could also have used an object and JSON.stringify in this function
function makeJsonPerson() {
    var jsonstring = "{";
    if ($("#form_id").val() != "") jsonstring += '"id":"' + $("#form_id").val() + '",';
    if ($("#form_firstname").val() != "") jsonstring += '"firstname":"' + $("#form_firstname").val() + '",';
    if ($("#form_lastname").val() != "") jsonstring += '"lastname":"' + $("#form_lastname").val() + '",';
    if ($("#form_email").val() != "") jsonstring += '"email":"' + $("#form_email").val() + '",';
    //phones
    
    if ($(".form_phones")[0].value != "") {
        var ps = $(".form_phones");
        var psd = $(".form_phones_desc");
        jsonstring += '"phones": [';
        for (i = 0; i < ps.size(); i++) {
            if (ps[i].value != "") jsonstring += '{"number":"' + ps[i].value + '", "description":"' + psd[i].value + '"},';
        }
        jsonstring = jsonstring.slice(0,-1);
        jsonstring += '],'
    }
    
    //hobbies
    if ($(".form_hobbies")[0].value != "") {
        var hs = $(".form_hobbies");
        var hsd = $(".form_hobbies_desc");
        jsonstring += '"hobbies": [';
        for (i = 0; i < hs.size(); i++) {
            if (hs[i].value != "") jsonstring += '{"name":"' + hs[i].value + '", "description":"' + hsd[i].value + '"},';
        }
        jsonstring = jsonstring.slice(0,-1);
        jsonstring += '],'
    }
    if ($("#form_street").val() != "") jsonstring += '"street":"' + $("#form_street").val() + '",';
    if ($("#form_streetnumber").val() != "") jsonstring += '"streetnumber":"' + $("#form_streetnumber").val() + '",';
    if ($("#form_zip").val() != "") jsonstring += '"zipcode":"' + $("#form_zip").val() + '",';
    if ($("#form_city").val() != "") jsonstring += '"city":"' + $("#form_city").val() + '",';
    jsonstring = jsonstring.slice(0,-1);
    jsonstring += "}";
    return jsonstring;
}

//Write and error message in the info div.
function errorToInfo(data) {
    var failstring = "Error code: " + data.status + "<br>";
    failstring += "Error message: " + data.responseJSON.message + "<br>";
    $("#info").html(failstring);
    
}