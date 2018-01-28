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

        body {
            background-color: #EFF3F8 !important
        }
        td {border: 1px solid black}

    </style>
</head>
<body>

<div class="block">
    <div class="block-heading">Operations report</div>
    <div class="block-body">
        <button class="button-primary btn-small" onclick="location.href = '/operation'">Back</button>
        <button class="button-primary btn-small" onclick="location.href = '/home'">Exit</button>
        <table style="width: 100%;">
            <thead>
            <tr>
                <td>Card Number</td>
                <td>Date/time</td>
                <td>Amount withdrawal</td>
                <td>Balance</td>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td>${number}</td>
                <td>${date}</td>
                <td>${amount}</td>
                <td>${balance}</td>
            </tr>
            </tbody>
        </table>
    </div>
</div>

</body>
</html>