/**
 * 
 * @author gdelossantos
 */


$(document).ready(function () {

//    var events = $('#events');
    var table = $('#itemsCurrency').DataTable({
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
        $("#name").val(rowData[0][1]);
        $("#code").val(rowData[0][2]);
        $("#symbol").val(rowData[0][3]);
        $("#status").val(rowData[0][4]);

    })
    .on('deselect', function (e, dt, type, index) {
        
        itemsHelper.clearForm();
    });
});



var itemsHelper = {
    inactiveItem: function () {
        var form =$("#form-currency");
        form.attr("action","/currency/inactive").submit();
//        alert( id);
    },
    activeItem: function () {
        $("#form-currency").attr("action","/currency/reactive").submit();
    },
    clearForm:function (){
        $("#id").val("");
        $("#name").val("");
        $("#code").val("");
//        $("#createDate").val("");
        $("#symbol").val("");
    }
}
//itemsCurrency