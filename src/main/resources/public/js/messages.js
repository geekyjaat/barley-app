var webSocket = new WebSocket('ws://localhost:5000/echo');
webSocket.onmessage = handleSocket;
webSocket.onopen = print;
webSocket.onerror = print;

function print(event) {
    console.log(event);
}

function handleSocket(event) {
    onMessage(event.data);
};

Messenger.options = {
    extraClasses: 'messenger-fixed messenger-on-bottom messenger-on-right',
    theme: 'future'
};

function onMessage(event) {
    Messenger().post({
        message: '' + event,
        type: 'info',
        showCloseButton: true
    });
};