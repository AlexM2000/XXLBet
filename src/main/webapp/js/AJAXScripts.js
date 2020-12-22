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
    if (document.getElementById("InputSurName").value.length === 0) {
        $('#surnameInformer').text("This field must not be empty");
        errorCount++;
    }
    if (document.getElementById("InputName").value.length === 0) {
        $('#nameInformer').text("This field must not be empty");
        errorCount++;
    }
    if (document.getElementById("InputSecondName").value.length === 0) {
        $('#secondNameInformer').text("This field must not be empty");
        errorCount++;
    }
    if (document.getElementById("InputBirthDate").value === "" || new Date(document.getElementById("InputBirthDate").value).getTime() > new Date().getTime()) {
        $('#birthDateInformer').text("Wrong date");
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

        $.ajax({
            url: '/xxlbet',
            type: 'POST',
            dataType: "json",
            data: {
                command: "registration",
                body: {
                    "email": email,
                    "phoneNumber": phoneNumber,
                    "surname": document.getElementById("InputSurName").value,
                    "name": document.getElementById("InputName").value,
                    "secondName": document.getElementById("InputSecondName").value,
                    "password": document.getElementById("InputPassword").value,
                    "repeatPassword": document.getElementById("InputRPassword").value,
                    "birthDate": document.getElementById("InputBirthDate").value
                }
            },
            success: function (dataFromServer) {
                if(dataFromServer.status === 'verified'){
                    window.location.href = '/xxlbet?command=confirm_page';
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

                    $('#surnameInformer').text(dataFromServer['field.is.empty'])
                    $('#nameInformer').text(dataFromServer['field.is.empty'])
                    $('#surnameInformer').text(dataFromServer['field.is.empty'])
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

function login() {
    var data = {
        "login" : document.getElementById("InputLogin").value,
        "password": document.getElementById("InputPassword").value
    };

    $.ajax({
        url: '/xxlbet',
        type: 'POST',
        data: {
            command: "login",
            body: data
        },
        dateType: "json",
        success: function (dataFromServer) {
            dataFromServer = JSON.parse(dataFromServer);

            if (dataFromServer.status === 'verified'){
                window.location.href = 'profile';
            } else if (dataFromServer.status === 'failed') {
                if (dataFromServer['user.email.not.exists'] !== null) {
                    $('#loginError').text(dataFromServer['user.email.not.exists']);
                }
                if (dataFromServer['user.please.confirm.registration'] !== null) {
                    $('#loginError').text(dataFromServer['user.please.confirm.registration']);
                }

                if (dataFromServer['wrong.password'] !== null) {
                    $('#passError').text(dataFromServer['wrong.password']);
                }
            } else {
                alert('Stop doing this!!!');
            }
        },
        error: function (e) {
            alert(e);
        }
    });
}

function languageSelector(language){
    $.ajax({
        url: '/xxlbet',
        data: ({
            command: "lang",
            lang : language
        }),
        success: function (data) {
            document.location.reload();
        },
        error: function (e) {
            alert('ERROR_ERROR!!!');
        }
    });
}