if ( window.history.replaceState ) {
    window.history.replaceState( null, null, window.location.href );
}

var input_username = null;
var input_password = null;
var input_repeatpassword = null;
var error_message = null;
var register_button = null;

var xhr = new XMLHttpRequest();

//TODO: NOT ALLOW THE USER TO CEATE TO SIMPLE PASSWORDS + CNAT BE SAME AS USERNAME

function displayErrorMessage(message){
    error_message.innerHTML = message;
}

xhr.onreadystatechange = function() { 
    if (xhr.readyState == 4 & xhr.status == 200){
        var json = JSON.parse(xhr.responseText);

        if(json.statusCode != 500){
            error_message.innerHTML = json.message;
        }
    }
};

function register(username, password, repeatPassword){
    if(password != repeatPassword){
        displayErrorMessage("Passwords dont match");
        return;
    }

    xhr.open("POST", "/register");
    xhr.setRequestHeader("Content-Type", "application/json");
    var data = JSON.stringify({"username": username, "password": password});
    xhr.send(data); 
}

window.onload = function(){
    input_username = document.getElementById("input_username");
    input_password = document.getElementById("input_password");
    input_repeatpassword = document.getElementById("input_repeatpassword");
    register_button = document.getElementById("register_button");
    error_message = document.getElementById("error_message");

    input_username.addEventListener("keypress", function(event) {
        if (event.key === "Enter") {
            event.preventDefault();
            register(input_username.value, input_password.value, input_repeatpassword.value);
        }
    });

    input_password.addEventListener("keypress", function(event) {
        if (event.key === "Enter") {
            event.preventDefault();
            register(input_username.value, input_password.value, input_repeatpassword.value);
        }
    });

    input_repeatpassword.addEventListener("keypress", function(event) {
        if (event.key === "Enter") {
            event.preventDefault();
            register(input_username.value, input_password.value, input_repeatpassword.value);
        }
    });

    register_button.onclick=function(){
        register(input_username.value, input_password.value, input_repeatpassword.value);
    };
}


