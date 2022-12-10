if ( window.history.replaceState ) {
    window.history.replaceState( null, null, window.location.href );
}

var input_username = null;
var input_password = null;
var error_message = null;
var login_button = null;

var xhr = new XMLHttpRequest();
xhr.onreadystatechange = function() { 
    if (xhr.readyState == 4 & xhr.status == 200){
        var json = JSON.parse(xhr.responseText);

        if(json.statusCode != 500){
            error_message.innerHTML = json.message;
        }
    }
};

function login(username, password){
    xhr.open("POST", "/login");
    xhr.setRequestHeader("Content-Type", "application/json");
    var data = JSON.stringify({"username": username, "password": password});
    xhr.send(data); 
}

window.onload = function(){
    input_username = document.getElementById("input_username");
    input_password = document.getElementById("input_password");
    login_button = document.getElementById("login_button");
    error_message = document.getElementById("error_message");

    input_username.addEventListener("keypress", function(event) {
        if (event.key === "Enter") {
            event.preventDefault();
            login(input_username.value, input_password.value);
        }
    });

    input_password.addEventListener("keypress", function(event) {
        if (event.key === "Enter") {
            event.preventDefault();
            login(input_username.value, input_password.value);
        }
    });

    login_button.onclick=function(){
        login(input_username.value, input_password.value);
    };
}


