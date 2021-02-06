$(document).ready(function () {
    // Close the dropdown menu if the user clicks outside of it
    window.onclick = function (event) {
        if (!event.target.matches('.dropbtn')) {
            var dropdowns = document.getElementsByClassName("dropdown-content");
            var i;
            for (i = 0; i < dropdowns.length; i++) {
                var openDropdown = dropdowns[i];
                if (openDropdown.classList.contains('show')) {
                    openDropdown.classList.remove('show');
                }
            }
        }
    }
})

/* When the user clicks on the button,
toggle between hiding and showing the dropdown content */
function myFunction() {
    document.getElementById("myDropdown").classList.toggle("show");
}

function registration() {
    var email = document.getElementById("InputEmail").value;
    var phoneNumber = document.getElementById("InputPhoneNumber").value;
    var password = document.getElementById("InputPassword").value;
    var email_regex = RegExp("/^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$/");
    var phone_number_regex = RegExp("^(80|375)(25|29|33|44)(\\\\d{7})$");
    var password_regexp = RegExp("^((?=.*[a-z])(?=.*[A-Z])(?=.*[\\\\W_\\\\d])).{7,20}$");
    var error_count = 0;
    $('#emailErrorInformer').text('');
    $('#phoneErrorInformer').text('');
    $('#passwordErrorInformer').text('');
    $('#rPasswordErrorInformer').text('');
    $('#surnameInformer').text('');
    $('#nameInformer').text('');
    $('#secondNameInformer').text('');
    $('#birthDateInformer').text('');

    if (!password_regexp.test(password)) {
        $('#passwordErrorInformer').text("Password should contain at least one small letter, one big letter, one special character, minimum 7 maximum 20 symbols");
        error_count++;
    }
    if (document.getElementById("InputSurName").value.length === 0) {
        $('#surnameInformer').text("This field must not be empty");
        error_count++;
    }
    if (document.getElementById("InputName").value.length === 0) {
        $('#nameInformer').text("This field must not be empty");
        error_count++;
    }
    if (document.getElementById("InputSecondName").value.length === 0) {
        $('#secondNameInformer').text("This field must not be empty");
        error_count++;
    }
    if (document.getElementById("InputPassword").value !== document.getElementById("InputRPassword").value) {
        $('#rPasswordErrorInformer').text('Passwords does not match!');
        error_count++;
    }
    if (document.getElementById("InputBirthDate").value === "" || new Date(document.getElementById("InputBirthDate").value).getTime() > new Date().getTime()) {
        $('#birthDateInformer').text("Wrong date");
        error_count++;
    }

    if (error_count === 0) {
        const data = {
            "email": email,
            "phone_number": phoneNumber,
            "surname": document.getElementById("InputSurName").value,
            "name": document.getElementById("InputName").value,
            "second_name": document.getElementById("InputSecondName").value,
            "password": document.getElementById("InputPassword").value,
            "repeat_password": document.getElementById("InputRPassword").value,
            "birth_date": document.getElementById("InputBirthDate").value
        };

        $.ajax({
            url: '/xxlbet?command=registration',
            type: 'POST',
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            data: JSON.stringify(data),
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
                    if (dataFromServer['passwords.not.match'] != null) {
                        $('#rPasswordErrorInformer').text(dataFromServer['passwords.not.match']);
                    }
                    if (dataFromServer['phonenumber.not.matches.regexp'] != null) {
                        $('#phoneErrorInformer').text(dataFromServer['phonenumber.not.matches.regexp']);
                    }
                    if (dataFromServer['user.with.phonenumber.exists'] != null) {
                        $('#phoneErrorInformer').text(dataFromServer['user.with.phonenumber.exists']);
                    }
                    if (dataFromServer['password.not.matches.regexp'] != null) {
                        $('#passwordErrorInformer').text(dataFromServer['password.not.matches.regexp']);
                    }

                    $('#surnameInformer').text(dataFromServer['field.is.empty'])
                    $('#nameInformer').text(dataFromServer['field.is.empty'])
                    $('#secondNameInformer').text(dataFromServer['field.is.empty'])
                } else{
                    alert(dataFromServer);
                }
            },
            error: function () {
                window.location.href = '/500';
            }
        });
    }
}

function login() {
    const data = {
        "login": document.getElementById("InputLogin").value,
        "password": document.getElementById("InputPassword").value
    };

    $.ajax({
        url: '/xxlbet?command=login',
        type: 'POST',
        data: JSON.stringify(data),
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        success: function (dataFromServer) {
            if (dataFromServer.status === 'verified'){
                window.location.href = '/xxlbet?command=profile';
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
        error: function () {
            window.location.href = '/500';
        }
    });
}

function languageSelector(){
    const language = document.getElementById('lang').value;

    $.ajax({
        url: '/xxlbet',
        data: ({
            command: "lang",
            lang : language
        }),
        success: function (data) {
            document.location.reload();
            const options = document.querySelectorAll('#lang option');

            for (let i = 0; i < options.length; i++) {
                if (options[i].value === language) {
                    options[i].selected = true;
                    break;
                }
            }
        },
        error: function () {
            window.location.href = '/500';
        }
    });
}

function out() {
    $.ajax({
        url: '/xxlbet?command=logout',
        type: 'POST',
        success: function() {
            window.location.href = '/home';
        }
    })
}