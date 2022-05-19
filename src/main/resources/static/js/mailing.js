function action() {

    const email = $('#email').val();
    alert(email);

    let formData = new FormData();
    formData.append('email', email);

    fetch('/emailCheck', {
        method: 'post',
        body: JSON.stringify(formData)
    }).then(res => {

        alert(Promise.resolve(res));
        if (res === 1)
            alert('구독신청이 완료되었습니다.');
        else
            alert('구독해제 되었습니다.');
    }).catch(err => {
        console.log(err)
    });


}