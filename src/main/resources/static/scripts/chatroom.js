chatroom_name = '';
chat_logs = null;
input_message = null;
send_button = null;

window.onload = function(){
    chatroom_name = window.location.pathname.split('/').pop();
    chat_logs = document.getElementById("chat_logs");
    input_message = document.getElementById("input_message");
    send_button = document.getElementById("send_button");


    input_message.addEventListener("keypress", function(event) {
        if (event.key === "Enter") {
            event.preventDefault();
            send_message(input_message.value);
            input_message.value = '';
        }
    });

    send_button.onclick = function(){
        send_message(input_message.value);
        input_message.value = '';
    }

    //DISPLAY ROOMS NAME
    document.getElementById("chatroom_name").innerText = chatroom_name + ' ROOM';

    //CONNECT TO CHAT SERVER AND SUBSCRIBE TO THIS ROOMS MESSAGES
    connect_to_chatserver(chatroom_name, onmessage);
};

//TODO: WHEN THE USER EXITS CHATROOM PAGE DISCONNECT IT FROM SERVER 

function onmessage(message){
    chat_logs.innerText += message.body + "\n"
}
