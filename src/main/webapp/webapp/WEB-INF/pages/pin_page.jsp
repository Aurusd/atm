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
            height: 650px;
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
            color:aqua;
        }
        .exit {
            color: brown;
            width: calc(100% - 30px);
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
<form:form action="/card/pinCheck" id="formSubmit" modelAttribute="pinRequest" enctype="multipart/form-data">
<input name="password" value="" type="hidden">
    <h2>Enter pin number</h2>
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
            <div class="numBlock-number exit">Exit</div>
        </div>
    </div>
</div>


<script>
    // Вешаем обработчик на кнопки
    document.querySelector('.numBlock').addEventListener('click', numpadHandler);
    var inputD = document.querySelector('input[name="password"]');
    var pin = "";

    // Обработка кнопок
    function numpadHandler(e) {
        var result = document.querySelector('.numBlock-result');


        // обработка конопок с цифрами
        if (e.target.classList.contains('numBlock-number')
            && !e.target.classList.contains('clear')
            && !e.target.classList.contains('ok')
            && !e.target.classList.contains('exit')
            && result.textContent.length < 4)
        {
            result.textContent += "*";
            pin += e.target.textContent;
            inputD.value = pin;
            console.log(pin);
        }

        // Обработка "ок"
        if(e.target.classList.contains('ok'))
        {
            formSubmit.submit();
            /*var data = pin;
            var xhr = new XMLHttpRequest();
            xhr.open('GET', 'url');
            xhr.send(pin);

            xhr.onreadystatechange = function() {
                if (xhr.readyState != 4) return;

                if (xhr.status != 200) {
                    alert('Ошибка : ' + xhr.status + ': ' + xhr.statusText);
                } else {
                    alert('Ответ запроса: ' + xhr.responseText);
                }

            }*/

        }

        // Обработка "clear"
        if(e.target.classList.contains('clear'))
        {
            result.textContent = '';
            pin = '';
        }

        // Обработка "exit" ????
        if(e.target.classList.contains('exit'))
        {
            console.log('exit');
            document.location.href = '/home';

        }
    }

</script>
</form:form>
</body>
</html>