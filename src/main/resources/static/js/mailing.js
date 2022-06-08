function action() {

    const email = $('#email').val();
    formData = {'address': email};

    fetch('/email_address', {
        method: 'post',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(formData)
    }).then(res => {
        if (res === 1)
            alert('구독신청이 완료되었습니다.');
        else
            alert('구독해제 되었습니다.');
    }).catch(err => {
        console.log(err)
    });


}