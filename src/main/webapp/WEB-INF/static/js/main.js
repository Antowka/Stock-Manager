/**
 * Created by Anton Nikanorov on 22.10.15.
 */

socket = new SockJS('/websoc');
stompClient = Stomp.over(socket);
stompClient.connect({}, function(frame) {

    console.log(frame);

    stompClient.subscribe("/response/status", function(message) {
        console.log(message);
    });

    var ticker = {
        tickerid:1
    };

    stompClient.send("/app/ticker", {}, JSON.stringify(ticker));
});