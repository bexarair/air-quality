/**************************************Secret Bear********************************************/
function breathBear() {
    var secretCode = "";
    var konamiCode = "686578736976";

    $(document).on("keydown", function (e) {
        secretCode = secretCode + ("" + e.keyCode);
        if (secretCode === konamiCode) {
            // $(".container").hide();
            $(".bear")
                .append("<img src='/img/dancingBear.gif'/>");
                // .css("background-image", "url(img/dancingBear.gif)")
                // .css("background-repeat", "no-repeat")
            $(".bear2")
                .append("<img src='/img/dancingBear.gif'/>");
            $(".breath-title")
                .css("text-align", "center")
                .html("<h2 class='form-title'>Remember to breathe!</h2>")
        }
        if (!konamiCode.indexOf(secretCode)) return;
        secretCode = ("" + e.keyCode);

    });

    $(document).keyup(function(event){
        console.log(event.keyCode);
    });
}

breathBear();

/****************************************END OF BEAR**************************************************/
