function change_password() {
    $('#passwordInformer').text('');
    let error_count = 0;

    if (document.getElementById("password").value !== document.getElementById("repeatPassword").value) {
        $('#passwordInformer').text('Passwords don\'t match');
        error_count++;
    }


    if (error_count === 0) {
        const data = {
            user_id: document.getElementById("userId").value,
            password: document.getElementById("password").value
        };

        $.ajax({
            url: '/xxlbet?command=change_password',
            type: 'POST',
            contentType: 'application/json; charset=UTF-8',
            data: JSON.stringify(data),
            success: function (dataFromServer) {
                if (dataFromServer['password.not.matches.regexp']) {
                    $('#passwordInformer').text(dataFromServer['password.not.matches.regexp']);
                } else {
                    alert('Password was changed successfully!');
                    window.location.href = "/login";
                }
            },
            error: function () {
                window.location.href = '/500';
            }
        })
    }


}