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

function loadPage2() {
    var startDateTextBox = $('#item_start_time');
    var endDateTextBox = $('#item_end_time');

    startDateTextBox.datetimepicker({
        dateFormat: 'dd M yy',
        timeFormat: 'HH:mm',
        minDate: new Date(),
        onClose: function (dateText, inst) {
//            if (endDateTextBox.val() != '') {
//                var testStartDate = startDateTextBox.datetimepicker('getDate');
//                var testEndDate = endDateTextBox.datetimepicker('getDate');
//                if (testStartDate > testEndDate) {
//                    var newEndDateOneHourLater = startDateTextBox.datetimepicker('getDate').addHours(1);
//                    endDateTextBox.datetimepicker('setDate', newEndDateOneHourLater);
//                }
//            }
//            else {
//                endDateTextBox.val(dateText);
//            }

            updateMilliBoxes();
        },
        onSelect: function (selectedDateTime) {
//            startDateTextBox.datetimepicker('option', 'minDate', new Date());
            endDateTextBox.datetimepicker('option', 'minDate', startDateTextBox.datetimepicker('getDate'));
//            startDateTextBox.datetimepicker('setDate', new Date(Number.parse($('#item_start_time_long').val())));
//            endDateTextBox.datetimepicker('setDate', new Date(Number.parse($('#item_end_time_long').val())));
        }
    });
    endDateTextBox.datetimepicker({
        dateFormat: 'dd M yy',
        timeFormat: 'HH:mm',
        onClose: function (dateText, inst) {
//            if (startDateTextBox.val() != '') {
//                var testStartDate = startDateTextBox.datetimepicker('getDate');
//                var testEndDate = endDateTextBox.datetimepicker('getDate');
//                if (testStartDate > testEndDate)
//                    startDateTextBox.datetimepicker('setDate', testEndDate);
//            }
//            else {
//                startDateTextBox.val(dateText);
//            }
            updateMilliBoxes();
        },
        onSelect: function (selectedDateTime) {
////            startDateTextBox.datetimepicker('option', 'maxDate', endDateTextBox.datetimepicker('getDate'));
//            startDateTextBox.datetimepicker('setDate', new Date(Number.parse($('#item_start_time_long').val())));
//            endDateTextBox.datetimepicker('setDate', new Date(Number.parse($('#item_end_time_long').val())));
        }
    });
    endDateTextBox.datetimepicker('setDate', new Date(parseInt($('#item_start_time_long').val(), 10)));

//    startDateTextBox.datetimepicker('setDate', new Date(Number.parse($('#item_start_time_long'))));
//    endDateTextBox.datetimepicker('setDate', new Date(Number.parse($('#item_end_time_long').val())));
}
