var lastDataFromServer;

$(document).ready(function () {
    var input = $("input");
    var label = $("label");
    var submitButton = $('button');

    document.getElementById("sportSelect").addEventListener("change", function (ev) {
        input.hide();
        label.hide();
        submitButton.prop("disabled", true);
        $("#tournamentLabel").hide();
        $("#tournamentSelect").hide();
        $('#matchSelect').hide();
        $('#matchLabel').hide();
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
                console.log(dataFromServer);
                $("#tournamentSelect").empty();
                $('#noTournamentsInfo').text('');

                if (dataFromServer.length === 0) {
                    $('#noTournamentsInfo').text('No tournaments available');
                } else {
                    $("#tournamentLabel").show();

                    for (var i = 0; i < dataFromServer.length; i++) {
                        $('#tournamentSelect').append("<option value='" + dataFromServer[i]['name'] + "'>" + dataFromServer[i]['name'] + "</option>");
                    }

                    $("#tournamentSelect").show();
                }
            },
            error: function (e) {
                alert(e)
            }
        })
    });

    document.getElementById("tournamentSelect").addEventListener("change", function (ev) {
        $('#matchSelect').hide();
        $('#matchLabel').hide();
        submitButton.prop("disabled", true);
        var tournament = ev.target.value;;

        $.ajax({
            url: "/xxlbet?command=get_matches_by_tournament",
            type: 'GET',
            data: {
                tournament: tournament
            },
            success: function (dataFromServer) {
                /**
                 * [
                 * {
                 *      drawCoefficient: 3,
                 *      tournamentName: League 1,
                 *      opponents: [
                 *      {
                 *          id: 1
                 *          matchId: 1
                 *          tournamentId: 1
                 *          name: Team 1
                 *          coefficient: 3.0
                 *      },
                 *      {
                 *          id: 2
                 *          matchId: 1
                 *          tournamentId: 1
                 *          name: Team 2
                 *          coefficient: 0.5
                 *      }]
                 * },
                 *{
                 *      drawCoefficient: 2.2,
                 *      tournamentName: League 1,
                 *      opponents: [
                 *      {
                 *          id: 1
                 *          matchId: 2
                 *          tournamentId: 1
                 *          name: Team 3
                 *          coefficient: 1.5
                 *      },
                 *      {
                 *          id: 2
                 *          matchId: 2
                 *          tournamentId: 1
                 *          name: Team 4
                 *          coefficient: 0.9
                 *      }]
                 * }
                 * ]
                 */
                lastDataFromServer = dataFromServer;
                $('#matchSelect').empty();

                if (dataFromServer.length === 0) {
                    $('#noMatchesInfo').text('No matches available');
                } else {
                    for (var i = 0; i < dataFromServer.length; i++) {
                        var option = '<option value="' + dataFromServer[0]['opponents'][0]['matchId'] +'">'
                        for (var j = 0; j < dataFromServer[i]['opponents'].length; j++) {
                            option += dataFromServer[i]['opponents'][j]['name']
                        }
                        option += '</option>';
                        $('#matchSelect').append(option);
                    }

                    $('#matchSelect').show();
                    submitButton.prop("disabled", false);
                }

            },
            error: function (e) {
                alert(e)
            }
        })
    });

    document.getElementById("matchSelect").addEventListener("change", function (ev) {
        for (var i = 0; i < lastDataFromServer.length; i++) {
            // Assume that there always come 2 opponents
            if (lastDataFromServer[i]['opponents'][0]['matchId'] === ev.target.value) {
                $('#drawCoefficientInfo').text(lastDataFromServer[i]['drawCoefficient']);
                $('#firstTeamCoefficientInfo').text(lastDataFromServer[i]['opponents'][0]['coefficient']);
                $('#secondTeamCoefficientInfo').text(lastDataFromServer[i]['opponents'][1]['coefficient']);
                break;
            }
        }
    })
});
