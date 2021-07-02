/**
 * 
 * @author gdelossantos
 */


$(document).ready(function () {

//    var events = $('#events');
    var table = $('#itemsNcf').DataTable({
        "scrollY": "400px",
        "scrollCollapse": true,
        "paging": false
//        select: {
//            style: 'single'
//        }

    });

    $("#btn-save").click(function (e) {
        e.preventDefault();
        $("#form-ncf").attr("action", "/ncf/save").submit();
        return false;
    });

    $("#btn-clear").click(function (e) {
        e.preventDefault();
        $("#form-ncf").attr("action", "/ncf/clear").submit();
        return false;
    });

}); 

var itemsHelperNcf = {
    deleteItem: function (id) {
//                e.preventDefault();
        location.href = "/ncf/delete/" + id;
//                $.get()({
//                    url: "/ncf/delete/"+id ,
//                        dataType: "json",
//                        data: {
//                            id: id
//                        },
//                        success: function (data) {
//                            
//                            
//                        }
//                });
    }
}