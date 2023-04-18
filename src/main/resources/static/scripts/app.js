var input_username = null;

window.onload=function(){
    input_username = document.getElementById("input_room_name");

    input_username.addEventListener("keypress", function(event) {
        if (event.key === "Enter") {
            event.preventDefault();
            send_to_chatroom();
            input_username.value = '';
        }
    });

    document.getElementById("join_button").onclick = send_to_chatroom;

    function send_to_chatroom(){
        if(input_username.value != ''){
            location.href = '/chatrooms/' + input_username.value;
        }
    }
}

