/**
 * Created by Anton Nikanorov on 22.10.15.
 */

socket = new SockJS('/websoc');
stompClient = Stomp.over(socket);
stompClient.connect('antowka34', 'la136dop', function(frame) {
    console.log(frame);

    stompClient.subscribe("/portfolio", function(message) {

    });
});