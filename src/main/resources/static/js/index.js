// "use strict";
// // $(document).ready(function() {
//
//
//
// // Initialize and add the map
//     function initMap() {
//         // The location of Uluru
//         var uluru = {lat: 29.4241, lng: -98.4936};
//         // The map, centered at Uluru
//         var map = new google.maps.Map(
//             document.getElementById('map'), {zoom: 10, center: uluru});
//         // The marker, positioned at Uluru
//         var marker = new google.maps.Marker({position: uluru, map: map});
//
//
//
//
//
//
// // Define the LatLng coordinates for the polygon's path.
// var triangleCoords = [
//     {lat: 29.320956999697216, lng: -98.7802470005868},
//     {lat: 29.321121000046546, lng: -98.78026099985534},
//     {lat: 29.321208000412067, lng: -98.78026799908251},
//     {lat: 29.32149500025275, lng: -98.78029299989646},
//
//     {lat: 29.321842999976614, lng: -98.78030899981705},
//     {lat: 29.322318000011, lng: -98.78030200045468},
//     {lat: 29.325253000021547, lng: -98.78032000054976},
//     {lat: 29.326627000110758, lng: -98.78032899958052},
//
//     {lat: 29.327068999961632, lng: -98.78032400035016},
//     {lat: 29.327125999893024, lng: -98.78030899921716},
//     {lat: 29.327228000547496, lng: -98.780206000367},
//     {lat: 29.327253999627423, lng: -98.78013899953804},
//
//     {lat: 29.32726900028189, lng: -98.7800320004162},
//     {lat: 29.32727699992795, lng: -98.77983099971846},
//     {lat: 29.327286999843846, lng: -98.77968200005182},
//     {lat: 29.327294999228084, lng: -98.77962100067538},
//     {lat: 29.32731800002821, lng: -98.77956899937801}
// ];
//
// // Construct the polygon.
//
// var bermudaTriangle = new google.maps.Polygon({
//     paths: triangleCoords,
//     strokeColor: '#FF0000',
//     strokeOpacity: 0.8,
//     strokeWeight: 2,
//     fillColor: '#FF0000',
//     fillOpacity: 0.35
// });
// bermudaTriangle.setMap(map);
// }
var currentURL = "http://localhost:8080/airquality";


var zipcodes = ["78002","78006","78009","78015","78023","78039","78052","78056","78063","78064","78065","78066","78069","78073","78101","78108","78109","78112","78114","78121","78124","78148","78150","78152","78154","78155","78163","78201","78202","78203","78204","78205","78207","78208","78209","78210","78211","78212","78213","78214","78215","78216","78217","78218","78219","78220","78221","78222","78223","78224","78225","78226","78227","78228","78229","78230","78231","78232","78233","78234","78235","78236","78237","78238","78239","78240","78242","78244","78245","78247","78248","78249","78250","78251","78252","78253","78254","78255","78256","78257","78258","78259","78260","78261","78263","78264","78266"];
var testZip = ["78002", "78006", "78009", "78015", "78023"];

var airQualityName;
var airQuality1;
var airQuality2;
var airQualityAvg;

$.get(currentURL).done(function(airInfo){
        for(var i = 0; i < zipcodes.length; i++){
        // console.log(airInfo[0]);
        // console.log("airInfo" + airInfo);
        airQualityName = airInfo[0];

        console.log(airInfo[i]);
        console.log("air quality category: " + airInfo[i].categoryName);
            console.log("air quality: " + airInfo[i].aqi);

        // airQuality1 = airInfo[0].AQI;
        // airQuality2 = airInfo[1].AQI;
        airQualityAvg = (airQuality1+airQuality2)/2;

        // console.log(airQualityAvg);
    }
});


// var airColorObj = [
//     {
//         condition: "Good",
//         color: "green"},
//     {
//         condition: "Moderate",
//         color: "yellow"},
//     {
//         condition: "Unhealthy for Sensitive Groups",
//         color: "orange"},
//     {
//         condition: "Unhealthy",
//         color: "red"},
//     {
//         condition: "Very Unhealthy",
//         color: "magenta"},
//     {
//         condition: "Hazardous",
//         color: "maroon"}
//     ];
//
// function showAirColor(airQualityName){
//     for(var i = 0; i<=airColorObj.length; i++){
//         if(airQualityName === airColorObj[i].condition){
//             // $('body, html').css('background-image', weatherObj[i].backPic);
//             return "<img src=" + weatherObj[i].url + ">";
//         }
//     }
// }




console.log("Javascript is online");
var map;
function initMap() {
    map = new google.maps.Map(document.getElementById('map'), {
        zoom: 9,
        center: {lat: 29.4241, lng: -98.4936}

        // mapTypeControlOptions: {
        //     mapTypeIds: ['satellite']
        // }
    });

// grabs the properties in the geoJson data
//     this is specifically grabbing the ZIP property and checking if it 78002.  If it is, it turns red.  Otherwise Blue
    map.data.setStyle(function(feature) {
        var zipCode = feature.getProperty('ZIP');
        var color;
        if (airQualityAvg >= 0 && airQualityAvg <= 50){
            color = "green";
        }else if(airQualityAvg >= 51 && airQualityAvg <= 100){
            color = "yellow";
        }else if(airQualityAvg >= 101 && airQualityAvg <= 150){
            color = "orange";
        }else if(airQualityAvg >= 151 && airQualityAvg <= 200){
            color = "red";
        }else if(airQualityAvg >= 201 && airQualityAvg <= 300){
            color = "magenta";
        }else if(airQualityAvg >= 301 && airQualityAvg <= 500){
            color = "maroon";
        }else{
            color = "black";
        }
        // var color = ascii === "78002" ? 'red' : 'blue';
        return {
            fillColor: color,
            strokeWeight: 1
        };
    });


    // map.data.setStyle(function(feature) {
    //     var zipCode = feature.getProperty('ZIP');
    //     var color;
    //     if (zipCode.includes("1")){
    //         color = "green";
    //     }else if(zipCode.includes("9")){
    //         color = "yellow";
    //     }else if(zipCode.includes("0")){
    //         color = "orange";
    //     }else if(zipCode.includes("5")){
    //         color = "red";
    //     }else if(zipCode.includes("4") === "Very Unhealthy"){
    //         color = "magenta";
    //     }else if(zipCode.includes("6")){
    //         color = "maroon";
    //     }else{
    //         color = "black";
    //     }
    //     // var color = ascii === "78002" ? 'red' : 'blue';
    //     return {
    //         fillColor: color,
    //         strokeWeight: 1
    //     };
    // });



    // NOTE: This uses cross-domain XHR, and may not work on older browsers.
    map.data.loadGeoJson(
        'https://opendata.arcgis.com/datasets/4e6c13c6d8054783aaae3d3bc495bdfd_0.geojson'
    );
    // var triangleCoords = [
    //     {lat: 25.774, lng: -80.190},
    //     {lat: 18.466, lng: -66.118},
    //     {lat: 32.321, lng: -64.757},
    //     {lat: 25.774, lng: -80.190}
    // ];





}


google.charts.load('current', {packages: ['corechart', 'line']});
google.charts.setOnLoadCallback(drawCurveTypes);

function drawCurveTypes() {
    var data = new google.visualization.DataTable();
    data.addColumn('number', 'X');
    data.addColumn('number', 'Dogs');
    data.addColumn('number', 'Cats');

    data.addRows([
        [0, 0, 0],    [1, 10, 5],   [2, 23, 15],  [3, 17, 9],   [4, 18, 10],  [5, 9, 5],
        [6, 11, 3],   [7, 27, 19],  [8, 33, 25],  [9, 40, 32],  [10, 32, 24], [11, 35, 27],
        [12, 30, 22], [13, 40, 32], [14, 42, 34], [15, 47, 39], [16, 44, 36], [17, 48, 40],
        [18, 52, 44], [19, 54, 46], [20, 42, 34], [21, 55, 47], [22, 56, 48], [23, 57, 49],
        [24, 60, 52], [25, 50, 42], [26, 52, 44], [27, 51, 43], [28, 49, 41], [29, 53, 45],
        [30, 55, 47], [31, 60, 52], [32, 61, 53], [33, 59, 51], [34, 62, 54], [35, 65, 57],
        [36, 62, 54], [37, 58, 50], [38, 55, 47], [39, 61, 53], [40, 64, 56], [41, 65, 57],
        [42, 63, 55], [43, 66, 58], [44, 67, 59], [45, 69, 61], [46, 69, 61], [47, 70, 62],
        [48, 72, 64], [49, 68, 60], [50, 66, 58], [51, 65, 57], [52, 67, 59], [53, 70, 62],
        [54, 71, 63], [55, 72, 64], [56, 73, 65], [57, 75, 67], [58, 70, 62], [59, 68, 60],
        [60, 64, 56], [61, 60, 52], [62, 65, 57], [63, 67, 59], [64, 68, 60], [65, 69, 61],
        [66, 70, 62], [67, 72, 64], [68, 75, 67], [69, 80, 72]
    ]);

    var options = {
        hAxis: {
            title: 'Time'
        },
        vAxis: {
            title: 'Popularity'
        },
        series: {
            1: {curveType: 'function'}
        }
    };

    var chart = new google.visualization.LineChart(document.getElementById('chart_div'));
    chart.draw(data, options);
}




//
// "use strict";
// // $(document).ready(function() {
//
//
//
// // Initialize and add the map
// function initMap() {
//     // The location of Uluru
//     var uluru = {lat: 29.4241, lng: -98.4936};
//     // The map, centered at Uluru
//     var map = new google.maps.Map(
//         document.getElementById('map'), {zoom: 10, center: uluru});
//     // The marker, positioned at Uluru
//     var marker = new google.maps.Marker({position: uluru, map: map});
//
//
//
//
//
//
//
// // Define the LatLng coordinates for the polygon's path.
//     var triangleCoords = [
//         {lat: 25.774, lng: -80.190},
//         {lat: 18.466, lng: -66.118},
//         {lat: 32.321, lng: -64.757},
//         {lat: 25.774, lng: -80.190}
//     ];
//
// // Construct the polygon.
//
//     var bermudaTriangle = new google.maps.Polygon({
//         paths: triangleCoords,
//         strokeColor: '#FF0000',
//         strokeOpacity: 0.8,
//         strokeWeight: 2,
//         fillColor: '#FF0000',
//         fillOpacity: 0.35
//     });
//     bermudaTriangle.setMap(map);
// }