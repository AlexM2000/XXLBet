function create_change_password_request() {
    $('#emailInformer').text('');
    const data = document.getElementById("email").value;


    $.ajax({
        url: '/xxlbet?command=create_change_password_request',
        type: 'POST',
        contentType: 'text/plain; charset=UTF-8',
        dataType: 'json',
        data: data,
        success: function (dataFromServer) {
            if (dataFromServer['user.email.not.exists']) {
                $('#emailInformer').text(dataFromServer['user.email.not.exists']);
            } else {
                alert('Link for changing password was sent to your email!\nIt will expire in 24 hours');
                window.location.reload();
            }
        },
        error: function () {
            window.location.href = '/500';
        }
    })
}