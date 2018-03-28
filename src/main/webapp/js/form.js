$(document).ready(function () {
    //alert($("#inputActive").is(':checked'));

    /*$("#ping").click(function () {
        $.ajax({
            url:"/ping",
            type:"GET",
            contentType:"application/json; charset=utf-8",
            dataType:"text",
            success: function(result){
                console.log(result);
                alert(result);
            }
        });
    });

    $("#addPerson").click(function () {
        console.log('PERSON');
        var person = {
            name: $("#name").val(),
            age: $("#age").val()
        };
        console.log('PER', person);

        $.ajax({
            url:"/person",
            type:"POST",
            data: JSON.stringify(person),
            contentType:"application/json; charset=utf-8",
            success: function(){
                alert('Success');
            }
        });

        clearFields();
    });

    $("#personList").click(function () {
        $.ajax({
            url:"/person",
            type:"GET",
            contentType:"application/json; charset=utf-8",
            dataType:"json",
            success: function(result){
                console.log(result);
                alert(JSON.stringify(result));
            }
        });
    });*/
    $("#organization").click(function () {
        var id = $("#inputOrgId").val();
        $.ajax({
            url:"/api/organization/" + id,
            type:"GET",
            contentType:"application/json; charset=utf-8",
            dataType:"json",
            success: function(result){
                console.log(result);
                alert(JSON.stringify(result));
            }
        });
    });

    $("#saveOrganization").click(function () {
        var organization = {
            name: $("#inputName").val(),
            fullName: $("#inputFullName").val(),
            inn: $("#inputInn").val(),
            kpp: $("#inputKpp").val(),
            address: $("#inputAddress").val(),
            phone: $("#inputPhone").val(),
            isActive: $("#inputActive").is(':checked')
        };
        $.ajax({
            url:"/api/organization/create",
            type:"POST",
            data: JSON.stringify(organization),
            contentType:"application/json; charset=utf-8",
            dataType:"json",
            success: function(result){
                console.log(result);
                alert(JSON.stringify(result));
            }
        });
    });
    $("#updateOrganization").click(function () {
        var organization = {
            id: $("#inputId").val(),
            name: $("#inputName").val(),
            fullName: $("#inputFullName").val(),
            inn: $("#inputInn").val(),
            kpp: $("#inputKpp").val(),
            address: $("#inputAddress").val(),
            phone: $("#inputPhone").val(),
            isActive: $("#inputActive").is(':checked')
        };
        $.ajax({
            url:"/api/organization/update",
            type:"POST",
            data: JSON.stringify(organization),
            contentType:"application/json; charset=utf-8",
            dataType:"json",
            success: function(result){
                console.log(result);
                alert(JSON.stringify(result));
            }
        });
    });
    $("#deleteOrganization").click(function () {
        var id = $("#inputDeletedId").val();
        $.ajax({
            url:"/api/organization/delete/" + id,
            type:"GET",
            data: id,
            contentType:"application/json; charset=utf-8",
            dataType:"json",
            success: function(result){
                console.log(result);
                alert(JSON.stringify(result));
            }
        });
    });

});

var clearFields = function () {
    $("#name").val('');
    $("#age").val('');
};