<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
    <style>
        .numBlock {
            height: 550px;
            width: 400px;
            margin: 0 auto;
            margin-top: 200px;
            background-color: #ccc;
            padding: 15px;
        }

        .numBlock-result {
            height: 80px;
            width: 100%;
            background-color: white;
            line-height: 80px;
            font-size: 28px;
            text-align: center;
        }

        .numBlock-numbers {
            display: flex;
            flex-wrap: wrap;
            justify-content: space-around

        }

        .numBlock-number {
            width: calc(100% / 3 - 30px);
            height: 100px;
            line-height: 100px;
            margin-top: 15px;
            text-align: center;
            background-color: #89a092;
            color: black;
            font-size: 30px;

        }

        .clear {
            color: red;
        }

        .ok {
            color: aqua;
        }
        body {background-color: #EFF3F8!important }
        h2 {
            transform: translateX(-50%); left: 50%; top: 145px; position: fixed;
        }
        strong {
            transform: translateX(-50%); left: 50%; top: 110px; position: fixed; color: #FF0A37;
        }
    </style>
</head>
<body>
<c:if test="${error != null}">
    <strong>${error}</strong>
</c:if>
<form:form action="/card/check" id="formSubmit" modelAttribute="request" enctype="multipart/form-data">
<input name="number" value="" type="hidden">
    <h2>Enter your card number</h2>
<div class="container">
    <div class="numBlock">
        <div class="numBlock-result"></div>
        <div class="numBlock-numbers">
            <div class="numBlock-number">1</div>
            <div class="numBlock-number">2</div>
            <div class="numBlock-number">3</div>
            <div class="numBlock-number">4</div>
            <div class="numBlock-number">5</div>
            <div class="numBlock-number">6</div>
            <div class="numBlock-number">7</div>
            <div class="numBlock-number">8</div>
            <div class="numBlock-number">9</div>
            <div class="numBlock-number clear">Clear</div>
            <div class="numBlock-number">0</div>
            <div class="numBlock-number ok">Ok</div>
        </div>
    </div>
</div>


<script>
    // Вешаем обработчик на кнопки
    document.querySelector('.numBlock').addEventListener('click', numpadHandler);

    // Обработка кнопок
    function numpadHandler(e) {
        var result = document.querySelector('.numBlock-result');
        var inputD = document.querySelector('input[name="number"]');

        // обработка конопок с цифрами
        if (e.target.classList.contains('numBlock-number')
            && !e.target.classList.contains('clear')
            && !e.target.classList.contains('ok')
            && result.textContent.length <= 18) {
            result.textContent += e.target.textContent;
            inputD.value += e.target.textContent;
            console.log(result.textContent.length);
            if (result.textContent.length == 4
                || result.textContent.length == 9
                || result.textContent.length == 14) {
                result.textContent += '-';
                inputD.value += '-';
            }
        }

        // Обработка "ок"
        if (e.target.classList.contains('ok')) {
            formSubmit.submit();
                       // var data = result.textContent;
            // var xhr = new XMLHttpRequest();
            // xhr.open('GET', 'url');
            // xhr.send(data);
            //
            // xhr.onreadystatechange = function () {
            //     if (xhr.readyState != 4) return;
            //
            //     if (xhr.status != 200) {
            //         alert('Ошибка : ' + xhr.status + ': ' + xhr.statusText);
            //     } else {
            //         alert('Ответ запроса: ' + xhr.responseText);
            //     }
            //
            // }


        }

        // Обработка "clear"
        if (e.target.classList.contains('clear')) {
            result.textContent = '';
            inputD.value = "";
        }
    }

</script>
</form:form>

</body>
</html>