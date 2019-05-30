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
        var response = xhr.responseText;
        if(xhr.status == 200) {
            singleFileUploadError.style.display = "none";
            singleFileUploadSuccess.innerHTML = "<p>File Uploaded Successfully!</p>" + "<br>" ;
            singleFileUploadSuccess.style.display = "block";
        } else {
            singleFileUploadSuccess.style.display = "none";
            singleFileUploadError.innerHTML = (response && response.message) || "Some Error Occurred";
        }
    };

    xhr.send(formData);
}


//For selecting a file from your machine

singleUploadForm.addEventListener('submit', function(event){
    var files = singleFileUploadInput.files;
    if(files.length === 0) {
        singleFileUploadError.innerHTML = "Please select a file";
        singleFileUploadError.style.display = "block";
    }
    uploadSingleFile(files[0]);
    event.preventDefault();
}, true);



//To submit the download and give a status of the processing
$(document).ready(function () {

    $("#processForm").submit(function (event) {

        //stop submit the form, we send it manually.
        event.preventDefault();

        form_submit();

    });

});

function form_submit() {

    $("#processFormbutton").prop("disabled", true);

    $.ajax({
        type: "GET",
        contentType: "application/json",
        url: "/load",
        data: JSON.parse(status),
        dataType: 'json',
        cache: false,
        timeout: 60000,
        success: function (data) {

            var json = "<h5>Status:</h5><pre>"
                + JSON.parse(data, null, 4) + "</pre>";
            $('#form-load-responseSuccess').html(json);

            console.log("SUCCESS : ", data);
            $("#processFormbutton").prop("disabled", false);

        },
        error: function (e) {

            var json = "<h4>Status</h4><pre>"
                + e.responseText + "</pre>";
            $('#form-load-responseError').html(json);

            console.log("ERROR : ", e);
            $("#processFormbutton").prop("disabled", false);

        }
    });

}





