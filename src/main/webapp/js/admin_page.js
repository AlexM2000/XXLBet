$(document).ready(function () {
    const input = $("input");
    const label = $("label");
    const submitButton = $('button');

    document.getElementById("sportSelect").addEventListener("change", function (ev) {
        input.hide();
        label.hide();
        submitButton.prop("disabled", true);
        $("#tournamentLabel").hide();
        $("#tournamentSelect").hide();

        const sport = ev.target.value;

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
                    for (let i = 0; i < dataFromServer.length; i++) {
                        $('#tournamentSelect').append("<option value='" + dataFromServer[i]['name'] + "'>" + dataFromServer[i]['name'] + "</option>");
                    }

                    $("#tournamentSelect").show();
                }
            },
            error: function () {
                window.location.href = '/500';
            }
        })
    })

    document.getElementById("tournamentSelect").addEventListener("change", function (ev) {
        input.hide();
        label.hide();
        submitButton.prop("disabled", true);
        $('#team1').hide();
        $('#team1Label').hide();
        $('#team2').hide();
        $('#team2Label').hide();

        const tournament = ev.target.value;

        $.ajax({
            url: '/xxlbet',
            type: 'GET',
            data: {
                command: 'get_opponents_by_tournament',
                tournament: tournament
            },
            dataType: 'json',
            success: function (dataFromServer) {
                $('#team1').empty();
                $('#team2').empty();
                $('#noTeamsInfo').text('');

                if (dataFromServer.length === 0) {
                    $('#noTeamsInfo').text('No teams available');
                } else {
                    for (var i = 0; i < dataFromServer.length; i++) {
                        $('#team1').append("<option value='" + dataFromServer[i]['name'] + "'>" + dataFromServer[i]['name'] + "</option>");
                        $('#team2').append("<option value='" + dataFromServer[i]['name'] + "'>" + dataFromServer[i]['name'] + "</option>");
                    }

                    $('#team1').show();
                    $('#team2').show();
                    input.show();
                    label.show();
                    submitButton.prop("disabled", false);
                }
            },
            error: function (e) {
                alert(e)
            }
        })
    });
});

function createMatch() {
    let errorCount = 0;

    $('#drawCoefficientInformer').text("");
    $('#team1Informer').text("");
    $('#team1CoefficientInformer').text("");
    $('#team2CoefficientInformer').text("");
    $('#dateStartedInformer').text("");

    if (document.getElementById("inputDrawCoefficient").value === "" || document.getElementById("inputDrawCoefficient").value <= 0) {
        $('#drawCoefficientInformer').text("Wrong value");
        errorCount++;
    }
    if (document.getElementById("inputTeam1Coefficient").value === "" || document.getElementById("inputTeam1Coefficient").value <= 0) {
        $('#team1CoefficientInformer').text("Wrong value");
        errorCount++;
    }
    if (document.getElementById("inputTeam2Coefficient").value === "" || document.getElementById("inputTeam2Coefficient").value <= 0) {
        $('#team2CoefficientInformer').text("Wrong value");
        errorCount++;
    }
    if (document.getElementById("dateStarted").value === "" || new Date(document.getElementById("dateStarted").value).getTime() < new Date().getTime()) {
        $('#dateStartedInformer').text("Wrong date");
        errorCount++;
    }
    if ($('#team1').val() === $('#team2').val()) {
        $('#team1Informer').text('Cannnot create match with equal opponents');
        errorCount++;
    }

    if (errorCount === 0) {
        var data = {
            tournament: $("#tournamentSelect").val(),
            team1: $('#team1').val(),
            team2: $('#team2').val(),
            draw_coefficient: document.getElementById("inputDrawCoefficient").value,
            team1_coefficient: document.getElementById("inputTeam1Coefficient").value,
            team2_coefficient: document.getElementById("inputTeam2Coefficient").value,
            date_started: document.getElementById("dateStarted").value
        };

        $.ajax({
            url: "/xxlbet?command=create_match",
            type: 'POST',
            contentType: "application/json; charset=utf-8",
            data: JSON.stringify(data),
            success: function () {
                alert("Match was created successfully!");
                window.location.reload();
            },
            error: function (e) {
                alert(e)
            }
        })
    }
}
