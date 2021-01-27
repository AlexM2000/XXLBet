function unlink_credit_card() {
    const data = {
        user_id: document.getElementById("userId").value,
        number: document.getElementById("chooseCard").value
    };

    $.ajax({
        url: '/xxlbet?command=unlink_credit_card',
        type: 'POST',
        contentType: 'application/json; charset=utf-8',
        data: JSON.stringify(data),
        success: function () {
            alert('Credit card was unlinked successfully!');
            window.location.reload();
        },
        error: function () {
            window.location.href = '/500';
        }
    })
}