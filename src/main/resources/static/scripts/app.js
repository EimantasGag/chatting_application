var input = null;

window.onload=function(){
    input = document.getElementById("input_room_name");

    input.addEventListener("keypress", function(event) {
        if (event.key === "Enter") {
            event.preventDefault();
            send_to_chatroom();
            input.value = '';
        }
    });

    document.getElementById("join_button").onclick = send_to_chatroom;

    function send_to_chatroom(){
        if(input.value != ''){
            location.href = '/chatrooms/' + input.value;
        }
    }
}

