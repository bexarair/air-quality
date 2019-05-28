// // "use strict";
// // // $(document).ready(function() {

console.log("Javascript is online");
var currentDateURL = "http://localhost:8080/airquality/currentdate";
// var currentDateURL = "https://bexarair.com/airquality/currentdate";
var hospitalDataURL = "http://localhost:8080/hospitalrecords";
// var hospitalDataURL = "https://bexarair.com/hospitalrecords";
var geoJson = 'https://opendata.arcgis.com/datasets/4e6c13c6d8054783aaae3d3bc495bdfd_0.geojson';
var zipcodes = ["78002","78006","78009","78015","78023","78039","78052","78056","78063","78064","78065","78066","78069","78073","78101","78108","78109","78112","78114","78121","78124","78148","78150","78152","78154","78155","78163","78201","78202","78203","78204","78205","78207","78208","78209","78210","78211","78212","78213","78214","78215","78216","78217","78218","78219","78220","78221","78222","78223","78224","78225","78226","78227","78228","78229","78230","78231","78232","78233","78234","78235","78236","78237","78238","78239","78240","78242","78244","78245","78247","78248","78249","78250","78251","78252","78253","78254","78255","78256","78257","78258","78259","78260","78261","78263","78264","78266"];
var testZip = ["78002", "78006", "78009", "78015", "78023"];
var restZipArray = [];
var restAqiArray = [];
var heatmapData = [{}];
var googleData;

var restHospitalArray =[];
var restHospitalRateArray = [];
var map;




/************** AirQuality Record call to REST API ******************/
$.get(currentDateURL).done(function(airInfo){
    for(var i = 0; i < zipcodes.length; i++){
        restZipArray.push(airInfo[i].zipCode);
        restAqiArray.push(airInfo[i].aqi);
    }
});

/********* Hospital Record call to REST API *************************/
$.get(hospitalDataURL).done(function(hospitalData) {
    for(var i = 0; i < zipcodes.length; i++){
        restHospitalArray.push(hospitalData[i].zipcode);
        restHospitalRateArray.push(hospitalData[i].pedi_asthma_rate);
        console.log("hospital data from rest api call: " + restHospitalArray)
    }
});




/**************************** MAP STUFF*****************************/
function initMap() {
    map = new google.maps.Map(document.getElementById('map'), {
        zoom: 9,
        center: {lat: 29.4241, lng: -98.4936}

    });


    // NOTE: This uses cross-domain XHR, and may not work on older browsers.
    map.data.loadGeoJson(geoJson);


    console.log("THis is the length of the array: " + restZipArray[restZipArray.length-1]);
var delay2 = 2000; // delay time in milliseconds
        setTimeout(function () {
        // for(var j = 0; j < zipcodes.length; j++) {
            map.data.setStyle(function(data) {
                for (var i = 0; i < 87; i++) {
                    console.log("INSIDE THE FIRST LOOP: " + restZipArray);
                    console.log("Indiv zip: " + restZipArray[i]);
                    console.log("Google Zips: " + data.getProperty('ZIP'));
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
        // }
    }, delay2);

    googleData = [
        {location: new google.maps.LatLng(29.6172, -98.7261), zip: 78023},
        {location: new google.maps.LatLng(29.6451, -98.4733), zip: 78258},
        {location: new google.maps.LatLng(29.5517, -98.4952), zip: 78216},
        {location: new google.maps.LatLng(29.3978, -98.7371), zip: 78245},
        {location: new google.maps.LatLng(29.4854, -98.4513), zip: 78209},
        {location: new google.maps.LatLng(29.5676, -98.6051), zip: 78249},
        {location: new google.maps.LatLng(29.5857, -98.4129), zip: 78247},
        {location: new google.maps.LatLng(29.5601, -98.3636), zip: 78233},
        {location: new google.maps.LatLng(29.4476, -98.3198), zip: 78109},
        {location: new google.maps.LatLng(29.6172, -98.7261), zip: 78023},
        {location: new google.maps.LatLng(29.5330, -98.7812), zip: 78254},
        {location: new google.maps.LatLng(29.5420, -98.5556), zip: 78230},
        {location: new google.maps.LatLng(29.4733, -98.8142), zip: 78253},
        {location: new google.maps.LatLng(29.5944, -98.4568), zip: 78232},
        {location: new google.maps.LatLng(29.7487, -98.6491), zip: 78015},
        {location: new google.maps.LatLng(29.5886, -98.2760), zip: 78154},
        {location: new google.maps.LatLng(29.4636, -98.5227), zip: 78201},
        {location: new google.maps.LatLng(29.5270, -98.6106), zip: 78240},
        {location: new google.maps.LatLng(29.5061, -98.5776), zip: 78229},
        {location: new google.maps.LatLng(29.4671, -98.6766), zip: 78251},
        {location: new google.maps.LatLng(29.3038, -98.4075), zip: 78223},
        {location: new google.maps.LatLng(29.4896, -98.3855), zip: 78218},
        {location: new google.maps.LatLng(29.4104, -98.6326), zip: 78227},
        {location: new google.maps.LatLng(29.5390, -98.4239), zip: 78217},
        {location: new google.maps.LatLng(29.4175, -98.5227), zip: 78207},
        {location: new google.maps.LatLng(29.4596, -98.4952), zip: 78212},
        {location: new google.maps.LatLng(29.4553, -98.5611), zip: 78228},
        {location: new google.maps.LatLng(29.5004, -98.6656), zip: 78250},
        {location: new google.maps.LatLng(29.4687, -98.6216), zip: 78238},
        {location: new google.maps.LatLng(29.5201, -98.3581), zip: 78239},
        {location: new google.maps.LatLng(29.4981, -98.5227), zip: 78213},
        {location: new google.maps.LatLng(29.4747, -98.3472), zip: 78244},
        {location: new google.maps.LatLng(29.6897, -98.4952), zip: 78260},
        {location: new google.maps.LatLng(29.6249, -98.4294), zip: 78259},
        {location: new google.maps.LatLng(29.4227, -98.4870), zip: 78205},
        {location: new google.maps.LatLng(29.3980, -98.4678), zip: 78210},
        {location: new google.maps.LatLng(29.2436, -98.6271), zip: 78073},
        {location: new google.maps.LatLng(29.2982, -98.4952), zip: 78221},
        {location: new google.maps.LatLng(29.4489, -98.3910), zip: 78219},
        {location: new google.maps.LatLng(29.1706, -98.5062), zip: 78264},
        {location: new google.maps.LatLng(29.4302, -98.4596), zip: 78202},
        {location: new google.maps.LatLng(29.3744, -98.3855), zip: 78222},
        {location: new google.maps.LatLng(29.3173, -98.4678), zip: 78214},
        {location: new google.maps.LatLng(29.3185, -98.5391), zip: 78224},
        {location: new google.maps.LatLng(29.4143, -98.3910), zip: 78220},
        {location: new google.maps.LatLng(29.6611, -98.5831), zip: 78257},
        {location: new google.maps.LatLng(29.4147, -98.5666), zip: 78237},
        {location: new google.maps.LatLng(29.3542, -98.6106), zip: 78242},
        {location: new google.maps.LatLng(29.4043, -98.5035), zip: 78204},
        {location: new google.maps.LatLng(29.3455, -98.5666), zip: 78211},
        {location: new google.maps.LatLng(29.5929, -98.5254), zip: 78248}

    ];


    // console.log("This is in the map");
    //
    // console.log("Look at this hardcoded googleData3: " + googleData[3].zip);
    //
    setTimeout(function() {
    console.log("Hospital Data array that stuff should be in: " + restHospitalArray[0]);
    console.log("Hospital Data array that stuff should be in: " + restHospitalArray[1]);
    console.log(restHospitalArray.length);
        for (var i = 0; i < restHospitalArray.length; i++) {
            var hospitalZip = restHospitalArray[i];
                console.log("hospital ZIP code data: " + restHospitalArray[i]);

            for (var j = 0; j < googleData.length; j++) {
                console.log("GOOGLE " + googleData[j].zip);

                if (hospitalZip === googleData[j].zip) {
                    console.log("This is the googleData stuff : " + googleData[j]);
                    heatmapData.push(googleData[j])
                }
            }
        }
    }, 10000);


    // look through all the hospital record zip codes individual....

        // compare the hospital record zip codes with all the bexar county zip codes

            // if they match, push them into a heat map array to display on the map










// for(var i = 0; i < restHospitalZip.length; i++){
//     if(heatmapData[i].zip === restHospitalZip[i]){
//         if(restHospitalRate[i] < 11){
//             heatmapData[i].weight = restHospitalRate[i];
//         }else if(restHospitalRate[i] >= 11 && restHospitalRate[i] < 20 ){
//             heatmapData[i].weight = restHospitalRate[i]
//         }else if(restHospitalRate[i] >= 21 && restHospitalRate[i] < 30 ){
//             heatmapData[i].weight = restHospitalRate[i]
//         }else if(restHospitalRate[i] >= 31 && restHospitalRate[i] < 40 ){
//             heatmapData[i].weight = restHospitalRate[i]
//         }else if(restHospitalRate[i] >= 41 && restHospitalRate[i] < 50){
//             heatmapData[i].weight = restHospitalRate[i];
//         } else if(restHospitalRate[i] > 50){
//             heatmapData[i].weight = restHospitalRate[i];
//         }
//     }
// }


    var heatmap = new google.maps.visualization.HeatmapLayer({
        data: heatmapData

    });

    heatmap.setMap(map);
    heatmap.set('radius', 30);
    heatmap.set('maxIntensity', 3)


}
/************************************&*****END OF MAP**************************************/




