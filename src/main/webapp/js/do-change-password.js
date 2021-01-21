function change_password() {
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
            success: function () {
                alert('Password was changed successfully!');
                window.location.href = "/login";
            },
            error: function (e) {
                alert(e);
            }
        })
    }


}