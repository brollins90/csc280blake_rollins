Date.prototype.addHours= function(h){
    this.setHours(this.getHours()+h);
    return this;
};

$(document).ready(function() {

    loadPage2();
});

function updateMilliBoxes() {
    var startDateTextBox = $('#item_start_time');
    var startDateTextBoxLong = $('#item_start_time_long');
    var endDateTextBox = $('#item_end_time');
    var endDateTextBoxLong = $('#item_end_time_long');

    startDateTextBoxLong.val(new Date(startDateTextBox.datetimepicker('getDate')).getTime());
    endDateTextBoxLong.val(new Date(endDateTextBox.datetimepicker('getDate')).getTime());

}



function updateDateBoxes() {
    var startDateTextBox = $('#item_start_time');
    var startDateTextBoxLong = $('#item_start_time_long');
    var endDateTextBox = $('#item_end_time');
    var endDateTextBoxLong = $('#item_end_time_long');

    startDateTextBox.datetimepicker('setDate', new Date(parseInt(startDateTextBoxLong.val(), 10)));
    endDateTextBox.datetimepicker('setDate', new Date(parseInt(endDateTextBoxLong.val(), 10)));

}



function loadPage2() {
    var startDateTextBox = $('#item_start_time');
    var endDateTextBox = $('#item_end_time');

    startDateTextBox.datetimepicker({
        minDate: new Date(),
        showOn: 'button',
        buttonImage: "http://jqueryui.com/resources/demos/datepicker/images/calendar.gif",
        buttonImageOnly: true,
        onClose: function (dateText, inst) {
            updateMilliBoxes();
        },
        onSelect: function (selectedDateTime) {
            endDateTextBox.datetimepicker('option', 'minDate', startDateTextBox.datetimepicker('getDate'));
        }
    }).next('button').text('').button({ icons: { primary: 'ui-icon-calendar' } });

    endDateTextBox.datetimepicker({
        showOn: "button",
        buttonImage: "http://jqueryui.com/resources/demos/datepicker/images/calendar.gif",
        buttonImageOnly: true,
        onClose: function (dateText, inst) {
            updateMilliBoxes();
        },
        onSelect: function (selectedDateTime) {
        }
    }).next('button').text('').button({ icons: { primary: 'ui-icon-calendar' } });

    updateDateBoxes();

}
