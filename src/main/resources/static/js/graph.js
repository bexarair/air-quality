var testUrl = "http://localhost:8080/airquality/user/1";
var userId;





// Load the Visualization API and the corechart package.
google.charts.load('current', {'packages':['corechart']});

// Set a callback to run when the Google Visualization API is loaded.
google.charts.setOnLoadCallback(drawChart);

// Callback that creates and populates a data table,
// instantiates the line chart, passes in the data and
// draws it.
function drawChart() {
    var userAqi;
    $.get(testUrl).done(function(userAqiRecord) {
        console.log(userAqiRecord);
        console.log(userAqiRecord.length);
        for(var i = 0; i < userAqiRecord.length; i++) {
            userAqi = userAqiRecord[i].aqi;
            console.log(userAqiRecord[i].aqi);


            console.log("Is this doing anything?");
            // var userAqi = userAqiRecord.aqi;


            // Create the data table.
            var data = new google.visualization.DataTable();
            data.addColumn('string', 'Date'); //
            data.addColumn('number', 'Location 1'); //Air Quality, location
            data.addColumn('number', 'Location 2');
            data.addColumn('number', 'Location 3');
            data.addRows([
                [testUrl[i].]
                ['2018/05/14', 3.4, 8.2, 4.3]
                ['2018/05/15', 2.4, 6.4, 8.6],
                ['2018/05/16', 4.4, 6.5, 8.8],
                ['2018/05/17', 3.4, 4.2, 4.1],
                ['2018/05/18', userAqi, userAqi, userAqi]   //date, AQI for user location
            ]);

            // Set chart options
            var options = {
                'title': 'Your Air Quality Over Time',
                'width': 1000,
                'height': 1000,
                hAxis: {
                    title: 'Date'
                },
                vAxis: {
                    title: 'Air Quality'
                }

            };

            // Instantiate and draw our chart, passing in some options.
            var chart = new google.visualization.LineChart(document.getElementById('chart_div'));
            chart.draw(data, options);
        }
});
}