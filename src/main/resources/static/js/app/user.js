/**
 * 
 * @author gdelossantos
 */


$(document).ready(function () {

//    var events = $('#events');
    var table = $('#itemsUser').DataTable({
        "scrollY": "400px",
        "scrollCollapse": true,
        "paging": false,
        select: {
            style: 'single'
        }
    });

    table.on('select', function (e, dt, type, index) {
        var rowData = table.rows(index).data().toArray();
        console.info(rowData[0]);
     
        $("#id").val(rowData[0][0]);
        $("#username").val(rowData[0][1]);
        $("#name").val(rowData[0][2]);
        $("#email").val(rowData[0][3]);
        $("#enabled").val(rowData[0][4]);
        $("#comment").val(rowData[0][7]);
        $("#createDate").val(rowData[0][5]);
    })
    .on('deselect', function (e, dt, type, index) {
        
        itemsHelperUser.clearForm();
    });
});



var itemsHelperUser = {
    inactiveItem: function () {
        var form =$("#form-user");
        form.attr("action","/user/inactive").submit();
//        alert( id);
    },
    activeItem: function () {
        $("#form-user").attr("action","/user/reactive").submit();
    },
    clearForm:function (){
        $("#id").val("");
        $("#username").val("");
        $("#name").val("");
        $("#password").val("");
        $("#email").val("");
        $("#email").val("");
        $("#comment").val("");
    }
}