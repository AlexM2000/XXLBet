

function createSport() {
    $('#sportNameInformer').text('');
    var error_count = 0;
    var regexp = new RegExp("^[\\\\p{L} ]+$");
    const sport = document.getElementById("sportName").value;

    if (sport === "" || !regexp.test(sport)) {
        $('#sportNameInformer').text('Invalid value');
        error_count++;
    }

    if (error_count === 0) {
        $.ajax({
            uri: "/xxlbet?command=create_sport",
            type: 'POST',
            contentType: 'text/plain; charset=utf-8',
            data: sport,
            success: function(dataFromServer) {
                if (dataFromServer['sport.already.exists'] === null) {
                    alert('Sport was created successfully')
                    window.location.reload();
                } else {
                    $('#sportNameInformer').text('');
                    $('#sportNameInformer').text(dataFromServer['sport.already.exists']);
                }
            }
        })
    }
}