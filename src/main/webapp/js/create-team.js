$(document).ready(function () {

    document.getElementById("sportSelect").addEventListener("change", function (ev) {
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
});

function createTeam() {
    const regexp = new RegExp("^[a-zA-Z ]|^[А-Яа-я ]+$");
    $("#sportInformer").text('')
    $('#tournamentNameInformer').text('')
    $('#teamNameInformer').text('');
    const team = document.getElementById("teamName").value;
    let error_count = 0;

    if (team === "" || !regexp.test(team)) {
        $('#tournamentNameInformer').text('Invalid value');
        error_count++;
    }

    if(document.getElementById("sportSelect").value === "") {
        $('#sportInformer').text('Invalid value');
        error_count++;
    }

    if(document.getElementById("tournamentSelect").value === "") {
        $('#noTournamentsInfo').text('');
        $('#noTournamentsInfo').text('Invalid value');
        error_count++;
    }

    if (error_count === 0) {
        const data = {
            tournament: document.getElementById("tournamentSelect").value,
            name: document.getElementById("teamName").value
        };

        $.ajax({
            url: '/xxlbet?command=create_team',
            type: "POST",
            contentType: "application/json; charset=utf-8",
            data: JSON.stringify(data),
            dataType: "json",
            success: function (dataFromServer) {
                if (dataFromServer['team.already.exists']) {
                    $('#teamNameInformer').text(dataFromServer['team.already.exists'])
                } else {
                    alert('Team was created successfully!');
                    window.location.reload();
                }
            },
            error: function () {
                window.location.href = '/500';
            }
        })
    }
}