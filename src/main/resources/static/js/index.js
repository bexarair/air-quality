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

console.log("Javascript is online");
var map;
function initMap() {
    map = new google.maps.Map(document.getElementById('map'), {
        zoom: 9,
        center: {lat: 29.4241, lng: -98.4936},

        // mapTypeControlOptions: {
        //     mapTypeIds: ['satellite']
        // }
    });

    // NOTE: This uses cross-domain XHR, and may not work on older browsers.
    map.data.loadGeoJson(
        'https://opendata.arcgis.com/datasets/4e6c13c6d8054783aaae3d3bc495bdfd_0.geojson');





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