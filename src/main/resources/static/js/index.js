$(document).ready(function() {
    "use strict";

    var map;
    // function initMap() {
        map = new google.maps.Map(document.getElementById('map'), {
            center: {lat: -34.397, lng: 150.644},
            zoom: 14
        });
        function createInfoWindow(pos) {
        }
        function createMarker(place) {

            var pos = {
                lat: place.geometry.location.lat(),
                lng: place.geometry.location.lng()
            };
            var marker = new google.maps.Marker({
                position: pos,
                map: map
            });


            marker.addListener('click', function () {
                infoWindow.open(map, marker);
            })


        }


        // console.log(infoWindow.getPosition());
    // } // end of initialize map function












});