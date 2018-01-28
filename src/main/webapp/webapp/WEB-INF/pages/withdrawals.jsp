<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/skeleton/2.0.4/skeleton.css">
    <title>Document</title>
    <style>
        .block {
            height: 300px;
            width: 500px;
            border: 2px solid #000;
            border-radius: 3px;
            margin: 0 auto;
            margin-top: 100px;
            background: white;
        }

        .block-heading {
            border-bottom: 1px solid #ccc;
            padding-left: 10px;
        }

        .block-body {

        }

        .block-body .btn-small {
            float: right;
            margin-right: 10px;
            margin-top: 10px;
        }

        .btn-small {
            height: 22px;
            line-height: 22px;
        }

        th:first-child, td:first-child {
            padding-left: 15px;
        }

        th:last-child, td:last-child {
            padding-right: 15px;
        }

        .nums {
            display: flex;
            flex-wrap: wrap;
            justify-content: center;
            width: 200px;
            float: left;
            margin-left: 15px;
        }

        .num {
            width: 30%;
            height: 40px;
            text-align: center;
            line-height: 40px;
            margin: 2px;
            border: 1px solid #000;
        }

        .btns {
            width: 200px;
        }

        .mt-3 {
            margin-top: 50px;
            margin-left: 30px;
        }

        input {
            margin-top: 15px;
            margin-left: 15px;
            width: 60%;
        }

        .ml-2 {
            margin-left: 20px;
        }

        body {
            background-color: #EFF3F8 !important
        }

        strong {
            transform: translateX(-50%);
            left: 50%;
            top: 80px;
            position: fixed;
            color: #FF0A37;
        }
    </style>
</head>
<body>
<c:if test="${error != null}">
    <strong>${error}</strong>
</c:if>
<form:form name="myForm" id="myForm" action="/card/withdrawals" modelAttribute="amount" enctype="multipart/form-data">
    <div class="block">
        <div class="block-heading">withdrawal</div>
        <div class="block-body">
            <input name="withdraw" type="text" value="">
            <button class="button-primary ml-2" type="submit">Ok</button>
            <br>
            <div class="nums">
                <div class='num'>1</div>
                <div class='num'>2</div>
                <div class='num'>3</div>
                <div class='num'>4</div>
                <div class='num'>5</div>
                <div class='num'>6</div>
                <div class='num'>7</div>
                <div class='num'>8</div>
                <div class='num'>9</div>
                <div class='num'>0</div>
            </div>

            <button class="button-primary mt-3 delete" type="button">Delete</button>
            <button class="button-primary mt-3" type="button" onclick="location.href = '/operation'">Exit</button>

        </div>
    </div>
</form:form>
<script>

    // Вешаем обработчик на кнопки
    document.querySelector('.block-body').addEventListener('click', numpadHandler);

    // Обработка кнопок
    function numpadHandler(e) {
        var result = document.querySelector("input");

        // обработка конопок с цифрами
        if (e.target.classList.contains('num')) {
            result.value += e.target.textContent;
            console.log(result.value);
            document.querySelector('input').value = result.value;
        }

    }

    document.querySelector('#myForm').addEventListener('submit', checkBalans);
    document.querySelector('.delete').addEventListener('click', deleteLastChar);

    function deleteLastChar() {
        var inp = document.querySelector("input");
        var inpVal = document.querySelector("input").value;
        inp.value = inpVal.substring(0, inpVal.length-1);
    }

    function checkBalans(e) {
        var amount = document.querySelector("input");
        var xhr = new XMLHttpRequest();
        xhr.open('GET', '/card/withdrawals' + '?amount=' + amount.value);
        xhr.send();
        xhr.onreadystatechange = function () { // (3)
            if (xhr.readyState != 4) return;

            if (xhr.status == 200) {
                e.target.submit();
                // document.myForm.mySubmit.click();
                // formElement.addEventlistener('submit', triggerFunc);

            } else {
                showModal();
            }
        };
        e.preventDefault();
    }

    function showModal() {
        var div = document.createElement('div');
        div.textContent = "No enough of money!";
        div.setAttribute('class', 'tag-error');
        div.setAttribute('style',
            'position: fixed;' +
            'background-color: red;' +
            'padding: 20px;' +
            'top: 50px;' +
            'transform: translateX(-50%);' +
            'left: 50%;' +
            'color: white;' +
            'border-radius: 10px;' +
            'font-size: 16px;');
        document.body.appendChild(div);
        setTimeout(function () {
            document.querySelector('.tag-error').remove();
        }, 3000);
    }
</script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
</body>
</html>