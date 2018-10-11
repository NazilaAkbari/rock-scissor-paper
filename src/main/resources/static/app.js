var stompClient = null;

function choose(response) {
    $('#rock').prop('disabled', true);
    $('#paper').prop('disabled', true);
    $('#scissor').prop('disabled', true);
    stompClient.send('/app/response', {}, response);
}

function userInfo() {
    $.get('/playerinfo', function (data) {
        console.log(data.username);
        $('#name').text(data.username);
        $('#rank').text(data.rank);
    });

}

$(document).ready(function () {
    userInfo();
    $.get('/playerinfo', function (data) {
        console.log(data.username);
        $('#name').text(data.username);
        $('#rank').text(data.rank);
    });

    $('#start').click(function () {
        $.post('/start', function (data) {
            $('#rock').prop('disabled', false);
            $('#paper').prop('disabled', false);
            $('#scissor').prop('disabled', false);
            $('#startDiv').hide();
            $('#result').hide();
            $('#wait').show();
            connect(data);
        })
    });

    $('#rock').click(function () {
        choose('ROCK');
    });

    $('#paper').click(function () {
        choose('PAPER');
    });

    $('#scissor').click(function () {
        choose('SCISSOR');
    });

});

function handleGameMessage(message) {
    var text = message.body;
    console.log(message);
    if (text === 'Start') {
        console.log(message);
        $('#wait').hide();
        $('#play').show();
    } else {
        var gameState = JSON.parse(message.body);
        if (gameState.finished) {
            $('#play').hide();
            var obj = gameState.playerSelections;
            var keys = Object.keys(obj);
            var wincount = gameState.playerWinNum;
            var wincountKeys = Object.keys(wincount);
            $('#playerOne').text(keys[0]);
            $('#playerOneSelection').text(obj[keys[0]]);
            $('#playerOneWinCount').text(wincount[wincountKeys[0]]);
            $('#playerTwo').text(keys[1]);
            $('#playerTwoSelection').text(obj[keys[1]]);
            $('#playerTwoWinCount').text(wincount[wincountKeys[1]]);

            if (wincount[wincountKeys[0]] > wincount[wincountKeys[1]])
                $('#winnerName').text(wincountKeys[0]);
            else
                $('#winnerName').text(wincountKeys[1]);

            $('#startDiv').show();
            userInfo();
        } else {
            $('#rock').prop('disabled', false);
            $('#paper').prop('disabled', false);
            $('#scissor').prop('disabled', false);
            $('#winnerName').text(gameState.winnerUsername);
            var obj = gameState.playerSelections;
            var keys = Object.keys(obj);
            var wincount = gameState.playerWinNum;
            var wincountKeys = Object.keys(wincount);
            $('#playerOne').text(keys[0]);
            $('#playerOneSelection').text(obj[keys[0]]);
            $('#playerOneWinCount').text(wincount[wincountKeys[0]]);
            $('#playerTwo').text(keys[1]);
            $('#playerTwoSelection').text(obj[keys[1]]);
            $('#playerTwoWinCount').text(wincount[wincountKeys[1]]);
            $('#result').show();
        }
    }
}

function connect(gameId) {
    var socket = new SockJS('/play');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        stompClient.subscribe('/game/' + gameId, handleGameMessage);
    })
}




