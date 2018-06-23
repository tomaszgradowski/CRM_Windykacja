<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form"
           uri="http://www.springframework.org/tags/form" %>
<%@    taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Lista użytkowników</title>
    <link href="style.css" rel="stylesheet">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
<%@include file="/WEB-INF/resources/fragments/menu.jsp" %>

<div class="page-header">
    <h1><p class="bg-primary  text-center">Lista użytkowników: </p></h1>
</div>

<br>

<div class="container">
    <table class="table table-bordered">
        <tr>
            <th>Id</th>
            <th>Imie:</th>
            <th>Nazwisko</th>
            <th>Login</th>
            <th>Email</th>
            <th>Usuń</th>
            <th>Edytuj</th>
            <th>Przypisz uprawnienia</th>
            <%--<th>Zmień hasło</th>--%>
        </tr>
        <c:forEach items="${users}" var="user">
            <tr>
                <td><c:out value="${user.id}"/></td>
                <td><c:out value="${user.firstName}"/></td>
                <td><c:out value="${user.lastName}"/></td>
                <td><c:out value="${user.username}"/></td>
                <td><c:out value="${user.email}"/></td>

                <td><a href="/user/delete?userId=${user.id}">Usuń</a></td>
                <td><a href="/user/form?userId=${user.id}">Edytuj</a></td>
                <td><a href="/authority/form?userId=${user.id}">Przypisz uprawnienia</a></td>
                <%--<td><a href="/user/password?userId=${user.id}">Zmień hasło</a></td>--%>
            </tr>
        </c:forEach>
        <br>
    </table>
</div>

<div class="container">
    <button class="btn btn-primary" onClick="javascript:location.href='/user/form'">
        Dodaj użytkownika
    </button>
</div>

</body>
</html>