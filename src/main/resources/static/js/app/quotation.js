/**
 * 
 * @author gdelossantos
 */


$(document).ready(function () {

   var table = $('#t_quotation').DataTable({
        "scrollY": "400px",
        "scrollCollapse": true,
        "paging": false,
        select: {
            style: 'single'
        }
    });
    

    var inputCustomer=$("#find_customer");
    var customer=$("#customer");
    var form=$("#find_customer_form");
    
    inputCustomer.autocomplete({

        source: function (request, response) {
            $.ajax({
                url: "/customer/find/" + request.term,
                dataType: "json",
                data: {
                    term: request.term
                },
                success: function (data) {
                    response($.map(data, function (item) {
                        return {
                            id: item.id,
                            label: item.name,
                            rnc: item.rnc,
                        };
                    }));
                },
            });
        },
        select: function (event, ui) {

            inputCustomer.val(ui.item.label);
            customer.val(ui.item.id);
            goToCustomer(ui.item.id);

            return false;
        }
    });
    
    
    form.submit(function (e) {
       e.preventDefault();
//        goToCustomer(customer.val());
    });
    
//    button.click(function () {
//        goToCustomer(customer.val());
//    });
    
});

function goToCustomer(id){
    location.href = "/customer/ver/" + id;
}
