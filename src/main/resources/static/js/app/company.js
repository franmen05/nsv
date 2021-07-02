/**
 * 
 * @author gdelossantos
 */


$(document).ready(function () {

//    var events = $('#events');
    var table = $('#itemsCompany').DataTable({
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
        $("#RNC").val(rowData[0][2]);
        $("#email").val(rowData[0][3]);
        $("#status").val(rowData[0][4]);
        $("#createDate").val(rowData[0][5]);
        $("#lastUpdateDate").val(rowData[0][6]);
        $("#address").val(rowData[0][7]);
        $("#comment").val(rowData[0][8]);
    })
    .on('deselect', function (e, dt, type, index) {
        
        itemsHelperCompany.clearForm();
    });
});



var itemsHelperCompany = {
    inactiveItem: function () {
        var form =$("#form-company");
        form.attr("action","/company/inactive").submit();
//        alert( id);
    },
    activeItem: function () {
        $("#form-company").attr("action","/company/reactive").submit();
    },
    clearForm:function (){
        $("#id").val("");
        $("#name").val("");
        $("#RNC").val("");
//        $("#createDate").val("");
        $("#status").val("");
        $("#email").val("");
        $("#address").val("");
        $("#comment").val("");
    }
}