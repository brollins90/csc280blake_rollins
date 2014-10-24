
$(document).ready(function() {
    updateItem();
});


function updateItem() {

    var id = $('#item_id').val();
    $.get('/lab4/item/' + id + '/json', function(data) {

        var json = $.parseJSON(data);

        $('#item_description').html(json.description);
        $('#item_current_bid').html(json.current_bid);
        $('#item_number_bids').html(json.num_bids);
        $('#item_start_time').html(json.start_time + " - " + formatStartTime(json.start_time));
        $('#item_end_time').html(json.end_time + " - " + formatEndTime(json.end_time));

    });
}

function formatStartTime(otherTime) {
    var now = new Date();
    var nowMil = now.getTime();
    var other = new Date(otherTime);

    if (nowMil > otherTime) {
        return "Auction started at " + otherTime + " " + other;
    }
    else {
        return "Auction starts at " + other + " (" + dDiff(now, otherTime) + ")";
    }

    return now;

}

function formatEndTime(otherTime) {
    var now = Date.now();

    if (now < otherTime) {
        return "Auction ends at " + new Date(otherTime) + " (" + dDiff(now, otherTime) + ")";
    }
    else {
        return "Auction has ended. (" + new Date(otherTime) + ")";
    }

    return now;

}

function dDiff( date1, date2 ) {
    //Get 1 day in milliseconds
    var one_day=1000*60*60*24;

    // Convert both dates to milliseconds
    var date1_ms = date1;
    var date2_ms = date2;

    // Calculate the difference in milliseconds
    var difference_ms = date2_ms - date1_ms;
    //take out milliseconds
    difference_ms = difference_ms/1000;
    var seconds = Math.floor(difference_ms % 60);
    difference_ms = difference_ms/60;
    var minutes = Math.floor(difference_ms % 60);
    difference_ms = difference_ms/60;
    var hours = Math.floor(difference_ms % 24);
    var days = Math.floor(difference_ms/24);

    var retVal = "";
    retVal += (days >= 0) ? days : "";
    retVal += (days >= 0) ? " day" : "";
    retVal += (days > 0) ? "s" : "";


    return retVal + ": " + days + ' days, ' + hours + ' hours, ' + minutes + ' minutes, and ' + seconds + ' seconds';
}
