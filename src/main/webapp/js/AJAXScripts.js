function registration() {
    var email = document.getElementById("InputEmail").value;
    var phoneNumber = document.getElementById("InputPhoneNumber").value;
    var password = document.getElementById("InputPassword").value;
    var emailRegex = RegExp("/^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$/");
    var phoneNumberRegex = RegExp("^(80|375)(25|29|33|44)(\\\\d{7})$");
    var passwordRegexp = RegExp("^((?=.*[a-z])(?=.*[A-Z])(?=.*[\\\\W_\\\\d])).{7,20}$");
    var errorCount = 0;

    if (!passwordRegexp.test(password)) {
        $('#passwordErrorInformer').text("Password should contain at least one small letter, one big letter, one number, one special character, minimum 7 maximum 20 symbols");
        errorCount++;
    }

    if (errorCount === 0) {
        var data = {
            "email": email,
            "phoneNumber": phoneNumber,
            "surname": document.getElementById("InputSurName").value,
            "name": document.getElementById("InputName").value,
            "secondName": document.getElementById("InputSecondName").value,
            "password": document.getElementById("InputPassword").value,
            "repeatPassword": document.getElementById("InputRPassword").value,
            "birthDate": document.getElementById("InputBirthDate").value
        };

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

                    if (dataFromServer['user.email.already.exists'] != null) {
                        $('#emailErrorInformer').text(dataFromServer['user.email.already.exists']);
                    }
                    if (dataFromServer['user.please.confirm.registration'] != null) {
                        $('#emailErrorInformer').text(dataFromServer['user.please.confirm.registration']);
                    }
                    if (dataFromServer['email.not.matches.regexp'] != null) {
                        $('#emailErrorInformer').text(dataFromServer['email.not.matches.regexp']);
                    }

                    $('#phoneErrorInformer').text(dataFromServer['phonenumber.not.matches.regexp']);
                    $('#passwordErrorInformer').text(dataFromServer['password.not.matches.regexp']);
                } else{
                    alert(dataFromServer);
                }
            },
            error: function (e) {
                alert(e);
            }
        });
    }
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