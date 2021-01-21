function getBets(command) {
    $.ajax({
        url: "/xxlbet",
        type: "GET",
        data: {
            command: command
        },
        success: function (dataFromServer) {
            var table = $("#betsBody");
            $("#betsBody tr").remove();
            for (var i = 0; i < dataFromServer.length; i++) {
                table.append("<tr>" +
                    "<td>" + dataFromServer[i]['match'] + "</td>" +
                    "<td>" + dataFromServer[i]['sum'] + "</td>" +
                    "<td>" + dataFromServer[i]['coefficient'] + "</td>" +
                    "<td>" + dataFromServer[i]['winningSum'] + "</td>" +
                    "</tr>"
                );
            }
        }
    })
}

function showPayForm() {
    $("#moneyLabel").show();
    $("#money").show();
    $("#chooseCard").show();
    $("#chooseCardLabel").show();
    $("#payButton").show();
}

$(document).ready(function() {

    document.getElementById("betSelector").addEventListener('change', function (ev) {
            getBets(ev.target.value)
        }
    );

    getBets("all_user_bets");
});

function updateBalance(userId) {
    // should be actual payment process

    const data = {
        user_id: userId,
        money: document.getElementById("money").value
    };

    $.ajax({
        url: '/xxlbet?command=pay_balance',
        type: 'POST',
        contentType: 'application/json; charset=UTF-8',
        data: JSON.stringify(data),
        success: function() {
            alert('Transaction completed successfully!');
            window.location.reload();
        },
        error: function (e) {
            alert(e);
        }
    })
}