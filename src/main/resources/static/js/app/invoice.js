const itemsHelperPayment = {

    id: 0,
    hasProducto: function (id) {

        var resultado = false;

        $('input[name="payment_item_id[]"]').each(function () {
            if (parseInt(id) === parseInt($(this).val())) {
                resultado = true;
            }
        });

        return resultado;
    },
//                incrementaCantidad: function (id, precio) {
//                    var cantidad = $("#ae_cost_" + id).val() ? parseInt($("#ae_cost_" + id).val()) : 0;
//                    $("#ae_cost_" + id).val(++cantidad);
//                    this.calcularImporte(id, precio, cantidad);
//                },
    delete: function (id) {
        $("#row_" + id).remove();
        this.calculateTotal();
    },
    calculateTotal: function () {
        var tempTotal = 0;

        $('input[id^="payment_value_"]').each(function () {
//var v = parseFloat($(this).inputmask('unmaskedvalue'), 10);
            var v = parseFloat($(this).val(), 10);
            if (!isNaN(v)) {
//                console.debug(v);
                tempTotal += v;
            }
        });
        $('#total_pay').html(tempTotal);
        this.calculateChange(tempTotal);

    },
    calculateChange: function (_total) {
        const totalInvoice = $('#total_invoice').inputmask('unmaskedvalue');
        const change = _total - totalInvoice;
        $('#change').html(change);

        if(change<=0){
            $('#b_payment').attr('disabled',true);
        }else{
            $('#b_payment').attr('disabled',false);
        }
    }

};

let taxesChecked = false;

function hasTaxes() {

    taxesChecked=$("#set_tax").is(":checked")
    if (taxesChecked){
        granTotalWithTaxes(taxesChecked)
        $("#total_taxes_div").show();
    }else{
        granTotalWithTaxes(taxesChecked)
        $("#total_taxes_div").hide();

    }
}

$(document).ready(function () {

    let taxes = 0.0;
    hasTaxes();

    $("#find_product").autocomplete({

        source: function (request, response) {
            $.ajax({
                url: "/invoice/loadProduct/" + request.term,
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

    $("form").submit(function () {
        $("#plantillaItemsFactura").remove();
        $("#templateItemsAdditionalExpense").remove();
        // return;
    });


    // $("form").submit(function () {
    //     $("#templateItemsAdditionalExpense").remove();
    //     return;
    // });
    
    $("button[name*='b_product']").click(function (e) {
        e.preventDefault();
        const t = $(this).text();

        $.ajax({
            url: "/invoice/loadProduct/" + t,
            dataType: "json",
            success: function (data) {
//                alert(data);
                $.map(data, function (item) {
                    addProduct(item.id,item.name,item.price);
                });
            },
        });
    });
    
    $("button[name*='b_additionalExpense']").click(function (e) {
        e.preventDefault();
        const t = $(this).text();
        $.ajax({
            url: "/invoice/loadAdditionalExpense/" + t,
            dataType: "json",
            success: function (data) {

                $.map(data, function (item) {
                    
                    addAddionalExpense(item.id,item.name,item.price);
                    
                });
            },
        });
    });

    $("#find_additional_expense").autocomplete({

        source: function (request, response) {
            $.ajax({
                url: "/invoice/loadAdditionalExpense/" + request.term,
                dataType: "json",
                data: {
                    term: request.term
                },
                success: function (data) {
                    response($.map(data, function (item) {
                        return {
                            value: item.id,
                            label: item.name,
                            cost: item.cost,
                        };
                    }));
                },
            });
        },
        select: function (event, ui) {
            
            return addAddionalExpense(ui.item.value,ui.item.label,ui.item.precio);
        },
    });

    $("#add_payment").click(function (e) {
        e.preventDefault();
        var item = $("#templateItemsPayment").html();

        item = item.replace(/{ID}/g, itemsHelperPayment.id);
        $("#loadItemPayment tbody").append(item);
        itemsHelperPayment.id++;

        return false;
    });

    $("#btn-paid").click(function (e) {
        e.preventDefault();
        $("#form-invoice").attr("action", "/invoice/payment/doPayment").submit();

        return false;
    });

    $("#b_edit_invoice").click(function (e) {
        e.preventDefault();
        location.href = "/invoice/form/edit/" + $("#id-invoice").val();
//      $("#form-invoice").attr("action","/invoice/payment/doPayment").submit();

        return false;
    });

    $("#set_tax").change( () => hasTaxes());

    $("#total_invoice").on('DOMSubtreeModified', () => {
        if(taxes===0.0)
            $.ajax({
                url: "/invoice/loadTaxes",
                dataType: "json",
                success: (d)=> taxes=d ,
            });

        let total=$("#total_invoice").html();
        total = total*taxes;
        $("#total_taxes").html(total)
    });

    $("#total_taxes").on('DOMSubtreeModified', () => {
        granTotalWithTaxes(taxesChecked);
    });
});

function addProduct(id, name, price) {
    
    if (itemsHelper.hasProducto(id)) {

        itemsHelper.incrementaCantidad(id, $("#cost_" + id).text());
        return false;
    }

    var linea = $("#plantillaItemsFactura").html();
    linea = linea.replace(/{ID}/g, id);
    linea = linea.replace(/{NOMBRE}/g, name);
    linea = linea.replace(/{PRECIO}/g, price);

    $("#cargarItemProductos tbody").append(linea);
    itemsHelper.calcularImporte(id, price, 1);
    return false;
}


function addAddionalExpense(id, name, price) {
    
         if (itemsHelperAddionalExpense.hasItem(id)) {
//                itemsHelperAddionalExpense.incrementaCantidad(ui.item.value, ui.item.precio);
                return false;
            }

    let linea = $("#templateItemsAdditionalExpense").html();

    linea = linea.replace(/{ID}/g, id);
    linea = linea.replace(/{NAME}/g, name);
    linea = linea.replace(/{COST}/g, price);

    $("#loadItemAdditionalExpense tbody").append(linea);
    itemsHelperAddionalExpense.calculateTotal();
    return false;
}

function granTotalWithTaxes(checked) {

    let total = parseFloat($("#total_invoice").html());
    let taxes = parseFloat($("#total_taxes").html());

    if(checked)
        $("#total").html(total+taxes);
    else
        $("#total").html(total);

}

var itemsHelper = {
//                quantity = 0, 
//                discount = 0.0,
//                total = 0.0,
    calcularImporte: function (_id, precio, cantidad) {
//                    var quantity = cantidad;
        const discount = $("#discount_" + _id).val();
        const total = parseInt(precio) * parseInt(cantidad);
        $("#total_importe_" + _id).html(total);
        this.calculateDiscount(_id, precio, discount);
        this.calcularGranTotal();

    },
    calculateDiscount: function (_id, precio, _discount) {
//                    this.calcularImporte(id, precio,quantity);
//                    discount = _discount;
        var quantity = $("#cantidad_" + _id).val();
        var total = parseInt(precio) * parseInt(quantity);
//                                var total=$("#total_importe_" + id).html();
        var discountTotal = parseInt(total) * (parseInt(_discount) / 100);
        $("#total_discount_" + _id).html("-" + discountTotal);
        $("#total_importe_" + _id).html((parseFloat(total) - discountTotal));
        this.calcularGranTotal();
    },
    hasProducto: function (id) {

        var resultado = false;

        $('input[name="item_id[]"]').each(function () {
            if (parseInt(id) == parseInt($(this).val())) {
                resultado = true;
            }
        });

        return resultado;
    },
    incrementaCantidad: function (id, precio) {
        var cantidad = $("#cantidad_" + id).val() ? parseInt($("#cantidad_" + id).val()) : 0;
        $("#cantidad_" + id).val(++cantidad);
        this.calcularImporte(id, precio, cantidad);
    },
    eliminarLineaFactura: function (id) {
        $("#row_" + id).remove();
        this.calcularGranTotal();
    },
    calcularGranTotal: function () {
        let tempTotal = 0;

        $('span[id^="total_importe_"]').each(function () {
            tempTotal += parseFloat($(this).html());
        });

        $('#gran_total').html(tempTotal);

        const t = parseInt($('#ae_gran_total').html());
//        alert(t);
        $('#total_invoice').html(tempTotal+t);
    }
}

var itemsHelperAddionalExpense = {
//    calcularImporte: function (_id, price) {
////                    var quantity = cantidad;                    
//        var total = parseInt(precio) ;
////        $("#ae_total").html(total);
//        this.calculateTotal();
//
//    },
//        calculateDiscount: function (_id, precio, _discount) {
////                            this.calcularImporte(id, precio,quantity);
////                    discount = _discount;
//            var quantity = $("#cantidad_" + _id).val();
//            var total = parseInt(precio) * parseInt(quantity);
////                                var total=$("#total_importe_" + id).html();
//            var discountTotal = parseInt(total) * (parseInt(_discount) / 100);
//            $("#total_discount_" + _id).html("-" + discountTotal);
//            $("#total_importe_" + _id).html((parseFloat(total) - discountTotal));
//            this.calcularGranTotal();
//        },
    hasItem: function (id) {

        var resultado = false;

        $('input[name="ae_item_id[]"]').each(function () {
            if (parseInt(id) == parseInt($(this).val())) {
                resultado = true;
            }
        });

        return resultado;
    },
//    incrementaCantidad: function (id, precio) {
//        var cantidad = $("#ae_cost_" + id).val() ? parseInt($("#ae_cost_" + id).val()) : 0;
//        $("#ae_cost_" + id).val(++cantidad);
//        this.calcularImporte(id, precio, cantidad);
//    },
    deleteItem: function (id) {
        $("#ae_row_" + id).remove();
        this.calculateTotal();
    },
    calculateTotal: function () {
        var tempTotal = 0;

        $('input[id^="ae_cost_"]').each(function () {
            var v = parseFloat($(this).val(), 10);
            if (!isNaN(v)) {
                tempTotal += v;
            }

        });

        $('#ae_gran_total').html(tempTotal);

        const t = parseInt($('#gran_total').html());
        $('#total_invoice').html(tempTotal+t);
    }
}


