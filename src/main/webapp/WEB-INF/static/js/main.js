/**
 * Created by Anton Nikanorov on 22.10.15.
 */

socket = new SockJS('/websoc');
stompClient = Stomp.over(socket);
stompClient.connect({}, function(frame) {

    console.log(frame);

    stompClient.send("/app/login", {}, JSON.stringify({login:"antowka", password:"la136dop"}));

    setTimeout(function(){

        stompClient.send("/app/login2", {}, JSON.stringify({login:"antowka1"}));

        stompClient.subscribe("/response/status", function(message) {
            console.log(message);
        });
    }, 2000);


});