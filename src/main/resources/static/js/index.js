"use strict";
// $(document).ready(function() {



// Initialize and add the map
    function initMap() {
        // The location of Uluru
        var uluru = {lat: 29.4241, lng: -98.4936};
        // The map, centered at Uluru
        var map = new google.maps.Map(
            document.getElementById('map'), {zoom: 10, center: uluru});
        // The marker, positioned at Uluru
        var marker = new google.maps.Marker({position: uluru, map: map});
    }










// });