//Date.prototype.addHours = function (h) {
//    this.setHours(this.getHours() + h);
//    return this;
//};

$(document).ready(function () {

    loadPage2();

    //hang on event of form with id=myform
    $("#form").submit(function(e) {

        //prevent Default functionality
        e.preventDefault();

        //get the action-url of the form
        var actionurl = e.currentTarget.action;

        //do your own request an handle the results
        //$.ajax({
        //    url: actionurl,
        //    type: 'post',
        //    dataType: 'json',
        //    data: $("#form").serialize(),
        //    success: function(data) {
        //        //... do something with the data...
        //    }
        //});

    });
});

function updateMilliBoxes() {
    var startDateTextBox = $('#start_time');
    var startDateTextBoxLong = $('#start_time_long');
    var endDateTextBox = $('#end_time');
    var endDateTextBoxLong = $('#end_time_long');

    startDateTextBoxLong.val(new Date(startDateTextBox.datetimepicker('getDate')).getTime());
    endDateTextBoxLong.val(new Date(endDateTextBox.datetimepicker('getDate')).getTime());

}


function updateDateBoxes() {
    var startDateTextBox = $('#start_time');
    var startDateTextBoxLong = $('#start_time_long');
    var endDateTextBox = $('#end_time');
    var endDateTextBoxLong = $('#end_time_long');

    startDateTextBox.datetimepicker('setDate', new Date(parseInt(startDateTextBoxLong.val(), 10)));
    endDateTextBox.datetimepicker('setDate', new Date(parseInt(endDateTextBoxLong.val(), 10)));

}


function loadPage2() {
    var startDateTextBox = $('#start_time');
    var endDateTextBox = $('#end_time');

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
