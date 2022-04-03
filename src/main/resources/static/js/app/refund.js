
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
        $("#RNC").val(rowData[0][2]);
        $("#email").val(rowData[0][3]);
    })
    .on('deselect', function (e, dt, type, index) {
        
        itemsHelper.clearForm();
    });

    $("#find_product").click({

        source: function (request, response) {
            $.ajax({
                url: "/invoice/loadInvoice/" + request.term +"/customer/"+ request.term,
                dataType: "json",
                data: {
                    term: request.term
                },
                success: function (data) {
                    response($.map(data, function (item) {
                        return {
                            value: item.id,
                            label: item.name,
                            precio: item.price,
                        };
                    }));
                },
            });
        },
        select: function (event, ui) {
            return addProduct(ui.item.value,ui.item.label,ui.item.precio);
        }
    });

});



var itemsHelper = {
    inactiveItem: function () {
        var form =$("#form-ac");
        form.attr("action","/accountingclosing/doClose").submit();
//        alert( id);
    },
    activeItem: function () {
        $("#form-currency").attr("action","/currency/reactive").submit();
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
//itemsCurrency
