
/**
 * 
 * @author gdelossantos
 */


$(document).ready(function () {

//    var events = $('#events');
    var table = $('#itemsRefund').DataTable({
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
        // ,''
        itemsHelper.clearForm();
    });

    $("#find-invoice").click((e)=>{

        e.preventDefault();
        $("#items").empty();

        $.ajax({
            url: "/invoice/loadInvoice/" +getValueFromInputElement("#invoiceId")+"/customer/"+  $("#customerId").val(),
            dataType: "json",
            success: (d)=>{
                console.debug("success")
                console.debug(d)
                d.items.forEach((item)=>{
                    $("#items").append(`<option value=${item.id}  >  (${item.quantity}) ${item.description} </option>`);
                })

            },
            error:(resp)=> {
                alert(resp.responseJSON.message)
                // console.debug(xhr.responseJSON.message)

            },
        });
        // $("#meeting_ranch_location").selectpicker('refresh');

    });

    $("#items").on('change', function(){
        // e.preventDefault();

        // alert( this.value );

        $("#itemQuantity").empty();
        const qts=this.value;
        for(let i = 0; i <= qts; i++){
            $("#itemQuantity").append(`<option value=${i+1}  >   ${i+1} </option>`);
        }
    });

});

function getValueFromInputElement(elementIdentity){
    const v=$(elementIdentity).val() ;

     if(v)
         return v;
     else
         return 0;
}



var itemsHelper = {
    inactiveItem: function () {
        var form =$("#form-refund");
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
