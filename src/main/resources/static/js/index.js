// // "use strict";
// // // $(document).ready(function() {
console.log("Javascript is online");
var currentURL = "http://localhost:8080/airquality/currentdate.geojson";
var geoJson = 'https://opendata.arcgis.com/datasets/4e6c13c6d8054783aaae3d3bc495bdfd_0.geojson';
var zipcodes = ["78002","78006","78009","78015","78023","78039","78052","78056","78063","78064","78065","78066","78069","78073","78101","78108","78109","78112","78114","78121","78124","78148","78150","78152","78154","78155","78163","78201","78202","78203","78204","78205","78207","78208","78209","78210","78211","78212","78213","78214","78215","78216","78217","78218","78219","78220","78221","78222","78223","78224","78225","78226","78227","78228","78229","78230","78231","78232","78233","78234","78235","78236","78237","78238","78239","78240","78242","78244","78245","78247","78248","78249","78250","78251","78252","78253","78254","78255","78256","78257","78258","78259","78260","78261","78263","78264","78266"];
var testZip = ["78002", "78006", "78009", "78015", "78023"];
var restZipArray = [];
var restAqiArray = [];
var map;




/************** MAP INFORMATION ******************/
$.get(currentURL).done(function(airInfo){
    for(var i = 0; i < zipcodes.length; i++){
        restZipArray.push(airInfo[i].zipCode);
        restAqiArray.push(airInfo[i].aqi);
    }
});


function initMap() {
    map = new google.maps.Map(document.getElementById('map'), {
        zoom: 9,
        center: {lat: 29.4241, lng: -98.4936}


    });


    // NOTE: This uses cross-domain XHR, and may not work on older browsers.
    map.data.loadGeoJson(geoJson);



var delay2 = 500; // delay time in milliseconds
        setTimeout(function () {
        for(var j = 0; j < restZipArray.length; j++) {
            map.data.setStyle(function (data) {
                for (var i = 0; i < restZipArray.length; i++) {
                    var color;
                    if (restZipArray[i] === data.getProperty('ZIP')) {
                        if (restAqiArray[i] >= 0 && restAqiArray[i] <= 50) {
                            color = "green";
                        } else if (restAqiArray[i] >= 51 && restAqiArray[i] <= 100) {
                            color = "yellow";
                        }else if(restAqiArray[i] >= 101 && restAqiArray[i] <= 150){
                            color= "#cc5500";
                        }else if(restAqiArray[i] >= 151 && restAqiArray[i] <= 200){
                            color= "red";
                        }else if(restAqiArray[i] >= 201 && restAqiArray[i] <= 300){
                            color="purple";
                        }else if(restAqiArray[i] >= 301 && restAqiArray[i] <= 500){
                            color="maroon";
                        }
                    }
                }
                return {
                    fillColor: color,
                    strokeWeight: 1
                };
            });
        }
    }, delay2);
}
/************************************&*****END OF MAP**************************************/

