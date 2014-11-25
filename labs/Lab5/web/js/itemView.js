
$(document).ready(function() {
    updateItem();
});


function updateItem() {

    var id = $('#item_id').val();
    $.get('/lab5/item/' + id + '/json', function(data) {

        var json = $.parseJSON(data);

        $('#item_description').html(json.description);
        $('#item_current_bid').html(json.current_bid);
        $('#item_number_bids').html(json.num_bids);
        $('#item_start_time').html(formatStartTime(json.start_time));
        $('#item_end_time').html(formatEndTime(json.end_time));

    });
}

function formatStartTime(startTimeMillis) {
    var nowDate = new Date();
    var nowMillis = nowDate.getTime();
    var startDate = new Date(parseInt(startTimeMillis,10));

    if (nowMillis > startTimeMillis) {
        return "Auction started at " + startDate + " (" + dDiff(nowMillis, startTimeMillis) + " ago)";
    }
    else {
        return "Auction starts at " + startDate + " (in " + dDiff(startTimeMillis, nowMillis) + ")";
    }

    return nowMillis;

}

function formatEndTime(endTimeMillis) {
    var nowDate = new Date();
    var nowMillis = nowDate.getTime();
    var endDate = new Date(parseInt(endTimeMillis,10));

    if (nowMillis < endTimeMillis) {
        return "Auction ends at " + endDate + " (in " + dDiff(endTimeMillis, nowMillis) + ")";
    }
    else {
        return "Auction has ended. (" + endDate + " - " + dDiff(nowMillis, endTimeMillis) + " ago)";;
    }

    return nowMillis;

}


function dDiff( date1, date2 ) {
    //Get 1 day in milliseconds
    var one_day=1000*60*60*24;

    // Convert both dates to milliseconds
    var date1_ms = date1;
    var date2_ms = date2;

    // Calculate the difference in milliseconds
    var difference_ms = date1_ms - date2_ms;
    //take out milliseconds
    difference_ms = difference_ms/1000;
    var seconds = Math.floor(difference_ms % 60);
    difference_ms = difference_ms/60;
    var minutes = Math.floor(difference_ms % 60);
    difference_ms = difference_ms/60;
    var hours = Math.floor(difference_ms % 24);
    var days = Math.floor(difference_ms/24);

    var retVal = "";
    retVal += (days > 0) ? days + " day" : "";
    retVal += (days > 1) ? "s" : "";
    retVal += (days > 0) ? ", " : "";

    retVal += (hours > 0) ? hours + " hour" : "";
    retVal += (hours > 1) ? "s" : "";
    retVal += (hours > 0) ? ", " : "";

    retVal += (minutes > 0) ? minutes + " minute" : "";
    retVal += (minutes > 1) ? "s" : "";
    retVal += (minutes > 0) ? ", " : "";

    retVal += (seconds > 0) ? seconds + " second" : "";
    retVal += (seconds > 1) ? "s" : "";


    return retVal;// + ": " + days + ' days, ' + hours + ' hours, ' + minutes + ' minutes, and ' + seconds + ' seconds';
}
