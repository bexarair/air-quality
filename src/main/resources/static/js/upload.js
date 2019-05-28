'use strict';

var singleUploadForm = document.querySelector('#singleUploadForm');
var singleFileUploadInput = document.querySelector('#singleFileUploadInput');
var singleFileUploadError = document.querySelector('#singleFileUploadError');
var singleFileUploadSuccess = document.querySelector('#singleFileUploadSuccess');



function uploadSingleFile(file) {
    var formData = new FormData();
    formData.append("file", file);

    var xhr = new XMLHttpRequest();
    xhr.open("POST", "/uploadFile");

    xhr.onload = function() {
        console.log(xhr.responseText);
        var response = JSON.parse(xhr.responseText);
        if(xhr.status == 200) {
            singleFileUploadError.style.display = "none";
            singleFileUploadSuccess.innerHTML = "<p>File Uploaded Successfully.</p>" + "<br>" +
                "<p>DownloadUrl : <a href='" + response.fileDownloadUri + "' target='_blank'>" + response.fileDownloadUri + "</a></p>";
            singleFileUploadSuccess.style.display = "block";
        } else {
            singleFileUploadSuccess.style.display = "none";
            singleFileUploadError.innerHTML = (response && response.message) || "Some Error Occurred";
        }
    };

    xhr.send(formData);
}



// function processForm() {
//     var xhttp = new XMLHttpRequest();
//     xhttp.onreadystatechange = function() {
//         if (this.readyState == 4 && this.status == 200) {
//             document.getElementById("demo").innerHTML = this.responseText;
//         }
//     };
//     xhttp.open("GET", "load", true);
//     xhttp.send();
// }

//
// (function() {
//     var httpRequest;
//     document.getElementById("processFormbutton").addEventListener('click', makeRequest);
//
//     function makeRequest() {
//         httpRequest = new XMLHttpRequest();
//
//         if (!httpRequest) {
//             alert('Giving up :( Cannot create an XMLHTTP instance');
//             return false;
//         }
//         httpRequest.onreadystatechange = alertContents;
//         httpRequest.open('GET', 'http://localhost:8080/load    ');
//         httpRequest.send();
//     }

//     function alertContents() {
//         if (httpRequest.readyState === XMLHttpRequest.DONE) {
//             if (httpRequest.status === 200) {
//                 alert(httpRequest.responseText);
//             } else {
//                 alert('There was a problem with the request.');
//             }
//         }
//     }
// })();



singleUploadForm.addEventListener('submit', function(event){
    var files = singleFileUploadInput.files;
    if(files.length === 0) {
        singleFileUploadError.innerHTML = "Please select a file";
        singleFileUploadError.style.display = "block";
    }
    uploadSingleFile(files[0]);
    event.preventDefault();
}, true);

// document.getElementById("processFormbutton").onclick = function () {
//     location.href = "http://localhost:8080/load";
// };

// $(document).ready(function() {
//     $("processFrombutton").click(function (e) {
//         e.preventDefault();
//         $.ajax({
//             type: "POST",
//             url: "/load",
//             // data: {
//             //     id: $(this).val(), // < note use of 'this' here
//             //     access_token: $("#access_token").val()
//             // },
//             success: function (result) {
//                 alert('ok');
//             },
//             error: function (result) {
//                 alert('error');
//             }
//         });
//     });
// });



