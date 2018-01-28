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
            min-height: 300px;
            width: 700px;
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
        body {background-color: #EFF3F8!important }

        td {
            border: 1px solid #000;
        }
    </style>
</head>
<body>

<div class="block">
    <div class="block-heading">Operations</div>
    <div class="block-body" style="margin-left: 170px; margin-top: 20px;">
        <button class="button-primary" onclick="location.href = '/operation/101'">Balance</button>
        <button class="button-primary" onclick="location.href = '/operation/102'">Remove sum</button>
        <button class="button-primary" onclick="location.href = '/home'">Exit</button>

    </div>
    <table style="width: 650px; margin: 0 auto;">
        <thead>
        <tr>
            <td style="width: 140px">Card ID</td>
            <td>date</td>
            <td>operation code</td>
            <td>amount</td>
            <td>balance</td>
        </tr>
        </thead>
        <tbody>

        <c:forEach var="operation" items="${operations}" varStatus="index">
            <tr>
                <td>${operation.cardNumber}</td>
                <td>${operation.date}</td>
                <td>${operation.operationCode}</td>
                <td>${operation.amount}</td>
                <td>${operation.balance}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

</div>

</body>
</html>