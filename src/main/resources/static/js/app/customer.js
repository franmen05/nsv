/**
 * 
 * @author gdelossantos
 */


$(document).ready(function () {

//    var events = $('#events');
    var table = $('#itemsCustomer').DataTable({
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
        $("#email").val(rowData[0][2]);
        $("#rnc").val(rowData[0][3]);
        $("#passport").val(rowData[0][4]);
        $("#phone").val(rowData[0][5]);
        $("#phone2").val(rowData[0][6]);
        $("#status").val(rowData[0][7]);
        $("#createDate").val(rowData[0][8]);
        $("#lastUpdateDate").val(rowData[0][9]);
        $("#address").val(rowData[0][10]);
        $("#comment").val(rowData[0][11]);
        $("#lastName").val(rowData[0][12]);
    })
    .on('deselect', function (e, dt, type, index) {
        
        itemsHelperCustomer.clearForm();
    });
});



var itemsHelperCustomer = {
    inactiveItem: function () {
        var form =$("#form-customer");
        form.attr("action","/customer/inactive").submit();
//        alert( id);
    },
    activeItem: function () {
        $("#form-customer").attr("action","/customer/reactive").submit();
    },
    clearForm:function (){
        $("#id").val("");
        $("#name").val("");
        $("#lastName").val("");
        $("#status").val("");
        $("#email").val("");
        $("#address").val("");
        $("#comment").val("");
        $("#rnc").val("");
        $("#passport").val("");
        $("#phone").val("");
        $("#phone2").val("");
        $("#lastName").val("");
    }
}