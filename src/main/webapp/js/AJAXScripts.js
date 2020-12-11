function registration() {
    var data = $('#registration').serialize();

    console.log(data);
    $.ajax({
        url: '/registration',
        type: 'POST',
        data: data,
        success: function (dataFromServer) {
            if(dataFromServer.status === 'verified'){
                window.location.href = '/confirm';
            } else if (dataFromServer.status === 'failed') {
                $('#registration').find('small').text('');

                $('#emailErrorInformer').text(dataFromServer.emailError);
                $('#loginErrorInformer').text(dataFromServer.loginError);
                $('.password_field').text(dataFromServer.passError);
            } else{
                alert(dataFromServer);
            }
        },
        error: function (e) {
            alert(e);
        }
    });
}

function languageSelector(language){
    $.ajax({
        url: '/lang',
        data: ({lang : language}),
        success: function (data) {
            document.location.reload();
        },
        error: function (e) {
            alert('ERROR_ERROR!!!');
        }
    });
}