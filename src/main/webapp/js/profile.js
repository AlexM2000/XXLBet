function getBets(command, page) {
    $.ajax({
        url: "/xxlbet",
        type: "GET",
        data: {
            command: command,
            page: page
        },
        success: function (dataFromServer) {
            dataFromServer = JSON.parse(dataFromServer);
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

$(document).ready(function() {

    document.getElementById("betSelector").addEventListener('change', function (ev) {
            getBets(ev.target.value, 1)
        }
    );



    getBets("all_user_bets", 1);
});