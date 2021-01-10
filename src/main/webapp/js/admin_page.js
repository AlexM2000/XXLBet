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

    document.getElementById("tournamentSelect").addEventListener("change", function (ev) {
        var teamSelect1 = $('#team1');
        teamSelect1.hide();
        var teamSelect2 = $('#team2');
        teamSelect2.hide();

        var tournament = ev.target.value;

        $.ajax({
            url: '/xxlbet',
            type: 'GET',
            data: {
                command: 'get_opponents_by_tournament',
                tournament: tournament
            },
            dataType: 'json',
            success: function (dataFromServer) {
                teamSelect1.empty();
                teamSelect2.empty();
                $('#noTeamsInfo').text('');

                if (dataFromServer.length === 0) {
                    $('#noTeamsInfo').text('No teams available');
                } else {
                    for (var i = 0; i < dataFromServer.length; i++) {
                        teamSelect1.append("<option value='" + dataFromServer[i]['name'] + "'>" + dataFromServer[i]['name'] + "</option>");
                        teamSelect2.append("<option value='" + dataFromServer[i]['name'] + "'>" + dataFromServer[i]['name'] + "</option>");
                    }

                    teamSelect1.show();
                    teamSelect2.show();
                }
            },
            error: function (e) {
                alert(e)
            }
        })
    });
});