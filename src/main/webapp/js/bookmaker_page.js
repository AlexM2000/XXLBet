function toggleForm() {
    var form = document.getElementById("userRolesEditForm");
    var selectRole = document.getElementById("InputRole");
    var selectStatus = document.getElementById("InputStatus");
    var changeDataButton = document.getElementById("changeDataButton");
    var changeButton = document.getElementById("changeButton");
    form.style.display = (form.style.display === 'none' ? 'block' : 'none');
    selectRole.style.display = (form.style.display === 'none' ? 'block' : 'none');
    selectStatus.style.display = (form.style.display === 'none' ? 'block' : 'none');
    changeDataButton.style.display = (form.style.display === 'none' ? 'block' : 'none');
    changeButton.style.display = (form.style.display === 'none' ? 'block' : 'none');
}

function changeUserRoleAndStatus(email) {
    var data = {
        email: email,
        role: document.getElementById("InputRole").value,
        status: document.getElementById("InputStatus").value
    };

    $.ajax({
        url: '/xxlbet?command=change',
        type: 'POST',
        contentType: 'application/json; charset=utf-8',
        data: JSON.stringify(data),
        success: function () {
            window.location.reload();
        }
    });
}