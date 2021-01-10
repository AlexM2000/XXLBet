$(document).ready(function () {
    document.getElementById("sportSelect").addEventListener("change", function (ev) {
        var tournamentSelect = $("#tournamentSelect");
        tournamentSelect.hide();
        var sport = ev.target.value;

        $.ajax({
            url: '/xxlbet',
            type: 'GET',
            data: {
                command: 'get_tournaments_by_sport',
                sport: sport
            },
            dataType: 'json',
            success: function (dataFromServer) {
                tournamentSelect.empty();
                $('#noTournamentsInfo').text('');

                if (dataFromServer.length === 0) {
                    $('#noTournamentsInfo').text('No tournaments available');
                } else {
                    for (var i = 0; i < dataFromServer.length; i++) {
                        tournamentSelect.append("<option value='" + dataFromServer[i]['name'] + "'>" + dataFromServer[i]['name'] + "</option>");
                    }

                    tournamentSelect.show();
                }
            },
            error: function (e) {
                alert(e)
            }
        })
    })
});