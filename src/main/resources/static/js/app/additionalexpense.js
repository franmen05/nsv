/**
 * 
 * @author gdelossantos
 */


$(document).ready(function () {

//    var events = $('#events');
    var table = $('#t_additionalexpense').DataTable({
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
//        $("#price").val(rowData[0][2]);
        $("#description").val(rowData[0][2]);
        $("#status").val(rowData[0][3]);
    })
    .on('deselect', function (e, dt, type, index) {
        
        itemsHelperAdditionalExpense.clearForm();
    });
});



var itemsHelperAdditionalExpense = {
    inactiveItem: function () {
        var form =$("#form-additionalexpense");
        form.attr("action","/additionalexpense/delete").submit();
//        alert( id);
    },
    activeItem: function () {
        $("#form-additionalexpense").attr("action","/additionalexpense/reactive").submit();
    },
    clearForm:function (){
        $("#id").val("");
        $("#name").val("");
//        $("#price").val("");
        $("#description").val("");
    }
}