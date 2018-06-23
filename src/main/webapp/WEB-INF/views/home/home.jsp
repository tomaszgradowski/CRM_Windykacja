
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Strona główna</title>
    <%@ taglib prefix="form"
               uri="http://www.springframework.org/tags/form" %>
    <%@	taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

</head>
<body>
<%@include file="/WEB-INF/resources/fragments/menu.jsp" %>



<%--<a href="/actiontype/form">Dodaj nowy kod akcji</a><br>--%>
<%--<a href="/actiontype/list">Lista kodów akcji</a><br>--%>

<%--<a href="/resulttype/form">Dodaj nowy rezultat akcji</a><br>--%>
<%--<a href="/resulttype/list">Lista rezultatów akcji</a><br>--%>

<%--<a href="/acttyperestype/form">Przypisz rezultat do akcji</a><br>--%>

<%--<a href="/user/form">Dodaj użytkownika</a><br>--%>
<%--<a href="/user/list">Lista użytkowników</a><br>--%>


</body>
</html>
