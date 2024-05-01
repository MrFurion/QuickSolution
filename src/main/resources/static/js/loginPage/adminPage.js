$(document).ready(function() {
    $('.update-btn').click(function() {
        var orderId = $(this).closest('.order-item').data('order-id');
        var newStatus = $(this).closest('.order-item').find('.status-select').val();

        $.post('/admin/updateOrderStatus', {orderId: orderId, newStatus: newStatus})
            .done(function() {
                $.get('/admin/findAllOrders', function(data) {
                    $('#orderDetails').html($(data).find('#orderDetails').html());
                });
            })
            .fail(function() {
                alert('Failed to update order status');
            });
    });
});
$(document).ready(function() {
    $('tbody').on('click', '.update-btn', function() { // Привязываем обработчик события click к tbody, делегируя его к элементам с классом .update-btn
        var orderId = $(this).closest('.order-item').data('order-id');
        var newStatus = $(this).closest('.order-item').find('.status-select').val();

        $.post('/admin/updateOrderStatus', {orderId: orderId, newStatus: newStatus})
            .done(function() {
                $.get('/admin/findAllOrders', function(data) {
                    $('#orderDetails').html($(data).find('#orderDetails').html());
                });
            })
            .fail(function() {
                alert('Failed to update order status');
            });
    });
});