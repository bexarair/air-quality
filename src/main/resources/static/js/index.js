// // "use strict";
// // // $(document).ready(function() {
/*global google*/
console.log("Javascript is online");
// var currentDateURL = "http://localhost:8080/airquality/currentdate.geojson";
var currentDateURL = "https://bexarair.com/airquality/currentdate";
// var hospitalDataURL = "http://localhost:8080/hospitalrecords";
var hospitalDataURL = "https://bexarair.com/hospitalrecords";
var geoJson = 'https://opendata.arcgis.com/datasets/4e6c13c6d8054783aaae3d3bc495bdfd_0.geojson';
var zipcodes = ["78002","78006","78009","78015","78023","78039","78052","78056","78063","78064","78065","78066","78069","78073","78101","78108","78109","78112","78114","78121","78124","78148","78150","78152","78154","78155","78163","78201","78202","78203","78204","78205","78207","78208","78209","78210","78211","78212","78213","78214","78215","78216","78217","78218","78219","78220","78221","78222","78223","78224","78225","78226","78227","78228","78229","78230","78231","78232","78233","78234","78235","78236","78237","78238","78239","78240","78242","78244","78245","78247","78248","78249","78250","78251","78252","78253","78254","78255","78256","78257","78258","78259","78260","78261","78263","78264","78266"];
var testZip = ["78002", "78006", "78009", "78015", "78023"];
var restZipArray = [];
var restAqiArray = [];
var heatmapData = [];
var googleZips= [];

var restHospitalZip =[];
var restHospitalRate = [];
var restHospitalDate = [];
var restYear;
var map;
var heatmap;



/************** AirQuality Record call to REST API ******************/
$.get(currentDateURL).done(function(airInfo){
    for(var i = 0; i < zipcodes.length; i++){
        restZipArray.push(airInfo[i].zipCode);
        restAqiArray.push(airInfo[i].aqi);
    }
});

/********* Hospital Record call to REST API *************************/
$.get(hospitalDataURL).done(function(hospitalData) {
    for(var i = 0; i < hospitalData.length; i++){
        restHospitalZip.push(hospitalData[i].zipcode);
        restHospitalRate.push(hospitalData[i].pedi_asthma_rate);
        restHospitalDate.push(hospitalData[i].year);
    }
});



/**************************** MAP STUFF*****************************/
function initMap() {
    map = new google.maps.Map(document.getElementById('map'), {
        zoom: 9,
        center: {lat: 29.4241, lng: -98.4936},
        mapTypeId: 'terrain'


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


    googleZips = [
        {location: new google.maps.LatLng(29.282899, -98.73143), zip: 78002},
        {location: new google.maps.LatLng(29.905620, -98.69535), zip: 78006},
        {location: new google.maps.LatLng(29.365666, -98.88893), zip: 78009},
        {location: new google.maps.LatLng(29.745868, -98.65337), zip: 78015},
        {location: new google.maps.LatLng(29.616950, -98.74356), zip: 78023},
        {location: new google.maps.LatLng(29.313487, -98.82649), zip: 78039},
        {location: new google.maps.LatLng(29.218984, -98.78124), zip: 78052},
        {location: new google.maps.LatLng(29.548241, -98.89627), zip: 78056},
        {location: new google.maps.LatLng(29.687398, -98.91348), zip: 78063},
        {location: new google.maps.LatLng(29.963654, -98.42355), zip: 78064},

        {location: new google.maps.LatLng(29.077250, -98.64936), zip: 78065},
        {location: new google.maps.LatLng(29.490392, -98.88826), zip: 78066},
        {location: new google.maps.LatLng(29.184843, -98.67931), zip: 78069},
        {location: new google.maps.LatLng(29.251196, -98.62165), zip: 78073},
        {location: new google.maps.LatLng(29.342261, -98.23285), zip: 78101},
        {location: new google.maps.LatLng(29.568736, -98.21957), zip: 78108},
        {location: new google.maps.LatLng(29.473858, -98.29587), zip: 78109},
        {location: new google.maps.LatLng(29.214254, -98.37028), zip: 78112},
        {location: new google.maps.LatLng(29.134295, -98.20857), zip: 78114},
        {location: new google.maps.LatLng(29.352906, -98.09926), zip: 78121},

        {location: new google.maps.LatLng(29.555169, -98.15139), zip: 78124},
        {location: new google.maps.LatLng(29.549407, -98.30484), zip: 78148},
        {location: new google.maps.LatLng(29.5177, -98.2783), zip: 78150},
        {location: new google.maps.LatLng(29.429762, -98.20316), zip: 78152},
        {location: new google.maps.LatLng(29.550767, -98.26793), zip: 78154},
        {location: new google.maps.LatLng(29.534185, -98.93984), zip: 78155},
        {location: new google.maps.LatLng(29.766172, -98.44624), zip: 78163},
        {location: new google.maps.LatLng(29.469274, -98.52811), zip: 78201},
        {location: new google.maps.LatLng(29.4427793, -98.46151), zip: 78202},
        {location: new google.maps.LatLng(29.415111, -98.46054), zip: 78203},

        {location: new google.maps.LatLng(29.403045, -98.50354), zip: 78204},
        {location: new google.maps.LatLng(29.424275, -98.48764), zip: 78205},
        {location: new google.maps.LatLng(29.422234, -98.52502), zip: 78207},
        {location: new google.maps.LatLng(29.489269, -98.45682), zip: 78209},
        {location: new google.maps.LatLng(29.46378, -98.395806), zip: 78210},
        {location: new google.maps.LatLng(29.56335, -98.346264), zip: 78211},
        {location: new google.maps.LatLng(29.49171, -98.464571), zip: 78212},
        {location: new google.maps.LatLng(29.52303, -98.515413), zip: 78213},
        {location: new google.maps.LatLng(29.47016, -98.329683), zip: 78214},

        {location: new google.maps.LatLng(29.440978, -98.48028), zip: 78215},
        {location: new google.maps.LatLng(29.536735, -98.48852), zip: 78216},
        {location: new google.maps.LatLng(29.540570, -98.42136), zip: 78217},
        {location: new google.maps.LatLng(29.493174, -98.39973), zip: 78218},
        {location: new google.maps.LatLng(29.442150, -98.38887), zip: 78219},
        {location: new google.maps.LatLng(29.413863, -98.47779), zip: 78220},
        {location: new google.maps.LatLng(29.279988, -98.38067), zip: 78221},
        {location: new google.maps.LatLng(29.363010, -98.363010), zip: 78222},
        {location: new google.maps.LatLng(29.309104, -98.309104), zip: 78223},
        {location: new google.maps.LatLng(29.308504, -98.308504), zip: 78224},

        {location: new google.maps.LatLng(29.388108, -98.52600), zip: 78225},
        {location: new google.maps.LatLng(29.385026, -98.55744), zip: 78226},
        {location: new google.maps.LatLng(29.407930, -98.62673), zip: 78227},
        {location: new google.maps.LatLng(29.461039, -98.57190), zip: 78228},
        {location: new google.maps.LatLng(29.505995, -98.57243), zip: 78229},
        {location: new google.maps.LatLng(29.545761, -98.55490), zip: 78230},
        {location: new google.maps.LatLng(29.578215, -98.54355), zip: 78231},
        {location: new google.maps.LatLng(29.578186, -98.47474), zip: 78231},
        {location: new google.maps.LatLng(29.554849, -98.36473), zip: 78233},
        {location: new google.maps.LatLng(29.461820, -98.46413), zip: 78234},

        {location: new google.maps.LatLng(29.342712, -98.43975), zip: 78235},
        {location: new google.maps.LatLng(29.377659, -98.62852), zip: 78236},
        {location: new google.maps.LatLng(29.420371, -98.56675), zip: 78237},
        {location: new google.maps.LatLng(29.471098, -98.61752), zip: 78238},
        {location: new google.maps.LatLng(29.517064, -98.36333), zip: 78239},
        {location: new google.maps.LatLng(29.524648, -98.60789), zip: 78240},
        {location: new google.maps.LatLng(29.350807, -98.60677), zip: 78242},
        {location: new google.maps.LatLng(29.477897, -98.35225), zip: 78244},
        {location: new google.maps.LatLng(29.406400, -98.72699), zip: 78245},
        {location: new google.maps.LatLng(29.579509, -98.41302), zip: 78247},

        {location: new google.maps.LatLng(29.589931, -98.52520), zip: 78248},
        {location: new google.maps.LatLng(29.567510, -98.61402), zip: 78249},
        {location: new google.maps.LatLng(29.502647, -98.66572), zip: 78250},
        {location: new google.maps.LatLng(29.463314, -98.67706), zip: 78251},
        {location: new google.maps.LatLng(29.347076, -98.71572), zip: 78252},
        {location: new google.maps.LatLng(29.468948, -98.78843), zip: 78253},
        {location: new google.maps.LatLng(29.5296, -98.7875), zip: 78254},
        {location: new google.maps.LatLng(29.647378, -98.65949), zip: 78255},
        {location: new google.maps.LatLng(29.623876, -98.62682), zip: 78256},
        {location: new google.maps.LatLng(29.680565, -98.57427), zip: 78257},

        {location: new google.maps.LatLng(29.633032, -98.49607), zip: 78258},
        {location: new google.maps.LatLng(29.625193, -98.41790), zip: 78259},
        {location: new google.maps.LatLng(29.693359, -98.49024), zip: 78260},
        {location: new google.maps.LatLng(29.693508, -98.40397), zip: 78261},
        {location: new google.maps.LatLng(29.346890, -98.31021), zip: 78263},
        {location: new google.maps.LatLng(29.655470, -98.50390), zip: 78264},
        {location: new google.maps.LatLng(29.5296, -98.33075), zip: 78266}





        // {location: new google.maps.LatLng(29.6172, -98.7261), zip: 78023},
        // {location: new google.maps.LatLng(29.6451, -98.4733), zip: 78258},
        // {location: new google.maps.LatLng(29.5517, -98.4952), zip: 78216},
        // {location: new google.maps.LatLng(29.3978, -98.7371), zip: 78245},
        //
        // {location: new google.maps.LatLng(29.5676, -98.6051), zip: 78249},
        // {location: new google.maps.LatLng(29.5857, -98.4129), zip: 78247},
        // {location: new google.maps.LatLng(29.5601, -98.3636), zip: 78233},
        // {location: new google.maps.LatLng(29.4476, -98.3198), zip: 78109},
        // {location: new google.maps.LatLng(29.6172, -98.7261), zip: 78023},
        // {location: new google.maps.LatLng(29.5330, -98.7812), zip: 78254},
        // {location: new google.maps.LatLng(29.5420, -98.5556), zip: 78230},
        // {location: new google.maps.LatLng(29.4733, -98.8142), zip: 78253},
        // {location: new google.maps.LatLng(29.5944, -98.4568), zip: 78232},
        // {location: new google.maps.LatLng(29.7487, -98.6491), zip: 78015},
        // {location: new google.maps.LatLng(29.5886, -98.2760), zip: 78154},
        // {location: new google.maps.LatLng(29.4636, -98.5227), zip: 78201},
        // {location: new google.maps.LatLng(29.5270, -98.6106), zip: 78240},
        // {location: new google.maps.LatLng(29.5061, -98.5776), zip: 78229},
        // {location: new google.maps.LatLng(29.4671, -98.6766), zip: 78251},
        // {location: new google.maps.LatLng(29.3038, -98.4075), zip: 78223},
        // {location: new google.maps.LatLng(29.4896, -98.3855), zip: 78218},
        // {location: new google.maps.LatLng(29.4104, -98.6326), zip: 78227},
        // {location: new google.maps.LatLng(29.5390, -98.4239), zip: 78217},
        //
        //
        // {location: new google.maps.LatLng(29.4553, -98.5611), zip: 78228},
        // {location: new google.maps.LatLng(29.5004, -98.6656), zip: 78250},
        // {location: new google.maps.LatLng(29.4687, -98.6216), zip: 78238},
        // {location: new google.maps.LatLng(29.5201, -98.3581), zip: 78239},
        //
        // {location: new google.maps.LatLng(29.4747, -98.3472), zip: 78244},
        // {location: new google.maps.LatLng(29.6897, -98.4952), zip: 78260},
        // {location: new google.maps.LatLng(29.6249, -98.4294), zip: 78259},
        //
        //
        // {location: new google.maps.LatLng(29.2436, -98.6271), zip: 78073},
        // {location: new google.maps.LatLng(29.2982, -98.4952), zip: 78221},
        // {location: new google.maps.LatLng(29.4489, -98.3910), zip: 78219},
        // {location: new google.maps.LatLng(29.1706, -98.5062), zip: 78264},
        // {location: new google.maps.LatLng(29.4302, -98.4596), zip: 78202},
        // {location: new google.maps.LatLng(29.3744, -98.3855), zip: 78222},
        //
        // {location: new google.maps.LatLng(29.3185, -98.5391), zip: 78224},
        // {location: new google.maps.LatLng(29.4143, -98.3910), zip: 78220},
        // {location: new google.maps.LatLng(29.6611, -98.5831), zip: 78257},
        // {location: new google.maps.LatLng(29.4147, -98.5666), zip: 78237},
        // {location: new google.maps.LatLng(29.3542, -98.6106), zip: 78242},
        //
        //
        // {location: new google.maps.LatLng(29.5929, -98.5254), zip: 78248}


    ];
setTimeout(function(){
    for(var i = 0; i < restHospitalZip.length; i++) {
        console.log(googleZips[i]);
        console.log("These are the rest Zip: " + restHospitalZip[i]);
        console.log("These are the rest Dates: " + restHospitalDate[i]);
        var hospitalZip = restHospitalZip[i];

        // console.log("Here are the variable: " + googleCompareZip);
        if(restHospitalDate[i] === "2017"){
        // if (true) {
            for(var k = 0; k < googleZips.length; k++)
                // var googleCompareZip = googleZips[k].zip;
            if(googleZips[k].zip == hospitalZip){

            // console.log(heatmapData.push(googleZips[i]));
            console.log(heatmapData.push(googleZips[k]));

        }
    // }
        }
    }

    console.log("heat map length: " + heatmapData.length);
    console.log("Heat map data : " + heatmapData[i]);
    console.log("GOd HELP US: " + JSON.stringify(heatmapData[i]));
    console.log("This is the first Google Zip: " +googleZips[0]);
// }, 600);
/** THIS IS ALMOST THERE!!! I NEED TO MATCH THE RESTHOSTPITAL ZIP WITH THE GOOGLE ZIP **/


for(var j = 0; j < heatmapData.length; j++){
    // if(heatmapData[j].zip === restHospitalZip[j]){
    if(restHospitalDate[j] == "2017") {
        if (restHospitalRate[j] < 11) {
            heatmapData[j].weight = 1;
        } else if (restHospitalRate[j] >= 11 && restHospitalRate[j] < 20) {
            heatmapData[j].weight = 3
        } else if (restHospitalRate[j] >= 21 && restHospitalRate[j] < 30) {
            heatmapData[j].weight = 6
        } else if (restHospitalRate[j] >= 31 && restHospitalRate[j] < 40) {
            heatmapData[j].weight = 8
        } else if (restHospitalRate[j] >= 41 && restHospitalRate[j] < 50) {
            heatmapData[j].weight = 11
        } else if (restHospitalRate[j] > 50) {
            heatmapData[j].weight = 15
        }
    }
    // }
}

// setTimeout(function(){
    heatmap = new google.maps.visualization.HeatmapLayer({
        data: heatmapData
        // dissipating: false

    });

    heatmap.setMap(map);
    heatmap.set('radius', 30);
    heatmap.set('maxIntensity', 15)
},500);


}




/************************************&*****END OF MAP**************************************/



/**** floating table on map ****/

$("#toggle-heatmap").on("click", function(){
    heatmap.setMap(heatmap.getMap() ? null : map);  // this isn't defined :(
    console.log("you clicked toggle heat map");
});





function changeGradient() {
    var gradient = [
        'rgba(0, 255, 255, 0)',
        'rgba(0, 255, 255, 1)',
        'rgba(0, 191, 255, 1)',
        'rgba(0, 127, 255, 1)',
        'rgba(0, 63, 255, 1)',
        'rgba(0, 0, 255, 1)',
        'rgba(0, 0, 223, 1)',
        'rgba(0, 0, 191, 1)',
        'rgba(0, 0, 159, 1)',
        'rgba(0, 0, 127, 1)',
        'rgba(63, 0, 91, 1)',
        'rgba(127, 0, 63, 1)',
        'rgba(191, 0, 31, 1)',
        'rgba(255, 0, 0, 1)'
    ]
    heatmap.set('gradient', heatmap.get('gradient') ? null : gradient);
}

function changeRadius() {
    heatmap.set('radius', heatmap.get('radius') ? null : 20);
}

function changeOpacity() {
    heatmap.set('opacity', heatmap.get('opacity') ? null : 0.2);
}

