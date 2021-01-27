function createSport() {
    $('#sportNameInformer').text('');
    var error_count = 0;
    var regexp = new RegExp("^[a-zA-Z ]|^[А-Яа-я ]+$");
    const sport = document.getElementById("sportName").value;

    if (sport === "" || !regexp.test(sport)) {
        $('#sportNameInformer').text('Invalid value');
        error_count++;
    }

    if (error_count === 0) {
        $.ajax({
            url: "/xxlbet?command=create_sport",
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
            error: function () {
                window.location.href = '/500';
            }
        })
    }
}