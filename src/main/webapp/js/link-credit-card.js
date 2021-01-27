function link_credit_card() {
    const cvvRegex = new RegExp('^\\d{3}$');
    const thruRegex = new RegExp('(0[1-9]|1[0-2])/\\d{2}');
    const numberRegex = new RegExp('^\\d{16}$');
    $('#numberInformer').text('');
    $('#cvvInformer').text('');
    $('#thruInformer').text('');
    let error_count = 0;
    let userId = document.getElementById("userId").value;
    let number = document.getElementById("number").value;
    let cvv = document.getElementById("cvv").value;
    let thru = document.getElementById("thru").value;

    if (!numberRegex.test(number)) {
        $('#numberInformer').text('Invalid value');
        error_count++;
    }

    if (!thruRegex.test(thru)) {
        $('#thruInformer').text('Invalid value');
        error_count++;
    }

    if (!cvvRegex.test(cvv)) {
        $('#cvvInformer').text('Invalid value');
        error_count++;
    }

    if (error_count === 0) {
        const data = {
            number: number,
            thru: thru,
            cvv: cvv,
            userId: userId
        };

        $.ajax({
            url: "/xxlbet?command=link_credit_card",
            type: 'POST',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data),
            success: function (dataFromServer) {
                if (!(dataFromServer['status'] === 'verified')) {
                    $('#numberInformer').text(dataFromServer['credit.card.number.invalid']);
                    $('#cvvInformer').text(dataFromServer['credit.card.cvv.invalid']);
                    $('#thruInformer').text(dataFromServer['credit.card.thru.invalid']);
                } else {
                    alert('Credit card was connected successfully');
                    window.location.reload();
                }
            },
            error: function () {
                window.location.href = '/500';
            }
        })
    }
}