function calc(a) {
    const house_price = parseInt(uncomma($('#house_price').val()));
    let loan = parseInt(uncomma($('#loan').val()));
    let loan_rate = parseFloat($('#loan_rate').val());
    let annually_rent = parseInt(uncomma($('#annually_rent').val()));
    let monthly_rent = parseInt(uncomma($('#monthly_rent').val()));
    let cost = parseInt(uncomma($('#cost').val()));

    if (a === 'b') {
        loan = 0;
        loan_rate = 0;
    }

    if (a === 'c') {
        loan = house_price;
    }

    if ($('#loan').val() === "")
        $('#loan').val('0');
    if ($('#loan_rate').val() === "")
        $('#loan_rate').val('0');

    if (house_price === 0 || $('#house_price').val() === '') {
        alert('매매가를 입력해주세요');
        document.getElementById('house_price').focus();
    } else if (house_price < loan) {
        alert('대출금을 매매가 이하로 설정해주세요');
        $('#loan').val('');
    } else {

        if ($('#loan').val() != 0 || $('#loan').val() != "") {


            if ($('#cost').val() == "")
                cost = 0;

            if (a === 'a') {
                annually_rent = 0;
            }
            if ($('#monthly_rent').val() === "") {
                monthly_rent = 0;
            }

            const investment = house_price - loan - annually_rent + cost;
            const b_investment = house_price - loan + cost;
            let annually_earn = monthly_rent * 12 - loan * loan_rate / 100;
            const annually_earn_rate = (annually_earn / investment) * 100;


            if ($('#annually_rent').val() === "") {
                alert('보증금을 입력해주세요');
                document.getElementById('annually_rent').focus();
                return 0;
            }

            if (investment > 0) {
                $('#investment').val(comma(investment) + " -- (" + comma(b_investment) + ") 만원");
            } else {
                $('#investment').val("+P: " + comma(-investment) + " 만원");
            }

            $('#annually_earn').val(comma((annually_earn / 12).toFixed(1)) + " -- (" + comma(annually_earn.toFixed(0)) + ") 만원");


            if (investment > 0) {
                $('#annually_earn_rate').val(comma(annually_earn_rate.toFixed(1)) + " %");
            } else {

                $('#annually_earn_rate').val("투자금 회수");
            }
        } else {
            $('#investment').val(comma(house_price) + " -- (" + comma(b_investment) + ") 만원");
            $('#annually_earn').val(comma(monthly_rent * 12).toFixed(1) + " 만원 -- (" + comma(monthly_rent) + "만원)");
            $('#annually_earn_rate').val("투자금 회수");
        }
    }
}

function initial() {
    location.reload();
}

function add_price2(id, id2, price) {

    if ($(id).val() === "") {
        $(id).val(0);
    }
    if (id === '#loan_rate') {
        let tmp = parseFloat($(id).val()) + parseFloat(price);

        if (tmp > 0) {
            $(id).val(tmp.toFixed(1));
        } else {
            $(id).val();
        }

    } else {
        const tmp = parseInt(uncomma($(id).val())) + parseInt(price);
        if (tmp > 0) {
            $(id).val(comma(tmp));
            up(id2);
        } else
            $(id).val();
    }
}

function add_price(id, price) {
    if ($(id).val() === "") {
        $(id).val(0);
    }

    if (id === '#loan_rate') {
        let tmp = parseFloat($(id).val()) + parseFloat(price);

        if (tmp > 0) {
            $(id).val(tmp.toFixed(1));
        } else {
            $(id).val();
        }

    } else {
        const tmp = parseInt(uncomma($(id).val())) + parseInt(price);
        if (tmp > 0) {
            $(id).val(comma(tmp));
        } else
            $(id).val();
    }
}

function up(id) {

    const tmp2 = parseInt($(id).val()) + 1;

    $(id).val(tmp2);

}

function p_price(id, price) {
    let tmp = "";

    if ($('#house_price').val() === "") {
        $('#id').val('');
        return;
    }

    tmp = parseInt(uncomma($('#house_price').val())) / 100 * parseInt(price);
    $(id).val(comma(tmp));
}


function moveFocus(next) {
    if (event.keyCode === 13) {
        document.getElementById(next).focus();
    }
}

function callkey() {
    if (event.keyCode === 13) {
        calc();
    }
}

function setzero(id) {

    if (id === "#a1" || id === "#a2" || id === "#a3" || id === "#b1" || id === "#b2" || id === "#b3") {
        $(id).val("0");
        return;
    }
    $(id).val("");
}

function inputNumberFormat(obj) {
    obj.value = comma(uncomma(obj.value));
}

function comma(str) {
    str = String(str);
    return str.replace(/(\d)(?=(?:\d{3})+(?!\d))/g, '$1,');
}

function uncomma(str) {
    str = String(str);
    return str.replace(/[^\d]+/g, '');
}