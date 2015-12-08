/**
 * Created by Anton Nikanorov on 22.10.15.
 */

socket = new SockJS('/websoc');
stompClient = Stomp.over(socket);
stompClient.connect({}, function(frame) {

    console.log(frame);

    stompClient.subscribe("/response/ticker", function(message) {
        console.log("get response from /response/ticker");
        console.log(message);
    });

    stompClient.subscribe("/response/operation", function(message) {
        console.log("get response from /response/operation");
        console.log(message);
    });

    var ticker = {
        tickerid:1
    };

    var operation = {
        ticker:{tickerId:2},
        amount:5,
        price:100,
        operationType:{operationTypeId:2}
    };

    stompClient.send("/app/ticker/get", {}, JSON.stringify(ticker));
    stompClient.send("/app/operation/add", {}, JSON.stringify(operation));
});