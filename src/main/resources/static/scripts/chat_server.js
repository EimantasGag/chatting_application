let socket = null;
let stompClient = null;
const server_ip = "192.168.0.157";
room_name = '';
//const ws_url = "ws://" + server_ip + ":8080/chatserver"

//TODO: WRAP THIS INTO OBJECT

function send_message(message){
    if(stompClient != null && message != ''){
        stompClient.send("/chatroom/" + room_name, {}, message);
    }
}

function connect_to_chatserver(room_name, onmessage){
    this.room_name = room_name;

    socket = new SockJS("/chatserver");
    stompClient = Stomp.over(socket);
    stompClient.debug = () => {};
    
    if(room_name == ''){
        return;
    }

    stompClient.connect({}, function (frame) {
        stompClient.subscribe("/chatroom/" + room_name, onmessage);
    });
}