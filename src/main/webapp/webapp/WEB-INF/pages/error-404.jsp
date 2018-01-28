<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="ru">
  <head>
  </head>
  <link rel="stylesheet" href="/css/main.css"/>
  <style>
    body {
    	padding-top: 0;
    }
  </style>
</html>
<body class="error-bgi">
  <div class="remodal-bg wrapper">
    <div class="content" style="padding-bottom: 0;">
      <div class="l-container error"><img class="error-logo" src="/resources/img/icons/404-logo.png"/>
        <div class="error-text1">4<img src="/resources/img/icons/404-kitten.png"/>4</div>
        <div class="error-text2">Извините! Страница не найдена</div>
        <div class="error-text3">Возможно, запрашиваемая Вами страница была перенесена или удалена. Также возможно, Вы допустили небольшую опечатку при вводе адреса –  поэтому еще раз внимательно проверьте</div>
        <div class="error-text4">Переходите на главную страницу – там Вы также сможете найти много полезной информации</div><a class="error-button" href="/">На главную</a>
        <div class="error-text4">Человек читающий 30 мин в день,гораздо умнее остальных :)</div>


        <div class="row page404_article_row">
          <div id="page404_article" class="container page404_article_block">
            <%--<c:forEach var="article" items="${article404}" begin="0" end="5" varStatus="index">

              <div  class="col-md-3 page404_article_item">

                <a href="/${article.section.url}/${article.url}"><img src="/images/image/${article.mediumPicture}"/></a>

                  <div class="page404_article_name">${article.name}</div>

              </div>

            </c:forEach>--%>
          </div>
        </div>


      </div>
    </div>
  </div>
  <script src="/resources/js/scripts.min.js"></script>
  <script type="text/javascript">
      $( document ).ready(function() {
         $('.page404_article_item').css({"margin-right":"15px"});
         $('.page404_article_item:nth-child(3n+3)').css({"margin-right":"0px"})
      });

  </script>

</body>