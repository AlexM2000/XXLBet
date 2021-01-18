var lastDataFromServer;

$(document).ready(function () {
    var input = $("input");
    var label = $("label");
    var submitButton = $('button');

    document.getElementById("sportSelect").addEventListener("change", function (ev) {
        input.hide();
        label.hide();
        $('#moneyError').empty();
        $('#drawDescription').hide();
        $('#team1Description').hide();
        $('#team2Description').hide();
        submitButton.prop("disabled", true);
        $("#tournamentLabel").hide();
        $("#tournamentSelect").hide();
        $('#noMatchesInfo').text('');
        $('#matchSelect').hide();
        $('#matchLabel').hide();
        $('#moneyInput').hide();
        $('#moneyInfo').hide();
        $('#teamsLabel').hide();
        $('#teamsSelect').hide();
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

                    $('#tournamentSelect').append("<option></option>");
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
        $('#matchSelect').empty();
        $('#moneyError').empty();
        $('#matchLabel').hide();
        $('#drawDescription').hide();
        $('#team1Description').hide();
        submitButton.prop("disabled", true);
        $('#team2Description').hide();
        $('#noMatchesInfo').text('');
        $('#moneyInput').hide();
        $('#moneyInfo').hide();
        $('#canWinInfo').hide();
        $('#teamsLabel').hide();
        $('#teamsSelect').hide();


        var tournament = ev.target.value;;

        $.ajax({
            url: "/xxlbet?command=get_matches_by_tournament",
            type: 'GET',
            data: {
                tournament: tournament
            },
            success: function (dataFromServer) {
                console.log(dataFromServer);
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
                $('#noMatchesInfo').text('');
                $('#matchSelect').empty();

                if (dataFromServer.length === 0) {
                    $('#noMatchesInfo').text('No matches available');
                } else {
                    $('#matchSelect').append("<option></option>");
                    for (var i = 0; i < dataFromServer.length; i++) {
                        var option = '<option value="' + dataFromServer[i]['opponents'][0]['matchId'] +'">'
                        for (var j = 0; j < dataFromServer[i]['opponents'].length; j++) {
                            option += dataFromServer[i]['opponents'][j]['name'] + " - "
                        }
                        option = option.slice(0, -2);
                        option += '</option>';
                        $('#matchSelect').append(option);
                    }

                    $('#matchSelect').show();
                    $('#matchLabel').show();
                    $('#drawDescription').show();
                    $('#team1Description').show();
                    $('#team2Description').show();
                    $('#teamsLabel').show();
                    $('#teamsSelect').show();
                    $('#canWinInfo').show();
                    submitButton.prop("disabled", false);
                }

            },
            error: function (e) {
                alert(e)
            }
        })
    });

    document.getElementById("matchSelect").addEventListener("change", function (ev) {
        $('#canWinInfo').hide();
        $('#teamsSelect').hide();
        $('#moneyError').empty();
        submitButton.prop("disabled", true);
        $('#teamsSelect').empty();
        $('#moneyInput').hide();
        $('#moneyInfo').hide();
        $('#canWinInfo').hide();

        for (var i = 0; i < lastDataFromServer.length; i++) {
            // Assume that there always come 2 opponents
            if (lastDataFromServer[i]['opponents'][0]['matchId'].toString() === ev.target.value) {
                $('#drawCoefficientInfo').text(lastDataFromServer[i]['drawCoefficient']);
                $('#firstTeamCoefficientInfo').text(lastDataFromServer[i]['opponents'][0]['coefficient']);
                $('#secondTeamCoefficientInfo').text(lastDataFromServer[i]['opponents'][1]['coefficient']);
                $('#teamsSelect').append('<option></option>')
                for (var j = 0; j < lastDataFromServer[i]['opponents'].length; j++) {
                    $('#teamsSelect').append(
                        '<option name="' + lastDataFromServer[i]['opponents'][j]['coefficient'] + '" value="' + lastDataFromServer[i]['opponents'][j]['id']  + '">'
                        + lastDataFromServer[i]['opponents'][j]['name'] + '</option>'
                    )
                }

                $('#canWinInfo').show();
                $('#teamsSelect').show();
                $('#moneyInfo').show();
                $('#moneyInput').show();
                break;
            }
        }
    });

    document.getElementById("teamsSelect").addEventListener("change", function (ev) {
        submitButton.prop("disabled", true);
        if (document.getElementById("moneyInput").value !== "" && parseFloat(document.getElementById("moneyInput").value) >= 0.00) {
            $('#canWinDescription').text((parseFloat($(this).find('option:selected').attr("name")) * parseFloat(document.getElementById("moneyInput").value)).toFixed(2));
        }
        submitButton.prop("disabled", false);
    });
});


function createBet(email) {
    var errorCount = 0;
    $('#moneyError').empty();

    if (document.getElementById('moneyInput').value === ""
        || parseFloat(document.getElementById('moneyInput').value).toFixed(2) <= 0) {
        errorCount++;
        $('#moneyError').text('Invalid value')
    }

    if (errorCount === 0) {

        var data = {
            email: email,
            match_id: parseInt(document.getElementById('matchSelect').value),
            sum: parseFloat(document.getElementById('moneyInput').value).toFixed(2),
            expected_winner_id: parseInt(document.getElementById('teamsSelect').value)
        };

        $.ajax({
            url: '/xxlbet?command=create_bet',
            type: 'POST',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data),
            success: function (dataFromServer) {
                if (dataFromServer === "ok") {
                    alert('Your bet was created successfully');
                    window.location.reload();
                } else {
                    $('#moneyError').text(dataFromServer['create-bet-page.bad-balance'])
                }
            },
            error: function (e) {
                alert(e)
            }
        });
    }
}
