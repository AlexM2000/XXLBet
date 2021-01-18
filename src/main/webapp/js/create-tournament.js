$(document).ready(function (){
    document.getElementById("sportSelect").addEventListener("change", function (ev) {
        if (ev.target.value === "") {
            $("#tournamentLabel").hide();
            $('#tournamentSelect').hide();
            $('#tournamentName').text('');
            $('#tournamentNameInformer').text('');
        } else {
            $("#tournamentLabel").show();
            $('#tournamentName').show();
        }
    });
})

function createTournament() {
    $('#tournamentNameInformer').text('');
    $('#sportNameInformer').text('');
    let error_count = 0;
    const regexp = new RegExp("^[a-zA-Z ]|^[А-Яа-я ]+$");
    const tournament = document.getElementById("tournamentName").value;

    if (tournament === "" || !regexp.test(tournament)) {
        $('#tournamentNameInformer').text('Invalid value');
        error_count++;
    }

    if(document.getElementById("sportSelect").value === "") {
        $('#sportInformer').text('Invalid value');
        error_count++;
    }

    if (error_count === 0) {
        const data = {
            sport_id: document.getElementById("sportSelect").value,
            name: document.getElementById("tournamentName").value
        }

        $.ajax({
            url: "/xxlbet?command=create_tournament",
            type: 'POST',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data),
            success: function(dataFromServer) {
                if (!dataFromServer['tournament.already.exists']) {
                    alert('Tournament was created successfully')
                    window.location.reload();
                } else {
                    $('#tournamentNameInformer').text('');
                    $('#tournamentNameInformer').text(dataFromServer['tournament.already.exists']);
                }
            },
            error: function (e){
                alert(e)
            }
        })
    }
}