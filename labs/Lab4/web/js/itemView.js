
$(document).ready(function() {
    updateItem();
});


function updateItem() {
    var item_description = $('#item_description');
    var item_current_bid = $('#item_current_bid');
    var item_number_bids = $('#item_number_bids');
    var item_start_time = $('#item_start_time');
    var item_end_time = $('#item_end_time');





    item_start_time.html('BLAKE');
    var id = $('#item_id').val();
    item_end_time.html(id);

}
