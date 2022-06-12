function action() {
    var re = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    const email = $('#email').val();
    if (email == '' || !re.test(email)) {
        alert("올바른 이메일 주소를 입력 하세요")
        return false;
    } else {
        $.ajax(
            {
                type: 'post',
                url: '/email_address',
                contentType: 'application/json',
                dataType: 'json',
                data: JSON.stringify({'email_address': email}),

                success: function (result) {
                    if (result == 1) {
                        alert('구독 신청이 완료 되었습니다.');
                        location.reload();
                    } else {
                        alert('구독 신청이 해제 되었습니다.');
                        location.reload();
                    }
                }
            }
        )
    }
}

function enterkey() {
    if (window.event.keyCode == 13) {
        action();
    }
}