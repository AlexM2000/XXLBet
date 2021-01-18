$(document).ready(function (){
    document.getElementById("sportSelect").addEventListener("change", function (ev) {
        if (ev.target.value === "") {
            $("#tournamentNameLabel").hide();
            $('#tournamentName').hide();
            $('#tournamentName').text('');
            $('#tournamentNameInformer').text('');
        } else {
            $("#tournamentNameLabel").show();
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
        $.ajax({
            url: "/xxlbet?command=create_tournament",
            type: 'POST',
            contentType: 'text/plain; charset=utf-8',
            data: sport,
            success: function(dataFromServer) {
                if (!dataFromServer['sport.already.exists']) {
                    alert('Sport was created successfully')
                    window.location.reload();
                } else {
                    $('#sportNameInformer').text('');
                    $('#sportNameInformer').text(dataFromServer['sport.already.exists']);
                }
            },
            error: function (e){
                alert(e)
            }
        })
    }
}