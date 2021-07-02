/**
 * 
 * @author gdelossantos
 */


$(document).ready(function () {

//    var events = $('#events');
    var table = $('#itemsProducto').DataTable({
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
        $("#price").val(rowData[0][2]);
        $("#description").val(rowData[0][3]);
        $("#status").val(rowData[0][4]);
    })
    .on('deselect', function (e, dt, type, index) {
        
        itemsHelperProduct.clearForm();
    });
});



var itemsHelperProduct = {
    inactiveItem: function () {
        var form =$("#form-product");
        form.attr("action","/product/inactive").submit();
//        alert( id);
    },
    activeItem: function () {
        $("#form-product").attr("action","/product/reactive").submit();
    },
    clearForm:function (){
        $("#id").val("");
        $("#name").val("");
        $("#price").val("");
        $("#description").val("");
    }
}