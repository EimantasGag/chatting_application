let socket = null;
let stompClient = null;
const server_ip = "192.168.0.157";
//const ws_url = "ws://" + server_ip + ":8080/chatserver"

function send_message(){
    if(stompClient != null){
        console.log("SENT A MESSAGE: LABAS");
        stompClient.send("/chatroom/kambarys", {}, "LABAS");
    }
}

function connect_to_chatserver(){
    socket = new SockJS("/chatserver");
    stompClient = Stomp.over(socket);
    stompClient.debug = () => {};

    let room_name = input.value;
    
    if(room_name == ''){
        return;
    }

    console.log(room_name);

    stompClient.connect({}, function (frame) {
        stompClient.subscribe("/chatroom/" + room_name, function (message) {
            console.log("RECEIVED A MESSAGE: " + message.body);
        });
    });
}

var input = null;

window.onload=function(){
    input = document.getElementById("input_room_name");

    input.addEventListener("keypress", function(event) {
        if (event.key === "Enter") {
            event.preventDefault();
            connect_to_chatserver();
        }
    });
}