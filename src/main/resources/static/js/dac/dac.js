let text = '';
for (let elem of document.querySelectorAll('*')) {
    elem.addEventListener("click",
        function (event) {
            text += elem.tagName + ' > ';
            console.log(text);

            if (no == document.querySelectorAll('*').length)
                zero();

        }, true)
}

function zero() {

    text = '';
}